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
import com.kaltura.client.types.MetadataFieldConfig;
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
  assetStruct with configuration
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MetadataFieldConfigurationMap.Tokenizer.class)
public class MetadataFieldConfigurationMap extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		MetadataFieldConfig.Tokenizer genre();
		MetadataFieldConfig.Tokenizer subGenre();
		MetadataFieldConfig.Tokenizer sentiment();
		MetadataFieldConfig.Tokenizer suggestedTitle();
		MetadataFieldConfig.Tokenizer description();
		MetadataFieldConfig.Tokenizer oneLiner();
		MetadataFieldConfig.Tokenizer keywords();
		MetadataFieldConfig.Tokenizer sensitiveContent();
	}

	/**
	 * Configuration for &amp;#39;genre&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig genre;
	/**
	 * Configuration for &amp;#39;subGenre&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig subGenre;
	/**
	 * Configuration for &amp;#39;sentiment&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig sentiment;
	/**
	 * Configuration for &amp;#39;suggestedTitle&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig suggestedTitle;
	/**
	 * Configuration for &amp;#39;Description&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig description;
	/**
	 * Configuration for &amp;#39;oneLiner&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig oneLiner;
	/**
	 * Configuration for &amp;#39;Keywords&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig keywords;
	/**
	 * Configuration for &amp;#39;sensitiveContent&amp;#39; AI generated metadata field
	 */
	private MetadataFieldConfig sensitiveContent;

	// genre:
	public MetadataFieldConfig getGenre(){
		return this.genre;
	}
	public void setGenre(MetadataFieldConfig genre){
		this.genre = genre;
	}

	// subGenre:
	public MetadataFieldConfig getSubGenre(){
		return this.subGenre;
	}
	public void setSubGenre(MetadataFieldConfig subGenre){
		this.subGenre = subGenre;
	}

	// sentiment:
	public MetadataFieldConfig getSentiment(){
		return this.sentiment;
	}
	public void setSentiment(MetadataFieldConfig sentiment){
		this.sentiment = sentiment;
	}

	// suggestedTitle:
	public MetadataFieldConfig getSuggestedTitle(){
		return this.suggestedTitle;
	}
	public void setSuggestedTitle(MetadataFieldConfig suggestedTitle){
		this.suggestedTitle = suggestedTitle;
	}

	// description:
	public MetadataFieldConfig getDescription(){
		return this.description;
	}
	public void setDescription(MetadataFieldConfig description){
		this.description = description;
	}

	// oneLiner:
	public MetadataFieldConfig getOneLiner(){
		return this.oneLiner;
	}
	public void setOneLiner(MetadataFieldConfig oneLiner){
		this.oneLiner = oneLiner;
	}

	// keywords:
	public MetadataFieldConfig getKeywords(){
		return this.keywords;
	}
	public void setKeywords(MetadataFieldConfig keywords){
		this.keywords = keywords;
	}

	// sensitiveContent:
	public MetadataFieldConfig getSensitiveContent(){
		return this.sensitiveContent;
	}
	public void setSensitiveContent(MetadataFieldConfig sensitiveContent){
		this.sensitiveContent = sensitiveContent;
	}


	public MetadataFieldConfigurationMap() {
		super();
	}

	public MetadataFieldConfigurationMap(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		genre = GsonParser.parseObject(jsonObject.getAsJsonObject("genre"), MetadataFieldConfig.class);
		subGenre = GsonParser.parseObject(jsonObject.getAsJsonObject("subGenre"), MetadataFieldConfig.class);
		sentiment = GsonParser.parseObject(jsonObject.getAsJsonObject("sentiment"), MetadataFieldConfig.class);
		suggestedTitle = GsonParser.parseObject(jsonObject.getAsJsonObject("suggestedTitle"), MetadataFieldConfig.class);
		description = GsonParser.parseObject(jsonObject.getAsJsonObject("description"), MetadataFieldConfig.class);
		oneLiner = GsonParser.parseObject(jsonObject.getAsJsonObject("oneLiner"), MetadataFieldConfig.class);
		keywords = GsonParser.parseObject(jsonObject.getAsJsonObject("keywords"), MetadataFieldConfig.class);
		sensitiveContent = GsonParser.parseObject(jsonObject.getAsJsonObject("sensitiveContent"), MetadataFieldConfig.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMetadataFieldConfigurationMap");
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

