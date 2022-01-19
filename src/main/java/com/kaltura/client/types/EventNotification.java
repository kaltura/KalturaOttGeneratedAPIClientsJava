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
import com.kaltura.client.enums.EventNotificationStatus;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Household Coupon details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EventNotification.Tokenizer.class)
public class EventNotification extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String id();
		String objectId();
		String eventObjectType();
		String message();
		String status();
		String actionType();
		String createDate();
		String updateDate();
	}

	/**
	 * Identifier
	 */
	private String id;
	/**
	 * Object identifier
	 */
	private Long objectId;
	/**
	 * Event object type
	 */
	private String eventObjectType;
	/**
	 * Message
	 */
	private String message;
	/**
	 * Status
	 */
	private EventNotificationStatus status;
	/**
	 * Action type
	 */
	private String actionType;
	/**
	 * Create date
	 */
	private Long createDate;
	/**
	 * Update date
	 */
	private Long updateDate;

	// id:
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
	}

	// objectId:
	public Long getObjectId(){
		return this.objectId;
	}
	public void setObjectId(Long objectId){
		this.objectId = objectId;
	}

	public void objectId(String multirequestToken){
		setToken("objectId", multirequestToken);
	}

	// eventObjectType:
	public String getEventObjectType(){
		return this.eventObjectType;
	}
	public void setEventObjectType(String eventObjectType){
		this.eventObjectType = eventObjectType;
	}

	public void eventObjectType(String multirequestToken){
		setToken("eventObjectType", multirequestToken);
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
	public EventNotificationStatus getStatus(){
		return this.status;
	}
	public void setStatus(EventNotificationStatus status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// actionType:
	public String getActionType(){
		return this.actionType;
	}
	public void setActionType(String actionType){
		this.actionType = actionType;
	}

	public void actionType(String multirequestToken){
		setToken("actionType", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}

	public EventNotification() {
		super();
	}

	public EventNotification(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		objectId = GsonParser.parseLong(jsonObject.get("objectId"));
		eventObjectType = GsonParser.parseString(jsonObject.get("eventObjectType"));
		message = GsonParser.parseString(jsonObject.get("message"));
		status = EventNotificationStatus.get(GsonParser.parseString(jsonObject.get("status")));
		actionType = GsonParser.parseString(jsonObject.get("actionType"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEventNotification");
		kparams.add("id", this.id);
		kparams.add("objectId", this.objectId);
		kparams.add("eventObjectType", this.eventObjectType);
		kparams.add("message", this.message);
		kparams.add("status", this.status);
		kparams.add("actionType", this.actionType);
		return kparams;
	}

}

