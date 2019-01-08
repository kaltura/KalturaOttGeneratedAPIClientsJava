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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.PersonalFeed;
import com.kaltura.client.types.PersonalFeedFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PersonalFeedService {
	
	public static class ListPersonalFeedBuilder extends ListResponseRequestBuilder<PersonalFeed, PersonalFeed.Tokenizer, ListPersonalFeedBuilder> {
		
		public ListPersonalFeedBuilder(PersonalFeedFilter filter, FilterPager pager) {
			super(PersonalFeed.class, "personalfeed", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListPersonalFeedBuilder list(PersonalFeedFilter filter)  {
		return list(filter, null);
	}

	/**
	 * List user&amp;#39;s feeds.              Possible status codes:
	 * 
	 * @param filter Required sort option to apply for the identified assets. If omitted – will use
	 * relevancy.
	 *             Possible values: relevancy, a_to_z, z_to_a, views, ratings, votes,
	 * newest.
	 * @param pager Page size and index
	 */
    public static ListPersonalFeedBuilder list(PersonalFeedFilter filter, FilterPager pager)  {
		return new ListPersonalFeedBuilder(filter, pager);
	}
}
