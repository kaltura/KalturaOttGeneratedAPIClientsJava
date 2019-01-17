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

import com.kaltura.client.types.SSOAdapterProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SsoAdapterProfileService {
	
	public static class AddSsoAdapterProfileBuilder extends RequestBuilder<SSOAdapterProfile, SSOAdapterProfile.Tokenizer, AddSsoAdapterProfileBuilder> {
		
		public AddSsoAdapterProfileBuilder(SSOAdapterProfile ssoAdapter) {
			super(SSOAdapterProfile.class, "ssoadapterprofile", "add");
			params.add("ssoAdapter", ssoAdapter);
		}
	}

	/**
	 * Insert new sso adapter for partner
	 * 
	 * @param ssoAdapter SSO Adapter Object to be added
	 */
    public static AddSsoAdapterProfileBuilder add(SSOAdapterProfile ssoAdapter)  {
		return new AddSsoAdapterProfileBuilder(ssoAdapter);
	}
	
	public static class DeleteSsoAdapterProfileBuilder extends RequestBuilder<Boolean, String, DeleteSsoAdapterProfileBuilder> {
		
		public DeleteSsoAdapterProfileBuilder(int ssoAdapterId) {
			super(Boolean.class, "ssoadapterprofile", "delete");
			params.add("ssoAdapterId", ssoAdapterId);
		}
		
		public void ssoAdapterId(String multirequestToken) {
			params.add("ssoAdapterId", multirequestToken);
		}
	}

	/**
	 * Delete sso adapters by sso adapters id
	 * 
	 * @param ssoAdapterId SSO Adapter Identifier
	 */
    public static DeleteSsoAdapterProfileBuilder delete(int ssoAdapterId)  {
		return new DeleteSsoAdapterProfileBuilder(ssoAdapterId);
	}
	
	public static class GenerateSharedSecretSsoAdapterProfileBuilder extends RequestBuilder<SSOAdapterProfile, SSOAdapterProfile.Tokenizer, GenerateSharedSecretSsoAdapterProfileBuilder> {
		
		public GenerateSharedSecretSsoAdapterProfileBuilder(int ssoAdapterId) {
			super(SSOAdapterProfile.class, "ssoadapterprofile", "generateSharedSecret");
			params.add("ssoAdapterId", ssoAdapterId);
		}
		
		public void ssoAdapterId(String multirequestToken) {
			params.add("ssoAdapterId", multirequestToken);
		}
	}

	/**
	 * Generate SSO Adapter shared secret
	 * 
	 * @param ssoAdapterId SSO Adapter identifier
	 */
    public static GenerateSharedSecretSsoAdapterProfileBuilder generateSharedSecret(int ssoAdapterId)  {
		return new GenerateSharedSecretSsoAdapterProfileBuilder(ssoAdapterId);
	}
	
	public static class ListSsoAdapterProfileBuilder extends ListResponseRequestBuilder<SSOAdapterProfile, SSOAdapterProfile.Tokenizer, ListSsoAdapterProfileBuilder> {
		
		public ListSsoAdapterProfileBuilder() {
			super(SSOAdapterProfile.class, "ssoadapterprofile", "list");
		}
	}

	/**
	 * Returns all sso adapters for partner : id + name
	 */
    public static ListSsoAdapterProfileBuilder list()  {
		return new ListSsoAdapterProfileBuilder();
	}
	
	public static class UpdateSsoAdapterProfileBuilder extends RequestBuilder<SSOAdapterProfile, SSOAdapterProfile.Tokenizer, UpdateSsoAdapterProfileBuilder> {
		
		public UpdateSsoAdapterProfileBuilder(int ssoAdapterId, SSOAdapterProfile ssoAdapter) {
			super(SSOAdapterProfile.class, "ssoadapterprofile", "update");
			params.add("ssoAdapterId", ssoAdapterId);
			params.add("ssoAdapter", ssoAdapter);
		}
		
		public void ssoAdapterId(String multirequestToken) {
			params.add("ssoAdapterId", multirequestToken);
		}
	}

	/**
	 * Update sso adapter details
	 * 
	 * @param ssoAdapterId SSO Adapter Identifier
	 * @param ssoAdapter SSO Adapter Object
	 */
    public static UpdateSsoAdapterProfileBuilder update(int ssoAdapterId, SSOAdapterProfile ssoAdapter)  {
		return new UpdateSsoAdapterProfileBuilder(ssoAdapterId, ssoAdapter);
	}
}
