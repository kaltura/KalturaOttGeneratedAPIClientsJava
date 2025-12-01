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
 * Program-specific semantic search parameters.              Presence of this
  object indicates programs should be included in search results.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ProgramSemanticSearchParams.Tokenizer.class)
public class ProgramSemanticSearchParams extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String endsAfter();
		String expiresAfter();
	}

	/**
	 * Only include programs that end after this timestamp (Unix epoch seconds).       
	        Optional filter.
	 */
	private Long endsAfter;
	/**
	 * Only include programs that expire after this timestamp (Unix epoch seconds).    
	           Optional filter.
	 */
	private Long expiresAfter;

	// endsAfter:
	public Long getEndsAfter(){
		return this.endsAfter;
	}
	public void setEndsAfter(Long endsAfter){
		this.endsAfter = endsAfter;
	}

	public void endsAfter(String multirequestToken){
		setToken("endsAfter", multirequestToken);
	}

	// expiresAfter:
	public Long getExpiresAfter(){
		return this.expiresAfter;
	}
	public void setExpiresAfter(Long expiresAfter){
		this.expiresAfter = expiresAfter;
	}

	public void expiresAfter(String multirequestToken){
		setToken("expiresAfter", multirequestToken);
	}


	public ProgramSemanticSearchParams() {
		super();
	}

	public ProgramSemanticSearchParams(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		endsAfter = GsonParser.parseLong(jsonObject.get("endsAfter"));
		expiresAfter = GsonParser.parseLong(jsonObject.get("expiresAfter"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaProgramSemanticSearchParams");
		kparams.add("endsAfter", this.endsAfter);
		kparams.add("expiresAfter", this.expiresAfter);
		return kparams;
	}

}

