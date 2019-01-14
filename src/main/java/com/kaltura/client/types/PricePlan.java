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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Price plan
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PricePlan.Tokenizer.class)
public class PricePlan extends UsageModule {
	
	public interface Tokenizer extends UsageModule.Tokenizer {
		String isRenewable();
		String renewalsNumber();
		String discountId();
		String priceDetailsId();
	}

	/**
	 * Denotes whether or not this object can be renewed
	 */
	private Boolean isRenewable;
	/**
	 * Defines the number of times the module will be renewed (for the life_cycle
	  period)
	 */
	private Integer renewalsNumber;
	/**
	 * The discount module identifier of the price plan
	 */
	private Long discountId;
	/**
	 * The ID of the price details associated with this price plan
	 */
	private Long priceDetailsId;

	// isRenewable:
	public Boolean getIsRenewable(){
		return this.isRenewable;
	}
	// renewalsNumber:
	public Integer getRenewalsNumber(){
		return this.renewalsNumber;
	}
	// discountId:
	public Long getDiscountId(){
		return this.discountId;
	}
	// priceDetailsId:
	public Long getPriceDetailsId(){
		return this.priceDetailsId;
	}
	public void setPriceDetailsId(Long priceDetailsId){
		this.priceDetailsId = priceDetailsId;
	}

	public void priceDetailsId(String multirequestToken){
		setToken("priceDetailsId", multirequestToken);
	}


	public PricePlan() {
		super();
	}

	public PricePlan(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		isRenewable = GsonParser.parseBoolean(jsonObject.get("isRenewable"));
		renewalsNumber = GsonParser.parseInt(jsonObject.get("renewalsNumber"));
		discountId = GsonParser.parseLong(jsonObject.get("discountId"));
		priceDetailsId = GsonParser.parseLong(jsonObject.get("priceDetailsId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPricePlan");
		kparams.add("priceDetailsId", this.priceDetailsId);
		return kparams;
	}

}

