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
// Copyright (C) 2006-2021  Kaltura Inc.
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

import com.kaltura.client.types.SmsAdapterProfile;
import com.kaltura.client.types.SmsAdapterProfileFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SmsAdapterProfileService {
	
	public static class AddSmsAdapterProfileBuilder extends RequestBuilder<SmsAdapterProfile, SmsAdapterProfile.Tokenizer, AddSmsAdapterProfileBuilder> {
		
		public AddSmsAdapterProfileBuilder(SmsAdapterProfile objectToAdd) {
			super(SmsAdapterProfile.class, "smsadapterprofile", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add an object
	 * 
	 * @param objectToAdd Object to add
	 */
    public static AddSmsAdapterProfileBuilder add(SmsAdapterProfile objectToAdd)  {
		return new AddSmsAdapterProfileBuilder(objectToAdd);
	}
	
	public static class UpdateSmsAdapterProfileBuilder extends RequestBuilder<SmsAdapterProfile, SmsAdapterProfile.Tokenizer, UpdateSmsAdapterProfileBuilder> {
		
		public UpdateSmsAdapterProfileBuilder(long id, SmsAdapterProfile objectToUpdate) {
			super(SmsAdapterProfile.class, "smsadapterprofile", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an object
	 * 
	 * @param id Object ID to update
	 * @param objectToUpdate Object to update
	 */
    public static UpdateSmsAdapterProfileBuilder update(long id, SmsAdapterProfile objectToUpdate)  {
		return new UpdateSmsAdapterProfileBuilder(id, objectToUpdate);
	}
	
	public static class GetSmsAdapterProfileBuilder extends RequestBuilder<SmsAdapterProfile, SmsAdapterProfile.Tokenizer, GetSmsAdapterProfileBuilder> {
		
		public GetSmsAdapterProfileBuilder(long id) {
			super(SmsAdapterProfile.class, "smsadapterprofile", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get an object
	 * 
	 * @param id Object ID to get
	 */
    public static GetSmsAdapterProfileBuilder get(long id)  {
		return new GetSmsAdapterProfileBuilder(id);
	}
	
	public static class ListSmsAdapterProfileBuilder extends ListResponseRequestBuilder<SmsAdapterProfile, SmsAdapterProfile.Tokenizer, ListSmsAdapterProfileBuilder> {
		
		public ListSmsAdapterProfileBuilder(SmsAdapterProfileFilter filter) {
			super(SmsAdapterProfile.class, "smsadapterprofile", "list");
			params.add("filter", filter);
		}
	}

    public static ListSmsAdapterProfileBuilder list(SmsAdapterProfileFilter filter)  {
		return new ListSmsAdapterProfileBuilder(filter);
	}
	
	public static class DeleteSmsAdapterProfileBuilder extends NullRequestBuilder {
		
		public DeleteSmsAdapterProfileBuilder(long id) {
			super("smsadapterprofile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an object
	 * 
	 * @param id Object ID to delete
	 */
    public static DeleteSmsAdapterProfileBuilder delete(long id)  {
		return new DeleteSmsAdapterProfileBuilder(id);
	}
	
	public static class GenerateSharedSecretSmsAdapterProfileBuilder extends RequestBuilder<SmsAdapterProfile, SmsAdapterProfile.Tokenizer, GenerateSharedSecretSmsAdapterProfileBuilder> {
		
		public GenerateSharedSecretSmsAdapterProfileBuilder(int smsAdapterId) {
			super(SmsAdapterProfile.class, "smsadapterprofile", "generateSharedSecret");
			params.add("smsAdapterId", smsAdapterId);
		}
		
		public void smsAdapterId(String multirequestToken) {
			params.add("smsAdapterId", multirequestToken);
		}
	}

	/**
	 * Generate Sms Adapter shared secret
	 * 
	 * @param smsAdapterId Sms Adapter identifier
	 */
    public static GenerateSharedSecretSmsAdapterProfileBuilder generateSharedSecret(int smsAdapterId)  {
		return new GenerateSharedSecretSmsAdapterProfileBuilder(smsAdapterId);
	}
}
