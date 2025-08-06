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
import com.kaltura.client.enums.VodIngestAssetResultStatus;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(VodIngestAssetResult.Tokenizer.class)
public class VodIngestAssetResult extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetName();
		String shopAssetUserRuleId();
		String fileName();
		String ingestDate();
		String status();
		String vodTypeSystemName();
		RequestBuilder.ListTokenizer<VodIngestAssetResultErrorMessage.Tokenizer> errors();
		RequestBuilder.ListTokenizer<VodIngestAssetResultErrorMessage.Tokenizer> warnings();
		String fileUploadDate();
		String processingStartDate();
		String processingCompletionDate();
	}

	/**
	 * Ingested asset name. Absent only in case of NameRequired error
	 */
	private String assetName;
	/**
	 * The shop ID the asset is assigned to. Omitted if the asset is not associated to
	  any shop.
	 */
	private Long shopAssetUserRuleId;
	/**
	 * The XML file name used at the ingest gateway. Referred to as process name
	 */
	private String fileName;
	/**
	 * Date and time the asset was ingested. Date and time represented as epoch.
	 */
	private Long ingestDate;
	/**
	 * The status result for the asset ingest.              FAILURE - the asset ingest
	  was failed after the ingest process started, specify the error for it.          
	     SUCCESS - the asset was succeeded to be ingested.             
	  SUCCESS_WARNING - the asset was succeeded to be ingested with warnings that do
	  not prevent the ingest.              EXTERNAL_FAILURE - the asset ingest was
	  failed before the ingest process started, specify the error for it.
	 */
	private VodIngestAssetResultStatus status;
	/**
	 * VOD asset type (assetStruct.systemName).
	 */
	private String vodTypeSystemName;
	/**
	 * Errors which prevent the asset from being ingested
	 */
	private List<VodIngestAssetResultErrorMessage> errors;
	/**
	 * Errors which do not prevent the asset from being ingested
	 */
	private List<VodIngestAssetResultErrorMessage> warnings;
	/**
	 * The date and time for which the ingest file was uploaded to the remote file
	  server. Expressed in milliseconds EPOCH time.
	 */
	private Long fileUploadDate;
	/**
	 * The date and time for which the ingest file moved to in progress folder and
	  started processing. Expressed in milliseconds EPOCH time.
	 */
	private Long processingStartDate;
	/**
	 * The date and time for which the ingest file completed the ingest process.
	  Expressed in milliseconds EPOCH time.
	 */
	private Long processingCompletionDate;

	// assetName:
	public String getAssetName(){
		return this.assetName;
	}
	public void setAssetName(String assetName){
		this.assetName = assetName;
	}

	public void assetName(String multirequestToken){
		setToken("assetName", multirequestToken);
	}

	// shopAssetUserRuleId:
	public Long getShopAssetUserRuleId(){
		return this.shopAssetUserRuleId;
	}
	public void setShopAssetUserRuleId(Long shopAssetUserRuleId){
		this.shopAssetUserRuleId = shopAssetUserRuleId;
	}

	public void shopAssetUserRuleId(String multirequestToken){
		setToken("shopAssetUserRuleId", multirequestToken);
	}

	// fileName:
	public String getFileName(){
		return this.fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public void fileName(String multirequestToken){
		setToken("fileName", multirequestToken);
	}

	// ingestDate:
	public Long getIngestDate(){
		return this.ingestDate;
	}
	public void setIngestDate(Long ingestDate){
		this.ingestDate = ingestDate;
	}

	public void ingestDate(String multirequestToken){
		setToken("ingestDate", multirequestToken);
	}

	// status:
	public VodIngestAssetResultStatus getStatus(){
		return this.status;
	}
	public void setStatus(VodIngestAssetResultStatus status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// vodTypeSystemName:
	public String getVodTypeSystemName(){
		return this.vodTypeSystemName;
	}
	public void setVodTypeSystemName(String vodTypeSystemName){
		this.vodTypeSystemName = vodTypeSystemName;
	}

	public void vodTypeSystemName(String multirequestToken){
		setToken("vodTypeSystemName", multirequestToken);
	}

	// errors:
	public List<VodIngestAssetResultErrorMessage> getErrors(){
		return this.errors;
	}
	public void setErrors(List<VodIngestAssetResultErrorMessage> errors){
		this.errors = errors;
	}

	// warnings:
	public List<VodIngestAssetResultErrorMessage> getWarnings(){
		return this.warnings;
	}
	public void setWarnings(List<VodIngestAssetResultErrorMessage> warnings){
		this.warnings = warnings;
	}

	// fileUploadDate:
	public Long getFileUploadDate(){
		return this.fileUploadDate;
	}
	public void setFileUploadDate(Long fileUploadDate){
		this.fileUploadDate = fileUploadDate;
	}

	public void fileUploadDate(String multirequestToken){
		setToken("fileUploadDate", multirequestToken);
	}

	// processingStartDate:
	public Long getProcessingStartDate(){
		return this.processingStartDate;
	}
	public void setProcessingStartDate(Long processingStartDate){
		this.processingStartDate = processingStartDate;
	}

	public void processingStartDate(String multirequestToken){
		setToken("processingStartDate", multirequestToken);
	}

	// processingCompletionDate:
	public Long getProcessingCompletionDate(){
		return this.processingCompletionDate;
	}
	public void setProcessingCompletionDate(Long processingCompletionDate){
		this.processingCompletionDate = processingCompletionDate;
	}

	public void processingCompletionDate(String multirequestToken){
		setToken("processingCompletionDate", multirequestToken);
	}


	public VodIngestAssetResult() {
		super();
	}

	public VodIngestAssetResult(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetName = GsonParser.parseString(jsonObject.get("assetName"));
		shopAssetUserRuleId = GsonParser.parseLong(jsonObject.get("shopAssetUserRuleId"));
		fileName = GsonParser.parseString(jsonObject.get("fileName"));
		ingestDate = GsonParser.parseLong(jsonObject.get("ingestDate"));
		status = VodIngestAssetResultStatus.get(GsonParser.parseString(jsonObject.get("status")));
		vodTypeSystemName = GsonParser.parseString(jsonObject.get("vodTypeSystemName"));
		errors = GsonParser.parseArray(jsonObject.getAsJsonArray("errors"), VodIngestAssetResultErrorMessage.class);
		warnings = GsonParser.parseArray(jsonObject.getAsJsonArray("warnings"), VodIngestAssetResultErrorMessage.class);
		fileUploadDate = GsonParser.parseLong(jsonObject.get("fileUploadDate"));
		processingStartDate = GsonParser.parseLong(jsonObject.get("processingStartDate"));
		processingCompletionDate = GsonParser.parseLong(jsonObject.get("processingCompletionDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaVodIngestAssetResult");
		kparams.add("assetName", this.assetName);
		kparams.add("shopAssetUserRuleId", this.shopAssetUserRuleId);
		kparams.add("fileName", this.fileName);
		kparams.add("ingestDate", this.ingestDate);
		kparams.add("status", this.status);
		kparams.add("vodTypeSystemName", this.vodTypeSystemName);
		kparams.add("errors", this.errors);
		kparams.add("warnings", this.warnings);
		kparams.add("fileUploadDate", this.fileUploadDate);
		kparams.add("processingStartDate", this.processingStartDate);
		kparams.add("processingCompletionDate", this.processingCompletionDate);
		return kparams;
	}

}

