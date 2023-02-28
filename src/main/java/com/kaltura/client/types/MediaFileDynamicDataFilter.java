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
@MultiRequestBuilder.Tokenizer(MediaFileDynamicDataFilter.Tokenizer.class)
public class MediaFileDynamicDataFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idIn();
		String mediaFileTypeId();
		String mediaFileTypeKeyName();
		String valueEqual();
		String valueStartsWith();
	}

	/**
	 * A comma-separated list of KalturaMediaFileDynamicData.Id to be searched.
	 */
	private String idIn;
	/**
	 * An integer representing the the mediaFileType holding the keys for which the
	  values should be stored.
	 */
	private Long mediaFileTypeId;
	/**
	 * A string representing the key name within the mediaFileType that identifies the
	  list corresponding              to that key name.
	 */
	private String mediaFileTypeKeyName;
	/**
	 * A string representing a specific value to be searched.
	 */
	private String valueEqual;
	/**
	 * A string representing the beginning of multiple (zero or more) matching values.
	 */
	private String valueStartsWith;

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

	// mediaFileTypeId:
	public Long getMediaFileTypeId(){
		return this.mediaFileTypeId;
	}
	public void setMediaFileTypeId(Long mediaFileTypeId){
		this.mediaFileTypeId = mediaFileTypeId;
	}

	public void mediaFileTypeId(String multirequestToken){
		setToken("mediaFileTypeId", multirequestToken);
	}

	// mediaFileTypeKeyName:
	public String getMediaFileTypeKeyName(){
		return this.mediaFileTypeKeyName;
	}
	public void setMediaFileTypeKeyName(String mediaFileTypeKeyName){
		this.mediaFileTypeKeyName = mediaFileTypeKeyName;
	}

	public void mediaFileTypeKeyName(String multirequestToken){
		setToken("mediaFileTypeKeyName", multirequestToken);
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

	// valueStartsWith:
	public String getValueStartsWith(){
		return this.valueStartsWith;
	}
	public void setValueStartsWith(String valueStartsWith){
		this.valueStartsWith = valueStartsWith;
	}

	public void valueStartsWith(String multirequestToken){
		setToken("valueStartsWith", multirequestToken);
	}


	public MediaFileDynamicDataFilter() {
		super();
	}

	public MediaFileDynamicDataFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		mediaFileTypeId = GsonParser.parseLong(jsonObject.get("mediaFileTypeId"));
		mediaFileTypeKeyName = GsonParser.parseString(jsonObject.get("mediaFileTypeKeyName"));
		valueEqual = GsonParser.parseString(jsonObject.get("valueEqual"));
		valueStartsWith = GsonParser.parseString(jsonObject.get("valueStartsWith"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMediaFileDynamicDataFilter");
		kparams.add("idIn", this.idIn);
		kparams.add("mediaFileTypeId", this.mediaFileTypeId);
		kparams.add("mediaFileTypeKeyName", this.mediaFileTypeKeyName);
		kparams.add("valueEqual", this.valueEqual);
		kparams.add("valueStartsWith", this.valueStartsWith);
		return kparams;
	}

}

