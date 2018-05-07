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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset rule
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetRule.Tokenizer.class)
public class AssetRule extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String description();
		RequestBuilder.ListTokenizer<Condition.Tokenizer> conditions();
		RequestBuilder.ListTokenizer<RuleAction.Tokenizer> actions();
	}

	/**
	 * ID
	 */
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Description
	 */
	private String description;
	/**
	 * List of conditions for the rule
	 */
	private List<Condition> conditions;
	/**
	 * List of actions for the rule
	 */
	private List<RuleAction> actions;

	// id:
	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
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

	// conditions:
	public List<Condition> getConditions(){
		return this.conditions;
	}
	public void setConditions(List<Condition> conditions){
		this.conditions = conditions;
	}

	// actions:
	public List<RuleAction> getActions(){
		return this.actions;
	}
	public void setActions(List<RuleAction> actions){
		this.actions = actions;
	}


	public AssetRule() {
		super();
	}

	public AssetRule(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		description = GsonParser.parseString(jsonObject.get("description"));
		conditions = GsonParser.parseArray(jsonObject.getAsJsonArray("conditions"), Condition.class);
		actions = GsonParser.parseArray(jsonObject.getAsJsonArray("actions"), RuleAction.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetRule");
		kparams.add("name", this.name);
		kparams.add("description", this.description);
		kparams.add("conditions", this.conditions);
		kparams.add("actions", this.actions);
		return kparams;
	}

}

