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

public class GoogleIdpService {
	
	public static class AttachGoogleIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, AttachGoogleIdpBuilder> {
		
		public AttachGoogleIdpBuilder(String idToken) {
			super(SocialAttachStatus.class, "googleidp", "attach");
			params.add("idToken", idToken);
		}
		
		public void idToken(String multirequestToken) {
			params.add("idToken", multirequestToken);
		}
	}

	/**
	 * Attaches the KS’s ottUser to a Google identity. Note: Attempting to attach to
	  an IDP, a user that is already attached to the IDP in question, will fail with
	  the appropriate error.
	 * 
	 * @param idToken The Google OIDC ID Token obtained from the client.
	 */
    public static AttachGoogleIdpBuilder attach(String idToken)  {
		return new AttachGoogleIdpBuilder(idToken);
	}
	
	public static class DetachGoogleIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, DetachGoogleIdpBuilder> {
		
		public DetachGoogleIdpBuilder() {
			super(SocialAttachStatus.class, "googleidp", "detach");
		}
	}

	/**
	 * Detaches the KS’s ottUser from the Google identity that he is connected to.
	  Note: Attempting to detach from an IDP, a user that is not attached to the IDP
	  in question, will fail with the appropriate error.
	 */
    public static DetachGoogleIdpBuilder detach()  {
		return new DetachGoogleIdpBuilder();
	}
	
	public static class GetServiceIdGoogleIdpBuilder extends RequestBuilder<SocialServiceId, SocialServiceId.Tokenizer, GetServiceIdGoogleIdpBuilder> {
		
		public GetServiceIdGoogleIdpBuilder() {
			super(SocialServiceId.class, "googleidp", "getServiceId");
		}
	}

	/**
	 * Returns the identification of Kaltura’s partner (acting as the service
	  provider) in Google (acting as the identity provider).
	 */
    public static GetServiceIdGoogleIdpBuilder getServiceId()  {
		return new GetServiceIdGoogleIdpBuilder();
	}
	
	public static class IsAttachedGoogleIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, IsAttachedGoogleIdpBuilder> {
		
		public IsAttachedGoogleIdpBuilder() {
			super(SocialAttachStatus.class, "googleidp", "isAttached");
		}
	}

	/**
	 * Returns whether the user in question is attached to the Google.
	 */
    public static IsAttachedGoogleIdpBuilder isAttached()  {
		return new IsAttachedGoogleIdpBuilder();
	}
	
	public static class LoginGoogleIdpBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginGoogleIdpBuilder> {
		
		public LoginGoogleIdpBuilder(int partnerId, String idToken, Map<String, StringValue> extraParams, String udid) {
			super(LoginResponse.class, "googleidp", "login");
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

	public static LoginGoogleIdpBuilder login(int partnerId, String idToken)  {
		return login(partnerId, idToken, null);
	}

	public static LoginGoogleIdpBuilder login(int partnerId, String idToken, Map<String, StringValue> extraParams)  {
		return login(partnerId, idToken, extraParams, null);
	}

	/**
	 * Login an ottUser (acquire KS) using a Google id token.
	 * 
	 * @param partnerId Partner identifier
	 * @param idToken The Google OIDC ID Token used to verify user identity.
	 * @param extraParams Partner specific extra parameters for the login process
	 * @param udid The user device identification
	 */
    public static LoginGoogleIdpBuilder login(int partnerId, String idToken, Map<String, StringValue> extraParams, String udid)  {
		return new LoginGoogleIdpBuilder(partnerId, idToken, extraParams, udid);
	}
	
	public static class SetServiceIdGoogleIdpBuilder extends RequestBuilder<SocialServiceId, SocialServiceId.Tokenizer, SetServiceIdGoogleIdpBuilder> {
		
		public SetServiceIdGoogleIdpBuilder(String serviceId) {
			super(SocialServiceId.class, "googleidp", "setServiceId");
			params.add("serviceId", serviceId);
		}
		
		public void serviceId(String multirequestToken) {
			params.add("serviceId", multirequestToken);
		}
	}

	/**
	 * Sets the identification of Kaltura’s partner (that acts as a service provider)
	  in Google (that acts as identity provider).
	 * 
	 * @param serviceId The Google App ID (Application Identifier).
	 */
    public static SetServiceIdGoogleIdpBuilder setServiceId(String serviceId)  {
		return new SetServiceIdGoogleIdpBuilder(serviceId);
	}
}
