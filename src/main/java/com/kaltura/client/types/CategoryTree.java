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
// Copyright (C) 2006-2021  Kaltura Inc.
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
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Category details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CategoryTree.Tokenizer.class)
public class CategoryTree extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		RequestBuilder.ListTokenizer<CategoryTree.Tokenizer> children();
		RequestBuilder.ListTokenizer<UnifiedChannelInfo.Tokenizer> unifiedChannels();
		RequestBuilder.MapTokenizer<StringValue.Tokenizer> dynamicData();
		RequestBuilder.ListTokenizer<Image.Tokenizer> images();
		String isActive();
		String startDateInSeconds();
		String endDateInSeconds();
		String type();
		String versionId();
		String virtualAssetId();
		String referenceId();
	}

	/**
	 * Unique identifier for the category item
	 */
	private Long id;
	/**
	 * Category name
	 */
	private String name;
	/**
	 * Category name
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * List of category tree
	 */
	private List<CategoryTree> children;
	/**
	 * List of unified Channels.
	 */
	private List<UnifiedChannelInfo> unifiedChannels;
	/**
	 * Dynamic data
	 */
	private Map<String, StringValue> dynamicData;
	/**
	 * Category images
	 */
	private List<Image> images;
	/**
	 * Category active status
	 */
	private Boolean isActive;
	/**
	 * Start date in seconds
	 */
	private Long startDateInSeconds;
	/**
	 * End date in seconds
	 */
	private Long endDateInSeconds;
	/**
	 * Category type
	 */
	private String type;
	/**
	 * Unique identifier for the category version
	 */
	private Long versionId;
	/**
	 * Virtual asset id
	 */
	private Long virtualAssetId;
	/**
	 * Category reference identifier
	 */
	private String referenceId;

	// id:
	public Long getId(){
		return this.id;
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

	// children:
	public List<CategoryTree> getChildren(){
		return this.children;
	}
	// unifiedChannels:
	public List<UnifiedChannelInfo> getUnifiedChannels(){
		return this.unifiedChannels;
	}
	public void setUnifiedChannels(List<UnifiedChannelInfo> unifiedChannels){
		this.unifiedChannels = unifiedChannels;
	}

	// dynamicData:
	public Map<String, StringValue> getDynamicData(){
		return this.dynamicData;
	}
	public void setDynamicData(Map<String, StringValue> dynamicData){
		this.dynamicData = dynamicData;
	}

	// images:
	public List<Image> getImages(){
		return this.images;
	}
	public void setImages(List<Image> images){
		this.images = images;
	}

	// isActive:
	public Boolean getIsActive(){
		return this.isActive;
	}
	public void setIsActive(Boolean isActive){
		this.isActive = isActive;
	}

	public void isActive(String multirequestToken){
		setToken("isActive", multirequestToken);
	}

	// startDateInSeconds:
	public Long getStartDateInSeconds(){
		return this.startDateInSeconds;
	}
	public void setStartDateInSeconds(Long startDateInSeconds){
		this.startDateInSeconds = startDateInSeconds;
	}

	public void startDateInSeconds(String multirequestToken){
		setToken("startDateInSeconds", multirequestToken);
	}

	// endDateInSeconds:
	public Long getEndDateInSeconds(){
		return this.endDateInSeconds;
	}
	public void setEndDateInSeconds(Long endDateInSeconds){
		this.endDateInSeconds = endDateInSeconds;
	}

	public void endDateInSeconds(String multirequestToken){
		setToken("endDateInSeconds", multirequestToken);
	}

	// type:
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}

	// versionId:
	public Long getVersionId(){
		return this.versionId;
	}
	// virtualAssetId:
	public Long getVirtualAssetId(){
		return this.virtualAssetId;
	}
	// referenceId:
	public String getReferenceId(){
		return this.referenceId;
	}

	public CategoryTree() {
		super();
	}

	public CategoryTree(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		children = GsonParser.parseArray(jsonObject.getAsJsonArray("children"), CategoryTree.class);
		unifiedChannels = GsonParser.parseArray(jsonObject.getAsJsonArray("unifiedChannels"), UnifiedChannelInfo.class);
		dynamicData = GsonParser.parseMap(jsonObject.getAsJsonObject("dynamicData"), StringValue.class);
		images = GsonParser.parseArray(jsonObject.getAsJsonArray("images"), Image.class);
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));
		startDateInSeconds = GsonParser.parseLong(jsonObject.get("startDateInSeconds"));
		endDateInSeconds = GsonParser.parseLong(jsonObject.get("endDateInSeconds"));
		type = GsonParser.parseString(jsonObject.get("type"));
		versionId = GsonParser.parseLong(jsonObject.get("versionId"));
		virtualAssetId = GsonParser.parseLong(jsonObject.get("virtualAssetId"));
		referenceId = GsonParser.parseString(jsonObject.get("referenceId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCategoryTree");
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("unifiedChannels", this.unifiedChannels);
		kparams.add("dynamicData", this.dynamicData);
		kparams.add("images", this.images);
		kparams.add("isActive", this.isActive);
		kparams.add("startDateInSeconds", this.startDateInSeconds);
		kparams.add("endDateInSeconds", this.endDateInSeconds);
		kparams.add("type", this.type);
		return kparams;
	}

}

