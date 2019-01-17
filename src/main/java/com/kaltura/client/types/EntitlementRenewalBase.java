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
@MultiRequestBuilder.Tokenizer(EntitlementRenewalBase.Tokenizer.class)
public class EntitlementRenewalBase extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String price();
		String purchaseId();
		String subscriptionId();
	}

	/**
	 * Price that is going to be paid on the renewal
	 */
	private Double price;
	/**
	 * Puchase ID
	 */
	private Long purchaseId;
	/**
	 * Subscription ID
	 */
	private Long subscriptionId;

	// price:
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		this.price = price;
	}

	public void price(String multirequestToken){
		setToken("price", multirequestToken);
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


	public EntitlementRenewalBase() {
		super();
	}

	public EntitlementRenewalBase(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		price = GsonParser.parseDouble(jsonObject.get("price"));
		purchaseId = GsonParser.parseLong(jsonObject.get("purchaseId"));
		subscriptionId = GsonParser.parseLong(jsonObject.get("subscriptionId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEntitlementRenewalBase");
		kparams.add("price", this.price);
		kparams.add("purchaseId", this.purchaseId);
		kparams.add("subscriptionId", this.subscriptionId);
		return kparams;
	}

}

