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
public enum KalturaUserState implements KalturaEnumAsString {
    OK ("ok"),
    USER_WITH_NO_HOUSEHOLD ("user_with_no_household"),
    USER_CREATED_WITH_NO_ROLE ("user_created_with_no_role"),
    USER_NOT_ACTIVATED ("user_not_activated");

    public String hashCode;

    KalturaUserState(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaUserState get(String hashCode) {
        if (hashCode.equals("ok"))
        {
           return OK;
        }
        else 
        if (hashCode.equals("user_with_no_household"))
        {
           return USER_WITH_NO_HOUSEHOLD;
        }
        else 
        if (hashCode.equals("user_created_with_no_role"))
        {
           return USER_CREATED_WITH_NO_ROLE;
        }
        else 
        if (hashCode.equals("user_not_activated"))
        {
           return USER_NOT_ACTIVATED;
        }
        else 
        {
           return OK;
        }
    }
}
