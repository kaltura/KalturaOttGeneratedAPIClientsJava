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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Returns the KalturaLiveAsset object passed as input parameter if there is at
  least one associated KalturaRecordingAsset object. Returns empty array
  otherwise.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(LiveAssetHasRecordingsFilter.Tokenizer.class)
public class LiveAssetHasRecordingsFilter extends AssetFilter {
	
	public interface Tokenizer extends AssetFilter.Tokenizer {
		String liveAssetIdEqual();
	}

	/**
	 * KalturaLiveAsset.id value of the live linear channel to be examined for
	  associated recordings
	 */
	private Long liveAssetIdEqual;

	// liveAssetIdEqual:
	public Long getLiveAssetIdEqual(){
		return this.liveAssetIdEqual;
	}
	public void setLiveAssetIdEqual(Long liveAssetIdEqual){
		this.liveAssetIdEqual = liveAssetIdEqual;
	}

	public void liveAssetIdEqual(String multirequestToken){
		setToken("liveAssetIdEqual", multirequestToken);
	}


	public LiveAssetHasRecordingsFilter() {
		super();
	}

	public LiveAssetHasRecordingsFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		liveAssetIdEqual = GsonParser.parseLong(jsonObject.get("liveAssetIdEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLiveAssetHasRecordingsFilter");
		kparams.add("liveAssetIdEqual", this.liveAssetIdEqual);
		return kparams;
	}

}

