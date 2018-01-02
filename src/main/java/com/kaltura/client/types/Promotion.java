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
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Promotion.Tokenizer.class)
public class Promotion extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String link();
		String text();
		String startTime();
		String endTime();
	}

	/**
	 * Link
	 */
	private String link;
	/**
	 * Text
	 */
	private String text;
	/**
	 * StartTime
	 */
	private Long startTime;
	/**
	 * EndTime
	 */
	private Long endTime;

	// link:
	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}

	public void link(String multirequestToken){
		setToken("link", multirequestToken);
	}

	// text:
	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
	}

	public void text(String multirequestToken){
		setToken("text", multirequestToken);
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

	// endTime:
	public Long getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Long endTime){
		this.endTime = endTime;
	}

	public void endTime(String multirequestToken){
		setToken("endTime", multirequestToken);
	}


	public Promotion() {
		super();
	}

	public Promotion(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		link = GsonParser.parseString(jsonObject.get("link"));
		text = GsonParser.parseString(jsonObject.get("text"));
		startTime = GsonParser.parseLong(jsonObject.get("startTime"));
		endTime = GsonParser.parseLong(jsonObject.get("endTime"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPromotion");
		kparams.add("link", this.link);
		kparams.add("text", this.text);
		kparams.add("startTime", this.startTime);
		kparams.add("endTime", this.endTime);
		return kparams;
	}

}

