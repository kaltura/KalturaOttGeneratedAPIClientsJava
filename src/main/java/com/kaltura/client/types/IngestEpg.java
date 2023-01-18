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
import com.kaltura.client.enums.IngestStatus;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(IngestEpg.Tokenizer.class)
public class IngestEpg extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String ingestId();
		String ingestName();
		String ingestFilenameExtension();
		String createdDate();
		String ingestedByUserId();
		String completedDate();
		String ingestProfileId();
		String status();
	}

	/**
	 * Unique id of the ingest job in question
	 */
	private Long ingestId;
	/**
	 * The ingested file name without its extention
	 */
	private String ingestName;
	/**
	 * The ingested file name extention
	 */
	private String ingestFilenameExtension;
	/**
	 * The ingest job created date and time. Date and time represented as epoch.
	 */
	private Long createdDate;
	/**
	 * The user id of the addFromBulkUpload caller.
	 */
	private Long ingestedByUserId;
	/**
	 * The ingest job completed date and time. Date and time represented as epoch.
	 */
	private Long completedDate;
	/**
	 * The ingest profile id that of the ingest job.
	 */
	private Long ingestProfileId;
	/**
	 * The ingest profile id that of the ingest job.
	 */
	private IngestStatus status;

	// ingestId:
	public Long getIngestId(){
		return this.ingestId;
	}
	// ingestName:
	public String getIngestName(){
		return this.ingestName;
	}
	public void setIngestName(String ingestName){
		this.ingestName = ingestName;
	}

	public void ingestName(String multirequestToken){
		setToken("ingestName", multirequestToken);
	}

	// ingestFilenameExtension:
	public String getIngestFilenameExtension(){
		return this.ingestFilenameExtension;
	}
	public void setIngestFilenameExtension(String ingestFilenameExtension){
		this.ingestFilenameExtension = ingestFilenameExtension;
	}

	public void ingestFilenameExtension(String multirequestToken){
		setToken("ingestFilenameExtension", multirequestToken);
	}

	// createdDate:
	public Long getCreatedDate(){
		return this.createdDate;
	}
	public void setCreatedDate(Long createdDate){
		this.createdDate = createdDate;
	}

	public void createdDate(String multirequestToken){
		setToken("createdDate", multirequestToken);
	}

	// ingestedByUserId:
	public Long getIngestedByUserId(){
		return this.ingestedByUserId;
	}
	public void setIngestedByUserId(Long ingestedByUserId){
		this.ingestedByUserId = ingestedByUserId;
	}

	public void ingestedByUserId(String multirequestToken){
		setToken("ingestedByUserId", multirequestToken);
	}

	// completedDate:
	public Long getCompletedDate(){
		return this.completedDate;
	}
	public void setCompletedDate(Long completedDate){
		this.completedDate = completedDate;
	}

	public void completedDate(String multirequestToken){
		setToken("completedDate", multirequestToken);
	}

	// ingestProfileId:
	public Long getIngestProfileId(){
		return this.ingestProfileId;
	}
	public void setIngestProfileId(Long ingestProfileId){
		this.ingestProfileId = ingestProfileId;
	}

	public void ingestProfileId(String multirequestToken){
		setToken("ingestProfileId", multirequestToken);
	}

	// status:
	public IngestStatus getStatus(){
		return this.status;
	}
	public void setStatus(IngestStatus status){
		this.status = status;
	}

	public void status(String multirequestToken){
		setToken("status", multirequestToken);
	}


	public IngestEpg() {
		super();
	}

	public IngestEpg(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		ingestId = GsonParser.parseLong(jsonObject.get("ingestId"));
		ingestName = GsonParser.parseString(jsonObject.get("ingestName"));
		ingestFilenameExtension = GsonParser.parseString(jsonObject.get("ingestFilenameExtension"));
		createdDate = GsonParser.parseLong(jsonObject.get("createdDate"));
		ingestedByUserId = GsonParser.parseLong(jsonObject.get("ingestedByUserId"));
		completedDate = GsonParser.parseLong(jsonObject.get("completedDate"));
		ingestProfileId = GsonParser.parseLong(jsonObject.get("ingestProfileId"));
		status = IngestStatus.get(GsonParser.parseString(jsonObject.get("status")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIngestEpg");
		kparams.add("ingestName", this.ingestName);
		kparams.add("ingestFilenameExtension", this.ingestFilenameExtension);
		kparams.add("createdDate", this.createdDate);
		kparams.add("ingestedByUserId", this.ingestedByUserId);
		kparams.add("completedDate", this.completedDate);
		kparams.add("ingestProfileId", this.ingestProfileId);
		kparams.add("status", this.status);
		return kparams;
	}

}

