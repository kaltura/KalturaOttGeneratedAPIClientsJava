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
// Copyright (C) 2006-2021  Kaltura Inc.
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
 * Kaltura Session Characteristic
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SessionCharacteristic.Tokenizer.class)
public class SessionCharacteristic extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String regionId();
		String userSegmentsIds();
		String userRolesIds();
		String userSessionProfilesIds();
	}

	/**
	 * Session characteristic identifier
	 */
	private String id;
	/**
	 * Region identifier
	 */
	private Integer regionId;
	/**
	 * Comma-separated list of user segments identifiers
	 */
	private String userSegmentsIds;
	/**
	 * Comma-separated list of user roles identifiers
	 */
	private String userRolesIds;
	/**
	 * Comma-separated list of user session profiles identifiers
	 */
	private String userSessionProfilesIds;

	// id:
	public String getId(){
		return this.id;
	}
	// regionId:
	public Integer getRegionId(){
		return this.regionId;
	}
	// userSegmentsIds:
	public String getUserSegmentsIds(){
		return this.userSegmentsIds;
	}
	// userRolesIds:
	public String getUserRolesIds(){
		return this.userRolesIds;
	}
	// userSessionProfilesIds:
	public String getUserSessionProfilesIds(){
		return this.userSessionProfilesIds;
	}

	public SessionCharacteristic() {
		super();
	}

	public SessionCharacteristic(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		regionId = GsonParser.parseInt(jsonObject.get("regionId"));
		userSegmentsIds = GsonParser.parseString(jsonObject.get("userSegmentsIds"));
		userRolesIds = GsonParser.parseString(jsonObject.get("userRolesIds"));
		userSessionProfilesIds = GsonParser.parseString(jsonObject.get("userSessionProfilesIds"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSessionCharacteristic");
		return kparams;
	}

}

