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
// Copyright (C) 2006-2020  Kaltura Inc.
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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Recording-asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(RecordingAsset.Tokenizer.class)
public class RecordingAsset extends ProgramAsset {
	
	public interface Tokenizer extends ProgramAsset.Tokenizer {
		String recordingId();
		String recordingType();
	}

	/**
	 * Recording identifier
	 */
	private String recordingId;
	/**
	 * Recording Type: single/season/series
	 */
	private RecordingType recordingType;

	// recordingId:
	public String getRecordingId(){
		return this.recordingId;
	}
	public void setRecordingId(String recordingId){
		this.recordingId = recordingId;
	}

	public void recordingId(String multirequestToken){
		setToken("recordingId", multirequestToken);
	}

	// recordingType:
	public RecordingType getRecordingType(){
		return this.recordingType;
	}
	public void setRecordingType(RecordingType recordingType){
		this.recordingType = recordingType;
	}

	public void recordingType(String multirequestToken){
		setToken("recordingType", multirequestToken);
	}


	public RecordingAsset() {
		super();
	}

	public RecordingAsset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		recordingId = GsonParser.parseString(jsonObject.get("recordingId"));
		recordingType = RecordingType.get(GsonParser.parseString(jsonObject.get("recordingType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRecordingAsset");
		kparams.add("recordingId", this.recordingId);
		kparams.add("recordingType", this.recordingType);
		return kparams;
	}

}

