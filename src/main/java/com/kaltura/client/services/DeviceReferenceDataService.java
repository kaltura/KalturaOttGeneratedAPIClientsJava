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
// Copyright (C) 2006-2020  Kaltura Inc.
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

import com.kaltura.client.types.DeviceReferenceData;
import com.kaltura.client.types.DeviceReferenceDataFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DeviceReferenceDataService {
	
	public static class AddDeviceReferenceDataBuilder extends RequestBuilder<DeviceReferenceData, DeviceReferenceData.Tokenizer, AddDeviceReferenceDataBuilder> {
		
		public AddDeviceReferenceDataBuilder(DeviceReferenceData objectToAdd) {
			super(DeviceReferenceData.class, "devicereferencedata", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add an object
	 * 
	 * @param objectToAdd Object to add
	 */
    public static AddDeviceReferenceDataBuilder add(DeviceReferenceData objectToAdd)  {
		return new AddDeviceReferenceDataBuilder(objectToAdd);
	}
	
	public static class UpdateDeviceReferenceDataBuilder extends RequestBuilder<DeviceReferenceData, DeviceReferenceData.Tokenizer, UpdateDeviceReferenceDataBuilder> {
		
		public UpdateDeviceReferenceDataBuilder(long id, DeviceReferenceData objectToUpdate) {
			super(DeviceReferenceData.class, "devicereferencedata", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an object
	 * 
	 * @param id Object ID to update
	 * @param objectToUpdate Object to update
	 */
    public static UpdateDeviceReferenceDataBuilder update(long id, DeviceReferenceData objectToUpdate)  {
		return new UpdateDeviceReferenceDataBuilder(id, objectToUpdate);
	}
	
	public static class DeleteDeviceReferenceDataBuilder extends NullRequestBuilder {
		
		public DeleteDeviceReferenceDataBuilder(long id) {
			super("devicereferencedata", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an object
	 * 
	 * @param id Object ID to delete
	 */
    public static DeleteDeviceReferenceDataBuilder delete(long id)  {
		return new DeleteDeviceReferenceDataBuilder(id);
	}
	
	public static class ListDeviceReferenceDataBuilder extends ListResponseRequestBuilder<DeviceReferenceData, DeviceReferenceData.Tokenizer, ListDeviceReferenceDataBuilder> {
		
		public ListDeviceReferenceDataBuilder(DeviceReferenceDataFilter filter, FilterPager pager) {
			super(DeviceReferenceData.class, "devicereferencedata", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListDeviceReferenceDataBuilder list(DeviceReferenceDataFilter filter)  {
		return list(filter, null);
	}

    public static ListDeviceReferenceDataBuilder list(DeviceReferenceDataFilter filter, FilterPager pager)  {
		return new ListDeviceReferenceDataBuilder(filter, pager);
	}
}
