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
import com.kaltura.client.enums.AssetInheritancePolicy;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Media-asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MediaAsset.Tokenizer.class)
public class MediaAsset extends Asset {
	
	public interface Tokenizer extends Asset.Tokenizer {
		String externalIds();
		String entryId();
		String deviceRuleId();
		String geoBlockRuleId();
		String status();
		String inheritancePolicy();
	}

	/**
	 * External identifiers
	 */
	private String externalIds;
	/**
	 * Entry Identifier
	 */
	private String entryId;
	/**
	 * Device rule identifier
	 */
	private Integer deviceRuleId;
	/**
	 * Geo block rule identifier
	 */
	private Integer geoBlockRuleId;
	/**
	 * The media asset status
	 */
	private Boolean status;
	/**
	 * The media asset inheritance policy
	 */
	private AssetInheritancePolicy inheritancePolicy;

	// externalIds:
	public String getExternalIds(){
		return this.externalIds;
	}
	public void setExternalIds(String externalIds){
		this.externalIds = externalIds;
	}

	public void externalIds(String multirequestToken){
		setToken("externalIds", multirequestToken);
	}

	// entryId:
	public String getEntryId(){
		return this.entryId;
	}
	public void setEntryId(String entryId){
		this.entryId = entryId;
	}

	public void entryId(String multirequestToken){
		setToken("entryId", multirequestToken);
	}

	// deviceRuleId:
	public Integer getDeviceRuleId(){
		return this.deviceRuleId;
	}
	public void setDeviceRuleId(Integer deviceRuleId){
		this.deviceRuleId = deviceRuleId;
	}

	public void deviceRuleId(String multirequestToken){
		setToken("deviceRuleId", multirequestToken);
	}

	// geoBlockRuleId:
	public Integer getGeoBlockRuleId(){
		return this.geoBlockRuleId;
	}
	public void setGeoBlockRuleId(Integer geoBlockRuleId){
		this.geoBlockRuleId = geoBlockRuleId;
	}

	public void geoBlockRuleId(String multirequestToken){
		setToken("geoBlockRuleId", multirequestToken);
	}

	// status:
	public Boolean getStatus(){
		return this.status;
	}
	public void setStatus(Boolean status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// inheritancePolicy:
	public AssetInheritancePolicy getInheritancePolicy(){
		return this.inheritancePolicy;
	}
	public void setInheritancePolicy(AssetInheritancePolicy inheritancePolicy){
		this.inheritancePolicy = inheritancePolicy;
	}

	public void inheritancePolicy(String multirequestToken){
		setToken("inheritancePolicy", multirequestToken);
	}


	public MediaAsset() {
		super();
	}

	public MediaAsset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		externalIds = GsonParser.parseString(jsonObject.get("externalIds"));
		entryId = GsonParser.parseString(jsonObject.get("entryId"));
		deviceRuleId = GsonParser.parseInt(jsonObject.get("deviceRuleId"));
		geoBlockRuleId = GsonParser.parseInt(jsonObject.get("geoBlockRuleId"));
		status = GsonParser.parseBoolean(jsonObject.get("status"));
		inheritancePolicy = AssetInheritancePolicy.get(GsonParser.parseString(jsonObject.get("inheritancePolicy")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMediaAsset");
		kparams.add("externalIds", this.externalIds);
		kparams.add("entryId", this.entryId);
		kparams.add("deviceRuleId", this.deviceRuleId);
		kparams.add("geoBlockRuleId", this.geoBlockRuleId);
		kparams.add("status", this.status);
		kparams.add("inheritancePolicy", this.inheritancePolicy);
		return kparams;
	}

}

