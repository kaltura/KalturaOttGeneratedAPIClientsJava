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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Promotion
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Promotion.Tokenizer.class)
public class Promotion extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String discountModuleId();
		RequestBuilder.ListTokenizer<Condition.Tokenizer> conditions();
		String numberOfRecurring();
	}

	/**
	 * The discount module id that is promoted to the user
	 */
	private Long discountModuleId;
	/**
	 * These conditions define the Promotion that applies on
	 */
	private List<Condition> conditions;
	/**
	 * the numer of recurring for this promotion
	 */
	private Integer numberOfRecurring;

	// discountModuleId:
	public Long getDiscountModuleId(){
		return this.discountModuleId;
	}
	public void setDiscountModuleId(Long discountModuleId){
		this.discountModuleId = discountModuleId;
	}

	public void discountModuleId(String multirequestToken){
		setToken("discountModuleId", multirequestToken);
	}

	// conditions:
	public List<Condition> getConditions(){
		return this.conditions;
	}
	public void setConditions(List<Condition> conditions){
		this.conditions = conditions;
	}

	// numberOfRecurring:
	public Integer getNumberOfRecurring(){
		return this.numberOfRecurring;
	}
	public void setNumberOfRecurring(Integer numberOfRecurring){
		this.numberOfRecurring = numberOfRecurring;
	}

	public void numberOfRecurring(String multirequestToken){
		setToken("numberOfRecurring", multirequestToken);
	}


	public Promotion() {
		super();
	}

	public Promotion(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		discountModuleId = GsonParser.parseLong(jsonObject.get("discountModuleId"));
		conditions = GsonParser.parseArray(jsonObject.getAsJsonArray("conditions"), Condition.class);
		numberOfRecurring = GsonParser.parseInt(jsonObject.get("numberOfRecurring"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPromotion");
		kparams.add("discountModuleId", this.discountModuleId);
		kparams.add("conditions", this.conditions);
		kparams.add("numberOfRecurring", this.numberOfRecurring);
		return kparams;
	}

}

