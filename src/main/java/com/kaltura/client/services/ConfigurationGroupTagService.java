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

import com.kaltura.client.types.ConfigurationGroupTag;
import com.kaltura.client.types.ConfigurationGroupTagFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationGroupTagService {
	
	public static class AddConfigurationGroupTagBuilder extends RequestBuilder<ConfigurationGroupTag, ConfigurationGroupTag.Tokenizer, AddConfigurationGroupTagBuilder> {
		
		public AddConfigurationGroupTagBuilder(ConfigurationGroupTag configurationGroupTag) {
			super(ConfigurationGroupTag.class, "configurationgrouptag", "add");
			params.add("configurationGroupTag", configurationGroupTag);
		}
	}

	/**
	 * Add a new tag to a configuration group. If this tag is already associated to
	  another group, request fails
	 * 
	 * @param configurationGroupTag Configuration group tag
	 */
    public static AddConfigurationGroupTagBuilder add(ConfigurationGroupTag configurationGroupTag)  {
		return new AddConfigurationGroupTagBuilder(configurationGroupTag);
	}
	
	public static class DeleteConfigurationGroupTagBuilder extends RequestBuilder<Boolean, String, DeleteConfigurationGroupTagBuilder> {
		
		public DeleteConfigurationGroupTagBuilder(String tag) {
			super(Boolean.class, "configurationgrouptag", "delete");
			params.add("tag", tag);
		}
		
		public void tag(String multirequestToken) {
			params.add("tag", multirequestToken);
		}
	}

	/**
	 * Remove a tag association from configuration group
	 * 
	 * @param tag Tag
	 */
    public static DeleteConfigurationGroupTagBuilder delete(String tag)  {
		return new DeleteConfigurationGroupTagBuilder(tag);
	}
	
	public static class GetConfigurationGroupTagBuilder extends RequestBuilder<ConfigurationGroupTag, ConfigurationGroupTag.Tokenizer, GetConfigurationGroupTagBuilder> {
		
		public GetConfigurationGroupTagBuilder(String tag) {
			super(ConfigurationGroupTag.class, "configurationgrouptag", "get");
			params.add("tag", tag);
		}
		
		public void tag(String multirequestToken) {
			params.add("tag", multirequestToken);
		}
	}

	/**
	 * Return the configuration group the tag is associated to
	 * 
	 * @param tag Tag
	 */
    public static GetConfigurationGroupTagBuilder get(String tag)  {
		return new GetConfigurationGroupTagBuilder(tag);
	}
	
	public static class ListConfigurationGroupTagBuilder extends ListResponseRequestBuilder<ConfigurationGroupTag, ConfigurationGroupTag.Tokenizer, ListConfigurationGroupTagBuilder> {
		
		public ListConfigurationGroupTagBuilder(ConfigurationGroupTagFilter filter) {
			super(ConfigurationGroupTag.class, "configurationgrouptag", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Return list of tags for a configuration group
	 * 
	 * @param filter Filter option for configuration group identifier
	 */
    public static ListConfigurationGroupTagBuilder list(ConfigurationGroupTagFilter filter)  {
		return new ListConfigurationGroupTagBuilder(filter);
	}
}
