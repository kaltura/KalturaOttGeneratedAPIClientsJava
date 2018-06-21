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
import com.kaltura.client.enums.RecordingType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SeriesRecording.Tokenizer.class)
public class SeriesRecording extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String epgId();
		String channelId();
		String seriesId();
		String seasonNumber();
		String type();
		String createDate();
		String updateDate();
		RequestBuilder.ListTokenizer<IntegerValue.Tokenizer> excludedSeasons();
	}

	/**
	 * Kaltura unique ID representing the series recording identifier
	 */
	private Long id;
	/**
	 * Kaltura EpgId
	 */
	private Long epgId;
	/**
	 * Kaltura ChannelId
	 */
	private Long channelId;
	/**
	 * Kaltura SeriesId
	 */
	private String seriesId;
	/**
	 * Kaltura SeasonNumber
	 */
	private Integer seasonNumber;
	/**
	 * Recording Type: single/series/season
	 */
	private RecordingType type;
	/**
	 * Specifies when was the series recording created. Date and time represented as
	  epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the series recording last updated. Date and time represented
	  as epoch.
	 */
	private Long updateDate;
	/**
	 * List of the season numbers to exclude.
	 */
	private List<IntegerValue> excludedSeasons;

	// id:
	public Long getId(){
		return this.id;
	}
	// epgId:
	public Long getEpgId(){
		return this.epgId;
	}
	public void setEpgId(Long epgId){
		this.epgId = epgId;
	}

	public void epgId(String multirequestToken){
		setToken("epgId", multirequestToken);
	}

	// channelId:
	public Long getChannelId(){
		return this.channelId;
	}
	public void setChannelId(Long channelId){
		this.channelId = channelId;
	}

	public void channelId(String multirequestToken){
		setToken("channelId", multirequestToken);
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

	// seasonNumber:
	public Integer getSeasonNumber(){
		return this.seasonNumber;
	}
	public void setSeasonNumber(Integer seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	public void seasonNumber(String multirequestToken){
		setToken("seasonNumber", multirequestToken);
	}

	// type:
	public RecordingType getType(){
		return this.type;
	}
	public void setType(RecordingType type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// excludedSeasons:
	public List<IntegerValue> getExcludedSeasons(){
		return this.excludedSeasons;
	}

	public SeriesRecording() {
		super();
	}

	public SeriesRecording(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		epgId = GsonParser.parseLong(jsonObject.get("epgId"));
		channelId = GsonParser.parseLong(jsonObject.get("channelId"));
		seriesId = GsonParser.parseString(jsonObject.get("seriesId"));
		seasonNumber = GsonParser.parseInt(jsonObject.get("seasonNumber"));
		type = RecordingType.get(GsonParser.parseString(jsonObject.get("type")));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		excludedSeasons = GsonParser.parseArray(jsonObject.getAsJsonArray("excludedSeasons"), IntegerValue.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSeriesRecording");
		kparams.add("epgId", this.epgId);
		kparams.add("channelId", this.channelId);
		kparams.add("seriesId", this.seriesId);
		kparams.add("seasonNumber", this.seasonNumber);
		kparams.add("type", this.type);
		return kparams;
	}

}

