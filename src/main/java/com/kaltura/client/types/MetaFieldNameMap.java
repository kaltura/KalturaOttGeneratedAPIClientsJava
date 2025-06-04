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

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Map a newly generated metadata field to an existing meta field on the
  assetStruct
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MetaFieldNameMap.Tokenizer.class)
public class MetaFieldNameMap extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String genre();
		String subGenre();
		String sentiment();
		String suggestedTitle();
		String description();
		String oneLiner();
		String keywords();
		String sensitiveContent();
	}

	/**
	 * map &amp;#39;genre&amp;#39; AI generated metadata name to assetStruct&amp;#39;s
	  meta systemName
	 */
	private String genre;
	/**
	 * map &amp;#39;subGenre&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String subGenre;
	/**
	 * map &amp;#39;sentiment&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String sentiment;
	/**
	 * map &amp;#39;suggestedTitle&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String suggestedTitle;
	/**
	 * map &amp;#39;Description&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String description;
	/**
	 * map &amp;#39;oneLiner&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String oneLiner;
	/**
	 * map &amp;#39;Keywords&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String keywords;
	/**
	 * map &amp;#39;sensitiveContent&amp;#39; AI generated metadata name to
	  assetStruct&amp;#39;s meta systemName
	 */
	private String sensitiveContent;

	// genre:
	public String getGenre(){
		return this.genre;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}

	public void genre(String multirequestToken){
		setToken("genre", multirequestToken);
	}

	// subGenre:
	public String getSubGenre(){
		return this.subGenre;
	}
	public void setSubGenre(String subGenre){
		this.subGenre = subGenre;
	}

	public void subGenre(String multirequestToken){
		setToken("subGenre", multirequestToken);
	}

	// sentiment:
	public String getSentiment(){
		return this.sentiment;
	}
	public void setSentiment(String sentiment){
		this.sentiment = sentiment;
	}

	public void sentiment(String multirequestToken){
		setToken("sentiment", multirequestToken);
	}

	// suggestedTitle:
	public String getSuggestedTitle(){
		return this.suggestedTitle;
	}
	public void setSuggestedTitle(String suggestedTitle){
		this.suggestedTitle = suggestedTitle;
	}

	public void suggestedTitle(String multirequestToken){
		setToken("suggestedTitle", multirequestToken);
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public void description(String multirequestToken){
		setToken("description", multirequestToken);
	}

	// oneLiner:
	public String getOneLiner(){
		return this.oneLiner;
	}
	public void setOneLiner(String oneLiner){
		this.oneLiner = oneLiner;
	}

	public void oneLiner(String multirequestToken){
		setToken("oneLiner", multirequestToken);
	}

	// keywords:
	public String getKeywords(){
		return this.keywords;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}

	public void keywords(String multirequestToken){
		setToken("keywords", multirequestToken);
	}

	// sensitiveContent:
	public String getSensitiveContent(){
		return this.sensitiveContent;
	}
	public void setSensitiveContent(String sensitiveContent){
		this.sensitiveContent = sensitiveContent;
	}

	public void sensitiveContent(String multirequestToken){
		setToken("sensitiveContent", multirequestToken);
	}


	public MetaFieldNameMap() {
		super();
	}

	public MetaFieldNameMap(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		genre = GsonParser.parseString(jsonObject.get("genre"));
		subGenre = GsonParser.parseString(jsonObject.get("subGenre"));
		sentiment = GsonParser.parseString(jsonObject.get("sentiment"));
		suggestedTitle = GsonParser.parseString(jsonObject.get("suggestedTitle"));
		description = GsonParser.parseString(jsonObject.get("description"));
		oneLiner = GsonParser.parseString(jsonObject.get("oneLiner"));
		keywords = GsonParser.parseString(jsonObject.get("keywords"));
		sensitiveContent = GsonParser.parseString(jsonObject.get("sensitiveContent"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMetaFieldNameMap");
		kparams.add("genre", this.genre);
		kparams.add("subGenre", this.subGenre);
		kparams.add("sentiment", this.sentiment);
		kparams.add("suggestedTitle", this.suggestedTitle);
		kparams.add("description", this.description);
		kparams.add("oneLiner", this.oneLiner);
		kparams.add("keywords", this.keywords);
		kparams.add("sensitiveContent", this.sensitiveContent);
		return kparams;
	}

}

