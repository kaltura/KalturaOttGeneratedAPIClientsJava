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
// Copyright (C) 2006-2022  Kaltura Inc.
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

import com.kaltura.client.types.DeviceBrand;
import com.kaltura.client.types.DeviceBrandFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DeviceBrandService {
	
	public static class AddDeviceBrandBuilder extends RequestBuilder<DeviceBrand, DeviceBrand.Tokenizer, AddDeviceBrandBuilder> {
		
		public AddDeviceBrandBuilder(DeviceBrand deviceBrand) {
			super(DeviceBrand.class, "devicebrand", "add");
			params.add("deviceBrand", deviceBrand);
		}
	}

	/**
	 * Adds a new device brand which belongs to a specific group.
	 * 
	 * @param deviceBrand Device brand.
	 */
    public static AddDeviceBrandBuilder add(DeviceBrand deviceBrand)  {
		return new AddDeviceBrandBuilder(deviceBrand);
	}
	
	public static class ListDeviceBrandBuilder extends ListResponseRequestBuilder<DeviceBrand, DeviceBrand.Tokenizer, ListDeviceBrandBuilder> {
		
		public ListDeviceBrandBuilder(DeviceBrandFilter filter, FilterPager pager) {
			super(DeviceBrand.class, "devicebrand", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListDeviceBrandBuilder list()  {
		return list(null);
	}

	public static ListDeviceBrandBuilder list(DeviceBrandFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Return a list of the available device brands.
	 * 
	 * @param filter Filter with no more than one condition specified.
	 * @param pager Page size and index.
	 */
    public static ListDeviceBrandBuilder list(DeviceBrandFilter filter, FilterPager pager)  {
		return new ListDeviceBrandBuilder(filter, pager);
	}
	
	public static class UpdateDeviceBrandBuilder extends RequestBuilder<DeviceBrand, DeviceBrand.Tokenizer, UpdateDeviceBrandBuilder> {
		
		public UpdateDeviceBrandBuilder(long id, DeviceBrand deviceBrand) {
			super(DeviceBrand.class, "devicebrand", "update");
			params.add("id", id);
			params.add("deviceBrand", deviceBrand);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Updates an existing device brand which belongs to a specific group.
	 * 
	 * @param id Device brand's identifier.
	 * @param deviceBrand Device brand.
	 */
    public static UpdateDeviceBrandBuilder update(long id, DeviceBrand deviceBrand)  {
		return new UpdateDeviceBrandBuilder(id, deviceBrand);
	}
}
