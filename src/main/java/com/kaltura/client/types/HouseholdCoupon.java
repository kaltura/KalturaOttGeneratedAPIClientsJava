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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Household Coupon details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdCoupon.Tokenizer.class)
public class HouseholdCoupon extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String code();
		String lastUsageDate();
	}

	/**
	 * Coupon code
	 */
	private String code;
	/**
	 * Last Usage Date
	 */
	private Long lastUsageDate;

	// code:
	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}

	public void code(String multirequestToken){
		setToken("code", multirequestToken);
	}

	// lastUsageDate:
	public Long getLastUsageDate(){
		return this.lastUsageDate;
	}
	public void setLastUsageDate(Long lastUsageDate){
		this.lastUsageDate = lastUsageDate;
	}

	public void lastUsageDate(String multirequestToken){
		setToken("lastUsageDate", multirequestToken);
	}


	public HouseholdCoupon() {
		super();
	}

	public HouseholdCoupon(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		code = GsonParser.parseString(jsonObject.get("code"));
		lastUsageDate = GsonParser.parseLong(jsonObject.get("lastUsageDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdCoupon");
		kparams.add("code", this.code);
		kparams.add("lastUsageDate", this.lastUsageDate);
		return kparams;
	}

}

