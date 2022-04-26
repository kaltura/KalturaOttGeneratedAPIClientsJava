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
// Copyright (C) 2006-2022  Kaltura Inc.
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
public class IotProfileAws extends OTTObjectSupportNullable {
	
	public interface Tokenizer extends OTTObjectSupportNullable.Tokenizer {
		String iotEndPoint();
		String accessKeyId();
		String secretAccessKey();
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
	 * accessKeyId
	 */
	private String accessKeyId;
	/**
	 * secretAccessKey
	 */
	private String secretAccessKey;
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
		accessKeyId = GsonParser.parseString(jsonObject.get("accessKeyId"));
		secretAccessKey = GsonParser.parseString(jsonObject.get("secretAccessKey"));
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
		kparams.add("accessKeyId", this.accessKeyId);
		kparams.add("secretAccessKey", this.secretAccessKey);
		kparams.add("userPoolId", this.userPoolId);
		kparams.add("clientId", this.clientId);
		kparams.add("identityPoolId", this.identityPoolId);
		kparams.add("region", this.region);
		kparams.add("updateDate", this.updateDate);
		return kparams;
	}

}

