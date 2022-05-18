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
 * IOT DEVICE
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Iot.Tokenizer.class)
public class Iot extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String udid();
		String accessKey();
		String accessSecretKey();
		String username();
		String userPassword();
		String identityId();
		String thingArn();
		String thingId();
		String principal();
		String endPoint();
		String extendedEndPoint();
		String identityPoolId();
	}

	/**
	 * id
	 */
	private String udid;
	/**
	 * accessKey
	 */
	private String accessKey;
	/**
	 * accessSecretKey
	 */
	private String accessSecretKey;
	/**
	 * Username
	 */
	private String username;
	/**
	 * UserPassword
	 */
	private String userPassword;
	/**
	 * IdentityId
	 */
	private String identityId;
	/**
	 * ThingArn
	 */
	private String thingArn;
	/**
	 * ThingId
	 */
	private String thingId;
	/**
	 * Principal
	 */
	private String principal;
	/**
	 * EndPoint
	 */
	private String endPoint;
	/**
	 * ExtendedEndPoint
	 */
	private String extendedEndPoint;
	/**
	 * IdentityPoolId
	 */
	private String identityPoolId;

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

	// accessKey:
	public String getAccessKey(){
		return this.accessKey;
	}
	public void setAccessKey(String accessKey){
		this.accessKey = accessKey;
	}

	public void accessKey(String multirequestToken){
		setToken("accessKey", multirequestToken);
	}

	// accessSecretKey:
	public String getAccessSecretKey(){
		return this.accessSecretKey;
	}
	public void setAccessSecretKey(String accessSecretKey){
		this.accessSecretKey = accessSecretKey;
	}

	public void accessSecretKey(String multirequestToken){
		setToken("accessSecretKey", multirequestToken);
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

	// userPassword:
	public String getUserPassword(){
		return this.userPassword;
	}
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public void userPassword(String multirequestToken){
		setToken("userPassword", multirequestToken);
	}

	// identityId:
	public String getIdentityId(){
		return this.identityId;
	}
	public void setIdentityId(String identityId){
		this.identityId = identityId;
	}

	public void identityId(String multirequestToken){
		setToken("identityId", multirequestToken);
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

	// principal:
	public String getPrincipal(){
		return this.principal;
	}
	public void setPrincipal(String principal){
		this.principal = principal;
	}

	public void principal(String multirequestToken){
		setToken("principal", multirequestToken);
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

	// extendedEndPoint:
	public String getExtendedEndPoint(){
		return this.extendedEndPoint;
	}
	public void setExtendedEndPoint(String extendedEndPoint){
		this.extendedEndPoint = extendedEndPoint;
	}

	public void extendedEndPoint(String multirequestToken){
		setToken("extendedEndPoint", multirequestToken);
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


	public Iot() {
		super();
	}

	public Iot(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		udid = GsonParser.parseString(jsonObject.get("udid"));
		accessKey = GsonParser.parseString(jsonObject.get("accessKey"));
		accessSecretKey = GsonParser.parseString(jsonObject.get("accessSecretKey"));
		username = GsonParser.parseString(jsonObject.get("username"));
		userPassword = GsonParser.parseString(jsonObject.get("userPassword"));
		identityId = GsonParser.parseString(jsonObject.get("identityId"));
		thingArn = GsonParser.parseString(jsonObject.get("thingArn"));
		thingId = GsonParser.parseString(jsonObject.get("thingId"));
		principal = GsonParser.parseString(jsonObject.get("principal"));
		endPoint = GsonParser.parseString(jsonObject.get("endPoint"));
		extendedEndPoint = GsonParser.parseString(jsonObject.get("extendedEndPoint"));
		identityPoolId = GsonParser.parseString(jsonObject.get("identityPoolId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIot");
		kparams.add("udid", this.udid);
		kparams.add("accessKey", this.accessKey);
		kparams.add("accessSecretKey", this.accessSecretKey);
		kparams.add("username", this.username);
		kparams.add("userPassword", this.userPassword);
		kparams.add("identityId", this.identityId);
		kparams.add("thingArn", this.thingArn);
		kparams.add("thingId", this.thingId);
		kparams.add("principal", this.principal);
		kparams.add("endPoint", this.endPoint);
		kparams.add("extendedEndPoint", this.extendedEndPoint);
		kparams.add("identityPoolId", this.identityPoolId);
		return kparams;
	}

}

