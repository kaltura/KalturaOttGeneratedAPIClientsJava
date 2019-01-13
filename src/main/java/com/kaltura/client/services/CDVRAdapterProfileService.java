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

import com.kaltura.client.types.CDVRAdapterProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CDVRAdapterProfileService {
	
	public static class AddCDVRAdapterProfileBuilder extends RequestBuilder<CDVRAdapterProfile, CDVRAdapterProfile.Tokenizer, AddCDVRAdapterProfileBuilder> {
		
		public AddCDVRAdapterProfileBuilder(CDVRAdapterProfile adapter) {
			super(CDVRAdapterProfile.class, "cdvradapterprofile", "add");
			params.add("adapter", adapter);
		}
	}

	/**
	 * Insert new C-DVR adapter for partner
	 * 
	 * @param adapter C-DVR adapter object
	 */
    public static AddCDVRAdapterProfileBuilder add(CDVRAdapterProfile adapter)  {
		return new AddCDVRAdapterProfileBuilder(adapter);
	}
	
	public static class DeleteCDVRAdapterProfileBuilder extends RequestBuilder<Boolean, String, DeleteCDVRAdapterProfileBuilder> {
		
		public DeleteCDVRAdapterProfileBuilder(int adapterId) {
			super(Boolean.class, "cdvradapterprofile", "delete");
			params.add("adapterId", adapterId);
		}
		
		public void adapterId(String multirequestToken) {
			params.add("adapterId", multirequestToken);
		}
	}

	/**
	 * Delete C-DVR adapter by C-DVR adapter id
	 * 
	 * @param adapterId C-DVR adapter identifier
	 */
    public static DeleteCDVRAdapterProfileBuilder delete(int adapterId)  {
		return new DeleteCDVRAdapterProfileBuilder(adapterId);
	}
	
	public static class GenerateSharedSecretCDVRAdapterProfileBuilder extends RequestBuilder<CDVRAdapterProfile, CDVRAdapterProfile.Tokenizer, GenerateSharedSecretCDVRAdapterProfileBuilder> {
		
		public GenerateSharedSecretCDVRAdapterProfileBuilder(int adapterId) {
			super(CDVRAdapterProfile.class, "cdvradapterprofile", "generateSharedSecret");
			params.add("adapterId", adapterId);
		}
		
		public void adapterId(String multirequestToken) {
			params.add("adapterId", multirequestToken);
		}
	}

	/**
	 * Generate C-DVR adapter shared secret
	 * 
	 * @param adapterId C-DVR adapter identifier
	 */
    public static GenerateSharedSecretCDVRAdapterProfileBuilder generateSharedSecret(int adapterId)  {
		return new GenerateSharedSecretCDVRAdapterProfileBuilder(adapterId);
	}
	
	public static class ListCDVRAdapterProfileBuilder extends ListResponseRequestBuilder<CDVRAdapterProfile, CDVRAdapterProfile.Tokenizer, ListCDVRAdapterProfileBuilder> {
		
		public ListCDVRAdapterProfileBuilder() {
			super(CDVRAdapterProfile.class, "cdvradapterprofile", "list");
		}
	}

	/**
	 * Returns all C-DVR adapters for partner
	 */
    public static ListCDVRAdapterProfileBuilder list()  {
		return new ListCDVRAdapterProfileBuilder();
	}
	
	public static class UpdateCDVRAdapterProfileBuilder extends RequestBuilder<CDVRAdapterProfile, CDVRAdapterProfile.Tokenizer, UpdateCDVRAdapterProfileBuilder> {
		
		public UpdateCDVRAdapterProfileBuilder(int adapterId, CDVRAdapterProfile adapter) {
			super(CDVRAdapterProfile.class, "cdvradapterprofile", "update");
			params.add("adapterId", adapterId);
			params.add("adapter", adapter);
		}
		
		public void adapterId(String multirequestToken) {
			params.add("adapterId", multirequestToken);
		}
	}

	/**
	 * Update C-DVR adapter details
	 * 
	 * @param adapterId C-DVR adapter identifier
	 * @param adapter C-DVR adapter Object
	 */
    public static UpdateCDVRAdapterProfileBuilder update(int adapterId, CDVRAdapterProfile adapter)  {
		return new UpdateCDVRAdapterProfileBuilder(adapterId, adapter);
	}
}
