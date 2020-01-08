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
// Copyright (C) 2006-2020  Kaltura Inc.
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

import com.kaltura.client.types.TopicNotification;
import com.kaltura.client.types.TopicNotificationFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TopicNotificationService {
	
	public static class AddTopicNotificationBuilder extends RequestBuilder<TopicNotification, TopicNotification.Tokenizer, AddTopicNotificationBuilder> {
		
		public AddTopicNotificationBuilder(TopicNotification topicNotification) {
			super(TopicNotification.class, "topicnotification", "add");
			params.add("topicNotification", topicNotification);
		}
	}

	/**
	 * Add a new topic notification
	 * 
	 * @param topicNotification The topic notification to add
	 */
    public static AddTopicNotificationBuilder add(TopicNotification topicNotification)  {
		return new AddTopicNotificationBuilder(topicNotification);
	}
	
	public static class DeleteTopicNotificationBuilder extends NullRequestBuilder {
		
		public DeleteTopicNotificationBuilder(long id) {
			super("topicnotification", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing topic notification
	 * 
	 * @param id ID of topic notification to delete
	 */
    public static DeleteTopicNotificationBuilder delete(long id)  {
		return new DeleteTopicNotificationBuilder(id);
	}
	
	public static class ListTopicNotificationBuilder extends ListResponseRequestBuilder<TopicNotification, TopicNotification.Tokenizer, ListTopicNotificationBuilder> {
		
		public ListTopicNotificationBuilder(TopicNotificationFilter filter) {
			super(TopicNotification.class, "topicnotification", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Lists all topic notifications in the system.
	 * 
	 * @param filter Filter options
	 */
    public static ListTopicNotificationBuilder list(TopicNotificationFilter filter)  {
		return new ListTopicNotificationBuilder(filter);
	}
	
	public static class SubscribeTopicNotificationBuilder extends NullRequestBuilder {
		
		public SubscribeTopicNotificationBuilder(long topicNotificationId) {
			super("topicnotification", "subscribe");
			params.add("topicNotificationId", topicNotificationId);
		}
		
		public void topicNotificationId(String multirequestToken) {
			params.add("topicNotificationId", multirequestToken);
		}
	}

	/**
	 * Subscribe a user to a topic notification
	 * 
	 * @param topicNotificationId ID of topic notification to subscribe to.
	 */
    public static SubscribeTopicNotificationBuilder subscribe(long topicNotificationId)  {
		return new SubscribeTopicNotificationBuilder(topicNotificationId);
	}
	
	public static class UnsubscribeTopicNotificationBuilder extends NullRequestBuilder {
		
		public UnsubscribeTopicNotificationBuilder(long topicNotificationId) {
			super("topicnotification", "unsubscribe");
			params.add("topicNotificationId", topicNotificationId);
		}
		
		public void topicNotificationId(String multirequestToken) {
			params.add("topicNotificationId", multirequestToken);
		}
	}

	/**
	 * Unubscribe a user from a topic notification
	 * 
	 * @param topicNotificationId ID of topic notification to unsubscribe from.
	 */
    public static UnsubscribeTopicNotificationBuilder unsubscribe(long topicNotificationId)  {
		return new UnsubscribeTopicNotificationBuilder(topicNotificationId);
	}
	
	public static class UpdateTopicNotificationBuilder extends RequestBuilder<TopicNotification, TopicNotification.Tokenizer, UpdateTopicNotificationBuilder> {
		
		public UpdateTopicNotificationBuilder(int id, TopicNotification topicNotification) {
			super(TopicNotification.class, "topicnotification", "update");
			params.add("id", id);
			params.add("topicNotification", topicNotification);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing topic notification
	 * 
	 * @param id The topic notification ID to update
	 * @param topicNotification The topic notification to update
	 */
    public static UpdateTopicNotificationBuilder update(int id, TopicNotification topicNotification)  {
		return new UpdateTopicNotificationBuilder(id, topicNotification);
	}
}
