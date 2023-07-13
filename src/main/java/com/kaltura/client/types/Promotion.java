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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

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
public class Promotion extends BasePromotion {
	
	public interface Tokenizer extends BasePromotion.Tokenizer {
		String discountModuleId();
		String numberOfRecurring();
		String maxDiscountUsages();
	}

	/**
	 * The discount module id that is promoted to the user
	 */
	private Long discountModuleId;
	/**
	 * the numer of recurring for this promotion
	 */
	private Integer numberOfRecurring;
	/**
	 * The number of times a household can use the discount module in this campaign.   
	           If omitted than no limitation is enforced on the number of usages.
	 */
	private Integer maxDiscountUsages;

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

	// maxDiscountUsages:
	public Integer getMaxDiscountUsages(){
		return this.maxDiscountUsages;
	}
	public void setMaxDiscountUsages(Integer maxDiscountUsages){
		this.maxDiscountUsages = maxDiscountUsages;
	}

	public void maxDiscountUsages(String multirequestToken){
		setToken("maxDiscountUsages", multirequestToken);
	}


	public Promotion() {
		super();
	}

	public Promotion(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		discountModuleId = GsonParser.parseLong(jsonObject.get("discountModuleId"));
		numberOfRecurring = GsonParser.parseInt(jsonObject.get("numberOfRecurring"));
		maxDiscountUsages = GsonParser.parseInt(jsonObject.get("maxDiscountUsages"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPromotion");
		kparams.add("discountModuleId", this.discountModuleId);
		kparams.add("numberOfRecurring", this.numberOfRecurring);
		kparams.add("maxDiscountUsages", this.maxDiscountUsages);
		return kparams;
	}

}

