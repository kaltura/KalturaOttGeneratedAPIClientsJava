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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A class representing content recommendations.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TreeRecommendations.Tokenizer.class)
public class TreeRecommendations extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String title();
		RequestBuilder.ListTokenizer<Asset.Tokenizer> assets();
	}

	/**
	 * Descriptive title for the recommendation set.
	 */
	private String title;
	/**
	 * Array of content assets matching the recommendation criteria, this is
	  essentially a KalturaAssetListResponseObject.
	 */
	private List<Asset> assets;

	// title:
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public void title(String multirequestToken){
		setToken("title", multirequestToken);
	}

	// assets:
	public List<Asset> getAssets(){
		return this.assets;
	}
	public void setAssets(List<Asset> assets){
		this.assets = assets;
	}


	public TreeRecommendations() {
		super();
	}

	public TreeRecommendations(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		title = GsonParser.parseString(jsonObject.get("title"));
		assets = GsonParser.parseArray(jsonObject.getAsJsonArray("assets"), Asset.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTreeRecommendations");
		kparams.add("title", this.title);
		kparams.add("assets", this.assets);
		return kparams;
	}

}

