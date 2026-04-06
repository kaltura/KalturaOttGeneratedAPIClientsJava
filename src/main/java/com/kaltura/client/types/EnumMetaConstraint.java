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
 * Filters assets based on an enum metadata field matching one of the values.      
         Attempting to create KalturaEnumMetaConstraint for key that is not type
  of Enum will fail.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EnumMetaConstraint.Tokenizer.class)
public class EnumMetaConstraint extends BaseAttributeConstraint {
	
	public interface Tokenizer extends BaseAttributeConstraint.Tokenizer {
		String oneOf();
	}

	/**
	 * A comma-separated list of values. The metadata field enum&amp;#39;s values must
	  match at least one of these items.
	 */
	private String oneOf;

	// oneOf:
	public String getOneOf(){
		return this.oneOf;
	}
	public void setOneOf(String oneOf){
		this.oneOf = oneOf;
	}

	public void oneOf(String multirequestToken){
		setToken("oneOf", multirequestToken);
	}


	public EnumMetaConstraint() {
		super();
	}

	public EnumMetaConstraint(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		oneOf = GsonParser.parseString(jsonObject.get("oneOf"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEnumMetaConstraint");
		kparams.add("oneOf", this.oneOf);
		return kparams;
	}

}

