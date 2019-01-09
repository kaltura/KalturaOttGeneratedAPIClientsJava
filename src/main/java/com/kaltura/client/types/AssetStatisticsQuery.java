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
import com.kaltura.client.enums.AssetType;
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
@MultiRequestBuilder.Tokenizer(AssetStatisticsQuery.Tokenizer.class)
public class AssetStatisticsQuery extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetIdIn();
		String assetTypeEqual();
		String startDateGreaterThanOrEqual();
		String endDateGreaterThanOrEqual();
	}

	/**
	 * Comma separated list of asset identifiers.
	 */
	private String assetIdIn;
	/**
	 * Asset type
	 */
	private AssetType assetTypeEqual;
	/**
	 * The beginning of the time window to get the statistics for (in epoch).
	 */
	private Long startDateGreaterThanOrEqual;
	/**
	 * /// The end of the time window to get the statistics for (in epoch).
	 */
	private Long endDateGreaterThanOrEqual;

	// assetIdIn:
	public String getAssetIdIn(){
		return this.assetIdIn;
	}
	public void setAssetIdIn(String assetIdIn){
		this.assetIdIn = assetIdIn;
	}

	public void assetIdIn(String multirequestToken){
		setToken("assetIdIn", multirequestToken);
	}

	// assetTypeEqual:
	public AssetType getAssetTypeEqual(){
		return this.assetTypeEqual;
	}
	public void setAssetTypeEqual(AssetType assetTypeEqual){
		this.assetTypeEqual = assetTypeEqual;
	}

	public void assetTypeEqual(String multirequestToken){
		setToken("assetTypeEqual", multirequestToken);
	}

	// startDateGreaterThanOrEqual:
	public Long getStartDateGreaterThanOrEqual(){
		return this.startDateGreaterThanOrEqual;
	}
	public void setStartDateGreaterThanOrEqual(Long startDateGreaterThanOrEqual){
		this.startDateGreaterThanOrEqual = startDateGreaterThanOrEqual;
	}

	public void startDateGreaterThanOrEqual(String multirequestToken){
		setToken("startDateGreaterThanOrEqual", multirequestToken);
	}

	// endDateGreaterThanOrEqual:
	public Long getEndDateGreaterThanOrEqual(){
		return this.endDateGreaterThanOrEqual;
	}
	public void setEndDateGreaterThanOrEqual(Long endDateGreaterThanOrEqual){
		this.endDateGreaterThanOrEqual = endDateGreaterThanOrEqual;
	}

	public void endDateGreaterThanOrEqual(String multirequestToken){
		setToken("endDateGreaterThanOrEqual", multirequestToken);
	}


	public AssetStatisticsQuery() {
		super();
	}

	public AssetStatisticsQuery(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetIdIn = GsonParser.parseString(jsonObject.get("assetIdIn"));
		assetTypeEqual = AssetType.get(GsonParser.parseString(jsonObject.get("assetTypeEqual")));
		startDateGreaterThanOrEqual = GsonParser.parseLong(jsonObject.get("startDateGreaterThanOrEqual"));
		endDateGreaterThanOrEqual = GsonParser.parseLong(jsonObject.get("endDateGreaterThanOrEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetStatisticsQuery");
		kparams.add("assetIdIn", this.assetIdIn);
		kparams.add("assetTypeEqual", this.assetTypeEqual);
		kparams.add("startDateGreaterThanOrEqual", this.startDateGreaterThanOrEqual);
		kparams.add("endDateGreaterThanOrEqual", this.endDateGreaterThanOrEqual);
		return kparams;
	}

}

