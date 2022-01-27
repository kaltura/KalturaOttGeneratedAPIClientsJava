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
import com.kaltura.client.enums.CategoryVersionState;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

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
@MultiRequestBuilder.Tokenizer(CategoryVersion.Tokenizer.class)
public class CategoryVersion extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String id();
		String name();
		String treeId();
		String state();
		String baseVersionId();
		String categoryRootId();
		String defaultDate();
		String updaterId();
		String comment();
		String createDate();
		String updateDate();
	}

	/**
	 * Unique identifier for the category version
	 */
	private Long id;
	/**
	 * Category version name
	 */
	private String name;
	/**
	 * Category tree identifier
	 */
	private Long treeId;
	/**
	 * The category version state
	 */
	private CategoryVersionState state;
	/**
	 * The version id that this version was created from
	 */
	private Long baseVersionId;
	/**
	 * The root of category item id that was created for this version
	 */
	private Long categoryRootId;
	/**
	 * The date that this version became default represented as epoch.
	 */
	private Long defaultDate;
	/**
	 * Last updater user id.
	 */
	private Long updaterId;
	/**
	 * Comment.
	 */
	private String comment;
	/**
	 * The date that this version was created represented as epoch.
	 */
	private Long createDate;
	/**
	 * The date that this version was last updated represented as epoch.
	 */
	private Long updateDate;

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

	// treeId:
	public Long getTreeId(){
		return this.treeId;
	}
	// state:
	public CategoryVersionState getState(){
		return this.state;
	}
	// baseVersionId:
	public Long getBaseVersionId(){
		return this.baseVersionId;
	}
	public void setBaseVersionId(Long baseVersionId){
		this.baseVersionId = baseVersionId;
	}

	public void baseVersionId(String multirequestToken){
		setToken("baseVersionId", multirequestToken);
	}

	// categoryRootId:
	public Long getCategoryRootId(){
		return this.categoryRootId;
	}
	// defaultDate:
	public Long getDefaultDate(){
		return this.defaultDate;
	}
	// updaterId:
	public Long getUpdaterId(){
		return this.updaterId;
	}
	// comment:
	public String getComment(){
		return this.comment;
	}
	public void setComment(String comment){
		this.comment = comment;
	}

	public void comment(String multirequestToken){
		setToken("comment", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}

	public CategoryVersion() {
		super();
	}

	public CategoryVersion(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		treeId = GsonParser.parseLong(jsonObject.get("treeId"));
		state = CategoryVersionState.get(GsonParser.parseString(jsonObject.get("state")));
		baseVersionId = GsonParser.parseLong(jsonObject.get("baseVersionId"));
		categoryRootId = GsonParser.parseLong(jsonObject.get("categoryRootId"));
		defaultDate = GsonParser.parseLong(jsonObject.get("defaultDate"));
		updaterId = GsonParser.parseLong(jsonObject.get("updaterId"));
		comment = GsonParser.parseString(jsonObject.get("comment"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCategoryVersion");
		kparams.add("name", this.name);
		kparams.add("baseVersionId", this.baseVersionId);
		kparams.add("comment", this.comment);
		return kparams;
	}

}

