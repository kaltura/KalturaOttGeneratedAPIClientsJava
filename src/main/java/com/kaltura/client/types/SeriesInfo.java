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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SeriesInfo.Tokenizer.class)
public class SeriesInfo extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String seriesIdMetadataName();
		String seriesTypeId();
		String episodeTypeId();
	}

	/**
	 * Series ID meta name
	 */
	private String seriesIdMetadataName;
	/**
	 * Series asset type ID
	 */
	private Long seriesTypeId;
	/**
	 * Episode asset type ID
	 */
	private Long episodeTypeId;

	// seriesIdMetadataName:
	public String getSeriesIdMetadataName(){
		return this.seriesIdMetadataName;
	}
	public void setSeriesIdMetadataName(String seriesIdMetadataName){
		this.seriesIdMetadataName = seriesIdMetadataName;
	}

	public void seriesIdMetadataName(String multirequestToken){
		setToken("seriesIdMetadataName", multirequestToken);
	}

	// seriesTypeId:
	public Long getSeriesTypeId(){
		return this.seriesTypeId;
	}
	public void setSeriesTypeId(Long seriesTypeId){
		this.seriesTypeId = seriesTypeId;
	}

	public void seriesTypeId(String multirequestToken){
		setToken("seriesTypeId", multirequestToken);
	}

	// episodeTypeId:
	public Long getEpisodeTypeId(){
		return this.episodeTypeId;
	}
	public void setEpisodeTypeId(Long episodeTypeId){
		this.episodeTypeId = episodeTypeId;
	}

	public void episodeTypeId(String multirequestToken){
		setToken("episodeTypeId", multirequestToken);
	}


	public SeriesInfo() {
		super();
	}

	public SeriesInfo(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		seriesIdMetadataName = GsonParser.parseString(jsonObject.get("seriesIdMetadataName"));
		seriesTypeId = GsonParser.parseLong(jsonObject.get("seriesTypeId"));
		episodeTypeId = GsonParser.parseLong(jsonObject.get("episodeTypeId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSeriesInfo");
		kparams.add("seriesIdMetadataName", this.seriesIdMetadataName);
		kparams.add("seriesTypeId", this.seriesTypeId);
		kparams.add("episodeTypeId", this.episodeTypeId);
		return kparams;
	}

}

