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
@MultiRequestBuilder.Tokenizer(CaptionPlaybackPluginData.Tokenizer.class)
public class CaptionPlaybackPluginData extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String url();
		String language();
		String label();
		String format();
	}

	/**
	 * url
	 */
	private String url;
	/**
	 * Language
	 */
	private String language;
	/**
	 * Label
	 */
	private String label;
	/**
	 * Format
	 */
	private String format;

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

	// language:
	public String getLanguage(){
		return this.language;
	}
	public void setLanguage(String language){
		this.language = language;
	}

	public void language(String multirequestToken){
		setToken("language", multirequestToken);
	}

	// label:
	public String getLabel(){
		return this.label;
	}
	public void setLabel(String label){
		this.label = label;
	}

	public void label(String multirequestToken){
		setToken("label", multirequestToken);
	}

	// format:
	public String getFormat(){
		return this.format;
	}
	public void setFormat(String format){
		this.format = format;
	}

	public void format(String multirequestToken){
		setToken("format", multirequestToken);
	}


	public CaptionPlaybackPluginData() {
		super();
	}

	public CaptionPlaybackPluginData(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		url = GsonParser.parseString(jsonObject.get("url"));
		language = GsonParser.parseString(jsonObject.get("language"));
		label = GsonParser.parseString(jsonObject.get("label"));
		format = GsonParser.parseString(jsonObject.get("format"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCaptionPlaybackPluginData");
		kparams.add("url", this.url);
		kparams.add("language", this.language);
		kparams.add("label", this.label);
		kparams.add("format", this.format);
		return kparams;
	}

}

