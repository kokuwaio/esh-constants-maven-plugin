package ${package};

import org.eclipse.smarthome.core.thing.ChannelUID;
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

	// BridgeTypeUIDs
<#list bridgeTypeIDs as key>
	public static final ThingTypeUID ${key?upper_case}_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, BRIDGE_TYPE_ID_${key?upper_case});
</#list>

	// ThingTypeUIDs
<#list thingTypeIDs as key>
	public static final ThingTypeUID ${key?upper_case}_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_${key?upper_case});
</#list>
<#list channelIDs?keys as thingId>

	// ChannelUIDs for ${thingId}
	<#list channelIDs[thingId] as channelId>
	public static final ChannelUID ${thingId?upper_case}_${channelId?upper_case}_UID = new ChannelUID(${thingId?upper_case}_THING_TYPE_UID, CHANNEL_ID_${channelId?upper_case});
	</#list>
</#list>

}
