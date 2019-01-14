// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2018  Kaltura Inc.
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
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TagFilter.Tokenizer.class)
public class TagFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String tagEqual();
		String tagStartsWith();
		String typeEqual();
		String languageEqual();
	}

	/**
	 * Tag to filter by
	 */
	private String tagEqual;
	/**
	 * Tag to filter by
	 */
	private String tagStartsWith;
	/**
	 * Type identifier
	 */
	private Integer typeEqual;
	/**
	 * Language to filter by
	 */
	private String languageEqual;

	// tagEqual:
	public String getTagEqual(){
		return this.tagEqual;
	}
	public void setTagEqual(String tagEqual){
		this.tagEqual = tagEqual;
	}

	public void tagEqual(String multirequestToken){
		setToken("tagEqual", multirequestToken);
	}

	// tagStartsWith:
	public String getTagStartsWith(){
		return this.tagStartsWith;
	}
	public void setTagStartsWith(String tagStartsWith){
		this.tagStartsWith = tagStartsWith;
	}

	public void tagStartsWith(String multirequestToken){
		setToken("tagStartsWith", multirequestToken);
	}

	// typeEqual:
	public Integer getTypeEqual(){
		return this.typeEqual;
	}
	public void setTypeEqual(Integer typeEqual){
		this.typeEqual = typeEqual;
	}

	public void typeEqual(String multirequestToken){
		setToken("typeEqual", multirequestToken);
	}

	// languageEqual:
	public String getLanguageEqual(){
		return this.languageEqual;
	}
	public void setLanguageEqual(String languageEqual){
		this.languageEqual = languageEqual;
	}

	public void languageEqual(String multirequestToken){
		setToken("languageEqual", multirequestToken);
	}


	public TagFilter() {
		super();
	}

	public TagFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		tagEqual = GsonParser.parseString(jsonObject.get("tagEqual"));
		tagStartsWith = GsonParser.parseString(jsonObject.get("tagStartsWith"));
		typeEqual = GsonParser.parseInt(jsonObject.get("typeEqual"));
		languageEqual = GsonParser.parseString(jsonObject.get("languageEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTagFilter");
		kparams.add("tagEqual", this.tagEqual);
		kparams.add("tagStartsWith", this.tagStartsWith);
		kparams.add("typeEqual", this.typeEqual);
		kparams.add("languageEqual", this.languageEqual);
		return kparams;
	}

}

