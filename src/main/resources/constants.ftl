package ${package};

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * Auto generated class for ESH-INF constants.
 */
public final class ${class} {

	// Not needed
	private ${class}() {
	}

	// Binding ID
	public static final String BINDING_ID = "${bindingId}";

	// Constants
<#list constants?keys as key>
	public static final String ${key} = "${constants[key]}";
</#list>

	// UIDs
<#list uids?keys as key>
	public static final ThingTypeUID ${key}_UID = new ThingTypeUID(BINDING_ID, ${key});
</#list>

}
