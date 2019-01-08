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

/**
 * Buzz score
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BuzzScore.Tokenizer.class)
public class BuzzScore extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String normalizedAvgScore();
		String updateDate();
		String avgScore();
	}

	/**
	 * Normalized average score
	 */
	private Double normalizedAvgScore;
	/**
	 * Update date
	 */
	private Long updateDate;
	/**
	 * Average score
	 */
	private Double avgScore;

	// normalizedAvgScore:
	public Double getNormalizedAvgScore(){
		return this.normalizedAvgScore;
	}
	public void setNormalizedAvgScore(Double normalizedAvgScore){
		this.normalizedAvgScore = normalizedAvgScore;
	}

	public void normalizedAvgScore(String multirequestToken){
		setToken("normalizedAvgScore", multirequestToken);
	}

	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	public void setUpdateDate(Long updateDate){
		this.updateDate = updateDate;
	}

	public void updateDate(String multirequestToken){
		setToken("updateDate", multirequestToken);
	}

	// avgScore:
	public Double getAvgScore(){
		return this.avgScore;
	}
	public void setAvgScore(Double avgScore){
		this.avgScore = avgScore;
	}

	public void avgScore(String multirequestToken){
		setToken("avgScore", multirequestToken);
	}


	public BuzzScore() {
		super();
	}

	public BuzzScore(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		normalizedAvgScore = GsonParser.parseDouble(jsonObject.get("normalizedAvgScore"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		avgScore = GsonParser.parseDouble(jsonObject.get("avgScore"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBuzzScore");
		kparams.add("normalizedAvgScore", this.normalizedAvgScore);
		kparams.add("updateDate", this.updateDate);
		kparams.add("avgScore", this.avgScore);
		return kparams;
	}

}

