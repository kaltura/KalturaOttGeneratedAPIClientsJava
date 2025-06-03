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
 * Partner configuration for semantic query generation.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SemanticQueryPartnerConfiguration.Tokenizer.class)
public class SemanticQueryPartnerConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String subQueriesCount();
		String defaultQueriesCount();
		String assetsPerCollectionCount();
	}

	/**
	 * The number of sub-queries to generate, including the main (base) one. Optional,
	  Requires Admin role.
	 */
	private Integer subQueriesCount;
	/**
	 * The number of default sub-queries to generate if the primary requested query is
	  empty. Optional, Requires Admin role.
	 */
	private Integer defaultQueriesCount;
	/**
	 * The number of assets per suggested collection.
	 */
	private Integer assetsPerCollectionCount;

	// subQueriesCount:
	public Integer getSubQueriesCount(){
		return this.subQueriesCount;
	}
	public void setSubQueriesCount(Integer subQueriesCount){
		this.subQueriesCount = subQueriesCount;
	}

	public void subQueriesCount(String multirequestToken){
		setToken("subQueriesCount", multirequestToken);
	}

	// defaultQueriesCount:
	public Integer getDefaultQueriesCount(){
		return this.defaultQueriesCount;
	}
	public void setDefaultQueriesCount(Integer defaultQueriesCount){
		this.defaultQueriesCount = defaultQueriesCount;
	}

	public void defaultQueriesCount(String multirequestToken){
		setToken("defaultQueriesCount", multirequestToken);
	}

	// assetsPerCollectionCount:
	public Integer getAssetsPerCollectionCount(){
		return this.assetsPerCollectionCount;
	}
	public void setAssetsPerCollectionCount(Integer assetsPerCollectionCount){
		this.assetsPerCollectionCount = assetsPerCollectionCount;
	}

	public void assetsPerCollectionCount(String multirequestToken){
		setToken("assetsPerCollectionCount", multirequestToken);
	}


	public SemanticQueryPartnerConfiguration() {
		super();
	}

	public SemanticQueryPartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		subQueriesCount = GsonParser.parseInt(jsonObject.get("subQueriesCount"));
		defaultQueriesCount = GsonParser.parseInt(jsonObject.get("defaultQueriesCount"));
		assetsPerCollectionCount = GsonParser.parseInt(jsonObject.get("assetsPerCollectionCount"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSemanticQueryPartnerConfiguration");
		kparams.add("subQueriesCount", this.subQueriesCount);
		kparams.add("defaultQueriesCount", this.defaultQueriesCount);
		kparams.add("assetsPerCollectionCount", this.assetsPerCollectionCount);
		return kparams;
	}

}

