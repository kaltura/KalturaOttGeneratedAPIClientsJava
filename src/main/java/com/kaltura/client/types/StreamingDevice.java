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
import com.kaltura.client.types.SlimAsset;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Watch history asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(StreamingDevice.Tokenizer.class)
public class StreamingDevice extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		SlimAsset.Tokenizer asset();
		String userId();
		String udid();
	}

	/**
	 * Asset
	 */
	private SlimAsset asset;
	/**
	 * User identifier
	 */
	private String userId;
	/**
	 * Device UDID
	 */
	private String udid;

	// asset:
	public SlimAsset getAsset(){
		return this.asset;
	}
	// userId:
	public String getUserId(){
		return this.userId;
	}
	// udid:
	public String getUdid(){
		return this.udid;
	}
	public void setUdid(String udid){
		this.udid = udid;
	}

	public void udid(String multirequestToken){
		setToken("udid", multirequestToken);
	}


	public StreamingDevice() {
		super();
	}

	public StreamingDevice(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		asset = GsonParser.parseObject(jsonObject.getAsJsonObject("asset"), SlimAsset.class);
		userId = GsonParser.parseString(jsonObject.get("userId"));
		udid = GsonParser.parseString(jsonObject.get("udid"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaStreamingDevice");
		kparams.add("udid", this.udid);
		return kparams;
	}

}

