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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ConcurrencyViolation.Tokenizer.class)
public class ConcurrencyViolation extends EventObject {
	
	public interface Tokenizer extends EventObject.Tokenizer {
		String timestamp();
		String udid();
		String assetId();
		String violationRule();
		String householdId();
		String userId();
	}

	/**
	 * Timestamp
	 */
	private Long timestamp;
	/**
	 * UDID
	 */
	private String udid;
	/**
	 * Asset Id
	 */
	private String assetId;
	/**
	 * Violation Rule
	 */
	private String violationRule;
	/**
	 * Household Id
	 */
	private String householdId;
	/**
	 * User Id
	 */
	private String userId;

	// timestamp:
	public Long getTimestamp(){
		return this.timestamp;
	}
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}

	public void timestamp(String multirequestToken){
		setToken("timestamp", multirequestToken);
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

	// assetId:
	public String getAssetId(){
		return this.assetId;
	}
	public void setAssetId(String assetId){
		this.assetId = assetId;
	}

	public void assetId(String multirequestToken){
		setToken("assetId", multirequestToken);
	}

	// violationRule:
	public String getViolationRule(){
		return this.violationRule;
	}
	public void setViolationRule(String violationRule){
		this.violationRule = violationRule;
	}

	public void violationRule(String multirequestToken){
		setToken("violationRule", multirequestToken);
	}

	// householdId:
	public String getHouseholdId(){
		return this.householdId;
	}
	public void setHouseholdId(String householdId){
		this.householdId = householdId;
	}

	public void householdId(String multirequestToken){
		setToken("householdId", multirequestToken);
	}

	// userId:
	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	public void userId(String multirequestToken){
		setToken("userId", multirequestToken);
	}


	public ConcurrencyViolation() {
		super();
	}

	public ConcurrencyViolation(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		timestamp = GsonParser.parseLong(jsonObject.get("timestamp"));
		udid = GsonParser.parseString(jsonObject.get("udid"));
		assetId = GsonParser.parseString(jsonObject.get("assetId"));
		violationRule = GsonParser.parseString(jsonObject.get("violationRule"));
		householdId = GsonParser.parseString(jsonObject.get("householdId"));
		userId = GsonParser.parseString(jsonObject.get("userId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaConcurrencyViolation");
		kparams.add("timestamp", this.timestamp);
		kparams.add("udid", this.udid);
		kparams.add("assetId", this.assetId);
		kparams.add("violationRule", this.violationRule);
		kparams.add("householdId", this.householdId);
		kparams.add("userId", this.userId);
		return kparams;
	}

}

