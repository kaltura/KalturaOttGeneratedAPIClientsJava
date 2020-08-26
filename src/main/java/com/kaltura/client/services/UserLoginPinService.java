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
// Copyright (C) 2006-2020  Kaltura Inc.
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

import com.kaltura.client.types.UserLoginPin;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UserLoginPinService {
	
	public static class AddUserLoginPinBuilder extends RequestBuilder<UserLoginPin, UserLoginPin.Tokenizer, AddUserLoginPinBuilder> {
		
		public AddUserLoginPinBuilder(String secret, int pinUsages, int pinDuration) {
			super(UserLoginPin.class, "userloginpin", "add");
			params.add("secret", secret);
			params.add("pinUsages", pinUsages);
			params.add("pinDuration", pinDuration);
		}
		
		public void secret(String multirequestToken) {
			params.add("secret", multirequestToken);
		}
		
		public void pinUsages(String multirequestToken) {
			params.add("pinUsages", multirequestToken);
		}
		
		public void pinDuration(String multirequestToken) {
			params.add("pinDuration", multirequestToken);
		}
	}

	public static AddUserLoginPinBuilder add()  {
		return add(null);
	}

	public static AddUserLoginPinBuilder add(String secret)  {
		return add(secret, Integer.MIN_VALUE);
	}

	public static AddUserLoginPinBuilder add(String secret, int pinUsages)  {
		return add(secret, pinUsages, Integer.MIN_VALUE);
	}

	/**
	 * Generate a time and usage expiry login-PIN that can allow a single/multiple
	  login/s per PIN.               If an active login-PIN already exists. Calling
	  this API again for same user will add another login-PIN
	 * 
	 * @param secret Additional security parameter for optional enhanced security
	 * @param pinUsages Optional number of pin usages
	 * @param pinDuration Optional duration in minutes of the pin
	 */
    public static AddUserLoginPinBuilder add(String secret, int pinUsages, int pinDuration)  {
		return new AddUserLoginPinBuilder(secret, pinUsages, pinDuration);
	}
	
	public static class DeleteUserLoginPinBuilder extends RequestBuilder<Boolean, String, DeleteUserLoginPinBuilder> {
		
		public DeleteUserLoginPinBuilder(String pinCode) {
			super(Boolean.class, "userloginpin", "delete");
			params.add("pinCode", pinCode);
		}
		
		public void pinCode(String multirequestToken) {
			params.add("pinCode", multirequestToken);
		}
	}

	/**
	 * Immediately deletes a given pre set login pin code for the user.
	 * 
	 * @param pinCode Login pin code to expire
	 */
    public static DeleteUserLoginPinBuilder delete(String pinCode)  {
		return new DeleteUserLoginPinBuilder(pinCode);
	}
	
	public static class DeleteAllUserLoginPinBuilder extends RequestBuilder<Boolean, String, DeleteAllUserLoginPinBuilder> {
		
		public DeleteAllUserLoginPinBuilder() {
			super(Boolean.class, "userloginpin", "deleteAll");
		}
	}

	/**
	 * Immediately expire all active login-PINs for a user
	 */
    public static DeleteAllUserLoginPinBuilder deleteAll()  {
		return new DeleteAllUserLoginPinBuilder();
	}
	
	public static class UpdateUserLoginPinBuilder extends RequestBuilder<UserLoginPin, UserLoginPin.Tokenizer, UpdateUserLoginPinBuilder> {
		
		public UpdateUserLoginPinBuilder(String pinCode, String secret, int pinUsages, int pinDuration) {
			super(UserLoginPin.class, "userloginpin", "update");
			params.add("pinCode", pinCode);
			params.add("secret", secret);
			params.add("pinUsages", pinUsages);
			params.add("pinDuration", pinDuration);
		}
		
		public void pinCode(String multirequestToken) {
			params.add("pinCode", multirequestToken);
		}
		
		public void secret(String multirequestToken) {
			params.add("secret", multirequestToken);
		}
		
		public void pinUsages(String multirequestToken) {
			params.add("pinUsages", multirequestToken);
		}
		
		public void pinDuration(String multirequestToken) {
			params.add("pinDuration", multirequestToken);
		}
	}

	public static UpdateUserLoginPinBuilder update(String pinCode)  {
		return update(pinCode, null);
	}

	public static UpdateUserLoginPinBuilder update(String pinCode, String secret)  {
		return update(pinCode, secret, Integer.MIN_VALUE);
	}

	public static UpdateUserLoginPinBuilder update(String pinCode, String secret, int pinUsages)  {
		return update(pinCode, secret, pinUsages, Integer.MIN_VALUE);
	}

	/**
	 * Set a time and usage expiry login-PIN that can allow a single login per PIN. If
	  an active login-PIN already exists. Calling this API again for same user will
	  add another login-PIN
	 * 
	 * @param pinCode Device Identifier
	 * @param secret Additional security parameter to validate the login
	 * @param pinUsages Optional number of pin usages
	 * @param pinDuration Optional duration in seconds of the pin
	 */
    public static UpdateUserLoginPinBuilder update(String pinCode, String secret, int pinUsages, int pinDuration)  {
		return new UpdateUserLoginPinBuilder(pinCode, secret, pinUsages, pinDuration);
	}
}
