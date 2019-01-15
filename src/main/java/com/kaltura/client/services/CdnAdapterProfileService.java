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

import com.kaltura.client.types.CDNAdapterProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CdnAdapterProfileService {
	
	public static class AddCdnAdapterProfileBuilder extends RequestBuilder<CDNAdapterProfile, CDNAdapterProfile.Tokenizer, AddCdnAdapterProfileBuilder> {
		
		public AddCdnAdapterProfileBuilder(CDNAdapterProfile adapter) {
			super(CDNAdapterProfile.class, "cdnadapterprofile", "add");
			params.add("adapter", adapter);
		}
	}

	/**
	 * Insert new CDN adapter for partner
	 * 
	 * @param adapter CDN adapter object
	 */
    public static AddCdnAdapterProfileBuilder add(CDNAdapterProfile adapter)  {
		return new AddCdnAdapterProfileBuilder(adapter);
	}
	
	public static class DeleteCdnAdapterProfileBuilder extends RequestBuilder<Boolean, String, DeleteCdnAdapterProfileBuilder> {
		
		public DeleteCdnAdapterProfileBuilder(int adapterId) {
			super(Boolean.class, "cdnadapterprofile", "delete");
			params.add("adapterId", adapterId);
		}
		
		public void adapterId(String multirequestToken) {
			params.add("adapterId", multirequestToken);
		}
	}

	/**
	 * Delete CDN adapter by CDN adapter id
	 * 
	 * @param adapterId CDN adapter identifier
	 */
    public static DeleteCdnAdapterProfileBuilder delete(int adapterId)  {
		return new DeleteCdnAdapterProfileBuilder(adapterId);
	}
	
	public static class GenerateSharedSecretCdnAdapterProfileBuilder extends RequestBuilder<CDNAdapterProfile, CDNAdapterProfile.Tokenizer, GenerateSharedSecretCdnAdapterProfileBuilder> {
		
		public GenerateSharedSecretCdnAdapterProfileBuilder(int adapterId) {
			super(CDNAdapterProfile.class, "cdnadapterprofile", "generateSharedSecret");
			params.add("adapterId", adapterId);
		}
		
		public void adapterId(String multirequestToken) {
			params.add("adapterId", multirequestToken);
		}
	}

	/**
	 * Generate CDN adapter shared secret
	 * 
	 * @param adapterId CDN adapter identifier
	 */
    public static GenerateSharedSecretCdnAdapterProfileBuilder generateSharedSecret(int adapterId)  {
		return new GenerateSharedSecretCdnAdapterProfileBuilder(adapterId);
	}
	
	public static class ListCdnAdapterProfileBuilder extends ListResponseRequestBuilder<CDNAdapterProfile, CDNAdapterProfile.Tokenizer, ListCdnAdapterProfileBuilder> {
		
		public ListCdnAdapterProfileBuilder() {
			super(CDNAdapterProfile.class, "cdnadapterprofile", "list");
		}
	}

	/**
	 * Returns all CDN adapters for partner
	 */
    public static ListCdnAdapterProfileBuilder list()  {
		return new ListCdnAdapterProfileBuilder();
	}
	
	public static class UpdateCdnAdapterProfileBuilder extends RequestBuilder<CDNAdapterProfile, CDNAdapterProfile.Tokenizer, UpdateCdnAdapterProfileBuilder> {
		
		public UpdateCdnAdapterProfileBuilder(int adapterId, CDNAdapterProfile adapter) {
			super(CDNAdapterProfile.class, "cdnadapterprofile", "update");
			params.add("adapterId", adapterId);
			params.add("adapter", adapter);
		}
		
		public void adapterId(String multirequestToken) {
			params.add("adapterId", multirequestToken);
		}
	}

	/**
	 * Update CDN adapter details
	 * 
	 * @param adapterId CDN adapter id to update
	 * @param adapter CDN adapter Object
	 */
    public static UpdateCdnAdapterProfileBuilder update(int adapterId, CDNAdapterProfile adapter)  {
		return new UpdateCdnAdapterProfileBuilder(adapterId, adapter);
	}
}
