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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SearchPriorityGroupFilter.Tokenizer.class)
public class SearchPriorityGroupFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String activeOnlyEqual();
		String idEqual();
	}

	/**
	 * Return only search priority groups that are in use
	 */
	private Boolean activeOnlyEqual;
	/**
	 * Identifier of search priority group to return
	 */
	private Long idEqual;

	// activeOnlyEqual:
	public Boolean getActiveOnlyEqual(){
		return this.activeOnlyEqual;
	}
	public void setActiveOnlyEqual(Boolean activeOnlyEqual){
		this.activeOnlyEqual = activeOnlyEqual;
	}

	public void activeOnlyEqual(String multirequestToken){
		setToken("activeOnlyEqual", multirequestToken);
	}

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


	public SearchPriorityGroupFilter() {
		super();
	}

	public SearchPriorityGroupFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		activeOnlyEqual = GsonParser.parseBoolean(jsonObject.get("activeOnlyEqual"));
		idEqual = GsonParser.parseLong(jsonObject.get("idEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSearchPriorityGroupFilter");
		kparams.add("activeOnlyEqual", this.activeOnlyEqual);
		kparams.add("idEqual", this.idEqual);
		return kparams;
	}

}

