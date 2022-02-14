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
@MultiRequestBuilder.Tokenizer(EpgServicePartnerConfiguration.Tokenizer.class)
public class EpgServicePartnerConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String numberOfSlots();
		String firstSlotOffset();
	}

	/**
	 * The number of slots (NOS) that are supported (1, 2, 3, 4, 6, 8, 12, 24)
	 */
	private Integer numberOfSlots;
	/**
	 * The offset of the first slot from 00:00 UTC
	 */
	private Integer firstSlotOffset;

	// numberOfSlots:
	public Integer getNumberOfSlots(){
		return this.numberOfSlots;
	}
	public void setNumberOfSlots(Integer numberOfSlots){
		this.numberOfSlots = numberOfSlots;
	}

	public void numberOfSlots(String multirequestToken){
		setToken("numberOfSlots", multirequestToken);
	}

	// firstSlotOffset:
	public Integer getFirstSlotOffset(){
		return this.firstSlotOffset;
	}
	public void setFirstSlotOffset(Integer firstSlotOffset){
		this.firstSlotOffset = firstSlotOffset;
	}

	public void firstSlotOffset(String multirequestToken){
		setToken("firstSlotOffset", multirequestToken);
	}


	public EpgServicePartnerConfiguration() {
		super();
	}

	public EpgServicePartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		numberOfSlots = GsonParser.parseInt(jsonObject.get("numberOfSlots"));
		firstSlotOffset = GsonParser.parseInt(jsonObject.get("firstSlotOffset"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEpgServicePartnerConfiguration");
		kparams.add("numberOfSlots", this.numberOfSlots);
		kparams.add("firstSlotOffset", this.firstSlotOffset);
		return kparams;
	}

}

