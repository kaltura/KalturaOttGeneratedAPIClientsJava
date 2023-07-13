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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MediaFileDynamicData.Tokenizer.class)
public class MediaFileDynamicData extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String mediaFileTypeId();
		String mediaFileTypeKeyName();
		String value();
	}

	/**
	 * An integer representing the identifier of the value.
	 */
	private Long id;
	/**
	 * An integer representing the the mediaFileType holding the keys for which the
	  values should be stored.
	 */
	private Long mediaFileTypeId;
	/**
	 * A string representing the key name within the mediaFileType that identifies the
	  list corresponding             to that key name.
	 */
	private String mediaFileTypeKeyName;
	/**
	 * Dynamic data value
	 */
	private String value;

	// id:
	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
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

	// value:
	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}

	public void value(String multirequestToken){
		setToken("value", multirequestToken);
	}


	public MediaFileDynamicData() {
		super();
	}

	public MediaFileDynamicData(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		mediaFileTypeId = GsonParser.parseLong(jsonObject.get("mediaFileTypeId"));
		mediaFileTypeKeyName = GsonParser.parseString(jsonObject.get("mediaFileTypeKeyName"));
		value = GsonParser.parseString(jsonObject.get("value"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMediaFileDynamicData");
		kparams.add("id", this.id);
		kparams.add("mediaFileTypeId", this.mediaFileTypeId);
		kparams.add("mediaFileTypeKeyName", this.mediaFileTypeKeyName);
		kparams.add("value", this.value);
		return kparams;
	}

}

