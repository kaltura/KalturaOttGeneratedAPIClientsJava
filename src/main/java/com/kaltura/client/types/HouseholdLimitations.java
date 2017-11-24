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
// Copyright (C) 2006-2017  Kaltura Inc.
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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Household limitations details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdLimitations.Tokenizer.class)
public class HouseholdLimitations extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String concurrentLimit();
		String deviceLimit();
		String deviceFrequency();
		String deviceFrequencyDescription();
		String userFrequency();
		String userFrequencyDescription();
		String npvrQuotaInSeconds();
		String usersLimit();
		RequestBuilder.ListTokenizer<HouseholdDeviceFamilyLimitations.Tokenizer> deviceFamiliesLimitations();
	}

	/**
	 * Household limitation module identifier
	 */
	private Integer id;
	/**
	 * Household limitation module name
	 */
	private String name;
	/**
	 * Max number of streams allowed for the household
	 */
	private Integer concurrentLimit;
	/**
	 * Max number of devices allowed for the household
	 */
	private Integer deviceLimit;
	/**
	 * Allowed device change frequency code
	 */
	private Integer deviceFrequency;
	/**
	 * Allowed device change frequency description
	 */
	private String deviceFrequencyDescription;
	/**
	 * Allowed user change frequency code
	 */
	private Integer userFrequency;
	/**
	 * Allowed user change frequency description
	 */
	private String userFrequencyDescription;
	/**
	 * Allowed NPVR Quota in Seconds
	 */
	private Integer npvrQuotaInSeconds;
	/**
	 * Max number of users allowed for the household
	 */
	private Integer usersLimit;
	/**
	 * Device families limitations
	 */
	private List<HouseholdDeviceFamilyLimitations> deviceFamiliesLimitations;

	// id:
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
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

	// deviceFrequency:
	public Integer getDeviceFrequency(){
		return this.deviceFrequency;
	}
	public void setDeviceFrequency(Integer deviceFrequency){
		this.deviceFrequency = deviceFrequency;
	}

	public void deviceFrequency(String multirequestToken){
		setToken("deviceFrequency", multirequestToken);
	}

	// deviceFrequencyDescription:
	public String getDeviceFrequencyDescription(){
		return this.deviceFrequencyDescription;
	}
	public void setDeviceFrequencyDescription(String deviceFrequencyDescription){
		this.deviceFrequencyDescription = deviceFrequencyDescription;
	}

	public void deviceFrequencyDescription(String multirequestToken){
		setToken("deviceFrequencyDescription", multirequestToken);
	}

	// userFrequency:
	public Integer getUserFrequency(){
		return this.userFrequency;
	}
	public void setUserFrequency(Integer userFrequency){
		this.userFrequency = userFrequency;
	}

	public void userFrequency(String multirequestToken){
		setToken("userFrequency", multirequestToken);
	}

	// userFrequencyDescription:
	public String getUserFrequencyDescription(){
		return this.userFrequencyDescription;
	}
	public void setUserFrequencyDescription(String userFrequencyDescription){
		this.userFrequencyDescription = userFrequencyDescription;
	}

	public void userFrequencyDescription(String multirequestToken){
		setToken("userFrequencyDescription", multirequestToken);
	}

	// npvrQuotaInSeconds:
	public Integer getNpvrQuotaInSeconds(){
		return this.npvrQuotaInSeconds;
	}
	public void setNpvrQuotaInSeconds(Integer npvrQuotaInSeconds){
		this.npvrQuotaInSeconds = npvrQuotaInSeconds;
	}

	public void npvrQuotaInSeconds(String multirequestToken){
		setToken("npvrQuotaInSeconds", multirequestToken);
	}

	// usersLimit:
	public Integer getUsersLimit(){
		return this.usersLimit;
	}
	public void setUsersLimit(Integer usersLimit){
		this.usersLimit = usersLimit;
	}

	public void usersLimit(String multirequestToken){
		setToken("usersLimit", multirequestToken);
	}

	// deviceFamiliesLimitations:
	public List<HouseholdDeviceFamilyLimitations> getDeviceFamiliesLimitations(){
		return this.deviceFamiliesLimitations;
	}
	public void setDeviceFamiliesLimitations(List<HouseholdDeviceFamilyLimitations> deviceFamiliesLimitations){
		this.deviceFamiliesLimitations = deviceFamiliesLimitations;
	}


	public HouseholdLimitations() {
		super();
	}

	public HouseholdLimitations(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		concurrentLimit = GsonParser.parseInt(jsonObject.get("concurrentLimit"));
		deviceLimit = GsonParser.parseInt(jsonObject.get("deviceLimit"));
		deviceFrequency = GsonParser.parseInt(jsonObject.get("deviceFrequency"));
		deviceFrequencyDescription = GsonParser.parseString(jsonObject.get("deviceFrequencyDescription"));
		userFrequency = GsonParser.parseInt(jsonObject.get("userFrequency"));
		userFrequencyDescription = GsonParser.parseString(jsonObject.get("userFrequencyDescription"));
		npvrQuotaInSeconds = GsonParser.parseInt(jsonObject.get("npvrQuotaInSeconds"));
		usersLimit = GsonParser.parseInt(jsonObject.get("usersLimit"));
		deviceFamiliesLimitations = GsonParser.parseArray(jsonObject.getAsJsonArray("deviceFamiliesLimitations"), HouseholdDeviceFamilyLimitations.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdLimitations");
		return kparams;
	}

}

