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
import com.kaltura.client.enums.MediaFileStreamerType;
import com.kaltura.client.enums.MediaFileTypeQuality;
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
 * Media-file type
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MediaFileType.Tokenizer.class)
public class MediaFileType extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String description();
		String status();
		String createDate();
		String updateDate();
		String isTrailer();
		String streamerType();
		String drmProfileId();
		String quality();
		String videoCodecs();
		String audioCodecs();
	}

	/**
	 * Unique identifier
	 */
	private Integer id;
	/**
	 * Unique name
	 */
	private String name;
	/**
	 * Unique description
	 */
	private String description;
	/**
	 * Indicates if media-file type is active or disabled
	 */
	private Boolean status;
	/**
	 * Specifies when was the type was created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the type last updated. Date and time represented as epoch.
	 */
	private Long updateDate;
	/**
	 * Specifies whether playback as trailer is allowed
	 */
	private Boolean isTrailer;
	/**
	 * Defines playback streamer type
	 */
	private MediaFileStreamerType streamerType;
	/**
	 * DRM adapter-profile identifier, use -1 for uDRM, 0 for no DRM.
	 */
	private Integer drmProfileId;
	/**
	 * Media file type quality
	 */
	private MediaFileTypeQuality quality;
	/**
	 * List of comma separated video codecs
	 */
	private String videoCodecs;
	/**
	 * List of comma separated audio codecs
	 */
	private String audioCodecs;

	// id:
	public Integer getId(){
		return this.id;
	}
	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public void description(String multirequestToken){
		setToken("description", multirequestToken);
	}

	// status:
	public Boolean getStatus(){
		return this.status;
	}
	public void setStatus(Boolean status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// isTrailer:
	public Boolean getIsTrailer(){
		return this.isTrailer;
	}
	public void setIsTrailer(Boolean isTrailer){
		this.isTrailer = isTrailer;
	}

	public void isTrailer(String multirequestToken){
		setToken("isTrailer", multirequestToken);
	}

	// streamerType:
	public MediaFileStreamerType getStreamerType(){
		return this.streamerType;
	}
	public void setStreamerType(MediaFileStreamerType streamerType){
		this.streamerType = streamerType;
	}

	public void streamerType(String multirequestToken){
		setToken("streamerType", multirequestToken);
	}

	// drmProfileId:
	public Integer getDrmProfileId(){
		return this.drmProfileId;
	}
	public void setDrmProfileId(Integer drmProfileId){
		this.drmProfileId = drmProfileId;
	}

	public void drmProfileId(String multirequestToken){
		setToken("drmProfileId", multirequestToken);
	}

	// quality:
	public MediaFileTypeQuality getQuality(){
		return this.quality;
	}
	public void setQuality(MediaFileTypeQuality quality){
		this.quality = quality;
	}

	public void quality(String multirequestToken){
		setToken("quality", multirequestToken);
	}

	// videoCodecs:
	public String getVideoCodecs(){
		return this.videoCodecs;
	}
	public void setVideoCodecs(String videoCodecs){
		this.videoCodecs = videoCodecs;
	}

	public void videoCodecs(String multirequestToken){
		setToken("videoCodecs", multirequestToken);
	}

	// audioCodecs:
	public String getAudioCodecs(){
		return this.audioCodecs;
	}
	public void setAudioCodecs(String audioCodecs){
		this.audioCodecs = audioCodecs;
	}

	public void audioCodecs(String multirequestToken){
		setToken("audioCodecs", multirequestToken);
	}


	public MediaFileType() {
		super();
	}

	public MediaFileType(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		description = GsonParser.parseString(jsonObject.get("description"));
		status = GsonParser.parseBoolean(jsonObject.get("status"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		isTrailer = GsonParser.parseBoolean(jsonObject.get("isTrailer"));
		streamerType = MediaFileStreamerType.get(GsonParser.parseString(jsonObject.get("streamerType")));
		drmProfileId = GsonParser.parseInt(jsonObject.get("drmProfileId"));
		quality = MediaFileTypeQuality.get(GsonParser.parseString(jsonObject.get("quality")));
		videoCodecs = GsonParser.parseString(jsonObject.get("videoCodecs"));
		audioCodecs = GsonParser.parseString(jsonObject.get("audioCodecs"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMediaFileType");
		kparams.add("name", this.name);
		kparams.add("description", this.description);
		kparams.add("status", this.status);
		kparams.add("isTrailer", this.isTrailer);
		kparams.add("streamerType", this.streamerType);
		kparams.add("drmProfileId", this.drmProfileId);
		kparams.add("quality", this.quality);
		kparams.add("videoCodecs", this.videoCodecs);
		kparams.add("audioCodecs", this.audioCodecs);
		return kparams;
	}

}

