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
@MultiRequestBuilder.Tokenizer(SocialComment.Tokenizer.class)
public class SocialComment extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String header();
		String text();
		String createDate();
		String writer();
	}

	/**
	 * Comment header
	 */
	private String header;
	/**
	 * Comment body
	 */
	private String text;
	/**
	 * Comment creation date
	 */
	private Long createDate;
	/**
	 * The writer of the comment
	 */
	private String writer;

	// header:
	public String getHeader(){
		return this.header;
	}
	public void setHeader(String header){
		this.header = header;
	}

	public void header(String multirequestToken){
		setToken("header", multirequestToken);
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

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	public void setCreateDate(Long createDate){
		this.createDate = createDate;
	}

	public void createDate(String multirequestToken){
		setToken("createDate", multirequestToken);
	}

	// writer:
	public String getWriter(){
		return this.writer;
	}
	public void setWriter(String writer){
		this.writer = writer;
	}

	public void writer(String multirequestToken){
		setToken("writer", multirequestToken);
	}


	public SocialComment() {
		super();
	}

	public SocialComment(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		header = GsonParser.parseString(jsonObject.get("header"));
		text = GsonParser.parseString(jsonObject.get("text"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		writer = GsonParser.parseString(jsonObject.get("writer"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSocialComment");
		kparams.add("header", this.header);
		kparams.add("text", this.text);
		kparams.add("createDate", this.createDate);
		kparams.add("writer", this.writer);
		return kparams;
	}

}

