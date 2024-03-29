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
import com.kaltura.client.enums.AssetRuleStatus;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset rule
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetRule.Tokenizer.class)
public class AssetRule extends AssetRuleBase {
	
	public interface Tokenizer extends AssetRuleBase.Tokenizer {
		RequestBuilder.ListTokenizer<Condition.Tokenizer> conditions();
		RequestBuilder.ListTokenizer<AssetRuleAction.Tokenizer> actions();
		String status();
	}

	/**
	 * List of conditions for the rule
	 */
	private List<Condition> conditions;
	/**
	 * List of actions for the rule
	 */
	private List<AssetRuleAction> actions;
	/**
	 * List of actions for the rule
	 */
	private AssetRuleStatus status;

	// conditions:
	public List<Condition> getConditions(){
		return this.conditions;
	}
	public void setConditions(List<Condition> conditions){
		this.conditions = conditions;
	}

	// actions:
	public List<AssetRuleAction> getActions(){
		return this.actions;
	}
	public void setActions(List<AssetRuleAction> actions){
		this.actions = actions;
	}

	// status:
	public AssetRuleStatus getStatus(){
		return this.status;
	}

	public AssetRule() {
		super();
	}

	public AssetRule(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		conditions = GsonParser.parseArray(jsonObject.getAsJsonArray("conditions"), Condition.class);
		actions = GsonParser.parseArray(jsonObject.getAsJsonArray("actions"), AssetRuleAction.class);
		status = AssetRuleStatus.get(GsonParser.parseString(jsonObject.get("status")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetRule");
		kparams.add("conditions", this.conditions);
		kparams.add("actions", this.actions);
		return kparams;
	}

}

