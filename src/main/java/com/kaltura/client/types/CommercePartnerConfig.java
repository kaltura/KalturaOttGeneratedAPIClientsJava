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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * partner configuration for commerce
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CommercePartnerConfig.Tokenizer.class)
public class CommercePartnerConfig extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		RequestBuilder.ListTokenizer<BookmarkEventThreshold.Tokenizer> bookmarkEventThresholds();
		String keepSubscriptionAddOns();
		String programAssetEntitlementPaddingStart();
		String programAssetEntitlementPaddingEnd();
	}

	/**
	 * configuration for bookmark event threshold (when to dispatch the event) in
	  seconds.
	 */
	private List<BookmarkEventThreshold> bookmarkEventThresholds;
	/**
	 * configuration for keep add-ons after subscription deletion
	 */
	private Boolean keepSubscriptionAddOns;
	/**
	 * configuration for asset start entitlement padding e.g. asset start time -
	  padding still relevant for asset
	 */
	private Integer programAssetEntitlementPaddingStart;
	/**
	 * configuration for asset end entitlement padding e.g. asset end time + padding
	  still relevant for asset
	 */
	private Integer programAssetEntitlementPaddingEnd;

	// bookmarkEventThresholds:
	public List<BookmarkEventThreshold> getBookmarkEventThresholds(){
		return this.bookmarkEventThresholds;
	}
	public void setBookmarkEventThresholds(List<BookmarkEventThreshold> bookmarkEventThresholds){
		this.bookmarkEventThresholds = bookmarkEventThresholds;
	}

	// keepSubscriptionAddOns:
	public Boolean getKeepSubscriptionAddOns(){
		return this.keepSubscriptionAddOns;
	}
	public void setKeepSubscriptionAddOns(Boolean keepSubscriptionAddOns){
		this.keepSubscriptionAddOns = keepSubscriptionAddOns;
	}

	public void keepSubscriptionAddOns(String multirequestToken){
		setToken("keepSubscriptionAddOns", multirequestToken);
	}

	// programAssetEntitlementPaddingStart:
	public Integer getProgramAssetEntitlementPaddingStart(){
		return this.programAssetEntitlementPaddingStart;
	}
	public void setProgramAssetEntitlementPaddingStart(Integer programAssetEntitlementPaddingStart){
		this.programAssetEntitlementPaddingStart = programAssetEntitlementPaddingStart;
	}

	public void programAssetEntitlementPaddingStart(String multirequestToken){
		setToken("programAssetEntitlementPaddingStart", multirequestToken);
	}

	// programAssetEntitlementPaddingEnd:
	public Integer getProgramAssetEntitlementPaddingEnd(){
		return this.programAssetEntitlementPaddingEnd;
	}
	public void setProgramAssetEntitlementPaddingEnd(Integer programAssetEntitlementPaddingEnd){
		this.programAssetEntitlementPaddingEnd = programAssetEntitlementPaddingEnd;
	}

	public void programAssetEntitlementPaddingEnd(String multirequestToken){
		setToken("programAssetEntitlementPaddingEnd", multirequestToken);
	}


	public CommercePartnerConfig() {
		super();
	}

	public CommercePartnerConfig(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		bookmarkEventThresholds = GsonParser.parseArray(jsonObject.getAsJsonArray("bookmarkEventThresholds"), BookmarkEventThreshold.class);
		keepSubscriptionAddOns = GsonParser.parseBoolean(jsonObject.get("keepSubscriptionAddOns"));
		programAssetEntitlementPaddingStart = GsonParser.parseInt(jsonObject.get("programAssetEntitlementPaddingStart"));
		programAssetEntitlementPaddingEnd = GsonParser.parseInt(jsonObject.get("programAssetEntitlementPaddingEnd"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCommercePartnerConfig");
		kparams.add("bookmarkEventThresholds", this.bookmarkEventThresholds);
		kparams.add("keepSubscriptionAddOns", this.keepSubscriptionAddOns);
		kparams.add("programAssetEntitlementPaddingStart", this.programAssetEntitlementPaddingStart);
		kparams.add("programAssetEntitlementPaddingEnd", this.programAssetEntitlementPaddingEnd);
		return kparams;
	}

}

