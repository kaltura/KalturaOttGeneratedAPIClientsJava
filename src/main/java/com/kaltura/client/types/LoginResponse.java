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
package com.kaltura.client.types;

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(LoginResponse.Tokenizer.class)
public class LoginResponse extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		OTTUser.Tokenizer user();
		LoginSession.Tokenizer loginSession();
	}

	/**
	 * User
	 */
	private OTTUser user;
	/**
	 * Kaltura login session details
	 */
	private LoginSession loginSession;

	// user:
	public OTTUser getUser(){
		return this.user;
	}
	public void setUser(OTTUser user){
		this.user = user;
	}

	// loginSession:
	public LoginSession getLoginSession(){
		return this.loginSession;
	}
	public void setLoginSession(LoginSession loginSession){
		this.loginSession = loginSession;
	}


	public LoginResponse() {
		super();
	}

	public LoginResponse(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		user = GsonParser.parseObject(jsonObject.getAsJsonObject("user"), OTTUser.class);
		loginSession = GsonParser.parseObject(jsonObject.getAsJsonObject("loginSession"), LoginSession.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLoginResponse");
		kparams.add("user", this.user);
		kparams.add("loginSession", this.loginSession);
		return kparams;
	}

}

