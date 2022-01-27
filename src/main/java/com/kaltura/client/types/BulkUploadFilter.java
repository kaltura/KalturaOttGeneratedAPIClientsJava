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
// Copyright (C) 2006-2021  Kaltura Inc.
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
 * Bulk Upload Filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkUploadFilter.Tokenizer.class)
public class BulkUploadFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String bulkObjectTypeEqual();
		String createDateGreaterThanOrEqual();
		String uploadedByUserIdEqualCurrent();
		String statusIn();
	}

	/**
	 * bulk objects Type name (must be type of KalturaOTTObject)
	 */
	private String bulkObjectTypeEqual;
	/**
	 * upload date to search within (search in the last 60 days)
	 */
	private Long createDateGreaterThanOrEqual;
	/**
	 * Indicates if to get the BulkUpload list that created by current user or by the
	  entire group.
	 */
	private Boolean uploadedByUserIdEqualCurrent;
	/**
	 * Comma separated list of BulkUpload Statuses to search\filter
	 */
	private String statusIn;

	// bulkObjectTypeEqual:
	public String getBulkObjectTypeEqual(){
		return this.bulkObjectTypeEqual;
	}
	public void setBulkObjectTypeEqual(String bulkObjectTypeEqual){
		this.bulkObjectTypeEqual = bulkObjectTypeEqual;
	}

	public void bulkObjectTypeEqual(String multirequestToken){
		setToken("bulkObjectTypeEqual", multirequestToken);
	}

	// createDateGreaterThanOrEqual:
	public Long getCreateDateGreaterThanOrEqual(){
		return this.createDateGreaterThanOrEqual;
	}
	public void setCreateDateGreaterThanOrEqual(Long createDateGreaterThanOrEqual){
		this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
	}

	public void createDateGreaterThanOrEqual(String multirequestToken){
		setToken("createDateGreaterThanOrEqual", multirequestToken);
	}

	// uploadedByUserIdEqualCurrent:
	public Boolean getUploadedByUserIdEqualCurrent(){
		return this.uploadedByUserIdEqualCurrent;
	}
	public void setUploadedByUserIdEqualCurrent(Boolean uploadedByUserIdEqualCurrent){
		this.uploadedByUserIdEqualCurrent = uploadedByUserIdEqualCurrent;
	}

	public void uploadedByUserIdEqualCurrent(String multirequestToken){
		setToken("uploadedByUserIdEqualCurrent", multirequestToken);
	}

	// statusIn:
	public String getStatusIn(){
		return this.statusIn;
	}
	public void setStatusIn(String statusIn){
		this.statusIn = statusIn;
	}

	public void statusIn(String multirequestToken){
		setToken("statusIn", multirequestToken);
	}


	public BulkUploadFilter() {
		super();
	}

	public BulkUploadFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		bulkObjectTypeEqual = GsonParser.parseString(jsonObject.get("bulkObjectTypeEqual"));
		createDateGreaterThanOrEqual = GsonParser.parseLong(jsonObject.get("createDateGreaterThanOrEqual"));
		uploadedByUserIdEqualCurrent = GsonParser.parseBoolean(jsonObject.get("uploadedByUserIdEqualCurrent"));
		statusIn = GsonParser.parseString(jsonObject.get("statusIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadFilter");
		kparams.add("bulkObjectTypeEqual", this.bulkObjectTypeEqual);
		kparams.add("createDateGreaterThanOrEqual", this.createDateGreaterThanOrEqual);
		kparams.add("uploadedByUserIdEqualCurrent", this.uploadedByUserIdEqualCurrent);
		kparams.add("statusIn", this.statusIn);
		return kparams;
	}

}

