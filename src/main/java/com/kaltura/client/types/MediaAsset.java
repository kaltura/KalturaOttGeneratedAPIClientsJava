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

/**
 * Media-asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MediaAsset.Tokenizer.class)
public class MediaAsset extends Asset {
	
	public interface Tokenizer extends Asset.Tokenizer {
		String externalIds();
		String catchUpBuffer();
		String trickPlayBuffer();
		String enableRecordingPlaybackNonEntitledChannel();
		String typeDescription();
		String entryId();
		String deviceRule();
		String geoBlockRule();
		String watchPermissionRule();
	}

	/**
	 * External identifiers
	 */
	private String externalIds;
	/**
	 * Catch-up buffer
	 */
	private Long catchUpBuffer;
	/**
	 * Trick-play buffer
	 */
	private Long trickPlayBuffer;
	/**
	 * Enable Recording playback for non entitled channel
	 */
	private Boolean enableRecordingPlaybackNonEntitledChannel;
	/**
	 * Asset type description
	 */
	private String typeDescription;
	/**
	 * Entry Identifier
	 */
	private String entryId;
	/**
	 * Device rule
	 */
	private String deviceRule;
	/**
	 * Geo block rule
	 */
	private String geoBlockRule;
	/**
	 * Watch permission rule
	 */
	private String watchPermissionRule;

	// externalIds:
	public String getExternalIds(){
		return this.externalIds;
	}
	public void setExternalIds(String externalIds){
		this.externalIds = externalIds;
	}

	public void externalIds(String multirequestToken){
		setToken("externalIds", multirequestToken);
	}

	// catchUpBuffer:
	public Long getCatchUpBuffer(){
		return this.catchUpBuffer;
	}
	public void setCatchUpBuffer(Long catchUpBuffer){
		this.catchUpBuffer = catchUpBuffer;
	}

	public void catchUpBuffer(String multirequestToken){
		setToken("catchUpBuffer", multirequestToken);
	}

	// trickPlayBuffer:
	public Long getTrickPlayBuffer(){
		return this.trickPlayBuffer;
	}
	public void setTrickPlayBuffer(Long trickPlayBuffer){
		this.trickPlayBuffer = trickPlayBuffer;
	}

	public void trickPlayBuffer(String multirequestToken){
		setToken("trickPlayBuffer", multirequestToken);
	}

	// enableRecordingPlaybackNonEntitledChannel:
	public Boolean getEnableRecordingPlaybackNonEntitledChannel(){
		return this.enableRecordingPlaybackNonEntitledChannel;
	}
	// typeDescription:
	public String getTypeDescription(){
		return this.typeDescription;
	}
	public void setTypeDescription(String typeDescription){
		this.typeDescription = typeDescription;
	}

	public void typeDescription(String multirequestToken){
		setToken("typeDescription", multirequestToken);
	}

	// entryId:
	public String getEntryId(){
		return this.entryId;
	}
	public void setEntryId(String entryId){
		this.entryId = entryId;
	}

	public void entryId(String multirequestToken){
		setToken("entryId", multirequestToken);
	}

	// deviceRule:
	public String getDeviceRule(){
		return this.deviceRule;
	}
	public void setDeviceRule(String deviceRule){
		this.deviceRule = deviceRule;
	}

	public void deviceRule(String multirequestToken){
		setToken("deviceRule", multirequestToken);
	}

	// geoBlockRule:
	public String getGeoBlockRule(){
		return this.geoBlockRule;
	}
	public void setGeoBlockRule(String geoBlockRule){
		this.geoBlockRule = geoBlockRule;
	}

	public void geoBlockRule(String multirequestToken){
		setToken("geoBlockRule", multirequestToken);
	}

	// watchPermissionRule:
	public String getWatchPermissionRule(){
		return this.watchPermissionRule;
	}
	public void setWatchPermissionRule(String watchPermissionRule){
		this.watchPermissionRule = watchPermissionRule;
	}

	public void watchPermissionRule(String multirequestToken){
		setToken("watchPermissionRule", multirequestToken);
	}


	public MediaAsset() {
		super();
	}

	public MediaAsset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		externalIds = GsonParser.parseString(jsonObject.get("externalIds"));
		catchUpBuffer = GsonParser.parseLong(jsonObject.get("catchUpBuffer"));
		trickPlayBuffer = GsonParser.parseLong(jsonObject.get("trickPlayBuffer"));
		enableRecordingPlaybackNonEntitledChannel = GsonParser.parseBoolean(jsonObject.get("enableRecordingPlaybackNonEntitledChannel"));
		typeDescription = GsonParser.parseString(jsonObject.get("typeDescription"));
		entryId = GsonParser.parseString(jsonObject.get("entryId"));
		deviceRule = GsonParser.parseString(jsonObject.get("deviceRule"));
		geoBlockRule = GsonParser.parseString(jsonObject.get("geoBlockRule"));
		watchPermissionRule = GsonParser.parseString(jsonObject.get("watchPermissionRule"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMediaAsset");
		kparams.add("externalIds", this.externalIds);
		kparams.add("catchUpBuffer", this.catchUpBuffer);
		kparams.add("trickPlayBuffer", this.trickPlayBuffer);
		kparams.add("typeDescription", this.typeDescription);
		kparams.add("entryId", this.entryId);
		kparams.add("deviceRule", this.deviceRule);
		kparams.add("geoBlockRule", this.geoBlockRule);
		kparams.add("watchPermissionRule", this.watchPermissionRule);
		return kparams;
	}

}

