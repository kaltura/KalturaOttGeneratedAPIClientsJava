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
@MultiRequestBuilder.Tokenizer(ChannelExternalFilter.Tokenizer.class)
public class ChannelExternalFilter extends AssetFilter {
	
	public interface Tokenizer extends AssetFilter.Tokenizer {
		String idEqual();
		String utcOffsetEqual();
		String freeText();
		String alias();
	}

	/**
	 * External Channel Id.
	 */
	private Integer idEqual;
	/**
	 * UtcOffsetEqual
	 */
	private Double utcOffsetEqual;
	/**
	 * FreeTextEqual
	 */
	private String freeText;
	/**
	 * Alias for External Channel Id.
	 */
	private String alias;

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

	// utcOffsetEqual:
	public Double getUtcOffsetEqual(){
		return this.utcOffsetEqual;
	}
	public void setUtcOffsetEqual(Double utcOffsetEqual){
		this.utcOffsetEqual = utcOffsetEqual;
	}

	public void utcOffsetEqual(String multirequestToken){
		setToken("utcOffsetEqual", multirequestToken);
	}

	// freeText:
	public String getFreeText(){
		return this.freeText;
	}
	public void setFreeText(String freeText){
		this.freeText = freeText;
	}

	public void freeText(String multirequestToken){
		setToken("freeText", multirequestToken);
	}

	// alias:
	public String getAlias(){
		return this.alias;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}

	public void alias(String multirequestToken){
		setToken("alias", multirequestToken);
	}


	public ChannelExternalFilter() {
		super();
	}

	public ChannelExternalFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseInt(jsonObject.get("idEqual"));
		utcOffsetEqual = GsonParser.parseDouble(jsonObject.get("utcOffsetEqual"));
		freeText = GsonParser.parseString(jsonObject.get("freeText"));
		alias = GsonParser.parseString(jsonObject.get("alias"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaChannelExternalFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("utcOffsetEqual", this.utcOffsetEqual);
		kparams.add("freeText", this.freeText);
		kparams.add("alias", this.alias);
		return kparams;
	}

}

