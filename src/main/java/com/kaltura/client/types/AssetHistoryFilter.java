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
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetHistoryFilter.Tokenizer.class)
public class AssetHistoryFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String typeIn();
		String assetIdIn();
		String statusEqual();
		String daysLessThanOrEqual();
	}

	/**
	 * Comma separated list of asset types to search within.              Possible
	  values: 0 – EPG linear programs entries, any media type ID (according to media
	  type IDs defined dynamically in the system).              If omitted – all
	  types should be included.
	 */
	private String typeIn;
	/**
	 * Comma separated list of asset identifiers.
	 */
	private String assetIdIn;
	/**
	 * Which type of recently watched media to include in the result – those that
	  finished watching, those that are in progress or both.              If omitted
	  or specified filter = all – return all types.              Allowed values:
	  progress – return medias that are in-progress, done – return medias that
	  finished watching.
	 */
	private WatchStatus statusEqual;
	/**
	 * How many days back to return the watched media. If omitted, default to 7 days
	 */
	private Integer daysLessThanOrEqual;

	// typeIn:
	public String getTypeIn(){
		return this.typeIn;
	}
	public void setTypeIn(String typeIn){
		this.typeIn = typeIn;
	}

	public void typeIn(String multirequestToken){
		setToken("typeIn", multirequestToken);
	}

	// assetIdIn:
	public String getAssetIdIn(){
		return this.assetIdIn;
	}
	public void setAssetIdIn(String assetIdIn){
		this.assetIdIn = assetIdIn;
	}

	public void assetIdIn(String multirequestToken){
		setToken("assetIdIn", multirequestToken);
	}

	// statusEqual:
	public WatchStatus getStatusEqual(){
		return this.statusEqual;
	}
	public void setStatusEqual(WatchStatus statusEqual){
		this.statusEqual = statusEqual;
	}

	public void statusEqual(String multirequestToken){
		setToken("statusEqual", multirequestToken);
	}

	// daysLessThanOrEqual:
	public Integer getDaysLessThanOrEqual(){
		return this.daysLessThanOrEqual;
	}
	public void setDaysLessThanOrEqual(Integer daysLessThanOrEqual){
		this.daysLessThanOrEqual = daysLessThanOrEqual;
	}

	public void daysLessThanOrEqual(String multirequestToken){
		setToken("daysLessThanOrEqual", multirequestToken);
	}


	public AssetHistoryFilter() {
		super();
	}

	public AssetHistoryFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		typeIn = GsonParser.parseString(jsonObject.get("typeIn"));
		assetIdIn = GsonParser.parseString(jsonObject.get("assetIdIn"));
		statusEqual = WatchStatus.get(GsonParser.parseString(jsonObject.get("statusEqual")));
		daysLessThanOrEqual = GsonParser.parseInt(jsonObject.get("daysLessThanOrEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetHistoryFilter");
		kparams.add("typeIn", this.typeIn);
		kparams.add("assetIdIn", this.assetIdIn);
		kparams.add("statusEqual", this.statusEqual);
		kparams.add("daysLessThanOrEqual", this.daysLessThanOrEqual);
		return kparams;
	}

}

