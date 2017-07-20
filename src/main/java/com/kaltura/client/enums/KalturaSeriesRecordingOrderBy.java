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
public enum KalturaSeriesRecordingOrderBy implements KalturaEnumAsString {
    START_DATE_ASC ("START_DATE_ASC"),
    START_DATE_DESC ("START_DATE_DESC"),
    ID_ASC ("ID_ASC"),
    ID_DESC ("ID_DESC"),
    SERIES_ID_ASC ("SERIES_ID_ASC"),
    SERIES_ID_DESC ("SERIES_ID_DESC");

    public String hashCode;

    KalturaSeriesRecordingOrderBy(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaSeriesRecordingOrderBy get(String hashCode) {
        if (hashCode.equals("START_DATE_ASC"))
        {
           return START_DATE_ASC;
        }
        else 
        if (hashCode.equals("START_DATE_DESC"))
        {
           return START_DATE_DESC;
        }
        else 
        if (hashCode.equals("ID_ASC"))
        {
           return ID_ASC;
        }
        else 
        if (hashCode.equals("ID_DESC"))
        {
           return ID_DESC;
        }
        else 
        if (hashCode.equals("SERIES_ID_ASC"))
        {
           return SERIES_ID_ASC;
        }
        else 
        if (hashCode.equals("SERIES_ID_DESC"))
        {
           return SERIES_ID_DESC;
        }
        else 
        {
           return START_DATE_ASC;
        }
    }
}
