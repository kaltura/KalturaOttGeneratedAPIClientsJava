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
// Copyright (C) 2006-2022  Kaltura Inc.
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
@MultiRequestBuilder.Tokenizer(ImmediateRecording.Tokenizer.class)
public class ImmediateRecording extends Recording {
	
	public interface Tokenizer extends Recording.Tokenizer {
		String endPadding();
		String absoluteStart();
	}

	/**
	 * Household specific end padding of the recording
	 */
	private Integer endPadding;
	/**
	 * Household absolute start time of the immediate recording
	 */
	private Long absoluteStart;

	// endPadding:
	public Integer getEndPadding(){
		return this.endPadding;
	}
	public void setEndPadding(Integer endPadding){
		this.endPadding = endPadding;
	}

	public void endPadding(String multirequestToken){
		setToken("endPadding", multirequestToken);
	}

	// absoluteStart:
	public Long getAbsoluteStart(){
		return this.absoluteStart;
	}
	public void setAbsoluteStart(Long absoluteStart){
		this.absoluteStart = absoluteStart;
	}

	public void absoluteStart(String multirequestToken){
		setToken("absoluteStart", multirequestToken);
	}


	public ImmediateRecording() {
		super();
	}

	public ImmediateRecording(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		endPadding = GsonParser.parseInt(jsonObject.get("endPadding"));
		absoluteStart = GsonParser.parseLong(jsonObject.get("absoluteStart"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaImmediateRecording");
		kparams.add("endPadding", this.endPadding);
		kparams.add("absoluteStart", this.absoluteStart);
		return kparams;
	}

}

