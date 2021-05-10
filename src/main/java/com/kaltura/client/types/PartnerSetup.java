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
// Copyright (C) 2006-2021  Kaltura Inc.
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
import com.kaltura.client.types.BasePartnerConfiguration;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Parameters for partner setup
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PartnerSetup.Tokenizer.class)
public class PartnerSetup extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String adminUsername();
		String adminPassword();
		BasePartnerConfiguration.Tokenizer basePartnerConfiguration();
	}

	/**
	 * admin Username
	 */
	private String adminUsername;
	/**
	 * admin Password
	 */
	private String adminPassword;
	/**
	 * basePartnerConfiguration
	 */
	private BasePartnerConfiguration basePartnerConfiguration;

	// adminUsername:
	public String getAdminUsername(){
		return this.adminUsername;
	}
	public void setAdminUsername(String adminUsername){
		this.adminUsername = adminUsername;
	}

	public void adminUsername(String multirequestToken){
		setToken("adminUsername", multirequestToken);
	}

	// adminPassword:
	public String getAdminPassword(){
		return this.adminPassword;
	}
	public void setAdminPassword(String adminPassword){
		this.adminPassword = adminPassword;
	}

	public void adminPassword(String multirequestToken){
		setToken("adminPassword", multirequestToken);
	}

	// basePartnerConfiguration:
	public BasePartnerConfiguration getBasePartnerConfiguration(){
		return this.basePartnerConfiguration;
	}
	public void setBasePartnerConfiguration(BasePartnerConfiguration basePartnerConfiguration){
		this.basePartnerConfiguration = basePartnerConfiguration;
	}


	public PartnerSetup() {
		super();
	}

	public PartnerSetup(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		adminUsername = GsonParser.parseString(jsonObject.get("adminUsername"));
		adminPassword = GsonParser.parseString(jsonObject.get("adminPassword"));
		basePartnerConfiguration = GsonParser.parseObject(jsonObject.getAsJsonObject("basePartnerConfiguration"), BasePartnerConfiguration.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPartnerSetup");
		kparams.add("adminUsername", this.adminUsername);
		kparams.add("adminPassword", this.adminPassword);
		kparams.add("basePartnerConfiguration", this.basePartnerConfiguration);
		return kparams;
	}

}

