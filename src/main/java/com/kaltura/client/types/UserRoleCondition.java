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
// Copyright (C) 2006-2019  Kaltura Inc.
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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * UserRole Condition - indicates which users this rule is applied on by their
  roles
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserRoleCondition.Tokenizer.class)
public class UserRoleCondition extends Condition {
	
	public interface Tokenizer extends Condition.Tokenizer {
		String idIn();
	}

	/**
	 * Comma separated user role IDs list
	 */
	private String idIn;

	// idIn:
	public String getIdIn(){
		return this.idIn;
	}
	public void setIdIn(String idIn){
		this.idIn = idIn;
	}

	public void idIn(String multirequestToken){
		setToken("idIn", multirequestToken);
	}


	public UserRoleCondition() {
		super();
	}

	public UserRoleCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserRoleCondition");
		kparams.add("idIn", this.idIn);
		return kparams;
	}

}

