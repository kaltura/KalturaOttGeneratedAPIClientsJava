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
import com.kaltura.client.enums.GenerateMetadataStatus;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * An object containing information on the metadata generation job.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GenerateMetadataJob.Tokenizer.class)
public class GenerateMetadataJob extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String createDate();
		String updateDate();
		String sourceName();
		String status();
		String errorMessage();
	}

	/**
	 * Unique identifier for the generation job
	 */
	private Long id;
	/**
	 * Specifies when the job was created, expressed in Epoch timestamp.
	 */
	private Long createDate;
	/**
	 * Specifies when the job was updated, expressed in Epoch timestamp.
	 */
	private Long updateDate;
	/**
	 * Name of the source job element generating the metadata.              For
	  generateMetadataBySubtitles: the uploaded subtitle file name.              For
	  generateMetadataByDescription: the asset name from which metadata is generated.
	 */
	private String sourceName;
	/**
	 * can be either Processing/Success/Failed, per the last status updated by the
	  aiMetadataGenerator.
	 */
	private GenerateMetadataStatus status;
	/**
	 * Error messages for non-success cases.
	 */
	private String errorMessage;

	// id:
	public Long getId(){
		return this.id;
	}
	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// sourceName:
	public String getSourceName(){
		return this.sourceName;
	}
	// status:
	public GenerateMetadataStatus getStatus(){
		return this.status;
	}
	// errorMessage:
	public String getErrorMessage(){
		return this.errorMessage;
	}

	public GenerateMetadataJob() {
		super();
	}

	public GenerateMetadataJob(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		sourceName = GsonParser.parseString(jsonObject.get("sourceName"));
		status = GenerateMetadataStatus.get(GsonParser.parseString(jsonObject.get("status")));
		errorMessage = GsonParser.parseString(jsonObject.get("errorMessage"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGenerateMetadataJob");
		return kparams;
	}

}

