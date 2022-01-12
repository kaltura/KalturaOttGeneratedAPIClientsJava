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
@MultiRequestBuilder.Tokenizer(UnifiedChannelInfo.Tokenizer.class)
public class UnifiedChannelInfo extends UnifiedChannel {
	
	public interface Tokenizer extends UnifiedChannel.Tokenizer {
		String name();
		String startDateInSeconds();
		String endDateInSeconds();
	}

	/**
	 * Channel&amp;#160;name
	 */
	private String name;
	/**
	 * Start date in seconds
	 */
	private Long startDateInSeconds;
	/**
	 * End date in seconds
	 */
	private Long endDateInSeconds;

	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// startDateInSeconds:
	public Long getStartDateInSeconds(){
		return this.startDateInSeconds;
	}
	public void setStartDateInSeconds(Long startDateInSeconds){
		this.startDateInSeconds = startDateInSeconds;
	}

	public void startDateInSeconds(String multirequestToken){
		setToken("startDateInSeconds", multirequestToken);
	}

	// endDateInSeconds:
	public Long getEndDateInSeconds(){
		return this.endDateInSeconds;
	}
	public void setEndDateInSeconds(Long endDateInSeconds){
		this.endDateInSeconds = endDateInSeconds;
	}

	public void endDateInSeconds(String multirequestToken){
		setToken("endDateInSeconds", multirequestToken);
	}


	public UnifiedChannelInfo() {
		super();
	}

	public UnifiedChannelInfo(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		name = GsonParser.parseString(jsonObject.get("name"));
		startDateInSeconds = GsonParser.parseLong(jsonObject.get("startDateInSeconds"));
		endDateInSeconds = GsonParser.parseLong(jsonObject.get("endDateInSeconds"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUnifiedChannelInfo");
		kparams.add("name", this.name);
		kparams.add("startDateInSeconds", this.startDateInSeconds);
		kparams.add("endDateInSeconds", this.endDateInSeconds);
		return kparams;
	}

}

