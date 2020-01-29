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
// Copyright (C) 2006-2019  Kaltura Inc.
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

import com.kaltura.client.types.PasswordPolicy;
import com.kaltura.client.types.PasswordPolicyFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PasswordPolicyService {
	
	public static class AddPasswordPolicyBuilder extends RequestBuilder<PasswordPolicy, PasswordPolicy.Tokenizer, AddPasswordPolicyBuilder> {
		
		public AddPasswordPolicyBuilder(PasswordPolicy objectToAdd) {
			super(PasswordPolicy.class, "passwordpolicy", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add an object
	 * 
	 * @param objectToAdd Object to add
	 */
    public static AddPasswordPolicyBuilder add(PasswordPolicy objectToAdd)  {
		return new AddPasswordPolicyBuilder(objectToAdd);
	}
	
	public static class UpdatePasswordPolicyBuilder extends RequestBuilder<PasswordPolicy, PasswordPolicy.Tokenizer, UpdatePasswordPolicyBuilder> {
		
		public UpdatePasswordPolicyBuilder(long id, PasswordPolicy objectToUpdate) {
			super(PasswordPolicy.class, "passwordpolicy", "update");
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
    public static UpdatePasswordPolicyBuilder update(long id, PasswordPolicy objectToUpdate)  {
		return new UpdatePasswordPolicyBuilder(id, objectToUpdate);
	}
	
	public static class DeletePasswordPolicyBuilder extends NullRequestBuilder {
		
		public DeletePasswordPolicyBuilder(long id) {
			super("passwordpolicy", "delete");
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
    public static DeletePasswordPolicyBuilder delete(long id)  {
		return new DeletePasswordPolicyBuilder(id);
	}
	
	public static class ListPasswordPolicyBuilder extends ListResponseRequestBuilder<PasswordPolicy, PasswordPolicy.Tokenizer, ListPasswordPolicyBuilder> {
		
		public ListPasswordPolicyBuilder(PasswordPolicyFilter filter) {
			super(PasswordPolicy.class, "passwordpolicy", "list");
			params.add("filter", filter);
		}
	}

	public static ListPasswordPolicyBuilder list()  {
		return list(null);
	}

    public static ListPasswordPolicyBuilder list(PasswordPolicyFilter filter)  {
		return new ListPasswordPolicyBuilder(filter);
	}
}
