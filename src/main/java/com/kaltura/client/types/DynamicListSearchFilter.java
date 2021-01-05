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
// Copyright (C) 2006-2021  Kaltura Inc.
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
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * DynamicListSearchFilter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(DynamicListSearchFilter.Tokenizer.class)
public abstract class DynamicListSearchFilter extends DynamicListFilter {
	
	public interface Tokenizer extends DynamicListFilter.Tokenizer {
		String idEqual();
		String valueEqual();
	}

	/**
	 * DynamicList id to search by
	 */
	private Long idEqual;
	/**
	 * udid value that should be in the DynamicList
	 */
	private String valueEqual;

	// idEqual:
	public Long getIdEqual(){
		return this.idEqual;
	}
	public void setIdEqual(Long idEqual){
		this.idEqual = idEqual;
	}

	public void idEqual(String multirequestToken){
		setToken("idEqual", multirequestToken);
	}

	// valueEqual:
	public String getValueEqual(){
		return this.valueEqual;
	}
	public void setValueEqual(String valueEqual){
		this.valueEqual = valueEqual;
	}

	public void valueEqual(String multirequestToken){
		setToken("valueEqual", multirequestToken);
	}


	public DynamicListSearchFilter() {
		super();
	}

	public DynamicListSearchFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseLong(jsonObject.get("idEqual"));
		valueEqual = GsonParser.parseString(jsonObject.get("valueEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaDynamicListSearchFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("valueEqual", this.valueEqual);
		return kparams;
	}

}

