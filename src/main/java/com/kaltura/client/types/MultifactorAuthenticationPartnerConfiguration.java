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
package com.kaltura.client.types;

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.enums.TokenDeliveryMethod;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MultifactorAuthenticationPartnerConfiguration.Tokenizer.class)
public class MultifactorAuthenticationPartnerConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String isEnabled();
		String roles();
		String tokenExpirationInSeconds();
		String tokenDeliveryMethod();
	}

	/**
	 * Is MFA Enabled for partner
	 */
	private Boolean isEnabled;
	/**
	 * Roles
	 */
	private String roles;
	/**
	 * Token expiration in seconds
	 */
	private Integer tokenExpirationInSeconds;
	/**
	 * Token delivery method
	 */
	private TokenDeliveryMethod tokenDeliveryMethod;

	// isEnabled:
	public Boolean getIsEnabled(){
		return this.isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled){
		this.isEnabled = isEnabled;
	}

	public void isEnabled(String multirequestToken){
		setToken("isEnabled", multirequestToken);
	}

	// roles:
	public String getRoles(){
		return this.roles;
	}
	public void setRoles(String roles){
		this.roles = roles;
	}

	public void roles(String multirequestToken){
		setToken("roles", multirequestToken);
	}

	// tokenExpirationInSeconds:
	public Integer getTokenExpirationInSeconds(){
		return this.tokenExpirationInSeconds;
	}
	public void setTokenExpirationInSeconds(Integer tokenExpirationInSeconds){
		this.tokenExpirationInSeconds = tokenExpirationInSeconds;
	}

	public void tokenExpirationInSeconds(String multirequestToken){
		setToken("tokenExpirationInSeconds", multirequestToken);
	}

	// tokenDeliveryMethod:
	public TokenDeliveryMethod getTokenDeliveryMethod(){
		return this.tokenDeliveryMethod;
	}
	public void setTokenDeliveryMethod(TokenDeliveryMethod tokenDeliveryMethod){
		this.tokenDeliveryMethod = tokenDeliveryMethod;
	}

	public void tokenDeliveryMethod(String multirequestToken){
		setToken("tokenDeliveryMethod", multirequestToken);
	}


	public MultifactorAuthenticationPartnerConfiguration() {
		super();
	}

	public MultifactorAuthenticationPartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		isEnabled = GsonParser.parseBoolean(jsonObject.get("isEnabled"));
		roles = GsonParser.parseString(jsonObject.get("roles"));
		tokenExpirationInSeconds = GsonParser.parseInt(jsonObject.get("tokenExpirationInSeconds"));
		tokenDeliveryMethod = TokenDeliveryMethod.get(GsonParser.parseString(jsonObject.get("tokenDeliveryMethod")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMultifactorAuthenticationPartnerConfiguration");
		kparams.add("isEnabled", this.isEnabled);
		kparams.add("roles", this.roles);
		kparams.add("tokenExpirationInSeconds", this.tokenExpirationInSeconds);
		kparams.add("tokenDeliveryMethod", this.tokenDeliveryMethod);
		return kparams;
	}

}

