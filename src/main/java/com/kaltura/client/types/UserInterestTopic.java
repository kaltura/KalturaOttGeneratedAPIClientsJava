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
// Copyright (C) 2006-2017  Kaltura Inc.
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
import com.kaltura.client.types.UserInterestTopic;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * User interest topic
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserInterestTopic.Tokenizer.class)
public class UserInterestTopic extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String metaId();
		String value();
		UserInterestTopic.Tokenizer parentTopic();
	}

	/**
	 * Meta identifier
	 */
	private String metaId;
	/**
	 * Meta Value
	 */
	private String value;
	/**
	 * Parent topic
	 */
	private UserInterestTopic parentTopic;

	// metaId:
	public String getMetaId(){
		return this.metaId;
	}
	public void setMetaId(String metaId){
		this.metaId = metaId;
	}

	public void metaId(String multirequestToken){
		setToken("metaId", multirequestToken);
	}

	// value:
	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}

	public void value(String multirequestToken){
		setToken("value", multirequestToken);
	}

	// parentTopic:
	public UserInterestTopic getParentTopic(){
		return this.parentTopic;
	}
	public void setParentTopic(UserInterestTopic parentTopic){
		this.parentTopic = parentTopic;
	}


	public UserInterestTopic() {
		super();
	}

	public UserInterestTopic(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		metaId = GsonParser.parseString(jsonObject.get("metaId"));
		value = GsonParser.parseString(jsonObject.get("value"));
		parentTopic = GsonParser.parseObject(jsonObject.getAsJsonObject("parentTopic"), UserInterestTopic.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserInterestTopic");
		kparams.add("metaId", this.metaId);
		kparams.add("value", this.value);
		kparams.add("parentTopic", this.parentTopic);
		return kparams;
	}

}

