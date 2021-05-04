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
// Copyright (C) 2006-2021  Kaltura Inc.
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

import com.kaltura.client.types.BaseRegionFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Region;
import com.kaltura.client.types.RegionChannelNumber;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class RegionService {
	
	public static class AddRegionBuilder extends RequestBuilder<Region, Region.Tokenizer, AddRegionBuilder> {
		
		public AddRegionBuilder(Region region) {
			super(Region.class, "region", "add");
			params.add("region", region);
		}
	}

	/**
	 * Adds a new region for partner
	 * 
	 * @param region Region to add
	 */
    public static AddRegionBuilder add(Region region)  {
		return new AddRegionBuilder(region);
	}
	
	public static class LinearchannelbulkaddRegionBuilder extends RequestBuilder<Boolean, String, LinearchannelbulkaddRegionBuilder> {
		
		public LinearchannelbulkaddRegionBuilder(long linearChannelId, List<RegionChannelNumber> regionChannelNumbers) {
			super(Boolean.class, "region", "linearchannelbulkadd");
			params.add("linearChannelId", linearChannelId);
			params.add("regionChannelNumbers", regionChannelNumbers);
		}
		
		public void linearChannelId(String multirequestToken) {
			params.add("linearChannelId", multirequestToken);
		}
	}

	/**
	 * Adds a linear channel to the list of regions.
	 * 
	 * @param linearChannelId The identifier of the linear channel
	 * @param regionChannelNumbers List of regions and number of linear channel in it.
	 */
    public static LinearchannelbulkaddRegionBuilder linearchannelbulkadd(long linearChannelId, List<RegionChannelNumber> regionChannelNumbers)  {
		return new LinearchannelbulkaddRegionBuilder(linearChannelId, regionChannelNumbers);
	}
	
	public static class LinearchannelbulkdeleteRegionBuilder extends RequestBuilder<Boolean, String, LinearchannelbulkdeleteRegionBuilder> {
		
		public LinearchannelbulkdeleteRegionBuilder(long linearChannelId, String regionIds) {
			super(Boolean.class, "region", "linearchannelbulkdelete");
			params.add("linearChannelId", linearChannelId);
			params.add("regionIds", regionIds);
		}
		
		public void linearChannelId(String multirequestToken) {
			params.add("linearChannelId", multirequestToken);
		}
		
		public void regionIds(String multirequestToken) {
			params.add("regionIds", multirequestToken);
		}
	}

	/**
	 * Deletes a linear channel from the list of regions.
	 * 
	 * @param linearChannelId The identifier of the linear channel
	 * @param regionIds List of identifiers of regions.
	 */
    public static LinearchannelbulkdeleteRegionBuilder linearchannelbulkdelete(long linearChannelId, String regionIds)  {
		return new LinearchannelbulkdeleteRegionBuilder(linearChannelId, regionIds);
	}
	
	public static class DeleteRegionBuilder extends NullRequestBuilder {
		
		public DeleteRegionBuilder(int id) {
			super("region", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing region
	 * 
	 * @param id Region ID to delete
	 */
    public static DeleteRegionBuilder delete(int id)  {
		return new DeleteRegionBuilder(id);
	}
	
	public static class ListRegionBuilder extends ListResponseRequestBuilder<Region, Region.Tokenizer, ListRegionBuilder> {
		
		public ListRegionBuilder(BaseRegionFilter filter, FilterPager pager) {
			super(Region.class, "region", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListRegionBuilder list(BaseRegionFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns all regions for the partner
	 * 
	 * @param filter Regions filter
	 * @param pager Paging the request
	 */
    public static ListRegionBuilder list(BaseRegionFilter filter, FilterPager pager)  {
		return new ListRegionBuilder(filter, pager);
	}
	
	public static class UpdateRegionBuilder extends RequestBuilder<Region, Region.Tokenizer, UpdateRegionBuilder> {
		
		public UpdateRegionBuilder(int id, Region region) {
			super(Region.class, "region", "update");
			params.add("id", id);
			params.add("region", region);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing region
	 * 
	 * @param id Region ID to update
	 * @param region Region to update
	 */
    public static UpdateRegionBuilder update(int id, Region region)  {
		return new UpdateRegionBuilder(id, region);
	}
}
