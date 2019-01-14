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
import com.kaltura.client.enums.Platform;
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
@MultiRequestBuilder.Tokenizer(Configurations.Tokenizer.class)
public class Configurations extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String partnerId();
		String configurationGroupId();
		String appName();
		String clientVersion();
		String platform();
		String externalPushId();
		String isForceUpdate();
		String content();
	}

	/**
	 * Configuration id
	 */
	private String id;
	/**
	 * Partner id
	 */
	private Integer partnerId;
	/**
	 * Configuration group id
	 */
	private String configurationGroupId;
	/**
	 * Application name
	 */
	private String appName;
	/**
	 * Client version
	 */
	private String clientVersion;
	/**
	 * Platform: Android/iOS/WindowsPhone/Blackberry/STB/CTV/Other
	 */
	private Platform platform;
	/**
	 * External push id
	 */
	private String externalPushId;
	/**
	 * Is force update
	 */
	private Boolean isForceUpdate;
	/**
	 * Content
	 */
	private String content;

	// id:
	public String getId(){
		return this.id;
	}
	// partnerId:
	public Integer getPartnerId(){
		return this.partnerId;
	}
	// configurationGroupId:
	public String getConfigurationGroupId(){
		return this.configurationGroupId;
	}
	public void setConfigurationGroupId(String configurationGroupId){
		this.configurationGroupId = configurationGroupId;
	}

	public void configurationGroupId(String multirequestToken){
		setToken("configurationGroupId", multirequestToken);
	}

	// appName:
	public String getAppName(){
		return this.appName;
	}
	public void setAppName(String appName){
		this.appName = appName;
	}

	public void appName(String multirequestToken){
		setToken("appName", multirequestToken);
	}

	// clientVersion:
	public String getClientVersion(){
		return this.clientVersion;
	}
	public void setClientVersion(String clientVersion){
		this.clientVersion = clientVersion;
	}

	public void clientVersion(String multirequestToken){
		setToken("clientVersion", multirequestToken);
	}

	// platform:
	public Platform getPlatform(){
		return this.platform;
	}
	public void setPlatform(Platform platform){
		this.platform = platform;
	}

	public void platform(String multirequestToken){
		setToken("platform", multirequestToken);
	}

	// externalPushId:
	public String getExternalPushId(){
		return this.externalPushId;
	}
	public void setExternalPushId(String externalPushId){
		this.externalPushId = externalPushId;
	}

	public void externalPushId(String multirequestToken){
		setToken("externalPushId", multirequestToken);
	}

	// isForceUpdate:
	public Boolean getIsForceUpdate(){
		return this.isForceUpdate;
	}
	public void setIsForceUpdate(Boolean isForceUpdate){
		this.isForceUpdate = isForceUpdate;
	}

	public void isForceUpdate(String multirequestToken){
		setToken("isForceUpdate", multirequestToken);
	}

	// content:
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}

	public void content(String multirequestToken){
		setToken("content", multirequestToken);
	}


	public Configurations() {
		super();
	}

	public Configurations(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		partnerId = GsonParser.parseInt(jsonObject.get("partnerId"));
		configurationGroupId = GsonParser.parseString(jsonObject.get("configurationGroupId"));
		appName = GsonParser.parseString(jsonObject.get("appName"));
		clientVersion = GsonParser.parseString(jsonObject.get("clientVersion"));
		platform = Platform.get(GsonParser.parseString(jsonObject.get("platform")));
		externalPushId = GsonParser.parseString(jsonObject.get("externalPushId"));
		isForceUpdate = GsonParser.parseBoolean(jsonObject.get("isForceUpdate"));
		content = GsonParser.parseString(jsonObject.get("content"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaConfigurations");
		kparams.add("configurationGroupId", this.configurationGroupId);
		kparams.add("appName", this.appName);
		kparams.add("clientVersion", this.clientVersion);
		kparams.add("platform", this.platform);
		kparams.add("externalPushId", this.externalPushId);
		kparams.add("isForceUpdate", this.isForceUpdate);
		kparams.add("content", this.content);
		return kparams;
	}

}

