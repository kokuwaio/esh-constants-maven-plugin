package ${package};

/**
 * Auto generated class for ESH-INF constants.
 */
public final class ${class} {

	private ${class}() {
	}

<#list constants?keys as key>
	public static final String ${key} = "${constants[key]}";
</#list>

}
