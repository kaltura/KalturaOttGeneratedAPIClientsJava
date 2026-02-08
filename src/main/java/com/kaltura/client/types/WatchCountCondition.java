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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Evaluates the number of times the user watched content matching the min and max
  criterias.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(WatchCountCondition.Tokenizer.class)
public class WatchCountCondition extends BaseWatchCondition {
	
	public interface Tokenizer extends BaseWatchCondition.Tokenizer {
		String minCount();
		String maxCount();
	}

	/**
	 * The minimum count to be met.              Constraint: Must be less than or equal
	  to maxCount.
	 */
	private Integer minCount;
	/**
	 * The maximum count to be met.              Constraint: Must be greater than or
	  equal to minCount.
	 */
	private Integer maxCount;

	// minCount:
	public Integer getMinCount(){
		return this.minCount;
	}
	public void setMinCount(Integer minCount){
		this.minCount = minCount;
	}

	public void minCount(String multirequestToken){
		setToken("minCount", multirequestToken);
	}

	// maxCount:
	public Integer getMaxCount(){
		return this.maxCount;
	}
	public void setMaxCount(Integer maxCount){
		this.maxCount = maxCount;
	}

	public void maxCount(String multirequestToken){
		setToken("maxCount", multirequestToken);
	}


	public WatchCountCondition() {
		super();
	}

	public WatchCountCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		minCount = GsonParser.parseInt(jsonObject.get("minCount"));
		maxCount = GsonParser.parseInt(jsonObject.get("maxCount"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaWatchCountCondition");
		kparams.add("minCount", this.minCount);
		kparams.add("maxCount", this.maxCount);
		return kparams;
	}

}

