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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(RegistryResponse.Tokenizer.class)
public class RegistryResponse extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String announcementId();
		String key();
		String url();
	}

	/**
	 * Announcement Id
	 */
	private Long announcementId;
	/**
	 * Key
	 */
	private String key;
	/**
	 * URL
	 */
	private String url;

	// announcementId:
	public Long getAnnouncementId(){
		return this.announcementId;
	}
	public void setAnnouncementId(Long announcementId){
		this.announcementId = announcementId;
	}

	public void announcementId(String multirequestToken){
		setToken("announcementId", multirequestToken);
	}

	// key:
	public String getKey(){
		return this.key;
	}
	public void setKey(String key){
		this.key = key;
	}

	public void key(String multirequestToken){
		setToken("key", multirequestToken);
	}

	// url:
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}

	public void url(String multirequestToken){
		setToken("url", multirequestToken);
	}


	public RegistryResponse() {
		super();
	}

	public RegistryResponse(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		announcementId = GsonParser.parseLong(jsonObject.get("announcementId"));
		key = GsonParser.parseString(jsonObject.get("key"));
		url = GsonParser.parseString(jsonObject.get("url"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRegistryResponse");
		kparams.add("announcementId", this.announcementId);
		kparams.add("key", this.key);
		kparams.add("url", this.url);
		return kparams;
	}

}

