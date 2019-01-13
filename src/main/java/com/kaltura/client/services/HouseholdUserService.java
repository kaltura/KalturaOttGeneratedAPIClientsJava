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

import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.HouseholdUserFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdUserService {
	
	public static class AddHouseholdUserBuilder extends RequestBuilder<HouseholdUser, HouseholdUser.Tokenizer, AddHouseholdUserBuilder> {
		
		public AddHouseholdUserBuilder(HouseholdUser householdUser) {
			super(HouseholdUser.class, "householduser", "add");
			params.add("householdUser", householdUser);
		}
	}

	/**
	 * Adds a user to household
	 * 
	 * @param householdUser User details to add
	 */
    public static AddHouseholdUserBuilder add(HouseholdUser householdUser)  {
		return new AddHouseholdUserBuilder(householdUser);
	}
	
	public static class DeleteHouseholdUserBuilder extends RequestBuilder<Boolean, String, DeleteHouseholdUserBuilder> {
		
		public DeleteHouseholdUserBuilder(String id) {
			super(Boolean.class, "householduser", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Removes a user from household
	 * 
	 * @param id The identifier of the user to delete
	 */
    public static DeleteHouseholdUserBuilder delete(String id)  {
		return new DeleteHouseholdUserBuilder(id);
	}
	
	public static class ListHouseholdUserBuilder extends ListResponseRequestBuilder<HouseholdUser, HouseholdUser.Tokenizer, ListHouseholdUserBuilder> {
		
		public ListHouseholdUserBuilder(HouseholdUserFilter filter) {
			super(HouseholdUser.class, "householduser", "list");
			params.add("filter", filter);
		}
	}

	public static ListHouseholdUserBuilder list()  {
		return list(null);
	}

	/**
	 * Returns the users within the household
	 * 
	 * @param filter Household user filter
	 */
    public static ListHouseholdUserBuilder list(HouseholdUserFilter filter)  {
		return new ListHouseholdUserBuilder(filter);
	}
}
