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
		String externalIngestId();
		String externalCdvrId();
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
	private String externalIngestId;
	/**
	 * External identifier for the CDVR
	 */
	private String externalCdvrId;

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

	// externalIngestId:
	public String getExternalIngestId(){
		return this.externalIngestId;
	}
	public void setExternalIngestId(String externalIngestId){
		this.externalIngestId = externalIngestId;
	}

	public void externalIngestId(String multirequestToken){
		setToken("externalIngestId", multirequestToken);
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
		externalIngestId = GsonParser.parseString(jsonObject.get("externalIngestId"));
		externalCdvrId = GsonParser.parseString(jsonObject.get("externalCdvrId"));

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
		kparams.add("externalIngestId", this.externalIngestId);
		kparams.add("externalCdvrId", this.externalCdvrId);
		return kparams;
	}

}

