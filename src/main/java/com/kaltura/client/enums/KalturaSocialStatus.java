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
public enum KalturaSocialStatus implements KalturaEnumAsString {
    ERROR ("error"),
    OK ("ok"),
    USER_DOES_NOT_EXIST ("user_does_not_exist"),
    NO_USER_SOCIAL_SETTINGS_FOUND ("no_user_social_settings_found"),
    ASSET_ALREADY_LIKED ("asset_already_liked"),
    NOT_ALLOWED ("not_allowed"),
    INVALID_PARAMETERS ("invalid_parameters"),
    NO_FACEBOOK_ACTION ("no_facebook_action"),
    ASSET_ALREADY_RATED ("asset_already_rated"),
    ASSET_DOSE_NOT_EXISTS ("asset_dose_not_exists"),
    INVALID_PLATFORM_REQUEST ("invalid_platform_request"),
    INVALID_ACCESS_TOKEN ("invalid_access_token");

    public String hashCode;

    KalturaSocialStatus(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaSocialStatus get(String hashCode) {
        if (hashCode.equals("error"))
        {
           return ERROR;
        }
        else 
        if (hashCode.equals("ok"))
        {
           return OK;
        }
        else 
        if (hashCode.equals("user_does_not_exist"))
        {
           return USER_DOES_NOT_EXIST;
        }
        else 
        if (hashCode.equals("no_user_social_settings_found"))
        {
           return NO_USER_SOCIAL_SETTINGS_FOUND;
        }
        else 
        if (hashCode.equals("asset_already_liked"))
        {
           return ASSET_ALREADY_LIKED;
        }
        else 
        if (hashCode.equals("not_allowed"))
        {
           return NOT_ALLOWED;
        }
        else 
        if (hashCode.equals("invalid_parameters"))
        {
           return INVALID_PARAMETERS;
        }
        else 
        if (hashCode.equals("no_facebook_action"))
        {
           return NO_FACEBOOK_ACTION;
        }
        else 
        if (hashCode.equals("asset_already_rated"))
        {
           return ASSET_ALREADY_RATED;
        }
        else 
        if (hashCode.equals("asset_dose_not_exists"))
        {
           return ASSET_DOSE_NOT_EXISTS;
        }
        else 
        if (hashCode.equals("invalid_platform_request"))
        {
           return INVALID_PLATFORM_REQUEST;
        }
        else 
        if (hashCode.equals("invalid_access_token"))
        {
           return INVALID_ACCESS_TOKEN;
        }
        else 
        {
           return ERROR;
        }
    }
}
