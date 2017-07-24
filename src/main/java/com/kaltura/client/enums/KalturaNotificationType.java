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
public enum KalturaNotificationType implements KalturaEnumAsString {
    ANNOUNCEMENT ("announcement"),
    SYSTEM ("system"),
    REMINDER ("Reminder"),
    SERIES_REMINDER ("series_reminder");

    public String hashCode;

    KalturaNotificationType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaNotificationType get(String hashCode) {
        if (hashCode.equals("announcement"))
        {
           return ANNOUNCEMENT;
        }
        else 
        if (hashCode.equals("system"))
        {
           return SYSTEM;
        }
        else 
        if (hashCode.equals("Reminder"))
        {
           return REMINDER;
        }
        else 
        if (hashCode.equals("series_reminder"))
        {
           return SERIES_REMINDER;
        }
        else 
        {
           return ANNOUNCEMENT;
        }
    }
}
