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

import com.kaltura.client.types.IotProfile;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class IotProfileService {
	
	public static class AddIotProfileBuilder extends RequestBuilder<IotProfile, IotProfile.Tokenizer, AddIotProfileBuilder> {
		
		public AddIotProfileBuilder(IotProfile objectToAdd) {
			super(IotProfile.class, "iotprofile", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add an object
	 * 
	 * @param objectToAdd Object to add
	 */
    public static AddIotProfileBuilder add(IotProfile objectToAdd)  {
		return new AddIotProfileBuilder(objectToAdd);
	}
	
	public static class UpdateIotProfileBuilder extends RequestBuilder<IotProfile, IotProfile.Tokenizer, UpdateIotProfileBuilder> {
		
		public UpdateIotProfileBuilder(long id, IotProfile objectToUpdate) {
			super(IotProfile.class, "iotprofile", "update");
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
    public static UpdateIotProfileBuilder update(long id, IotProfile objectToUpdate)  {
		return new UpdateIotProfileBuilder(id, objectToUpdate);
	}
	
	public static class GetIotProfileBuilder extends RequestBuilder<IotProfile, IotProfile.Tokenizer, GetIotProfileBuilder> {
		
		public GetIotProfileBuilder(long id) {
			super(IotProfile.class, "iotprofile", "get");
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
    public static GetIotProfileBuilder get(long id)  {
		return new GetIotProfileBuilder(id);
	}
}
