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
// Copyright (C) 2006-2022  Kaltura Inc.
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
import com.kaltura.client.types.TopicNotificationMessage;
import com.kaltura.client.types.TopicNotificationMessageFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TopicNotificationMessageService {
	
	public static class AddTopicNotificationMessageBuilder extends RequestBuilder<TopicNotificationMessage, TopicNotificationMessage.Tokenizer, AddTopicNotificationMessageBuilder> {
		
		public AddTopicNotificationMessageBuilder(TopicNotificationMessage topicNotificationMessage) {
			super(TopicNotificationMessage.class, "topicnotificationmessage", "add");
			params.add("topicNotificationMessage", topicNotificationMessage);
		}
	}

	/**
	 * Add a new topic notification message
	 * 
	 * @param topicNotificationMessage The topic notification message to add
	 */
    public static AddTopicNotificationMessageBuilder add(TopicNotificationMessage topicNotificationMessage)  {
		return new AddTopicNotificationMessageBuilder(topicNotificationMessage);
	}
	
	public static class DeleteTopicNotificationMessageBuilder extends NullRequestBuilder {
		
		public DeleteTopicNotificationMessageBuilder(long id) {
			super("topicnotificationmessage", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing topic notification message
	 * 
	 * @param id ID of topic notification message to delete
	 */
    public static DeleteTopicNotificationMessageBuilder delete(long id)  {
		return new DeleteTopicNotificationMessageBuilder(id);
	}
	
	public static class ListTopicNotificationMessageBuilder extends ListResponseRequestBuilder<TopicNotificationMessage, TopicNotificationMessage.Tokenizer, ListTopicNotificationMessageBuilder> {
		
		public ListTopicNotificationMessageBuilder(TopicNotificationMessageFilter filter, FilterPager pager) {
			super(TopicNotificationMessage.class, "topicnotificationmessage", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListTopicNotificationMessageBuilder list()  {
		return list(null);
	}

	public static ListTopicNotificationMessageBuilder list(TopicNotificationMessageFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Lists all topic notifications in the system.
	 * 
	 * @param filter Filter options
	 * @param pager Paging the request
	 */
    public static ListTopicNotificationMessageBuilder list(TopicNotificationMessageFilter filter, FilterPager pager)  {
		return new ListTopicNotificationMessageBuilder(filter, pager);
	}
	
	public static class UpdateTopicNotificationMessageBuilder extends RequestBuilder<TopicNotificationMessage, TopicNotificationMessage.Tokenizer, UpdateTopicNotificationMessageBuilder> {
		
		public UpdateTopicNotificationMessageBuilder(int id, TopicNotificationMessage topicNotificationMessage) {
			super(TopicNotificationMessage.class, "topicnotificationmessage", "update");
			params.add("id", id);
			params.add("topicNotificationMessage", topicNotificationMessage);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing topic notification message
	 * 
	 * @param id The topic notification message ID to update
	 * @param topicNotificationMessage The topic notification message to update
	 */
    public static UpdateTopicNotificationMessageBuilder update(int id, TopicNotificationMessage topicNotificationMessage)  {
		return new UpdateTopicNotificationMessageBuilder(id, topicNotificationMessage);
	}
}
