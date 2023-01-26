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
 * Partner configuration for segments configuration
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SegmentationPartnerConfiguration.Tokenizer.class)
public class SegmentationPartnerConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String maxCalculatedPeriod();
		String maxDynamicSegments();
	}

	/**
	 * The maximum number of past days to be calculated for dynamic segments,
	  default=180
	 */
	private Integer maxCalculatedPeriod;
	/**
	 * How many dynamic segments (segments with conditions) the operator is allowed to
	  have, default=50
	 */
	private Integer maxDynamicSegments;

	// maxCalculatedPeriod:
	public Integer getMaxCalculatedPeriod(){
		return this.maxCalculatedPeriod;
	}
	public void setMaxCalculatedPeriod(Integer maxCalculatedPeriod){
		this.maxCalculatedPeriod = maxCalculatedPeriod;
	}

	public void maxCalculatedPeriod(String multirequestToken){
		setToken("maxCalculatedPeriod", multirequestToken);
	}

	// maxDynamicSegments:
	public Integer getMaxDynamicSegments(){
		return this.maxDynamicSegments;
	}
	public void setMaxDynamicSegments(Integer maxDynamicSegments){
		this.maxDynamicSegments = maxDynamicSegments;
	}

	public void maxDynamicSegments(String multirequestToken){
		setToken("maxDynamicSegments", multirequestToken);
	}


	public SegmentationPartnerConfiguration() {
		super();
	}

	public SegmentationPartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		maxCalculatedPeriod = GsonParser.parseInt(jsonObject.get("maxCalculatedPeriod"));
		maxDynamicSegments = GsonParser.parseInt(jsonObject.get("maxDynamicSegments"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSegmentationPartnerConfiguration");
		kparams.add("maxCalculatedPeriod", this.maxCalculatedPeriod);
		kparams.add("maxDynamicSegments", this.maxDynamicSegments);
		return kparams;
	}

}

