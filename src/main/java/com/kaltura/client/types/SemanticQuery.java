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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * The result of semantic query generation.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SemanticQuery.Tokenizer.class)
public class SemanticQuery extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		RequestBuilder.ListTokenizer<SemanticSubQuery.Tokenizer> subQueries();
		String title();
	}

	/**
	 * A list of generated sub-queries.
	 */
	private List<SemanticSubQuery> subQueries;
	/**
	 * A title generated for the entire queries&amp;#39; generation.
	 */
	private String title;

	// subQueries:
	public List<SemanticSubQuery> getSubQueries(){
		return this.subQueries;
	}
	public void setSubQueries(List<SemanticSubQuery> subQueries){
		this.subQueries = subQueries;
	}

	// title:
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public void title(String multirequestToken){
		setToken("title", multirequestToken);
	}


	public SemanticQuery() {
		super();
	}

	public SemanticQuery(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		subQueries = GsonParser.parseArray(jsonObject.getAsJsonArray("subQueries"), SemanticSubQuery.class);
		title = GsonParser.parseString(jsonObject.get("title"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSemanticQuery");
		kparams.add("subQueries", this.subQueries);
		kparams.add("title", this.title);
		return kparams;
	}

}

