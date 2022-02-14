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
// Copyright (C) 2006-2022  Kaltura Inc.
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
import com.kaltura.client.enums.TransactionType;
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
@MultiRequestBuilder.Tokenizer(BusinessModuleDetails.Tokenizer.class)
public class BusinessModuleDetails extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String businessModuleId();
		String businessModuleType();
	}

	/**
	 * BusinessModuleId
	 */
	private Integer businessModuleId;
	/**
	 * BusinessModuleType
	 */
	private TransactionType businessModuleType;

	// businessModuleId:
	public Integer getBusinessModuleId(){
		return this.businessModuleId;
	}
	public void setBusinessModuleId(Integer businessModuleId){
		this.businessModuleId = businessModuleId;
	}

	public void businessModuleId(String multirequestToken){
		setToken("businessModuleId", multirequestToken);
	}

	// businessModuleType:
	public TransactionType getBusinessModuleType(){
		return this.businessModuleType;
	}
	public void setBusinessModuleType(TransactionType businessModuleType){
		this.businessModuleType = businessModuleType;
	}

	public void businessModuleType(String multirequestToken){
		setToken("businessModuleType", multirequestToken);
	}


	public BusinessModuleDetails() {
		super();
	}

	public BusinessModuleDetails(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		businessModuleId = GsonParser.parseInt(jsonObject.get("businessModuleId"));
		businessModuleType = TransactionType.get(GsonParser.parseString(jsonObject.get("businessModuleType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBusinessModuleDetails");
		kparams.add("businessModuleId", this.businessModuleId);
		kparams.add("businessModuleType", this.businessModuleType);
		return kparams;
	}

}

