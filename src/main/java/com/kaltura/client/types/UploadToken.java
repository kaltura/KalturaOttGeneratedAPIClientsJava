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
import com.kaltura.client.enums.UploadTokenStatus;
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
@MultiRequestBuilder.Tokenizer(UploadToken.Tokenizer.class)
public class UploadToken extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String status();
		String fileSize();
		String createDate();
		String updateDate();
	}

	/**
	 * Upload-token identifier
	 */
	private String id;
	/**
	 * Status
	 */
	private UploadTokenStatus status;
	/**
	 * File size
	 */
	private Double fileSize;
	/**
	 * Specifies when was the Asset was created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the Asset last updated. Date and time represented as epoch.
	 */
	private Long updateDate;

	// id:
	public String getId(){
		return this.id;
	}
	// status:
	public UploadTokenStatus getStatus(){
		return this.status;
	}
	// fileSize:
	public Double getFileSize(){
		return this.fileSize;
	}
	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}

	public UploadToken() {
		super();
	}

	public UploadToken(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		status = UploadTokenStatus.get(GsonParser.parseString(jsonObject.get("status")));
		fileSize = GsonParser.parseDouble(jsonObject.get("fileSize"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUploadToken");
		return kparams;
	}

}

