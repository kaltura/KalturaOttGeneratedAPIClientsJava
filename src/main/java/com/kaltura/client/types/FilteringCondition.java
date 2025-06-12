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
import com.kaltura.client.enums.ConditionOperator;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Represents a filtering condition used in Kaltura&amp;#39;s search and query
  functionalities.              This class defines a condition based on a metadata
  attribute, an operator, and a comparison value.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(FilteringCondition.Tokenizer.class)
public class FilteringCondition extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String metaName();
		String operator();
		String value();
	}

	/**
	 * The name of the metadata attribute to apply the filtering condition on.
	 */
	private String metaName;
	/**
	 * The operator defining how the value should be compared (e.g., Equal, NotEqual).
	 */
	private ConditionOperator operator;
	/**
	 * The value to compare against the metadata attribute using the specified
	  operator.
	 */
	private String value;

	// metaName:
	public String getMetaName(){
		return this.metaName;
	}
	public void setMetaName(String metaName){
		this.metaName = metaName;
	}

	public void metaName(String multirequestToken){
		setToken("metaName", multirequestToken);
	}

	// operator:
	public ConditionOperator getOperator(){
		return this.operator;
	}
	public void setOperator(ConditionOperator operator){
		this.operator = operator;
	}

	public void operator(String multirequestToken){
		setToken("operator", multirequestToken);
	}

	// value:
	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}

	public void value(String multirequestToken){
		setToken("value", multirequestToken);
	}


	public FilteringCondition() {
		super();
	}

	public FilteringCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		metaName = GsonParser.parseString(jsonObject.get("metaName"));
		operator = ConditionOperator.get(GsonParser.parseString(jsonObject.get("operator")));
		value = GsonParser.parseString(jsonObject.get("value"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaFilteringCondition");
		kparams.add("metaName", this.metaName);
		kparams.add("operator", this.operator);
		kparams.add("value", this.value);
		return kparams;
	}

}

