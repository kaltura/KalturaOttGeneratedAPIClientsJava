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
 * Request object for generating semantic queries.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GenerateSemanticQuery.Tokenizer.class)
public class GenerateSemanticQuery extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String query();
	}

	/**
	 * The input query text to generate semantic queries from.
	 */
	private String query;

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


	public GenerateSemanticQuery() {
		super();
	}

	public GenerateSemanticQuery(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		query = GsonParser.parseString(jsonObject.get("query"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGenerateSemanticQuery");
		kparams.add("query", this.query);
		return kparams;
	}

}

