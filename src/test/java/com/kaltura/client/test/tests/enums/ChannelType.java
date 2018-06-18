package com.kaltura.client.test.tests.enums;

import com.kaltura.client.enums.EnumAsString;

public enum ChannelType implements EnumAsString {
	AUTOMATIC_CHANNEL_TYPE("1"),
    KSQL_CHANNEL_TYPE("4"),
    MANUAL_CHANNEL_TYPE("2");

	private String value;

	ChannelType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static ChannelType get(String value) {
		if(value == null)
		{
			return null;
		}
		
		// goes over Channel types defined values and compare the inner value with the given one:
		for(ChannelType item: values()) {
			if(item.getValue().equals(value)) {
				return item;
			}
		}
		// in case the requested value was not found in the enum values, we return the first item as default.
		return ChannelType.values().length > 0 ? ChannelType.values()[0]: null;
   }
}
