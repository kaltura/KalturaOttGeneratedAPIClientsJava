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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Filters assets based on a date metadata field (epoch) using range comparisons.  
             Attempting to create KalturaDateMetaConstraint for key that is not
  type of Date will fail.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(DateMetaConstraint.Tokenizer.class)
public class DateMetaConstraint extends BaseAttributeConstraint {
	
	public interface Tokenizer extends BaseAttributeConstraint.Tokenizer {
		String equals();
		String greaterThan();
		String smallerThan();
	}

	/**
	 * The exact epoch timestamp the field must equal.
	 */
	private String equals;
	/**
	 * The epoch timestamp the field must be greater than.
	 */
	private String greaterThan;
	/**
	 * The epoch timestamp the field must be smaller than.
	 */
	private String smallerThan;

	// equals:
	public String getEquals(){
		return this.equals;
	}
	public void setEquals(String equals){
		this.equals = equals;
	}

	public void equals(String multirequestToken){
		setToken("equals", multirequestToken);
	}

	// greaterThan:
	public String getGreaterThan(){
		return this.greaterThan;
	}
	public void setGreaterThan(String greaterThan){
		this.greaterThan = greaterThan;
	}

	public void greaterThan(String multirequestToken){
		setToken("greaterThan", multirequestToken);
	}

	// smallerThan:
	public String getSmallerThan(){
		return this.smallerThan;
	}
	public void setSmallerThan(String smallerThan){
		this.smallerThan = smallerThan;
	}

	public void smallerThan(String multirequestToken){
		setToken("smallerThan", multirequestToken);
	}


	public DateMetaConstraint() {
		super();
	}

	public DateMetaConstraint(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		equals = GsonParser.parseString(jsonObject.get("equals"));
		greaterThan = GsonParser.parseString(jsonObject.get("greaterThan"));
		smallerThan = GsonParser.parseString(jsonObject.get("smallerThan"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaDateMetaConstraint");
		kparams.add("equals", this.equals);
		kparams.add("greaterThan", this.greaterThan);
		kparams.add("smallerThan", this.smallerThan);
		return kparams;
	}

}

