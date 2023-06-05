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

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(LineupRegionalChannelFilter.Tokenizer.class)
public class LineupRegionalChannelFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String regionIdEqual();
		String parentRegionIncluded();
		String kSql();
		String lcnGreaterThanOrEqual();
		String lcnLessThanOrEqual();
	}

	/**
	 * Region ID filter
	 */
	private Long regionIdEqual;
	/**
	 * Should include lineup from parent region into response
	 */
	private Boolean parentRegionIncluded;
	/**
	 * A valid KSQL statement - Only linear channels that satisfies the KSQL statement
	  will be included in the results
	 */
	private String kSql;
	/**
	 * Filter only LCNs that greater or equals to the provided number
	 */
	private Integer lcnGreaterThanOrEqual;
	/**
	 * Filter only LCNs that less or equals to the provided number
	 */
	private Integer lcnLessThanOrEqual;

	// regionIdEqual:
	public Long getRegionIdEqual(){
		return this.regionIdEqual;
	}
	public void setRegionIdEqual(Long regionIdEqual){
		this.regionIdEqual = regionIdEqual;
	}

	public void regionIdEqual(String multirequestToken){
		setToken("regionIdEqual", multirequestToken);
	}

	// parentRegionIncluded:
	public Boolean getParentRegionIncluded(){
		return this.parentRegionIncluded;
	}
	public void setParentRegionIncluded(Boolean parentRegionIncluded){
		this.parentRegionIncluded = parentRegionIncluded;
	}

	public void parentRegionIncluded(String multirequestToken){
		setToken("parentRegionIncluded", multirequestToken);
	}

	// kSql:
	public String getKSql(){
		return this.kSql;
	}
	public void setKSql(String kSql){
		this.kSql = kSql;
	}

	public void kSql(String multirequestToken){
		setToken("kSql", multirequestToken);
	}

	// lcnGreaterThanOrEqual:
	public Integer getLcnGreaterThanOrEqual(){
		return this.lcnGreaterThanOrEqual;
	}
	public void setLcnGreaterThanOrEqual(Integer lcnGreaterThanOrEqual){
		this.lcnGreaterThanOrEqual = lcnGreaterThanOrEqual;
	}

	public void lcnGreaterThanOrEqual(String multirequestToken){
		setToken("lcnGreaterThanOrEqual", multirequestToken);
	}

	// lcnLessThanOrEqual:
	public Integer getLcnLessThanOrEqual(){
		return this.lcnLessThanOrEqual;
	}
	public void setLcnLessThanOrEqual(Integer lcnLessThanOrEqual){
		this.lcnLessThanOrEqual = lcnLessThanOrEqual;
	}

	public void lcnLessThanOrEqual(String multirequestToken){
		setToken("lcnLessThanOrEqual", multirequestToken);
	}


	public LineupRegionalChannelFilter() {
		super();
	}

	public LineupRegionalChannelFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		regionIdEqual = GsonParser.parseLong(jsonObject.get("regionIdEqual"));
		parentRegionIncluded = GsonParser.parseBoolean(jsonObject.get("parentRegionIncluded"));
		kSql = GsonParser.parseString(jsonObject.get("kSql"));
		lcnGreaterThanOrEqual = GsonParser.parseInt(jsonObject.get("lcnGreaterThanOrEqual"));
		lcnLessThanOrEqual = GsonParser.parseInt(jsonObject.get("lcnLessThanOrEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLineupRegionalChannelFilter");
		kparams.add("regionIdEqual", this.regionIdEqual);
		kparams.add("parentRegionIncluded", this.parentRegionIncluded);
		kparams.add("kSql", this.kSql);
		kparams.add("lcnGreaterThanOrEqual", this.lcnGreaterThanOrEqual);
		kparams.add("lcnLessThanOrEqual", this.lcnLessThanOrEqual);
		return kparams;
	}

}

