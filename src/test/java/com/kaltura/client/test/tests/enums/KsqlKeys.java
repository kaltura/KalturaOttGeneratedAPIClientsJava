package com.kaltura.client.test.tests.enums;

import com.kaltura.client.enums.EnumAsString;

public enum KsqlKeys implements EnumAsString {

    START_DATE("start_date"),
    END_DATE("end_date"),
    GEO_BLOCK("geo_block"),
    PARENTAL_RULES("parental_rules"),
    USER_INTERESTS("user_interests"),
    EPG_CHANNEL_ID("epg_channel_id"),
    EPG_ID("epg_id"),
    MEDIA_ID("media_id"),
    ENTITLED_ASSETS("entitled_assets");

    private String value;

    KsqlKeys(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static KsqlKeys get(String value) {
        if(value == null) {
            return null;
        }

        // goes over Currency defined values and compare the inner value with the given one:
        for(KsqlKeys item: values()) {
            if(item.getValue().equals(value)) {
                return item;
            }
        }
        // in case the requested value was not found in the enum values, we return the first item as default.
        return Currency.values().length > 0 ? KsqlKeys.values()[0]: null;
    }
}
