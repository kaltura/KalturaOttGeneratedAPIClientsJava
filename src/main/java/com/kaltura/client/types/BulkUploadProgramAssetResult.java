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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkUploadProgramAssetResult.Tokenizer.class)
public class BulkUploadProgramAssetResult extends BulkUploadResult {
	
	public interface Tokenizer extends BulkUploadResult.Tokenizer {
		String programId();
		String programExternalId();
		String liveAssetId();
	}

	/**
	 * The programID that was created
	 */
	private Integer programId;
	/**
	 * The external program Id as was sent in the bulk xml file
	 */
	private String programExternalId;
	/**
	 * The  live asset Id that was identified according liveAssetExternalId that was
	  sent in bulk xml file
	 */
	private Integer liveAssetId;

	// programId:
	public Integer getProgramId(){
		return this.programId;
	}
	// programExternalId:
	public String getProgramExternalId(){
		return this.programExternalId;
	}
	// liveAssetId:
	public Integer getLiveAssetId(){
		return this.liveAssetId;
	}

	public BulkUploadProgramAssetResult() {
		super();
	}

	public BulkUploadProgramAssetResult(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		programId = GsonParser.parseInt(jsonObject.get("programId"));
		programExternalId = GsonParser.parseString(jsonObject.get("programExternalId"));
		liveAssetId = GsonParser.parseInt(jsonObject.get("liveAssetId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadProgramAssetResult");
		return kparams;
	}

}

