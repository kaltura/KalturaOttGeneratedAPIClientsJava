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

import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.Session;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SessionService {
	
	public static class GetSessionBuilder extends RequestBuilder<Session, Session.Tokenizer, GetSessionBuilder> {
		
		public GetSessionBuilder(String session) {
			super(Session.class, "session", "get");
			params.add("session", session);
		}
		
		public void session(String multirequestToken) {
			params.add("session", multirequestToken);
		}
	}

	public static GetSessionBuilder get()  {
		return get(null);
	}

	/**
	 * Parses KS
	 * 
	 * @param session Additional KS to parse, if not passed the user's KS will be parsed
	 */
    public static GetSessionBuilder get(String session)  {
		return new GetSessionBuilder(session);
	}
	
	public static class RevokeSessionBuilder extends RequestBuilder<Boolean, String, RevokeSessionBuilder> {
		
		public RevokeSessionBuilder() {
			super(Boolean.class, "session", "revoke");
		}
	}

	/**
	 * Revokes all the sessions (KS) of a given user
	 */
    public static RevokeSessionBuilder revoke()  {
		return new RevokeSessionBuilder();
	}
	
	public static class SwitchUserSessionBuilder extends RequestBuilder<LoginSession, LoginSession.Tokenizer, SwitchUserSessionBuilder> {
		
		public SwitchUserSessionBuilder(String userIdToSwitch) {
			super(LoginSession.class, "session", "switchUser");
			params.add("userIdToSwitch", userIdToSwitch);
		}
		
		public void userIdToSwitch(String multirequestToken) {
			params.add("userIdToSwitch", multirequestToken);
		}
	}

	/**
	 * Switching the user in the session by generating a new session for a new user
	  within the same household
	 * 
	 * @param userIdToSwitch The identifier of the user to change
	 */
    public static SwitchUserSessionBuilder switchUser(String userIdToSwitch)  {
		return new SwitchUserSessionBuilder(userIdToSwitch);
	}
}
