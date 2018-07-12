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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Media file details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MediaFile.Tokenizer.class)
public class MediaFile extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetId();
		String id();
		String type();
		String url();
		String duration();
		String externalId();
		String fileSize();
	}

	/**
	 * Unique identifier for the asset
	 */
	private Integer assetId;
	/**
	 * File unique identifier
	 */
	private Integer id;
	/**
	 * Device types as defined in the system
	 */
	private String type;
	/**
	 * URL of the media file to be played
	 */
	private String url;
	/**
	 * Duration of the media file
	 */
	private Long duration;
	/**
	 * External identifier for the media file
	 */
	private String externalId;
	/**
	 * File size
	 */
	private Long fileSize;

	// assetId:
	public Integer getAssetId(){
		return this.assetId;
	}
	public void setAssetId(Integer assetId){
		this.assetId = assetId;
	}

	public void assetId(String multirequestToken){
		setToken("assetId", multirequestToken);
	}

	// id:
	public Integer getId(){
		return this.id;
	}
	// type:
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}

	// url:
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}

	public void url(String multirequestToken){
		setToken("url", multirequestToken);
	}

	// duration:
	public Long getDuration(){
		return this.duration;
	}
	public void setDuration(Long duration){
		this.duration = duration;
	}

	public void duration(String multirequestToken){
		setToken("duration", multirequestToken);
	}

	// externalId:
	public String getExternalId(){
		return this.externalId;
	}
	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public void externalId(String multirequestToken){
		setToken("externalId", multirequestToken);
	}

	// fileSize:
	public Long getFileSize(){
		return this.fileSize;
	}
	public void setFileSize(Long fileSize){
		this.fileSize = fileSize;
	}

	public void fileSize(String multirequestToken){
		setToken("fileSize", multirequestToken);
	}


	public MediaFile() {
		super();
	}

	public MediaFile(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetId = GsonParser.parseInt(jsonObject.get("assetId"));
		id = GsonParser.parseInt(jsonObject.get("id"));
		type = GsonParser.parseString(jsonObject.get("type"));
		url = GsonParser.parseString(jsonObject.get("url"));
		duration = GsonParser.parseLong(jsonObject.get("duration"));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		fileSize = GsonParser.parseLong(jsonObject.get("fileSize"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMediaFile");
		kparams.add("assetId", this.assetId);
		kparams.add("type", this.type);
		kparams.add("url", this.url);
		kparams.add("duration", this.duration);
		kparams.add("externalId", this.externalId);
		kparams.add("fileSize", this.fileSize);
		return kparams;
	}

}

