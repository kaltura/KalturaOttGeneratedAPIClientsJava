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

import com.kaltura.client.types.Engagement;
import com.kaltura.client.types.EngagementFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EngagementService {
	
	public static class AddEngagementBuilder extends RequestBuilder<Engagement, Engagement.Tokenizer, AddEngagementBuilder> {
		
		public AddEngagementBuilder(Engagement engagement) {
			super(Engagement.class, "engagement", "add");
			params.add("engagement", engagement);
		}
	}

	/**
	 * Insert new Engagement for partner
	 * 
	 * @param engagement Engagement adapter Object
	 */
    public static AddEngagementBuilder add(Engagement engagement)  {
		return new AddEngagementBuilder(engagement);
	}
	
	public static class DeleteEngagementBuilder extends RequestBuilder<Boolean, String, DeleteEngagementBuilder> {
		
		public DeleteEngagementBuilder(int id) {
			super(Boolean.class, "engagement", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete engagement by engagement adapter id
	 * 
	 * @param id Engagement identifier
	 */
    public static DeleteEngagementBuilder delete(int id)  {
		return new DeleteEngagementBuilder(id);
	}
	
	public static class GetEngagementBuilder extends RequestBuilder<Engagement, Engagement.Tokenizer, GetEngagementBuilder> {
		
		public GetEngagementBuilder(int id) {
			super(Engagement.class, "engagement", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Return engagement
	 * 
	 * @param id Engagement identifier
	 */
    public static GetEngagementBuilder get(int id)  {
		return new GetEngagementBuilder(id);
	}
	
	public static class ListEngagementBuilder extends ListResponseRequestBuilder<Engagement, Engagement.Tokenizer, ListEngagementBuilder> {
		
		public ListEngagementBuilder(EngagementFilter filter) {
			super(Engagement.class, "engagement", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Returns all Engagement for partner
	 * 
	 * @param filter filter
	 */
    public static ListEngagementBuilder list(EngagementFilter filter)  {
		return new ListEngagementBuilder(filter);
	}
}
