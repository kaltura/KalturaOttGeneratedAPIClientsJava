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
		String fileObjectNameEqual();
		String createDateGreaterThanOrEqual();
		String userIdEqualCurrent();
		String shouldGetOnGoingBulkUploads();
	}

	/**
	 * File&amp;#39;s objectType name (must be type of KalturaOTTObject)
	 */
	private String fileObjectNameEqual;
	/**
	 * upload date to search within (search in the last 60 days)
	 */
	private Long createDateGreaterThanOrEqual;
	/**
	 * Indicates if to get the BulkUpload list that created by current user or by the
	  entire group.
	 */
	private Boolean userIdEqualCurrent;
	/**
	 * Indicates if to get the BulkUpload list that are stil in OnGoing process or
	  finished.
	 */
	private Boolean shouldGetOnGoingBulkUploads;

	// fileObjectNameEqual:
	public String getFileObjectNameEqual(){
		return this.fileObjectNameEqual;
	}
	public void setFileObjectNameEqual(String fileObjectNameEqual){
		this.fileObjectNameEqual = fileObjectNameEqual;
	}

	public void fileObjectNameEqual(String multirequestToken){
		setToken("fileObjectNameEqual", multirequestToken);
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

	// shouldGetOnGoingBulkUploads:
	public Boolean getShouldGetOnGoingBulkUploads(){
		return this.shouldGetOnGoingBulkUploads;
	}
	public void setShouldGetOnGoingBulkUploads(Boolean shouldGetOnGoingBulkUploads){
		this.shouldGetOnGoingBulkUploads = shouldGetOnGoingBulkUploads;
	}

	public void shouldGetOnGoingBulkUploads(String multirequestToken){
		setToken("shouldGetOnGoingBulkUploads", multirequestToken);
	}


	public BulkUploadFilter() {
		super();
	}

	public BulkUploadFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		fileObjectNameEqual = GsonParser.parseString(jsonObject.get("fileObjectNameEqual"));
		createDateGreaterThanOrEqual = GsonParser.parseLong(jsonObject.get("createDateGreaterThanOrEqual"));
		userIdEqualCurrent = GsonParser.parseBoolean(jsonObject.get("userIdEqualCurrent"));
		shouldGetOnGoingBulkUploads = GsonParser.parseBoolean(jsonObject.get("shouldGetOnGoingBulkUploads"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadFilter");
		kparams.add("fileObjectNameEqual", this.fileObjectNameEqual);
		kparams.add("createDateGreaterThanOrEqual", this.createDateGreaterThanOrEqual);
		kparams.add("userIdEqualCurrent", this.userIdEqualCurrent);
		kparams.add("shouldGetOnGoingBulkUploads", this.shouldGetOnGoingBulkUploads);
		return kparams;
	}

}

