package io.kokuwa.edge.esh.constants.maven.plugin.junit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.kokuwa.edge.esh.constants.maven.plugin.GenerateConstantsMojo;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.apache.maven.plugin.descriptor.Parameter;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.descriptor.PluginDescriptorBuilder;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.InterpolationFilterReader;
import org.codehaus.plexus.util.ReflectionUtils;
import org.codehaus.plexus.util.xml.XmlStreamReader;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;

public class MojoExtension implements ParameterResolver, BeforeAllCallback, BeforeEachCallback {

	private PluginDescriptor plugin;
	private Map<Class<? extends GenerateConstantsMojo>, MojoDescriptor> mojoDescriptors;

	// lifecycle

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {

		// get plugin descriptor

		InputStream inputStream = GenerateConstantsMojo.class.getResourceAsStream("/META-INF/maven/plugin.xml");
		assertNotNull(inputStream, "Plugin descriptor not found.");
		plugin = new PluginDescriptorBuilder().build(new InterpolationFilterReader(new BufferedReader(new XmlStreamReader(
				inputStream)), new HashMap<>()));

		// get mojos

		mojoDescriptors = new HashMap<>();
		for (MojoDescriptor mojoDescriptor : plugin.getMojos()) {
			mojoDescriptors.put((Class<? extends GenerateConstantsMojo>) Class.forName(mojoDescriptor.getImplementation()),
					mojoDescriptor);
		}
	}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		FileUtils.forceDelete(getProjectBuildDirectory(context).toFile());
	}

	// parameter

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext context)
			throws ParameterResolutionException
	{
		return GenerateConstantsMojo.class.isAssignableFrom(parameterContext.getParameter().getType());
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context)
			throws ParameterResolutionException
	{
		try {

			// get descriptor

			Class<? extends GenerateConstantsMojo> mojoType = (Class<GenerateConstantsMojo>) parameterContext.getParameter()
					.getType();
			MojoDescriptor descriptor = mojoDescriptors.get(mojoType);
			assertNotNull(descriptor,
					"Plugin descriptor for "
							+ mojoType.getSimpleName()
							+ " not found, run 'maven-plugin-plugin:descriptor'.");

			// create mojo with default values

			GenerateConstantsMojo mojo = spy(mojoType);
			for (Parameter parameter : descriptor.getParameters()) {
				if (parameter.getDefaultValue() == null || !parameter.isEditable() || parameter.getType()
						.equals("boolean"))
				{
					continue;
				}
				getField(mojoType, parameter.getName()).set(mojo, resolve(context, parameter.getDefaultValue()));
			}

			// read mojo values from annotations

			List<MojoProperty> mojoProperties = Stream
					.concat(Arrays.stream(context.getRequiredTestClass().getAnnotationsByType(MojoProperty.class)),
							Arrays.stream(context.getRequiredTestMethod().getAnnotationsByType(MojoProperty.class)))
					.collect(Collectors.toList());
			for (MojoProperty mojoProperty : mojoProperties) {
				getField(mojoType, mojoProperty.name()).set(mojo, resolve(context, mojoProperty.value()));
			}

			// validate that every parameter is set

			for (Parameter paramter : descriptor.getParameters()) {
				if (paramter.isRequired()) {
					assertNotNull(
							getField(mojoType, paramter.getName()).get(mojo),
							"Parameter '"
									+ paramter.getName()
									+ "' not set for mojo '"
									+ mojoType.getSimpleName()
									+ "'.");
				}
			}

			return mojo;
		} catch (ReflectiveOperationException e) {
			throw new ParameterResolutionException("Failed to setup mockito.", e);
		}
	}

	// internal

	private Field getField(Class<? extends GenerateConstantsMojo> type, String name) {
		Field field = ReflectionUtils.getFieldByNameIncludingSuperclasses(name, type);
		assertNotNull(field, "Field with name '" + name + "' not found at type '" + type.getSimpleName() + "'.");
		field.setAccessible(true);
		return field;
	}

	private String resolve(ExtensionContext context, String property) {
		String resolved = property;
		// use test specific build directory
		resolved = property.replace("${project.build.directory}", getProjectBuildDirectory(context).toString());
		return resolved;
	}

	private Path getProjectBuildDirectory(ExtensionContext context) {
		return Paths.get("target",
				"surefire",
				context.getRequiredTestClass().getSimpleName()
						+ "."
						+ context.getRequiredTestMethod().getName());
	}
}