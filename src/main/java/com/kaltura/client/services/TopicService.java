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

import com.kaltura.client.enums.TopicAutomaticIssueNotification;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Topic;
import com.kaltura.client.types.TopicFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TopicService {
	
	public static class DeleteTopicBuilder extends RequestBuilder<Boolean, String, DeleteTopicBuilder> {
		
		public DeleteTopicBuilder(int id) {
			super(Boolean.class, "topic", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Deleted a topic
	 * 
	 * @param id Topic identifier
	 */
    public static DeleteTopicBuilder delete(int id)  {
		return new DeleteTopicBuilder(id);
	}
	
	public static class GetTopicBuilder extends RequestBuilder<Topic, Topic.Tokenizer, GetTopicBuilder> {
		
		public GetTopicBuilder(int id) {
			super(Topic.class, "topic", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Gets a topic
	 * 
	 * @param id Topic identifier
	 */
    public static GetTopicBuilder get(int id)  {
		return new GetTopicBuilder(id);
	}
	
	public static class ListTopicBuilder extends ListResponseRequestBuilder<Topic, Topic.Tokenizer, ListTopicBuilder> {
		
		public ListTopicBuilder(TopicFilter filter, FilterPager pager) {
			super(Topic.class, "topic", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListTopicBuilder list()  {
		return list(null);
	}

	public static ListTopicBuilder list(TopicFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get list of topics
	 * 
	 * @param filter Topics filter
	 * @param pager Page size and index
	 */
    public static ListTopicBuilder list(TopicFilter filter, FilterPager pager)  {
		return new ListTopicBuilder(filter, pager);
	}
	
	public static class UpdateStatusTopicBuilder extends RequestBuilder<Boolean, String, UpdateStatusTopicBuilder> {
		
		public UpdateStatusTopicBuilder(int id, TopicAutomaticIssueNotification automaticIssueNotification) {
			super(Boolean.class, "topic", "updateStatus");
			params.add("id", id);
			params.add("automaticIssueNotification", automaticIssueNotification);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void automaticIssueNotification(String multirequestToken) {
			params.add("automaticIssueNotification", multirequestToken);
		}
	}

	/**
	 * Updates a topic &amp;quot;automatic issue notification&amp;quot; behavior.
	 * 
	 * @param id Topic identifier
	 * @param automaticIssueNotification Behavior options:
	 *              Inherit = 0: Take value from partner notification settings
	 *              Yes = 1: Issue a notification massage when a new episode is
	 * available on the catalog
	 *              No = 2: Do send a notification message when a new episode is
	 * available on the catalog
	 */
    public static UpdateStatusTopicBuilder updateStatus(int id, TopicAutomaticIssueNotification automaticIssueNotification)  {
		return new UpdateStatusTopicBuilder(id, automaticIssueNotification);
	}
}
