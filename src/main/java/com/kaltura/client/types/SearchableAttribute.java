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
 * Represents a single searchable attribute for a given asset structure.           
    This class extends KalturaOTTObject and contains details such as the asset
  structure ID and its associated attributes.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SearchableAttribute.Tokenizer.class)
public class SearchableAttribute extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String assetStructId();
		String attributes();
	}

	/**
	 * The unique identifier for the asset structure associated with the searchable
	  attribute.
	 */
	private String assetStructId;
	/**
	 * The specific attributes that define the searchable aspect of the asset.
	 */
	private String attributes;

	// assetStructId:
	public String getAssetStructId(){
		return this.assetStructId;
	}
	public void setAssetStructId(String assetStructId){
		this.assetStructId = assetStructId;
	}

	public void assetStructId(String multirequestToken){
		setToken("assetStructId", multirequestToken);
	}

	// attributes:
	public String getAttributes(){
		return this.attributes;
	}
	public void setAttributes(String attributes){
		this.attributes = attributes;
	}

	public void attributes(String multirequestToken){
		setToken("attributes", multirequestToken);
	}


	public SearchableAttribute() {
		super();
	}

	public SearchableAttribute(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetStructId = GsonParser.parseString(jsonObject.get("assetStructId"));
		attributes = GsonParser.parseString(jsonObject.get("attributes"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSearchableAttribute");
		kparams.add("assetStructId", this.assetStructId);
		kparams.add("attributes", this.attributes);
		return kparams;
	}

}

