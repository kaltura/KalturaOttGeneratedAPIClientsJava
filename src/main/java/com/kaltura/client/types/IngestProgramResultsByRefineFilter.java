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
// Copyright (C) 2006-2022  Kaltura Inc.
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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(IngestProgramResultsByRefineFilter.Tokenizer.class)
public abstract class IngestProgramResultsByRefineFilter extends IngestEpgProgramResultFilter {
	
	public interface Tokenizer extends IngestEpgProgramResultFilter.Tokenizer {
		String ingestStatusIn();
		String startDateGreaterThan();
		String startDateSmallerThan();
	}

	/**
	 * Comma seperated valid statuses - only &amp;#39;FAILURE&amp;#39;,
	  &amp;#39;WARNING&amp;#39; and &amp;#39;SUCCESS&amp;#39; are valid strings. No
	  repetitions are allowed.
	 */
	private String ingestStatusIn;
	/**
	 * Program EPG start date greater then this value. Date and time represented as
	  epoch.
	 */
	private Long startDateGreaterThan;
	/**
	 * Program EPG start date smaller than this value. Date and time represented as
	  epoch.
	 */
	private Long startDateSmallerThan;

	// ingestStatusIn:
	public String getIngestStatusIn(){
		return this.ingestStatusIn;
	}
	public void setIngestStatusIn(String ingestStatusIn){
		this.ingestStatusIn = ingestStatusIn;
	}

	public void ingestStatusIn(String multirequestToken){
		setToken("ingestStatusIn", multirequestToken);
	}

	// startDateGreaterThan:
	public Long getStartDateGreaterThan(){
		return this.startDateGreaterThan;
	}
	public void setStartDateGreaterThan(Long startDateGreaterThan){
		this.startDateGreaterThan = startDateGreaterThan;
	}

	public void startDateGreaterThan(String multirequestToken){
		setToken("startDateGreaterThan", multirequestToken);
	}

	// startDateSmallerThan:
	public Long getStartDateSmallerThan(){
		return this.startDateSmallerThan;
	}
	public void setStartDateSmallerThan(Long startDateSmallerThan){
		this.startDateSmallerThan = startDateSmallerThan;
	}

	public void startDateSmallerThan(String multirequestToken){
		setToken("startDateSmallerThan", multirequestToken);
	}


	public IngestProgramResultsByRefineFilter() {
		super();
	}

	public IngestProgramResultsByRefineFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		ingestStatusIn = GsonParser.parseString(jsonObject.get("ingestStatusIn"));
		startDateGreaterThan = GsonParser.parseLong(jsonObject.get("startDateGreaterThan"));
		startDateSmallerThan = GsonParser.parseLong(jsonObject.get("startDateSmallerThan"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIngestProgramResultsByRefineFilter");
		kparams.add("ingestStatusIn", this.ingestStatusIn);
		kparams.add("startDateGreaterThan", this.startDateGreaterThan);
		kparams.add("startDateSmallerThan", this.startDateSmallerThan);
		return kparams;
	}

}

