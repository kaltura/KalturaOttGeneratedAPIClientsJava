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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EpgNotificationSettings.Tokenizer.class)
public class EpgNotificationSettings extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String enabled();
		String deviceFamilyIds();
		String liveAssetIds();
		String backwardTimeRange();
		String forwardTimeRange();
	}

	/**
	 * EPG notification capability is enabled for the account
	 */
	private Boolean enabled;
	/**
	 * Specify which devices should receive notifications
	 */
	private String deviceFamilyIds;
	/**
	 * Specify which live assets should fire notifications
	 */
	private String liveAssetIds;
	/**
	 * The backward range (in hours), in which, EPG updates triggers a notification,   
	            every program that is updated and it’s starts time falls within this
	  range shall trigger a notification
	 */
	private Integer backwardTimeRange;
	/**
	 * The forward range (in hours), in which, EPG updates triggers a notification,    
	           every program that is updated and it’s starts time falls within this
	  range shall trigger a notification
	 */
	private Integer forwardTimeRange;

	// enabled:
	public Boolean getEnabled(){
		return this.enabled;
	}
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}

	public void enabled(String multirequestToken){
		setToken("enabled", multirequestToken);
	}

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

	// liveAssetIds:
	public String getLiveAssetIds(){
		return this.liveAssetIds;
	}
	public void setLiveAssetIds(String liveAssetIds){
		this.liveAssetIds = liveAssetIds;
	}

	public void liveAssetIds(String multirequestToken){
		setToken("liveAssetIds", multirequestToken);
	}

	// backwardTimeRange:
	public Integer getBackwardTimeRange(){
		return this.backwardTimeRange;
	}
	public void setBackwardTimeRange(Integer backwardTimeRange){
		this.backwardTimeRange = backwardTimeRange;
	}

	public void backwardTimeRange(String multirequestToken){
		setToken("backwardTimeRange", multirequestToken);
	}

	// forwardTimeRange:
	public Integer getForwardTimeRange(){
		return this.forwardTimeRange;
	}
	public void setForwardTimeRange(Integer forwardTimeRange){
		this.forwardTimeRange = forwardTimeRange;
	}

	public void forwardTimeRange(String multirequestToken){
		setToken("forwardTimeRange", multirequestToken);
	}


	public EpgNotificationSettings() {
		super();
	}

	public EpgNotificationSettings(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		enabled = GsonParser.parseBoolean(jsonObject.get("enabled"));
		deviceFamilyIds = GsonParser.parseString(jsonObject.get("deviceFamilyIds"));
		liveAssetIds = GsonParser.parseString(jsonObject.get("liveAssetIds"));
		backwardTimeRange = GsonParser.parseInt(jsonObject.get("backwardTimeRange"));
		forwardTimeRange = GsonParser.parseInt(jsonObject.get("forwardTimeRange"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEpgNotificationSettings");
		kparams.add("enabled", this.enabled);
		kparams.add("deviceFamilyIds", this.deviceFamilyIds);
		kparams.add("liveAssetIds", this.liveAssetIds);
		kparams.add("backwardTimeRange", this.backwardTimeRange);
		kparams.add("forwardTimeRange", this.forwardTimeRange);
		return kparams;
	}

}

