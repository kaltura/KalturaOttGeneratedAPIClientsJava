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
 * Asset file ppv
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetFilePpv.Tokenizer.class)
public class AssetFilePpv extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetFileId();
		String ppvModuleId();
		String startDate();
		String endDate();
	}

	/**
	 * Asset file identifier
	 */
	private Long assetFileId;
	/**
	 * Ppv module identifier
	 */
	private Long ppvModuleId;
	/**
	 * Start date and time represented as epoch.
	 */
	private Long startDate;
	/**
	 * End date and time represented as epoch.
	 */
	private Long endDate;

	// assetFileId:
	public Long getAssetFileId(){
		return this.assetFileId;
	}
	public void setAssetFileId(Long assetFileId){
		this.assetFileId = assetFileId;
	}

	public void assetFileId(String multirequestToken){
		setToken("assetFileId", multirequestToken);
	}

	// ppvModuleId:
	public Long getPpvModuleId(){
		return this.ppvModuleId;
	}
	public void setPpvModuleId(Long ppvModuleId){
		this.ppvModuleId = ppvModuleId;
	}

	public void ppvModuleId(String multirequestToken){
		setToken("ppvModuleId", multirequestToken);
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


	public AssetFilePpv() {
		super();
	}

	public AssetFilePpv(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetFileId = GsonParser.parseLong(jsonObject.get("assetFileId"));
		ppvModuleId = GsonParser.parseLong(jsonObject.get("ppvModuleId"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetFilePpv");
		kparams.add("assetFileId", this.assetFileId);
		kparams.add("ppvModuleId", this.ppvModuleId);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		return kparams;
	}

}

