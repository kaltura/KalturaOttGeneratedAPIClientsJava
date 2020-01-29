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
// Copyright (C) 2006-2019  Kaltura Inc.
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

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MailDispatcher.Tokenizer.class)
public class MailDispatcher extends Dispatcher {
	
	public interface Tokenizer extends Dispatcher.Tokenizer {
		String bodyTemplate();
		String subjectTemplate();
	}

	/**
	 * Mail body template
	 */
	private String bodyTemplate;
	/**
	 * Mail subjsct template
	 */
	private String subjectTemplate;

	// bodyTemplate:
	public String getBodyTemplate(){
		return this.bodyTemplate;
	}
	public void setBodyTemplate(String bodyTemplate){
		this.bodyTemplate = bodyTemplate;
	}

	public void bodyTemplate(String multirequestToken){
		setToken("bodyTemplate", multirequestToken);
	}

	// subjectTemplate:
	public String getSubjectTemplate(){
		return this.subjectTemplate;
	}
	public void setSubjectTemplate(String subjectTemplate){
		this.subjectTemplate = subjectTemplate;
	}

	public void subjectTemplate(String multirequestToken){
		setToken("subjectTemplate", multirequestToken);
	}


	public MailDispatcher() {
		super();
	}

	public MailDispatcher(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		bodyTemplate = GsonParser.parseString(jsonObject.get("bodyTemplate"));
		subjectTemplate = GsonParser.parseString(jsonObject.get("subjectTemplate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMailDispatcher");
		kparams.add("bodyTemplate", this.bodyTemplate);
		kparams.add("subjectTemplate", this.subjectTemplate);
		return kparams;
	}

}

