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

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetStruct.Tokenizer.class)
public class AssetStruct extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String systemName();
		String isProtected();
		String metaIds();
		String createDate();
		String updateDate();
		String features();
		String pluralName();
		String parentId();
		String connectingMetaId();
		String connectedParentMetaId();
	}

	/**
	 * Asset Struct id
	 */
	private Long id;
	/**
	 * Asset struct name for the partner
	 */
	private String name;
	/**
	 * Asset struct name for the partner
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * Asset Struct system name for the partner
	 */
	private String systemName;
	/**
	 * Is the Asset Struct protected by the system
	 */
	private Boolean isProtected;
	/**
	 * A list of comma separated meta ids associated with this asset struct, returned
	  according to the order.
	 */
	private String metaIds;
	/**
	 * Specifies when was the Asset Struct was created. Date and time represented as
	  epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the Asset Struct last updated. Date and time represented as
	  epoch.
	 */
	private Long updateDate;
	/**
	 * List of supported features
	 */
	private String features;
	/**
	 * Plural Name
	 */
	private String pluralName;
	/**
	 * AssetStruct parent Id
	 */
	private Long parentId;
	/**
	 * connectingMetaId
	 */
	private Long connectingMetaId;
	/**
	 * connectedParentMetaId
	 */
	private Long connectedParentMetaId;

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

	// systemName:
	public String getSystemName(){
		return this.systemName;
	}
	public void setSystemName(String systemName){
		this.systemName = systemName;
	}

	public void systemName(String multirequestToken){
		setToken("systemName", multirequestToken);
	}

	// isProtected:
	public Boolean getIsProtected(){
		return this.isProtected;
	}
	public void setIsProtected(Boolean isProtected){
		this.isProtected = isProtected;
	}

	public void isProtected(String multirequestToken){
		setToken("isProtected", multirequestToken);
	}

	// metaIds:
	public String getMetaIds(){
		return this.metaIds;
	}
	public void setMetaIds(String metaIds){
		this.metaIds = metaIds;
	}

	public void metaIds(String multirequestToken){
		setToken("metaIds", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// features:
	public String getFeatures(){
		return this.features;
	}
	public void setFeatures(String features){
		this.features = features;
	}

	public void features(String multirequestToken){
		setToken("features", multirequestToken);
	}

	// pluralName:
	public String getPluralName(){
		return this.pluralName;
	}
	public void setPluralName(String pluralName){
		this.pluralName = pluralName;
	}

	public void pluralName(String multirequestToken){
		setToken("pluralName", multirequestToken);
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

	// connectingMetaId:
	public Long getConnectingMetaId(){
		return this.connectingMetaId;
	}
	public void setConnectingMetaId(Long connectingMetaId){
		this.connectingMetaId = connectingMetaId;
	}

	public void connectingMetaId(String multirequestToken){
		setToken("connectingMetaId", multirequestToken);
	}

	// connectedParentMetaId:
	public Long getConnectedParentMetaId(){
		return this.connectedParentMetaId;
	}
	public void setConnectedParentMetaId(Long connectedParentMetaId){
		this.connectedParentMetaId = connectedParentMetaId;
	}

	public void connectedParentMetaId(String multirequestToken){
		setToken("connectedParentMetaId", multirequestToken);
	}


	public AssetStruct() {
		super();
	}

	public AssetStruct(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		systemName = GsonParser.parseString(jsonObject.get("systemName"));
		isProtected = GsonParser.parseBoolean(jsonObject.get("isProtected"));
		metaIds = GsonParser.parseString(jsonObject.get("metaIds"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		features = GsonParser.parseString(jsonObject.get("features"));
		pluralName = GsonParser.parseString(jsonObject.get("pluralName"));
		parentId = GsonParser.parseLong(jsonObject.get("parentId"));
		connectingMetaId = GsonParser.parseLong(jsonObject.get("connectingMetaId"));
		connectedParentMetaId = GsonParser.parseLong(jsonObject.get("connectedParentMetaId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetStruct");
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("systemName", this.systemName);
		kparams.add("isProtected", this.isProtected);
		kparams.add("metaIds", this.metaIds);
		kparams.add("features", this.features);
		kparams.add("pluralName", this.pluralName);
		kparams.add("parentId", this.parentId);
		kparams.add("connectingMetaId", this.connectingMetaId);
		kparams.add("connectedParentMetaId", this.connectedParentMetaId);
		return kparams;
	}

}

