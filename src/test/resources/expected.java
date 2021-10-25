package org.test;

import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * Auto generated class for ESH-INF constants.
 */
public final class Constants {

	// Not needed
	private Constants() {
	}

	// Binding ID
	public static final String BINDING_ID = "wmbus";

	// Constants
	public static final String PROPERTY_VENDOR = "vendor";
	public static final String PROPERTY_MODELID = "modelId";
	public static final String PROPERTY_SERIAL = "serial";
	public static final String THING_TYPE_ID_ITRON_SMOKE_DETECTOR = "itron_smoke_detector";
	public static final String THING_TYPE_ID_TECHEM_HKV45 = "techem_hkv45";
	public static final String THING_TYPE_ID_TECHEM_HKV61 = "techem_hkv61";
	public static final String THING_TYPE_ID_TECHEM_HKV64 = "techem_hkv64";
	public static final String THING_TYPE_ID_TECHEM_HKV69 = "techem_hkv69";
	public static final String THING_TYPE_ID_TECHEM_HKV94 = "techem_hkv94";
	public static final String THING_TYPE_ID_TECHEM_SD76 = "techem_sd76";
	public static final String THING_TYPE_ID_TECHEM_WZ62 = "techem_wz62";
	public static final String THING_TYPE_ID_TECHEM_WZ72 = "techem_wz72";
	public static final String THING_TYPE_ID_TECHEM_WMZ43 = "techem_wmz43";
	public static final String THING_TYPE_ID_METER = "meter";
	public static final String THING_TYPE_ID_ENCRYPTED_METER = "encrypted_meter";
	public static final String THING_TYPE_ID_THING_WITH_GROUPS = "thing_with_groups";
	public static final String BRIDGE_TYPE_ID_WMBUSBRIDGE = "wmbusbridge";
	public static final String BRIDGE_TYPE_ID_WMBUSVIRTUALBRIDGE = "wmbusvirtualbridge";
	public static final String CHANNEL_ID_LAST_FRAME = "last_frame";
	public static final String CHANNEL_ID_CURRENT_DATE = "current_date";
	public static final String CHANNEL_ID_CURRENT_DATE_STRING = "current_date_string";
	public static final String CHANNEL_ID_CURRENT_DATE_NUMBER = "current_date_number";
	public static final String CHANNEL_ID_STATUS_REMOVAL_OCCURRED = "status_removal_occurred";
	public static final String CHANNEL_ID_STATUS_BILLING_DATE = "status_billing_date";
	public static final String CHANNEL_ID_STATUS_PRODUCT_INSTALLED = "status_product_installed";
	public static final String CHANNEL_ID_STATUS_OPERATION_MODE = "status_operation_mode";
	public static final String CHANNEL_ID_STATUS_PERIMETER_INTRUSION_OCCURRED = "status_perimeter_intrusion_occurred";
	public static final String CHANNEL_ID_STATUS_SMOKE_INLET_BLOCKED_OCCURRED = "status_smoke_inlet_blocked_occurred";
	public static final String CHANNEL_ID_STATUS_OUT_OF_TEMP_RANGE_OCCURRED = "status_out_of_temp_range_occurred";
	public static final String CHANNEL_ID_STATUS_PRODUCT_CODE = "status_product_code";
	public static final String CHANNEL_ID_STATUS_BATTERY_LIFETIME = "status_battery_lifetime";
	public static final String CHANNEL_ID_STATUS_PERIMETER_INTRUSION = "status_perimeter_intrusion";
	public static final String CHANNEL_ID_STATUS_REMOVAL_ERROR = "status_removal_error";
	public static final String CHANNEL_ID_STATUS_DATA_ENCRYPTED = "status_data_encrypted";
	public static final String CHANNEL_ID_LAST_SMOKE_ALERT_START_DATE = "last_smoke_alert_start_date";
	public static final String CHANNEL_ID_LAST_SMOKE_ALERT_START_DATE_STRING = "last_smoke_alert_start_date_string";
	public static final String CHANNEL_ID_LAST_SMOKE_ALERT_START_DATE_NUMBER = "last_smoke_alert_start_date_number";
	public static final String CHANNEL_ID_LAST_SMOKE_ALERT_END_DATE = "last_smoke_alert_end_date";
	public static final String CHANNEL_ID_LAST_SMOKE_ALERT_END_DATE_STRING = "last_smoke_alert_end_date_string";
	public static final String CHANNEL_ID_LAST_SMOKE_ALERT_END_DATE_NUMBER = "last_smoke_alert_end_date_number";
	public static final String CHANNEL_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE = "last_beeper_stopped_during_smoke_alert_date";
	public static final String CHANNEL_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_STRING = "last_beeper_stopped_during_smoke_alert_date_string";
	public static final String CHANNEL_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_NUMBER = "last_beeper_stopped_during_smoke_alert_date_number";
	public static final String CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE = "last_perimeter_intrusion_obstacle_occurred_date";
	public static final String CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_STRING = "last_perimeter_intrusion_obstacle_occurred_date_string";
	public static final String CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_NUMBER = "last_perimeter_intrusion_obstacle_occurred_date_number";
	public static final String CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE = "last_perimeter_intrusion_obstacle_removed_date";
	public static final String CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_STRING = "last_perimeter_intrusion_obstacle_removed_date_string";
	public static final String CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_NUMBER = "last_perimeter_intrusion_obstacle_removed_date_number";
	public static final String CHANNEL_ID_LAST_SMOKE_INLET_BLOCKED_DATE = "last_smoke_inlet_blocked_date";
	public static final String CHANNEL_ID_LAST_SMOKE_INLET_BLOCKED_DATE_STRING = "last_smoke_inlet_blocked_date_string";
	public static final String CHANNEL_ID_LAST_SMOKE_INLET_BLOCKED_DATE_NUMBER = "last_smoke_inlet_blocked_date_number";
	public static final String CHANNEL_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE = "last_smoke_inlet_blocking_removed_date";
	public static final String CHANNEL_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_STRING = "last_smoke_inlet_blocking_removed_date_string";
	public static final String CHANNEL_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_NUMBER = "last_smoke_inlet_blocking_removed_date_number";
	public static final String CHANNEL_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE = "last_temperature_out_of_range_date";
	public static final String CHANNEL_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_STRING = "last_temperature_out_of_range_date_string";
	public static final String CHANNEL_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_NUMBER = "last_temperature_out_of_range_date_number";
	public static final String CHANNEL_ID_LAST_TEST_SWITCH_DATE = "last_test_switch_date";
	public static final String CHANNEL_ID_LAST_TEST_SWITCH_DATE_STRING = "last_test_switch_date_string";
	public static final String CHANNEL_ID_LAST_TEST_SWITCH_DATE_NUMBER = "last_test_switch_date_number";
	public static final String CHANNEL_ID_NUMBER_OF_TEST_SWITCHES_OPERATED = "number_of_test_switches_operated";
	public static final String CHANNEL_ID_PERIMETER_INTRUSION_DAY_COUNTER_CUMULATED = "perimeter_intrusion_day_counter_cumulated";
	public static final String CHANNEL_ID_SMOKE_INLET_DAY_COUNTER_CUMULATED = "smoke_inlet_day_counter_cumulated";
	public static final String CHANNEL_ID_CURRENT_READING = "current_reading";
	public static final String CHANNEL_ID_LAST_READING = "last_reading";
	public static final String CHANNEL_ID_LAST_DATE = "last_date";
	public static final String CHANNEL_ID_LAST_DATE_STRING = "last_date_string";
	public static final String CHANNEL_ID_LAST_DATE_NUMBER = "last_date_number";
	public static final String CHANNEL_ID_RECEPTION = "reception";
	public static final String CHANNEL_ID_ALMANAC = "almanac";
	public static final String CHANNEL_ID_ROOM_TEMPERATURE = "room_temperature";
	public static final String CHANNEL_ID_RADIATOR_TEMPERATURE = "radiator_temperature";
	public static final String CHANNEL_ID_STATUS = "status";
	public static final String CHANNEL_ID_CURRENT_DATE_SMOKE = "current_date_smoke";
	public static final String CHANNEL_ID_CURRENT_DATE_SMOKE_STRING = "current_date_smoke_string";
	public static final String CHANNEL_ID_CURRENT_DATE_SMOKE_NUMBER = "current_date_smoke_number";
	public static final String CHANNEL_ID_COUNTER = "counter";
	public static final String CHANNEL_TYPE_ID_LAST_FRAME = "last_frame";
	public static final String CHANNEL_TYPE_ID_ITRON_CURRENT_DATE_TIME = "itron_current_date_time";
	public static final String CHANNEL_TYPE_ID_ITRON_CURRENT_DATE_TIME_STRING = "itron_current_date_time_string";
	public static final String CHANNEL_TYPE_ID_ITRON_CURRENT_DATE_TIME_NUMBER = "itron_current_date_time_number";
	public static final String CHANNEL_TYPE_ID_STATUS_REMOVAL_OCCURRED = "status_removal_occurred";
	public static final String CHANNEL_TYPE_ID_STATUS_BILLING_DATE = "status_billing_date";
	public static final String CHANNEL_TYPE_ID_STATUS_PRODUCT_INSTALLED = "status_product_installed";
	public static final String CHANNEL_TYPE_ID_STATUS_OPERATION_MODE = "status_operation_mode";
	public static final String CHANNEL_TYPE_ID_STATUS_PERIMETER_INTRUSION_OCCURRED = "status_perimeter_intrusion_occurred";
	public static final String CHANNEL_TYPE_ID_STATUS_SMOKE_INLET_BLOCKED_OCCURRED = "status_smoke_inlet_blocked_occurred";
	public static final String CHANNEL_TYPE_ID_STATUS_OUT_OF_TEMP_RANGE_OCCURRED = "status_out_of_temp_range_occurred";
	public static final String CHANNEL_TYPE_ID_STATUS_PRODUCT_CODE = "status_product_code";
	public static final String CHANNEL_TYPE_ID_STATUS_BATTERY_LIFETIME = "status_battery_lifetime";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_ALERT_START_DATE = "last_smoke_alert_start_date";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_ALERT_START_DATE_STRING = "last_smoke_alert_start_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_ALERT_START_DATE_NUMBER = "last_smoke_alert_start_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_ALERT_END_DATE = "last_smoke_alert_end_date";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_ALERT_END_DATE_STRING = "last_smoke_alert_end_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_ALERT_END_DATE_NUMBER = "last_smoke_alert_end_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE = "last_beeper_stopped_during_smoke_alert_date";
	public static final String CHANNEL_TYPE_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_STRING = "last_beeper_stopped_during_smoke_alert_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_NUMBER = "last_beeper_stopped_during_smoke_alert_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE = "last_perimeter_intrusion_obstacle_occurred_date";
	public static final String CHANNEL_TYPE_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_STRING = "last_perimeter_intrusion_obstacle_occurred_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_NUMBER = "last_perimeter_intrusion_obstacle_occurred_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE = "last_perimeter_intrusion_obstacle_removed_date";
	public static final String CHANNEL_TYPE_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_STRING = "last_perimeter_intrusion_obstacle_removed_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_NUMBER = "last_perimeter_intrusion_obstacle_removed_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_INLET_BLOCKED_DATE = "last_smoke_inlet_blocked_date";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_INLET_BLOCKED_DATE_STRING = "last_smoke_inlet_blocked_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_INLET_BLOCKED_DATE_NUMBER = "last_smoke_inlet_blocked_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE = "last_smoke_inlet_blocking_removed_date";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_STRING = "last_smoke_inlet_blocking_removed_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_NUMBER = "last_smoke_inlet_blocking_removed_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE = "last_temperature_out_of_range_date";
	public static final String CHANNEL_TYPE_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_STRING = "last_temperature_out_of_range_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_NUMBER = "last_temperature_out_of_range_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_TEST_SWITCH_DATE = "last_test_switch_date";
	public static final String CHANNEL_TYPE_ID_LAST_TEST_SWITCH_DATE_STRING = "last_test_switch_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_TEST_SWITCH_DATE_NUMBER = "last_test_switch_date_number";
	public static final String CHANNEL_TYPE_ID_NUMBER_OF_TEST_SWITCHES_OPERATED = "number_of_test_switches_operated";
	public static final String CHANNEL_TYPE_ID_PERIMETER_INTRUSION_DAY_COUNTER_CUMULATED = "perimeter_intrusion_day_counter_cumulated";
	public static final String CHANNEL_TYPE_ID_SMOKE_INLET_DAY_COUNTER_CUMULATED = "smoke_inlet_day_counter_cumulated";
	public static final String CHANNEL_TYPE_ID_ENERGY = "energy";
	public static final String CHANNEL_TYPE_ID_VOLUME = "volume";
	public static final String CHANNEL_TYPE_ID_POWER = "power";
	public static final String CHANNEL_TYPE_ID_FLOW_VOLUME = "flow_volume";
	public static final String CHANNEL_TYPE_ID_FLOW_TEMPERATURE = "flow_temperature";
	public static final String CHANNEL_TYPE_ID_RETURN_TEMPERATURE = "return_temperature";
	public static final String CHANNEL_TYPE_ID_EXTERNAL_TEMPERATURE = "external_temperature";
	public static final String CHANNEL_TYPE_ID_TEMPERATURE_DIFFERENCE = "temperature_difference";
	public static final String CHANNEL_TYPE_ID_MAX_POWER = "max_power";
	public static final String CHANNEL_TYPE_ID_MAX_FLOW_VOLUME = "max_flow_volume";
	public static final String CHANNEL_TYPE_ID_MAX_FLOW_TEMPERATURE = "max_flow_temperature";
	public static final String CHANNEL_TYPE_ID_MAX_RETURN_TEMPERATURE = "max_return_temperature";
	public static final String CHANNEL_TYPE_ID_MAX_EXTERNAL_TEMPERATURE = "max_external_temperature";
	public static final String CHANNEL_TYPE_ID_ON_TIME_ERROR = "on_time_error";
	public static final String CHANNEL_TYPE_ID_ERROR_DATE = "error_date";
	public static final String CHANNEL_TYPE_ID_ERROR_FLAGS = "error_flags";
	public static final String CHANNEL_TYPE_ID_ROOM_TEMPERATURE = "room_temperature";
	public static final String CHANNEL_TYPE_ID_RADIATOR_TEMPERATURE = "radiator_temperature";
	public static final String CHANNEL_TYPE_ID_CURRENT_READING = "current_reading";
	public static final String CHANNEL_TYPE_ID_PREVIOUS_READING = "previous_reading";
	public static final String CHANNEL_TYPE_ID_LAST_READING = "last_reading";
	public static final String CHANNEL_TYPE_ID_CURRENT_DATE = "current_date";
	public static final String CHANNEL_TYPE_ID_CURRENT_DATE_STRING = "current_date_string";
	public static final String CHANNEL_TYPE_ID_CURRENT_DATE_NUMBER = "current_date_number";
	public static final String CHANNEL_TYPE_ID_PREVIOUS_DATE = "previous_date";
	public static final String CHANNEL_TYPE_ID_PREVIOUS_DATE_STRING = "previous_date_string";
	public static final String CHANNEL_TYPE_ID_PREVIOUS_DATE_NUMBER = "previous_date_number";
	public static final String CHANNEL_TYPE_ID_LAST_DATE = "last_date";
	public static final String CHANNEL_TYPE_ID_LAST_DATE_STRING = "last_date_string";
	public static final String CHANNEL_TYPE_ID_LAST_DATE_NUMBER = "last_date_number";
	public static final String CHANNEL_TYPE_ID_RECEPTION = "reception";
	public static final String CHANNEL_TYPE_ID_ALMANAC = "almanac";
	public static final String CHANNEL_TYPE_ID_STATUS = "status";
	public static final String CHANNEL_TYPE_ID_COUNTER = "counter";
	public static final String CHANNEL_TYPE_ID_CURRENT_DATE_SMOKE = "current_date_smoke";
	public static final String CHANNEL_TYPE_ID_CURRENT_DATE_SMOKE_STRING = "current_date_smoke_string";
	public static final String CHANNEL_TYPE_ID_CURRENT_DATE_SMOKE_NUMBER = "current_date_smoke_number";
	public static final String CHANNEL_GROUP_TYPE_ID_GROUP_TYPE_1 = "group_type_1";
	public static final String CHANNEL_GROUP_TYPE_ID_GROUP_TYPE_2 = "group_type_2";
	public static final String GID_GROUP1 = "group1";
	public static final String GID_GROUP2 = "group2";

