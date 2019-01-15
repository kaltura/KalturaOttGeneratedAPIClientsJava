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
@MultiRequestBuilder.Tokenizer(PushParams.Tokenizer.class)
public class PushParams extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String token();
		String externalToken();
	}

	/**
	 * Device-Application push token
	 */
	private String token;
	/**
	 * External device token as received from external push provider in exchange for
	  the device token
	 */
	private String externalToken;

	// token:
	public String getToken(){
		return this.token;
	}
	public void setToken(String token){
		this.token = token;
	}

	public void token(String multirequestToken){
		setToken("token", multirequestToken);
	}

	// externalToken:
	public String getExternalToken(){
		return this.externalToken;
	}
	public void setExternalToken(String externalToken){
		this.externalToken = externalToken;
	}

	public void externalToken(String multirequestToken){
		setToken("externalToken", multirequestToken);
	}


	public PushParams() {
		super();
	}

	public PushParams(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		token = GsonParser.parseString(jsonObject.get("token"));
		externalToken = GsonParser.parseString(jsonObject.get("externalToken"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPushParams");
		kparams.add("token", this.token);
		kparams.add("externalToken", this.externalToken);
		return kparams;
	}

}

