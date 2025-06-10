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
import com.kaltura.client.enums.SubtitlesType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A class representing the properties of an uploaded subtitles file.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Subtitles.Tokenizer.class)
public class Subtitles extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String createDate();
		String fileName();
		String detectedType();
		String language();
	}

	/**
	 * Unique identifier for the subtitles file.
	 */
	private Long id;
	/**
	 * Specifies when the file was uploaded, expressed in Epoch timestamp.
	 */
	private Long createDate;
	/**
	 * Name of the uploaded subtitles text file.
	 */
	private String fileName;
	/**
	 * The content type included in the subtitles file, as auto-detected by the
	  subtitles service. Can be of SRT, WebVTT or free text without cues.
	 */
	private SubtitlesType detectedType;
	/**
	 * The language used for the subtitles.
	 */
	private String language;

	// id:
	public Long getId(){
		return this.id;
	}
	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// fileName:
	public String getFileName(){
		return this.fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public void fileName(String multirequestToken){
		setToken("fileName", multirequestToken);
	}

	// detectedType:
	public SubtitlesType getDetectedType(){
		return this.detectedType;
	}
	public void setDetectedType(SubtitlesType detectedType){
		this.detectedType = detectedType;
	}

	public void detectedType(String multirequestToken){
		setToken("detectedType", multirequestToken);
	}

	// language:
	public String getLanguage(){
		return this.language;
	}
	public void setLanguage(String language){
		this.language = language;
	}

	public void language(String multirequestToken){
		setToken("language", multirequestToken);
	}


	public Subtitles() {
		super();
	}

	public Subtitles(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		fileName = GsonParser.parseString(jsonObject.get("fileName"));
		detectedType = SubtitlesType.get(GsonParser.parseString(jsonObject.get("detectedType")));
		language = GsonParser.parseString(jsonObject.get("language"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubtitles");
		kparams.add("fileName", this.fileName);
		kparams.add("detectedType", this.detectedType);
		kparams.add("language", this.language);
		return kparams;
	}

}

