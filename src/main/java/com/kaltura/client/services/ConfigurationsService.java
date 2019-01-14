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

import com.kaltura.client.types.Configurations;
import com.kaltura.client.types.ConfigurationsFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import com.kaltura.client.utils.request.ServeRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationsService {
	
	public static class AddConfigurationsBuilder extends RequestBuilder<Configurations, Configurations.Tokenizer, AddConfigurationsBuilder> {
		
		public AddConfigurationsBuilder(Configurations configurations) {
			super(Configurations.class, "configurations", "add");
			params.add("configurations", configurations);
		}
	}

	/**
	 * Add a new device configuration to a configuration group
	 * 
	 * @param configurations Device configuration
	 */
    public static AddConfigurationsBuilder add(Configurations configurations)  {
		return new AddConfigurationsBuilder(configurations);
	}
	
	public static class DeleteConfigurationsBuilder extends RequestBuilder<Boolean, String, DeleteConfigurationsBuilder> {
		
		public DeleteConfigurationsBuilder(String id) {
			super(Boolean.class, "configurations", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a device configuration
	 * 
	 * @param id Configuration identifier
	 */
    public static DeleteConfigurationsBuilder delete(String id)  {
		return new DeleteConfigurationsBuilder(id);
	}
	
	public static class GetConfigurationsBuilder extends RequestBuilder<Configurations, Configurations.Tokenizer, GetConfigurationsBuilder> {
		
		public GetConfigurationsBuilder(String id) {
			super(Configurations.class, "configurations", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Return the device configuration
	 * 
	 * @param id Configuration identifier
	 */
    public static GetConfigurationsBuilder get(String id)  {
		return new GetConfigurationsBuilder(id);
	}
	
	public static class ListConfigurationsBuilder extends ListResponseRequestBuilder<Configurations, Configurations.Tokenizer, ListConfigurationsBuilder> {
		
		public ListConfigurationsBuilder(ConfigurationsFilter filter) {
			super(Configurations.class, "configurations", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Return a list of device configurations of a configuration group
	 * 
	 * @param filter Filter option for configuration group id.
	 */
    public static ListConfigurationsBuilder list(ConfigurationsFilter filter)  {
		return new ListConfigurationsBuilder(filter);
	}
	
	public static class ServeByDeviceConfigurationsBuilder extends ServeRequestBuilder {
		
		public ServeByDeviceConfigurationsBuilder(String applicationName, String clientVersion, String platform, String udid, String tag, int partnerId) {
			super("configurations", "serveByDevice");
			params.add("applicationName", applicationName);
			params.add("clientVersion", clientVersion);
			params.add("platform", platform);
			params.add("udid", udid);
			params.add("tag", tag);
			params.add("partnerId", partnerId);
		}
		
		public void applicationName(String multirequestToken) {
			params.add("applicationName", multirequestToken);
		}
		
		public void clientVersion(String multirequestToken) {
			params.add("clientVersion", multirequestToken);
		}
		
		public void platform(String multirequestToken) {
			params.add("platform", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
		
		public void tag(String multirequestToken) {
			params.add("tag", multirequestToken);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
	}

	public static ServeByDeviceConfigurationsBuilder serveByDevice(String applicationName, String clientVersion, String platform, String udid, String tag)  {
		return serveByDevice(applicationName, clientVersion, platform, udid, tag, 0);
	}

	/**
	 * Return a device configuration applicable for a specific device (UDID), app name,
	  software version, platform and optionally a configuration group’s tag
	 * 
	 * @param applicationName Application name
	 * @param clientVersion Client version
	 * @param platform platform
	 * @param udid Device UDID
	 * @param tag Tag
	 * @param partnerId Partner Id
	 */
    public static ServeByDeviceConfigurationsBuilder serveByDevice(String applicationName, String clientVersion, String platform, String udid, String tag, int partnerId)  {
		return new ServeByDeviceConfigurationsBuilder(applicationName, clientVersion, platform, udid, tag, partnerId);
	}
	
	public static class UpdateConfigurationsBuilder extends RequestBuilder<Configurations, Configurations.Tokenizer, UpdateConfigurationsBuilder> {
		
		public UpdateConfigurationsBuilder(String id, Configurations configurations) {
			super(Configurations.class, "configurations", "update");
			params.add("id", id);
			params.add("configurations", configurations);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update device configuration
	 * 
	 * @param id Configuration identifier
	 * @param configurations configuration to update
	 */
    public static UpdateConfigurationsBuilder update(String id, Configurations configurations)  {
		return new UpdateConfigurationsBuilder(id, configurations);
	}
}
