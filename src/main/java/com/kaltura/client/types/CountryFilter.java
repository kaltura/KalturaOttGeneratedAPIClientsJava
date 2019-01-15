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
 * Country filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CountryFilter.Tokenizer.class)
public class CountryFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idIn();
		String ipEqual();
		String ipEqualCurrent();
	}

	/**
	 * Country identifiers
	 */
	private String idIn;
	/**
	 * Ip to identify the country
	 */
	private String ipEqual;
	/**
	 * Indicates if to get the IP from the request
	 */
	private Boolean ipEqualCurrent;

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

	// ipEqual:
	public String getIpEqual(){
		return this.ipEqual;
	}
	public void setIpEqual(String ipEqual){
		this.ipEqual = ipEqual;
	}

	public void ipEqual(String multirequestToken){
		setToken("ipEqual", multirequestToken);
	}

	// ipEqualCurrent:
	public Boolean getIpEqualCurrent(){
		return this.ipEqualCurrent;
	}
	public void setIpEqualCurrent(Boolean ipEqualCurrent){
		this.ipEqualCurrent = ipEqualCurrent;
	}

	public void ipEqualCurrent(String multirequestToken){
		setToken("ipEqualCurrent", multirequestToken);
	}


	public CountryFilter() {
		super();
	}

	public CountryFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		ipEqual = GsonParser.parseString(jsonObject.get("ipEqual"));
		ipEqualCurrent = GsonParser.parseBoolean(jsonObject.get("ipEqualCurrent"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCountryFilter");
		kparams.add("idIn", this.idIn);
		kparams.add("ipEqual", this.ipEqual);
		kparams.add("ipEqualCurrent", this.ipEqualCurrent);
		return kparams;
	}

}

