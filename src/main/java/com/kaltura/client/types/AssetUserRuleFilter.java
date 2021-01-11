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
import com.kaltura.client.enums.RuleActionType;
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
@MultiRequestBuilder.Tokenizer(AssetUserRuleFilter.Tokenizer.class)
public class AssetUserRuleFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String attachedUserIdEqualCurrent();
		String actionsContainType();
	}

	/**
	 * Indicates if to get the asset user rule list for the attached user or for the
	  entire group
	 */
	private Boolean attachedUserIdEqualCurrent;
	/**
	 * Indicates which asset rule list to return by this KalturaRuleActionType.
	 */
	private RuleActionType actionsContainType;

	// attachedUserIdEqualCurrent:
	public Boolean getAttachedUserIdEqualCurrent(){
		return this.attachedUserIdEqualCurrent;
	}
	public void setAttachedUserIdEqualCurrent(Boolean attachedUserIdEqualCurrent){
		this.attachedUserIdEqualCurrent = attachedUserIdEqualCurrent;
	}

	public void attachedUserIdEqualCurrent(String multirequestToken){
		setToken("attachedUserIdEqualCurrent", multirequestToken);
	}

	// actionsContainType:
	public RuleActionType getActionsContainType(){
		return this.actionsContainType;
	}
	public void setActionsContainType(RuleActionType actionsContainType){
		this.actionsContainType = actionsContainType;
	}

	public void actionsContainType(String multirequestToken){
		setToken("actionsContainType", multirequestToken);
	}


	public AssetUserRuleFilter() {
		super();
	}

	public AssetUserRuleFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		attachedUserIdEqualCurrent = GsonParser.parseBoolean(jsonObject.get("attachedUserIdEqualCurrent"));
		actionsContainType = RuleActionType.get(GsonParser.parseString(jsonObject.get("actionsContainType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetUserRuleFilter");
		kparams.add("attachedUserIdEqualCurrent", this.attachedUserIdEqualCurrent);
		kparams.add("actionsContainType", this.actionsContainType);
		return kparams;
	}

}

