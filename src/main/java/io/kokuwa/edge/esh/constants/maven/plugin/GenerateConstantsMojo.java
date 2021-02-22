package io.kokuwa.edge.esh.constants.maven.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

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

	@Parameter(property = "esh-constants.outputDirectory",
			defaultValue = "${project.build.directory}/generated-sources/esh-constants")
	private String outputDirectory;

	@Parameter(property = "esh-constants.packageName", required = true)
	private String packageName;

	@Parameter(property = "esh-constants.className", required = true)
	private String className;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
	}

	private List<File> findXMLFiles() {
		return new ArrayList<File>();
	}
}
