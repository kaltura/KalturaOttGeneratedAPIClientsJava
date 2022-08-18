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
import com.kaltura.client.enums.EvictionPolicyType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Partner concurrency configuration
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ConcurrencyPartnerConfig.Tokenizer.class)
public class ConcurrencyPartnerConfig extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		String deviceFamilyIds();
		String evictionPolicy();
		String concurrencyThresholdInSeconds();
		String revokeOnDeviceDelete();
		String excludeFreeContentFromConcurrency();
	}

	/**
	 * Comma separated list of device Family Ids order by their priority.
	 */
	private String deviceFamilyIds;
	/**
	 * Policy of eviction devices
	 */
	private EvictionPolicyType evictionPolicy;
	/**
	 * Concurrency threshold in seconds
	 */
	private Long concurrencyThresholdInSeconds;
	/**
	 * Revoke on device delete
	 */
	private Boolean revokeOnDeviceDelete;
	/**
	 * If set to true then for all concurrency checks in all APIs, system shall exclude
	  free content from counting towards the use of a concurrency slot
	 */
	private Boolean excludeFreeContentFromConcurrency;

	// deviceFamilyIds:
	public String getDeviceFamilyIds(){
		return this.deviceFamilyIds;
	}
	public void setDeviceFamilyIds(String deviceFamilyIds){
		this.deviceFamilyIds = deviceFamilyIds;
	}

	public void deviceFamilyIds(String multirequestToken){
		setToken("deviceFamilyIds", multirequestToken);
	}

	// evictionPolicy:
	public EvictionPolicyType getEvictionPolicy(){
		return this.evictionPolicy;
	}
	public void setEvictionPolicy(EvictionPolicyType evictionPolicy){
		this.evictionPolicy = evictionPolicy;
	}

	public void evictionPolicy(String multirequestToken){
		setToken("evictionPolicy", multirequestToken);
	}

	// concurrencyThresholdInSeconds:
	public Long getConcurrencyThresholdInSeconds(){
		return this.concurrencyThresholdInSeconds;
	}
	public void setConcurrencyThresholdInSeconds(Long concurrencyThresholdInSeconds){
		this.concurrencyThresholdInSeconds = concurrencyThresholdInSeconds;
	}

	public void concurrencyThresholdInSeconds(String multirequestToken){
		setToken("concurrencyThresholdInSeconds", multirequestToken);
	}

	// revokeOnDeviceDelete:
	public Boolean getRevokeOnDeviceDelete(){
		return this.revokeOnDeviceDelete;
	}
	public void setRevokeOnDeviceDelete(Boolean revokeOnDeviceDelete){
		this.revokeOnDeviceDelete = revokeOnDeviceDelete;
	}

	public void revokeOnDeviceDelete(String multirequestToken){
		setToken("revokeOnDeviceDelete", multirequestToken);
	}

	// excludeFreeContentFromConcurrency:
	public Boolean getExcludeFreeContentFromConcurrency(){
		return this.excludeFreeContentFromConcurrency;
	}
	public void setExcludeFreeContentFromConcurrency(Boolean excludeFreeContentFromConcurrency){
		this.excludeFreeContentFromConcurrency = excludeFreeContentFromConcurrency;
	}

	public void excludeFreeContentFromConcurrency(String multirequestToken){
		setToken("excludeFreeContentFromConcurrency", multirequestToken);
	}


	public ConcurrencyPartnerConfig() {
		super();
	}

	public ConcurrencyPartnerConfig(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		deviceFamilyIds = GsonParser.parseString(jsonObject.get("deviceFamilyIds"));
		evictionPolicy = EvictionPolicyType.get(GsonParser.parseString(jsonObject.get("evictionPolicy")));
		concurrencyThresholdInSeconds = GsonParser.parseLong(jsonObject.get("concurrencyThresholdInSeconds"));
		revokeOnDeviceDelete = GsonParser.parseBoolean(jsonObject.get("revokeOnDeviceDelete"));
		excludeFreeContentFromConcurrency = GsonParser.parseBoolean(jsonObject.get("excludeFreeContentFromConcurrency"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaConcurrencyPartnerConfig");
		kparams.add("deviceFamilyIds", this.deviceFamilyIds);
		kparams.add("evictionPolicy", this.evictionPolicy);
		kparams.add("concurrencyThresholdInSeconds", this.concurrencyThresholdInSeconds);
		kparams.add("revokeOnDeviceDelete", this.revokeOnDeviceDelete);
		kparams.add("excludeFreeContentFromConcurrency", this.excludeFreeContentFromConcurrency);
		return kparams;
	}

}

