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
import com.kaltura.client.types.BaseResponseProfile;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.types.SkipCondition;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Define client request optional configurations
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(RequestConfiguration.Tokenizer.class)
public class RequestConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String partnerId();
		String userId();
		String language();
		String currency();
		String ks();
		BaseResponseProfile.Tokenizer responseProfile();
		String abortAllOnError();
		SkipCondition.Tokenizer skipCondition();
	}

	/**
	 * Impersonated partner id
	 */
	private Integer partnerId;
	/**
	 * Impersonated user id
	 */
	private Integer userId;
	/**
	 * Content language
	 */
	private String language;
	/**
	 * Currency to be used
	 */
	private String currency;
	/**
	 * Kaltura API session
	 */
	private String ks;
	/**
	 * Kaltura response profile object
	 */
	private BaseResponseProfile responseProfile;
	/**
	 * Abort all following requests if current request has an error
	 */
	private Boolean abortAllOnError;
	/**
	 * Skip current request according to skip condition
	 */
	private SkipCondition skipCondition;

	// partnerId:
	public Integer getPartnerId(){
		return this.partnerId;
	}
	public void setPartnerId(Integer partnerId){
		this.partnerId = partnerId;
	}

	public void partnerId(String multirequestToken){
		setToken("partnerId", multirequestToken);
	}

	// userId:
	public Integer getUserId(){
		return this.userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public void userId(String multirequestToken){
		setToken("userId", multirequestToken);
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

	// currency:
	public String getCurrency(){
		return this.currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public void currency(String multirequestToken){
		setToken("currency", multirequestToken);
	}

	// ks:
	public String getKs(){
		return this.ks;
	}
	public void setKs(String ks){
		this.ks = ks;
	}

	public void ks(String multirequestToken){
		setToken("ks", multirequestToken);
	}

	// responseProfile:
	public BaseResponseProfile getResponseProfile(){
		return this.responseProfile;
	}
	public void setResponseProfile(BaseResponseProfile responseProfile){
		this.responseProfile = responseProfile;
	}

	// abortAllOnError:
	public Boolean getAbortAllOnError(){
		return this.abortAllOnError;
	}
	public void setAbortAllOnError(Boolean abortAllOnError){
		this.abortAllOnError = abortAllOnError;
	}

	public void abortAllOnError(String multirequestToken){
		setToken("abortAllOnError", multirequestToken);
	}

	// skipCondition:
	public SkipCondition getSkipCondition(){
		return this.skipCondition;
	}
	public void setSkipCondition(SkipCondition skipCondition){
		this.skipCondition = skipCondition;
	}


	public RequestConfiguration() {
		super();
	}

	public RequestConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		partnerId = GsonParser.parseInt(jsonObject.get("partnerId"));
		userId = GsonParser.parseInt(jsonObject.get("userId"));
		language = GsonParser.parseString(jsonObject.get("language"));
		currency = GsonParser.parseString(jsonObject.get("currency"));
		ks = GsonParser.parseString(jsonObject.get("ks"));
		responseProfile = GsonParser.parseObject(jsonObject.getAsJsonObject("responseProfile"), BaseResponseProfile.class);
		abortAllOnError = GsonParser.parseBoolean(jsonObject.get("abortAllOnError"));
		skipCondition = GsonParser.parseObject(jsonObject.getAsJsonObject("skipCondition"), SkipCondition.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRequestConfiguration");
		kparams.add("partnerId", this.partnerId);
		kparams.add("userId", this.userId);
		kparams.add("language", this.language);
		kparams.add("currency", this.currency);
		kparams.add("ks", this.ks);
		kparams.add("responseProfile", this.responseProfile);
		kparams.add("abortAllOnError", this.abortAllOnError);
		kparams.add("skipCondition", this.skipCondition);
		return kparams;
	}

}

