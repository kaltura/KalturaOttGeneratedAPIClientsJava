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
import com.kaltura.client.enums.ProtectionPolicy;
import com.kaltura.client.enums.QuotaOveragePolicy;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TimeShiftedTvPartnerSettings.Tokenizer.class)
public class TimeShiftedTvPartnerSettings extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String catchUpEnabled();
		String cdvrEnabled();
		String startOverEnabled();
		String trickPlayEnabled();
		String recordingScheduleWindowEnabled();
		String protectionEnabled();
		String catchUpBufferLength();
		String trickPlayBufferLength();
		String recordingScheduleWindow();
		String paddingBeforeProgramStarts();
		String paddingAfterProgramEnds();
		String protectionPeriod();
		String protectionQuotaPercentage();
		String recordingLifetimePeriod();
		String cleanupNoticePeriod();
		String seriesRecordingEnabled();
		String nonEntitledChannelPlaybackEnabled();
		String nonExistingChannelPlaybackEnabled();
		String quotaOveragePolicy();
		String protectionPolicy();
		String recoveryGracePeriod();
	}

	/**
	 * Is catch-up enabled
	 */
	private Boolean catchUpEnabled;
	/**
	 * Is c-dvr enabled
	 */
	private Boolean cdvrEnabled;
	/**
	 * Is start-over enabled
	 */
	private Boolean startOverEnabled;
	/**
	 * Is trick-play enabled
	 */
	private Boolean trickPlayEnabled;
	/**
	 * Is recording schedule window enabled
	 */
	private Boolean recordingScheduleWindowEnabled;
	/**
	 * Is recording protection enabled
	 */
	private Boolean protectionEnabled;
	/**
	 * Catch-up buffer length
	 */
	private Long catchUpBufferLength;
	/**
	 * Trick play buffer length
	 */
	private Long trickPlayBufferLength;
	/**
	 * Recording schedule window. Indicates how long (in minutes) after the program
	  starts it is allowed to schedule the recording
	 */
	private Long recordingScheduleWindow;
	/**
	 * Indicates how long (in seconds) before the program starts the recording will
	  begin
	 */
	private Long paddingBeforeProgramStarts;
	/**
	 * Indicates how long (in seconds) after the program ends the recording will end
	 */
	private Long paddingAfterProgramEnds;
	/**
	 * Specify the time in days that a recording should be protected. Start time begins
	  at protection request.
	 */
	private Integer protectionPeriod;
	/**
	 * Indicates how many percent of the quota can be used for protection
	 */
	private Integer protectionQuotaPercentage;
	/**
	 * Specify the time in days that a recording should be kept for user. Start time
	  begins with the program end date.
	 */
	private Integer recordingLifetimePeriod;
	/**
	 * The time in days before the recording lifetime is due from which the client
	  should be able to warn user about deletion.
	 */
	private Integer cleanupNoticePeriod;
	/**
	 * Is recording of series enabled
	 */
	private Boolean seriesRecordingEnabled;
	/**
	 * Is recording playback for non-entitled channel enables
	 */
	private Boolean nonEntitledChannelPlaybackEnabled;
	/**
	 * Is recording playback for non-existing channel enables
	 */
	private Boolean nonExistingChannelPlaybackEnabled;
	/**
	 * Quota Policy
	 */
	private QuotaOveragePolicy quotaOveragePolicy;
	/**
	 * Protection Policy
	 */
	private ProtectionPolicy protectionPolicy;
	/**
	 * The time in days for recovery recording that was delete by Auto Delete .
	 */
	private Integer recoveryGracePeriod;

	// catchUpEnabled:
	public Boolean getCatchUpEnabled(){
		return this.catchUpEnabled;
	}
	public void setCatchUpEnabled(Boolean catchUpEnabled){
		this.catchUpEnabled = catchUpEnabled;
	}

	public void catchUpEnabled(String multirequestToken){
		setToken("catchUpEnabled", multirequestToken);
	}

	// cdvrEnabled:
	public Boolean getCdvrEnabled(){
		return this.cdvrEnabled;
	}
	public void setCdvrEnabled(Boolean cdvrEnabled){
		this.cdvrEnabled = cdvrEnabled;
	}

	public void cdvrEnabled(String multirequestToken){
		setToken("cdvrEnabled", multirequestToken);
	}

	// startOverEnabled:
	public Boolean getStartOverEnabled(){
		return this.startOverEnabled;
	}
	public void setStartOverEnabled(Boolean startOverEnabled){
		this.startOverEnabled = startOverEnabled;
	}

	public void startOverEnabled(String multirequestToken){
		setToken("startOverEnabled", multirequestToken);
	}

	// trickPlayEnabled:
	public Boolean getTrickPlayEnabled(){
		return this.trickPlayEnabled;
	}
	public void setTrickPlayEnabled(Boolean trickPlayEnabled){
		this.trickPlayEnabled = trickPlayEnabled;
	}

	public void trickPlayEnabled(String multirequestToken){
		setToken("trickPlayEnabled", multirequestToken);
	}

	// recordingScheduleWindowEnabled:
	public Boolean getRecordingScheduleWindowEnabled(){
		return this.recordingScheduleWindowEnabled;
	}
	public void setRecordingScheduleWindowEnabled(Boolean recordingScheduleWindowEnabled){
		this.recordingScheduleWindowEnabled = recordingScheduleWindowEnabled;
	}

	public void recordingScheduleWindowEnabled(String multirequestToken){
		setToken("recordingScheduleWindowEnabled", multirequestToken);
	}

	// protectionEnabled:
	public Boolean getProtectionEnabled(){
		return this.protectionEnabled;
	}
	public void setProtectionEnabled(Boolean protectionEnabled){
		this.protectionEnabled = protectionEnabled;
	}

	public void protectionEnabled(String multirequestToken){
		setToken("protectionEnabled", multirequestToken);
	}

	// catchUpBufferLength:
	public Long getCatchUpBufferLength(){
		return this.catchUpBufferLength;
	}
	public void setCatchUpBufferLength(Long catchUpBufferLength){
		this.catchUpBufferLength = catchUpBufferLength;
	}

	public void catchUpBufferLength(String multirequestToken){
		setToken("catchUpBufferLength", multirequestToken);
	}

	// trickPlayBufferLength:
	public Long getTrickPlayBufferLength(){
		return this.trickPlayBufferLength;
	}
	public void setTrickPlayBufferLength(Long trickPlayBufferLength){
		this.trickPlayBufferLength = trickPlayBufferLength;
	}

	public void trickPlayBufferLength(String multirequestToken){
		setToken("trickPlayBufferLength", multirequestToken);
	}

	// recordingScheduleWindow:
	public Long getRecordingScheduleWindow(){
		return this.recordingScheduleWindow;
	}
	public void setRecordingScheduleWindow(Long recordingScheduleWindow){
		this.recordingScheduleWindow = recordingScheduleWindow;
	}

	public void recordingScheduleWindow(String multirequestToken){
		setToken("recordingScheduleWindow", multirequestToken);
	}

	// paddingBeforeProgramStarts:
	public Long getPaddingBeforeProgramStarts(){
		return this.paddingBeforeProgramStarts;
	}
	public void setPaddingBeforeProgramStarts(Long paddingBeforeProgramStarts){
		this.paddingBeforeProgramStarts = paddingBeforeProgramStarts;
	}

	public void paddingBeforeProgramStarts(String multirequestToken){
		setToken("paddingBeforeProgramStarts", multirequestToken);
	}

	// paddingAfterProgramEnds:
	public Long getPaddingAfterProgramEnds(){
		return this.paddingAfterProgramEnds;
	}
	public void setPaddingAfterProgramEnds(Long paddingAfterProgramEnds){
		this.paddingAfterProgramEnds = paddingAfterProgramEnds;
	}

	public void paddingAfterProgramEnds(String multirequestToken){
		setToken("paddingAfterProgramEnds", multirequestToken);
	}

	// protectionPeriod:
	public Integer getProtectionPeriod(){
		return this.protectionPeriod;
	}
	public void setProtectionPeriod(Integer protectionPeriod){
		this.protectionPeriod = protectionPeriod;
	}

	public void protectionPeriod(String multirequestToken){
		setToken("protectionPeriod", multirequestToken);
	}

	// protectionQuotaPercentage:
	public Integer getProtectionQuotaPercentage(){
		return this.protectionQuotaPercentage;
	}
	public void setProtectionQuotaPercentage(Integer protectionQuotaPercentage){
		this.protectionQuotaPercentage = protectionQuotaPercentage;
	}

	public void protectionQuotaPercentage(String multirequestToken){
		setToken("protectionQuotaPercentage", multirequestToken);
	}

	// recordingLifetimePeriod:
	public Integer getRecordingLifetimePeriod(){
		return this.recordingLifetimePeriod;
	}
	public void setRecordingLifetimePeriod(Integer recordingLifetimePeriod){
		this.recordingLifetimePeriod = recordingLifetimePeriod;
	}

	public void recordingLifetimePeriod(String multirequestToken){
		setToken("recordingLifetimePeriod", multirequestToken);
	}

	// cleanupNoticePeriod:
	public Integer getCleanupNoticePeriod(){
		return this.cleanupNoticePeriod;
	}
	public void setCleanupNoticePeriod(Integer cleanupNoticePeriod){
		this.cleanupNoticePeriod = cleanupNoticePeriod;
	}

	public void cleanupNoticePeriod(String multirequestToken){
		setToken("cleanupNoticePeriod", multirequestToken);
	}

	// seriesRecordingEnabled:
	public Boolean getSeriesRecordingEnabled(){
		return this.seriesRecordingEnabled;
	}
	public void setSeriesRecordingEnabled(Boolean seriesRecordingEnabled){
		this.seriesRecordingEnabled = seriesRecordingEnabled;
	}

	public void seriesRecordingEnabled(String multirequestToken){
		setToken("seriesRecordingEnabled", multirequestToken);
	}

	// nonEntitledChannelPlaybackEnabled:
	public Boolean getNonEntitledChannelPlaybackEnabled(){
		return this.nonEntitledChannelPlaybackEnabled;
	}
	public void setNonEntitledChannelPlaybackEnabled(Boolean nonEntitledChannelPlaybackEnabled){
		this.nonEntitledChannelPlaybackEnabled = nonEntitledChannelPlaybackEnabled;
	}

	public void nonEntitledChannelPlaybackEnabled(String multirequestToken){
		setToken("nonEntitledChannelPlaybackEnabled", multirequestToken);
	}

	// nonExistingChannelPlaybackEnabled:
	public Boolean getNonExistingChannelPlaybackEnabled(){
		return this.nonExistingChannelPlaybackEnabled;
	}
	public void setNonExistingChannelPlaybackEnabled(Boolean nonExistingChannelPlaybackEnabled){
		this.nonExistingChannelPlaybackEnabled = nonExistingChannelPlaybackEnabled;
	}

	public void nonExistingChannelPlaybackEnabled(String multirequestToken){
		setToken("nonExistingChannelPlaybackEnabled", multirequestToken);
	}

	// quotaOveragePolicy:
	public QuotaOveragePolicy getQuotaOveragePolicy(){
		return this.quotaOveragePolicy;
	}
	public void setQuotaOveragePolicy(QuotaOveragePolicy quotaOveragePolicy){
		this.quotaOveragePolicy = quotaOveragePolicy;
	}

	public void quotaOveragePolicy(String multirequestToken){
		setToken("quotaOveragePolicy", multirequestToken);
	}

	// protectionPolicy:
	public ProtectionPolicy getProtectionPolicy(){
		return this.protectionPolicy;
	}
	public void setProtectionPolicy(ProtectionPolicy protectionPolicy){
		this.protectionPolicy = protectionPolicy;
	}

	public void protectionPolicy(String multirequestToken){
		setToken("protectionPolicy", multirequestToken);
	}

	// recoveryGracePeriod:
	public Integer getRecoveryGracePeriod(){
		return this.recoveryGracePeriod;
	}
	public void setRecoveryGracePeriod(Integer recoveryGracePeriod){
		this.recoveryGracePeriod = recoveryGracePeriod;
	}

	public void recoveryGracePeriod(String multirequestToken){
		setToken("recoveryGracePeriod", multirequestToken);
	}


	public TimeShiftedTvPartnerSettings() {
		super();
	}

	public TimeShiftedTvPartnerSettings(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		catchUpEnabled = GsonParser.parseBoolean(jsonObject.get("catchUpEnabled"));
		cdvrEnabled = GsonParser.parseBoolean(jsonObject.get("cdvrEnabled"));
		startOverEnabled = GsonParser.parseBoolean(jsonObject.get("startOverEnabled"));
		trickPlayEnabled = GsonParser.parseBoolean(jsonObject.get("trickPlayEnabled"));
		recordingScheduleWindowEnabled = GsonParser.parseBoolean(jsonObject.get("recordingScheduleWindowEnabled"));
		protectionEnabled = GsonParser.parseBoolean(jsonObject.get("protectionEnabled"));
		catchUpBufferLength = GsonParser.parseLong(jsonObject.get("catchUpBufferLength"));
		trickPlayBufferLength = GsonParser.parseLong(jsonObject.get("trickPlayBufferLength"));
		recordingScheduleWindow = GsonParser.parseLong(jsonObject.get("recordingScheduleWindow"));
		paddingBeforeProgramStarts = GsonParser.parseLong(jsonObject.get("paddingBeforeProgramStarts"));
		paddingAfterProgramEnds = GsonParser.parseLong(jsonObject.get("paddingAfterProgramEnds"));
		protectionPeriod = GsonParser.parseInt(jsonObject.get("protectionPeriod"));
		protectionQuotaPercentage = GsonParser.parseInt(jsonObject.get("protectionQuotaPercentage"));
		recordingLifetimePeriod = GsonParser.parseInt(jsonObject.get("recordingLifetimePeriod"));
		cleanupNoticePeriod = GsonParser.parseInt(jsonObject.get("cleanupNoticePeriod"));
		seriesRecordingEnabled = GsonParser.parseBoolean(jsonObject.get("seriesRecordingEnabled"));
		nonEntitledChannelPlaybackEnabled = GsonParser.parseBoolean(jsonObject.get("nonEntitledChannelPlaybackEnabled"));
		nonExistingChannelPlaybackEnabled = GsonParser.parseBoolean(jsonObject.get("nonExistingChannelPlaybackEnabled"));
		quotaOveragePolicy = QuotaOveragePolicy.get(GsonParser.parseString(jsonObject.get("quotaOveragePolicy")));
		protectionPolicy = ProtectionPolicy.get(GsonParser.parseString(jsonObject.get("protectionPolicy")));
		recoveryGracePeriod = GsonParser.parseInt(jsonObject.get("recoveryGracePeriod"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTimeShiftedTvPartnerSettings");
		kparams.add("catchUpEnabled", this.catchUpEnabled);
		kparams.add("cdvrEnabled", this.cdvrEnabled);
		kparams.add("startOverEnabled", this.startOverEnabled);
		kparams.add("trickPlayEnabled", this.trickPlayEnabled);
		kparams.add("recordingScheduleWindowEnabled", this.recordingScheduleWindowEnabled);
		kparams.add("protectionEnabled", this.protectionEnabled);
		kparams.add("catchUpBufferLength", this.catchUpBufferLength);
		kparams.add("trickPlayBufferLength", this.trickPlayBufferLength);
		kparams.add("recordingScheduleWindow", this.recordingScheduleWindow);
		kparams.add("paddingBeforeProgramStarts", this.paddingBeforeProgramStarts);
		kparams.add("paddingAfterProgramEnds", this.paddingAfterProgramEnds);
		kparams.add("protectionPeriod", this.protectionPeriod);
		kparams.add("protectionQuotaPercentage", this.protectionQuotaPercentage);
		kparams.add("recordingLifetimePeriod", this.recordingLifetimePeriod);
		kparams.add("cleanupNoticePeriod", this.cleanupNoticePeriod);
		kparams.add("seriesRecordingEnabled", this.seriesRecordingEnabled);
		kparams.add("nonEntitledChannelPlaybackEnabled", this.nonEntitledChannelPlaybackEnabled);
		kparams.add("nonExistingChannelPlaybackEnabled", this.nonExistingChannelPlaybackEnabled);
		kparams.add("quotaOveragePolicy", this.quotaOveragePolicy);
		kparams.add("protectionPolicy", this.protectionPolicy);
		kparams.add("recoveryGracePeriod", this.recoveryGracePeriod);
		return kparams;
	}

}

