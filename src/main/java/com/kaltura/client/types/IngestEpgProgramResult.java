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
// Copyright (C) 2006-2022  Kaltura Inc.
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
import com.kaltura.client.enums.IngestEpgProgramStatus;
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
@MultiRequestBuilder.Tokenizer(IngestEpgProgramResult.Tokenizer.class)
public class IngestEpgProgramResult extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String programId();
		String externalProgramId();
		String linearChannelId();
		String indexInFile();
		String startDate();
		String endDate();
		String status();
		RequestBuilder.ListTokenizer<EpgIngestErrorMessage.Tokenizer> errors();
		RequestBuilder.ListTokenizer<Message.Tokenizer> warnings();
	}

	/**
	 * The unique ingested program id
	 */
	private Long programId;
	/**
	 * An external program id
	 */
	private String externalProgramId;
	/**
	 * The id of the linear channel asset that the program belongs to
	 */
	private Long linearChannelId;
	/**
	 * The index of the program in the ingested file
	 */
	private Long indexInFile;
	/**
	 * Program EPG start date. Date and time represented as epoch
	 */
	private Long startDate;
	/**
	 * Program EPG end date. Date and time represented as epoch
	 */
	private Long endDate;
	/**
	 * The program status
	 */
	private IngestEpgProgramStatus status;
	/**
	 * List of errors. Note: error cause the data in question or the whole ingest to
	  fail
	 */
	private List<EpgIngestErrorMessage> errors;
	/**
	 * List of warnings. Note: warning cause no failure
	 */
	private List<Message> warnings;

	// programId:
	public Long getProgramId(){
		return this.programId;
	}
	public void setProgramId(Long programId){
		this.programId = programId;
	}

	public void programId(String multirequestToken){
		setToken("programId", multirequestToken);
	}

	// externalProgramId:
	public String getExternalProgramId(){
		return this.externalProgramId;
	}
	public void setExternalProgramId(String externalProgramId){
		this.externalProgramId = externalProgramId;
	}

	public void externalProgramId(String multirequestToken){
		setToken("externalProgramId", multirequestToken);
	}

	// linearChannelId:
	public Long getLinearChannelId(){
		return this.linearChannelId;
	}
	public void setLinearChannelId(Long linearChannelId){
		this.linearChannelId = linearChannelId;
	}

	public void linearChannelId(String multirequestToken){
		setToken("linearChannelId", multirequestToken);
	}

	// indexInFile:
	public Long getIndexInFile(){
		return this.indexInFile;
	}
	public void setIndexInFile(Long indexInFile){
		this.indexInFile = indexInFile;
	}

	public void indexInFile(String multirequestToken){
		setToken("indexInFile", multirequestToken);
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

	// status:
	public IngestEpgProgramStatus getStatus(){
		return this.status;
	}
	public void setStatus(IngestEpgProgramStatus status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}

	// errors:
	public List<EpgIngestErrorMessage> getErrors(){
		return this.errors;
	}
	public void setErrors(List<EpgIngestErrorMessage> errors){
		this.errors = errors;
	}

	// warnings:
	public List<Message> getWarnings(){
		return this.warnings;
	}
	public void setWarnings(List<Message> warnings){
		this.warnings = warnings;
	}


	public IngestEpgProgramResult() {
		super();
	}

	public IngestEpgProgramResult(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		programId = GsonParser.parseLong(jsonObject.get("programId"));
		externalProgramId = GsonParser.parseString(jsonObject.get("externalProgramId"));
		linearChannelId = GsonParser.parseLong(jsonObject.get("linearChannelId"));
		indexInFile = GsonParser.parseLong(jsonObject.get("indexInFile"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		status = IngestEpgProgramStatus.get(GsonParser.parseString(jsonObject.get("status")));
		errors = GsonParser.parseArray(jsonObject.getAsJsonArray("errors"), EpgIngestErrorMessage.class);
		warnings = GsonParser.parseArray(jsonObject.getAsJsonArray("warnings"), Message.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIngestEpgProgramResult");
		kparams.add("programId", this.programId);
		kparams.add("externalProgramId", this.externalProgramId);
		kparams.add("linearChannelId", this.linearChannelId);
		kparams.add("indexInFile", this.indexInFile);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("status", this.status);
		kparams.add("errors", this.errors);
		kparams.add("warnings", this.warnings);
		return kparams;
	}

}

