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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(FollowDataBase.Tokenizer.class)
public class FollowDataBase extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String announcementId();
		String status();
		String title();
		String timestamp();
		String followPhrase();
	}

	/**
	 * Announcement Id
	 */
	private Long announcementId;
	/**
	 * Status
	 */
	private Integer status;
	/**
	 * Title
	 */
	private String title;
	/**
	 * Timestamp
	 */
	private Long timestamp;
	/**
	 * Follow Phrase
	 */
	private String followPhrase;

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

	// status:
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// title:
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public void title(String multirequestToken){
		setToken("title", multirequestToken);
	}

	// timestamp:
	public Long getTimestamp(){
		return this.timestamp;
	}
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}

	public void timestamp(String multirequestToken){
		setToken("timestamp", multirequestToken);
	}

	// followPhrase:
	public String getFollowPhrase(){
		return this.followPhrase;
	}
	public void setFollowPhrase(String followPhrase){
		this.followPhrase = followPhrase;
	}

	public void followPhrase(String multirequestToken){
		setToken("followPhrase", multirequestToken);
	}


	public FollowDataBase() {
		super();
	}

	public FollowDataBase(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		announcementId = GsonParser.parseLong(jsonObject.get("announcementId"));
		status = GsonParser.parseInt(jsonObject.get("status"));
		title = GsonParser.parseString(jsonObject.get("title"));
		timestamp = GsonParser.parseLong(jsonObject.get("timestamp"));
		followPhrase = GsonParser.parseString(jsonObject.get("followPhrase"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaFollowDataBase");
		return kparams;
	}

}

