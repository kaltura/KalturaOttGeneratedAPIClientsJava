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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GenerateMetadataBySubtitles.Tokenizer.class)
public class GenerateMetadataBySubtitles extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String subtitlesFileId();
		RequestBuilder.ListTokenizer<StringValue.Tokenizer> externalAssetIds();
	}

	/**
	 * A mandatory Long type with the subtitles file ID returned from the
	  subtitles.uploadFile request.              It is used to correlate the uploaded
	  file with the metadata generation request.
	 */
	private Long subtitlesFileId;
	/**
	 * An optional array of KalturaStringValue specifying the target assets to which
	  the generated metadata will be pushed.
	 */
	private List<StringValue> externalAssetIds;

	// subtitlesFileId:
	public Long getSubtitlesFileId(){
		return this.subtitlesFileId;
	}
	public void setSubtitlesFileId(Long subtitlesFileId){
		this.subtitlesFileId = subtitlesFileId;
	}

	public void subtitlesFileId(String multirequestToken){
		setToken("subtitlesFileId", multirequestToken);
	}

	// externalAssetIds:
	public List<StringValue> getExternalAssetIds(){
		return this.externalAssetIds;
	}
	public void setExternalAssetIds(List<StringValue> externalAssetIds){
		this.externalAssetIds = externalAssetIds;
	}


	public GenerateMetadataBySubtitles() {
		super();
	}

	public GenerateMetadataBySubtitles(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		subtitlesFileId = GsonParser.parseLong(jsonObject.get("subtitlesFileId"));
		externalAssetIds = GsonParser.parseArray(jsonObject.getAsJsonArray("externalAssetIds"), StringValue.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGenerateMetadataBySubtitles");
		kparams.add("subtitlesFileId", this.subtitlesFileId);
		kparams.add("externalAssetIds", this.externalAssetIds);
		return kparams;
	}

}

