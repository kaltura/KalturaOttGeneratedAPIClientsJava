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
import com.kaltura.client.enums.HouseholdUserStatus;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Household user
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdUser.Tokenizer.class)
public class HouseholdUser extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String householdId();
		String userId();
		String isMaster();
		String householdMasterUsername();
		String status();
		String isDefault();
	}

	/**
	 * The identifier of the household
	 */
	private Integer householdId;
	/**
	 * The identifier of the user
	 */
	private String userId;
	/**
	 * True if the user added as master use
	 */
	private Boolean isMaster;
	/**
	 * The username of the household master for adding a user in status pending for the
	  household master to approve
	 */
	private String householdMasterUsername;
	/**
	 * The status of the user in the household
	 */
	private HouseholdUserStatus status;
	/**
	 * True if the user is a default user
	 */
	private Boolean isDefault;

	// householdId:
	public Integer getHouseholdId(){
		return this.householdId;
	}
	public void setHouseholdId(Integer householdId){
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

	// isMaster:
	public Boolean getIsMaster(){
		return this.isMaster;
	}
	public void setIsMaster(Boolean isMaster){
		this.isMaster = isMaster;
	}

	public void isMaster(String multirequestToken){
		setToken("isMaster", multirequestToken);
	}

	// householdMasterUsername:
	public String getHouseholdMasterUsername(){
		return this.householdMasterUsername;
	}
	public void setHouseholdMasterUsername(String householdMasterUsername){
		this.householdMasterUsername = householdMasterUsername;
	}

	public void householdMasterUsername(String multirequestToken){
		setToken("householdMasterUsername", multirequestToken);
	}

	// status:
	public HouseholdUserStatus getStatus(){
		return this.status;
	}
	// isDefault:
	public Boolean getIsDefault(){
		return this.isDefault;
	}

	public HouseholdUser() {
		super();
	}

	public HouseholdUser(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		householdId = GsonParser.parseInt(jsonObject.get("householdId"));
		userId = GsonParser.parseString(jsonObject.get("userId"));
		isMaster = GsonParser.parseBoolean(jsonObject.get("isMaster"));
		householdMasterUsername = GsonParser.parseString(jsonObject.get("householdMasterUsername"));
		status = HouseholdUserStatus.get(GsonParser.parseString(jsonObject.get("status")));
		isDefault = GsonParser.parseBoolean(jsonObject.get("isDefault"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdUser");
		kparams.add("householdId", this.householdId);
		kparams.add("userId", this.userId);
		kparams.add("isMaster", this.isMaster);
		kparams.add("householdMasterUsername", this.householdMasterUsername);
		return kparams;
	}

}

