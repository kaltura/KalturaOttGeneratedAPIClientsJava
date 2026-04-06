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
 * Evaluates the total duration (in hours) the user watched content matching the
  min and max criterias.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(WatchDurationCondition.Tokenizer.class)
public class WatchDurationCondition extends BaseWatchCondition {
	
	public interface Tokenizer extends BaseWatchCondition.Tokenizer {
		String minDurationHours();
		String maxDurationHours();
	}

	/**
	 * The minimum duration in hours to be met.              Constraint: Must be less
	  than or equal to maxDurationHours.
	 */
	private Integer minDurationHours;
	/**
	 * The maximum duration in hours to be met.              Constraint: Must be
	  greater than or equal to minDurationHours.
	 */
	private Integer maxDurationHours;

	// minDurationHours:
	public Integer getMinDurationHours(){
		return this.minDurationHours;
	}
	public void setMinDurationHours(Integer minDurationHours){
		this.minDurationHours = minDurationHours;
	}

	public void minDurationHours(String multirequestToken){
		setToken("minDurationHours", multirequestToken);
	}

	// maxDurationHours:
	public Integer getMaxDurationHours(){
		return this.maxDurationHours;
	}
	public void setMaxDurationHours(Integer maxDurationHours){
		this.maxDurationHours = maxDurationHours;
	}

	public void maxDurationHours(String multirequestToken){
		setToken("maxDurationHours", multirequestToken);
	}


	public WatchDurationCondition() {
		super();
	}

	public WatchDurationCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		minDurationHours = GsonParser.parseInt(jsonObject.get("minDurationHours"));
		maxDurationHours = GsonParser.parseInt(jsonObject.get("maxDurationHours"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaWatchDurationCondition");
		kparams.add("minDurationHours", this.minDurationHours);
		kparams.add("maxDurationHours", this.maxDurationHours);
		return kparams;
	}

}

