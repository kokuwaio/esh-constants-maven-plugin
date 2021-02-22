package io.kokuwa.edge.esh.constants.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
 * Mojo for generating constant class from "ESH-INF" folder.
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

		// Get input file list
		final Set<Path> inputFiles = findXMLFiles();
		final Map<String, String> constants = new HashMap<>();

		// Grep constants
		for(Path inputFile : inputFiles) {
			constants.putAll(getConstants(inputFile.toFile(),
					"//bridge-type/@id",
					"BRIDGE_TYPE_ID_"));
			constants.putAll(getConstants(inputFile.toFile(),
					"//channel-group-type/@id",
					"CHANNEL_GROUP_TYPE_ID_"));
			constants.putAll(getConstants(inputFile.toFile(),
					"//channel-type/@id",
					"CHANNEL_TYPE_ID_"));
			constants.putAll(getConstants(inputFile.toFile(),
					"//thing-type/@id",
					"THING_TYPE_ID_"));
			constants.putAll(getConstants(inputFile.toFile(),
					"//thing-descriptions/@bindingId",
					"BINDING_ID_"));
		}

		// Generate code from template
		String classResult = createClassFromTemplate(constants);

		// Write output file
		writeFile(Path.of(this.outputDirectory, this.className + ".java"), classResult);

	}

	private Set<Path> findXMLFiles() throws MojoExecutionException {

		Set<Path> result;
		try (Stream<Path> walk = Files.walk(Path.of(inputDirectory))) {
			result = walk.filter(p -> p.getFileName().toString().endsWith("xml")).collect(Collectors.toSet());

		} catch (IOException e) {
			throw new MojoExecutionException("Unable to get input files.", e);

		}
		return result;
	}

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

			XPathExpression xPathExpression = xpath.compile(expression);
			NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
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

	private String createClassFromTemplate(Map<String, String> constants) throws MojoExecutionException {
		try {
			Map root = new HashMap();
			root.put("package", this.packageName);
			root.put("class", this.className);
			root.put("constants", constants);
			Configuration cfg = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS); // Create configuration
			cfg.setClassForTemplateLoading(this.getClass(), "/");
			Template template = cfg.getTemplate("constants.ftl"); // Filename of your template
			StringWriter sw = new StringWriter(); // So you can use the output as String
			template.process(root, sw); // process the template to output
			return sw.toString();
		} catch (TemplateException e) {
			throw new MojoExecutionException("Unable to generate code from template.", e);
		} catch (IOException e) {
			throw new MojoExecutionException("Unable to generate code from template.", e);
		}
	}

	private void writeFile(Path path, String content) throws MojoExecutionException {
		try {
			Files.write(path, content.getBytes());
		} catch (IOException e) {
			throw new MojoExecutionException("Unable to write file: " + path, e);
		}
	}
}
