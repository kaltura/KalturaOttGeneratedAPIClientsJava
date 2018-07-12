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
import com.kaltura.client.enums.RuleConditionType;
import com.kaltura.client.types.SlimAsset;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset rule filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetRuleFilter.Tokenizer.class)
public class AssetRuleFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String conditionsContainType();
		SlimAsset.Tokenizer assetApplied();
	}

	/**
	 * Indicates which asset rule list to return by it KalturaRuleConditionType.       
	        Default value: KalturaRuleConditionType.COUNTRY
	 */
	private RuleConditionType conditionsContainType;
	/**
	 * Indicates if to return an asset rule list that related to specific asset
	 */
	private SlimAsset assetApplied;

	// conditionsContainType:
	public RuleConditionType getConditionsContainType(){
		return this.conditionsContainType;
	}
	public void setConditionsContainType(RuleConditionType conditionsContainType){
		this.conditionsContainType = conditionsContainType;
	}

	public void conditionsContainType(String multirequestToken){
		setToken("conditionsContainType", multirequestToken);
	}

	// assetApplied:
	public SlimAsset getAssetApplied(){
		return this.assetApplied;
	}
	public void setAssetApplied(SlimAsset assetApplied){
		this.assetApplied = assetApplied;
	}


	public AssetRuleFilter() {
		super();
	}

	public AssetRuleFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		conditionsContainType = RuleConditionType.get(GsonParser.parseString(jsonObject.get("conditionsContainType")));
		assetApplied = GsonParser.parseObject(jsonObject.getAsJsonObject("assetApplied"), SlimAsset.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetRuleFilter");
		kparams.add("conditionsContainType", this.conditionsContainType);
		kparams.add("assetApplied", this.assetApplied);
		return kparams;
	}

}

