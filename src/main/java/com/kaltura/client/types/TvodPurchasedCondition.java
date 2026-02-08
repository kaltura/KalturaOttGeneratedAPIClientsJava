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
import com.kaltura.client.enums.ConditionLevel;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Evaluates whether a user purchased a specific TVOD.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TvodPurchasedCondition.Tokenizer.class)
public class TvodPurchasedCondition extends BaseSegmentCondition {
	
	public interface Tokenizer extends BaseSegmentCondition.Tokenizer {
		String level();
		String ppvIdEquals();
		String mediaIdEquals();
		String days();
	}

	/**
	 * TVOD purchase conditions are always evaluated at the Household level.
	 */
	private ConditionLevel level;
	/**
	 * The specific purchased ppv product identifier to check.
	 */
	private Long ppvIdEquals;
	/**
	 * The specific purchased media entry identifier to check.
	 */
	private Long mediaIdEquals;
	/**
	 * The number of days to look back for the purchase.
	 */
	private Integer days;

	// level:
	public ConditionLevel getLevel(){
		return this.level;
	}
	public void setLevel(ConditionLevel level){
		this.level = level;
	}

	public void level(String multirequestToken){
		setToken("level", multirequestToken);
	}

	// ppvIdEquals:
	public Long getPpvIdEquals(){
		return this.ppvIdEquals;
	}
	public void setPpvIdEquals(Long ppvIdEquals){
		this.ppvIdEquals = ppvIdEquals;
	}

	public void ppvIdEquals(String multirequestToken){
		setToken("ppvIdEquals", multirequestToken);
	}

	// mediaIdEquals:
	public Long getMediaIdEquals(){
		return this.mediaIdEquals;
	}
	public void setMediaIdEquals(Long mediaIdEquals){
		this.mediaIdEquals = mediaIdEquals;
	}

	public void mediaIdEquals(String multirequestToken){
		setToken("mediaIdEquals", multirequestToken);
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


	public TvodPurchasedCondition() {
		super();
	}

	public TvodPurchasedCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		level = ConditionLevel.get(GsonParser.parseString(jsonObject.get("level")));
		ppvIdEquals = GsonParser.parseLong(jsonObject.get("ppvIdEquals"));
		mediaIdEquals = GsonParser.parseLong(jsonObject.get("mediaIdEquals"));
		days = GsonParser.parseInt(jsonObject.get("days"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTvodPurchasedCondition");
		kparams.add("level", this.level);
		kparams.add("ppvIdEquals", this.ppvIdEquals);
		kparams.add("mediaIdEquals", this.mediaIdEquals);
		kparams.add("days", this.days);
		return kparams;
	}

}

