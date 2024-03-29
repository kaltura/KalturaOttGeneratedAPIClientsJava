// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
// text.
//
// Copyright (C) 2006-2023  Kaltura Inc.
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
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum RuleConditionType implements EnumAsString {
	ASSET("ASSET"),
	COUNTRY("COUNTRY"),
	CONCURRENCY("CONCURRENCY"),
	IP_RANGE("IP_RANGE"),
	BUSINESS_MODULE("BUSINESS_MODULE"),
	SEGMENTS("SEGMENTS"),
	DATE("DATE"),
	OR("OR"),
	HEADER("HEADER"),
	USER_SUBSCRIPTION("USER_SUBSCRIPTION"),
	ASSET_SUBSCRIPTION("ASSET_SUBSCRIPTION"),
	USER_ROLE("USER_ROLE"),
	DEVICE_BRAND("DEVICE_BRAND"),
	DEVICE_FAMILY("DEVICE_FAMILY"),
	DEVICE_MANUFACTURER("DEVICE_MANUFACTURER"),
	DEVICE_MODEL("DEVICE_MODEL"),
	DEVICE_UDID_DYNAMIC_LIST("DEVICE_UDID_DYNAMIC_LIST"),
	DYNAMIC_KEYS("DYNAMIC_KEYS"),
	USER_SESSION_PROFILE("USER_SESSION_PROFILE"),
	DEVICE_DYNAMIC_DATA("DEVICE_DYNAMIC_DATA"),
	IP_V6_RANGE("IP_V6_RANGE"),
	ASSET_SHOP("ASSET_SHOP"),
	CHANNEL("CHANNEL"),
	FILE_TYPE("FILE_TYPE");

	private String value;

	RuleConditionType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static RuleConditionType get(String value) {
		if(value == null)
		{
			return null;
		}
		
		// goes over RuleConditionType defined values and compare the inner value with the given one:
		for(RuleConditionType item: values()) {
			if(item.getValue().equals(value)) {
				return item;
			}
		}
		// in case the requested value was not found in the enum values, we return the first item as default.
		return RuleConditionType.values().length > 0 ? RuleConditionType.values()[0]: null;
   }
}
