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
public enum KalturaHouseholdState implements KalturaEnumAsString {
    OK ("ok"),
    CREATED_WITHOUT_NPVR_ACCOUNT ("created_without_npvr_account"),
    SUSPENDED ("suspended"),
    NO_USERS_IN_HOUSEHOLD ("no_users_in_household"),
    PENDING ("pending");

    public String hashCode;

    KalturaHouseholdState(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaHouseholdState get(String hashCode) {
        if (hashCode.equals("ok"))
        {
           return OK;
        }
        else 
        if (hashCode.equals("created_without_npvr_account"))
        {
           return CREATED_WITHOUT_NPVR_ACCOUNT;
        }
        else 
        if (hashCode.equals("suspended"))
        {
           return SUSPENDED;
        }
        else 
        if (hashCode.equals("no_users_in_household"))
        {
           return NO_USERS_IN_HOUSEHOLD;
        }
        else 
        if (hashCode.equals("pending"))
        {
           return PENDING;
        }
        else 
        {
           return OK;
        }
    }
}
