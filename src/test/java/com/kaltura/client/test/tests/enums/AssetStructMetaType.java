package com.kaltura.client.test.tests.enums;

import com.kaltura.client.enums.EnumAsString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum AssetStructMetaType implements EnumAsString {
    MULTI_LINGUAL_TEXT("Text"),
    STRING("String"),
    NUMBER("Number"),
    DATE("Date"),
    BOOLEAN("Boolean"),
    //TAG("tag"),
    ALL("All");

    private String value;
    private static List<Integer> idsOfMetaTypes = null;
//    private static Map<String, Integer> types2IdsInDb = null;

    /*Data chosen from that ENUM provided by Lior
    public enum MetaType
    {
        All = 0,
        String = 1,
        Number = 2,
        Bool = 3,
        Tag = 4,
        DateTime = 5,
        MultilingualString = 6
    }*/
    public static List<Integer> getIdsOfMetaTypes() {
        if (idsOfMetaTypes == null) {
            idsOfMetaTypes = new ArrayList<>();
            idsOfMetaTypes.add(6); // text multilingual
            idsOfMetaTypes.add(2); // number
            idsOfMetaTypes.add(5); // date
            idsOfMetaTypes.add(3); // boolean
            idsOfMetaTypes.add(1); // String
        }
        return idsOfMetaTypes;
    }

//    public static Map<String, Integer> getTypes2IdsInDb() {
//        if (types2IdsInDb == null) {
//            types2IdsInDb.put("Text", 6);
//            types2IdsInDb.put("Number", 2);
//            types2IdsInDb.put("Date", 5);
//            types2IdsInDb.put("Boolean", 3);
//            types2IdsInDb.put("String", 1);
//        }
//        return types2IdsInDb;
//    }

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
