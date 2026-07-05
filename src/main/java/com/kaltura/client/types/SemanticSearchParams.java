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
import com.kaltura.client.types.MediaSemanticSearchParams;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.types.ProgramSemanticSearchParams;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Parameters for unified semantic search across media and programs.             
  At least one of programParams or mediaParams must be provided.             
  Presence of a parameter object indicates inclusion in search results.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SemanticSearchParams.Tokenizer.class)
public class SemanticSearchParams extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String query();
		String refineQuery();
		String size();
		ProgramSemanticSearchParams.Tokenizer programParams();
		MediaSemanticSearchParams.Tokenizer mediaParams();
	}

	/**
	 * Search query text.
	 */
	private String query;
	/**
	 * Whether to refine the query using LLM.
	 */
	private Boolean refineQuery;
	/**
	 * Maximum number of results to return.
	 */
	private Integer size;
	/**
	 * Program-specific search parameters.              If provided, programs will be
	  included in search results.
	 */
	private ProgramSemanticSearchParams programParams;
	/**
	 * Media-specific search parameters.              If provided, media/VOD assets
	  will be included in search results.
	 */
	private MediaSemanticSearchParams mediaParams;

	// query:
	public String getQuery(){
		return this.query;
	}
	public void setQuery(String query){
		this.query = query;
	}

	public void query(String multirequestToken){
		setToken("query", multirequestToken);
	}

	// refineQuery:
	public Boolean getRefineQuery(){
		return this.refineQuery;
	}
	public void setRefineQuery(Boolean refineQuery){
		this.refineQuery = refineQuery;
	}

	public void refineQuery(String multirequestToken){
		setToken("refineQuery", multirequestToken);
	}

	// size:
	public Integer getSize(){
		return this.size;
	}
	public void setSize(Integer size){
		this.size = size;
	}

	public void size(String multirequestToken){
		setToken("size", multirequestToken);
	}

	// programParams:
	public ProgramSemanticSearchParams getProgramParams(){
		return this.programParams;
	}
	public void setProgramParams(ProgramSemanticSearchParams programParams){
		this.programParams = programParams;
	}

	// mediaParams:
	public MediaSemanticSearchParams getMediaParams(){
		return this.mediaParams;
	}
	public void setMediaParams(MediaSemanticSearchParams mediaParams){
		this.mediaParams = mediaParams;
	}


	public SemanticSearchParams() {
		super();
	}

	public SemanticSearchParams(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		query = GsonParser.parseString(jsonObject.get("query"));
		refineQuery = GsonParser.parseBoolean(jsonObject.get("refineQuery"));
		size = GsonParser.parseInt(jsonObject.get("size"));
		programParams = GsonParser.parseObject(jsonObject.getAsJsonObject("programParams"), ProgramSemanticSearchParams.class);
		mediaParams = GsonParser.parseObject(jsonObject.getAsJsonObject("mediaParams"), MediaSemanticSearchParams.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSemanticSearchParams");
		kparams.add("query", this.query);
		kparams.add("refineQuery", this.refineQuery);
		kparams.add("size", this.size);
		kparams.add("programParams", this.programParams);
		kparams.add("mediaParams", this.mediaParams);
		return kparams;
	}

}

