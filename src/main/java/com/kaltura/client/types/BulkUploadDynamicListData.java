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
// Copyright (C) 2006-2020  Kaltura Inc.
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
 * indicates the DynamicList object type in the bulk file
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkUploadDynamicListData.Tokenizer.class)
public abstract class BulkUploadDynamicListData extends BulkUploadObjectData {
	
	public interface Tokenizer extends BulkUploadObjectData.Tokenizer {
		String dynamicListId();
	}

	/**
	 * Identifies the dynamicList Id
	 */
	private Long dynamicListId;

	// dynamicListId:
	public Long getDynamicListId(){
		return this.dynamicListId;
	}
	public void setDynamicListId(Long dynamicListId){
		this.dynamicListId = dynamicListId;
	}

	public void dynamicListId(String multirequestToken){
		setToken("dynamicListId", multirequestToken);
	}


	public BulkUploadDynamicListData() {
		super();
	}

	public BulkUploadDynamicListData(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		dynamicListId = GsonParser.parseLong(jsonObject.get("dynamicListId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadDynamicListData");
		kparams.add("dynamicListId", this.dynamicListId);
		return kparams;
	}

}

