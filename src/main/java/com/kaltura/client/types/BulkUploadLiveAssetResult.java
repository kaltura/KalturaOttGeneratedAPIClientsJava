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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkUploadLiveAssetResult.Tokenizer.class)
public class BulkUploadLiveAssetResult extends BulkUploadResult {
	
	public interface Tokenizer extends BulkUploadResult.Tokenizer {
		String id();
		String externalEpgIngestId();
		RequestBuilder.ListTokenizer<BulkUploadProgramAssetResult.Tokenizer> programs();
	}

	/**
	 * The internal kaltura channel id
	 */
	private Integer id;
	/**
	 * Indicates the epg asset object id in the bulk file
	 */
	private String externalEpgIngestId;
	/**
	 * List of programs that were ingested to the channel
	 */
	private List<BulkUploadProgramAssetResult> programs;

	// id:
	public Integer getId(){
		return this.id;
	}
	// externalEpgIngestId:
	public String getExternalEpgIngestId(){
		return this.externalEpgIngestId;
	}
	// programs:
	public List<BulkUploadProgramAssetResult> getPrograms(){
		return this.programs;
	}

	public BulkUploadLiveAssetResult() {
		super();
	}

	public BulkUploadLiveAssetResult(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		externalEpgIngestId = GsonParser.parseString(jsonObject.get("externalEpgIngestId"));
		programs = GsonParser.parseArray(jsonObject.getAsJsonArray("programs"), BulkUploadProgramAssetResult.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadLiveAssetResult");
		return kparams;
	}

}

