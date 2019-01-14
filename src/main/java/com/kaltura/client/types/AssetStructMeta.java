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

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset statistics
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetStructMeta.Tokenizer.class)
public class AssetStructMeta extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetStructId();
		String metaId();
		String ingestReferencePath();
		String protectFromIngest();
		String defaultIngestValue();
		String createDate();
		String updateDate();
		String isInherited();
	}

	/**
	 * Asset Struct id (template_id)
	 */
	private Long assetStructId;
	/**
	 * Meta id (topic_id)
	 */
	private Long metaId;
	/**
	 * IngestReferencePath
	 */
	private String ingestReferencePath;
	/**
	 * ProtectFromIngest
	 */
	private Boolean protectFromIngest;
	/**
	 * DefaultIngestValue
	 */
	private String defaultIngestValue;
	/**
	 * Specifies when was the Asset Struct Meta was created. Date and time represented
	  as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the Asset Struct Meta last updated. Date and time represented
	  as epoch.
	 */
	private Long updateDate;
	/**
	 * Is inherited
	 */
	private Boolean isInherited;

	// assetStructId:
	public Long getAssetStructId(){
		return this.assetStructId;
	}
	// metaId:
	public Long getMetaId(){
		return this.metaId;
	}
	// ingestReferencePath:
	public String getIngestReferencePath(){
		return this.ingestReferencePath;
	}
	public void setIngestReferencePath(String ingestReferencePath){
		this.ingestReferencePath = ingestReferencePath;
	}

	public void ingestReferencePath(String multirequestToken){
		setToken("ingestReferencePath", multirequestToken);
	}

	// protectFromIngest:
	public Boolean getProtectFromIngest(){
		return this.protectFromIngest;
	}
	public void setProtectFromIngest(Boolean protectFromIngest){
		this.protectFromIngest = protectFromIngest;
	}

	public void protectFromIngest(String multirequestToken){
		setToken("protectFromIngest", multirequestToken);
	}

	// defaultIngestValue:
	public String getDefaultIngestValue(){
		return this.defaultIngestValue;
	}
	public void setDefaultIngestValue(String defaultIngestValue){
		this.defaultIngestValue = defaultIngestValue;
	}

	public void defaultIngestValue(String multirequestToken){
		setToken("defaultIngestValue", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// isInherited:
	public Boolean getIsInherited(){
		return this.isInherited;
	}
	public void setIsInherited(Boolean isInherited){
		this.isInherited = isInherited;
	}

	public void isInherited(String multirequestToken){
		setToken("isInherited", multirequestToken);
	}


	public AssetStructMeta() {
		super();
	}

	public AssetStructMeta(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetStructId = GsonParser.parseLong(jsonObject.get("assetStructId"));
		metaId = GsonParser.parseLong(jsonObject.get("metaId"));
		ingestReferencePath = GsonParser.parseString(jsonObject.get("ingestReferencePath"));
		protectFromIngest = GsonParser.parseBoolean(jsonObject.get("protectFromIngest"));
		defaultIngestValue = GsonParser.parseString(jsonObject.get("defaultIngestValue"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		isInherited = GsonParser.parseBoolean(jsonObject.get("isInherited"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetStructMeta");
		kparams.add("ingestReferencePath", this.ingestReferencePath);
		kparams.add("protectFromIngest", this.protectFromIngest);
		kparams.add("defaultIngestValue", this.defaultIngestValue);
		kparams.add("isInherited", this.isInherited);
		return kparams;
	}

}

