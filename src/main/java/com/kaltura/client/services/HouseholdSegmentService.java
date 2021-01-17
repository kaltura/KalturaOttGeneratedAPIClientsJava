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

import com.kaltura.client.types.HouseholdSegment;
import com.kaltura.client.types.HouseholdSegmentFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdSegmentService {
	
	public static class AddHouseholdSegmentBuilder extends RequestBuilder<HouseholdSegment, HouseholdSegment.Tokenizer, AddHouseholdSegmentBuilder> {
		
		public AddHouseholdSegmentBuilder(HouseholdSegment objectToAdd) {
			super(HouseholdSegment.class, "householdsegment", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * householdSegment add
	 * 
	 * @param objectToAdd householdSegment details
	 */
    public static AddHouseholdSegmentBuilder add(HouseholdSegment objectToAdd)  {
		return new AddHouseholdSegmentBuilder(objectToAdd);
	}
	
	public static class DeleteHouseholdSegmentBuilder extends NullRequestBuilder {
		
		public DeleteHouseholdSegmentBuilder(long id) {
			super("householdsegment", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Remove segment from household
	 * 
	 * @param id Segment identifier
	 */
    public static DeleteHouseholdSegmentBuilder delete(long id)  {
		return new DeleteHouseholdSegmentBuilder(id);
	}
	
	public static class ListHouseholdSegmentBuilder extends ListResponseRequestBuilder<HouseholdSegment, HouseholdSegment.Tokenizer, ListHouseholdSegmentBuilder> {
		
		public ListHouseholdSegmentBuilder(HouseholdSegmentFilter filter) {
			super(HouseholdSegment.class, "householdsegment", "list");
			params.add("filter", filter);
		}
	}

	public static ListHouseholdSegmentBuilder list()  {
		return list(null);
	}

	/**
	 * Gets all HouseholdSegment items for a household
	 * 
	 * @param filter Request filter
	 */
    public static ListHouseholdSegmentBuilder list(HouseholdSegmentFilter filter)  {
		return new ListHouseholdSegmentBuilder(filter);
	}
}
