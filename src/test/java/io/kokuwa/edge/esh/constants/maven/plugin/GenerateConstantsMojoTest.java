package io.kokuwa.edge.esh.constants.maven.plugin;

import static com.google.testing.compile.JavaFileObjects.forResource;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.google.testing.compile.JavaFileObjectSubject;
import com.google.testing.compile.JavaFileObjects;

/**
 * Tests for {@link GenerateConstantsMojo}.
 *
 * @author Fabian Schlegel
 */
public class GenerateConstantsMojoTest {

	@Test
	void verify() throws Exception {

		Path outputDirectory = Files.createTempDirectory("esh-constants-test");
		GenerateConstantsMojo mojo = new GenerateConstantsMojo();
		mojo.setOpenhabClassName("OpenHabConstants");
		mojo.setStringsClassName("StringConstants");
		mojo.setPackageName("org.test");
		mojo.setInputDirectory("src/test/resources/ESH-INF");
		mojo.setOutputDirectory("/will/be/set/in/test");
		mojo.setOutputDirectory(outputDirectory.toString());
		mojo.execute();

		String constants = new String(Files.readAllBytes(outputDirectory.resolve("org/test/StringConstants.java")));
		JavaFileObjectSubject
				.assertThat(JavaFileObjects.forSourceString("org.test.StringConstants", constants))
				.hasSourceEquivalentTo(forResource("ExpectedStringConstants.java"));

		String ohConstants = new String(Files.readAllBytes(outputDirectory.resolve("org/test/OpenHabConstants.java")));
		JavaFileObjectSubject
				.assertThat(JavaFileObjects.forSourceString("org.test.OpenHabConstants", ohConstants))
				.hasSourceEquivalentTo(forResource("ExpectedOpenHabConstants.java"));
	}
}
