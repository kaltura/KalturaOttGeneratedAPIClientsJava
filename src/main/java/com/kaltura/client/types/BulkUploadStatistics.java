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
 * Bulk Upload Statistics
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkUploadStatistics.Tokenizer.class)
public class BulkUploadStatistics extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String pending();
		String uploaded();
		String queued();
		String parsing();
		String processing();
		String processed();
		String success();
		String partial();
		String failed();
		String fatal();
	}

	/**
	 * count of bulk upload in pending status
	 */
	private Long pending;
	/**
	 * count of bulk Uploaded in uploaded status
	 */
	private Long uploaded;
	/**
	 * count of bulk upload in queued status
	 */
	private Long queued;
	/**
	 * count of bulk upload in parsing status
	 */
	private Long parsing;
	/**
	 * count of bulk upload in processing status
	 */
	private Long processing;
	/**
	 * count of bulk upload in processed status
	 */
	private Long processed;
	/**
	 * count of bulk upload in success status
	 */
	private Long success;
	/**
	 * count of bulk upload in partial status
	 */
	private Long partial;
	/**
	 * count of bulk upload in failed status
	 */
	private Long failed;
	/**
	 * count of bulk upload in fatal status
	 */
	private Long fatal;

	// pending:
	public Long getPending(){
		return this.pending;
	}
	public void setPending(Long pending){
		this.pending = pending;
	}

	public void pending(String multirequestToken){
		setToken("pending", multirequestToken);
	}

	// uploaded:
	public Long getUploaded(){
		return this.uploaded;
	}
	public void setUploaded(Long uploaded){
		this.uploaded = uploaded;
	}

	public void uploaded(String multirequestToken){
		setToken("uploaded", multirequestToken);
	}

	// queued:
	public Long getQueued(){
		return this.queued;
	}
	public void setQueued(Long queued){
		this.queued = queued;
	}

	public void queued(String multirequestToken){
		setToken("queued", multirequestToken);
	}

	// parsing:
	public Long getParsing(){
		return this.parsing;
	}
	public void setParsing(Long parsing){
		this.parsing = parsing;
	}

	public void parsing(String multirequestToken){
		setToken("parsing", multirequestToken);
	}

	// processing:
	public Long getProcessing(){
		return this.processing;
	}
	public void setProcessing(Long processing){
		this.processing = processing;
	}

	public void processing(String multirequestToken){
		setToken("processing", multirequestToken);
	}

	// processed:
	public Long getProcessed(){
		return this.processed;
	}
	public void setProcessed(Long processed){
		this.processed = processed;
	}

	public void processed(String multirequestToken){
		setToken("processed", multirequestToken);
	}

	// success:
	public Long getSuccess(){
		return this.success;
	}
	public void setSuccess(Long success){
		this.success = success;
	}

	public void success(String multirequestToken){
		setToken("success", multirequestToken);
	}

	// partial:
	public Long getPartial(){
		return this.partial;
	}
	public void setPartial(Long partial){
		this.partial = partial;
	}

	public void partial(String multirequestToken){
		setToken("partial", multirequestToken);
	}

	// failed:
	public Long getFailed(){
		return this.failed;
	}
	public void setFailed(Long failed){
		this.failed = failed;
	}

	public void failed(String multirequestToken){
		setToken("failed", multirequestToken);
	}

	// fatal:
	public Long getFatal(){
		return this.fatal;
	}
	public void setFatal(Long fatal){
		this.fatal = fatal;
	}

	public void fatal(String multirequestToken){
		setToken("fatal", multirequestToken);
	}


	public BulkUploadStatistics() {
		super();
	}

	public BulkUploadStatistics(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		pending = GsonParser.parseLong(jsonObject.get("pending"));
		uploaded = GsonParser.parseLong(jsonObject.get("uploaded"));
		queued = GsonParser.parseLong(jsonObject.get("queued"));
		parsing = GsonParser.parseLong(jsonObject.get("parsing"));
		processing = GsonParser.parseLong(jsonObject.get("processing"));
		processed = GsonParser.parseLong(jsonObject.get("processed"));
		success = GsonParser.parseLong(jsonObject.get("success"));
		partial = GsonParser.parseLong(jsonObject.get("partial"));
		failed = GsonParser.parseLong(jsonObject.get("failed"));
		fatal = GsonParser.parseLong(jsonObject.get("fatal"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkUploadStatistics");
		kparams.add("pending", this.pending);
		kparams.add("uploaded", this.uploaded);
		kparams.add("queued", this.queued);
		kparams.add("parsing", this.parsing);
		kparams.add("processing", this.processing);
		kparams.add("processed", this.processed);
		kparams.add("success", this.success);
		kparams.add("partial", this.partial);
		kparams.add("failed", this.failed);
		kparams.add("fatal", this.fatal);
		return kparams;
	}

}

