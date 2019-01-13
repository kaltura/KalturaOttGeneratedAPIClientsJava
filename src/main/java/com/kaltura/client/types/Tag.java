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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Tag.Tokenizer.class)
public class Tag extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String type();
		String tag();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualTag();
	}

	/**
	 * Tag id
	 */
	private Long id;
	/**
	 * Tag Type
	 */
	private Integer type;
	/**
	 * Tag
	 */
	private String tag;
	/**
	 * Tag
	 */
	private List<TranslationToken> multilingualTag;

	// id:
	public Long getId(){
		return this.id;
	}
	// type:
	public Integer getType(){
		return this.type;
	}
	public void setType(Integer type){
		this.type = type;
	}

	public void type(String multirequestToken){
		setToken("type", multirequestToken);
	}

	// tag:
	public String getTag(){
		return this.tag;
	}
	// multilingualTag:
	public List<TranslationToken> getMultilingualTag(){
		return this.multilingualTag;
	}
	public void setMultilingualTag(List<TranslationToken> multilingualTag){
		this.multilingualTag = multilingualTag;
	}


	public Tag() {
		super();
	}

	public Tag(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		type = GsonParser.parseInt(jsonObject.get("type"));
		tag = GsonParser.parseString(jsonObject.get("tag"));
		multilingualTag = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualTag"), TranslationToken.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTag");
		kparams.add("type", this.type);
		kparams.add("multilingualTag", this.multilingualTag);
		return kparams;
	}

}

