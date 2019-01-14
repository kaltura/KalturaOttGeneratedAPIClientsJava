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
import com.kaltura.client.enums.ScheduledRecordingAssetType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ScheduledRecordingProgramFilter.Tokenizer.class)
public class ScheduledRecordingProgramFilter extends AssetFilter {
	
	public interface Tokenizer extends AssetFilter.Tokenizer {
		String recordingTypeEqual();
		String channelsIn();
		String startDateGreaterThanOrNull();
		String endDateLessThanOrNull();
	}

	/**
	 * The type of recordings to return
	 */
	private ScheduledRecordingAssetType recordingTypeEqual;
	/**
	 * Channels to filter by
	 */
	private String channelsIn;
	/**
	 * start date
	 */
	private Long startDateGreaterThanOrNull;
	/**
	 * end date
	 */
	private Long endDateLessThanOrNull;

	// recordingTypeEqual:
	public ScheduledRecordingAssetType getRecordingTypeEqual(){
		return this.recordingTypeEqual;
	}
	public void setRecordingTypeEqual(ScheduledRecordingAssetType recordingTypeEqual){
		this.recordingTypeEqual = recordingTypeEqual;
	}

	public void recordingTypeEqual(String multirequestToken){
		setToken("recordingTypeEqual", multirequestToken);
	}

	// channelsIn:
	public String getChannelsIn(){
		return this.channelsIn;
	}
	public void setChannelsIn(String channelsIn){
		this.channelsIn = channelsIn;
	}

	public void channelsIn(String multirequestToken){
		setToken("channelsIn", multirequestToken);
	}

	// startDateGreaterThanOrNull:
	public Long getStartDateGreaterThanOrNull(){
		return this.startDateGreaterThanOrNull;
	}
	public void setStartDateGreaterThanOrNull(Long startDateGreaterThanOrNull){
		this.startDateGreaterThanOrNull = startDateGreaterThanOrNull;
	}

	public void startDateGreaterThanOrNull(String multirequestToken){
		setToken("startDateGreaterThanOrNull", multirequestToken);
	}

	// endDateLessThanOrNull:
	public Long getEndDateLessThanOrNull(){
		return this.endDateLessThanOrNull;
	}
	public void setEndDateLessThanOrNull(Long endDateLessThanOrNull){
		this.endDateLessThanOrNull = endDateLessThanOrNull;
	}

	public void endDateLessThanOrNull(String multirequestToken){
		setToken("endDateLessThanOrNull", multirequestToken);
	}


	public ScheduledRecordingProgramFilter() {
		super();
	}

	public ScheduledRecordingProgramFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		recordingTypeEqual = ScheduledRecordingAssetType.get(GsonParser.parseString(jsonObject.get("recordingTypeEqual")));
		channelsIn = GsonParser.parseString(jsonObject.get("channelsIn"));
		startDateGreaterThanOrNull = GsonParser.parseLong(jsonObject.get("startDateGreaterThanOrNull"));
		endDateLessThanOrNull = GsonParser.parseLong(jsonObject.get("endDateLessThanOrNull"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaScheduledRecordingProgramFilter");
		kparams.add("recordingTypeEqual", this.recordingTypeEqual);
		kparams.add("channelsIn", this.channelsIn);
		kparams.add("startDateGreaterThanOrNull", this.startDateGreaterThanOrNull);
		kparams.add("endDateLessThanOrNull", this.endDateLessThanOrNull);
		return kparams;
	}

}

