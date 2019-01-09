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
 * Filtering Asset Struct Metas
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetStructMetaFilter.Tokenizer.class)
public class AssetStructMetaFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String assetStructIdEqual();
		String metaIdEqual();
	}

	/**
	 * Filter Asset Struct metas that contain a specific asset struct id
	 */
	private Long assetStructIdEqual;
	/**
	 * Filter Asset Struct metas that contain a specific meta id
	 */
	private Long metaIdEqual;

	// assetStructIdEqual:
	public Long getAssetStructIdEqual(){
		return this.assetStructIdEqual;
	}
	public void setAssetStructIdEqual(Long assetStructIdEqual){
		this.assetStructIdEqual = assetStructIdEqual;
	}

	public void assetStructIdEqual(String multirequestToken){
		setToken("assetStructIdEqual", multirequestToken);
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


	public AssetStructMetaFilter() {
		super();
	}

	public AssetStructMetaFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		assetStructIdEqual = GsonParser.parseLong(jsonObject.get("assetStructIdEqual"));
		metaIdEqual = GsonParser.parseLong(jsonObject.get("metaIdEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetStructMetaFilter");
		kparams.add("assetStructIdEqual", this.assetStructIdEqual);
		kparams.add("metaIdEqual", this.metaIdEqual);
		return kparams;
	}

}

