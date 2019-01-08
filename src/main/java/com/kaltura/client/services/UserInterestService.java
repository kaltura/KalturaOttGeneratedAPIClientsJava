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

import com.kaltura.client.types.UserInterest;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UserInterestService {
	
	public static class AddUserInterestBuilder extends RequestBuilder<UserInterest, UserInterest.Tokenizer, AddUserInterestBuilder> {
		
		public AddUserInterestBuilder(UserInterest userInterest) {
			super(UserInterest.class, "userinterest", "add");
			params.add("userInterest", userInterest);
		}
	}

	/**
	 * Insert new user interest for partner user
	 * 
	 * @param userInterest User interest Object
	 */
    public static AddUserInterestBuilder add(UserInterest userInterest)  {
		return new AddUserInterestBuilder(userInterest);
	}
	
	public static class DeleteUserInterestBuilder extends RequestBuilder<Boolean, String, DeleteUserInterestBuilder> {
		
		public DeleteUserInterestBuilder(String id) {
			super(Boolean.class, "userinterest", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete new user interest for partner user
	 * 
	 * @param id User interest identifier
	 */
    public static DeleteUserInterestBuilder delete(String id)  {
		return new DeleteUserInterestBuilder(id);
	}
	
	public static class DeleteWithTokenUserInterestBuilder extends NullRequestBuilder {
		
		public DeleteWithTokenUserInterestBuilder(String id, String token, int partnerId) {
			super("userinterest", "deleteWithToken");
			params.add("id", id);
			params.add("token", token);
			params.add("partnerId", partnerId);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
	}

	/**
	 * Delete new user interest for partner user
	 * 
	 * @param id User interest identifier
	 * @param token User's token identifier
	 * @param partnerId Partner identifier
	 */
    public static DeleteWithTokenUserInterestBuilder deleteWithToken(String id, String token, int partnerId)  {
		return new DeleteWithTokenUserInterestBuilder(id, token, partnerId);
	}
	
	public static class ListUserInterestBuilder extends ListResponseRequestBuilder<UserInterest, UserInterest.Tokenizer, ListUserInterestBuilder> {
		
		public ListUserInterestBuilder() {
			super(UserInterest.class, "userinterest", "list");
		}
	}

	/**
	 * Returns all Engagement for partner
	 */
    public static ListUserInterestBuilder list()  {
		return new ListUserInterestBuilder();
	}
}
