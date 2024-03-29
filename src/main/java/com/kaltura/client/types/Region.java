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
@MultiRequestBuilder.Tokenizer(Region.Tokenizer.class)
public class Region extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String externalId();
		String isDefault();
		RequestBuilder.ListTokenizer<RegionalChannel.Tokenizer> linearChannels();
		String parentId();
	}

	/**
	 * Region identifier
	 */
	private Integer id;
	/**
	 * Region name
	 */
	private String name;
	/**
	 * Region external identifier
	 */
	private String externalId;
	/**
	 * Indicates whether this is the default region for the partner
	 */
	private Boolean isDefault;
	/**
	 * List of associated linear channels
	 */
	private List<RegionalChannel> linearChannels;
	/**
	 * Parent region ID
	 */
	private Long parentId;

	// id:
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
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

	// isDefault:
	public Boolean getIsDefault(){
		return this.isDefault;
	}
	// linearChannels:
	public List<RegionalChannel> getLinearChannels(){
		return this.linearChannels;
	}
	public void setLinearChannels(List<RegionalChannel> linearChannels){
		this.linearChannels = linearChannels;
	}

	// parentId:
	public Long getParentId(){
		return this.parentId;
	}
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public void parentId(String multirequestToken){
		setToken("parentId", multirequestToken);
	}


	public Region() {
		super();
	}

	public Region(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		isDefault = GsonParser.parseBoolean(jsonObject.get("isDefault"));
		linearChannels = GsonParser.parseArray(jsonObject.getAsJsonArray("linearChannels"), RegionalChannel.class);
		parentId = GsonParser.parseLong(jsonObject.get("parentId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRegion");
		kparams.add("id", this.id);
		kparams.add("name", this.name);
		kparams.add("externalId", this.externalId);
		kparams.add("linearChannels", this.linearChannels);
		kparams.add("parentId", this.parentId);
		return kparams;
	}

}

