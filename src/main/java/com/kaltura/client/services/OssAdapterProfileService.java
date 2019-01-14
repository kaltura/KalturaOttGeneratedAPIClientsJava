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

import com.kaltura.client.types.OSSAdapterProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class OssAdapterProfileService {
	
	public static class AddOssAdapterProfileBuilder extends RequestBuilder<OSSAdapterProfile, OSSAdapterProfile.Tokenizer, AddOssAdapterProfileBuilder> {
		
		public AddOssAdapterProfileBuilder(OSSAdapterProfile ossAdapter) {
			super(OSSAdapterProfile.class, "ossadapterprofile", "add");
			params.add("ossAdapter", ossAdapter);
		}
	}

	/**
	 * Insert new OSS adapter for partner
	 * 
	 * @param ossAdapter OSS adapter Object
	 */
    public static AddOssAdapterProfileBuilder add(OSSAdapterProfile ossAdapter)  {
		return new AddOssAdapterProfileBuilder(ossAdapter);
	}
	
	public static class DeleteOssAdapterProfileBuilder extends RequestBuilder<Boolean, String, DeleteOssAdapterProfileBuilder> {
		
		public DeleteOssAdapterProfileBuilder(int ossAdapterId) {
			super(Boolean.class, "ossadapterprofile", "delete");
			params.add("ossAdapterId", ossAdapterId);
		}
		
		public void ossAdapterId(String multirequestToken) {
			params.add("ossAdapterId", multirequestToken);
		}
	}

	/**
	 * Delete OSS adapter by OSS adapter id
	 * 
	 * @param ossAdapterId OSS adapter identifier
	 */
    public static DeleteOssAdapterProfileBuilder delete(int ossAdapterId)  {
		return new DeleteOssAdapterProfileBuilder(ossAdapterId);
	}
	
	public static class GenerateSharedSecretOssAdapterProfileBuilder extends RequestBuilder<OSSAdapterProfile, OSSAdapterProfile.Tokenizer, GenerateSharedSecretOssAdapterProfileBuilder> {
		
		public GenerateSharedSecretOssAdapterProfileBuilder(int ossAdapterId) {
			super(OSSAdapterProfile.class, "ossadapterprofile", "generateSharedSecret");
			params.add("ossAdapterId", ossAdapterId);
		}
		
		public void ossAdapterId(String multirequestToken) {
			params.add("ossAdapterId", multirequestToken);
		}
	}

	/**
	 * Generate oss adapter shared secret
	 * 
	 * @param ossAdapterId OSS adapter identifier
	 */
    public static GenerateSharedSecretOssAdapterProfileBuilder generateSharedSecret(int ossAdapterId)  {
		return new GenerateSharedSecretOssAdapterProfileBuilder(ossAdapterId);
	}
	
	public static class GetOssAdapterProfileBuilder extends RequestBuilder<OSSAdapterProfile, OSSAdapterProfile.Tokenizer, GetOssAdapterProfileBuilder> {
		
		public GetOssAdapterProfileBuilder(int id) {
			super(OSSAdapterProfile.class, "ossadapterprofile", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Returns all OSS adapters for partner : id + name
	 * 
	 * @param id OSS adapter identifier
	 */
    public static GetOssAdapterProfileBuilder get(int id)  {
		return new GetOssAdapterProfileBuilder(id);
	}
	
	public static class ListOssAdapterProfileBuilder extends ListResponseRequestBuilder<OSSAdapterProfile, OSSAdapterProfile.Tokenizer, ListOssAdapterProfileBuilder> {
		
		public ListOssAdapterProfileBuilder() {
			super(OSSAdapterProfile.class, "ossadapterprofile", "list");
		}
	}

	/**
	 * Returns all OSS adapters for partner : id + name
	 */
    public static ListOssAdapterProfileBuilder list()  {
		return new ListOssAdapterProfileBuilder();
	}
	
	public static class UpdateOssAdapterProfileBuilder extends RequestBuilder<OSSAdapterProfile, OSSAdapterProfile.Tokenizer, UpdateOssAdapterProfileBuilder> {
		
		public UpdateOssAdapterProfileBuilder(int ossAdapterId, OSSAdapterProfile ossAdapter) {
			super(OSSAdapterProfile.class, "ossadapterprofile", "update");
			params.add("ossAdapterId", ossAdapterId);
			params.add("ossAdapter", ossAdapter);
		}
		
		public void ossAdapterId(String multirequestToken) {
			params.add("ossAdapterId", multirequestToken);
		}
	}

	/**
	 * Update OSS adapter details
	 * 
	 * @param ossAdapterId OSS adapter identifier
	 * @param ossAdapter OSS adapter Object
	 */
    public static UpdateOssAdapterProfileBuilder update(int ossAdapterId, OSSAdapterProfile ossAdapter)  {
		return new UpdateOssAdapterProfileBuilder(ossAdapterId, ossAdapter);
	}
}
