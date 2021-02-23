package ${package};

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * Auto generated class for ESH-INF constants.
 */
public final class ${class} {

	private ${class}() {
	}

	public static final String BINDING_ID = "${bindingId}";

<#list constants?keys as key>
	public static final String ${key} = "${constants[key]}";
</#list>

<#list uids?keys as key>
	public static final ThingTypeUID ${key}_UID = new ThingTypeUID(BINDING_ID, ${key});
</#list>

}
