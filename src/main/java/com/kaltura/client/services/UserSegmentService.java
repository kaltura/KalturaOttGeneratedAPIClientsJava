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
import com.kaltura.client.types.UserSegment;
import com.kaltura.client.types.UserSegmentFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UserSegmentService {
	
	public static class AddUserSegmentBuilder extends RequestBuilder<UserSegment, UserSegment.Tokenizer, AddUserSegmentBuilder> {
		
		public AddUserSegmentBuilder(UserSegment userSegment) {
			super(UserSegment.class, "usersegment", "add");
			params.add("userSegment", userSegment);
		}
	}

	/**
	 * Adds a segment to a user
	 * 
	 * @param userSegment User segment
	 */
    public static AddUserSegmentBuilder add(UserSegment userSegment)  {
		return new AddUserSegmentBuilder(userSegment);
	}
	
	public static class DeleteUserSegmentBuilder extends RequestBuilder<Boolean, String, DeleteUserSegmentBuilder> {
		
		public DeleteUserSegmentBuilder(String userId, long segmentId) {
			super(Boolean.class, "usersegment", "delete");
			params.add("userId", userId);
			params.add("segmentId", segmentId);
		}
		
		public void userId(String multirequestToken) {
			params.add("userId", multirequestToken);
		}
		
		public void segmentId(String multirequestToken) {
			params.add("segmentId", multirequestToken);
		}
	}

	/**
	 * Deletes a segment from a user
	 * 
	 * @param userId User id
	 * @param segmentId Segment id
	 */
    public static DeleteUserSegmentBuilder delete(String userId, long segmentId)  {
		return new DeleteUserSegmentBuilder(userId, segmentId);
	}
	
	public static class ListUserSegmentBuilder extends ListResponseRequestBuilder<UserSegment, UserSegment.Tokenizer, ListUserSegmentBuilder> {
		
		public ListUserSegmentBuilder(UserSegmentFilter filter, FilterPager pager) {
			super(UserSegment.class, "usersegment", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListUserSegmentBuilder list(UserSegmentFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Retrieve all the segments that apply for given user
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListUserSegmentBuilder list(UserSegmentFilter filter, FilterPager pager)  {
		return new ListUserSegmentBuilder(filter, pager);
	}
}
