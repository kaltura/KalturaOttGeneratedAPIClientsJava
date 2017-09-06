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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.enums.OTTAssetType;
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
		
		public GetMessageTemplateBuilder(OTTAssetType assetType) {
			super(MessageTemplate.class, "messagetemplate", "get");
			params.add("assetType", assetType);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
	}

	/**  Retrieve a message template used in push notifications and inbox  */
    public static GetMessageTemplateBuilder get(OTTAssetType assetType)  {
		return new GetMessageTemplateBuilder(assetType);
	}
	
	public static class UpdateMessageTemplateBuilder extends RequestBuilder<MessageTemplate, MessageTemplate.Tokenizer, UpdateMessageTemplateBuilder> {
		
		public UpdateMessageTemplateBuilder(OTTAssetType assetType, MessageTemplate template) {
			super(MessageTemplate.class, "messagetemplate", "update");
			params.add("assetType", assetType);
			params.add("template", template);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
	}

	/**  Set the account’s push notifications and inbox messages templates  */
    public static UpdateMessageTemplateBuilder update(OTTAssetType assetType, MessageTemplate template)  {
		return new UpdateMessageTemplateBuilder(assetType, template);
	}
}
