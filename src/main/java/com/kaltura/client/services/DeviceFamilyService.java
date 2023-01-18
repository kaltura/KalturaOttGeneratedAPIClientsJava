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
package com.kaltura.client.services;

import com.kaltura.client.types.DeviceFamily;
import com.kaltura.client.types.DeviceFamilyFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DeviceFamilyService {
	
	public static class AddDeviceFamilyBuilder extends RequestBuilder<DeviceFamily, DeviceFamily.Tokenizer, AddDeviceFamilyBuilder> {
		
		public AddDeviceFamilyBuilder(DeviceFamily deviceFamily) {
			super(DeviceFamily.class, "devicefamily", "add");
			params.add("deviceFamily", deviceFamily);
		}
	}

	/**
	 * Adds a new device family which belongs to a specific group.
	 * 
	 * @param deviceFamily Device family.
	 */
    public static AddDeviceFamilyBuilder add(DeviceFamily deviceFamily)  {
		return new AddDeviceFamilyBuilder(deviceFamily);
	}
	
	public static class ListDeviceFamilyBuilder extends ListResponseRequestBuilder<DeviceFamily, DeviceFamily.Tokenizer, ListDeviceFamilyBuilder> {
		
		public ListDeviceFamilyBuilder(DeviceFamilyFilter filter, FilterPager pager) {
			super(DeviceFamily.class, "devicefamily", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListDeviceFamilyBuilder list()  {
		return list(null);
	}

	public static ListDeviceFamilyBuilder list(DeviceFamilyFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Return a list of the available device families.
	 * 
	 * @param filter Filter with no more than one condition specified.
	 * @param pager Page size and index.
	 */
    public static ListDeviceFamilyBuilder list(DeviceFamilyFilter filter, FilterPager pager)  {
		return new ListDeviceFamilyBuilder(filter, pager);
	}
	
	public static class UpdateDeviceFamilyBuilder extends RequestBuilder<DeviceFamily, DeviceFamily.Tokenizer, UpdateDeviceFamilyBuilder> {
		
		public UpdateDeviceFamilyBuilder(long id, DeviceFamily deviceFamily) {
			super(DeviceFamily.class, "devicefamily", "update");
			params.add("id", id);
			params.add("deviceFamily", deviceFamily);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Updates an existing device family which belongs to a specific group.
	 * 
	 * @param id Device family's identifier.
	 * @param deviceFamily Device family.
	 */
    public static UpdateDeviceFamilyBuilder update(long id, DeviceFamily deviceFamily)  {
		return new UpdateDeviceFamilyBuilder(id, deviceFamily);
	}
}
