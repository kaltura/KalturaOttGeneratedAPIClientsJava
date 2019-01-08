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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SeriesReminder.Tokenizer.class)
public class SeriesReminder extends Reminder {
	
	public interface Tokenizer extends Reminder.Tokenizer {
		String seriesId();
		String seasonNumber();
		String epgChannelId();
	}

	/**
	 * Series identifier
	 */
	private String seriesId;
	/**
	 * Season number
	 */
	private Long seasonNumber;
	/**
	 * EPG channel identifier
	 */
	private Long epgChannelId;

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
	public Long getSeasonNumber(){
		return this.seasonNumber;
	}
	public void setSeasonNumber(Long seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	public void seasonNumber(String multirequestToken){
		setToken("seasonNumber", multirequestToken);
	}

	// epgChannelId:
	public Long getEpgChannelId(){
		return this.epgChannelId;
	}
	public void setEpgChannelId(Long epgChannelId){
		this.epgChannelId = epgChannelId;
	}

	public void epgChannelId(String multirequestToken){
		setToken("epgChannelId", multirequestToken);
	}


	public SeriesReminder() {
		super();
	}

	public SeriesReminder(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		seriesId = GsonParser.parseString(jsonObject.get("seriesId"));
		seasonNumber = GsonParser.parseLong(jsonObject.get("seasonNumber"));
		epgChannelId = GsonParser.parseLong(jsonObject.get("epgChannelId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSeriesReminder");
		kparams.add("seriesId", this.seriesId);
		kparams.add("seasonNumber", this.seasonNumber);
		kparams.add("epgChannelId", this.epgChannelId);
		return kparams;
	}

}

