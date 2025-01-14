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
 * For each metadata type, defines its system name in the partner
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MetaFieldNameMap.Tokenizer.class)
public class MetaFieldNameMap extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String genre();
		String sentiment();
		String shortDescription();
		String longDescription();
		String oneLiner();
		String keywords();
	}

	/**
	 * Genre
	 */
	private String genre;
	/**
	 * Sentiment
	 */
	private String sentiment;
	/**
	 * Short Description
	 */
	private String shortDescription;
	/**
	 * Long Description
	 */
	private String longDescription;
	/**
	 * One Liner
	 */
	private String oneLiner;
	/**
	 * Keywords
	 */
	private String keywords;

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

	// shortDescription:
	public String getShortDescription(){
		return this.shortDescription;
	}
	public void setShortDescription(String shortDescription){
		this.shortDescription = shortDescription;
	}

	public void shortDescription(String multirequestToken){
		setToken("shortDescription", multirequestToken);
	}

	// longDescription:
	public String getLongDescription(){
		return this.longDescription;
	}
	public void setLongDescription(String longDescription){
		this.longDescription = longDescription;
	}

	public void longDescription(String multirequestToken){
		setToken("longDescription", multirequestToken);
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


	public MetaFieldNameMap() {
		super();
	}

	public MetaFieldNameMap(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		genre = GsonParser.parseString(jsonObject.get("genre"));
		sentiment = GsonParser.parseString(jsonObject.get("sentiment"));
		shortDescription = GsonParser.parseString(jsonObject.get("shortDescription"));
		longDescription = GsonParser.parseString(jsonObject.get("longDescription"));
		oneLiner = GsonParser.parseString(jsonObject.get("oneLiner"));
		keywords = GsonParser.parseString(jsonObject.get("keywords"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMetaFieldNameMap");
		kparams.add("genre", this.genre);
		kparams.add("sentiment", this.sentiment);
		kparams.add("shortDescription", this.shortDescription);
		kparams.add("longDescription", this.longDescription);
		kparams.add("oneLiner", this.oneLiner);
		kparams.add("keywords", this.keywords);
		return kparams;
	}

}

