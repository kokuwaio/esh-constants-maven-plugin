# ESH Constants Generator Maven Plugin

## What?

This plugin generates a Java constants class with all namings defined in the `ESH_INF` folder files.

## Why?

Because it's hard to write this class on your own, if you have a lot of content. Also, the XML files should be
in the lead and your code base is in sync all the time.

## How?

```xml
<plugins>
	<plugin>
		<groupId>io.kokuwa.edge</groupId>
		<artifactId>esh-constants-maven-plugin</artifactId>
		<version>RELEASE</version>
		<configuration>
			<packageName>io.kokuwa.examples</packageName>
			<className>Constants</className>
			<inputDirectory>${project.basdir}/src/main/resources/ESH-INF</inputDirectory>
			<outputDirectory>${project.build.dir}/generated-sources</outputDirectory>
		</configuration>
	</plugin>
</plugins>
```