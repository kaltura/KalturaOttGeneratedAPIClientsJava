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
// Copyright (C) 2006-2020  Kaltura Inc.
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
@MultiRequestBuilder.Tokenizer(CategoryItem.Tokenizer.class)
public class CategoryItem extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String id();
		String name();
		String parentCategoryId();
		String childCategoriesIds();
		RequestBuilder.ListTokenizer<UnifiedChannel.Tokenizer> unifiedChannels();
		RequestBuilder.MapTokenizer<StringValue.Tokenizer> dynamicData();
	}

	/**
	 * Unique identifier for the category
	 */
	private Long id;
	/**
	 * Category name
	 */
	private String name;
	/**
	 * Category parent identifier
	 */
	private Long parentCategoryId;
	/**
	 * Comma separated list of child categories&amp;#39; Ids.
	 */
	private String childCategoriesIds;
	/**
	 * List of unified Channels.
	 */
	private List<UnifiedChannel> unifiedChannels;
	/**
	 * Dynamic data
	 */
	private Map<String, StringValue> dynamicData;

	// id:
	public Long getId(){
		return this.id;
	}
	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// parentCategoryId:
	public Long getParentCategoryId(){
		return this.parentCategoryId;
	}
	// childCategoriesIds:
	public String getChildCategoriesIds(){
		return this.childCategoriesIds;
	}
	public void setChildCategoriesIds(String childCategoriesIds){
		this.childCategoriesIds = childCategoriesIds;
	}

	public void childCategoriesIds(String multirequestToken){
		setToken("childCategoriesIds", multirequestToken);
	}

	// unifiedChannels:
	public List<UnifiedChannel> getUnifiedChannels(){
		return this.unifiedChannels;
	}
	public void setUnifiedChannels(List<UnifiedChannel> unifiedChannels){
		this.unifiedChannels = unifiedChannels;
	}

	// dynamicData:
	public Map<String, StringValue> getDynamicData(){
		return this.dynamicData;
	}
	public void setDynamicData(Map<String, StringValue> dynamicData){
		this.dynamicData = dynamicData;
	}


	public CategoryItem() {
		super();
	}

	public CategoryItem(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		parentCategoryId = GsonParser.parseLong(jsonObject.get("parentCategoryId"));
		childCategoriesIds = GsonParser.parseString(jsonObject.get("childCategoriesIds"));
		unifiedChannels = GsonParser.parseArray(jsonObject.getAsJsonArray("unifiedChannels"), UnifiedChannel.class);
		dynamicData = GsonParser.parseMap(jsonObject.getAsJsonObject("dynamicData"), StringValue.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCategoryItem");
		kparams.add("name", this.name);
		kparams.add("childCategoriesIds", this.childCategoriesIds);
		kparams.add("unifiedChannels", this.unifiedChannels);
		kparams.add("dynamicData", this.dynamicData);
		return kparams;
	}

}

