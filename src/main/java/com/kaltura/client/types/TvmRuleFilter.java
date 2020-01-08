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
// Copyright (C) 2006-2020  Kaltura Inc.
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
import com.kaltura.client.enums.TvmRuleType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset user rule filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TvmRuleFilter.Tokenizer.class)
public class TvmRuleFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String ruleTypeEqual();
		String nameEqual();
	}

	/**
	 * Indicates which tvm rule list to return by their type.
	 */
	private TvmRuleType ruleTypeEqual;
	/**
	 * Indicates which tvm rule list to return by their name.
	 */
	private String nameEqual;

	// ruleTypeEqual:
	public TvmRuleType getRuleTypeEqual(){
		return this.ruleTypeEqual;
	}
	public void setRuleTypeEqual(TvmRuleType ruleTypeEqual){
		this.ruleTypeEqual = ruleTypeEqual;
	}

	public void ruleTypeEqual(String multirequestToken){
		setToken("ruleTypeEqual", multirequestToken);
	}

	// nameEqual:
	public String getNameEqual(){
		return this.nameEqual;
	}
	public void setNameEqual(String nameEqual){
		this.nameEqual = nameEqual;
	}

	public void nameEqual(String multirequestToken){
		setToken("nameEqual", multirequestToken);
	}


	public TvmRuleFilter() {
		super();
	}

	public TvmRuleFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		ruleTypeEqual = TvmRuleType.get(GsonParser.parseString(jsonObject.get("ruleTypeEqual")));
		nameEqual = GsonParser.parseString(jsonObject.get("nameEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTvmRuleFilter");
		kparams.add("ruleTypeEqual", this.ruleTypeEqual);
		kparams.add("nameEqual", this.nameEqual);
		return kparams;
	}

}

