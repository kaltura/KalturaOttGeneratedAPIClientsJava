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
import com.kaltura.client.enums.MetaDataType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Meta filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MetaFilter.Tokenizer.class)
public class MetaFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idIn();
		String assetStructIdEqual();
		String dataTypeEqual();
		String multipleValueEqual();
	}

	/**
	 * Comma separated identifiers
	 */
	private String idIn;
	/**
	 * Filter Metas that are contained in a specific asset struct
	 */
	private Long assetStructIdEqual;
	/**
	 * Meta data type to filter by
	 */
	private MetaDataType dataTypeEqual;
	/**
	 * Filter metas by multipleValueEqual value
	 */
	private Boolean multipleValueEqual;

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

	// dataTypeEqual:
	public MetaDataType getDataTypeEqual(){
		return this.dataTypeEqual;
	}
	public void setDataTypeEqual(MetaDataType dataTypeEqual){
		this.dataTypeEqual = dataTypeEqual;
	}

	public void dataTypeEqual(String multirequestToken){
		setToken("dataTypeEqual", multirequestToken);
	}

	// multipleValueEqual:
	public Boolean getMultipleValueEqual(){
		return this.multipleValueEqual;
	}
	public void setMultipleValueEqual(Boolean multipleValueEqual){
		this.multipleValueEqual = multipleValueEqual;
	}

	public void multipleValueEqual(String multirequestToken){
		setToken("multipleValueEqual", multirequestToken);
	}


	public MetaFilter() {
		super();
	}

	public MetaFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		assetStructIdEqual = GsonParser.parseLong(jsonObject.get("assetStructIdEqual"));
		dataTypeEqual = MetaDataType.get(GsonParser.parseString(jsonObject.get("dataTypeEqual")));
		multipleValueEqual = GsonParser.parseBoolean(jsonObject.get("multipleValueEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMetaFilter");
		kparams.add("idIn", this.idIn);
		kparams.add("assetStructIdEqual", this.assetStructIdEqual);
		kparams.add("dataTypeEqual", this.dataTypeEqual);
		kparams.add("multipleValueEqual", this.multipleValueEqual);
		return kparams;
	}

}

