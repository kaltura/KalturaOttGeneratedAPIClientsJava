// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2017  Kaltura Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.kaltura.client.enums;

import com.google.gson.annotations.SerializedName;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum MessageTemplateType implements EnumAsInt {
    SERIES(0),
    REMINDER(1),
    CHURN(2),
    SERIESREMINDER(3),
    INTERESTVOD(4),
    INTERESTEPG(5);

    private int value;

    MessageTemplateType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static MessageTemplateType get(Integer value) {
        if(value == null)
        {
        	return null;
        }
        
        // goes over MessageTemplateType defined values and compare the inner value with the given one:
        for(MessageTemplateType item: values()) {
            if(item.getValue() == value) {
                return item;
            }
        }
        // in case the requested value was not found in the enum values, we return the first item as default.
        return MessageTemplateType.values().length > 0 ? MessageTemplateType.values()[0]: null;
   }
}
