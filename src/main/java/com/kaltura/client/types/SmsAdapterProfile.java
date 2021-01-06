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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Sms adapter profile
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SmsAdapterProfile.Tokenizer.class)
public class SmsAdapterProfile extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String id();
		String adapterUrl();
		String sharedSecret();
		String isActive();
		RequestBuilder.MapTokenizer<StringValue.Tokenizer> settings();
		String externalIdentifier();
		String name();
	}

	/**
	 * id
	 */
	private Long id;
	/**
	 * adapter url
	 */
	private String adapterUrl;
	/**
	 * Shared Secret
	 */
	private String sharedSecret;
	/**
	 * SSO Adapter is active status
	 */
	private Integer isActive;
	/**
	 * SSO Adapter extra parameters
	 */
	private Map<String, StringValue> settings;
	/**
	 * SSO Adapter external identifier
	 */
	private String externalIdentifier;
	/**
	 * Name
	 */
	private String name;

	// id:
	public Long getId(){
		return this.id;
	}
	// adapterUrl:
	public String getAdapterUrl(){
		return this.adapterUrl;
	}
	public void setAdapterUrl(String adapterUrl){
		this.adapterUrl = adapterUrl;
	}

	public void adapterUrl(String multirequestToken){
		setToken("adapterUrl", multirequestToken);
	}

	// sharedSecret:
	public String getSharedSecret(){
		return this.sharedSecret;
	}
	public void setSharedSecret(String sharedSecret){
		this.sharedSecret = sharedSecret;
	}

	public void sharedSecret(String multirequestToken){
		setToken("sharedSecret", multirequestToken);
	}

	// isActive:
	public Integer getIsActive(){
		return this.isActive;
	}
	public void setIsActive(Integer isActive){
		this.isActive = isActive;
	}

	public void isActive(String multirequestToken){
		setToken("isActive", multirequestToken);
	}

	// settings:
	public Map<String, StringValue> getSettings(){
		return this.settings;
	}
	public void setSettings(Map<String, StringValue> settings){
		this.settings = settings;
	}

	// externalIdentifier:
	public String getExternalIdentifier(){
		return this.externalIdentifier;
	}
	public void setExternalIdentifier(String externalIdentifier){
		this.externalIdentifier = externalIdentifier;
	}

	public void externalIdentifier(String multirequestToken){
		setToken("externalIdentifier", multirequestToken);
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


	public SmsAdapterProfile() {
		super();
	}

	public SmsAdapterProfile(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		adapterUrl = GsonParser.parseString(jsonObject.get("adapterUrl"));
		sharedSecret = GsonParser.parseString(jsonObject.get("sharedSecret"));
		isActive = GsonParser.parseInt(jsonObject.get("isActive"));
		settings = GsonParser.parseMap(jsonObject.getAsJsonObject("settings"), StringValue.class);
		externalIdentifier = GsonParser.parseString(jsonObject.get("externalIdentifier"));
		name = GsonParser.parseString(jsonObject.get("name"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSmsAdapterProfile");
		kparams.add("adapterUrl", this.adapterUrl);
		kparams.add("sharedSecret", this.sharedSecret);
		kparams.add("isActive", this.isActive);
		kparams.add("settings", this.settings);
		kparams.add("externalIdentifier", this.externalIdentifier);
		kparams.add("name", this.name);
		return kparams;
	}

}

