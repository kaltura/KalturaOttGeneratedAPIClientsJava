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
import com.kaltura.client.enums.RuleType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * User asset rule - representing different type of rules on an asset(Parental,
  Geo, User Type, Device)
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserAssetRule.Tokenizer.class)
public class UserAssetRule extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String ruleType();
		String name();
		String description();
	}

	/**
	 * Unique rule identifier
	 */
	private Long id;
	/**
	 * Rule type - possible values: Rule type – Parental, Geo, UserType, Device
	 */
	private RuleType ruleType;
	/**
	 * Rule display name
	 */
	private String name;
	/**
	 * Additional description for the specific rule
	 */
	private String description;

	// id:
	public Long getId(){
		return this.id;
	}
	// ruleType:
	public RuleType getRuleType(){
		return this.ruleType;
	}
	public void setRuleType(RuleType ruleType){
		this.ruleType = ruleType;
	}

	public void ruleType(String multirequestToken){
		setToken("ruleType", multirequestToken);
	}

	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public void description(String multirequestToken){
		setToken("description", multirequestToken);
	}


	public UserAssetRule() {
		super();
	}

	public UserAssetRule(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		ruleType = RuleType.get(GsonParser.parseString(jsonObject.get("ruleType")));
		name = GsonParser.parseString(jsonObject.get("name"));
		description = GsonParser.parseString(jsonObject.get("description"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserAssetRule");
		kparams.add("ruleType", this.ruleType);
		kparams.add("name", this.name);
		kparams.add("description", this.description);
		return kparams;
	}

}

