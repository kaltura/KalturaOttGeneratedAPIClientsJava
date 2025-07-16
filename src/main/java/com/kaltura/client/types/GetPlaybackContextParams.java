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
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.types.PlaybackContextOptions;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Parameters for a single getPlaybackContext request within a bulk request
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GetPlaybackContextParams.Tokenizer.class)
public class GetPlaybackContextParams extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetId();
		String assetType();
		PlaybackContextOptions.Tokenizer contextDataParams();
		String sourceType();
	}

	/**
	 * Unique identifier of the asset
	 */
	private String assetId;
	/**
	 * Type of the asset
	 */
	private AssetType assetType;
	/**
	 * Playback context options
	 */
	private PlaybackContextOptions contextDataParams;
	/**
	 * Source type (optional)
	 */
	private String sourceType;

	// assetId:
	public String getAssetId(){
		return this.assetId;
	}
	public void setAssetId(String assetId){
		this.assetId = assetId;
	}

	public void assetId(String multirequestToken){
		setToken("assetId", multirequestToken);
	}

	// assetType:
	public AssetType getAssetType(){
		return this.assetType;
	}
	public void setAssetType(AssetType assetType){
		this.assetType = assetType;
	}

	public void assetType(String multirequestToken){
		setToken("assetType", multirequestToken);
	}

	// contextDataParams:
	public PlaybackContextOptions getContextDataParams(){
		return this.contextDataParams;
	}
	public void setContextDataParams(PlaybackContextOptions contextDataParams){
		this.contextDataParams = contextDataParams;
	}

	// sourceType:
	public String getSourceType(){
		return this.sourceType;
	}
	public void setSourceType(String sourceType){
		this.sourceType = sourceType;
	}

	public void sourceType(String multirequestToken){
		setToken("sourceType", multirequestToken);
	}


	public GetPlaybackContextParams() {
		super();
	}

	public GetPlaybackContextParams(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetId = GsonParser.parseString(jsonObject.get("assetId"));
		assetType = AssetType.get(GsonParser.parseString(jsonObject.get("assetType")));
		contextDataParams = GsonParser.parseObject(jsonObject.getAsJsonObject("contextDataParams"), PlaybackContextOptions.class);
		sourceType = GsonParser.parseString(jsonObject.get("sourceType"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGetPlaybackContextParams");
		kparams.add("assetId", this.assetId);
		kparams.add("assetType", this.assetType);
		kparams.add("contextDataParams", this.contextDataParams);
		kparams.add("sourceType", this.sourceType);
		return kparams;
	}

}

