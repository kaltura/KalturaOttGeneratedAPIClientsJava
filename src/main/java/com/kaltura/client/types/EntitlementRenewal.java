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
import com.kaltura.client.types.Price;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EntitlementRenewal.Tokenizer.class)
public class EntitlementRenewal extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		Price.Tokenizer price();
		String date();
		String purchaseId();
		String subscriptionId();
		String userId();
	}

	/**
	 * Price that is going to be paid on the renewal
	 */
	private Price price;
	/**
	 * Next renewal date
	 */
	private Long date;
	/**
	 * Puchase ID
	 */
	private Long purchaseId;
	/**
	 * Subscription ID
	 */
	private Long subscriptionId;
	/**
	 * User ID
	 */
	private Long userId;

	// price:
	public Price getPrice(){
		return this.price;
	}
	public void setPrice(Price price){
		this.price = price;
	}

	// date:
	public Long getDate(){
		return this.date;
	}
	public void setDate(Long date){
		this.date = date;
	}

	public void date(String multirequestToken){
		setToken("date", multirequestToken);
	}

	// purchaseId:
	public Long getPurchaseId(){
		return this.purchaseId;
	}
	public void setPurchaseId(Long purchaseId){
		this.purchaseId = purchaseId;
	}

	public void purchaseId(String multirequestToken){
		setToken("purchaseId", multirequestToken);
	}

	// subscriptionId:
	public Long getSubscriptionId(){
		return this.subscriptionId;
	}
	public void setSubscriptionId(Long subscriptionId){
		this.subscriptionId = subscriptionId;
	}

	public void subscriptionId(String multirequestToken){
		setToken("subscriptionId", multirequestToken);
	}

	// userId:
	public Long getUserId(){
		return this.userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public void userId(String multirequestToken){
		setToken("userId", multirequestToken);
	}


	public EntitlementRenewal() {
		super();
	}

	public EntitlementRenewal(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		price = GsonParser.parseObject(jsonObject.getAsJsonObject("price"), Price.class);
		date = GsonParser.parseLong(jsonObject.get("date"));
		purchaseId = GsonParser.parseLong(jsonObject.get("purchaseId"));
		subscriptionId = GsonParser.parseLong(jsonObject.get("subscriptionId"));
		userId = GsonParser.parseLong(jsonObject.get("userId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEntitlementRenewal");
		kparams.add("price", this.price);
		kparams.add("date", this.date);
		kparams.add("purchaseId", this.purchaseId);
		kparams.add("subscriptionId", this.subscriptionId);
		kparams.add("userId", this.userId);
		return kparams;
	}

}

