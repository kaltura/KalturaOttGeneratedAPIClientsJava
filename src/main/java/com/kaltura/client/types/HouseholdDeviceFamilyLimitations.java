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
 * Device family limitations details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdDeviceFamilyLimitations.Tokenizer.class)
public class HouseholdDeviceFamilyLimitations extends DeviceFamilyBase {
	
	public interface Tokenizer extends DeviceFamilyBase.Tokenizer {
		String frequency();
		String deviceLimit();
		String concurrentLimit();
	}

	/**
	 * Allowed device change frequency code
	 */
	private Integer frequency;
	/**
	 * Max number of devices allowed for this family
	 */
	private Integer deviceLimit;
	/**
	 * Max number of streams allowed for this family
	 */
	private Integer concurrentLimit;

	// frequency:
	public Integer getFrequency(){
		return this.frequency;
	}
	public void setFrequency(Integer frequency){
		this.frequency = frequency;
	}

	public void frequency(String multirequestToken){
		setToken("frequency", multirequestToken);
	}

	// deviceLimit:
	public Integer getDeviceLimit(){
		return this.deviceLimit;
	}
	public void setDeviceLimit(Integer deviceLimit){
		this.deviceLimit = deviceLimit;
	}

	public void deviceLimit(String multirequestToken){
		setToken("deviceLimit", multirequestToken);
	}

	// concurrentLimit:
	public Integer getConcurrentLimit(){
		return this.concurrentLimit;
	}
	public void setConcurrentLimit(Integer concurrentLimit){
		this.concurrentLimit = concurrentLimit;
	}

	public void concurrentLimit(String multirequestToken){
		setToken("concurrentLimit", multirequestToken);
	}


	public HouseholdDeviceFamilyLimitations() {
		super();
	}

	public HouseholdDeviceFamilyLimitations(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		frequency = GsonParser.parseInt(jsonObject.get("frequency"));
		deviceLimit = GsonParser.parseInt(jsonObject.get("deviceLimit"));
		concurrentLimit = GsonParser.parseInt(jsonObject.get("concurrentLimit"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdDeviceFamilyLimitations");
		kparams.add("frequency", this.frequency);
		kparams.add("deviceLimit", this.deviceLimit);
		kparams.add("concurrentLimit", this.concurrentLimit);
		return kparams;
	}

}

