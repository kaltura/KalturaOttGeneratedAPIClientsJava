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
import com.kaltura.client.types.PushParams;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(DeviceReport.Tokenizer.class)
public class DeviceReport extends Report {
	
	public interface Tokenizer extends Report.Tokenizer {
		String partnerId();
		String configurationGroupId();
		String udid();
		PushParams.Tokenizer pushParameters();
		String versionNumber();
		String versionPlatform();
		String versionAppName();
		String lastAccessIP();
		String lastAccessDate();
		String userAgent();
		String operationSystem();
	}

	/**
	 * Partner unique identifier
	 */
	private Integer partnerId;
	/**
	 * Configuration group identifier which the version configuration the device last
	  received belongs to
	 */
	private String configurationGroupId;
	/**
	 * Device unique identifier
	 */
	private String udid;
	/**
	 * Device-Application push parameters
	 */
	private PushParams pushParameters;
	/**
	 * Application version number
	 */
	private String versionNumber;
	/**
	 * Application version type
	 */
	private Platform versionPlatform;
	/**
	 * Application version name
	 */
	private String versionAppName;
	/**
	 * Last access IP
	 */
	private String lastAccessIP;
	/**
	 * Last device configuration request date
	 */
	private Long lastAccessDate;
	/**
	 * request header property
	 */
	private String userAgent;
	/**
	 * Request header property              Incase value cannot be found - returns
	  &amp;quot;Unknown 0.0&amp;quot;
	 */
	private String operationSystem;

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

	// udid:
	public String getUdid(){
		return this.udid;
	}
	public void setUdid(String udid){
		this.udid = udid;
	}

	public void udid(String multirequestToken){
		setToken("udid", multirequestToken);
	}

	// pushParameters:
	public PushParams getPushParameters(){
		return this.pushParameters;
	}
	public void setPushParameters(PushParams pushParameters){
		this.pushParameters = pushParameters;
	}

	// versionNumber:
	public String getVersionNumber(){
		return this.versionNumber;
	}
	public void setVersionNumber(String versionNumber){
		this.versionNumber = versionNumber;
	}

	public void versionNumber(String multirequestToken){
		setToken("versionNumber", multirequestToken);
	}

	// versionPlatform:
	public Platform getVersionPlatform(){
		return this.versionPlatform;
	}
	public void setVersionPlatform(Platform versionPlatform){
		this.versionPlatform = versionPlatform;
	}

	public void versionPlatform(String multirequestToken){
		setToken("versionPlatform", multirequestToken);
	}

	// versionAppName:
	public String getVersionAppName(){
		return this.versionAppName;
	}
	public void setVersionAppName(String versionAppName){
		this.versionAppName = versionAppName;
	}

	public void versionAppName(String multirequestToken){
		setToken("versionAppName", multirequestToken);
	}

	// lastAccessIP:
	public String getLastAccessIP(){
		return this.lastAccessIP;
	}
	public void setLastAccessIP(String lastAccessIP){
		this.lastAccessIP = lastAccessIP;
	}

	public void lastAccessIP(String multirequestToken){
		setToken("lastAccessIP", multirequestToken);
	}

	// lastAccessDate:
	public Long getLastAccessDate(){
		return this.lastAccessDate;
	}
	public void setLastAccessDate(Long lastAccessDate){
		this.lastAccessDate = lastAccessDate;
	}

	public void lastAccessDate(String multirequestToken){
		setToken("lastAccessDate", multirequestToken);
	}

	// userAgent:
	public String getUserAgent(){
		return this.userAgent;
	}
	public void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}

	public void userAgent(String multirequestToken){
		setToken("userAgent", multirequestToken);
	}

	// operationSystem:
	public String getOperationSystem(){
		return this.operationSystem;
	}
	public void setOperationSystem(String operationSystem){
		this.operationSystem = operationSystem;
	}

	public void operationSystem(String multirequestToken){
		setToken("operationSystem", multirequestToken);
	}


	public DeviceReport() {
		super();
	}

	public DeviceReport(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		partnerId = GsonParser.parseInt(jsonObject.get("partnerId"));
		configurationGroupId = GsonParser.parseString(jsonObject.get("configurationGroupId"));
		udid = GsonParser.parseString(jsonObject.get("udid"));
		pushParameters = GsonParser.parseObject(jsonObject.getAsJsonObject("pushParameters"), PushParams.class);
		versionNumber = GsonParser.parseString(jsonObject.get("versionNumber"));
		versionPlatform = Platform.get(GsonParser.parseString(jsonObject.get("versionPlatform")));
		versionAppName = GsonParser.parseString(jsonObject.get("versionAppName"));
		lastAccessIP = GsonParser.parseString(jsonObject.get("lastAccessIP"));
		lastAccessDate = GsonParser.parseLong(jsonObject.get("lastAccessDate"));
		userAgent = GsonParser.parseString(jsonObject.get("userAgent"));
		operationSystem = GsonParser.parseString(jsonObject.get("operationSystem"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaDeviceReport");
		kparams.add("partnerId", this.partnerId);
		kparams.add("configurationGroupId", this.configurationGroupId);
		kparams.add("udid", this.udid);
		kparams.add("pushParameters", this.pushParameters);
		kparams.add("versionNumber", this.versionNumber);
		kparams.add("versionPlatform", this.versionPlatform);
		kparams.add("versionAppName", this.versionAppName);
		kparams.add("lastAccessIP", this.lastAccessIP);
		kparams.add("lastAccessDate", this.lastAccessDate);
		kparams.add("userAgent", this.userAgent);
		kparams.add("operationSystem", this.operationSystem);
		return kparams;
	}

}

