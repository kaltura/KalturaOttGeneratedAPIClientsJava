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
package com.kaltura.client.services;

import com.kaltura.client.types.ConfigurationGroupDevice;
import com.kaltura.client.types.ConfigurationGroupDeviceFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationGroupDeviceService {
	
	public static class AddConfigurationGroupDeviceBuilder extends RequestBuilder<Boolean, String, AddConfigurationGroupDeviceBuilder> {
		
		public AddConfigurationGroupDeviceBuilder(ConfigurationGroupDevice configurationGroupDevice) {
			super(Boolean.class, "configurationgroupdevice", "add");
			params.add("configurationGroupDevice", configurationGroupDevice);
		}
	}

	/**
	 * Associate a collection of devices to a configuration group. If a device is
	  already associated to another group – old association is replaced
	 * 
	 * @param configurationGroupDevice Configuration group device
	 */
    public static AddConfigurationGroupDeviceBuilder add(ConfigurationGroupDevice configurationGroupDevice)  {
		return new AddConfigurationGroupDeviceBuilder(configurationGroupDevice);
	}
	
	public static class DeleteConfigurationGroupDeviceBuilder extends RequestBuilder<Boolean, String, DeleteConfigurationGroupDeviceBuilder> {
		
		public DeleteConfigurationGroupDeviceBuilder(String udid) {
			super(Boolean.class, "configurationgroupdevice", "delete");
			params.add("udid", udid);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	/**
	 * Remove a device association
	 * 
	 * @param udid Device UDID
	 */
    public static DeleteConfigurationGroupDeviceBuilder delete(String udid)  {
		return new DeleteConfigurationGroupDeviceBuilder(udid);
	}
	
	public static class GetConfigurationGroupDeviceBuilder extends RequestBuilder<ConfigurationGroupDevice, ConfigurationGroupDevice.Tokenizer, GetConfigurationGroupDeviceBuilder> {
		
		public GetConfigurationGroupDeviceBuilder(String udid) {
			super(ConfigurationGroupDevice.class, "configurationgroupdevice", "get");
			params.add("udid", udid);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	/**
	 * Return the configuration group to which a specific device is associated to
	 * 
	 * @param udid Device UDID
	 */
    public static GetConfigurationGroupDeviceBuilder get(String udid)  {
		return new GetConfigurationGroupDeviceBuilder(udid);
	}
	
	public static class ListConfigurationGroupDeviceBuilder extends ListResponseRequestBuilder<ConfigurationGroupDevice, ConfigurationGroupDevice.Tokenizer, ListConfigurationGroupDeviceBuilder> {
		
		public ListConfigurationGroupDeviceBuilder(ConfigurationGroupDeviceFilter filter, FilterPager pager) {
			super(ConfigurationGroupDevice.class, "configurationgroupdevice", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListConfigurationGroupDeviceBuilder list(ConfigurationGroupDeviceFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Return the list of associated devices for a given configuration group
	 * 
	 * @param filter Filter option for configuration group identifier
	 * @param pager Page size and index
	 */
    public static ListConfigurationGroupDeviceBuilder list(ConfigurationGroupDeviceFilter filter, FilterPager pager)  {
		return new ListConfigurationGroupDeviceBuilder(filter, pager);
	}
}
