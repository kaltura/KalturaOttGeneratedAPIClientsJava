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
 * Monetization based source (purchases etc.)
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MonetizationSource.Tokenizer.class)
public class MonetizationSource extends SegmentSource {
	
	public interface Tokenizer extends SegmentSource.Tokenizer {
		String type();
		String operator();
		String days();
	}

	/**
	 * Purchase type
	 */
	private MonetizationType type;
	/**
	 * Mathermtical operator to calculate
	 */
	private MathemticalOperatorType operator;
	/**
	 * Days to consider when checking the users&amp;#39; purchaes
	 */
	private Integer days;

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


	public MonetizationSource() {
		super();
	}

	public MonetizationSource(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		type = MonetizationType.get(GsonParser.parseString(jsonObject.get("type")));
		operator = MathemticalOperatorType.get(GsonParser.parseString(jsonObject.get("operator")));
		days = GsonParser.parseInt(jsonObject.get("days"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMonetizationSource");
		kparams.add("type", this.type);
		kparams.add("operator", this.operator);
		kparams.add("days", this.days);
		return kparams;
	}

}

