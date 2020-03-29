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
// Copyright (C) 2006-2020  Kaltura Inc.
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

/**
 * kalturaIotProfileAws
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(IotProfileAws.Tokenizer.class)
public class IotProfileAws extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String iotEndPoint();
		String pfxPath();
		String pfxPassword();
		String certificatePath();
		String brokerPort();
		String accessKeyId();
		String secretAccessKey();
		String tTL();
		String iotPolicyName();
		String userPoolId();
		String clientId();
		String identityPoolId();
		String region();
		String updateDate();
	}

	/**
	 * iotEndPoint
	 */
	private String iotEndPoint;
	/**
	 * pfxPath
	 */
	private String pfxPath;
	/**
	 * pfxPassword
	 */
	private String pfxPassword;
	/**
	 * certificatePath
	 */
	private String certificatePath;
	/**
	 * brokerPort
	 */
	private Integer brokerPort;
	/**
	 * accessKeyId
	 */
	private String accessKeyId;
	/**
	 * secretAccessKey
	 */
	private String secretAccessKey;
	/**
	 * tTL
	 */
	private String tTL;
	/**
	 * iotPolicyName
	 */
	private String iotPolicyName;
	/**
	 * userPoolId
	 */
	private String userPoolId;
	/**
	 * clientId
	 */
	private String clientId;
	/**
	 * identityPoolId
	 */
	private String identityPoolId;
	/**
	 * region
	 */
	private String region;
	/**
	 * updateDate
	 */
	private Long updateDate;

	// iotEndPoint:
	public String getIotEndPoint(){
		return this.iotEndPoint;
	}
	public void setIotEndPoint(String iotEndPoint){
		this.iotEndPoint = iotEndPoint;
	}

	public void iotEndPoint(String multirequestToken){
		setToken("iotEndPoint", multirequestToken);
	}

	// pfxPath:
	public String getPfxPath(){
		return this.pfxPath;
	}
	public void setPfxPath(String pfxPath){
		this.pfxPath = pfxPath;
	}

	public void pfxPath(String multirequestToken){
		setToken("pfxPath", multirequestToken);
	}

	// pfxPassword:
	public String getPfxPassword(){
		return this.pfxPassword;
	}
	public void setPfxPassword(String pfxPassword){
		this.pfxPassword = pfxPassword;
	}

	public void pfxPassword(String multirequestToken){
		setToken("pfxPassword", multirequestToken);
	}

	// certificatePath:
	public String getCertificatePath(){
		return this.certificatePath;
	}
	public void setCertificatePath(String certificatePath){
		this.certificatePath = certificatePath;
	}

	public void certificatePath(String multirequestToken){
		setToken("certificatePath", multirequestToken);
	}

	// brokerPort:
	public Integer getBrokerPort(){
		return this.brokerPort;
	}
	public void setBrokerPort(Integer brokerPort){
		this.brokerPort = brokerPort;
	}

	public void brokerPort(String multirequestToken){
		setToken("brokerPort", multirequestToken);
	}

	// accessKeyId:
	public String getAccessKeyId(){
		return this.accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId){
		this.accessKeyId = accessKeyId;
	}

	public void accessKeyId(String multirequestToken){
		setToken("accessKeyId", multirequestToken);
	}

	// secretAccessKey:
	public String getSecretAccessKey(){
		return this.secretAccessKey;
	}
	public void setSecretAccessKey(String secretAccessKey){
		this.secretAccessKey = secretAccessKey;
	}

	public void secretAccessKey(String multirequestToken){
		setToken("secretAccessKey", multirequestToken);
	}

	// tTL:
	public String getTTL(){
		return this.tTL;
	}
	public void setTTL(String tTL){
		this.tTL = tTL;
	}

	public void tTL(String multirequestToken){
		setToken("tTL", multirequestToken);
	}

	// iotPolicyName:
	public String getIotPolicyName(){
		return this.iotPolicyName;
	}
	public void setIotPolicyName(String iotPolicyName){
		this.iotPolicyName = iotPolicyName;
	}

	public void iotPolicyName(String multirequestToken){
		setToken("iotPolicyName", multirequestToken);
	}

	// userPoolId:
	public String getUserPoolId(){
		return this.userPoolId;
	}
	public void setUserPoolId(String userPoolId){
		this.userPoolId = userPoolId;
	}

	public void userPoolId(String multirequestToken){
		setToken("userPoolId", multirequestToken);
	}

	// clientId:
	public String getClientId(){
		return this.clientId;
	}
	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public void clientId(String multirequestToken){
		setToken("clientId", multirequestToken);
	}

	// identityPoolId:
	public String getIdentityPoolId(){
		return this.identityPoolId;
	}
	public void setIdentityPoolId(String identityPoolId){
		this.identityPoolId = identityPoolId;
	}

	public void identityPoolId(String multirequestToken){
		setToken("identityPoolId", multirequestToken);
	}

	// region:
	public String getRegion(){
		return this.region;
	}
	public void setRegion(String region){
		this.region = region;
	}

	public void region(String multirequestToken){
		setToken("region", multirequestToken);
	}

	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	public void setUpdateDate(Long updateDate){
		this.updateDate = updateDate;
	}

	public void updateDate(String multirequestToken){
		setToken("updateDate", multirequestToken);
	}


	public IotProfileAws() {
		super();
	}

	public IotProfileAws(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		iotEndPoint = GsonParser.parseString(jsonObject.get("iotEndPoint"));
		pfxPath = GsonParser.parseString(jsonObject.get("pfxPath"));
		pfxPassword = GsonParser.parseString(jsonObject.get("pfxPassword"));
		certificatePath = GsonParser.parseString(jsonObject.get("certificatePath"));
		brokerPort = GsonParser.parseInt(jsonObject.get("brokerPort"));
		accessKeyId = GsonParser.parseString(jsonObject.get("accessKeyId"));
		secretAccessKey = GsonParser.parseString(jsonObject.get("secretAccessKey"));
		tTL = GsonParser.parseString(jsonObject.get("tTL"));
		iotPolicyName = GsonParser.parseString(jsonObject.get("iotPolicyName"));
		userPoolId = GsonParser.parseString(jsonObject.get("userPoolId"));
		clientId = GsonParser.parseString(jsonObject.get("clientId"));
		identityPoolId = GsonParser.parseString(jsonObject.get("identityPoolId"));
		region = GsonParser.parseString(jsonObject.get("region"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIotProfileAws");
		kparams.add("iotEndPoint", this.iotEndPoint);
		kparams.add("pfxPath", this.pfxPath);
		kparams.add("pfxPassword", this.pfxPassword);
		kparams.add("certificatePath", this.certificatePath);
		kparams.add("brokerPort", this.brokerPort);
		kparams.add("accessKeyId", this.accessKeyId);
		kparams.add("secretAccessKey", this.secretAccessKey);
		kparams.add("tTL", this.tTL);
		kparams.add("iotPolicyName", this.iotPolicyName);
		kparams.add("userPoolId", this.userPoolId);
		kparams.add("clientId", this.clientId);
		kparams.add("identityPoolId", this.identityPoolId);
		kparams.add("region", this.region);
		kparams.add("updateDate", this.updateDate);
		return kparams;
	}

}

