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
import com.kaltura.client.types.TreeQuestion;
import com.kaltura.client.types.TreeRecommendations;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A class representing the response from the getNextNodeAndRecommendation API.    
           Contains the next question, possible answers, and content
  recommendations.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TreeNextNodeResponse.Tokenizer.class)
public class TreeNextNodeResponse extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		TreeQuestion.Tokenizer question();
		String totalLevelQuestions();
		RequestBuilder.ListTokenizer<TreeAnswer.Tokenizer> answers();
		TreeRecommendations.Tokenizer recommendations();
	}

	/**
	 * The next question to present to the user, or null for terminal nodes.
	 */
	private TreeQuestion question;
	/**
	 * Number of total questions in the level.
	 */
	private Integer totalLevelQuestions;
	/**
	 * Array of possible answer options for the question.
	 */
	private List<TreeAnswer> answers;
	/**
	 * Content recommendations based on the current path.
	 */
	private TreeRecommendations recommendations;

	// question:
	public TreeQuestion getQuestion(){
		return this.question;
	}
	public void setQuestion(TreeQuestion question){
		this.question = question;
	}

	// totalLevelQuestions:
	public Integer getTotalLevelQuestions(){
		return this.totalLevelQuestions;
	}
	public void setTotalLevelQuestions(Integer totalLevelQuestions){
		this.totalLevelQuestions = totalLevelQuestions;
	}

	public void totalLevelQuestions(String multirequestToken){
		setToken("totalLevelQuestions", multirequestToken);
	}

	// answers:
	public List<TreeAnswer> getAnswers(){
		return this.answers;
	}
	public void setAnswers(List<TreeAnswer> answers){
		this.answers = answers;
	}

	// recommendations:
	public TreeRecommendations getRecommendations(){
		return this.recommendations;
	}
	public void setRecommendations(TreeRecommendations recommendations){
		this.recommendations = recommendations;
	}


	public TreeNextNodeResponse() {
		super();
	}

	public TreeNextNodeResponse(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		question = GsonParser.parseObject(jsonObject.getAsJsonObject("question"), TreeQuestion.class);
		totalLevelQuestions = GsonParser.parseInt(jsonObject.get("totalLevelQuestions"));
		answers = GsonParser.parseArray(jsonObject.getAsJsonArray("answers"), TreeAnswer.class);
		recommendations = GsonParser.parseObject(jsonObject.getAsJsonObject("recommendations"), TreeRecommendations.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTreeNextNodeResponse");
		kparams.add("question", this.question);
		kparams.add("totalLevelQuestions", this.totalLevelQuestions);
		kparams.add("answers", this.answers);
		kparams.add("recommendations", this.recommendations);
		return kparams;
	}

}

