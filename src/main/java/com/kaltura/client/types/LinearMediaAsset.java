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
import com.kaltura.client.enums.LinearChannelType;
import com.kaltura.client.enums.TimeShiftedTvState;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Linear media asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(LinearMediaAsset.Tokenizer.class)
public class LinearMediaAsset extends MediaAsset {
	
	public interface Tokenizer extends MediaAsset.Tokenizer {
		String enableCdvrState();
		String enableCatchUpState();
		String enableStartOverState();
		String bufferCatchUp();
		String bufferTrickPlay();
		String enableRecordingPlaybackNonEntitledChannelState();
		String enableTrickPlayState();
		String externalEpgIngestId();
		String externalCdvrId();
		String cdvrEnabled();
		String catchUpEnabled();
		String startOverEnabled();
		String summedCatchUpBuffer();
		String summedTrickPlayBuffer();
		String recordingPlaybackNonEntitledChannelEnabled();
		String trickPlayEnabled();
		String channelType();
	}

	/**
	 * Enable CDVR, configuration only
	 */
	private TimeShiftedTvState enableCdvrState;
	/**
	 * Enable catch-up, configuration only
	 */
	private TimeShiftedTvState enableCatchUpState;
	/**
	 * Enable start over, configuration only
	 */
	private TimeShiftedTvState enableStartOverState;
	/**
	 * buffer Catch-up, configuration only
	 */
	private Long bufferCatchUp;
	/**
	 * buffer Trick-play, configuration only
	 */
	private Long bufferTrickPlay;
	/**
	 * Enable Recording playback for non entitled channel, configuration only
	 */
	private TimeShiftedTvState enableRecordingPlaybackNonEntitledChannelState;
	/**
	 * Enable trick-play, configuration only
	 */
	private TimeShiftedTvState enableTrickPlayState;
	/**
	 * External identifier used when ingesting programs for this linear media asset
	 */
	private String externalEpgIngestId;
	/**
	 * External identifier for the CDVR
	 */
	private String externalCdvrId;
	/**
	 * Is CDVR enabled for this asset
	 */
	private Boolean cdvrEnabled;
	/**
	 * Is catch-up enabled for this asset
	 */
	private Boolean catchUpEnabled;
	/**
	 * Is start over enabled for this asset
	 */
	private Boolean startOverEnabled;
	/**
	 * summed Catch-up buffer, the TimeShiftedTvPartnerSettings are also taken into
	  consideration
	 */
	private Long summedCatchUpBuffer;
	/**
	 * summed Trick-play buffer, the TimeShiftedTvPartnerSettings are also taken into
	  consideration
	 */
	private Long summedTrickPlayBuffer;
	/**
	 * Is recording playback for non entitled channel enabled for this asset
	 */
	private Boolean recordingPlaybackNonEntitledChannelEnabled;
	/**
	 * Is trick-play enabled for this asset
	 */
	private Boolean trickPlayEnabled;
	/**
	 * channel type, possible values: UNKNOWN, DTT, OTT, DTT_AND_OTT
	 */
	private LinearChannelType channelType;

	// enableCdvrState:
	public TimeShiftedTvState getEnableCdvrState(){
		return this.enableCdvrState;
	}
	public void setEnableCdvrState(TimeShiftedTvState enableCdvrState){
		this.enableCdvrState = enableCdvrState;
	}

	public void enableCdvrState(String multirequestToken){
		setToken("enableCdvrState", multirequestToken);
	}

	// enableCatchUpState:
	public TimeShiftedTvState getEnableCatchUpState(){
		return this.enableCatchUpState;
	}
	public void setEnableCatchUpState(TimeShiftedTvState enableCatchUpState){
		this.enableCatchUpState = enableCatchUpState;
	}

	public void enableCatchUpState(String multirequestToken){
		setToken("enableCatchUpState", multirequestToken);
	}

	// enableStartOverState:
	public TimeShiftedTvState getEnableStartOverState(){
		return this.enableStartOverState;
	}
	public void setEnableStartOverState(TimeShiftedTvState enableStartOverState){
		this.enableStartOverState = enableStartOverState;
	}

	public void enableStartOverState(String multirequestToken){
		setToken("enableStartOverState", multirequestToken);
	}

	// bufferCatchUp:
	public Long getBufferCatchUp(){
		return this.bufferCatchUp;
	}
	public void setBufferCatchUp(Long bufferCatchUp){
		this.bufferCatchUp = bufferCatchUp;
	}

	public void bufferCatchUp(String multirequestToken){
		setToken("bufferCatchUp", multirequestToken);
	}

	// bufferTrickPlay:
	public Long getBufferTrickPlay(){
		return this.bufferTrickPlay;
	}
	public void setBufferTrickPlay(Long bufferTrickPlay){
		this.bufferTrickPlay = bufferTrickPlay;
	}

	public void bufferTrickPlay(String multirequestToken){
		setToken("bufferTrickPlay", multirequestToken);
	}

	// enableRecordingPlaybackNonEntitledChannelState:
	public TimeShiftedTvState getEnableRecordingPlaybackNonEntitledChannelState(){
		return this.enableRecordingPlaybackNonEntitledChannelState;
	}
	public void setEnableRecordingPlaybackNonEntitledChannelState(TimeShiftedTvState enableRecordingPlaybackNonEntitledChannelState){
		this.enableRecordingPlaybackNonEntitledChannelState = enableRecordingPlaybackNonEntitledChannelState;
	}

