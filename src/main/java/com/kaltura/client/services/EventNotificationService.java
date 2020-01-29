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
// Copyright (C) 2006-2019  Kaltura Inc.
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

import com.kaltura.client.types.EventNotification;
import com.kaltura.client.types.EventNotificationFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EventNotificationService {
	
	public static class UpdateEventNotificationBuilder extends RequestBuilder<EventNotification, EventNotification.Tokenizer, UpdateEventNotificationBuilder> {
		
		public UpdateEventNotificationBuilder(String id, EventNotification objectToUpdate) {
			super(EventNotification.class, "eventnotification", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * eventNotification update
	 * 
	 * @param id Object ID to update
	 * @param objectToUpdate eventNotification details
	 */
    public static UpdateEventNotificationBuilder update(String id, EventNotification objectToUpdate)  {
		return new UpdateEventNotificationBuilder(id, objectToUpdate);
	}
	
	public static class ListEventNotificationBuilder extends ListResponseRequestBuilder<EventNotification, EventNotification.Tokenizer, ListEventNotificationBuilder> {
		
		public ListEventNotificationBuilder(EventNotificationFilter filter) {
			super(EventNotification.class, "eventnotification", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Gets all EventNotification items for a given Object id and type
	 * 
	 * @param filter Request filter
	 */
    public static ListEventNotificationBuilder list(EventNotificationFilter filter)  {
		return new ListEventNotificationBuilder(filter);
	}
}
