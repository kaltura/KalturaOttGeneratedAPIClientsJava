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
@MultiRequestBuilder.Tokenizer(RelatedFilter.Tokenizer.class)
public class RelatedFilter extends BaseSearchAssetFilter {
	
	public interface Tokenizer extends BaseSearchAssetFilter.Tokenizer {
		String idEqual();
		String typeIn();
		String excludeWatched();
	}

	/**
	 * the ID of the asset for which to return related assets
	 */
	private Integer idEqual;
	/**
	 * Comma separated list of asset types to search within.               Possible
	  values: any media type ID (according to media type IDs defined dynamically in
	  the system).              If omitted – same type as the provided asset.
	 */
	private String typeIn;
	/**
	 * Exclude watched asset.
	 */
	private Boolean excludeWatched;

	// idEqual:
	public Integer getIdEqual(){
		return this.idEqual;
	}
	public void setIdEqual(Integer idEqual){
		this.idEqual = idEqual;
	}

	public void idEqual(String multirequestToken){
		setToken("idEqual", multirequestToken);
	}

	// typeIn:
	public String getTypeIn(){
		return this.typeIn;
	}
	public void setTypeIn(String typeIn){
		this.typeIn = typeIn;
	}

	public void typeIn(String multirequestToken){
		setToken("typeIn", multirequestToken);
	}

	// excludeWatched:
	public Boolean getExcludeWatched(){
		return this.excludeWatched;
	}
	public void setExcludeWatched(Boolean excludeWatched){
		this.excludeWatched = excludeWatched;
	}

	public void excludeWatched(String multirequestToken){
		setToken("excludeWatched", multirequestToken);
	}


	public RelatedFilter() {
		super();
	}

	public RelatedFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseInt(jsonObject.get("idEqual"));
		typeIn = GsonParser.parseString(jsonObject.get("typeIn"));
		excludeWatched = GsonParser.parseBoolean(jsonObject.get("excludeWatched"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRelatedFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("typeIn", this.typeIn);
		kparams.add("excludeWatched", this.excludeWatched);
		return kparams;
	}

}

