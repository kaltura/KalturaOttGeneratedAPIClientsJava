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

import com.kaltura.client.types.ExternalChannelProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ExternalChannelProfileService {
	
	public static class AddExternalChannelProfileBuilder extends RequestBuilder<ExternalChannelProfile, ExternalChannelProfile.Tokenizer, AddExternalChannelProfileBuilder> {
		
		public AddExternalChannelProfileBuilder(ExternalChannelProfile externalChannel) {
			super(ExternalChannelProfile.class, "externalchannelprofile", "add");
			params.add("externalChannel", externalChannel);
		}
	}

	/**
	 * Insert new External channel for partner
	 * 
	 * @param externalChannel External channel Object
	 */
    public static AddExternalChannelProfileBuilder add(ExternalChannelProfile externalChannel)  {
		return new AddExternalChannelProfileBuilder(externalChannel);
	}
	
	public static class DeleteExternalChannelProfileBuilder extends RequestBuilder<Boolean, String, DeleteExternalChannelProfileBuilder> {
		
		public DeleteExternalChannelProfileBuilder(int externalChannelId) {
			super(Boolean.class, "externalchannelprofile", "delete");
			params.add("externalChannelId", externalChannelId);
		}
		
		public void externalChannelId(String multirequestToken) {
			params.add("externalChannelId", multirequestToken);
		}
	}

	/**
	 * Delete External channel by External channel id
	 * 
	 * @param externalChannelId External channel identifier
	 */
    public static DeleteExternalChannelProfileBuilder delete(int externalChannelId)  {
		return new DeleteExternalChannelProfileBuilder(externalChannelId);
	}
	
	public static class ListExternalChannelProfileBuilder extends ListResponseRequestBuilder<ExternalChannelProfile, ExternalChannelProfile.Tokenizer, ListExternalChannelProfileBuilder> {
		
		public ListExternalChannelProfileBuilder() {
			super(ExternalChannelProfile.class, "externalchannelprofile", "list");
		}
	}

	/**
	 * Returns all External channels for partner
	 */
    public static ListExternalChannelProfileBuilder list()  {
		return new ListExternalChannelProfileBuilder();
	}
	
	public static class UpdateExternalChannelProfileBuilder extends RequestBuilder<ExternalChannelProfile, ExternalChannelProfile.Tokenizer, UpdateExternalChannelProfileBuilder> {
		
		public UpdateExternalChannelProfileBuilder(int externalChannelId, ExternalChannelProfile externalChannel) {
			super(ExternalChannelProfile.class, "externalchannelprofile", "update");
			params.add("externalChannelId", externalChannelId);
			params.add("externalChannel", externalChannel);
		}
		
		public void externalChannelId(String multirequestToken) {
			params.add("externalChannelId", multirequestToken);
		}
	}

	/**
	 * Update External channel details
	 * 
	 * @param externalChannelId External channel identifier
	 * @param externalChannel External channel Object
	 */
    public static UpdateExternalChannelProfileBuilder update(int externalChannelId, ExternalChannelProfile externalChannel)  {
		return new UpdateExternalChannelProfileBuilder(externalChannelId, externalChannel);
	}
}
