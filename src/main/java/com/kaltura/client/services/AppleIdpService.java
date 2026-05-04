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

import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.SocialAttachStatus;
import com.kaltura.client.types.SocialServiceId;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AppleIdpService {
	
	public static class AttachAppleIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, AttachAppleIdpBuilder> {
		
		public AttachAppleIdpBuilder(String idToken) {
			super(SocialAttachStatus.class, "appleidp", "attach");
			params.add("idToken", idToken);
		}
		
		public void idToken(String multirequestToken) {
			params.add("idToken", multirequestToken);
		}
	}

	/**
	 * Attaches the KS’s ottUser to a Apple identity. Note: Attempting to attach to
	  an IDP, a user that is already attached to the IDP in question, will fail with
	  the appropriate error.
	 * 
	 * @param idToken The Apple OIDC ID Token obtained from the client.
	 */
    public static AttachAppleIdpBuilder attach(String idToken)  {
		return new AttachAppleIdpBuilder(idToken);
	}
	
	public static class DetachAppleIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, DetachAppleIdpBuilder> {
		
		public DetachAppleIdpBuilder() {
			super(SocialAttachStatus.class, "appleidp", "detach");
		}
	}

	/**
	 * Detaches the KS’s ottUser from the Apple identity that he is connected to.
	  Note: Attempting to detach from an IDP, a user that is not attached to the IDP
	  in question, will fail with the appropriate error.
	 */
    public static DetachAppleIdpBuilder detach()  {
		return new DetachAppleIdpBuilder();
	}
	
	public static class GetServiceIdAppleIdpBuilder extends RequestBuilder<SocialServiceId, SocialServiceId.Tokenizer, GetServiceIdAppleIdpBuilder> {
		
		public GetServiceIdAppleIdpBuilder() {
			super(SocialServiceId.class, "appleidp", "getServiceId");
		}
	}

	/**
	 * Returns the identification of Kaltura’s partner (acting as the service
	  provider) in Apple (acting as the identity provider).
	 */
    public static GetServiceIdAppleIdpBuilder getServiceId()  {
		return new GetServiceIdAppleIdpBuilder();
	}
	
	public static class IsAttachedAppleIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, IsAttachedAppleIdpBuilder> {
		
		public IsAttachedAppleIdpBuilder() {
			super(SocialAttachStatus.class, "appleidp", "isAttached");
		}
	}

	/**
	 * Returns whether the user in question is attached to the Apple.
	 */
    public static IsAttachedAppleIdpBuilder isAttached()  {
		return new IsAttachedAppleIdpBuilder();
	}
	
	public static class LoginAppleIdpBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginAppleIdpBuilder> {
		
		public LoginAppleIdpBuilder(int partnerId, String idToken, Map<String, StringValue> extraParams, String udid) {
			super(LoginResponse.class, "appleidp", "login");
			params.add("partnerId", partnerId);
			params.add("idToken", idToken);
			params.add("extraParams", extraParams);
			params.add("udid", udid);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void idToken(String multirequestToken) {
			params.add("idToken", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static LoginAppleIdpBuilder login(int partnerId, String idToken)  {
		return login(partnerId, idToken, null);
	}

	public static LoginAppleIdpBuilder login(int partnerId, String idToken, Map<String, StringValue> extraParams)  {
		return login(partnerId, idToken, extraParams, null);
	}

	/**
	 * Login an ottUser (acquire KS) using an Apple id token.
	 * 
	 * @param partnerId Partner identifier
	 * @param idToken The Apple OIDC ID Token used to verify user identity.
	 * @param extraParams Partner specific extra parameters for the login process
	 * @param udid The user device identification
	 */
    public static LoginAppleIdpBuilder login(int partnerId, String idToken, Map<String, StringValue> extraParams, String udid)  {
		return new LoginAppleIdpBuilder(partnerId, idToken, extraParams, udid);
	}
	
	public static class SetServiceIdAppleIdpBuilder extends RequestBuilder<SocialServiceId, SocialServiceId.Tokenizer, SetServiceIdAppleIdpBuilder> {
		
		public SetServiceIdAppleIdpBuilder(String serviceId) {
			super(SocialServiceId.class, "appleidp", "setServiceId");
			params.add("serviceId", serviceId);
		}
		
		public void serviceId(String multirequestToken) {
			params.add("serviceId", multirequestToken);
		}
	}

	/**
	 * Sets the identification of Kaltura’s partner (that acts as a service provider)
	  in Apple (that acts as identity provider).
	 * 
	 * @param serviceId The Apple App ID (Application Identifier).
	 */
    public static SetServiceIdAppleIdpBuilder setServiceId(String serviceId)  {
		return new SetServiceIdAppleIdpBuilder(serviceId);
	}
}
