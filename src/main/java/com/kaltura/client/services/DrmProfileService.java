// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
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

import com.kaltura.client.types.DrmProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DrmProfileService {
	
	public static class AddDrmProfileBuilder extends RequestBuilder<DrmProfile, DrmProfile.Tokenizer, AddDrmProfileBuilder> {
		
		public AddDrmProfileBuilder(DrmProfile drmProfile) {
			super(DrmProfile.class, "drmprofile", "add");
			params.add("drmProfile", drmProfile);
		}
	}

	/**
	 * Internal API !!! Insert new DrmProfile
	 * 
	 * @param drmProfile Drm adapter Object
	 */
    public static AddDrmProfileBuilder add(DrmProfile drmProfile)  {
		return new AddDrmProfileBuilder(drmProfile);
	}
	
	public static class DeleteDrmProfileBuilder extends RequestBuilder<Boolean, String, DeleteDrmProfileBuilder> {
		
		public DeleteDrmProfileBuilder(long id) {
			super(Boolean.class, "drmprofile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Internal API !!! Delete DrmProfile
	 * 
	 * @param id Drm adapter id
	 */
    public static DeleteDrmProfileBuilder delete(long id)  {
		return new DeleteDrmProfileBuilder(id);
	}
	
	public static class ListDrmProfileBuilder extends ListResponseRequestBuilder<DrmProfile, DrmProfile.Tokenizer, ListDrmProfileBuilder> {
		
		public ListDrmProfileBuilder() {
			super(DrmProfile.class, "drmprofile", "list");
		}
	}

	/**
	 * Returns all DRM adapters for partner
	 */
    public static ListDrmProfileBuilder list()  {
		return new ListDrmProfileBuilder();
	}
}
