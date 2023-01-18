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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AggregatedIngestInfo.Tokenizer.class)
public class AggregatedIngestInfo extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String resultsCount();
		String totalFailureCount();
		String partialFailureCount();
		String warningsCount();
	}

	/**
	 * Number of results
	 */
	private Long resultsCount;
	/**
	 * Number of results that include at least one error of severity TotalFailure
	 */
	private Long totalFailureCount;
	/**
	 * Number of results that include no error with severity TotalFailure but at at
	  least one error of severity PartialFailure
	 */
	private Long partialFailureCount;
	/**
	 * Number of results that include at least one warning
	 */
	private Long warningsCount;

	// resultsCount:
	public Long getResultsCount(){
		return this.resultsCount;
	}
	public void setResultsCount(Long resultsCount){
		this.resultsCount = resultsCount;
	}

	public void resultsCount(String multirequestToken){
		setToken("resultsCount", multirequestToken);
	}

	// totalFailureCount:
	public Long getTotalFailureCount(){
		return this.totalFailureCount;
	}
	public void setTotalFailureCount(Long totalFailureCount){
		this.totalFailureCount = totalFailureCount;
	}

	public void totalFailureCount(String multirequestToken){
		setToken("totalFailureCount", multirequestToken);
	}

	// partialFailureCount:
	public Long getPartialFailureCount(){
		return this.partialFailureCount;
	}
	public void setPartialFailureCount(Long partialFailureCount){
		this.partialFailureCount = partialFailureCount;
	}

	public void partialFailureCount(String multirequestToken){
		setToken("partialFailureCount", multirequestToken);
	}

	// warningsCount:
	public Long getWarningsCount(){
		return this.warningsCount;
	}
	public void setWarningsCount(Long warningsCount){
		this.warningsCount = warningsCount;
	}

	public void warningsCount(String multirequestToken){
		setToken("warningsCount", multirequestToken);
	}


	public AggregatedIngestInfo() {
		super();
	}

	public AggregatedIngestInfo(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		resultsCount = GsonParser.parseLong(jsonObject.get("resultsCount"));
		totalFailureCount = GsonParser.parseLong(jsonObject.get("totalFailureCount"));
		partialFailureCount = GsonParser.parseLong(jsonObject.get("partialFailureCount"));
		warningsCount = GsonParser.parseLong(jsonObject.get("warningsCount"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAggregatedIngestInfo");
		kparams.add("resultsCount", this.resultsCount);
		kparams.add("totalFailureCount", this.totalFailureCount);
		kparams.add("partialFailureCount", this.partialFailureCount);
		kparams.add("warningsCount", this.warningsCount);
		return kparams;
	}

}

