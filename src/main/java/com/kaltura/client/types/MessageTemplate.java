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
import com.kaltura.client.enums.MessageTemplateType;
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
@MultiRequestBuilder.Tokenizer(MessageTemplate.Tokenizer.class)
public class MessageTemplate extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String message();
		String dateFormat();
		String messageType();
		String sound();
		String action();
		String url();
		String mailTemplate();
		String mailSubject();
		String ratioId();
	}

	/**
	 * The message template with placeholders
	 */
	private String message;
	/**
	 * Default date format for the date &amp;amp; time entries used in the template
	 */
	private String dateFormat;
	/**
	 * Template type. Possible values: Series, Reminder,Churn, SeriesReminder
	 */
	private MessageTemplateType messageType;
	/**
	 * Sound file name to play upon message arrival to the device (if supported by
	  target device)
	 */
	private String sound;
	/**
	 * an optional action
	 */
	private String action;
	/**
	 * URL template for deep linking. Example - /app/location/{mediaId}
	 */
	private String url;
	/**
	 * Mail template name
	 */
	private String mailTemplate;
	/**
	 * Mail subject
	 */
	private String mailSubject;
	/**
	 * Ratio identifier
	 */
	private String ratioId;

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

	// dateFormat:
	public String getDateFormat(){
		return this.dateFormat;
	}
	public void setDateFormat(String dateFormat){
		this.dateFormat = dateFormat;
	}

	public void dateFormat(String multirequestToken){
		setToken("dateFormat", multirequestToken);
	}

	// messageType:
	public MessageTemplateType getMessageType(){
		return this.messageType;
	}
	public void setMessageType(MessageTemplateType messageType){
		this.messageType = messageType;
	}

	public void messageType(String multirequestToken){
		setToken("messageType", multirequestToken);
	}

	// sound:
	public String getSound(){
		return this.sound;
	}
	public void setSound(String sound){
		this.sound = sound;
	}

	public void sound(String multirequestToken){
		setToken("sound", multirequestToken);
	}

	// action:
	public String getAction(){
		return this.action;
	}
	public void setAction(String action){
		this.action = action;
	}

	public void action(String multirequestToken){
		setToken("action", multirequestToken);
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

	// ratioId:
	public String getRatioId(){
		return this.ratioId;
	}
	public void setRatioId(String ratioId){
		this.ratioId = ratioId;
	}

	public void ratioId(String multirequestToken){
		setToken("ratioId", multirequestToken);
	}


	public MessageTemplate() {
		super();
	}

	public MessageTemplate(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		message = GsonParser.parseString(jsonObject.get("message"));
		dateFormat = GsonParser.parseString(jsonObject.get("dateFormat"));
		messageType = MessageTemplateType.get(GsonParser.parseString(jsonObject.get("messageType")));
		sound = GsonParser.parseString(jsonObject.get("sound"));
		action = GsonParser.parseString(jsonObject.get("action"));
		url = GsonParser.parseString(jsonObject.get("url"));
		mailTemplate = GsonParser.parseString(jsonObject.get("mailTemplate"));
		mailSubject = GsonParser.parseString(jsonObject.get("mailSubject"));
		ratioId = GsonParser.parseString(jsonObject.get("ratioId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMessageTemplate");
		kparams.add("message", this.message);
		kparams.add("dateFormat", this.dateFormat);
		kparams.add("messageType", this.messageType);
		kparams.add("sound", this.sound);
		kparams.add("action", this.action);
		kparams.add("url", this.url);
		kparams.add("mailTemplate", this.mailTemplate);
		kparams.add("mailSubject", this.mailSubject);
		kparams.add("ratioId", this.ratioId);
		return kparams;
	}

}