	public void enableRecordingPlaybackNonEntitledChannelState(String multirequestToken){
		setToken("enableRecordingPlaybackNonEntitledChannelState", multirequestToken);
	}

	// enableTrickPlayState:
	public TimeShiftedTvState getEnableTrickPlayState(){
		return this.enableTrickPlayState;
	}
	public void setEnableTrickPlayState(TimeShiftedTvState enableTrickPlayState){
		this.enableTrickPlayState = enableTrickPlayState;
	}

	public void enableTrickPlayState(String multirequestToken){
		setToken("enableTrickPlayState", multirequestToken);
	}

	// externalEpgIngestId:
	public String getExternalEpgIngestId(){
		return this.externalEpgIngestId;
	}
	public void setExternalEpgIngestId(String externalEpgIngestId){
		this.externalEpgIngestId = externalEpgIngestId;
	}

	public void externalEpgIngestId(String multirequestToken){
		setToken("externalEpgIngestId", multirequestToken);
	}

	// externalCdvrId:
	public String getExternalCdvrId(){
		return this.externalCdvrId;
	}
	public void setExternalCdvrId(String externalCdvrId){
		this.externalCdvrId = externalCdvrId;
	}

	public void externalCdvrId(String multirequestToken){
		setToken("externalCdvrId", multirequestToken);
	}

	// cdvrEnabled:
	public Boolean getCdvrEnabled(){
		return this.cdvrEnabled;
	}
	// catchUpEnabled:
	public Boolean getCatchUpEnabled(){
		return this.catchUpEnabled;
	}
	// startOverEnabled:
	public Boolean getStartOverEnabled(){
		return this.startOverEnabled;
	}
	// summedCatchUpBuffer:
	public Long getSummedCatchUpBuffer(){
		return this.summedCatchUpBuffer;
	}
	// summedTrickPlayBuffer:
	public Long getSummedTrickPlayBuffer(){
		return this.summedTrickPlayBuffer;
	}
	// recordingPlaybackNonEntitledChannelEnabled:
	public Boolean getRecordingPlaybackNonEntitledChannelEnabled(){
		return this.recordingPlaybackNonEntitledChannelEnabled;
	}
	// trickPlayEnabled:
	public Boolean getTrickPlayEnabled(){
		return this.trickPlayEnabled;
	}
	// channelType:
	public LinearChannelType getChannelType(){
		return this.channelType;
	}
	public void setChannelType(LinearChannelType channelType){
		this.channelType = channelType;
	}

	public void channelType(String multirequestToken){
		setToken("channelType", multirequestToken);
	}


	public LinearMediaAsset() {
		super();
	}

	public LinearMediaAsset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		enableCdvrState = TimeShiftedTvState.get(GsonParser.parseString(jsonObject.get("enableCdvrState")));
		enableCatchUpState = TimeShiftedTvState.get(GsonParser.parseString(jsonObject.get("enableCatchUpState")));
		enableStartOverState = TimeShiftedTvState.get(GsonParser.parseString(jsonObject.get("enableStartOverState")));
		bufferCatchUp = GsonParser.parseLong(jsonObject.get("bufferCatchUp"));
		bufferTrickPlay = GsonParser.parseLong(jsonObject.get("bufferTrickPlay"));
		enableRecordingPlaybackNonEntitledChannelState = TimeShiftedTvState.get(GsonParser.parseString(jsonObject.get("enableRecordingPlaybackNonEntitledChannelState")));
		enableTrickPlayState = TimeShiftedTvState.get(GsonParser.parseString(jsonObject.get("enableTrickPlayState")));
		externalEpgIngestId = GsonParser.parseString(jsonObject.get("externalEpgIngestId"));
		externalCdvrId = GsonParser.parseString(jsonObject.get("externalCdvrId"));
		cdvrEnabled = GsonParser.parseBoolean(jsonObject.get("cdvrEnabled"));
		catchUpEnabled = GsonParser.parseBoolean(jsonObject.get("catchUpEnabled"));
		startOverEnabled = GsonParser.parseBoolean(jsonObject.get("startOverEnabled"));
		summedCatchUpBuffer = GsonParser.parseLong(jsonObject.get("summedCatchUpBuffer"));
		summedTrickPlayBuffer = GsonParser.parseLong(jsonObject.get("summedTrickPlayBuffer"));
		recordingPlaybackNonEntitledChannelEnabled = GsonParser.parseBoolean(jsonObject.get("recordingPlaybackNonEntitledChannelEnabled"));
		trickPlayEnabled = GsonParser.parseBoolean(jsonObject.get("trickPlayEnabled"));
		channelType = LinearChannelType.get(GsonParser.parseString(jsonObject.get("channelType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLinearMediaAsset");
		kparams.add("enableCdvrState", this.enableCdvrState);
		kparams.add("enableCatchUpState", this.enableCatchUpState);
		kparams.add("enableStartOverState", this.enableStartOverState);
		kparams.add("bufferCatchUp", this.bufferCatchUp);
		kparams.add("bufferTrickPlay", this.bufferTrickPlay);
		kparams.add("enableRecordingPlaybackNonEntitledChannelState", this.enableRecordingPlaybackNonEntitledChannelState);
		kparams.add("enableTrickPlayState", this.enableTrickPlayState);
		kparams.add("externalEpgIngestId", this.externalEpgIngestId);
		kparams.add("externalCdvrId", this.externalCdvrId);
		kparams.add("channelType", this.channelType);
		return kparams;
	}

}

