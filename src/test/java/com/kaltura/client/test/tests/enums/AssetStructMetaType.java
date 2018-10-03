package com.kaltura.client.test.tests.enums;

import com.kaltura.client.enums.EnumAsString;

public enum AssetStructMetaType implements EnumAsString {
    TEXT("Text"),
    NUMBER("Number"),
    DATE("Date"),
    BOOLEAN("Boolean"),
    ALL("All");

    private String value;

    AssetStructMetaType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static AssetStructMetaType get(String value) {
        if(value == null) {
            return null;
        }

        // goes over defined values and compare the inner value with the given one:
        for(AssetStructMetaType item: values()) {
            if(item.getValue().equals(value)) {
                return item;
            }
        }
        // in case the requested value was not found in the enum values, we return the first item as default.
        return AssetStructMetaType.values().length > 0 ? AssetStructMetaType.values()[0]: null;
    }
}
