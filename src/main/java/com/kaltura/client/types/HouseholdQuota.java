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

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdQuota.Tokenizer.class)
public class HouseholdQuota extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String householdId();
		String totalQuota();
		String availableQuota();
	}

	/**
	 * Household identifier
	 */
	private Long householdId;
	/**
	 * Total quota that is allocated to the household
	 */
	private Integer totalQuota;
	/**
	 * Available quota that household has remaining
	 */
	private Integer availableQuota;

	// householdId:
	public Long getHouseholdId(){
		return this.householdId;
	}
	// totalQuota:
	public Integer getTotalQuota(){
		return this.totalQuota;
	}
	// availableQuota:
	public Integer getAvailableQuota(){
		return this.availableQuota;
	}

	public HouseholdQuota() {
		super();
	}

	public HouseholdQuota(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		householdId = GsonParser.parseLong(jsonObject.get("householdId"));
		totalQuota = GsonParser.parseInt(jsonObject.get("totalQuota"));
		availableQuota = GsonParser.parseInt(jsonObject.get("availableQuota"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdQuota");
		return kparams;
	}

}

