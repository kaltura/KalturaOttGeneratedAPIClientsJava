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

import com.kaltura.client.enums.ReminderType;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Reminder;
import com.kaltura.client.types.ReminderFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ReminderService {
	
	public static class AddReminderBuilder extends RequestBuilder<Reminder, Reminder.Tokenizer, AddReminderBuilder> {
		
		public AddReminderBuilder(Reminder reminder) {
			super(Reminder.class, "reminder", "add");
			params.add("reminder", reminder);
		}
	}

	/**
	 * Add a new future reminder
	 * 
	 * @param reminder The reminder to be added.
	 */
    public static AddReminderBuilder add(Reminder reminder)  {
		return new AddReminderBuilder(reminder);
	}
	
	public static class DeleteReminderBuilder extends RequestBuilder<Boolean, String, DeleteReminderBuilder> {
		
		public DeleteReminderBuilder(long id, ReminderType type) {
			super(Boolean.class, "reminder", "delete");
			params.add("id", id);
			params.add("type", type);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
	}

	/**
	 * Delete a reminder. Reminder cannot be delete while being sent.
	 * 
	 * @param id Id of the reminder.
	 * @param type Reminder type.
	 */
    public static DeleteReminderBuilder delete(long id, ReminderType type)  {
		return new DeleteReminderBuilder(id, type);
	}
	
	public static class DeleteWithTokenReminderBuilder extends NullRequestBuilder {
		
		public DeleteWithTokenReminderBuilder(long id, ReminderType type, String token, int partnerId) {
			super("reminder", "deleteWithToken");
			params.add("id", id);
			params.add("type", type);
			params.add("token", token);
			params.add("partnerId", partnerId);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
	}

	/**
	 * Delete a reminder. Reminder cannot be delete while being sent.
	 * 
	 * @param id Id of the reminder.
	 * @param type Reminder type.
	 * @param token User's token identifier
	 * @param partnerId Partner identifier
	 */
    public static DeleteWithTokenReminderBuilder deleteWithToken(long id, ReminderType type, String token, int partnerId)  {
		return new DeleteWithTokenReminderBuilder(id, type, token, partnerId);
	}
	
	public static class ListReminderBuilder extends ListResponseRequestBuilder<Reminder, Reminder.Tokenizer, ListReminderBuilder> {
		
		public ListReminderBuilder(ReminderFilter filter, FilterPager pager) {
			super(Reminder.class, "reminder", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListReminderBuilder list(ReminderFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Return a list of reminders with optional filter by KSQL.
	 * 
	 * @param filter Filter object
	 * @param pager Paging the request
	 */
    public static ListReminderBuilder list(ReminderFilter filter, FilterPager pager)  {
		return new ListReminderBuilder(filter, pager);
	}
}
