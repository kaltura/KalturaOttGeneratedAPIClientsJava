package com.kaltura.client.test.tests.enums;

import com.kaltura.client.enums.EnumAsString;

public enum PremiumService implements EnumAsString {
    CATCH_UP("1"),
    START_OVER("2"),
    NPVR("3"),
    DOWNLOAD("4"),
    AD_CONTROL("5");

    private String value;

    PremiumService(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static PremiumService get(String value) {
        if(value == null) {
            return null;
        }

        // goes over Currency defined values and compare the inner value with the given one:
        for(PremiumService item: values()) {
            if(item.getValue().equals(value)) {
                return item;
            }
        }
        // in case the requested value was not found in the enum values, we return the first item as default.
        return Currency.values().length > 0 ? PremiumService.values()[0]: null;
    }
}