	// BridgeTypeUIDs
	public static final ThingTypeUID WMBUSBRIDGE_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, BRIDGE_TYPE_ID_WMBUSBRIDGE);
	public static final ThingTypeUID WMBUSVIRTUALBRIDGE_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, BRIDGE_TYPE_ID_WMBUSVIRTUALBRIDGE);

	// ThingTypeUIDs
	public static final ThingTypeUID ITRON_SMOKE_DETECTOR_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_ITRON_SMOKE_DETECTOR);
	public static final ThingTypeUID TECHEM_HKV45_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_HKV45);
	public static final ThingTypeUID TECHEM_HKV61_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_HKV61);
	public static final ThingTypeUID TECHEM_HKV64_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_HKV64);
	public static final ThingTypeUID TECHEM_HKV69_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_HKV69);
	public static final ThingTypeUID TECHEM_HKV94_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_HKV94);
	public static final ThingTypeUID TECHEM_SD76_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_SD76);
	public static final ThingTypeUID TECHEM_WZ62_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_WZ62);
	public static final ThingTypeUID TECHEM_WZ72_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_WZ72);
	public static final ThingTypeUID TECHEM_WMZ43_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_TECHEM_WMZ43);
	public static final ThingTypeUID METER_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_METER);
	public static final ThingTypeUID ENCRYPTED_METER_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_ENCRYPTED_METER);
	public static final ThingTypeUID THING_WITH_GROUPS_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, THING_TYPE_ID_THING_WITH_GROUPS);

	// ChannelUIDs for itron_smoke_detector
	public static final ChannelUID ITRON_SMOKE_DETECTOR_CURRENT_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_CURRENT_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_CURRENT_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_REMOVAL_OCCURRED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_REMOVAL_OCCURRED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_BILLING_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_BILLING_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_PRODUCT_INSTALLED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_PRODUCT_INSTALLED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_OPERATION_MODE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_OPERATION_MODE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_PERIMETER_INTRUSION_OCCURRED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_PERIMETER_INTRUSION_OCCURRED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_SMOKE_INLET_BLOCKED_OCCURRED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_SMOKE_INLET_BLOCKED_OCCURRED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_OUT_OF_TEMP_RANGE_OCCURRED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_OUT_OF_TEMP_RANGE_OCCURRED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_PRODUCT_CODE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_PRODUCT_CODE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_BATTERY_LIFETIME_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_BATTERY_LIFETIME);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_PERIMETER_INTRUSION_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_PERIMETER_INTRUSION);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_REMOVAL_ERROR_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_REMOVAL_ERROR);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_STATUS_DATA_ENCRYPTED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_STATUS_DATA_ENCRYPTED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_ALERT_START_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_ALERT_START_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_ALERT_START_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_ALERT_START_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_ALERT_START_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_ALERT_START_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_ALERT_END_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_ALERT_END_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_ALERT_END_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_ALERT_END_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_ALERT_END_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_ALERT_END_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_BEEPER_STOPPED_DURING_SMOKE_ALERT_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_OCCURRED_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_PERIMETER_INTRUSION_OBSTACLE_REMOVED_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_INLET_BLOCKED_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_INLET_BLOCKED_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_INLET_BLOCKED_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_INLET_BLOCKED_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_INLET_BLOCKED_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_INLET_BLOCKED_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_SMOKE_INLET_BLOCKING_REMOVED_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_TEMPERATURE_OUT_OF_RANGE_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_TEST_SWITCH_DATE_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_TEST_SWITCH_DATE);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_TEST_SWITCH_DATE_STRING_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_TEST_SWITCH_DATE_STRING);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_LAST_TEST_SWITCH_DATE_NUMBER_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_LAST_TEST_SWITCH_DATE_NUMBER);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_NUMBER_OF_TEST_SWITCHES_OPERATED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_NUMBER_OF_TEST_SWITCHES_OPERATED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_PERIMETER_INTRUSION_DAY_COUNTER_CUMULATED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_PERIMETER_INTRUSION_DAY_COUNTER_CUMULATED);
	public static final ChannelUID ITRON_SMOKE_DETECTOR_SMOKE_INLET_DAY_COUNTER_CUMULATED_UID = new ChannelUID(ITRON_SMOKE_DETECTOR_THING_TYPE_UID, CHANNEL_ID_SMOKE_INLET_DAY_COUNTER_CUMULATED);

	// ChannelUIDs for techem_hkv45
	public static final ChannelUID TECHEM_HKV45_CURRENT_READING_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_HKV45_LAST_READING_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_HKV45_LAST_DATE_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_HKV45_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_HKV45_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV45_CURRENT_DATE_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_HKV45_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_HKV45_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV45_RECEPTION_UID = new ChannelUID(TECHEM_HKV45_THING_TYPE_UID, CHANNEL_ID_RECEPTION);

	// ChannelUIDs for techem_hkv61
	public static final ChannelUID TECHEM_HKV61_CURRENT_READING_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_HKV61_LAST_READING_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_HKV61_RECEPTION_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_RECEPTION);
	public static final ChannelUID TECHEM_HKV61_LAST_DATE_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_HKV61_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_HKV61_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV61_CURRENT_DATE_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_HKV61_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_HKV61_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV61_ALMANAC_UID = new ChannelUID(TECHEM_HKV61_THING_TYPE_UID, CHANNEL_ID_ALMANAC);

	// ChannelUIDs for techem_hkv64
	public static final ChannelUID TECHEM_HKV64_CURRENT_READING_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_HKV64_LAST_READING_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_HKV64_RECEPTION_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_RECEPTION);
	public static final ChannelUID TECHEM_HKV64_LAST_DATE_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_HKV64_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_HKV64_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV64_CURRENT_DATE_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_HKV64_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_HKV64_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV64_ALMANAC_UID = new ChannelUID(TECHEM_HKV64_THING_TYPE_UID, CHANNEL_ID_ALMANAC);

	// ChannelUIDs for techem_hkv69
	public static final ChannelUID TECHEM_HKV69_ROOM_TEMPERATURE_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_ROOM_TEMPERATURE);
	public static final ChannelUID TECHEM_HKV69_RADIATOR_TEMPERATURE_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_RADIATOR_TEMPERATURE);
	public static final ChannelUID TECHEM_HKV69_CURRENT_READING_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_HKV69_LAST_READING_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_HKV69_RECEPTION_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_RECEPTION);
	public static final ChannelUID TECHEM_HKV69_LAST_DATE_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_HKV69_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_HKV69_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV69_CURRENT_DATE_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_HKV69_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_HKV69_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV69_ALMANAC_UID = new ChannelUID(TECHEM_HKV69_THING_TYPE_UID, CHANNEL_ID_ALMANAC);

	// ChannelUIDs for techem_hkv94
	public static final ChannelUID TECHEM_HKV94_ROOM_TEMPERATURE_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_ROOM_TEMPERATURE);
	public static final ChannelUID TECHEM_HKV94_RADIATOR_TEMPERATURE_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_RADIATOR_TEMPERATURE);
	public static final ChannelUID TECHEM_HKV94_CURRENT_READING_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_HKV94_LAST_READING_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_HKV94_RECEPTION_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_RECEPTION);
	public static final ChannelUID TECHEM_HKV94_LAST_DATE_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_HKV94_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_HKV94_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_HKV94_CURRENT_DATE_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_HKV94_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_HKV94_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_HKV94_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);

	// ChannelUIDs for techem_sd76
	public static final ChannelUID TECHEM_SD76_STATUS_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_STATUS);
	public static final ChannelUID TECHEM_SD76_CURRENT_DATE_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_SD76_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_SD76_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_SD76_CURRENT_DATE_SMOKE_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_SMOKE);
	public static final ChannelUID TECHEM_SD76_CURRENT_DATE_SMOKE_STRING_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_SMOKE_STRING);
	public static final ChannelUID TECHEM_SD76_CURRENT_DATE_SMOKE_NUMBER_UID = new ChannelUID(TECHEM_SD76_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_SMOKE_NUMBER);

	// ChannelUIDs for techem_wz62
	public static final ChannelUID TECHEM_WZ62_STATUS_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_STATUS);
	public static final ChannelUID TECHEM_WZ62_CURRENT_READING_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_WZ62_LAST_READING_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_WZ62_LAST_DATE_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_WZ62_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_WZ62_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_WZ62_CURRENT_DATE_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_WZ62_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_WZ62_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_WZ62_RECEPTION_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_RECEPTION);
	public static final ChannelUID TECHEM_WZ62_COUNTER_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_COUNTER);
	public static final ChannelUID TECHEM_WZ62_ALMANAC_UID = new ChannelUID(TECHEM_WZ62_THING_TYPE_UID, CHANNEL_ID_ALMANAC);

	// ChannelUIDs for techem_wz72
	public static final ChannelUID TECHEM_WZ72_CURRENT_READING_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_WZ72_LAST_READING_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_WZ72_LAST_DATE_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_WZ72_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_WZ72_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_WZ72_CURRENT_DATE_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_WZ72_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_WZ72_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_WZ72_RECEPTION_UID = new ChannelUID(TECHEM_WZ72_THING_TYPE_UID, CHANNEL_ID_RECEPTION);

	// ChannelUIDs for techem_wmz43
	public static final ChannelUID TECHEM_WMZ43_CURRENT_READING_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_CURRENT_READING);
	public static final ChannelUID TECHEM_WMZ43_LAST_READING_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_LAST_READING);
	public static final ChannelUID TECHEM_WMZ43_LAST_DATE_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_LAST_DATE);
	public static final ChannelUID TECHEM_WMZ43_LAST_DATE_STRING_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_STRING);
	public static final ChannelUID TECHEM_WMZ43_LAST_DATE_NUMBER_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_LAST_DATE_NUMBER);
	public static final ChannelUID TECHEM_WMZ43_CURRENT_DATE_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE);
	public static final ChannelUID TECHEM_WMZ43_CURRENT_DATE_STRING_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_STRING);
	public static final ChannelUID TECHEM_WMZ43_CURRENT_DATE_NUMBER_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_CURRENT_DATE_NUMBER);
	public static final ChannelUID TECHEM_WMZ43_RECEPTION_UID = new ChannelUID(TECHEM_WMZ43_THING_TYPE_UID, CHANNEL_ID_RECEPTION);

	// ChannelUIDs for meter

	// ChannelUIDs for encrypted_meter

	// ChannelUIDs for thing_with_groups

}
