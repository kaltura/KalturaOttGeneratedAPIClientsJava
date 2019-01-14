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

/**
 * Log in pin code details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserLoginPin.Tokenizer.class)
public class UserLoginPin extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String pinCode();
		String expirationTime();
		String userId();
	}

	/**
	 * Generated login pin code
	 */
	private String pinCode;
	/**
	 * Login pin expiration time (epoch)
	 */
	private Long expirationTime;
	/**
	 * User Identifier
	 */
	private String userId;

	// pinCode:
	public String getPinCode(){
		return this.pinCode;
	}
	public void setPinCode(String pinCode){
		this.pinCode = pinCode;
	}

	public void pinCode(String multirequestToken){
		setToken("pinCode", multirequestToken);
	}

	// expirationTime:
	public Long getExpirationTime(){
		return this.expirationTime;
	}
	public void setExpirationTime(Long expirationTime){
		this.expirationTime = expirationTime;
	}

	public void expirationTime(String multirequestToken){
		setToken("expirationTime", multirequestToken);
	}

	// userId:
	public String getUserId(){
		return this.userId;
	}

	public UserLoginPin() {
		super();
	}

	public UserLoginPin(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		pinCode = GsonParser.parseString(jsonObject.get("pinCode"));
		expirationTime = GsonParser.parseLong(jsonObject.get("expirationTime"));
		userId = GsonParser.parseString(jsonObject.get("userId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserLoginPin");
		kparams.add("pinCode", this.pinCode);
		kparams.add("expirationTime", this.expirationTime);
		return kparams;
	}

}

