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
import com.kaltura.client.types.SocialSetSecretResponse;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class FacebookIdpService {
	
	public static class AttachFacebookIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, AttachFacebookIdpBuilder> {
		
		public AttachFacebookIdpBuilder(String accessToken) {
			super(SocialAttachStatus.class, "facebookidp", "attach");
			params.add("accessToken", accessToken);
		}
		
		public void accessToken(String multirequestToken) {
			params.add("accessToken", multirequestToken);
		}
	}

	/**
	 * Attaches the KS’s ottUser to a Facebook identity. Note: Attempting to attach
	  to an IDP, a user that is already attached to the IDP in question, will fail
	  with the appropriate error.
	 * 
	 * @param accessToken The valid Facebook Access Token obtained from the client-side login.
	 */
    public static AttachFacebookIdpBuilder attach(String accessToken)  {
		return new AttachFacebookIdpBuilder(accessToken);
	}
	
	public static class DetachFacebookIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, DetachFacebookIdpBuilder> {
		
		public DetachFacebookIdpBuilder() {
			super(SocialAttachStatus.class, "facebookidp", "detach");
		}
	}

	/**
	 * Detaches the KS’s ottUser from the Facebook identity that he is connected to.
	  Note: Attempting to detach from an IDP, a user that is not attached to the IDP
	  in question, will fail with the appropriate error.
	 */
    public static DetachFacebookIdpBuilder detach()  {
		return new DetachFacebookIdpBuilder();
	}
	
	public static class GetServiceIdFacebookIdpBuilder extends RequestBuilder<SocialServiceId, SocialServiceId.Tokenizer, GetServiceIdFacebookIdpBuilder> {
		
		public GetServiceIdFacebookIdpBuilder() {
			super(SocialServiceId.class, "facebookidp", "getServiceId");
		}
	}

	/**
	 * Returns the identification of Kaltura’s partner (acting as the service
	  provider) in Facebook (acting as the identity provider).
	 */
    public static GetServiceIdFacebookIdpBuilder getServiceId()  {
		return new GetServiceIdFacebookIdpBuilder();
	}
	
	public static class IsAttachedFacebookIdpBuilder extends RequestBuilder<SocialAttachStatus, SocialAttachStatus.Tokenizer, IsAttachedFacebookIdpBuilder> {
		
		public IsAttachedFacebookIdpBuilder() {
			super(SocialAttachStatus.class, "facebookidp", "isAttached");
		}
	}

	/**
	 * Returns whether the user in question is attached to the Facebook.
	 */
    public static IsAttachedFacebookIdpBuilder isAttached()  {
		return new IsAttachedFacebookIdpBuilder();
	}
	
	public static class LoginFacebookIdpBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginFacebookIdpBuilder> {
		
		public LoginFacebookIdpBuilder(int partnerId, String accessToken, Map<String, StringValue> extraParams, String udid) {
			super(LoginResponse.class, "facebookidp", "login");
			params.add("partnerId", partnerId);
			params.add("accessToken", accessToken);
			params.add("extraParams", extraParams);
			params.add("udid", udid);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void accessToken(String multirequestToken) {
			params.add("accessToken", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static LoginFacebookIdpBuilder login(int partnerId, String accessToken)  {
		return login(partnerId, accessToken, null);
	}

	public static LoginFacebookIdpBuilder login(int partnerId, String accessToken, Map<String, StringValue> extraParams)  {
		return login(partnerId, accessToken, extraParams, null);
	}

	/**
	 * Login an ottUser (acquire KS) using a Facebook access token.
	 * 
	 * @param partnerId Partner identifier
	 * @param accessToken The valid Facebook Access Token used to verify the user identity.
	 * @param extraParams Partner specific extra parameters for the login process
	 * @param udid The user device identification
	 */
    public static LoginFacebookIdpBuilder login(int partnerId, String accessToken, Map<String, StringValue> extraParams, String udid)  {
		return new LoginFacebookIdpBuilder(partnerId, accessToken, extraParams, udid);
	}
	
	public static class SetSecretFacebookIdpBuilder extends RequestBuilder<SocialSetSecretResponse, SocialSetSecretResponse.Tokenizer, SetSecretFacebookIdpBuilder> {
		
		public SetSecretFacebookIdpBuilder(String secret) {
			super(SocialSetSecretResponse.class, "facebookidp", "setSecret");
			params.add("secret", secret);
		}
		
		public void secret(String multirequestToken) {
			params.add("secret", multirequestToken);
		}
	}

	/**
	 * Sets the secret that is shared between Kaltura’s partner (that acts as a
	  service provider) in Facebook (that acts as identity provider) that enables
	  Facebook to identify the partner.
	 * 
	 * @param secret The shared secret key provided by Facebook for the application.
	 */
    public static SetSecretFacebookIdpBuilder setSecret(String secret)  {
		return new SetSecretFacebookIdpBuilder(secret);
	}
	
	public static class SetServiceIdFacebookIdpBuilder extends RequestBuilder<SocialServiceId, SocialServiceId.Tokenizer, SetServiceIdFacebookIdpBuilder> {
		
		public SetServiceIdFacebookIdpBuilder(String serviceId) {
			super(SocialServiceId.class, "facebookidp", "setServiceId");
			params.add("serviceId", serviceId);
		}
		
		public void serviceId(String multirequestToken) {
			params.add("serviceId", multirequestToken);
		}
	}

	/**
	 * Sets the identification of Kaltura’s partner (that acts as a service provider)
	  in Facebook (that acts as identity provider).
	 * 
	 * @param serviceId The Facebook App ID (Application Identifier).
	 */
    public static SetServiceIdFacebookIdpBuilder setServiceId(String serviceId)  {
		return new SetServiceIdFacebookIdpBuilder(serviceId);
	}
}
