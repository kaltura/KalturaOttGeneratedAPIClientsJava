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
import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionType;
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
@MultiRequestBuilder.Tokenizer(ProductPrice.Tokenizer.class)
public abstract class ProductPrice extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String productId();
		String productType();
		Price.Tokenizer price();
		String purchaseStatus();
	}

	/**
	 * Product identifier
	 */
	private String productId;
	/**
	 * Product Type
	 */
	private TransactionType productType;
	/**
	 * Product price
	 */
	private Price price;
	/**
	 * Product purchase status
	 */
	private PurchaseStatus purchaseStatus;

	// productId:
	public String getProductId(){
		return this.productId;
	}
	public void setProductId(String productId){
		this.productId = productId;
	}

	public void productId(String multirequestToken){
		setToken("productId", multirequestToken);
	}

	// productType:
	public TransactionType getProductType(){
		return this.productType;
	}
	public void setProductType(TransactionType productType){
		this.productType = productType;
	}

	public void productType(String multirequestToken){
		setToken("productType", multirequestToken);
	}

	// price:
	public Price getPrice(){
		return this.price;
	}
	public void setPrice(Price price){
		this.price = price;
	}

	// purchaseStatus:
	public PurchaseStatus getPurchaseStatus(){
		return this.purchaseStatus;
	}
	public void setPurchaseStatus(PurchaseStatus purchaseStatus){
		this.purchaseStatus = purchaseStatus;
	}

	public void purchaseStatus(String multirequestToken){
		setToken("purchaseStatus", multirequestToken);
	}


	public ProductPrice() {
		super();
	}

	public ProductPrice(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		productId = GsonParser.parseString(jsonObject.get("productId"));
		productType = TransactionType.get(GsonParser.parseString(jsonObject.get("productType")));
		price = GsonParser.parseObject(jsonObject.getAsJsonObject("price"), Price.class);
		purchaseStatus = PurchaseStatus.get(GsonParser.parseString(jsonObject.get("purchaseStatus")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaProductPrice");
		kparams.add("productId", this.productId);
		kparams.add("productType", this.productType);
		kparams.add("price", this.price);
		kparams.add("purchaseStatus", this.purchaseStatus);
		return kparams;
	}

}

