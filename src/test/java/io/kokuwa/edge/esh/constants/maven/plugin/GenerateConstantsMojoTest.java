package io.kokuwa.edge.esh.constants.maven.plugin;

import io.kokuwa.edge.esh.constants.maven.plugin.junit.MojoExtension;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.MojoProperty;
import io.kokuwa.edge.esh.constants.maven.plugin.junit.SystemPropertyExtension;
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
	public void verfiyGeneration(GenerateConstantsMojo mojo) {

	}
}
