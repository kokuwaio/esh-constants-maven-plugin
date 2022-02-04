package io.kokuwa.edge.esh.constants.maven.plugin;

import com.squareup.javapoet.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.lang.model.element.Modifier.*;

/**
 * Mojo for generating constant class from "ESH-INF" folder. The plugin scans for XML files in the input folder and
 * looks for a couple of XPath matching nodes to extract constants strings and writes them to a Java constants class
 * using a Freemarker template.
 *
 * @author Fabian Schlegel
 */
@Mojo(name = "generate-esh-constants", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateConstantsMojo extends AbstractMojo {

	/**
	 * property names which are already defined in org.eclipse.smarthome.core.thing.Thing
	 */
	private static final Set<String> STANDARD_PROPERTIES = Set.of(
			/* the key for the vendor property */
			"vendor",
			/* the key for the model ID property */
			"modelId",
			/* the key for the serial number property */
			"serialNumber",
			/* the key for the hardware version property */
			"hardwareVersion",
			/* the key for the firmware version property */
			"firmwareVersion",
			/* the key for the MAC address property */
			"macAddress"
	);
	public static final ClassName CHANNEL_UID_CLASS_NAME =
			ClassName.get("org.eclipse.smarthome.core.thing", "ChannelUID");
	public static final ClassName THING_TYPE_UID_CLASS_NAME =
			ClassName.get("org.eclipse.smarthome.core.thing", "ThingTypeUID");

	@Parameter(property = "esh-constants.inputDirectory",
			defaultValue = "${project.basedir}/src/main/resources/ESH-INF")
	private String inputDirectory;

	@Parameter(property = "esh-constants.outputDirectory",
			defaultValue = "${project.build.directory}/generated-sources/esh-constants")
	private String outputDirectory;

	@Parameter(property = "esh-constants.packageName", required = true)
	private String packageName;

	@Parameter(property = "esh-constants.openhabClassName", required = true)
	private String openhabClassName;

	@Parameter(property = "esh-constants.stringsClassName", required = true)
	private String stringsClassName;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// Create output directory
		createOutputDirectory();

		// Get input file list
		final Set<Path> inputFiles = findXMLFiles();

		Set<String> bindingIDs = scanDocuments(inputFiles, "//thing-descriptions/@bindingId");
		Set<String> properties = scanDocuments(inputFiles, "//property/@name");
		Set<String> thingTypeIDs = scanDocuments(inputFiles, "//thing-type/@id");
		Set<String> bridgeTypeIDs = scanDocuments(inputFiles, "//bridge-type/@id");
		Set<String> channelIDs = scanDocuments(inputFiles, "//channel/@id");
		Set<String> channelTypeIDs = scanDocuments(inputFiles, "//channel-type/@id");
		Set<String> channelGroupTypeIDs = scanDocuments(inputFiles, "//channel-group-type/@id");
		Set<String> channelGroupIDs = scanDocuments(inputFiles, "//channel-group/@id");

		properties.removeAll(STANDARD_PROPERTIES);

		if (bindingIDs.size() != 1) {
			throw new MojoFailureException("Expected exactly one binding ID. Please have a look at your XML files!");
		}

		String bindingId = bindingIDs.iterator().next();

		TypeSpec stringConstantsClass = TypeSpec.classBuilder(stringsClassName)
				.addModifiers(PUBLIC, FINAL)
				.addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
				.addField(FieldSpec.builder(String.class, "BINDING_ID", PUBLIC, STATIC, FINAL).initializer("$S", bindingId).build())
				.addFields(mapConstants(properties, "PROPERTY_"))
				.addFields(mapConstants(thingTypeIDs, "THING_TYPE_ID_"))
				.addFields(mapConstants(bridgeTypeIDs, "BRIDGE_TYPE_ID_"))
				.addFields(mapConstants(channelIDs, "CID_"))
				.addFields(mapConstants(channelTypeIDs, "CHANNEL_TYPE_ID_"))
				.addFields(mapConstants(channelGroupTypeIDs, "CHANNEL_GROUP_TYPE_ID_"))
				.addFields(mapConstants(channelGroupIDs, "GID_"))
				.build();

		try {
			JavaFile.builder(packageName, stringConstantsClass)
					.build()
					.writeTo(Path.of(outputDirectory));
		} catch (IOException exception) {
			throw new MojoExecutionException("Can't write string constants output file to " + outputDirectory, exception);
		}

		TypeSpec openhabConstantsClass = TypeSpec.classBuilder(openhabClassName)
				.addModifiers(PUBLIC, FINAL)
				.addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
				.addFields(bridgeTypeIDs.stream()
						.map(bridgeTypeId -> thingTypeUidSpec(bridgeTypeId, "BRIDGE_TYPE_ID_"))
						.collect(Collectors.toList()))
				.addFields(thingTypeIDs.stream()
						.map(thingTypeId -> thingTypeUidSpec(thingTypeId, "THING_TYPE_ID_"))
						.collect(Collectors.toList()))
				.build();

		try {
			JavaFile.builder(packageName, openhabConstantsClass)
					.addStaticImport(ClassName.get(packageName, stringConstantsClass.name), "*")
					.build()
					.writeTo(Path.of(outputDirectory));
		} catch (IOException exception) {
			throw new MojoExecutionException("Can't write openhab constants output file to " + outputDirectory, exception);
		}
	}

	/**
	 * Creates output directory (recursively)
	 *
	 * @throws MojoExecutionException on error, execution fails
	 */
	private void createOutputDirectory() throws MojoExecutionException {
		try {
			Files.createDirectories(Path.of(this.outputDirectory));
		} catch (IOException e) {
			throw new MojoExecutionException("Unable to create output directory: " + this.outputDirectory, e);
		}
	}

	/**
	 * Find all XML files in the input folder.
	 *
	 * @return Set of {@link Path} to XML files
	 * @throws MojoExecutionException on error, execution fails
	 */
	private Set<Path> findXMLFiles() throws MojoExecutionException {

		Set<Path> result;
		// Recursively walk down the directory tree and put all ".xml" files in the set
		try (Stream<Path> walk = Files.walk(Path.of(inputDirectory))) {
			result = walk.filter(p -> p.getFileName().toString().endsWith("xml")).collect(Collectors.toSet());

		} catch (IOException e) {
			throw new MojoExecutionException("Unable to get input files.", e);

		}
		return result;
	}

	/**
	 * Retrieve strings matching the XPath from the given XML files.
	 *
	 * @param inputFiles input file list
	 * @param expression the XPath expression to scan
	 * @return A {@link Set} of strings matching the XPath
	 * @throws MojoExecutionException on error, execution fails
	 */
	private Set<String> scanDocuments(Set<Path> inputFiles, String expression) throws MojoExecutionException {
		final Set<String> result = new LinkedHashSet<>();
		for (Path inputFile : inputFiles) {
			result.addAll(scanDocument(inputFile.toFile(), expression));
		}
		return result;
	}

	/**
	 * Retrieve strings matching the XPath from the given XML file.
	 *
	 * @param file       the input file
	 * @param expression the XPath expression to scan
	 * @return A {@link Set} of strings matching the XPath
	 * @throws MojoExecutionException on error, execution fails
	 */
	private Set<String> scanDocument(File file, String expression)
			throws MojoExecutionException {

		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(file);

			final XPathFactory xPathFactory = XPathFactory.newInstance();
			final XPath xpath = xPathFactory.newXPath();

			final Set<String> result = new LinkedHashSet<>();

			// Find node list in document matching the xpath expression
			XPathExpression xPathExpression = xpath.compile(expression);
			NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				// Put the text content of the node to the map
				result.add(node.getTextContent());
			}
			return result;
		} catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
			throw new MojoExecutionException("Unable to parse XML document: " + file, e);
		}
	}

	private String safeUpper(String name) {
		return name.replaceAll("\\W", "_").toUpperCase(Locale.ROOT);
	}

	private FieldSpec stringConstant(String prefix, String name) {
		return FieldSpec.builder(
				String.class,
				prefix + safeUpper(name),
				PUBLIC, STATIC, FINAL)
				.initializer("$S", name)
				.build();
	}

	private Iterable<FieldSpec> mapConstants(Collection<String> input, String prefix) {
		return input.stream().map(p -> stringConstant(prefix, p)).collect(Collectors.toList());
	}

	private FieldSpec thingTypeUidSpec(String id, String prefix) {
		String safeId = safeUpper(id);
		return FieldSpec.builder(
				THING_TYPE_UID_CLASS_NAME,
				safeId + "_THING_TYPE_UID",
				PUBLIC, STATIC, FINAL)
				.initializer(
						"new $T(BINDING_ID, $L$L)",
						THING_TYPE_UID_CLASS_NAME,
						prefix,
						safeId)
				.build();
	}

	private FieldSpec channelUidSpec(String thingId, String channelId) {
		String safeThingId = safeUpper(thingId);
		String safeChannelId = safeUpper(channelId);
		return FieldSpec.builder(
				CHANNEL_UID_CLASS_NAME,
				safeThingId + "_" + safeChannelId + "_UID",
				PUBLIC, STATIC, FINAL)
				.initializer(
						"new $T($L_THING_TYPE_UID, CID_$L)",
						CHANNEL_UID_CLASS_NAME,
						safeThingId,
						safeChannelId)
				.build();
	}

	// setter

	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setOpenhabClassName(String openhabClassName) {
		this.openhabClassName = openhabClassName;
	}

	public void setStringsClassName(String stringsClassName) {
		this.stringsClassName = stringsClassName;
	}
}
