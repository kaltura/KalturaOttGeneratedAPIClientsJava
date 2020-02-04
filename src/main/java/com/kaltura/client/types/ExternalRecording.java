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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ExternalRecording.Tokenizer.class)
public class ExternalRecording extends Recording {
	
	public interface Tokenizer extends Recording.Tokenizer {
		String externalId();
		RequestBuilder.MapTokenizer<StringValue.Tokenizer> metaData();
		String expiryDate();
	}

	/**
	 * External identifier for the recording
	 */
	private String externalId;
	/**
	 * key/value map field for extra data
	 */
	private Map<String, StringValue> metaData;
	/**
	 * Specifies until when the recording is available. Date and time represented as
	  epoch.
	 */
	private Long expiryDate;

	// externalId:
	public String getExternalId(){
		return this.externalId;
	}
	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public void externalId(String multirequestToken){
		setToken("externalId", multirequestToken);
	}

	// metaData:
	public Map<String, StringValue> getMetaData(){
		return this.metaData;
	}
	public void setMetaData(Map<String, StringValue> metaData){
		this.metaData = metaData;
	}

	// expiryDate:
	public Long getExpiryDate(){
		return this.expiryDate;
	}

	public ExternalRecording() {
		super();
	}

	public ExternalRecording(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		metaData = GsonParser.parseMap(jsonObject.getAsJsonObject("metaData"), StringValue.class);
		expiryDate = GsonParser.parseLong(jsonObject.get("expiryDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaExternalRecording");
		kparams.add("externalId", this.externalId);
		kparams.add("metaData", this.metaData);
		return kparams;
	}

}

