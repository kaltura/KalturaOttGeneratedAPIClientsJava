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
import com.kaltura.client.types.CognitoUserPool;
import com.kaltura.client.types.CredentialsProvider;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

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
		String announcementTopic();
		CredentialsProvider.Tokenizer credentialsProvider();
		CognitoUserPool.Tokenizer cognitoUserPool();
		String json();
		String topics();
	}

	/**
	 * announcementTopic
	 */
	private String announcementTopic;
	/**
	 * KalturaCredentialsProvider
	 */
	private CredentialsProvider credentialsProvider;
	/**
	 * CognitoUserPool
	 */
	private CognitoUserPool cognitoUserPool;
	/**
	 * json
	 */
	private String json;
	/**
	 * topics
	 */
	private String topics;

	// announcementTopic:
	public String getAnnouncementTopic(){
		return this.announcementTopic;
	}
	public void setAnnouncementTopic(String announcementTopic){
		this.announcementTopic = announcementTopic;
	}

	public void announcementTopic(String multirequestToken){
		setToken("announcementTopic", multirequestToken);
	}

	// credentialsProvider:
	public CredentialsProvider getCredentialsProvider(){
		return this.credentialsProvider;
	}
	public void setCredentialsProvider(CredentialsProvider credentialsProvider){
		this.credentialsProvider = credentialsProvider;
	}

	// cognitoUserPool:
	public CognitoUserPool getCognitoUserPool(){
		return this.cognitoUserPool;
	}
	public void setCognitoUserPool(CognitoUserPool cognitoUserPool){
		this.cognitoUserPool = cognitoUserPool;
	}

	// json:
	public String getJson(){
		return this.json;
	}
	public void setJson(String json){
		this.json = json;
	}

	public void json(String multirequestToken){
		setToken("json", multirequestToken);
	}

	// topics:
	public String getTopics(){
		return this.topics;
	}
	public void setTopics(String topics){
		this.topics = topics;
	}

	public void topics(String multirequestToken){
		setToken("topics", multirequestToken);
	}


	public IotClientConfiguration() {
		super();
	}

	public IotClientConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		announcementTopic = GsonParser.parseString(jsonObject.get("announcementTopic"));
		credentialsProvider = GsonParser.parseObject(jsonObject.getAsJsonObject("credentialsProvider"), CredentialsProvider.class);
		cognitoUserPool = GsonParser.parseObject(jsonObject.getAsJsonObject("cognitoUserPool"), CognitoUserPool.class);
		json = GsonParser.parseString(jsonObject.get("json"));
		topics = GsonParser.parseString(jsonObject.get("topics"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIotClientConfiguration");
		kparams.add("announcementTopic", this.announcementTopic);
		kparams.add("credentialsProvider", this.credentialsProvider);
		kparams.add("cognitoUserPool", this.cognitoUserPool);
		kparams.add("json", this.json);
		kparams.add("topics", this.topics);
		return kparams;
	}

}

