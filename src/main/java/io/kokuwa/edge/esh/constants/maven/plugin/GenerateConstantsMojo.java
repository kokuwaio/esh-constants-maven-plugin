package io.kokuwa.edge.esh.constants.maven.plugin;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

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

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Mojo for generating constant class from "ESH-INF" folder. The plugin scans for XML files in the input folder and
 * looks for a couple of XPath matching nodes to extract constants strings and writes them to a Java constants class
 * using a Freemarker template.
 *
 * @author Fabian Schlegel
 * @since 0.1.0
 */
@Mojo(name = "generate-esh-constants", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
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
			"macAddress");

	private static final String PACKAGE = "org.eclipse.smarthome.core.thing";
	private static final ClassName THING_TYPE_UID_CLASS_NAME = ClassName.get(PACKAGE, "ThingTypeUID");

	/**
	 * Input directory.
	 *
	 * @since 0.1.0
	 */
	@Parameter(property = "esh-constants.inputDirectory",
			defaultValue = "${project.basedir}/src/main/resources/ESH-INF")
	private String inputDirectory;

	/**
	 * Output directory for constant classes.
	 *
	 * @since 0.1.0
	 */
	@Parameter(property = "esh-constants.outputDirectory",
			defaultValue = "${project.build.directory}/generated-sources/esh-constants")
	private String outputDirectory;

	/**
	 * Package name for constant classes.
	 *
	 * @since 0.1.0
	 */
	@Parameter(property = "esh-constants.packageName", required = true)
	private String packageName;

	/**
	 * Class name for openHAB constants.
	 *
	 * @since 0.1.11
	 */
	@Parameter(property = "esh-constants.openhabClassName", required = true)
	private String openhabClassName;

	/**
	 * Class name for string constants.
	 *
	 * @since 0.1.11
	 */
	@Parameter(property = "esh-constants.stringsClassName", required = true)
	private String stringsClassName;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// Create output directory
		createOutputDirectory();

		// Get input file list
		final Set<Path> inputFiles = findXMLFiles();

		List<String> bindingIDs = scanDocuments(inputFiles, "//thing-descriptions/@bindingId");
		List<String> properties = scanDocuments(inputFiles, "//property/@name");
		List<String> thingTypeIDs = scanDocuments(inputFiles, "//thing-type/@id");
		List<String> bridgeTypeIDs = scanDocuments(inputFiles, "//bridge-type/@id");
		List<String> channelIDs = scanDocuments(inputFiles, "//channel/@id");
		List<String> channelTypeIDs = scanDocuments(inputFiles, "//channel-type/@id");
		List<String> channelGroupTypeIDs = scanDocuments(inputFiles, "//channel-group-type/@id");
		List<String> channelGroupIDs = scanDocuments(inputFiles, "//channel-group/@id");

		properties.removeAll(STANDARD_PROPERTIES);

		if (bindingIDs.size() != 1) {
			throw new MojoFailureException("Expected exactly one binding ID. Please have a look at your XML files!");
		}

		String bindingId = bindingIDs.iterator().next();

		TypeSpec stringConstantsClass;
		try {
			stringConstantsClass = TypeSpec.classBuilder(stringsClassName)
					.addModifiers(PUBLIC, FINAL)
					.addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
					.addField(FieldSpec
									.builder(String.class, "BINDING_ID", PUBLIC, STATIC, FINAL)
									.initializer("$S", bindingId)
									.build())
					.addFields(mapConstants(properties, "PROPERTY_"))
					.addFields(mapConstants(thingTypeIDs.stream().map(type -> bindingId + ":" + type)
									.collect(Collectors.toList()), "UID_"))
					.addFields(mapConstants(bridgeTypeIDs.stream().map(type -> bindingId + ":" + type)
									.collect(Collectors.toList()), "UID_"))
					.addFields(mapConstants(thingTypeIDs, "THING_TYPE_ID_"))
					.addFields(mapConstants(bridgeTypeIDs, "BRIDGE_TYPE_ID_"))
					.addFields(deprecated(mapConstants(channelIDs, "CID_")))
					.addFields(deprecated(mapConstants(channelTypeIDs, "CHANNEL_TYPE_ID_")))
					.addFields(deprecated(mapConstants(channelGroupTypeIDs, "CHANNEL_GROUP_TYPE_ID_")))
					.addFields(deprecated(mapConstants(channelGroupIDs, "GID_")))
					.addFields(mapConstants(scanDocuments(inputFiles,
							"//bridge-type/channels/channel/@id | //thing-type/channels/channel/@id"), "GCID_"))
					// add a constant for each channel which is in a group
					.addFields(mapConstants(channelGroupTypeIDs.stream().flatMap(
									groupType -> scanDocuments(inputFiles,
											"//channel-group-type[@id='" + groupType + "']//channel/@id")
											.stream()
											.flatMap(groupTypeChannel -> scanDocuments(
													inputFiles,
													"//channel-group[@typeId='" + groupType + "']/@id")
													.stream()
													.map(group -> group + "#" + groupTypeChannel)))
							.collect(Collectors.toCollection(LinkedHashSet::new)), "GCID_"))

					.build();
		} catch (RuntimeException exception) {
			throw new MojoExecutionException("Can't generate string constants class", exception);
		}

		try {
			JavaFile.builder(packageName, stringConstantsClass)
					.build()
					.writeTo(Path.of(outputDirectory));
		} catch (IOException e) {
			throw new MojoExecutionException("Can't write string constants output file to " + outputDirectory, e);
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
		} catch (IOException e) {
			throw new MojoExecutionException("Can't write openhab constants output file to " + outputDirectory, e);
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
	private List<String> scanDocuments(Set<Path> inputFiles, String expression) {
		final Set<String> result = new LinkedHashSet<>();
		for (Path inputFile : inputFiles) {
			result.addAll(scanDocument(inputFile.toFile(), expression));
		}
		return result.stream().sorted().distinct().collect(Collectors.toList());
	}

	/**
	 * Retrieve strings matching the XPath from the given XML file.
	 *
	 * @param file       the input file
	 * @param expression the XPath expression to scan
	 * @return A {@link Set} of strings matching the XPath
	 * @throws MojoExecutionException on error, execution fails
	 */
	private Set<String> scanDocument(File file, String expression) {

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
			throw new RuntimeException("Unable to parse XML document: " + file, e);
		}
	}

	private String safeUpper(String name) {
		return name.replaceAll("\\W", "_").toUpperCase(Locale.ROOT);
	}

	private FieldSpec stringConstant(String prefix, String name) {
		return FieldSpec
				.builder(String.class, prefix + safeUpper(name), PUBLIC, STATIC, FINAL)
				.initializer("$S", name)
				.build();
	}

	private List<FieldSpec> mapConstants(Collection<String> input, String prefix) {
		return input.stream().map(p -> stringConstant(prefix, p)).collect(Collectors.toList());
	}

	/**
	 * {@code List<FieldSpec>} decorator that adds a @{@link Deprecated} annotation to all fields.
	 */
	private List<FieldSpec> deprecated(List<FieldSpec> delegates) {
		return delegates.stream()
				.map(field ->
						field.toBuilder()
								.addAnnotation(AnnotationSpec.builder(Deprecated.class)
								.addMember("forRemoval", "true").build()).build())
				.collect(Collectors.toList());
	}

	private FieldSpec thingTypeUidSpec(String id, String prefix) {
		String safeId = safeUpper(id);
		return FieldSpec
				.builder(THING_TYPE_UID_CLASS_NAME, safeId + "_THING_TYPE_UID", PUBLIC, STATIC, FINAL)
				.initializer("new $T(BINDING_ID, $L$L)", THING_TYPE_UID_CLASS_NAME, prefix, safeId)
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
