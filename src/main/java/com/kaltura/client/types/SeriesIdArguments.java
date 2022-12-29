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
// Copyright (C) 2006-2022  Kaltura Inc.
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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SeriesIdArguments.Tokenizer.class)
public class SeriesIdArguments extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetTypeIdIn();
		String seriesId();
		String seriesIdMetaName();
		String seasonNumberMetaName();
		String episodeNumberMetaName();
	}

	/**
	 * Comma separated asset type IDs
	 */
	private String assetTypeIdIn;
	/**
	 * Series ID
	 */
	private String seriesId;
	/**
	 * Series ID meta name.
	 */
	private String seriesIdMetaName;
	/**
	 * Season number meta name
	 */
	private String seasonNumberMetaName;
	/**
	 * Episode number meta name
	 */
	private String episodeNumberMetaName;

	// assetTypeIdIn:
	public String getAssetTypeIdIn(){
		return this.assetTypeIdIn;
	}
	public void setAssetTypeIdIn(String assetTypeIdIn){
		this.assetTypeIdIn = assetTypeIdIn;
	}

	public void assetTypeIdIn(String multirequestToken){
		setToken("assetTypeIdIn", multirequestToken);
	}

	// seriesId:
	public String getSeriesId(){
		return this.seriesId;
	}
	public void setSeriesId(String seriesId){
		this.seriesId = seriesId;
	}

	public void seriesId(String multirequestToken){
		setToken("seriesId", multirequestToken);
	}

	// seriesIdMetaName:
	public String getSeriesIdMetaName(){
		return this.seriesIdMetaName;
	}
	public void setSeriesIdMetaName(String seriesIdMetaName){
		this.seriesIdMetaName = seriesIdMetaName;
	}

	public void seriesIdMetaName(String multirequestToken){
		setToken("seriesIdMetaName", multirequestToken);
	}

	// seasonNumberMetaName:
	public String getSeasonNumberMetaName(){
		return this.seasonNumberMetaName;
	}
	public void setSeasonNumberMetaName(String seasonNumberMetaName){
		this.seasonNumberMetaName = seasonNumberMetaName;
	}

	public void seasonNumberMetaName(String multirequestToken){
		setToken("seasonNumberMetaName", multirequestToken);
	}

	// episodeNumberMetaName:
	public String getEpisodeNumberMetaName(){
		return this.episodeNumberMetaName;
	}
	public void setEpisodeNumberMetaName(String episodeNumberMetaName){
		this.episodeNumberMetaName = episodeNumberMetaName;
	}

	public void episodeNumberMetaName(String multirequestToken){
		setToken("episodeNumberMetaName", multirequestToken);
	}


	public SeriesIdArguments() {
		super();
	}

	public SeriesIdArguments(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetTypeIdIn = GsonParser.parseString(jsonObject.get("assetTypeIdIn"));
		seriesId = GsonParser.parseString(jsonObject.get("seriesId"));
		seriesIdMetaName = GsonParser.parseString(jsonObject.get("seriesIdMetaName"));
		seasonNumberMetaName = GsonParser.parseString(jsonObject.get("seasonNumberMetaName"));
		episodeNumberMetaName = GsonParser.parseString(jsonObject.get("episodeNumberMetaName"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSeriesIdArguments");
		kparams.add("assetTypeIdIn", this.assetTypeIdIn);
		kparams.add("seriesId", this.seriesId);
		kparams.add("seriesIdMetaName", this.seriesIdMetaName);
		kparams.add("seasonNumberMetaName", this.seasonNumberMetaName);
		kparams.add("episodeNumberMetaName", this.episodeNumberMetaName);
		return kparams;
	}

}

