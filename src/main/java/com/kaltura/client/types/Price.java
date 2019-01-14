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

/**
 * Price
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Price.Tokenizer.class)
public class Price extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String amount();
		String currency();
		String currencySign();
		String countryId();
	}

	/**
	 * Price
	 */
	private Double amount;
	/**
	 * Currency
	 */
	private String currency;
	/**
	 * Currency Sign
	 */
	private String currencySign;
	/**
	 * Country ID
	 */
	private Long countryId;

	// amount:
	public Double getAmount(){
		return this.amount;
	}
	public void setAmount(Double amount){
		this.amount = amount;
	}

	public void amount(String multirequestToken){
		setToken("amount", multirequestToken);
	}

	// currency:
	public String getCurrency(){
		return this.currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public void currency(String multirequestToken){
		setToken("currency", multirequestToken);
	}

	// currencySign:
	public String getCurrencySign(){
		return this.currencySign;
	}
	public void setCurrencySign(String currencySign){
		this.currencySign = currencySign;
	}

	public void currencySign(String multirequestToken){
		setToken("currencySign", multirequestToken);
	}

	// countryId:
	public Long getCountryId(){
		return this.countryId;
	}
	public void setCountryId(Long countryId){
		this.countryId = countryId;
	}

	public void countryId(String multirequestToken){
		setToken("countryId", multirequestToken);
	}


	public Price() {
		super();
	}

	public Price(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		amount = GsonParser.parseDouble(jsonObject.get("amount"));
		currency = GsonParser.parseString(jsonObject.get("currency"));
		currencySign = GsonParser.parseString(jsonObject.get("currencySign"));
		countryId = GsonParser.parseLong(jsonObject.get("countryId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPrice");
		kparams.add("amount", this.amount);
		kparams.add("currency", this.currency);
		kparams.add("currencySign", this.currencySign);
		kparams.add("countryId", this.countryId);
		return kparams;
	}

}

