package com.kaltura.client.test.tests.enums;

import com.kaltura.client.enums.EnumAsString;

public enum DurationPeriod implements EnumAsString {
    DAYS("days"),
    HOURS("hours"),
    MINUTES("minutes"),
    SECONDS("seconds");

    private String value;

    DurationPeriod(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static DurationPeriod get(String value) {
        if(value == null) {
            return null;
        }

        // goes over Currency defined values and compare the inner value with the given one:
        for(DurationPeriod item: values()) {
            if(item.getValue().equals(value)) {
                return item;
            }
        }
        // in case the requested value was not found in the enum values, we return the first item as default.
        return Currency.values().length > 0 ? DurationPeriod.values()[0]: null;
    }

}
