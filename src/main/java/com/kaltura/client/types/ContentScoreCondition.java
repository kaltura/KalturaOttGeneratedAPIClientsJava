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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Defines a condition which is essentially a combination of several content-based
  actions, each has their own score multiplier
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ContentScoreCondition.Tokenizer.class)
public class ContentScoreCondition extends BaseSegmentCondition {
	
	public interface Tokenizer extends BaseSegmentCondition.Tokenizer {
		String score();
		String days();
		RequestBuilder.ListTokenizer<ContentActionCondition.Tokenizer> actions();
	}

	/**
	 * The minimum score to be met
	 */
	private Integer score;
	/**
	 * How many days back should the actions be considered
	 */
	private Integer days;
	/**
	 * List of the actions that consist the condition
	 */
	private List<ContentActionCondition> actions;

	// score:
	public Integer getScore(){
		return this.score;
	}
	public void setScore(Integer score){
		this.score = score;
	}

	public void score(String multirequestToken){
		setToken("score", multirequestToken);
	}

	// days:
	public Integer getDays(){
		return this.days;
	}
	public void setDays(Integer days){
		this.days = days;
	}

	public void days(String multirequestToken){
		setToken("days", multirequestToken);
	}

	// actions:
	public List<ContentActionCondition> getActions(){
		return this.actions;
	}
	public void setActions(List<ContentActionCondition> actions){
		this.actions = actions;
	}


	public ContentScoreCondition() {
		super();
	}

	public ContentScoreCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		score = GsonParser.parseInt(jsonObject.get("score"));
		days = GsonParser.parseInt(jsonObject.get("days"));
		actions = GsonParser.parseArray(jsonObject.getAsJsonArray("actions"), ContentActionCondition.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaContentScoreCondition");
		kparams.add("score", this.score);
		kparams.add("days", this.days);
		kparams.add("actions", this.actions);
		return kparams;
	}

}

