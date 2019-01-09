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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EmailMessage.Tokenizer.class)
public class EmailMessage extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String templateName();
		String subject();
		String firstName();
		String lastName();
		String senderName();
		String senderFrom();
		String senderTo();
		String bccAddress();
		RequestBuilder.ListTokenizer<KeyValue.Tokenizer> extraParameters();
	}

	/**
	 * email template name
	 */
	private String templateName;
	/**
	 * email subject
	 */
	private String subject;
	/**
	 * first name
	 */
	private String firstName;
	/**
	 * last name
	 */
	private String lastName;
	/**
	 * sender name
	 */
	private String senderName;
	/**
	 * sender from
	 */
	private String senderFrom;
	/**
	 * sender to
	 */
	private String senderTo;
	/**
	 * bcc address - seperated by comma
	 */
	private String bccAddress;
	/**
	 * extra parameters
	 */
	private List<KeyValue> extraParameters;

	// templateName:
	public String getTemplateName(){
		return this.templateName;
	}
	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}

	public void templateName(String multirequestToken){
		setToken("templateName", multirequestToken);
	}

	// subject:
	public String getSubject(){
		return this.subject;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}

	public void subject(String multirequestToken){
		setToken("subject", multirequestToken);
	}

	// firstName:
	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public void firstName(String multirequestToken){
		setToken("firstName", multirequestToken);
	}

	// lastName:
	public String getLastName(){
		return this.lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public void lastName(String multirequestToken){
		setToken("lastName", multirequestToken);
	}

	// senderName:
	public String getSenderName(){
		return this.senderName;
	}
	public void setSenderName(String senderName){
		this.senderName = senderName;
	}

	public void senderName(String multirequestToken){
		setToken("senderName", multirequestToken);
	}

	// senderFrom:
	public String getSenderFrom(){
		return this.senderFrom;
	}
	public void setSenderFrom(String senderFrom){
		this.senderFrom = senderFrom;
	}

	public void senderFrom(String multirequestToken){
		setToken("senderFrom", multirequestToken);
	}

	// senderTo:
	public String getSenderTo(){
		return this.senderTo;
	}
	public void setSenderTo(String senderTo){
		this.senderTo = senderTo;
	}

	public void senderTo(String multirequestToken){
		setToken("senderTo", multirequestToken);
	}

	// bccAddress:
	public String getBccAddress(){
		return this.bccAddress;
	}
	public void setBccAddress(String bccAddress){
		this.bccAddress = bccAddress;
	}

	public void bccAddress(String multirequestToken){
		setToken("bccAddress", multirequestToken);
	}

	// extraParameters:
	public List<KeyValue> getExtraParameters(){
		return this.extraParameters;
	}
	public void setExtraParameters(List<KeyValue> extraParameters){
		this.extraParameters = extraParameters;
	}


	public EmailMessage() {
		super();
	}

	public EmailMessage(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		templateName = GsonParser.parseString(jsonObject.get("templateName"));
		subject = GsonParser.parseString(jsonObject.get("subject"));
		firstName = GsonParser.parseString(jsonObject.get("firstName"));
		lastName = GsonParser.parseString(jsonObject.get("lastName"));
		senderName = GsonParser.parseString(jsonObject.get("senderName"));
		senderFrom = GsonParser.parseString(jsonObject.get("senderFrom"));
		senderTo = GsonParser.parseString(jsonObject.get("senderTo"));
		bccAddress = GsonParser.parseString(jsonObject.get("bccAddress"));
		extraParameters = GsonParser.parseArray(jsonObject.getAsJsonArray("extraParameters"), KeyValue.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEmailMessage");
		kparams.add("templateName", this.templateName);
		kparams.add("subject", this.subject);
		kparams.add("firstName", this.firstName);
		kparams.add("lastName", this.lastName);
		kparams.add("senderName", this.senderName);
		kparams.add("senderFrom", this.senderFrom);
		kparams.add("senderTo", this.senderTo);
		kparams.add("bccAddress", this.bccAddress);
		kparams.add("extraParameters", this.extraParameters);
		return kparams;
	}

}

