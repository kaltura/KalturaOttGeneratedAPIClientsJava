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
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset Comment
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetComment.Tokenizer.class)
public class AssetComment extends SocialComment {
	
	public interface Tokenizer extends SocialComment.Tokenizer {
		String id();
		String assetId();
		String assetType();
		String subHeader();
	}

	/**
	 * Comment ID
	 */
	private Integer id;
	/**
	 * Asset identifier
	 */
	private Integer assetId;
	/**
	 * Asset Type
	 */
	private AssetType assetType;
	/**
	 * Sub Header
	 */
	private String subHeader;

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

	// assetId:
	public Integer getAssetId(){
		return this.assetId;
	}
	public void setAssetId(Integer assetId){
		this.assetId = assetId;
	}

	public void assetId(String multirequestToken){
		setToken("assetId", multirequestToken);
	}

	// assetType:
	public AssetType getAssetType(){
		return this.assetType;
	}
	public void setAssetType(AssetType assetType){
		this.assetType = assetType;
	}

	public void assetType(String multirequestToken){
		setToken("assetType", multirequestToken);
	}

	// subHeader:
	public String getSubHeader(){
		return this.subHeader;
	}
	public void setSubHeader(String subHeader){
		this.subHeader = subHeader;
	}

	public void subHeader(String multirequestToken){
		setToken("subHeader", multirequestToken);
	}


	public AssetComment() {
		super();
	}

	public AssetComment(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		assetId = GsonParser.parseInt(jsonObject.get("assetId"));
		assetType = AssetType.get(GsonParser.parseString(jsonObject.get("assetType")));
		subHeader = GsonParser.parseString(jsonObject.get("subHeader"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetComment");
		kparams.add("id", this.id);
		kparams.add("assetId", this.assetId);
		kparams.add("assetType", this.assetType);
		kparams.add("subHeader", this.subHeader);
		return kparams;
	}

}

