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
@MultiRequestBuilder.Tokenizer(LiveToVodPartnerConfiguration.Tokenizer.class)
public class LiveToVodPartnerConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String isL2vEnabled();
		String retentionPeriodDays();
		String metadataClassifier();
	}

	/**
	 * Enable/disable the feature globally. If disabled, then all linear assets are not
	  enabled.
	 */
	private Boolean isL2vEnabled;
	/**
	 * Number of days the L2V asset is retained in the system.
	 */
	private Integer retentionPeriodDays;
	/**
	 * The name (label) of the metadata field marking the program asset to be
	  duplicated as a L2V asset.
	 */
	private String metadataClassifier;

	// isL2vEnabled:
	public Boolean getIsL2vEnabled(){
		return this.isL2vEnabled;
	}
	public void setIsL2vEnabled(Boolean isL2vEnabled){
		this.isL2vEnabled = isL2vEnabled;
	}

	public void isL2vEnabled(String multirequestToken){
		setToken("isL2vEnabled", multirequestToken);
	}

	// retentionPeriodDays:
	public Integer getRetentionPeriodDays(){
		return this.retentionPeriodDays;
	}
	public void setRetentionPeriodDays(Integer retentionPeriodDays){
		this.retentionPeriodDays = retentionPeriodDays;
	}

	public void retentionPeriodDays(String multirequestToken){
		setToken("retentionPeriodDays", multirequestToken);
	}

	// metadataClassifier:
	public String getMetadataClassifier(){
		return this.metadataClassifier;
	}
	public void setMetadataClassifier(String metadataClassifier){
		this.metadataClassifier = metadataClassifier;
	}

	public void metadataClassifier(String multirequestToken){
		setToken("metadataClassifier", multirequestToken);
	}


	public LiveToVodPartnerConfiguration() {
		super();
	}

	public LiveToVodPartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		isL2vEnabled = GsonParser.parseBoolean(jsonObject.get("isL2vEnabled"));
		retentionPeriodDays = GsonParser.parseInt(jsonObject.get("retentionPeriodDays"));
		metadataClassifier = GsonParser.parseString(jsonObject.get("metadataClassifier"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLiveToVodPartnerConfiguration");
		kparams.add("isL2vEnabled", this.isL2vEnabled);
		kparams.add("retentionPeriodDays", this.retentionPeriodDays);
		kparams.add("metadataClassifier", this.metadataClassifier);
		return kparams;
	}

}

