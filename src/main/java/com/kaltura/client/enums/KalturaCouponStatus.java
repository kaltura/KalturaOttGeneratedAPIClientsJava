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

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaCouponStatus implements KalturaEnumAsString {
    VALID ("VALID"),
    NOT_EXISTS ("NOT_EXISTS"),
    ALREADY_USED ("ALREADY_USED"),
    EXPIRED ("EXPIRED"),
    INACTIVE ("INACTIVE");

    public String hashCode;

    KalturaCouponStatus(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaCouponStatus get(String hashCode) {
        if (hashCode.equals("VALID"))
        {
           return VALID;
        }
        else 
        if (hashCode.equals("NOT_EXISTS"))
        {
           return NOT_EXISTS;
        }
        else 
        if (hashCode.equals("ALREADY_USED"))
        {
           return ALREADY_USED;
        }
        else 
        if (hashCode.equals("EXPIRED"))
        {
           return EXPIRED;
        }
        else 
        if (hashCode.equals("INACTIVE"))
        {
           return INACTIVE;
        }
        else 
        {
           return VALID;
        }
    }
}
