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
// Copyright (C) 2006-2023  Kaltura Inc.
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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.UserSessionProfile;
import com.kaltura.client.types.UserSessionProfileFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UserSessionProfileService {
	
	public static class AddUserSessionProfileBuilder extends RequestBuilder<UserSessionProfile, UserSessionProfile.Tokenizer, AddUserSessionProfileBuilder> {
		
		public AddUserSessionProfileBuilder(UserSessionProfile userSessionProfile) {
			super(UserSessionProfile.class, "usersessionprofile", "add");
			params.add("userSessionProfile", userSessionProfile);
		}
	}

	/**
	 * Add new UserSessionProfile
	 * 
	 * @param userSessionProfile userSessionProfile Object to add
	 */
    public static AddUserSessionProfileBuilder add(UserSessionProfile userSessionProfile)  {
		return new AddUserSessionProfileBuilder(userSessionProfile);
	}
	
	public static class DeleteUserSessionProfileBuilder extends NullRequestBuilder {
		
		public DeleteUserSessionProfileBuilder(long id) {
			super("usersessionprofile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete existing UserSessionProfile
	 * 
	 * @param id UserSessionProfile identifier
	 */
    public static DeleteUserSessionProfileBuilder delete(long id)  {
		return new DeleteUserSessionProfileBuilder(id);
	}
	
	public static class ListUserSessionProfileBuilder extends ListResponseRequestBuilder<UserSessionProfile, UserSessionProfile.Tokenizer, ListUserSessionProfileBuilder> {
		
		public ListUserSessionProfileBuilder(UserSessionProfileFilter filter, FilterPager pager) {
			super(UserSessionProfile.class, "usersessionprofile", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListUserSessionProfileBuilder list()  {
		return list(null);
	}

	public static ListUserSessionProfileBuilder list(UserSessionProfileFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns the list of available UserSessionProfiles
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListUserSessionProfileBuilder list(UserSessionProfileFilter filter, FilterPager pager)  {
		return new ListUserSessionProfileBuilder(filter, pager);
	}
	
	public static class UpdateUserSessionProfileBuilder extends RequestBuilder<UserSessionProfile, UserSessionProfile.Tokenizer, UpdateUserSessionProfileBuilder> {
		
		public UpdateUserSessionProfileBuilder(long id, UserSessionProfile userSessionProfile) {
			super(UserSessionProfile.class, "usersessionprofile", "update");
			params.add("id", id);
			params.add("userSessionProfile", userSessionProfile);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update existing UserSessionProfile
	 * 
	 * @param id id of userSessionProfile to update
	 * @param userSessionProfile userSessionProfile Object to update
	 */
    public static UpdateUserSessionProfileBuilder update(long id, UserSessionProfile userSessionProfile)  {
		return new UpdateUserSessionProfileBuilder(id, userSessionProfile);
	}
}
