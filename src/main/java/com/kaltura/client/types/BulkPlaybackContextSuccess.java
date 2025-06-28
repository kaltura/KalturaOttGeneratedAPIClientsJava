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
import com.kaltura.client.types.PlaybackContext;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Wrapper for KalturaPlaybackContext to make it compatible with bulk response
  operations
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkPlaybackContextSuccess.Tokenizer.class)
public class BulkPlaybackContextSuccess extends BulkResponseItem {
	
	public interface Tokenizer extends BulkResponseItem.Tokenizer {
		PlaybackContext.Tokenizer playbackContext();
	}

	/**
	 * The successful playback context
	 */
	private PlaybackContext playbackContext;

	// playbackContext:
	public PlaybackContext getPlaybackContext(){
		return this.playbackContext;
	}
	public void setPlaybackContext(PlaybackContext playbackContext){
		this.playbackContext = playbackContext;
	}


	public BulkPlaybackContextSuccess() {
		super();
	}

	public BulkPlaybackContextSuccess(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		playbackContext = GsonParser.parseObject(jsonObject.getAsJsonObject("playbackContext"), PlaybackContext.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkPlaybackContextSuccess");
		kparams.add("playbackContext", this.playbackContext);
		return kparams;
	}

}

