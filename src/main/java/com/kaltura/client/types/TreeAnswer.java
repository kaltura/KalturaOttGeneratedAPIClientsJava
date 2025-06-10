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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A class representing a possible response to a question.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TreeAnswer.Tokenizer.class)
public class TreeAnswer extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String answerId();
		String text();
		String hasNextQuestion();
		String isSpecial();
		String specialType();
	}

	/**
	 * Unique identifier for the answer.
	 */
	private String answerId;
	/**
	 * The answer text to display to the user.
	 */
	private String text;
	/**
	 * Boolean indicating if selecting this answer leads to another question.
	 */
	private Boolean hasNextQuestion;
	/**
	 * Flag for special answers like &amp;quot;I don&amp;#39;t know&amp;quot;
	  (optional).
	 */
	private Boolean isSpecial;
	/**
	 * The type of special answer, e.g., &amp;quot;unsure&amp;quot; (optional).
	 */
	private String specialType;

	// answerId:
	public String getAnswerId(){
		return this.answerId;
	}
	public void setAnswerId(String answerId){
		this.answerId = answerId;
	}

	public void answerId(String multirequestToken){
		setToken("answerId", multirequestToken);
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

	// hasNextQuestion:
	public Boolean getHasNextQuestion(){
		return this.hasNextQuestion;
	}
	public void setHasNextQuestion(Boolean hasNextQuestion){
		this.hasNextQuestion = hasNextQuestion;
	}

	public void hasNextQuestion(String multirequestToken){
		setToken("hasNextQuestion", multirequestToken);
	}

	// isSpecial:
	public Boolean getIsSpecial(){
		return this.isSpecial;
	}
	public void setIsSpecial(Boolean isSpecial){
		this.isSpecial = isSpecial;
	}

	public void isSpecial(String multirequestToken){
		setToken("isSpecial", multirequestToken);
	}

	// specialType:
	public String getSpecialType(){
		return this.specialType;
	}
	public void setSpecialType(String specialType){
		this.specialType = specialType;
	}

	public void specialType(String multirequestToken){
		setToken("specialType", multirequestToken);
	}


	public TreeAnswer() {
		super();
	}

	public TreeAnswer(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		answerId = GsonParser.parseString(jsonObject.get("answerId"));
		text = GsonParser.parseString(jsonObject.get("text"));
		hasNextQuestion = GsonParser.parseBoolean(jsonObject.get("hasNextQuestion"));
		isSpecial = GsonParser.parseBoolean(jsonObject.get("isSpecial"));
		specialType = GsonParser.parseString(jsonObject.get("specialType"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTreeAnswer");
		kparams.add("answerId", this.answerId);
		kparams.add("text", this.text);
		kparams.add("hasNextQuestion", this.hasNextQuestion);
		kparams.add("isSpecial", this.isSpecial);
		kparams.add("specialType", this.specialType);
		return kparams;
	}

}

