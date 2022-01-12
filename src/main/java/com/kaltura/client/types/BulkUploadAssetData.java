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

/**
 * indicates the asset object type in the bulk file
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkUploadAssetData.Tokenizer.class)
public abstract class BulkUploadAssetData extends BulkUploadObjectData {
	
	public interface Tokenizer extends BulkUploadObjectData.Tokenizer {
		String typeId();
	}

	/**
	 * Identifies the asset type (EPG, Recording, Movie, TV Series, etc).              
	  Possible values: 0 – EPG linear programs, 1 - Recording; or any asset type ID
	  according to the asset types IDs defined in the system.
	 */
	private Long typeId;

	// typeId:
	public Long getTypeId(){
		return this.typeId;
	}
	public void setTypeId(Long typeId){
		this.typeId = typeId;
	}

	public void typeId(String multirequestToken){
		setToken("typeId", multirequestToken);
	}


	public BulkUploadAssetData() {
		super();
	}

	public BulkUploadAssetData(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		typeId = GsonParser.parseLong(jsonObject.get("typeId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadAssetData");
		kparams.add("typeId", this.typeId);
		return kparams;
	}

}

