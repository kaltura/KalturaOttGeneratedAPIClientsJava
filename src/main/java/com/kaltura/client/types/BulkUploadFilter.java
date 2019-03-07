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
import com.kaltura.client.enums.DateComparisonType;
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
		String uploadedOnEqual();
		String dateComparisonType();
		String statusIn();
		String userIdEqualCurrent();
	}

	/**
	 * upload date to search within.
	 */
	private Long uploadedOnEqual;
	/**
	 * Date Comparison Type.
	 */
	private DateComparisonType dateComparisonType;
	/**
	 * List of KalturaBulkUploadJobStatus to search within.
	 */
	private String statusIn;
	/**
	 * Indicates if to get the BulkUpload list that created by current user or by the
	  entire group.
	 */
	private Boolean userIdEqualCurrent;

	// uploadedOnEqual:
	public Long getUploadedOnEqual(){
		return this.uploadedOnEqual;
	}
	public void setUploadedOnEqual(Long uploadedOnEqual){
		this.uploadedOnEqual = uploadedOnEqual;
	}

	public void uploadedOnEqual(String multirequestToken){
		setToken("uploadedOnEqual", multirequestToken);
	}

	// dateComparisonType:
	public DateComparisonType getDateComparisonType(){
		return this.dateComparisonType;
	}
	public void setDateComparisonType(DateComparisonType dateComparisonType){
		this.dateComparisonType = dateComparisonType;
	}

	public void dateComparisonType(String multirequestToken){
		setToken("dateComparisonType", multirequestToken);
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

	// userIdEqualCurrent:
	public Boolean getUserIdEqualCurrent(){
		return this.userIdEqualCurrent;
	}
	public void setUserIdEqualCurrent(Boolean userIdEqualCurrent){
		this.userIdEqualCurrent = userIdEqualCurrent;
	}

	public void userIdEqualCurrent(String multirequestToken){
		setToken("userIdEqualCurrent", multirequestToken);
	}


	public BulkUploadFilter() {
		super();
	}

	public BulkUploadFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		uploadedOnEqual = GsonParser.parseLong(jsonObject.get("uploadedOnEqual"));
		dateComparisonType = DateComparisonType.get(GsonParser.parseString(jsonObject.get("dateComparisonType")));
		statusIn = GsonParser.parseString(jsonObject.get("statusIn"));
		userIdEqualCurrent = GsonParser.parseBoolean(jsonObject.get("userIdEqualCurrent"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadFilter");
		kparams.add("uploadedOnEqual", this.uploadedOnEqual);
		kparams.add("dateComparisonType", this.dateComparisonType);
		kparams.add("statusIn", this.statusIn);
		kparams.add("userIdEqualCurrent", this.userIdEqualCurrent);
		return kparams;
	}

}

