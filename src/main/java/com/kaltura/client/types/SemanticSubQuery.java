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
import com.kaltura.client.types.TranslationToken;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Represents a semantic sub-query with text and name.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SemanticSubQuery.Tokenizer.class)
public class SemanticSubQuery extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String text();
		TranslationToken.Tokenizer name();
	}

	/**
	 * The text content of the sub-query.
	 */
	private String text;
	/**
	 * The name of the sub-query.
	 */
	private TranslationToken name;

	// text:
	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
	}

	public void text(String multirequestToken){
		setToken("text", multirequestToken);
	}

	// name:
	public TranslationToken getName(){
		return this.name;
	}
	public void setName(TranslationToken name){
		this.name = name;
	}


	public SemanticSubQuery() {
		super();
	}

	public SemanticSubQuery(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		text = GsonParser.parseString(jsonObject.get("text"));
		name = GsonParser.parseObject(jsonObject.getAsJsonObject("name"), TranslationToken.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSemanticSubQuery");
		kparams.add("text", this.text);
		kparams.add("name", this.name);
		return kparams;
	}

}

