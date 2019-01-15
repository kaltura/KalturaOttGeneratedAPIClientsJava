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
import com.kaltura.client.enums.SkipOperators;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Skips current request according to condition on given property
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PropertySkipCondition.Tokenizer.class)
public class PropertySkipCondition extends SkipCondition {
	
	public interface Tokenizer extends SkipCondition.Tokenizer {
		String propertyPath();
		String operator();
		String value();
	}

	/**
	 * The property path on which the condition is checked
	 */
	private String propertyPath;
	/**
	 * The operator that applies the check to the condition
	 */
	private SkipOperators operator;
	/**
	 * The value on which the condition is checked
	 */
	private String value;

	// propertyPath:
	public String getPropertyPath(){
		return this.propertyPath;
	}
	public void setPropertyPath(String propertyPath){
		this.propertyPath = propertyPath;
	}

	public void propertyPath(String multirequestToken){
		setToken("propertyPath", multirequestToken);
	}

	// operator:
	public SkipOperators getOperator(){
		return this.operator;
	}
	public void setOperator(SkipOperators operator){
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


	public PropertySkipCondition() {
		super();
	}

	public PropertySkipCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		propertyPath = GsonParser.parseString(jsonObject.get("propertyPath"));
		operator = SkipOperators.get(GsonParser.parseString(jsonObject.get("operator")));
		value = GsonParser.parseString(jsonObject.get("value"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPropertySkipCondition");
		kparams.add("propertyPath", this.propertyPath);
		kparams.add("operator", this.operator);
		kparams.add("value", this.value);
		return kparams;
	}

}

