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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.GeoBlockRule;
import com.kaltura.client.types.GeoBlockRuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class GeoBlockRuleService {
	
	public static class AddGeoBlockRuleBuilder extends RequestBuilder<GeoBlockRule, GeoBlockRule.Tokenizer, AddGeoBlockRuleBuilder> {
		
		public AddGeoBlockRuleBuilder(GeoBlockRule geoBlockRule) {
			super(GeoBlockRule.class, "geoblockrule", "add");
			params.add("geoBlockRule", geoBlockRule);
		}
	}

	/**
	 * Add a new geo block rule
	 * 
	 * @param geoBlockRule The geo block rule to add
	 */
    public static AddGeoBlockRuleBuilder add(GeoBlockRule geoBlockRule)  {
		return new AddGeoBlockRuleBuilder(geoBlockRule);
	}
	
	public static class DeleteGeoBlockRuleBuilder extends RequestBuilder<Boolean, String, DeleteGeoBlockRuleBuilder> {
		
		public DeleteGeoBlockRuleBuilder(long id) {
			super(Boolean.class, "geoblockrule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a geo block rule
	 * 
	 * @param id The id of the geo block rule to delete
	 */
    public static DeleteGeoBlockRuleBuilder delete(long id)  {
		return new DeleteGeoBlockRuleBuilder(id);
	}
	
	public static class ListGeoBlockRuleBuilder extends ListResponseRequestBuilder<GeoBlockRule, GeoBlockRule.Tokenizer, ListGeoBlockRuleBuilder> {
		
		public ListGeoBlockRuleBuilder(GeoBlockRuleFilter filter, FilterPager pager) {
			super(GeoBlockRule.class, "geoblockrule", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListGeoBlockRuleBuilder list()  {
		return list(null);
	}

	public static ListGeoBlockRuleBuilder list(GeoBlockRuleFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get the list of geo block rules for the partner
	 * 
	 * @param filter Filter criteria for the geo block rules
	 * @param pager Paging information for retrieving paginated results
	 */
    public static ListGeoBlockRuleBuilder list(GeoBlockRuleFilter filter, FilterPager pager)  {
		return new ListGeoBlockRuleBuilder(filter, pager);
	}
	
	public static class UpdateGeoBlockRuleBuilder extends RequestBuilder<GeoBlockRule, GeoBlockRule.Tokenizer, UpdateGeoBlockRuleBuilder> {
		
		public UpdateGeoBlockRuleBuilder(long id, GeoBlockRule geoBlockRule) {
			super(GeoBlockRule.class, "geoblockrule", "update");
			params.add("id", id);
			params.add("geoBlockRule", geoBlockRule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing geo block rule
	 * 
	 * @param id The id of the geo block rule to update
	 * @param geoBlockRule The geo block rule data to update
	 */
    public static UpdateGeoBlockRuleBuilder update(long id, GeoBlockRule geoBlockRule)  {
		return new UpdateGeoBlockRuleBuilder(id, geoBlockRule);
	}
}
