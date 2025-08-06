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
 * A class representing a question in the decision tree.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TreeQuestion.Tokenizer.class)
public class TreeQuestion extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String questionId();
		String text();
		String level();
	}

	/**
	 * Unique identifier for the question.
	 */
	private String questionId;
	/**
	 * The question text to display to the user.
	 */
	private String text;
	/**
	 * The depth level in the tree (1 for top-level).
	 */
	private Integer level;

	// questionId:
	public String getQuestionId(){
		return this.questionId;
	}
	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public void questionId(String multirequestToken){
		setToken("questionId", multirequestToken);
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

	// level:
	public Integer getLevel(){
		return this.level;
	}
	public void setLevel(Integer level){
		this.level = level;
	}

	public void level(String multirequestToken){
		setToken("level", multirequestToken);
	}


	public TreeQuestion() {
		super();
	}

	public TreeQuestion(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		questionId = GsonParser.parseString(jsonObject.get("questionId"));
		text = GsonParser.parseString(jsonObject.get("text"));
		level = GsonParser.parseInt(jsonObject.get("level"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTreeQuestion");
		kparams.add("questionId", this.questionId);
		kparams.add("text", this.text);
		kparams.add("level", this.level);
		return kparams;
	}

}

