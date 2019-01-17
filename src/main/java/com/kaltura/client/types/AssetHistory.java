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

/**
 * Watch history asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetHistory.Tokenizer.class)
public class AssetHistory extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetId();
		String assetType();
		String position();
		String duration();
		String watchedDate();
		String finishedWatching();
	}

	/**
	 * Asset identifier
	 */
	private Long assetId;
	/**
	 * Asset identifier
	 */
	private AssetType assetType;
	/**
	 * Position in seconds of the relevant asset
	 */
	private Integer position;
	/**
	 * Duration in seconds of the relevant asset
	 */
	private Integer duration;
	/**
	 * The date when the media was last watched
	 */
	private Long watchedDate;
	/**
	 * Boolean which specifies whether the user finished watching the movie or not
	 */
	private Boolean finishedWatching;

	// assetId:
	public Long getAssetId(){
		return this.assetId;
	}
	// assetType:
	public AssetType getAssetType(){
		return this.assetType;
	}
	// position:
	public Integer getPosition(){
		return this.position;
	}
	// duration:
	public Integer getDuration(){
		return this.duration;
	}
	// watchedDate:
	public Long getWatchedDate(){
		return this.watchedDate;
	}
	// finishedWatching:
	public Boolean getFinishedWatching(){
		return this.finishedWatching;
	}

	public AssetHistory() {
		super();
	}

	public AssetHistory(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetId = GsonParser.parseLong(jsonObject.get("assetId"));
		assetType = AssetType.get(GsonParser.parseString(jsonObject.get("assetType")));
		position = GsonParser.parseInt(jsonObject.get("position"));
		duration = GsonParser.parseInt(jsonObject.get("duration"));
		watchedDate = GsonParser.parseLong(jsonObject.get("watchedDate"));
		finishedWatching = GsonParser.parseBoolean(jsonObject.get("finishedWatching"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetHistory");
		return kparams;
	}

}

