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
public enum KalturaLanguageOrderBy implements KalturaEnumAsString {
    SYSTEM_NAME_ASC ("SYSTEM_NAME_ASC"),
    SYSTEM_NAME_DESC ("SYSTEM_NAME_DESC"),
    CODE_ASC ("CODE_ASC"),
    CODE_DESC ("CODE_DESC");

    public String hashCode;

    KalturaLanguageOrderBy(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaLanguageOrderBy get(String hashCode) {
        if (hashCode.equals("SYSTEM_NAME_ASC"))
        {
           return SYSTEM_NAME_ASC;
        }
        else 
        if (hashCode.equals("SYSTEM_NAME_DESC"))
        {
           return SYSTEM_NAME_DESC;
        }
        else 
        if (hashCode.equals("CODE_ASC"))
        {
           return CODE_ASC;
        }
        else 
        if (hashCode.equals("CODE_DESC"))
        {
           return CODE_DESC;
        }
        else 
        {
           return SYSTEM_NAME_ASC;
        }
    }
}
