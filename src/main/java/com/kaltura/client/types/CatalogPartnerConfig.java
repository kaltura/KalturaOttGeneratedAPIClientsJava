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
import com.kaltura.client.types.CategoryManagement;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Partner catalog configuration
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CatalogPartnerConfig.Tokenizer.class)
public class CatalogPartnerConfig extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		String singleMultilingualMode();
		CategoryManagement.Tokenizer categoryManagement();
		String epgMultilingualFallbackSupport();
		String uploadExportDatalake();
	}

	/**
	 * Single multilingual mode
	 */
	private Boolean singleMultilingualMode;
	/**
	 * Category management
	 */
	private CategoryManagement categoryManagement;
	/**
	 * EPG Multilingual Fallback Support
	 */
	private Boolean epgMultilingualFallbackSupport;
	/**
	 * Upload Export Datalake
	 */
	private Boolean uploadExportDatalake;

	// singleMultilingualMode:
	public Boolean getSingleMultilingualMode(){
		return this.singleMultilingualMode;
	}
	public void setSingleMultilingualMode(Boolean singleMultilingualMode){
		this.singleMultilingualMode = singleMultilingualMode;
	}

	public void singleMultilingualMode(String multirequestToken){
		setToken("singleMultilingualMode", multirequestToken);
	}

	// categoryManagement:
	public CategoryManagement getCategoryManagement(){
		return this.categoryManagement;
	}
	public void setCategoryManagement(CategoryManagement categoryManagement){
		this.categoryManagement = categoryManagement;
	}

	// epgMultilingualFallbackSupport:
	public Boolean getEpgMultilingualFallbackSupport(){
		return this.epgMultilingualFallbackSupport;
	}
	public void setEpgMultilingualFallbackSupport(Boolean epgMultilingualFallbackSupport){
		this.epgMultilingualFallbackSupport = epgMultilingualFallbackSupport;
	}

	public void epgMultilingualFallbackSupport(String multirequestToken){
		setToken("epgMultilingualFallbackSupport", multirequestToken);
	}

	// uploadExportDatalake:
	public Boolean getUploadExportDatalake(){
		return this.uploadExportDatalake;
	}
	public void setUploadExportDatalake(Boolean uploadExportDatalake){
		this.uploadExportDatalake = uploadExportDatalake;
	}

	public void uploadExportDatalake(String multirequestToken){
		setToken("uploadExportDatalake", multirequestToken);
	}


	public CatalogPartnerConfig() {
		super();
	}

	public CatalogPartnerConfig(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		singleMultilingualMode = GsonParser.parseBoolean(jsonObject.get("singleMultilingualMode"));
		categoryManagement = GsonParser.parseObject(jsonObject.getAsJsonObject("categoryManagement"), CategoryManagement.class);
		epgMultilingualFallbackSupport = GsonParser.parseBoolean(jsonObject.get("epgMultilingualFallbackSupport"));
		uploadExportDatalake = GsonParser.parseBoolean(jsonObject.get("uploadExportDatalake"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCatalogPartnerConfig");
		kparams.add("singleMultilingualMode", this.singleMultilingualMode);
		kparams.add("categoryManagement", this.categoryManagement);
		kparams.add("epgMultilingualFallbackSupport", this.epgMultilingualFallbackSupport);
		kparams.add("uploadExportDatalake", this.uploadExportDatalake);
		return kparams;
	}

}

