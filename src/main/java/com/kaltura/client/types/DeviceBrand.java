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
import com.kaltura.client.enums.DeviceBrandType;
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
 * Device brand details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(DeviceBrand.Tokenizer.class)
public class DeviceBrand extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String deviceFamilyid();
		String type();
	}

	/**
	 * Device brand identifier
	 */
	private Long id;
	/**
	 * Device brand name
	 */
	private String name;
	/**
	 * Device family identifier
	 */
	private Long deviceFamilyid;
	/**
	 * Type of device family.               if this device family belongs only to this
	  group,               otherwise.
	 */
	private DeviceBrandType type;

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

	// deviceFamilyid:
	public Long getDeviceFamilyid(){
		return this.deviceFamilyid;
	}
	public void setDeviceFamilyid(Long deviceFamilyid){
		this.deviceFamilyid = deviceFamilyid;
	}

	public void deviceFamilyid(String multirequestToken){
		setToken("deviceFamilyid", multirequestToken);
	}

	// type:
	public DeviceBrandType getType(){
		return this.type;
	}

	public DeviceBrand() {
		super();
	}

	public DeviceBrand(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		deviceFamilyid = GsonParser.parseLong(jsonObject.get("deviceFamilyid"));
		type = DeviceBrandType.get(GsonParser.parseString(jsonObject.get("type")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaDeviceBrand");
		kparams.add("id", this.id);
		kparams.add("name", this.name);
		kparams.add("deviceFamilyid", this.deviceFamilyid);
		return kparams;
	}

}

