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
// Copyright (C) 2006-2023  Kaltura Inc.
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
import com.kaltura.client.enums.InboxMessageStatus;
import com.kaltura.client.enums.InboxMessageType;
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
@MultiRequestBuilder.Tokenizer(InboxMessage.Tokenizer.class)
public class InboxMessage extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String message();
		String status();
		String type();
		String createdAt();
		String url();
		String campaignId();
	}

	/**
	 * message id
	 */
	private String id;
	/**
	 * message
	 */
	private String message;
	/**
	 * Status
	 */
	private InboxMessageStatus status;
	/**
	 * Type
	 */
	private InboxMessageType type;
	/**
	 * Created at
	 */
	private Long createdAt;
	/**
	 * url
	 */
	private String url;
	/**
	 * campaignId
	 */
	private Long campaignId;

	// id:
	public String getId(){
		return this.id;
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

	// status:
	public InboxMessageStatus getStatus(){
		return this.status;
	}
	// type:
	public InboxMessageType getType(){
		return this.type;
	}
	public void setType(InboxMessageType type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}

	// createdAt:
	public Long getCreatedAt(){
		return this.createdAt;
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

	// campaignId:
	public Long getCampaignId(){
		return this.campaignId;
	}

	public InboxMessage() {
		super();
	}

	public InboxMessage(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		message = GsonParser.parseString(jsonObject.get("message"));
		status = InboxMessageStatus.get(GsonParser.parseString(jsonObject.get("status")));
		type = InboxMessageType.get(GsonParser.parseString(jsonObject.get("type")));
		createdAt = GsonParser.parseLong(jsonObject.get("createdAt"));
		url = GsonParser.parseString(jsonObject.get("url"));
		campaignId = GsonParser.parseLong(jsonObject.get("campaignId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaInboxMessage");
		kparams.add("message", this.message);
		kparams.add("type", this.type);
		kparams.add("url", this.url);
		return kparams;
	}

}

