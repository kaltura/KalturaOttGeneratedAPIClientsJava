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
@MultiRequestBuilder.Tokenizer(DefaultPlaybackAdapters.Tokenizer.class)
public class DefaultPlaybackAdapters extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String mediaAdapterId();
		String epgAdapterId();
		String recordingAdapterId();
	}

	/**
	 * Default adapter identifier for media
	 */
	private Long mediaAdapterId;
	/**
	 * Default adapter identifier for epg
	 */
	private Long epgAdapterId;
	/**
	 * Default adapter identifier for recording
	 */
	private Long recordingAdapterId;

	// mediaAdapterId:
	public Long getMediaAdapterId(){
		return this.mediaAdapterId;
	}
	public void setMediaAdapterId(Long mediaAdapterId){
		this.mediaAdapterId = mediaAdapterId;
	}

	public void mediaAdapterId(String multirequestToken){
		setToken("mediaAdapterId", multirequestToken);
	}

	// epgAdapterId:
	public Long getEpgAdapterId(){
		return this.epgAdapterId;
	}
	public void setEpgAdapterId(Long epgAdapterId){
		this.epgAdapterId = epgAdapterId;
	}

	public void epgAdapterId(String multirequestToken){
		setToken("epgAdapterId", multirequestToken);
	}

	// recordingAdapterId:
	public Long getRecordingAdapterId(){
		return this.recordingAdapterId;
	}
	public void setRecordingAdapterId(Long recordingAdapterId){
		this.recordingAdapterId = recordingAdapterId;
	}

	public void recordingAdapterId(String multirequestToken){
		setToken("recordingAdapterId", multirequestToken);
	}


	public DefaultPlaybackAdapters() {
		super();
	}

	public DefaultPlaybackAdapters(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		mediaAdapterId = GsonParser.parseLong(jsonObject.get("mediaAdapterId"));
		epgAdapterId = GsonParser.parseLong(jsonObject.get("epgAdapterId"));
		recordingAdapterId = GsonParser.parseLong(jsonObject.get("recordingAdapterId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaDefaultPlaybackAdapters");
		kparams.add("mediaAdapterId", this.mediaAdapterId);
		kparams.add("epgAdapterId", this.epgAdapterId);
		kparams.add("recordingAdapterId", this.recordingAdapterId);
		return kparams;
	}

}

