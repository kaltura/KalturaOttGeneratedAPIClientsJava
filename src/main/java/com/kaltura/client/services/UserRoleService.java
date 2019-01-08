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

import com.kaltura.client.types.UserRole;
import com.kaltura.client.types.UserRoleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UserRoleService {
	
	public static class AddUserRoleBuilder extends RequestBuilder<UserRole, UserRole.Tokenizer, AddUserRoleBuilder> {
		
		public AddUserRoleBuilder(UserRole role) {
			super(UserRole.class, "userrole", "add");
			params.add("role", role);
		}
	}

	/**
	 * Creates a new role
	 * 
	 * @param role Role to add
	 */
    public static AddUserRoleBuilder add(UserRole role)  {
		return new AddUserRoleBuilder(role);
	}
	
	public static class DeleteUserRoleBuilder extends RequestBuilder<Boolean, String, DeleteUserRoleBuilder> {
		
		public DeleteUserRoleBuilder(long id) {
			super(Boolean.class, "userrole", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete role
	 * 
	 * @param id Role id to delete
	 */
    public static DeleteUserRoleBuilder delete(long id)  {
		return new DeleteUserRoleBuilder(id);
	}
	
	public static class ListUserRoleBuilder extends ListResponseRequestBuilder<UserRole, UserRole.Tokenizer, ListUserRoleBuilder> {
		
		public ListUserRoleBuilder(UserRoleFilter filter) {
			super(UserRole.class, "userrole", "list");
			params.add("filter", filter);
		}
	}

	public static ListUserRoleBuilder list()  {
		return list(null);
	}

	/**
	 * Retrieving user roles by identifiers, if filter is empty, returns all partner
	  roles
	 * 
	 * @param filter User roles filter
	 */
    public static ListUserRoleBuilder list(UserRoleFilter filter)  {
		return new ListUserRoleBuilder(filter);
	}
	
	public static class UpdateUserRoleBuilder extends RequestBuilder<UserRole, UserRole.Tokenizer, UpdateUserRoleBuilder> {
		
		public UpdateUserRoleBuilder(long id, UserRole role) {
			super(UserRole.class, "userrole", "update");
			params.add("id", id);
			params.add("role", role);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update role
	 * 
	 * @param id Role Id
	 * @param role Role to Update
	 */
    public static UpdateUserRoleBuilder update(long id, UserRole role)  {
		return new UpdateUserRoleBuilder(id, role);
	}
}
