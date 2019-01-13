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
import com.kaltura.client.enums.MathemticalOperatorType;
import com.kaltura.client.enums.MonetizationType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Defines a condition which is essentially a combination of several
  monetization-based actions, each has their own score multiplier
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MonetizationCondition.Tokenizer.class)
public class MonetizationCondition extends BaseSegmentCondition {
	
	public interface Tokenizer extends BaseSegmentCondition.Tokenizer {
		String minValue();
		String maxValue();
		String days();
		String type();
		String operator();
	}

	/**
	 * The minimum value to be met
	 */
	private Integer minValue;
	/**
	 * The maximum value to be met
	 */
	private Integer maxValue;
	/**
	 * How many days back should the actions be considered
	 */
	private Integer days;
	/**
	 * Purchase type
	 */
	private MonetizationType type;
	/**
	 * Mathermtical operator to calculate
	 */
	private MathemticalOperatorType operator;

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


	public MonetizationCondition() {
		super();
	}

	public MonetizationCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		minValue = GsonParser.parseInt(jsonObject.get("minValue"));
		maxValue = GsonParser.parseInt(jsonObject.get("maxValue"));
		days = GsonParser.parseInt(jsonObject.get("days"));
		type = MonetizationType.get(GsonParser.parseString(jsonObject.get("type")));
		operator = MathemticalOperatorType.get(GsonParser.parseString(jsonObject.get("operator")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMonetizationCondition");
		kparams.add("minValue", this.minValue);
		kparams.add("maxValue", this.maxValue);
		kparams.add("days", this.days);
		kparams.add("type", this.type);
		kparams.add("operator", this.operator);
		return kparams;
	}

}

