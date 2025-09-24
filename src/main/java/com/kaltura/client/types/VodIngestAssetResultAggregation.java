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
@MultiRequestBuilder.Tokenizer(VodIngestAssetResultAggregation.Tokenizer.class)
public class VodIngestAssetResultAggregation extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String ingestDateFrom();
		String ingestDateTo();
		String failureCount();
		String successCount();
		String externalFailureCount();
		String successWithWarningCount();
	}

	/**
	 * Ingest date of the first asset in the response list. Date and time represented
	  as epoch.
	 */
	private Long ingestDateFrom;
	/**
	 * Ingest date of the last asset in the response list. Date and time represented as
	  epoch.
	 */
	private Long ingestDateTo;
	/**
	 * Number of assets which failed ingest. Calculated on the assets returned
	  according to the applied filters.
	 */
	private Integer failureCount;
	/**
	 * Number of assets which succeeded ingest without any warning. Calculated on the
	  assets returned according to the applied filters.
	 */
	private Integer successCount;
	/**
	 * Number of files (not assets) which failed ingest and are reported by external
	  none-WS_ingest entity. Calculated on the failed files returned according to the
	  applied filters.
	 */
	private Integer externalFailureCount;
	/**
	 * Number of assets which succeeded ingest, but with warnings. Calculated on the
	  assets returned according to the applied filters.
	 */
	private Integer successWithWarningCount;

	// ingestDateFrom:
	public Long getIngestDateFrom(){
		return this.ingestDateFrom;
	}
	public void setIngestDateFrom(Long ingestDateFrom){
		this.ingestDateFrom = ingestDateFrom;
	}

	public void ingestDateFrom(String multirequestToken){
		setToken("ingestDateFrom", multirequestToken);
	}

	// ingestDateTo:
	public Long getIngestDateTo(){
		return this.ingestDateTo;
	}
	public void setIngestDateTo(Long ingestDateTo){
		this.ingestDateTo = ingestDateTo;
	}

	public void ingestDateTo(String multirequestToken){
		setToken("ingestDateTo", multirequestToken);
	}

	// failureCount:
	public Integer getFailureCount(){
		return this.failureCount;
	}
	public void setFailureCount(Integer failureCount){
		this.failureCount = failureCount;
	}

	public void failureCount(String multirequestToken){
		setToken("failureCount", multirequestToken);
	}

	// successCount:
	public Integer getSuccessCount(){
		return this.successCount;
	}
	public void setSuccessCount(Integer successCount){
		this.successCount = successCount;
	}

	public void successCount(String multirequestToken){
		setToken("successCount", multirequestToken);
	}

	// externalFailureCount:
	public Integer getExternalFailureCount(){
		return this.externalFailureCount;
	}
	public void setExternalFailureCount(Integer externalFailureCount){
		this.externalFailureCount = externalFailureCount;
	}

	public void externalFailureCount(String multirequestToken){
		setToken("externalFailureCount", multirequestToken);
	}

	// successWithWarningCount:
	public Integer getSuccessWithWarningCount(){
		return this.successWithWarningCount;
	}
	public void setSuccessWithWarningCount(Integer successWithWarningCount){
		this.successWithWarningCount = successWithWarningCount;
	}

	public void successWithWarningCount(String multirequestToken){
		setToken("successWithWarningCount", multirequestToken);
	}


	public VodIngestAssetResultAggregation() {
		super();
	}

	public VodIngestAssetResultAggregation(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		ingestDateFrom = GsonParser.parseLong(jsonObject.get("ingestDateFrom"));
		ingestDateTo = GsonParser.parseLong(jsonObject.get("ingestDateTo"));
		failureCount = GsonParser.parseInt(jsonObject.get("failureCount"));
		successCount = GsonParser.parseInt(jsonObject.get("successCount"));
		externalFailureCount = GsonParser.parseInt(jsonObject.get("externalFailureCount"));
		successWithWarningCount = GsonParser.parseInt(jsonObject.get("successWithWarningCount"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaVodIngestAssetResultAggregation");
		kparams.add("ingestDateFrom", this.ingestDateFrom);
		kparams.add("ingestDateTo", this.ingestDateTo);
		kparams.add("failureCount", this.failureCount);
		kparams.add("successCount", this.successCount);
		kparams.add("externalFailureCount", this.externalFailureCount);
		kparams.add("successWithWarningCount", this.successWithWarningCount);
		return kparams;
	}

}

