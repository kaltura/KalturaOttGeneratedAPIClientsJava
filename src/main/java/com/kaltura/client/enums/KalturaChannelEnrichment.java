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
public enum KalturaChannelEnrichment implements KalturaEnumAsString {
    CLIENTLOCATION ("ClientLocation"),
    USERID ("UserId"),
    HOUSEHOLDID ("HouseholdId"),
    DEVICEID ("DeviceId"),
    DEVICETYPE ("DeviceType"),
    UTCOFFSET ("UTCOffset"),
    LANGUAGE ("Language"),
    NPVRSUPPORT ("NPVRSupport"),
    CATCHUP ("Catchup"),
    PARENTAL ("Parental"),
    DTTREGION ("DTTRegion"),
    ATHOME ("AtHome");

    public String hashCode;

    KalturaChannelEnrichment(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaChannelEnrichment get(String hashCode) {
        if (hashCode.equals("ClientLocation"))
        {
           return CLIENTLOCATION;
        }
        else 
        if (hashCode.equals("UserId"))
        {
           return USERID;
        }
        else 
        if (hashCode.equals("HouseholdId"))
        {
           return HOUSEHOLDID;
        }
        else 
        if (hashCode.equals("DeviceId"))
        {
           return DEVICEID;
        }
        else 
        if (hashCode.equals("DeviceType"))
        {
           return DEVICETYPE;
        }
        else 
        if (hashCode.equals("UTCOffset"))
        {
           return UTCOFFSET;
        }
        else 
        if (hashCode.equals("Language"))
        {
           return LANGUAGE;
        }
        else 
        if (hashCode.equals("NPVRSupport"))
        {
           return NPVRSUPPORT;
        }
        else 
        if (hashCode.equals("Catchup"))
        {
           return CATCHUP;
        }
        else 
        if (hashCode.equals("Parental"))
        {
           return PARENTAL;
        }
        else 
        if (hashCode.equals("DTTRegion"))
        {
           return DTTREGION;
        }
        else 
        if (hashCode.equals("AtHome"))
        {
           return ATHOME;
        }
        else 
        {
           return CLIENTLOCATION;
        }
    }
}
