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
import com.kaltura.client.enums.MathemticalOperatorType;
import com.kaltura.client.enums.MonetizationType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Defines a condition based on monetization actions.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MonetizationCondition.Tokenizer.class)
public class MonetizationCondition extends BaseSegmentCondition {
	
	public interface Tokenizer extends BaseSegmentCondition.Tokenizer {
		String businessModuleIdIn();
		String currencyCode();
		String days();
		String maxValue();
		String minValue();
		String operator();
		String type();
	}

	/**
	 * A comma-separated list of business module IDs to include in the filter.
	 */
	private String businessModuleIdIn;
	/**
	 * The ISO 4217 currency code to filter by.
	 */
	private String currencyCode;
	/**
	 * The number of days to look back for monetization actions.
	 */
	private Integer days;
	/**
	 * The maximum allowable value for the calculated metric.              MinValue
	  must be greater than or equal to MaxValue.
	 */
	private Integer maxValue;
	/**
	 * The minimum required value for the calculated metric.              MinValue must
	  be less than or equal to MaxValue.
	 */
	private Integer minValue;
	/**
	 * The aggregation method used to calculate the value (e.g., counting transactions,
	  summing amounts).
	 */
	private MathemticalOperatorType operator;
	/**
	 * The specific monetization type to filter by.
	 */
	private MonetizationType type;

	// businessModuleIdIn:
	public String getBusinessModuleIdIn(){
		return this.businessModuleIdIn;
	}
	public void setBusinessModuleIdIn(String businessModuleIdIn){
		this.businessModuleIdIn = businessModuleIdIn;
	}

	public void businessModuleIdIn(String multirequestToken){
		setToken("businessModuleIdIn", multirequestToken);
	}

	// currencyCode:
	public String getCurrencyCode(){
		return this.currencyCode;
	}
	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public void currencyCode(String multirequestToken){
		setToken("currencyCode", multirequestToken);
	}

	// days:
	public Integer getDays(){
		return this.days;
	}
	public void setDays(Integer days){
		this.days = days;
	}

	public void days(String multirequestToken){
		setToken("days", multirequestToken);
	}

	// maxValue:
	public Integer getMaxValue(){
		return this.maxValue;
	}
	public void setMaxValue(Integer maxValue){
		this.maxValue = maxValue;
	}

	public void maxValue(String multirequestToken){
		setToken("maxValue", multirequestToken);
	}

	// minValue:
	public Integer getMinValue(){
		return this.minValue;
	}
	public void setMinValue(Integer minValue){
		this.minValue = minValue;
	}

	public void minValue(String multirequestToken){
		setToken("minValue", multirequestToken);
	}

	// operator:
	public MathemticalOperatorType getOperator(){
		return this.operator;
	}
	public void setOperator(MathemticalOperatorType operator){
		this.operator = operator;
	}

	public void operator(String multirequestToken){
		setToken("operator", multirequestToken);
	}

	// type:
	public MonetizationType getType(){
		return this.type;
	}
	public void setType(MonetizationType type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}


	public MonetizationCondition() {
		super();
	}

	public MonetizationCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		businessModuleIdIn = GsonParser.parseString(jsonObject.get("businessModuleIdIn"));
		currencyCode = GsonParser.parseString(jsonObject.get("currencyCode"));
		days = GsonParser.parseInt(jsonObject.get("days"));
		maxValue = GsonParser.parseInt(jsonObject.get("maxValue"));
		minValue = GsonParser.parseInt(jsonObject.get("minValue"));
		operator = MathemticalOperatorType.get(GsonParser.parseString(jsonObject.get("operator")));
		type = MonetizationType.get(GsonParser.parseString(jsonObject.get("type")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMonetizationCondition");
		kparams.add("businessModuleIdIn", this.businessModuleIdIn);
		kparams.add("currencyCode", this.currencyCode);
		kparams.add("days", this.days);
		kparams.add("maxValue", this.maxValue);
		kparams.add("minValue", this.minValue);
		kparams.add("operator", this.operator);
		kparams.add("type", this.type);
		return kparams;
	}

}

