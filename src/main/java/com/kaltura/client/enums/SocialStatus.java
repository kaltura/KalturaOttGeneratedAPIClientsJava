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
// Copyright (C) 2006-2018  Kaltura Inc.
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
public enum SocialStatus implements EnumAsString {
	ERROR("error"),
	OK("ok"),
	USER_DOES_NOT_EXIST("user_does_not_exist"),
	NO_USER_SOCIAL_SETTINGS_FOUND("no_user_social_settings_found"),
	ASSET_ALREADY_LIKED("asset_already_liked"),
	NOT_ALLOWED("not_allowed"),
	INVALID_PARAMETERS("invalid_parameters"),
	NO_FACEBOOK_ACTION("no_facebook_action"),
	ASSET_ALREADY_RATED("asset_already_rated"),
	ASSET_DOSE_NOT_EXISTS("asset_dose_not_exists"),
	INVALID_PLATFORM_REQUEST("invalid_platform_request"),
	INVALID_ACCESS_TOKEN("invalid_access_token");

	private String value;

	SocialStatus(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static SocialStatus get(String value) {
		if(value == null)
		{
			return null;
		}
		
		// goes over SocialStatus defined values and compare the inner value with the given one:
		for(SocialStatus item: values()) {
			if(item.getValue().equals(value)) {
				return item;
			}
		}
		// in case the requested value was not found in the enum values, we return the first item as default.
		return SocialStatus.values().length > 0 ? SocialStatus.values()[0]: null;
   }
}
