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

import com.kaltura.client.enums.MessageTemplateType;
import com.kaltura.client.types.MessageTemplate;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class MessageTemplateService {
	
	public static class GetMessageTemplateBuilder extends RequestBuilder<MessageTemplate, MessageTemplate.Tokenizer, GetMessageTemplateBuilder> {
		
		public GetMessageTemplateBuilder(MessageTemplateType messageType) {
			super(MessageTemplate.class, "messagetemplate", "get");
			params.add("messageType", messageType);
		}
		
		public void messageType(String multirequestToken) {
			params.add("messageType", multirequestToken);
		}
	}

	/**
	 * Retrieve a message template used in push notifications and inbox
	 * 
	 * @param messageType possible values: Asset type – Series, Reminder,Churn
	 */
    public static GetMessageTemplateBuilder get(MessageTemplateType messageType)  {
		return new GetMessageTemplateBuilder(messageType);
	}
	
	public static class UpdateMessageTemplateBuilder extends RequestBuilder<MessageTemplate, MessageTemplate.Tokenizer, UpdateMessageTemplateBuilder> {
		
		public UpdateMessageTemplateBuilder(MessageTemplateType messageType, MessageTemplate template) {
			super(MessageTemplate.class, "messagetemplate", "update");
			params.add("messageType", messageType);
			params.add("template", template);
		}
		
		public void messageType(String multirequestToken) {
			params.add("messageType", multirequestToken);
		}
	}

	/**
	 * Set the account’s push notifications and inbox messages templates
	 * 
	 * @param messageType The asset type to update its template
	 * @param template The actual message with placeholders to be presented to the user
	 */
    public static UpdateMessageTemplateBuilder update(MessageTemplateType messageType, MessageTemplate template)  {
		return new UpdateMessageTemplateBuilder(messageType, template);
	}
}
