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

import com.kaltura.client.enums.ObjectState;
import com.kaltura.client.types.Campaign;
import com.kaltura.client.types.CampaignFilter;
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

public class CampaignService {
	
	public static class AddCampaignBuilder extends RequestBuilder<Campaign, Campaign.Tokenizer, AddCampaignBuilder> {
		
		public AddCampaignBuilder(Campaign objectToAdd) {
			super(Campaign.class, "campaign", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add an object
	 * 
	 * @param objectToAdd Object to add
	 */
    public static AddCampaignBuilder add(Campaign objectToAdd)  {
		return new AddCampaignBuilder(objectToAdd);
	}
	
	public static class UpdateCampaignBuilder extends RequestBuilder<Campaign, Campaign.Tokenizer, UpdateCampaignBuilder> {
		
		public UpdateCampaignBuilder(long id, Campaign objectToUpdate) {
			super(Campaign.class, "campaign", "update");
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
    public static UpdateCampaignBuilder update(long id, Campaign objectToUpdate)  {
		return new UpdateCampaignBuilder(id, objectToUpdate);
	}
	
	public static class DeleteCampaignBuilder extends NullRequestBuilder {
		
		public DeleteCampaignBuilder(long id) {
			super("campaign", "delete");
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
    public static DeleteCampaignBuilder delete(long id)  {
		return new DeleteCampaignBuilder(id);
	}
	
	public static class ListCampaignBuilder extends ListResponseRequestBuilder<Campaign, Campaign.Tokenizer, ListCampaignBuilder> {
		
		public ListCampaignBuilder(CampaignFilter filter, FilterPager pager) {
			super(Campaign.class, "campaign", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListCampaignBuilder list(CampaignFilter filter)  {
		return list(filter, null);
	}

    public static ListCampaignBuilder list(CampaignFilter filter, FilterPager pager)  {
		return new ListCampaignBuilder(filter, pager);
	}
	
	public static class SetStateCampaignBuilder extends NullRequestBuilder {
		
		public SetStateCampaignBuilder(long campaignId, ObjectState newState) {
			super("campaign", "setState");
			params.add("campaignId", campaignId);
			params.add("newState", newState);
		}
		
		public void campaignId(String multirequestToken) {
			params.add("campaignId", multirequestToken);
		}
		
		public void newState(String multirequestToken) {
			params.add("newState", multirequestToken);
		}
	}

	/**
	 * Set campaign&amp;#39;s state
	 * 
	 * @param campaignId campaign Id
	 * @param newState new campaign state
	 */
    public static SetStateCampaignBuilder setState(long campaignId, ObjectState newState)  {
		return new SetStateCampaignBuilder(campaignId, newState);
	}
}
