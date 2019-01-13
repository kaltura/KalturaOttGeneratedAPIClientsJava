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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UnifiedPaymentRenewal.Tokenizer.class)
public class UnifiedPaymentRenewal extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		Price.Tokenizer price();
		String date();
		String unifiedPaymentId();
		RequestBuilder.ListTokenizer<EntitlementRenewalBase.Tokenizer> entitlements();
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
	 * Unified payment ID
	 */
	private Long unifiedPaymentId;
	/**
	 * List of entitlements in this unified payment renewal
	 */
	private List<EntitlementRenewalBase> entitlements;
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

	// unifiedPaymentId:
	public Long getUnifiedPaymentId(){
		return this.unifiedPaymentId;
	}
	public void setUnifiedPaymentId(Long unifiedPaymentId){
		this.unifiedPaymentId = unifiedPaymentId;
	}

	public void unifiedPaymentId(String multirequestToken){
		setToken("unifiedPaymentId", multirequestToken);
	}

	// entitlements:
	public List<EntitlementRenewalBase> getEntitlements(){
		return this.entitlements;
	}
	public void setEntitlements(List<EntitlementRenewalBase> entitlements){
		this.entitlements = entitlements;
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


	public UnifiedPaymentRenewal() {
		super();
	}

	public UnifiedPaymentRenewal(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		price = GsonParser.parseObject(jsonObject.getAsJsonObject("price"), Price.class);
		date = GsonParser.parseLong(jsonObject.get("date"));
		unifiedPaymentId = GsonParser.parseLong(jsonObject.get("unifiedPaymentId"));
		entitlements = GsonParser.parseArray(jsonObject.getAsJsonArray("entitlements"), EntitlementRenewalBase.class);
		userId = GsonParser.parseLong(jsonObject.get("userId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUnifiedPaymentRenewal");
		kparams.add("price", this.price);
		kparams.add("date", this.date);
		kparams.add("unifiedPaymentId", this.unifiedPaymentId);
		kparams.add("entitlements", this.entitlements);
		kparams.add("userId", this.userId);
		return kparams;
	}

}

