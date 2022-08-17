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
// Copyright (C) 2006-2022  Kaltura Inc.
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
import com.kaltura.client.types.SessionCharacteristic;
import com.kaltura.client.types.StringValueArray;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SessionService {
	
	public static class CreateSessionCharacteristicSessionBuilder extends RequestBuilder<SessionCharacteristic, SessionCharacteristic.Tokenizer, CreateSessionCharacteristicSessionBuilder> {
		
		public CreateSessionCharacteristicSessionBuilder(String userId, long householdId, String udid, long expiration, int regionId, Map<String, StringValueArray> sessionCharacteristicParams) {
			super(SessionCharacteristic.class, "session", "createSessionCharacteristic");
			params.add("userId", userId);
			params.add("householdId", householdId);
			params.add("udid", udid);
			params.add("expiration", expiration);
			params.add("regionId", regionId);
			params.add("sessionCharacteristicParams", sessionCharacteristicParams);
		}
		
		public void userId(String multirequestToken) {
			params.add("userId", multirequestToken);
		}
		
		public void householdId(String multirequestToken) {
			params.add("householdId", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
		
		public void expiration(String multirequestToken) {
			params.add("expiration", multirequestToken);
		}
		
		public void regionId(String multirequestToken) {
			params.add("regionId", multirequestToken);
		}
	}

	public static CreateSessionCharacteristicSessionBuilder createSessionCharacteristic(String userId, long householdId, String udid, long expiration)  {
		return createSessionCharacteristic(userId, householdId, udid, expiration, Integer.MIN_VALUE);
	}

	public static CreateSessionCharacteristicSessionBuilder createSessionCharacteristic(String userId, long householdId, String udid, long expiration, int regionId)  {
		return createSessionCharacteristic(userId, householdId, udid, expiration, regionId, null);
	}

	/**
	 * Create session characteristic
	 * 
	 * @param userId user identifier
	 * @param householdId household identifier
	 * @param udid device UDID
	 * @param expiration relative expiration(TTL) in seconds, should be equal or greater than KS
	 * expiration
	 * @param regionId region identifier
	 * @param sessionCharacteristicParams session characteristic dynamic params
	 */
    public static CreateSessionCharacteristicSessionBuilder createSessionCharacteristic(String userId, long householdId, String udid, long expiration, int regionId, Map<String, StringValueArray> sessionCharacteristicParams)  {
		return new CreateSessionCharacteristicSessionBuilder(userId, householdId, udid, expiration, regionId, sessionCharacteristicParams);
	}
	
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
