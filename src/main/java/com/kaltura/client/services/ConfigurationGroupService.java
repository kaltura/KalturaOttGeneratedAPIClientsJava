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

import com.kaltura.client.types.ConfigurationGroup;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationGroupService {
	
	public static class AddConfigurationGroupBuilder extends RequestBuilder<ConfigurationGroup, ConfigurationGroup.Tokenizer, AddConfigurationGroupBuilder> {
		
		public AddConfigurationGroupBuilder(ConfigurationGroup configurationGroup) {
			super(ConfigurationGroup.class, "configurationgroup", "add");
			params.add("configurationGroup", configurationGroup);
		}
	}

	/**
	 * Add a new configuration group
	 * 
	 * @param configurationGroup Configuration group
	 */
    public static AddConfigurationGroupBuilder add(ConfigurationGroup configurationGroup)  {
		return new AddConfigurationGroupBuilder(configurationGroup);
	}
	
	public static class DeleteConfigurationGroupBuilder extends RequestBuilder<Boolean, String, DeleteConfigurationGroupBuilder> {
		
		public DeleteConfigurationGroupBuilder(String id) {
			super(Boolean.class, "configurationgroup", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Remove a configuration group, including its tags, device configurations and
	  devices associations
	 * 
	 * @param id Configuration group identifier
	 */
    public static DeleteConfigurationGroupBuilder delete(String id)  {
		return new DeleteConfigurationGroupBuilder(id);
	}
	
	public static class GetConfigurationGroupBuilder extends RequestBuilder<ConfigurationGroup, ConfigurationGroup.Tokenizer, GetConfigurationGroupBuilder> {
		
		public GetConfigurationGroupBuilder(String id) {
			super(ConfigurationGroup.class, "configurationgroup", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Return the configuration group details, including group identifiers, tags, and
	  number of associated devices, and list of device configuration
	 * 
	 * @param id Configuration group identifier
	 */
    public static GetConfigurationGroupBuilder get(String id)  {
		return new GetConfigurationGroupBuilder(id);
	}
	
	public static class ListConfigurationGroupBuilder extends ListResponseRequestBuilder<ConfigurationGroup, ConfigurationGroup.Tokenizer, ListConfigurationGroupBuilder> {
		
		public ListConfigurationGroupBuilder() {
			super(ConfigurationGroup.class, "configurationgroup", "list");
		}
	}

	/**
	 * Return the list of configuration groups
	 */
    public static ListConfigurationGroupBuilder list()  {
		return new ListConfigurationGroupBuilder();
	}
	
	public static class UpdateConfigurationGroupBuilder extends RequestBuilder<ConfigurationGroup, ConfigurationGroup.Tokenizer, UpdateConfigurationGroupBuilder> {
		
		public UpdateConfigurationGroupBuilder(String id, ConfigurationGroup configurationGroup) {
			super(ConfigurationGroup.class, "configurationgroup", "update");
			params.add("id", id);
			params.add("configurationGroup", configurationGroup);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update configuration group name
	 * 
	 * @param id Configuration group identifier
	 * @param configurationGroup Configuration group
	 */
    public static UpdateConfigurationGroupBuilder update(String id, ConfigurationGroup configurationGroup)  {
		return new UpdateConfigurationGroupBuilder(id, configurationGroup);
	}
}
