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

/**
 * Filtering Asset Structs
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetStructFilter.Tokenizer.class)
public class AssetStructFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idIn();
		String metaIdEqual();
		String isProtectedEqual();
	}

	/**
	 * Comma separated identifiers, id = 0 is identified as program AssetStruct
	 */
	private String idIn;
	/**
	 * Filter Asset Structs that contain a specific meta id
	 */
	private Long metaIdEqual;
	/**
	 * Filter Asset Structs by isProtectedEqual value
	 */
	private Boolean isProtectedEqual;

	// idIn:
	public String getIdIn(){
		return this.idIn;
	}
	public void setIdIn(String idIn){
		this.idIn = idIn;
	}

	public void idIn(String multirequestToken){
		setToken("idIn", multirequestToken);
	}

	// metaIdEqual:
	public Long getMetaIdEqual(){
		return this.metaIdEqual;
	}
	public void setMetaIdEqual(Long metaIdEqual){
		this.metaIdEqual = metaIdEqual;
	}

	public void metaIdEqual(String multirequestToken){
		setToken("metaIdEqual", multirequestToken);
	}

	// isProtectedEqual:
	public Boolean getIsProtectedEqual(){
		return this.isProtectedEqual;
	}
	public void setIsProtectedEqual(Boolean isProtectedEqual){
		this.isProtectedEqual = isProtectedEqual;
	}

	public void isProtectedEqual(String multirequestToken){
		setToken("isProtectedEqual", multirequestToken);
	}


	public AssetStructFilter() {
		super();
	}

	public AssetStructFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		metaIdEqual = GsonParser.parseLong(jsonObject.get("metaIdEqual"));
		isProtectedEqual = GsonParser.parseBoolean(jsonObject.get("isProtectedEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetStructFilter");
		kparams.add("idIn", this.idIn);
		kparams.add("metaIdEqual", this.metaIdEqual);
		kparams.add("isProtectedEqual", this.isProtectedEqual);
		return kparams;
	}

}

