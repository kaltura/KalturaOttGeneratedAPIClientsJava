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
// Copyright (C) 2006-2021  Kaltura Inc.
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
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BasePartnerConfiguration.Tokenizer.class)
public class BasePartnerConfiguration extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		String useStartDate();
		String getOnlyActiveAssets();
		String shouldSupportSingleLogin();
		String ksExpirationSeconds();
		String anonymousKSExpirationSeconds();
		String refreshTokenExpirationSeconds();
		String isRefreshTokenExtendable();
		String refreshExpirationForPinLoginSeconds();
		String isSwitchingUsersAllowed();
		String tokenKeyFormat();
		String appTokenKeyFormat();
		String appTokenSessionMaxDurationSeconds();
		String appTokenMaxExpirySeconds();
		String userSessionsKeyFormat();
		String revokedKsMaxTtlSeconds();
		String mediaPrepAccountId();
		String fairplayCertificate();
	}

	/**
	 * UseStartDate
	 */
	private Boolean useStartDate;
	/**
	 * GetOnlyActiveAssets
	 */
	private Boolean getOnlyActiveAssets;
	/**
	 * ShouldSupportSingleLogin
	 */
	private Boolean shouldSupportSingleLogin;
	/**
	 * KSExpirationSeconds
	 */
	private Long ksExpirationSeconds;
	/**
	 * AnonymousKSExpirationSeconds
	 */
	private Long anonymousKSExpirationSeconds;
	/**
	 * RefreshTokenExpirationSeconds
	 */
	private Long refreshTokenExpirationSeconds;
	/**
	 * IsRefreshTokenExtendable
	 */
	private Boolean isRefreshTokenExtendable;
	/**
	 * RefreshExpirationForPinLoginSeconds
	 */
	private Long refreshExpirationForPinLoginSeconds;
	/**
	 * IsSwitchingUsersAllowed
	 */
	private Boolean isSwitchingUsersAllowed;
	/**
	 * TokenKeyFormat
	 */
	private String tokenKeyFormat;
	/**
	 * AppTokenKeyFormat
	 */
	private String appTokenKeyFormat;
	/**
	 * AppTokenSessionMaxDurationSeconds
	 */
	private Integer appTokenSessionMaxDurationSeconds;
	/**
	 * AppTokenMaxExpirySeconds
	 */
	private Integer appTokenMaxExpirySeconds;
	/**
	 * UserSessionsKeyFormat
	 */
	private String userSessionsKeyFormat;
	/**
	 * RevokedKsMaxTtlSeconds
	 */
	private Integer revokedKsMaxTtlSeconds;
	/**
	 * MediaPrepAccountId
	 */
	private Integer mediaPrepAccountId;
	/**
	 * FairplayCertificate
	 */
	private String fairplayCertificate;

	// useStartDate:
	public Boolean getUseStartDate(){
		return this.useStartDate;
	}
	public void setUseStartDate(Boolean useStartDate){
		this.useStartDate = useStartDate;
	}

	public void useStartDate(String multirequestToken){
		setToken("useStartDate", multirequestToken);
	}

	// getOnlyActiveAssets:
	public Boolean getGetOnlyActiveAssets(){
		return this.getOnlyActiveAssets;
	}
	public void setGetOnlyActiveAssets(Boolean getOnlyActiveAssets){
		this.getOnlyActiveAssets = getOnlyActiveAssets;
	}

	public void getOnlyActiveAssets(String multirequestToken){
		setToken("getOnlyActiveAssets", multirequestToken);
	}

	// shouldSupportSingleLogin:
	public Boolean getShouldSupportSingleLogin(){
		return this.shouldSupportSingleLogin;
	}
	public void setShouldSupportSingleLogin(Boolean shouldSupportSingleLogin){
		this.shouldSupportSingleLogin = shouldSupportSingleLogin;
	}

	public void shouldSupportSingleLogin(String multirequestToken){
		setToken("shouldSupportSingleLogin", multirequestToken);
	}

	// ksExpirationSeconds:
	public Long getKsExpirationSeconds(){
		return this.ksExpirationSeconds;
	}
	public void setKsExpirationSeconds(Long ksExpirationSeconds){
		this.ksExpirationSeconds = ksExpirationSeconds;
	}

	public void ksExpirationSeconds(String multirequestToken){
		setToken("ksExpirationSeconds", multirequestToken);
	}

	// anonymousKSExpirationSeconds:
	public Long getAnonymousKSExpirationSeconds(){
		return this.anonymousKSExpirationSeconds;
	}
	public void setAnonymousKSExpirationSeconds(Long anonymousKSExpirationSeconds){
		this.anonymousKSExpirationSeconds = anonymousKSExpirationSeconds;
	}

	public void anonymousKSExpirationSeconds(String multirequestToken){
		setToken("anonymousKSExpirationSeconds", multirequestToken);
	}

	// refreshTokenExpirationSeconds:
	public Long getRefreshTokenExpirationSeconds(){
		return this.refreshTokenExpirationSeconds;
	}
	public void setRefreshTokenExpirationSeconds(Long refreshTokenExpirationSeconds){
		this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
	}

	public void refreshTokenExpirationSeconds(String multirequestToken){
		setToken("refreshTokenExpirationSeconds", multirequestToken);
	}

	// isRefreshTokenExtendable:
	public Boolean getIsRefreshTokenExtendable(){
		return this.isRefreshTokenExtendable;
	}
	public void setIsRefreshTokenExtendable(Boolean isRefreshTokenExtendable){
		this.isRefreshTokenExtendable = isRefreshTokenExtendable;
	}

	public void isRefreshTokenExtendable(String multirequestToken){
		setToken("isRefreshTokenExtendable", multirequestToken);
	}

	// refreshExpirationForPinLoginSeconds:
	public Long getRefreshExpirationForPinLoginSeconds(){
		return this.refreshExpirationForPinLoginSeconds;
	}
	public void setRefreshExpirationForPinLoginSeconds(Long refreshExpirationForPinLoginSeconds){
		this.refreshExpirationForPinLoginSeconds = refreshExpirationForPinLoginSeconds;
	}

	public void refreshExpirationForPinLoginSeconds(String multirequestToken){
		setToken("refreshExpirationForPinLoginSeconds", multirequestToken);
	}

	// isSwitchingUsersAllowed:
	public Boolean getIsSwitchingUsersAllowed(){
		return this.isSwitchingUsersAllowed;
	}
	public void setIsSwitchingUsersAllowed(Boolean isSwitchingUsersAllowed){
		this.isSwitchingUsersAllowed = isSwitchingUsersAllowed;
	}

	public void isSwitchingUsersAllowed(String multirequestToken){
		setToken("isSwitchingUsersAllowed", multirequestToken);
	}

	// tokenKeyFormat:
	public String getTokenKeyFormat(){
		return this.tokenKeyFormat;
	}
	public void setTokenKeyFormat(String tokenKeyFormat){
		this.tokenKeyFormat = tokenKeyFormat;
	}

	public void tokenKeyFormat(String multirequestToken){
		setToken("tokenKeyFormat", multirequestToken);
	}

	// appTokenKeyFormat:
	public String getAppTokenKeyFormat(){
		return this.appTokenKeyFormat;
	}
	public void setAppTokenKeyFormat(String appTokenKeyFormat){
		this.appTokenKeyFormat = appTokenKeyFormat;
	}

	public void appTokenKeyFormat(String multirequestToken){
		setToken("appTokenKeyFormat", multirequestToken);
	}

	// appTokenSessionMaxDurationSeconds:
	public Integer getAppTokenSessionMaxDurationSeconds(){
		return this.appTokenSessionMaxDurationSeconds;
	}
	public void setAppTokenSessionMaxDurationSeconds(Integer appTokenSessionMaxDurationSeconds){
		this.appTokenSessionMaxDurationSeconds = appTokenSessionMaxDurationSeconds;
	}

	public void appTokenSessionMaxDurationSeconds(String multirequestToken){
		setToken("appTokenSessionMaxDurationSeconds", multirequestToken);
	}

	// appTokenMaxExpirySeconds:
	public Integer getAppTokenMaxExpirySeconds(){
		return this.appTokenMaxExpirySeconds;
	}
	public void setAppTokenMaxExpirySeconds(Integer appTokenMaxExpirySeconds){
		this.appTokenMaxExpirySeconds = appTokenMaxExpirySeconds;
	}

	public void appTokenMaxExpirySeconds(String multirequestToken){
		setToken("appTokenMaxExpirySeconds", multirequestToken);
	}

	// userSessionsKeyFormat:
	public String getUserSessionsKeyFormat(){
		return this.userSessionsKeyFormat;
	}
	public void setUserSessionsKeyFormat(String userSessionsKeyFormat){
		this.userSessionsKeyFormat = userSessionsKeyFormat;
	}

	public void userSessionsKeyFormat(String multirequestToken){
		setToken("userSessionsKeyFormat", multirequestToken);
	}

	// revokedKsMaxTtlSeconds:
	public Integer getRevokedKsMaxTtlSeconds(){
		return this.revokedKsMaxTtlSeconds;
	}
	public void setRevokedKsMaxTtlSeconds(Integer revokedKsMaxTtlSeconds){
		this.revokedKsMaxTtlSeconds = revokedKsMaxTtlSeconds;
	}

	public void revokedKsMaxTtlSeconds(String multirequestToken){
		setToken("revokedKsMaxTtlSeconds", multirequestToken);
	}

	// mediaPrepAccountId:
	public Integer getMediaPrepAccountId(){
		return this.mediaPrepAccountId;
	}
	public void setMediaPrepAccountId(Integer mediaPrepAccountId){
		this.mediaPrepAccountId = mediaPrepAccountId;
	}

	public void mediaPrepAccountId(String multirequestToken){
		setToken("mediaPrepAccountId", multirequestToken);
	}

	// fairplayCertificate:
	public String getFairplayCertificate(){
		return this.fairplayCertificate;
	}
	public void setFairplayCertificate(String fairplayCertificate){
		this.fairplayCertificate = fairplayCertificate;
	}

	public void fairplayCertificate(String multirequestToken){
		setToken("fairplayCertificate", multirequestToken);
	}


	public BasePartnerConfiguration() {
		super();
	}

	public BasePartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		useStartDate = GsonParser.parseBoolean(jsonObject.get("useStartDate"));
		getOnlyActiveAssets = GsonParser.parseBoolean(jsonObject.get("getOnlyActiveAssets"));
		shouldSupportSingleLogin = GsonParser.parseBoolean(jsonObject.get("shouldSupportSingleLogin"));
		ksExpirationSeconds = GsonParser.parseLong(jsonObject.get("ksExpirationSeconds"));
		anonymousKSExpirationSeconds = GsonParser.parseLong(jsonObject.get("anonymousKSExpirationSeconds"));
		refreshTokenExpirationSeconds = GsonParser.parseLong(jsonObject.get("refreshTokenExpirationSeconds"));
		isRefreshTokenExtendable = GsonParser.parseBoolean(jsonObject.get("isRefreshTokenExtendable"));
		refreshExpirationForPinLoginSeconds = GsonParser.parseLong(jsonObject.get("refreshExpirationForPinLoginSeconds"));
		isSwitchingUsersAllowed = GsonParser.parseBoolean(jsonObject.get("isSwitchingUsersAllowed"));
		tokenKeyFormat = GsonParser.parseString(jsonObject.get("tokenKeyFormat"));
		appTokenKeyFormat = GsonParser.parseString(jsonObject.get("appTokenKeyFormat"));
		appTokenSessionMaxDurationSeconds = GsonParser.parseInt(jsonObject.get("appTokenSessionMaxDurationSeconds"));
		appTokenMaxExpirySeconds = GsonParser.parseInt(jsonObject.get("appTokenMaxExpirySeconds"));
		userSessionsKeyFormat = GsonParser.parseString(jsonObject.get("userSessionsKeyFormat"));
		revokedKsMaxTtlSeconds = GsonParser.parseInt(jsonObject.get("revokedKsMaxTtlSeconds"));
		mediaPrepAccountId = GsonParser.parseInt(jsonObject.get("mediaPrepAccountId"));
		fairplayCertificate = GsonParser.parseString(jsonObject.get("fairplayCertificate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBasePartnerConfiguration");
		kparams.add("useStartDate", this.useStartDate);
		kparams.add("getOnlyActiveAssets", this.getOnlyActiveAssets);
		kparams.add("shouldSupportSingleLogin", this.shouldSupportSingleLogin);
		kparams.add("ksExpirationSeconds", this.ksExpirationSeconds);
		kparams.add("anonymousKSExpirationSeconds", this.anonymousKSExpirationSeconds);
		kparams.add("refreshTokenExpirationSeconds", this.refreshTokenExpirationSeconds);
		kparams.add("isRefreshTokenExtendable", this.isRefreshTokenExtendable);
		kparams.add("refreshExpirationForPinLoginSeconds", this.refreshExpirationForPinLoginSeconds);
		kparams.add("isSwitchingUsersAllowed", this.isSwitchingUsersAllowed);
		kparams.add("tokenKeyFormat", this.tokenKeyFormat);
		kparams.add("appTokenKeyFormat", this.appTokenKeyFormat);
		kparams.add("appTokenSessionMaxDurationSeconds", this.appTokenSessionMaxDurationSeconds);
		kparams.add("appTokenMaxExpirySeconds", this.appTokenMaxExpirySeconds);
		kparams.add("userSessionsKeyFormat", this.userSessionsKeyFormat);
		kparams.add("revokedKsMaxTtlSeconds", this.revokedKsMaxTtlSeconds);
		kparams.add("mediaPrepAccountId", this.mediaPrepAccountId);
		kparams.add("fairplayCertificate", this.fairplayCertificate);
		return kparams;
	}

}

