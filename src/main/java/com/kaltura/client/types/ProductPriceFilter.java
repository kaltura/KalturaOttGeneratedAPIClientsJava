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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ProductPriceFilter.Tokenizer.class)
public class ProductPriceFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String subscriptionIdIn();
		String fileIdIn();
		String collectionIdIn();
		String isLowest();
		String couponCodeEqual();
	}

	/**
	 * Comma separated subscriptions identifiers
	 */
	private String subscriptionIdIn;
	/**
	 * Comma separated media files identifiers
	 */
	private String fileIdIn;
	/**
	 * Comma separated collections identifiers
	 */
	private String collectionIdIn;
	/**
	 * A flag that indicates if only the lowest price of an item should return
	 */
	private Boolean isLowest;
	/**
	 * Discount coupon code
	 */
	private String couponCodeEqual;

	// subscriptionIdIn:
	public String getSubscriptionIdIn(){
		return this.subscriptionIdIn;
	}
	public void setSubscriptionIdIn(String subscriptionIdIn){
		this.subscriptionIdIn = subscriptionIdIn;
	}

	public void subscriptionIdIn(String multirequestToken){
		setToken("subscriptionIdIn", multirequestToken);
	}

	// fileIdIn:
	public String getFileIdIn(){
		return this.fileIdIn;
	}
	public void setFileIdIn(String fileIdIn){
		this.fileIdIn = fileIdIn;
	}

	public void fileIdIn(String multirequestToken){
		setToken("fileIdIn", multirequestToken);
	}

	// collectionIdIn:
	public String getCollectionIdIn(){
		return this.collectionIdIn;
	}
	public void setCollectionIdIn(String collectionIdIn){
		this.collectionIdIn = collectionIdIn;
	}

	public void collectionIdIn(String multirequestToken){
		setToken("collectionIdIn", multirequestToken);
	}

	// isLowest:
	public Boolean getIsLowest(){
		return this.isLowest;
	}
	public void setIsLowest(Boolean isLowest){
		this.isLowest = isLowest;
	}

	public void isLowest(String multirequestToken){
		setToken("isLowest", multirequestToken);
	}

	// couponCodeEqual:
	public String getCouponCodeEqual(){
		return this.couponCodeEqual;
	}
	public void setCouponCodeEqual(String couponCodeEqual){
		this.couponCodeEqual = couponCodeEqual;
	}

	public void couponCodeEqual(String multirequestToken){
		setToken("couponCodeEqual", multirequestToken);
	}


	public ProductPriceFilter() {
		super();
	}

	public ProductPriceFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		subscriptionIdIn = GsonParser.parseString(jsonObject.get("subscriptionIdIn"));
		fileIdIn = GsonParser.parseString(jsonObject.get("fileIdIn"));
		collectionIdIn = GsonParser.parseString(jsonObject.get("collectionIdIn"));
		isLowest = GsonParser.parseBoolean(jsonObject.get("isLowest"));
		couponCodeEqual = GsonParser.parseString(jsonObject.get("couponCodeEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaProductPriceFilter");
		kparams.add("subscriptionIdIn", this.subscriptionIdIn);
		kparams.add("fileIdIn", this.fileIdIn);
		kparams.add("collectionIdIn", this.collectionIdIn);
		kparams.add("isLowest", this.isLowest);
		kparams.add("couponCodeEqual", this.couponCodeEqual);
		return kparams;
	}

}

