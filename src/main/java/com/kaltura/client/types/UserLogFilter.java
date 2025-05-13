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
 * Filters user logs using the following criteria: user ID(s), message content
  (substring match), and creation date.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserLogFilter.Tokenizer.class)
public class UserLogFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String userIdIn();
		String startDate();
		String endDate();
	}

	/**
	 * A comma-separated list of up to 15 positive integer user IDs (greater than zero)
	  used to filter log entries. An empty list is not permitted;              Valid
	  IDs: Only log entries associated with valid, existing user IDs are returned;    
	            Invalid IDs: Specifying a non-existent user ID will result in no log
	  entries being returned for that specific ID;               Users: Log entries
	  associated with a deleted user will be returned unless the log entry itself has
	  also been deleted;
	 */
	private String userIdIn;
	/**
	 * The start date for filtering (Epoch format). Only logs created on or after this
	  date are returned. If omitted, no start date filter is applied.
	 */
	private Integer startDate;
	/**
	 * The end date for filtering (Epoch format). Only logs created on or before this
	  date are returned. If omitted, no end date filter is applied.
	 */
	private Integer endDate;

	// userIdIn:
	public String getUserIdIn(){
		return this.userIdIn;
	}
	public void setUserIdIn(String userIdIn){
		this.userIdIn = userIdIn;
	}

	public void userIdIn(String multirequestToken){
		setToken("userIdIn", multirequestToken);
	}

	// startDate:
	public Integer getStartDate(){
		return this.startDate;
	}
	public void setStartDate(Integer startDate){
		this.startDate = startDate;
	}

	public void startDate(String multirequestToken){
		setToken("startDate", multirequestToken);
	}

	// endDate:
	public Integer getEndDate(){
		return this.endDate;
	}
	public void setEndDate(Integer endDate){
		this.endDate = endDate;
	}

	public void endDate(String multirequestToken){
		setToken("endDate", multirequestToken);
	}


	public UserLogFilter() {
		super();
	}

	public UserLogFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		userIdIn = GsonParser.parseString(jsonObject.get("userIdIn"));
		startDate = GsonParser.parseInt(jsonObject.get("startDate"));
		endDate = GsonParser.parseInt(jsonObject.get("endDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserLogFilter");
		kparams.add("userIdIn", this.userIdIn);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		return kparams;
	}

}

