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

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Search history info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SearchHistory.Tokenizer.class)
public class SearchHistory extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String filter();
		String language();
		String createdAt();
		String service();
		String action();
		String deviceId();
	}

	/**
	 * Search ID
	 */
	private String id;
	/**
	 * Search name
	 */
	private String name;
	/**
	 * Filter
	 */
	private String filter;
	/**
	 * Search language
	 */
	private String language;
	/**
	 * When search was performed
	 */
	private Long createdAt;
	/**
	 * Kaltura OTT Service
	 */
	private String service;
	/**
	 * Kaltura OTT Service Action
	 */
	private String action;
	/**
	 * Unique Device ID
	 */
	private String deviceId;

	// id:
	public String getId(){
		return this.id;
	}
	// name:
	public String getName(){
		return this.name;
	}
	// filter:
	public String getFilter(){
		return this.filter;
	}
	// language:
	public String getLanguage(){
		return this.language;
	}
	// createdAt:
	public Long getCreatedAt(){
		return this.createdAt;
	}
	// service:
	public String getService(){
		return this.service;
	}
	// action:
	public String getAction(){
		return this.action;
	}
	// deviceId:
	public String getDeviceId(){
		return this.deviceId;
	}

	public SearchHistory() {
		super();
	}

	public SearchHistory(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		filter = GsonParser.parseString(jsonObject.get("filter"));
		language = GsonParser.parseString(jsonObject.get("language"));
		createdAt = GsonParser.parseLong(jsonObject.get("createdAt"));
		service = GsonParser.parseString(jsonObject.get("service"));
		action = GsonParser.parseString(jsonObject.get("action"));
		deviceId = GsonParser.parseString(jsonObject.get("deviceId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSearchHistory");
		return kparams;
	}

}

