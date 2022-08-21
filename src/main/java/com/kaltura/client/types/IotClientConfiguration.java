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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Iot client Configuration
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(IotClientConfiguration.Tokenizer.class)
public class IotClientConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String identityPoolId();
		String userPoolId();
		String awsRegion();
		String appClientId();
		String legacyEndPoint();
		String endPoint();
		String thingName();
		String thingArn();
		String thingId();
		String username();
		String password();
		RequestBuilder.ListTokenizer<StringValue.Tokenizer> topics();
		String status();
		String message();
	}

	/**
	 * IdentityPoolId
	 */
	private String identityPoolId;
	/**
	 * UserPoolId
	 */
	private String userPoolId;
	/**
	 * AwsRegion
	 */
	private String awsRegion;
	/**
	 * appClientId
	 */
	private String appClientId;
	/**
	 * legacyEndPoint
	 */
	private String legacyEndPoint;
	/**
	 * endPoint
	 */
	private String endPoint;
	/**
	 * thingName
	 */
	private String thingName;
	/**
	 * thingArn
	 */
	private String thingArn;
	/**
	 * thingId
	 */
	private String thingId;
	/**
	 * username
	 */
	private String username;
	/**
	 * password
	 */
	private String password;
	/**
	 * topics
	 */
	private List<StringValue> topics;
	/**
	 * status
	 */
	private String status;
	/**
	 * message
	 */
	private String message;

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

	// awsRegion:
	public String getAwsRegion(){
		return this.awsRegion;
	}
	public void setAwsRegion(String awsRegion){
		this.awsRegion = awsRegion;
	}

	public void awsRegion(String multirequestToken){
		setToken("awsRegion", multirequestToken);
	}

	// appClientId:
	public String getAppClientId(){
		return this.appClientId;
	}
	public void setAppClientId(String appClientId){
		this.appClientId = appClientId;
	}

	public void appClientId(String multirequestToken){
		setToken("appClientId", multirequestToken);
	}

	// legacyEndPoint:
	public String getLegacyEndPoint(){
		return this.legacyEndPoint;
	}
	public void setLegacyEndPoint(String legacyEndPoint){
		this.legacyEndPoint = legacyEndPoint;
	}

	public void legacyEndPoint(String multirequestToken){
		setToken("legacyEndPoint", multirequestToken);
	}

	// endPoint:
	public String getEndPoint(){
		return this.endPoint;
	}
	public void setEndPoint(String endPoint){
		this.endPoint = endPoint;
	}

	public void endPoint(String multirequestToken){
		setToken("endPoint", multirequestToken);
	}

	// thingName:
	public String getThingName(){
		return this.thingName;
	}
	public void setThingName(String thingName){
		this.thingName = thingName;
	}

	public void thingName(String multirequestToken){
		setToken("thingName", multirequestToken);
	}

	// thingArn:
	public String getThingArn(){
		return this.thingArn;
	}
	public void setThingArn(String thingArn){
		this.thingArn = thingArn;
	}

	public void thingArn(String multirequestToken){
		setToken("thingArn", multirequestToken);
	}

	// thingId:
	public String getThingId(){
		return this.thingId;
	}
	public void setThingId(String thingId){
		this.thingId = thingId;
	}

	public void thingId(String multirequestToken){
		setToken("thingId", multirequestToken);
	}

	// username:
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public void username(String multirequestToken){
		setToken("username", multirequestToken);
	}

	// password:
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public void password(String multirequestToken){
		setToken("password", multirequestToken);
	}

	// topics:
	public List<StringValue> getTopics(){
		return this.topics;
	}
	public void setTopics(List<StringValue> topics){
		this.topics = topics;
	}

	// status:
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// message:
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}

	public void message(String multirequestToken){
		setToken("message", multirequestToken);
	}


	public IotClientConfiguration() {
		super();
	}

	public IotClientConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		identityPoolId = GsonParser.parseString(jsonObject.get("identityPoolId"));
		userPoolId = GsonParser.parseString(jsonObject.get("userPoolId"));
		awsRegion = GsonParser.parseString(jsonObject.get("awsRegion"));
		appClientId = GsonParser.parseString(jsonObject.get("appClientId"));
		legacyEndPoint = GsonParser.parseString(jsonObject.get("legacyEndPoint"));
		endPoint = GsonParser.parseString(jsonObject.get("endPoint"));
		thingName = GsonParser.parseString(jsonObject.get("thingName"));
		thingArn = GsonParser.parseString(jsonObject.get("thingArn"));
		thingId = GsonParser.parseString(jsonObject.get("thingId"));
		username = GsonParser.parseString(jsonObject.get("username"));
		password = GsonParser.parseString(jsonObject.get("password"));
		topics = GsonParser.parseArray(jsonObject.getAsJsonArray("topics"), StringValue.class);
		status = GsonParser.parseString(jsonObject.get("status"));
		message = GsonParser.parseString(jsonObject.get("message"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIotClientConfiguration");
		kparams.add("identityPoolId", this.identityPoolId);
		kparams.add("userPoolId", this.userPoolId);
		kparams.add("awsRegion", this.awsRegion);
		kparams.add("appClientId", this.appClientId);
		kparams.add("legacyEndPoint", this.legacyEndPoint);
		kparams.add("endPoint", this.endPoint);
		kparams.add("thingName", this.thingName);
		kparams.add("thingArn", this.thingArn);
		kparams.add("thingId", this.thingId);
		kparams.add("username", this.username);
		kparams.add("password", this.password);
		kparams.add("topics", this.topics);
		kparams.add("status", this.status);
		kparams.add("message", this.message);
		return kparams;
	}

}

