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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * CDN Adapter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CDNAdapterProfile.Tokenizer.class)
public class CDNAdapterProfile extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String isActive();
		String adapterUrl();
		String baseUrl();
		RequestBuilder.MapTokenizer<StringValue.Tokenizer> settings();
		String systemName();
		String sharedSecret();
	}

	/**
	 * CDN adapter identifier
	 */
	private Integer id;
	/**
	 * CDNR adapter name
	 */
	private String name;
	/**
	 * CDN adapter active status
	 */
	private Boolean isActive;
	/**
	 * CDN adapter URL
	 */
	private String adapterUrl;
	/**
	 * CDN adapter base URL
	 */
	private String baseUrl;
	/**
	 * CDN adapter settings
	 */
	private Map<String, StringValue> settings;
	/**
	 * CDN adapter alias
	 */
	private String systemName;
	/**
	 * CDN shared secret
	 */
	private String sharedSecret;

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

	// isActive:
	public Boolean getIsActive(){
		return this.isActive;
	}
	public void setIsActive(Boolean isActive){
		this.isActive = isActive;
	}

	public void isActive(String multirequestToken){
		setToken("isActive", multirequestToken);
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

	// baseUrl:
	public String getBaseUrl(){
		return this.baseUrl;
	}
	public void setBaseUrl(String baseUrl){
		this.baseUrl = baseUrl;
	}

	public void baseUrl(String multirequestToken){
		setToken("baseUrl", multirequestToken);
	}

	// settings:
	public Map<String, StringValue> getSettings(){
		return this.settings;
	}
	public void setSettings(Map<String, StringValue> settings){
		this.settings = settings;
	}

	// systemName:
	public String getSystemName(){
		return this.systemName;
	}
	public void setSystemName(String systemName){
		this.systemName = systemName;
	}

	public void systemName(String multirequestToken){
		setToken("systemName", multirequestToken);
	}

	// sharedSecret:
	public String getSharedSecret(){
		return this.sharedSecret;
	}

	public CDNAdapterProfile() {
		super();
	}

	public CDNAdapterProfile(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));
		adapterUrl = GsonParser.parseString(jsonObject.get("adapterUrl"));
		baseUrl = GsonParser.parseString(jsonObject.get("baseUrl"));
		settings = GsonParser.parseMap(jsonObject.getAsJsonObject("settings"), StringValue.class);
		systemName = GsonParser.parseString(jsonObject.get("systemName"));
		sharedSecret = GsonParser.parseString(jsonObject.get("sharedSecret"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCDNAdapterProfile");
		kparams.add("name", this.name);
		kparams.add("isActive", this.isActive);
		kparams.add("adapterUrl", this.adapterUrl);
		kparams.add("baseUrl", this.baseUrl);
		kparams.add("settings", this.settings);
		kparams.add("systemName", this.systemName);
		return kparams;
	}

}

