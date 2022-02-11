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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(IngestByCompoundFilter.Tokenizer.class)
public class IngestByCompoundFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String ingestNameContains();
		String ingestedByUserIdIn();
		String ingestStatusIn();
		String createdDateGreaterThan();
		String createdDateSmallerThan();
	}

	/**
	 * A string that is included in the ingest file name
	 */
	private String ingestNameContains;
	/**
	 * Comma seperated user ids
	 */
	private String ingestedByUserIdIn;
	/**
	 * Comma seperated valid stutuses
	 */
	private String ingestStatusIn;
	/**
	 * Ingest created date greater then this value. . Date and time represented as
	  epoch.
	 */
	private Long createdDateGreaterThan;
	/**
	 * Ingest created date smaller than this value. Date and time represented as epoch.
	 */
	private Long createdDateSmallerThan;

	// ingestNameContains:
	public String getIngestNameContains(){
		return this.ingestNameContains;
	}
	public void setIngestNameContains(String ingestNameContains){
		this.ingestNameContains = ingestNameContains;
	}

	public void ingestNameContains(String multirequestToken){
		setToken("ingestNameContains", multirequestToken);
	}

	// ingestedByUserIdIn:
	public String getIngestedByUserIdIn(){
		return this.ingestedByUserIdIn;
	}
	public void setIngestedByUserIdIn(String ingestedByUserIdIn){
		this.ingestedByUserIdIn = ingestedByUserIdIn;
	}

	public void ingestedByUserIdIn(String multirequestToken){
		setToken("ingestedByUserIdIn", multirequestToken);
	}

	// ingestStatusIn:
	public String getIngestStatusIn(){
		return this.ingestStatusIn;
	}
	public void setIngestStatusIn(String ingestStatusIn){
		this.ingestStatusIn = ingestStatusIn;
	}

	public void ingestStatusIn(String multirequestToken){
		setToken("ingestStatusIn", multirequestToken);
	}

	// createdDateGreaterThan:
	public Long getCreatedDateGreaterThan(){
		return this.createdDateGreaterThan;
	}
	public void setCreatedDateGreaterThan(Long createdDateGreaterThan){
		this.createdDateGreaterThan = createdDateGreaterThan;
	}

	public void createdDateGreaterThan(String multirequestToken){
		setToken("createdDateGreaterThan", multirequestToken);
	}

	// createdDateSmallerThan:
	public Long getCreatedDateSmallerThan(){
		return this.createdDateSmallerThan;
	}
	public void setCreatedDateSmallerThan(Long createdDateSmallerThan){
		this.createdDateSmallerThan = createdDateSmallerThan;
	}

	public void createdDateSmallerThan(String multirequestToken){
		setToken("createdDateSmallerThan", multirequestToken);
	}


	public IngestByCompoundFilter() {
		super();
	}

	public IngestByCompoundFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		ingestNameContains = GsonParser.parseString(jsonObject.get("ingestNameContains"));
		ingestedByUserIdIn = GsonParser.parseString(jsonObject.get("ingestedByUserIdIn"));
		ingestStatusIn = GsonParser.parseString(jsonObject.get("ingestStatusIn"));
		createdDateGreaterThan = GsonParser.parseLong(jsonObject.get("createdDateGreaterThan"));
		createdDateSmallerThan = GsonParser.parseLong(jsonObject.get("createdDateSmallerThan"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIngestByCompoundFilter");
		kparams.add("ingestNameContains", this.ingestNameContains);
		kparams.add("ingestedByUserIdIn", this.ingestedByUserIdIn);
		kparams.add("ingestStatusIn", this.ingestStatusIn);
		kparams.add("createdDateGreaterThan", this.createdDateGreaterThan);
		kparams.add("createdDateSmallerThan", this.createdDateSmallerThan);
		return kparams;
	}

}

