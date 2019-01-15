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
// Copyright (C) 2006-2018  Kaltura Inc.
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
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BumpersPlaybackPluginData.Tokenizer.class)
public class BumpersPlaybackPluginData extends PlaybackPluginData {
	
	public interface Tokenizer extends PlaybackPluginData.Tokenizer {
		String url();
		String streamertype();
	}

	/**
	 * url
	 */
	private String url;
	/**
	 * Streamer type: hls, dash, progressive.
	 */
	private String streamertype;

	// url:
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}

	public void url(String multirequestToken){
		setToken("url", multirequestToken);
	}

	// streamertype:
	public String getStreamertype(){
		return this.streamertype;
	}
	public void setStreamertype(String streamertype){
		this.streamertype = streamertype;
	}

	public void streamertype(String multirequestToken){
		setToken("streamertype", multirequestToken);
	}


	public BumpersPlaybackPluginData() {
		super();
	}

	public BumpersPlaybackPluginData(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		url = GsonParser.parseString(jsonObject.get("url"));
		streamertype = GsonParser.parseString(jsonObject.get("streamertype"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBumpersPlaybackPluginData");
		kparams.add("url", this.url);
		kparams.add("streamertype", this.streamertype);
		return kparams;
	}

}

