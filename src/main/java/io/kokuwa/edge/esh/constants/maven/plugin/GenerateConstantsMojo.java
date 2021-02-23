package io.kokuwa.edge.esh.constants.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
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
		final Map<String, String> constants = new TreeMap<>();
		final Map<String, String> uids = new TreeMap<>();

		String bindingId = StringUtils.EMPTY;
		Map<String, String> bindingIDs = new HashMap<>();
		Map<String, String> thingTypeIDs = new HashMap<>();
		Map<String, String> bridgeTypeIDs = new HashMap<>();
		Map<String, String> channelGroupTypeIDs = new HashMap<>();
		Map<String, String> channelTypeIDs = new HashMap<>();

		// Grep constants
		for (Path inputFile : inputFiles) {
			bindingIDs.putAll(getConstants(inputFile.toFile(),
					"//thing-descriptions/@bindingId",
					"BINDING_ID_"));
			thingTypeIDs.putAll(getConstants(inputFile.toFile(),
					"//thing-type/@id",
					"THING_TYPE_ID_"));
			bridgeTypeIDs.putAll(getConstants(inputFile.toFile(),
					"//bridge-type/@id",
					"BRIDGE_TYPE_ID_"));
			channelGroupTypeIDs.putAll(getConstants(inputFile.toFile(),
					"//channel-group-type/@id",
					"CHANNEL_GROUP_TYPE_ID_"));
			channelTypeIDs.putAll(getConstants(inputFile.toFile(),
					"//channel-type/@id",
					"CHANNEL_TYPE_ID_"));
		}

		if (bindingIDs.size() > 1) {
			throw new MojoFailureException("Found more than one binding ID. Please have a look at your XML files!");
		}

		if (!bindingIDs.isEmpty()) {
			bindingId = bindingIDs.values().iterator().next();
			uids.putAll(bridgeTypeIDs);
			uids.putAll(thingTypeIDs);
		}

		// Add constants in specific order
		constants.putAll(bridgeTypeIDs);
		constants.putAll(thingTypeIDs);
		constants.putAll(channelTypeIDs);
		constants.putAll(channelGroupTypeIDs);

		// Generate code from template
		String classResult = createClassFromTemplate(bindingId, constants, uids);

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
	 * Retrieve constant strings from the given XML file.
	 *
	 * @param file       the input file
	 * @param expression the XPath expression to the constant
	 * @param prefix     the constants prefix
	 * @return A {@link Map} of constant name and constant value
	 * @throws MojoExecutionException on error, execution fails
	 */
	private Map<String, String> getConstants(File file, String expression, String prefix)
			throws MojoExecutionException
	{

		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(file);

			final XPathFactory xPathFactory = XPathFactory.newInstance();
			final XPath xpath = xPathFactory.newXPath();

			final Map<String, String> result = new HashMap<>();

			// Find node list in document matching the xpath expression
			XPathExpression xPathExpression = xpath.compile(expression);
			NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				// Put the text content of the node to the map
				result.put(prefix + node.getTextContent().toUpperCase(), node.getTextContent());
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

	/**
	 * Create the Java class based on the Freemarker template.
	 *
	 * @param bindingId the binding ID
	 * @param constants the constants map to generate
	 * @param uids      The map of UIDs to generate
	 * @return the Java class content as string
	 * @throws MojoExecutionException on error, execution fails
	 */
	private String createClassFromTemplate(String bindingId, Map<String, String> constants, Map<String, String> uids)
			throws MojoExecutionException
	{
		try {
			// Template input
			Map root = new HashMap();
			root.put("package", this.packageName);
			root.put("class", this.className);
			root.put("bindingId", bindingId);
			root.put("constants", constants);
			root.put("uids", uids);
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
	 * @param path    the path to the file
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
