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
import com.kaltura.client.enums.PlaybackContextType;
import com.kaltura.client.enums.UrlType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PlaybackContextOptions.Tokenizer.class)
public class PlaybackContextOptions extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String mediaProtocol();
		String streamerType();
		String assetFileIds();
		String context();
		String urlType();
	}

	/**
	 * Protocol of the specific media object (http / https).
	 */
	private String mediaProtocol;
	/**
	 * Playback streamer type: applehttp, mpegdash, url.
	 */
	private String streamerType;
	/**
	 * List of comma separated media file IDs
	 */
	private String assetFileIds;
	/**
	 * Playback context type
	 */
	private PlaybackContextType context;
	/**
	 * Url type
	 */
	private UrlType urlType;

	// mediaProtocol:
	public String getMediaProtocol(){
		return this.mediaProtocol;
	}
	public void setMediaProtocol(String mediaProtocol){
		this.mediaProtocol = mediaProtocol;
	}

	public void mediaProtocol(String multirequestToken){
		setToken("mediaProtocol", multirequestToken);
	}

	// streamerType:
	public String getStreamerType(){
		return this.streamerType;
	}
	public void setStreamerType(String streamerType){
		this.streamerType = streamerType;
	}

	public void streamerType(String multirequestToken){
		setToken("streamerType", multirequestToken);
	}

	// assetFileIds:
	public String getAssetFileIds(){
		return this.assetFileIds;
	}
	public void setAssetFileIds(String assetFileIds){
		this.assetFileIds = assetFileIds;
	}

	public void assetFileIds(String multirequestToken){
		setToken("assetFileIds", multirequestToken);
	}

	// context:
	public PlaybackContextType getContext(){
		return this.context;
	}
	public void setContext(PlaybackContextType context){
		this.context = context;
	}

	public void context(String multirequestToken){
		setToken("context", multirequestToken);
	}

	// urlType:
	public UrlType getUrlType(){
		return this.urlType;
	}
	public void setUrlType(UrlType urlType){
		this.urlType = urlType;
	}

	public void urlType(String multirequestToken){
		setToken("urlType", multirequestToken);
	}


	public PlaybackContextOptions() {
		super();
	}

	public PlaybackContextOptions(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		mediaProtocol = GsonParser.parseString(jsonObject.get("mediaProtocol"));
		streamerType = GsonParser.parseString(jsonObject.get("streamerType"));
		assetFileIds = GsonParser.parseString(jsonObject.get("assetFileIds"));
		context = PlaybackContextType.get(GsonParser.parseString(jsonObject.get("context")));
		urlType = UrlType.get(GsonParser.parseString(jsonObject.get("urlType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPlaybackContextOptions");
		kparams.add("mediaProtocol", this.mediaProtocol);
		kparams.add("streamerType", this.streamerType);
		kparams.add("assetFileIds", this.assetFileIds);
		kparams.add("context", this.context);
		kparams.add("urlType", this.urlType);
		return kparams;
	}

}

