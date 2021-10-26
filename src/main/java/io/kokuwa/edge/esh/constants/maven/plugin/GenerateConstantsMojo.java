package io.kokuwa.edge.esh.constants.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import lombok.Setter;
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

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

/**
 * Mojo for generating constant class from "ESH-INF" folder. The plugin scans for XML files in the input folder and
 * looks for a couple of XPath matching nodes to extract constants strings and writes them to a Java constants class
 * using a Freemarker template.
 *
 * @author Fabian Schlegel
 */
@Mojo(name = "generate-esh-constants", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateConstantsMojo extends AbstractMojo {

	@Parameter(property = "esh-constants.inputDirectory",
			defaultValue = "${project.basedir}/src/main/resources/ESH-INF")
	private String inputDirectory;

	@Setter
	@Parameter(property = "esh-constants.outputDirectory",
			defaultValue = "${project.build.directory}/generated-sources/esh-constants")
	private String outputDirectory;

	@Parameter(property = "esh-constants.packageName", required = true)
	private String packageName;

	@Parameter(property = "esh-constants.className", required = true)
	private String className;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// Create output directory
		createOutputDirectory();

		// Get input file list
		final Set<Path> inputFiles = findXMLFiles();
		final Map<String, String> constants = new LinkedHashMap<>();

		Set<String> bindingIDs = scanDocuments(inputFiles, "//thing-descriptions/@bindingId");
		Set<String> properties = scanDocuments(inputFiles, "//property/@name");
		Set<String> thingTypeIDs = scanDocuments(inputFiles, "//thing-type/@id");
		Set<String> bridgeTypeIDs = scanDocuments(inputFiles, "//bridge-type/@id");
		Set<String> channelIDs = scanDocuments(inputFiles, "//channel/@id");
		Set<String> channelTypeIDs = scanDocuments(inputFiles, "//channel-type/@id");
		Set<String> channelGroupTypeIDs = scanDocuments(inputFiles, "//channel-group-type/@id");
		Set<String> channelGroupIDs = scanDocuments(inputFiles, "//channel-group/@id");

		Map<String, Set<String>> channelUIDs = new LinkedHashMap<>();
		for (String thingTypeID : thingTypeIDs) {
			channelUIDs.put(thingTypeID,
					scanDocuments(inputFiles, "//thing-type[@id = '" + thingTypeID + "']/channels/channel/@id"));
		}

		if (bindingIDs.size() != 1) {
			throw new MojoFailureException("Expected exactly one binding ID. Please have a look at your XML files!");
		}

		String bindingId = bindingIDs.iterator().next();

		constants.putAll(toConstants(properties, "PROPERTY_"));
		constants.putAll(toConstants(thingTypeIDs, "THING_TYPE_ID_"));
		constants.putAll(toConstants(bridgeTypeIDs, "BRIDGE_TYPE_ID_"));
		constants.putAll(toConstants(channelIDs, "CID_"));
		constants.putAll(toConstants(channelTypeIDs, "CHANNEL_TYPE_ID_"));
		constants.putAll(toConstants(channelGroupTypeIDs, "CHANNEL_GROUP_TYPE_ID_"));
		constants.putAll(toConstants(channelGroupIDs, "GID_"));

		// Generate code from template
		String classResult = createClassFromTemplate(
				bindingId,
				constants,
				bridgeTypeIDs
						.stream()
						.map(id->id.replaceAll("\\W", "_"))
						// we must preserve the order, otherwise the unit test fails
						.collect(LinkedHashSet::new, HashSet::add, AbstractCollection::addAll),
				thingTypeIDs
						.stream()
						.map(id->id.replaceAll("\\W", "_"))
						// we must preserve the order, otherwise the unit test fails
						.collect(LinkedHashSet::new, HashSet::add, AbstractCollection::addAll),
				channelUIDs);

		// Write output file
		writeFile(Path.of(this.outputDirectory, this.className + ".java"), classResult);
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
	 * @param file the input file
	 * @param expression the XPath expression to scan
	 * @return A {@link Set} of strings matching the XPath
	 * @throws MojoExecutionException on error, execution fails
	 */
	private Set<String> scanDocument(File file, String expression)
			throws MojoExecutionException
	{

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
		} catch (ParserConfigurationException e) {
			throw new MojoExecutionException("Unable to parse XML document: " + file, e);
		} catch (IOException e) {
			throw new MojoExecutionException("Unable to parse XML document: " + file, e);
		} catch (SAXException e) {
			throw new MojoExecutionException("Unable to parse XML document: " + file, e);
		} catch (XPathExpressionException e) {
			throw new MojoExecutionException("Unable to parse XML document: " + file, e);
		}
	}

	private Map<String, String> toConstants(Set<String> values, String prefix) {
		Map<String, String> result = new LinkedHashMap<>();
		for (String value : values) {
			result.put(prefix + value.toUpperCase().replaceAll("\\W", "_"), value);
		}
		return result;
	}

	/**
	 * Create the Java class based on the Freemarker template.
	 *
	 * @param bindingId the binding ID
	 * @param constants the constants map to generate
	 * @param bridgeTypeIDs the map of bridgeType IDs to generate
	 * @param thingTypeIDs the map of thingType IDs to generate
	 * @param channelUIDs the map of channel IDs to generate
	 * @return the Java class content as string
	 * @throws MojoExecutionException on error, execution fails
	 */
	private String createClassFromTemplate(String bindingId, Map<String, String> constants,
			Set<String> bridgeTypeIDs, Set<String> thingTypeIDs, Map<String, Set<String>> channelUIDs)
			throws MojoExecutionException
	{
		try {
			// Template input
			Map<String, Object> root = new LinkedHashMap<>();
			root.put("package", this.packageName);
			root.put("class", this.className);
			root.put("bindingId", bindingId);
			root.put("constants", constants);
			root.put("bridgeTypeIDs", bridgeTypeIDs);
			root.put("thingTypeIDs", thingTypeIDs);
			root.put("channelIDs", channelUIDs);
			// Grep template from classpath
			Configuration cfg = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			cfg.setClassForTemplateLoading(this.getClass(), "/");
			Template template = cfg.getTemplate("constants.ftl");
			// Process template
			StringWriter sw = new StringWriter();
			template.process(root, sw);
			return sw.toString();
		} catch (TemplateException e) {
			throw new MojoExecutionException("Unable to generate code from template.", e);
		} catch (IOException e) {
			throw new MojoExecutionException("Unable to generate code from template.", e);
		}
	}

	/**
	 * Write file content to disk.
	 *
	 * @param path the path to the file
	 * @param content the file content
	 * @throws MojoExecutionException on error, execution fails
	 */
	private void writeFile(Path path, String content) throws MojoExecutionException {
		try {
			Files.write(path, content.getBytes());
		} catch (IOException e) {
			throw new MojoExecutionException("Unable to write file: " + path, e);
		}
	}
}
