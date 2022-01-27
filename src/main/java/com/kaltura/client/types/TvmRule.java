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
// Copyright (C) 2006-2021  Kaltura Inc.
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
 * TVM rule
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TvmRule.Tokenizer.class)
public abstract class TvmRule extends Rule {
	
	public interface Tokenizer extends Rule.Tokenizer {
		String createDate();
		String ruleType();
	}

	/**
	 * Specifies when was the tvm rule was created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies the tvm rule type.
	 */
	private TvmRuleType ruleType;

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// ruleType:
	public TvmRuleType getRuleType(){
		return this.ruleType;
	}

	public TvmRule() {
		super();
	}

	public TvmRule(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		ruleType = TvmRuleType.get(GsonParser.parseString(jsonObject.get("ruleType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTvmRule");
		return kparams;
	}

}

