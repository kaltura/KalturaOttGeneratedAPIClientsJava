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

import com.kaltura.client.enums.InboxMessageStatus;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.InboxMessage;
import com.kaltura.client.types.InboxMessageFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class InboxMessageService {
	
	public static class GetInboxMessageBuilder extends RequestBuilder<InboxMessage, InboxMessage.Tokenizer, GetInboxMessageBuilder> {
		
		public GetInboxMessageBuilder(String id) {
			super(InboxMessage.class, "inboxmessage", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * TBD
	 * 
	 * @param id message id
	 */
    public static GetInboxMessageBuilder get(String id)  {
		return new GetInboxMessageBuilder(id);
	}
	
	public static class ListInboxMessageBuilder extends ListResponseRequestBuilder<InboxMessage, InboxMessage.Tokenizer, ListInboxMessageBuilder> {
		
		public ListInboxMessageBuilder(InboxMessageFilter filter, FilterPager pager) {
			super(InboxMessage.class, "inboxmessage", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListInboxMessageBuilder list()  {
		return list(null);
	}

	public static ListInboxMessageBuilder list(InboxMessageFilter filter)  {
		return list(filter, null);
	}

	/**
	 * List inbox messages
	 * 
	 * @param filter filter
	 * @param pager Page size and index
	 */
    public static ListInboxMessageBuilder list(InboxMessageFilter filter, FilterPager pager)  {
		return new ListInboxMessageBuilder(filter, pager);
	}
	
	public static class UpdateStatusInboxMessageBuilder extends RequestBuilder<Boolean, String, UpdateStatusInboxMessageBuilder> {
		
		public UpdateStatusInboxMessageBuilder(String id, InboxMessageStatus status) {
			super(Boolean.class, "inboxmessage", "updateStatus");
			params.add("id", id);
			params.add("status", status);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void status(String multirequestToken) {
			params.add("status", multirequestToken);
		}
	}

	/**
	 * Updates the message status.
	 * 
	 * @param id Message identifier
	 * @param status Message status
	 */
    public static UpdateStatusInboxMessageBuilder updateStatus(String id, InboxMessageStatus status)  {
		return new UpdateStatusInboxMessageBuilder(id, status);
	}
}
