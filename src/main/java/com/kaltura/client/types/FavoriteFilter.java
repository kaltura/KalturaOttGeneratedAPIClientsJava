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
 * Favorite request filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(FavoriteFilter.Tokenizer.class)
public class FavoriteFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String mediaTypeEqual();
		String mediaIdIn();
		String udidEqualCurrent();
	}

	/**
	 * Media type to filter by the favorite assets
	 */
	private Integer mediaTypeEqual;
	/**
	 * Media identifiers from which to filter the favorite assets
	 */
	private String mediaIdIn;
	/**
	 * Indicates whether the results should be filtered by origin UDID using the
	  current
	 */
	private Boolean udidEqualCurrent;

	// mediaTypeEqual:
	public Integer getMediaTypeEqual(){
		return this.mediaTypeEqual;
	}
	public void setMediaTypeEqual(Integer mediaTypeEqual){
		this.mediaTypeEqual = mediaTypeEqual;
	}

	public void mediaTypeEqual(String multirequestToken){
		setToken("mediaTypeEqual", multirequestToken);
	}

	// mediaIdIn:
	public String getMediaIdIn(){
		return this.mediaIdIn;
	}
	public void setMediaIdIn(String mediaIdIn){
		this.mediaIdIn = mediaIdIn;
	}

	public void mediaIdIn(String multirequestToken){
		setToken("mediaIdIn", multirequestToken);
	}

	// udidEqualCurrent:
	public Boolean getUdidEqualCurrent(){
		return this.udidEqualCurrent;
	}
	public void setUdidEqualCurrent(Boolean udidEqualCurrent){
		this.udidEqualCurrent = udidEqualCurrent;
	}

	public void udidEqualCurrent(String multirequestToken){
		setToken("udidEqualCurrent", multirequestToken);
	}


	public FavoriteFilter() {
		super();
	}

	public FavoriteFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		mediaTypeEqual = GsonParser.parseInt(jsonObject.get("mediaTypeEqual"));
		mediaIdIn = GsonParser.parseString(jsonObject.get("mediaIdIn"));
		udidEqualCurrent = GsonParser.parseBoolean(jsonObject.get("udidEqualCurrent"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaFavoriteFilter");
		kparams.add("mediaTypeEqual", this.mediaTypeEqual);
		kparams.add("mediaIdIn", this.mediaIdIn);
		kparams.add("udidEqualCurrent", this.udidEqualCurrent);
		return kparams;
	}

}

