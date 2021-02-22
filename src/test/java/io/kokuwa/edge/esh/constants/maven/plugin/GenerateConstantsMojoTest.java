package io.kokuwa.edge.esh.constants.maven.plugin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.kokuwa.edge.esh.constants.maven.plugin.junit.MojoExtension;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.MojoProperty;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.SystemPropertyExtension;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Tests for {@link GenerateConstantsMojo}.
 *
 * @author Fabian Schlegel
 */
@ExtendWith({ SystemPropertyExtension.class, MojoExtension.class })
@MojoProperty(name = "packageName", value = "org.test")
@MojoProperty(name = "className", value = "Constants")
@MojoProperty(name = "inputDirectory", value = "src/test/resources/ESH-INF")
@MojoProperty(name = "outputDirectory", value = "/will/be/set/in/test")
public class GenerateConstantsMojoTest {

	@Test
	public void verfiyGeneration(GenerateConstantsMojo mojo)
			throws MojoFailureException, MojoExecutionException, IOException, URISyntaxException
	{
		Path outputDirectory = Files.createTempDirectory("esh-constants-test");
		mojo.setOutputDirectory(outputDirectory.toString());
		mojo.execute();

		String result = new String(Files.readAllBytes(Path.of(outputDirectory.toString(), "Constants.java")));
		String expected = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("expected.java").toURI())));

		Assertions.assertNotNull(result, "Result must be generated.");
		Assertions.assertNotNull(expected, "Expected result must be found.");

		Assertions.assertEquals(expected, result, "Generated result must match expected file.");
	}
}
