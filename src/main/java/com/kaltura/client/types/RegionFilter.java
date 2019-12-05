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
// Copyright (C) 2006-2019  Kaltura Inc.
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
@MultiRequestBuilder.Tokenizer(RegionFilter.Tokenizer.class)
public class RegionFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String externalIdIn();
		String idIn();
		String parentIdEqual();
		String liveAssetIdEqual();
	}

	/**
	 * List of comma separated regions external IDs
	 */
	private String externalIdIn;
	/**
	 * List of comma separated regions Ids
	 */
	private String idIn;
	/**
	 * Region parent ID to filter by
	 */
	private Integer parentIdEqual;
	/**
	 * Region parent ID to filter by
	 */
	private Integer liveAssetIdEqual;

	// externalIdIn:
	public String getExternalIdIn(){
		return this.externalIdIn;
	}
	public void setExternalIdIn(String externalIdIn){
		this.externalIdIn = externalIdIn;
	}

	public void externalIdIn(String multirequestToken){
		setToken("externalIdIn", multirequestToken);
	}

	// idIn:
	public String getIdIn(){
		return this.idIn;
	}
	public void setIdIn(String idIn){
		this.idIn = idIn;
	}

	public void idIn(String multirequestToken){
		setToken("idIn", multirequestToken);
	}

	// parentIdEqual:
	public Integer getParentIdEqual(){
		return this.parentIdEqual;
	}
	public void setParentIdEqual(Integer parentIdEqual){
		this.parentIdEqual = parentIdEqual;
	}

	public void parentIdEqual(String multirequestToken){
		setToken("parentIdEqual", multirequestToken);
	}

	// liveAssetIdEqual:
	public Integer getLiveAssetIdEqual(){
		return this.liveAssetIdEqual;
	}
	public void setLiveAssetIdEqual(Integer liveAssetIdEqual){
		this.liveAssetIdEqual = liveAssetIdEqual;
	}

	public void liveAssetIdEqual(String multirequestToken){
		setToken("liveAssetIdEqual", multirequestToken);
	}


	public RegionFilter() {
		super();
	}

	public RegionFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		externalIdIn = GsonParser.parseString(jsonObject.get("externalIdIn"));
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		parentIdEqual = GsonParser.parseInt(jsonObject.get("parentIdEqual"));
		liveAssetIdEqual = GsonParser.parseInt(jsonObject.get("liveAssetIdEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRegionFilter");
		kparams.add("externalIdIn", this.externalIdIn);
		kparams.add("idIn", this.idIn);
		kparams.add("parentIdEqual", this.parentIdEqual);
		kparams.add("liveAssetIdEqual", this.liveAssetIdEqual);
		return kparams;
	}

}

