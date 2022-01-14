package io.kokuwa.edge.esh.constants.maven.plugin;

import com.google.testing.compile.JavaFileObjectSubject;
import com.google.testing.compile.JavaFileObjects;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.MojoExtension;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.MojoProperty;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.SystemPropertyExtension;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.testing.compile.JavaFileObjects.forResource;

/**
 * Tests for {@link GenerateConstantsMojo}.
 *
 * @author Fabian Schlegel
 */
@ExtendWith({SystemPropertyExtension.class, MojoExtension.class})
@MojoProperty(name = "packageName", value = "org.test")
@MojoProperty(name = "stringsClassName", value = "StringConstants")
@MojoProperty(name = "openhabClassName", value = "OpenHabConstants")
@MojoProperty(name = "inputDirectory", value = "src/test/resources/ESH-INF")
@MojoProperty(name = "outputDirectory", value = "/will/be/set/in/test")
public class GenerateConstantsMojoTest {

	@Test
	public void verifyStringsEquals(GenerateConstantsMojo mojo) throws IOException, MojoExecutionException, MojoFailureException {
		Path outputDirectory = Files.createTempDirectory("esh-constants-test");
		mojo.setOutputDirectory(outputDirectory.toString());
		mojo.execute();

		String constants = new String(Files.readAllBytes(Path.of(outputDirectory.toString(), "org", "test", "StringConstants.java")));

		JavaFileObjectSubject.assertThat(JavaFileObjects.forSourceString("org.test.StringConstants", constants))
				.hasSourceEquivalentTo(forResource("ExpectedStringConstants.java"));
	}


	@Test
	public void verifyOpenHabEquals(GenerateConstantsMojo mojo) throws IOException, MojoExecutionException, MojoFailureException {
		Path outputDirectory = Files.createTempDirectory("esh-constants-test");
		mojo.setOutputDirectory(outputDirectory.toString());
		mojo.execute();

		String ohConstants = new String(Files.readAllBytes(Path.of(outputDirectory.toString(), "org", "test", "OpenHabConstants.java")));

		JavaFileObjectSubject.assertThat(JavaFileObjects.forSourceString("org.test.OpenHabConstants", ohConstants))
				.hasSourceEquivalentTo(forResource("ExpectedOpenHabConstants.java"));
	}
}
