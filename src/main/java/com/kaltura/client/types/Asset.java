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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;
import java.util.Map;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Asset.Tokenizer.class)
public abstract class Asset extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String type();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		RequestBuilder.ListTokenizer<MediaImage.Tokenizer> images();
		RequestBuilder.ListTokenizer<MediaFile.Tokenizer> mediaFiles();
		RequestBuilder.MapTokenizer<Value.Tokenizer> metas();
		RequestBuilder.MapTokenizer<MultilingualStringValueArray.Tokenizer> tags();
		String startDate();
		String endDate();
		String createDate();
		String updateDate();
		String externalId();
	}

	/**
	 * Unique identifier for the asset
	 */
	private Long id;
	/**
	 * Identifies the asset type (EPG, Recording, Movie, TV Series, etc).              
	  Possible values: 0 – EPG linear programs, 1 - Recording; or any asset type ID
	  according to the asset types IDs defined in the system.
	 */
	private Integer type;
	/**
	 * Asset name
	 */
	private String name;
	/**
	 * Asset name
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * Asset description
	 */
	private String description;
	/**
	 * Asset description
	 */
	private List<TranslationToken> multilingualDescription;
	/**
	 * Collection of images details that can be used to represent this asset
	 */
	private List<MediaImage> images;
	/**
	 * Files
	 */
	private List<MediaFile> mediaFiles;
	/**
	 * Dynamic collection of key-value pairs according to the String Meta defined in
	  the system
	 */
	private Map<String, Value> metas;
	/**
	 * Dynamic collection of key-value pairs according to the Tag Types defined in the
	  system
	 */
	private Map<String, MultilingualStringValueArray> tags;
	/**
	 * Date and time represented as epoch. For VOD – since when the asset is
	  available in the catalog. For EPG/Linear – when the program is aired (can be
	  in the future).
	 */
	private Long startDate;
	/**
	 * Date and time represented as epoch. For VOD – till when the asset be available
	  in the catalog. For EPG/Linear – program end time and date
	 */
	private Long endDate;
	/**
	 * Specifies when was the Asset was created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the Asset last updated. Date and time represented as epoch.
	 */
	private Long updateDate;
	/**
	 * External identifier for the asset
	 */
	private String externalId;

	// id:
	public Long getId(){
		return this.id;
	}
	// type:
	public Integer getType(){
		return this.type;
	}
	public void setType(Integer type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}

	// name:
	public String getName(){
		return this.name;
	}
	// multilingualName:
	public List<TranslationToken> getMultilingualName(){
		return this.multilingualName;
	}
	public void setMultilingualName(List<TranslationToken> multilingualName){
		this.multilingualName = multilingualName;
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	// multilingualDescription:
	public List<TranslationToken> getMultilingualDescription(){
		return this.multilingualDescription;
	}
	public void setMultilingualDescription(List<TranslationToken> multilingualDescription){
		this.multilingualDescription = multilingualDescription;
	}

	// images:
	public List<MediaImage> getImages(){
		return this.images;
	}
	// mediaFiles:
	public List<MediaFile> getMediaFiles(){
		return this.mediaFiles;
	}
	// metas:
	public Map<String, Value> getMetas(){
		return this.metas;
	}
	public void setMetas(Map<String, Value> metas){
		this.metas = metas;
	}

	// tags:
	public Map<String, MultilingualStringValueArray> getTags(){
		return this.tags;
	}
	public void setTags(Map<String, MultilingualStringValueArray> tags){
		this.tags = tags;
	}

	// startDate:
	public Long getStartDate(){
		return this.startDate;
	}
	public void setStartDate(Long startDate){
		this.startDate = startDate;
	}

	public void startDate(String multirequestToken){
		setToken("startDate", multirequestToken);
	}

	// endDate:
	public Long getEndDate(){
		return this.endDate;
	}
	public void setEndDate(Long endDate){
		this.endDate = endDate;
	}

	public void endDate(String multirequestToken){
		setToken("endDate", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// externalId:
	public String getExternalId(){
		return this.externalId;
	}
	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public void externalId(String multirequestToken){
		setToken("externalId", multirequestToken);
	}


	public Asset() {
		super();
	}

	public Asset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		type = GsonParser.parseInt(jsonObject.get("type"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		images = GsonParser.parseArray(jsonObject.getAsJsonArray("images"), MediaImage.class);
		mediaFiles = GsonParser.parseArray(jsonObject.getAsJsonArray("mediaFiles"), MediaFile.class);
		metas = GsonParser.parseMap(jsonObject.getAsJsonObject("metas"), Value.class);
		tags = GsonParser.parseMap(jsonObject.getAsJsonObject("tags"), MultilingualStringValueArray.class);
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAsset");
		kparams.add("type", this.type);
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("metas", this.metas);
		kparams.add("tags", this.tags);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("externalId", this.externalId);
		return kparams;
	}

}

