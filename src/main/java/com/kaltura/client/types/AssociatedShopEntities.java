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
 * This type will be used in KalturaFilter searches to filter entities by shop
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssociatedShopEntities.Tokenizer.class)
public class AssociatedShopEntities extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetUserRuleIdIn();
		String includeNullAssetUserRuleId();
	}

	/**
	 * comma-separated list of assetUserRuleId values. Matching entities will be
	  returned by the filter.
	 */
	private String assetUserRuleIdIn;
	/**
	 * If true, filter will return entities with null/empty assetUserRuleId value, in
	  addition to any entities whose assetUserRuleId value matches the
	  assetUserRuleIdIn parameter.              If false (or field is not specified)
	  filter will return only entities whose assetUserRuleId value matches the
	  assetUserRuleIdIn parameter.
	 */
	private Boolean includeNullAssetUserRuleId;

	// assetUserRuleIdIn:
	public String getAssetUserRuleIdIn(){
		return this.assetUserRuleIdIn;
	}
	public void setAssetUserRuleIdIn(String assetUserRuleIdIn){
		this.assetUserRuleIdIn = assetUserRuleIdIn;
	}

	public void assetUserRuleIdIn(String multirequestToken){
		setToken("assetUserRuleIdIn", multirequestToken);
	}

	// includeNullAssetUserRuleId:
	public Boolean getIncludeNullAssetUserRuleId(){
		return this.includeNullAssetUserRuleId;
	}
	public void setIncludeNullAssetUserRuleId(Boolean includeNullAssetUserRuleId){
		this.includeNullAssetUserRuleId = includeNullAssetUserRuleId;
	}

	public void includeNullAssetUserRuleId(String multirequestToken){
		setToken("includeNullAssetUserRuleId", multirequestToken);
	}


	public AssociatedShopEntities() {
		super();
	}

	public AssociatedShopEntities(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetUserRuleIdIn = GsonParser.parseString(jsonObject.get("assetUserRuleIdIn"));
		includeNullAssetUserRuleId = GsonParser.parseBoolean(jsonObject.get("includeNullAssetUserRuleId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssociatedShopEntities");
		kparams.add("assetUserRuleIdIn", this.assetUserRuleIdIn);
		kparams.add("includeNullAssetUserRuleId", this.includeNullAssetUserRuleId);
		return kparams;
	}

}

