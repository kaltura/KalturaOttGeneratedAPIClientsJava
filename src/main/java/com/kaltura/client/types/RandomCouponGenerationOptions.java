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
@MultiRequestBuilder.Tokenizer(RandomCouponGenerationOptions.Tokenizer.class)
public class RandomCouponGenerationOptions extends CouponGenerationOptions {
	
	public interface Tokenizer extends CouponGenerationOptions.Tokenizer {
		String numberOfCoupons();
		String useLetters();
		String useNumbers();
		String useSpecialCharacters();
	}

	/**
	 * Number of coupons to generate
	 */
	private Integer numberOfCoupons;
	/**
	 * Indicates whether to use letters in the generated codes (default is true)
	 */
	private Boolean useLetters;
	/**
	 * Indicates whether to use numbers in the generated codes (default is true)
	 */
	private Boolean useNumbers;
	/**
	 * Indicates whether to use special characters in the generated codes(default is
	  true)
	 */
	private Boolean useSpecialCharacters;

	// numberOfCoupons:
	public Integer getNumberOfCoupons(){
		return this.numberOfCoupons;
	}
	public void setNumberOfCoupons(Integer numberOfCoupons){
		this.numberOfCoupons = numberOfCoupons;
	}

	public void numberOfCoupons(String multirequestToken){
		setToken("numberOfCoupons", multirequestToken);
	}

	// useLetters:
	public Boolean getUseLetters(){
		return this.useLetters;
	}
	public void setUseLetters(Boolean useLetters){
		this.useLetters = useLetters;
	}

	public void useLetters(String multirequestToken){
		setToken("useLetters", multirequestToken);
	}

	// useNumbers:
	public Boolean getUseNumbers(){
		return this.useNumbers;
	}
	public void setUseNumbers(Boolean useNumbers){
		this.useNumbers = useNumbers;
	}

	public void useNumbers(String multirequestToken){
		setToken("useNumbers", multirequestToken);
	}

	// useSpecialCharacters:
	public Boolean getUseSpecialCharacters(){
		return this.useSpecialCharacters;
	}
	public void setUseSpecialCharacters(Boolean useSpecialCharacters){
		this.useSpecialCharacters = useSpecialCharacters;
	}

	public void useSpecialCharacters(String multirequestToken){
		setToken("useSpecialCharacters", multirequestToken);
	}


	public RandomCouponGenerationOptions() {
		super();
	}

	public RandomCouponGenerationOptions(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		numberOfCoupons = GsonParser.parseInt(jsonObject.get("numberOfCoupons"));
		useLetters = GsonParser.parseBoolean(jsonObject.get("useLetters"));
		useNumbers = GsonParser.parseBoolean(jsonObject.get("useNumbers"));
		useSpecialCharacters = GsonParser.parseBoolean(jsonObject.get("useSpecialCharacters"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRandomCouponGenerationOptions");
		kparams.add("numberOfCoupons", this.numberOfCoupons);
		kparams.add("useLetters", this.useLetters);
		kparams.add("useNumbers", this.useNumbers);
		kparams.add("useSpecialCharacters", this.useSpecialCharacters);
		return kparams;
	}

}

