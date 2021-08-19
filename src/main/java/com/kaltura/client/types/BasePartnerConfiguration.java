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
		String ksExpirationSeconds();
		String appTokenSessionMaxDurationSeconds();
		String anonymousKSExpirationSeconds();
		String refreshExpirationForPinLoginSeconds();
		String appTokenMaxExpirySeconds();
		String uploadTokenExpirySeconds();
		String apptokenUserValidationDisabled();
	}

	/**
	 * KSExpirationSeconds
	 */
	private Long ksExpirationSeconds;
	/**
	 * AppTokenSessionMaxDurationSeconds
	 */
	private Integer appTokenSessionMaxDurationSeconds;
	/**
	 * AnonymousKSExpirationSeconds
	 */
	private Long anonymousKSExpirationSeconds;
	/**
	 * RefreshExpirationForPinLoginSeconds
	 */
	private Long refreshExpirationForPinLoginSeconds;
	/**
	 * AppTokenMaxExpirySeconds
	 */
	private Integer appTokenMaxExpirySeconds;
	/**
	 * uploadTokenExpirySeconds
	 */
	private Integer uploadTokenExpirySeconds;
	/**
	 * apptokenUserValidationDisabled
	 */
	private Boolean apptokenUserValidationDisabled;

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

	// uploadTokenExpirySeconds:
	public Integer getUploadTokenExpirySeconds(){
		return this.uploadTokenExpirySeconds;
	}
	public void setUploadTokenExpirySeconds(Integer uploadTokenExpirySeconds){
		this.uploadTokenExpirySeconds = uploadTokenExpirySeconds;
	}

	public void uploadTokenExpirySeconds(String multirequestToken){
		setToken("uploadTokenExpirySeconds", multirequestToken);
	}

	// apptokenUserValidationDisabled:
	public Boolean getApptokenUserValidationDisabled(){
		return this.apptokenUserValidationDisabled;
	}
	public void setApptokenUserValidationDisabled(Boolean apptokenUserValidationDisabled){
		this.apptokenUserValidationDisabled = apptokenUserValidationDisabled;
	}

	public void apptokenUserValidationDisabled(String multirequestToken){
		setToken("apptokenUserValidationDisabled", multirequestToken);
	}


	public BasePartnerConfiguration() {
		super();
	}

	public BasePartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		ksExpirationSeconds = GsonParser.parseLong(jsonObject.get("ksExpirationSeconds"));
		appTokenSessionMaxDurationSeconds = GsonParser.parseInt(jsonObject.get("appTokenSessionMaxDurationSeconds"));
		anonymousKSExpirationSeconds = GsonParser.parseLong(jsonObject.get("anonymousKSExpirationSeconds"));
		refreshExpirationForPinLoginSeconds = GsonParser.parseLong(jsonObject.get("refreshExpirationForPinLoginSeconds"));
		appTokenMaxExpirySeconds = GsonParser.parseInt(jsonObject.get("appTokenMaxExpirySeconds"));
		uploadTokenExpirySeconds = GsonParser.parseInt(jsonObject.get("uploadTokenExpirySeconds"));
		apptokenUserValidationDisabled = GsonParser.parseBoolean(jsonObject.get("apptokenUserValidationDisabled"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBasePartnerConfiguration");
		kparams.add("ksExpirationSeconds", this.ksExpirationSeconds);
		kparams.add("appTokenSessionMaxDurationSeconds", this.appTokenSessionMaxDurationSeconds);
		kparams.add("anonymousKSExpirationSeconds", this.anonymousKSExpirationSeconds);
		kparams.add("refreshExpirationForPinLoginSeconds", this.refreshExpirationForPinLoginSeconds);
		kparams.add("appTokenMaxExpirySeconds", this.appTokenMaxExpirySeconds);
		kparams.add("uploadTokenExpirySeconds", this.uploadTokenExpirySeconds);
		kparams.add("apptokenUserValidationDisabled", this.apptokenUserValidationDisabled);
		return kparams;
	}

}

