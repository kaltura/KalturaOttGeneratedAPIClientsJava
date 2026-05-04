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
 * Defines the scope of content types to consider for the watch condition.         
      Default Behavior: If the object is empty or no properties are sent, ALL
  content types (Recordings, Programs, and all Media Types) are counted.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ContentTypeSelector.Tokenizer.class)
public class ContentTypeSelector extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String includeRecordings();
		String includePrograms();
		String mediaTypeIdIn();
	}

	/**
	 * Determines if Recording assets are counted.              Omitted or true:
	  Recordings are included.              false: Recordings are excluded.
	 */
	private Boolean includeRecordings;
	/**
	 * Determines if EPG Program assets (Live, Catch-up, Start-over) are counted.      
	         Omitted or true: Programs are included.              false: Programs are
	  excluded.
	 */
	private Boolean includePrograms;
	/**
	 * Filter for specific playable media types (e.g., Movie, Episode).             
	  Omitted: ALL playable media types are included.              Provided (List of
	  IDs): ONLY the media types matching the listed IDs are included.             
	  Provided (Empty String): NO media types are included.              Constraint:
	  IDs must correspond to valid playable media types. Providing an invalid ID will
	  result in an error.
	 */
	private String mediaTypeIdIn;

	// includeRecordings:
	public Boolean getIncludeRecordings(){
		return this.includeRecordings;
	}
	public void setIncludeRecordings(Boolean includeRecordings){
		this.includeRecordings = includeRecordings;
	}

	public void includeRecordings(String multirequestToken){
		setToken("includeRecordings", multirequestToken);
	}

	// includePrograms:
	public Boolean getIncludePrograms(){
		return this.includePrograms;
	}
	public void setIncludePrograms(Boolean includePrograms){
		this.includePrograms = includePrograms;
	}

	public void includePrograms(String multirequestToken){
		setToken("includePrograms", multirequestToken);
	}

	// mediaTypeIdIn:
	public String getMediaTypeIdIn(){
		return this.mediaTypeIdIn;
	}
	public void setMediaTypeIdIn(String mediaTypeIdIn){
		this.mediaTypeIdIn = mediaTypeIdIn;
	}

	public void mediaTypeIdIn(String multirequestToken){
		setToken("mediaTypeIdIn", multirequestToken);
	}


	public ContentTypeSelector() {
		super();
	}

	public ContentTypeSelector(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		includeRecordings = GsonParser.parseBoolean(jsonObject.get("includeRecordings"));
		includePrograms = GsonParser.parseBoolean(jsonObject.get("includePrograms"));
		mediaTypeIdIn = GsonParser.parseString(jsonObject.get("mediaTypeIdIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaContentTypeSelector");
		kparams.add("includeRecordings", this.includeRecordings);
		kparams.add("includePrograms", this.includePrograms);
		kparams.add("mediaTypeIdIn", this.mediaTypeIdIn);
		return kparams;
	}

}

