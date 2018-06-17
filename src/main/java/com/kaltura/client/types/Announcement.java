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
import com.kaltura.client.enums.AnnouncementRecipientsType;
import com.kaltura.client.enums.AnnouncementStatus;
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
@MultiRequestBuilder.Tokenizer(Announcement.Tokenizer.class)
public class Announcement extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String name();
		String message();
		String enabled();
		String startTime();
		String timezone();
		String status();
		String recipients();
		String id();
		String imageUrl();
		String includeMail();
		String mailTemplate();
		String mailSubject();
		String includeSms();
	}

	/**
	 * Announcement name
	 */
	private String name;
	/**
	 * Announcement message
	 */
	private String message;
	/**
	 * Announcement enabled
	 */
	private Boolean enabled;
	/**
	 * Announcement start time
	 */
	private Long startTime;
	/**
	 * Announcement time zone
	 */
	private String timezone;
	/**
	 * Announcement status: NotSent=0/Sending=1/Sent=2/Aborted=3
	 */
	private AnnouncementStatus status;
	/**
	 * Announcement recipients: All=0/LoggedIn=1/Guests=2/Other=3
	 */
	private AnnouncementRecipientsType recipients;
	/**
	 * Announcement id
	 */
	private Integer id;
	/**
	 * Announcement image URL, relevant for system announcements
	 */
	private String imageUrl;
	/**
	 * Include Mail
	 */
	private Boolean includeMail;
	/**
	 * Mail Template
	 */
	private String mailTemplate;
	/**
	 * Mail Subject
	 */
	private String mailSubject;
	/**
	 * Include SMS
	 */
	private Boolean includeSms;

	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
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

	// enabled:
	public Boolean getEnabled(){
		return this.enabled;
	}
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}

	public void enabled(String multirequestToken){
		setToken("enabled", multirequestToken);
	}

	// startTime:
	public Long getStartTime(){
		return this.startTime;
	}
	public void setStartTime(Long startTime){
		this.startTime = startTime;
	}

	public void startTime(String multirequestToken){
		setToken("startTime", multirequestToken);
	}

	// timezone:
	public String getTimezone(){
		return this.timezone;
	}
	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public void timezone(String multirequestToken){
		setToken("timezone", multirequestToken);
	}

	// status:
	public AnnouncementStatus getStatus(){
		return this.status;
	}
	// recipients:
	public AnnouncementRecipientsType getRecipients(){
		return this.recipients;
	}
	public void setRecipients(AnnouncementRecipientsType recipients){
		this.recipients = recipients;
	}

	public void recipients(String multirequestToken){
		setToken("recipients", multirequestToken);
	}

	// id:
	public Integer getId(){
		return this.id;
	}
	// imageUrl:
	public String getImageUrl(){
		return this.imageUrl;
	}
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public void imageUrl(String multirequestToken){
		setToken("imageUrl", multirequestToken);
	}

	// includeMail:
	public Boolean getIncludeMail(){
		return this.includeMail;
	}
	public void setIncludeMail(Boolean includeMail){
		this.includeMail = includeMail;
	}

	public void includeMail(String multirequestToken){
		setToken("includeMail", multirequestToken);
	}

	// mailTemplate:
	public String getMailTemplate(){
		return this.mailTemplate;
	}
	public void setMailTemplate(String mailTemplate){
		this.mailTemplate = mailTemplate;
	}

	public void mailTemplate(String multirequestToken){
		setToken("mailTemplate", multirequestToken);
	}

	// mailSubject:
	public String getMailSubject(){
		return this.mailSubject;
	}
	public void setMailSubject(String mailSubject){
		this.mailSubject = mailSubject;
	}

	public void mailSubject(String multirequestToken){
		setToken("mailSubject", multirequestToken);
	}

	// includeSms:
	public Boolean getIncludeSms(){
		return this.includeSms;
	}
	public void setIncludeSms(Boolean includeSms){
		this.includeSms = includeSms;
	}

	public void includeSms(String multirequestToken){
		setToken("includeSms", multirequestToken);
	}


	public Announcement() {
		super();
	}

	public Announcement(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		name = GsonParser.parseString(jsonObject.get("name"));
		message = GsonParser.parseString(jsonObject.get("message"));
		enabled = GsonParser.parseBoolean(jsonObject.get("enabled"));
		startTime = GsonParser.parseLong(jsonObject.get("startTime"));
		timezone = GsonParser.parseString(jsonObject.get("timezone"));
		status = AnnouncementStatus.get(GsonParser.parseString(jsonObject.get("status")));
		recipients = AnnouncementRecipientsType.get(GsonParser.parseString(jsonObject.get("recipients")));
		id = GsonParser.parseInt(jsonObject.get("id"));
		imageUrl = GsonParser.parseString(jsonObject.get("imageUrl"));
		includeMail = GsonParser.parseBoolean(jsonObject.get("includeMail"));
		mailTemplate = GsonParser.parseString(jsonObject.get("mailTemplate"));
		mailSubject = GsonParser.parseString(jsonObject.get("mailSubject"));
		includeSms = GsonParser.parseBoolean(jsonObject.get("includeSms"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAnnouncement");
		kparams.add("name", this.name);
		kparams.add("message", this.message);
		kparams.add("enabled", this.enabled);
		kparams.add("startTime", this.startTime);
		kparams.add("timezone", this.timezone);
		kparams.add("recipients", this.recipients);
		kparams.add("imageUrl", this.imageUrl);
		kparams.add("includeMail", this.includeMail);
		kparams.add("mailTemplate", this.mailTemplate);
		kparams.add("mailSubject", this.mailSubject);
		kparams.add("includeSms", this.includeSms);
		return kparams;
	}

}

