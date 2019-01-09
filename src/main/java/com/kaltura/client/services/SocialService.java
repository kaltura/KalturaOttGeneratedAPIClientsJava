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

import com.kaltura.client.enums.SocialNetwork;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.Social;
import com.kaltura.client.types.SocialConfig;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SocialService {
	
	public static class GetSocialBuilder extends RequestBuilder<Social, Social.Tokenizer, GetSocialBuilder> {
		
		public GetSocialBuilder(SocialNetwork type) {
			super(Social.class, "social", "get");
			params.add("type", type);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
	}

	/**
	 * List social accounts
	 * 
	 * @param type Social network type
	 */
    public static GetSocialBuilder get(SocialNetwork type)  {
		return new GetSocialBuilder(type);
	}
	
	public static class GetByTokenSocialBuilder extends RequestBuilder<Social, Social.Tokenizer, GetByTokenSocialBuilder> {
		
		public GetByTokenSocialBuilder(int partnerId, String token, SocialNetwork type) {
			super(Social.class, "social", "getByToken");
			params.add("partnerId", partnerId);
			params.add("token", token);
			params.add("type", type);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
	}

	/**
	 * Return the user object with social information according to a provided external
	  social token
	 * 
	 * @param partnerId Partner identifier
	 * @param token Social token
	 * @param type Social network type
	 */
    public static GetByTokenSocialBuilder getByToken(int partnerId, String token, SocialNetwork type)  {
		return new GetByTokenSocialBuilder(partnerId, token, type);
	}
	
	public static class GetConfigurationSocialBuilder extends RequestBuilder<SocialConfig, SocialConfig.Tokenizer, GetConfigurationSocialBuilder> {
		
		public GetConfigurationSocialBuilder(SocialNetwork type, int partnerId) {
			super(SocialConfig.class, "social", "getConfiguration");
			params.add("type", type);
			params.add("partnerId", partnerId);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
	}

	public static GetConfigurationSocialBuilder getConfiguration(SocialNetwork type)  {
		return getConfiguration(type, Integer.MIN_VALUE);
	}

	/**
	 * Retrieve the social network’s configuration information
	 * 
	 * @param type Social network type
	 * @param partnerId Partner identifier
	 */
    public static GetConfigurationSocialBuilder getConfiguration(SocialNetwork type, int partnerId)  {
		return new GetConfigurationSocialBuilder(type, partnerId);
	}
	
	public static class LoginSocialBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginSocialBuilder> {
		
		public LoginSocialBuilder(int partnerId, String token, SocialNetwork type, String udid) {
			super(LoginResponse.class, "social", "login");
			params.add("partnerId", partnerId);
			params.add("token", token);
			params.add("type", type);
			params.add("udid", udid);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static LoginSocialBuilder login(int partnerId, String token, SocialNetwork type)  {
		return login(partnerId, token, type, null);
	}

	/**
	 * Login using social token
	 * 
	 * @param partnerId Partner identifier
	 * @param token Social token
	 * @param type Social network
	 * @param udid Device UDID
	 */
    public static LoginSocialBuilder login(int partnerId, String token, SocialNetwork type, String udid)  {
		return new LoginSocialBuilder(partnerId, token, type, udid);
	}
	
	public static class MergeSocialBuilder extends RequestBuilder<Social, Social.Tokenizer, MergeSocialBuilder> {
		
		public MergeSocialBuilder(String token, SocialNetwork type) {
			super(Social.class, "social", "merge");
			params.add("token", token);
			params.add("type", type);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
	}

	/**
	 * Connect an existing user in the system to an external social network user
	 * 
	 * @param token social token
	 * @param type Social network type
	 */
    public static MergeSocialBuilder merge(String token, SocialNetwork type)  {
		return new MergeSocialBuilder(token, type);
	}
	
	public static class RegisterSocialBuilder extends RequestBuilder<Social, Social.Tokenizer, RegisterSocialBuilder> {
		
		public RegisterSocialBuilder(int partnerId, String token, SocialNetwork type, String email) {
			super(Social.class, "social", "register");
			params.add("partnerId", partnerId);
			params.add("token", token);
			params.add("type", type);
			params.add("email", email);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void email(String multirequestToken) {
			params.add("email", multirequestToken);
		}
	}

	public static RegisterSocialBuilder register(int partnerId, String token, SocialNetwork type)  {
		return register(partnerId, token, type, null);
	}

	/**
	 * Create a new user in the system using a provided external social token
	 * 
	 * @param partnerId Partner identifier
	 * @param token social token
	 * @param type Social network type
	 * @param email User email
	 */
    public static RegisterSocialBuilder register(int partnerId, String token, SocialNetwork type, String email)  {
		return new RegisterSocialBuilder(partnerId, token, type, email);
	}
	
	public static class UnmergeSocialBuilder extends RequestBuilder<Social, Social.Tokenizer, UnmergeSocialBuilder> {
		
		public UnmergeSocialBuilder(SocialNetwork type) {
			super(Social.class, "social", "unmerge");
			params.add("type", type);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
	}

	/**
	 * Disconnect an existing user in the system from its external social network user
	 * 
	 * @param type Social network type
	 */
    public static UnmergeSocialBuilder unmerge(SocialNetwork type)  {
		return new UnmergeSocialBuilder(type);
	}
	
	public static class UpdateConfigurationSocialBuilder extends RequestBuilder<SocialConfig, SocialConfig.Tokenizer, UpdateConfigurationSocialBuilder> {
		
		public UpdateConfigurationSocialBuilder(SocialConfig configuration) {
			super(SocialConfig.class, "social", "UpdateConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Set the user social network’s configuration information
	 * 
	 * @param configuration The social action settings
	 */
    public static UpdateConfigurationSocialBuilder UpdateConfiguration(SocialConfig configuration)  {
		return new UpdateConfigurationSocialBuilder(configuration);
	}
}
