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
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PlaybackContext.Tokenizer.class)
public class PlaybackContext extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		RequestBuilder.ListTokenizer<PlaybackSource.Tokenizer> sources();
		RequestBuilder.ListTokenizer<RuleAction.Tokenizer> actions();
		RequestBuilder.ListTokenizer<AccessControlMessage.Tokenizer> messages();
		RequestBuilder.ListTokenizer<CaptionPlaybackPluginData.Tokenizer> playbackCaptions();
		RequestBuilder.ListTokenizer<PlaybackPluginData.Tokenizer> plugins();
	}

	/**
	 * Sources
	 */
	private List<PlaybackSource> sources;
	/**
	 * Actions
	 */
	private List<RuleAction> actions;
	/**
	 * Messages
	 */
	private List<AccessControlMessage> messages;
	/**
	 * Playback captions
	 */
	private List<CaptionPlaybackPluginData> playbackCaptions;
	/**
	 * Plugins
	 */
	private List<PlaybackPluginData> plugins;

	// sources:
	public List<PlaybackSource> getSources(){
		return this.sources;
	}
	public void setSources(List<PlaybackSource> sources){
		this.sources = sources;
	}

	// actions:
	public List<RuleAction> getActions(){
		return this.actions;
	}
	public void setActions(List<RuleAction> actions){
		this.actions = actions;
	}

	// messages:
	public List<AccessControlMessage> getMessages(){
		return this.messages;
	}
	public void setMessages(List<AccessControlMessage> messages){
		this.messages = messages;
	}

	// playbackCaptions:
	public List<CaptionPlaybackPluginData> getPlaybackCaptions(){
		return this.playbackCaptions;
	}
	public void setPlaybackCaptions(List<CaptionPlaybackPluginData> playbackCaptions){
		this.playbackCaptions = playbackCaptions;
	}

	// plugins:
	public List<PlaybackPluginData> getPlugins(){
		return this.plugins;
	}
	public void setPlugins(List<PlaybackPluginData> plugins){
		this.plugins = plugins;
	}


	public PlaybackContext() {
		super();
	}

	public PlaybackContext(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		sources = GsonParser.parseArray(jsonObject.getAsJsonArray("sources"), PlaybackSource.class);
		actions = GsonParser.parseArray(jsonObject.getAsJsonArray("actions"), RuleAction.class);
		messages = GsonParser.parseArray(jsonObject.getAsJsonArray("messages"), AccessControlMessage.class);
		playbackCaptions = GsonParser.parseArray(jsonObject.getAsJsonArray("playbackCaptions"), CaptionPlaybackPluginData.class);
		plugins = GsonParser.parseArray(jsonObject.getAsJsonArray("plugins"), PlaybackPluginData.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPlaybackContext");
		kparams.add("sources", this.sources);
		kparams.add("actions", this.actions);
		kparams.add("messages", this.messages);
		kparams.add("playbackCaptions", this.playbackCaptions);
		kparams.add("plugins", this.plugins);
		return kparams;
	}

}

