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
@MultiRequestBuilder.Tokenizer(SeasonsReminderFilter.Tokenizer.class)
public class SeasonsReminderFilter extends ReminderFilter {
	
	public interface Tokenizer extends ReminderFilter.Tokenizer {
		String seriesIdEqual();
		String seasonNumberIn();
		String epgChannelIdEqual();
	}

	/**
	 * Series ID
	 */
	private String seriesIdEqual;
	/**
	 * Comma separated season numbers
	 */
	private String seasonNumberIn;
	/**
	 * EPG channel ID
	 */
	private Long epgChannelIdEqual;

	// seriesIdEqual:
	public String getSeriesIdEqual(){
		return this.seriesIdEqual;
	}
	public void setSeriesIdEqual(String seriesIdEqual){
		this.seriesIdEqual = seriesIdEqual;
	}

	public void seriesIdEqual(String multirequestToken){
		setToken("seriesIdEqual", multirequestToken);
	}

	// seasonNumberIn:
	public String getSeasonNumberIn(){
		return this.seasonNumberIn;
	}
	public void setSeasonNumberIn(String seasonNumberIn){
		this.seasonNumberIn = seasonNumberIn;
	}

	public void seasonNumberIn(String multirequestToken){
		setToken("seasonNumberIn", multirequestToken);
	}

	// epgChannelIdEqual:
	public Long getEpgChannelIdEqual(){
		return this.epgChannelIdEqual;
	}
	public void setEpgChannelIdEqual(Long epgChannelIdEqual){
		this.epgChannelIdEqual = epgChannelIdEqual;
	}

	public void epgChannelIdEqual(String multirequestToken){
		setToken("epgChannelIdEqual", multirequestToken);
	}


	public SeasonsReminderFilter() {
		super();
	}

	public SeasonsReminderFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		seriesIdEqual = GsonParser.parseString(jsonObject.get("seriesIdEqual"));
		seasonNumberIn = GsonParser.parseString(jsonObject.get("seasonNumberIn"));
		epgChannelIdEqual = GsonParser.parseLong(jsonObject.get("epgChannelIdEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSeasonsReminderFilter");
		kparams.add("seriesIdEqual", this.seriesIdEqual);
		kparams.add("seasonNumberIn", this.seasonNumberIn);
		kparams.add("epgChannelIdEqual", this.epgChannelIdEqual);
		return kparams;
	}

}

