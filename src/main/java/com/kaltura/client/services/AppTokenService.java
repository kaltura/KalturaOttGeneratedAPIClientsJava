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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.enums.SessionType;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.types.SessionInfo;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AppTokenService {
	
	public static class AddAppTokenBuilder extends RequestBuilder<AppToken, AppToken.Tokenizer, AddAppTokenBuilder> {
		
		public AddAppTokenBuilder(AppToken appToken) {
			super(AppToken.class, "apptoken", "add");
			params.add("appToken", appToken);
		}
	}

	/**  Add new application authentication token  */
    public static AddAppTokenBuilder add(AppToken appToken)  {
		return new AddAppTokenBuilder(appToken);
	}
	
	public static class DeleteAppTokenBuilder extends RequestBuilder<Boolean, String, DeleteAppTokenBuilder> {
		
		public DeleteAppTokenBuilder(String id) {
			super(Boolean.class, "apptoken", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**  Delete application authentication token by id  */
    public static DeleteAppTokenBuilder delete(String id)  {
		return new DeleteAppTokenBuilder(id);
	}
	
	public static class GetAppTokenBuilder extends RequestBuilder<AppToken, AppToken.Tokenizer, GetAppTokenBuilder> {
		
		public GetAppTokenBuilder(String id) {
			super(AppToken.class, "apptoken", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**  Get application authentication token by id  */
    public static GetAppTokenBuilder get(String id)  {
		return new GetAppTokenBuilder(id);
	}
	
	public static class StartSessionAppTokenBuilder extends RequestBuilder<SessionInfo, SessionInfo.Tokenizer, StartSessionAppTokenBuilder> {
		
		public StartSessionAppTokenBuilder(String id, String tokenHash, String userId, SessionType type, int expiry, String udid) {
			super(SessionInfo.class, "apptoken", "startSession");
			params.add("id", id);
			params.add("tokenHash", tokenHash);
			params.add("userId", userId);
			params.add("type", type);
			params.add("expiry", expiry);
			params.add("udid", udid);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void tokenHash(String multirequestToken) {
			params.add("tokenHash", multirequestToken);
		}
		
		public void userId(String multirequestToken) {
			params.add("userId", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void expiry(String multirequestToken) {
			params.add("expiry", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static StartSessionAppTokenBuilder startSession(String id, String tokenHash)  {
		return startSession(id, tokenHash, null);
	}

	public static StartSessionAppTokenBuilder startSession(String id, String tokenHash, String userId)  {
		return startSession(id, tokenHash, userId, SessionType.get(Integer.MIN_VALUE));
	}

	public static StartSessionAppTokenBuilder startSession(String id, String tokenHash, String userId, SessionType type)  {
		return startSession(id, tokenHash, userId, type, Integer.MIN_VALUE);
	}

	public static StartSessionAppTokenBuilder startSession(String id, String tokenHash, String userId, SessionType type, int expiry)  {
		return startSession(id, tokenHash, userId, type, expiry, null);
	}

	/**  Starts a new KS (Kaltura Session) based on application authentication token id  */
    public static StartSessionAppTokenBuilder startSession(String id, String tokenHash, String userId, SessionType type, int expiry, String udid)  {
		return new StartSessionAppTokenBuilder(id, tokenHash, userId, type, expiry, udid);
	}
}
