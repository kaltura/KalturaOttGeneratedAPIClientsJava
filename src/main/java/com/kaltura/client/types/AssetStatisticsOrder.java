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
import com.kaltura.client.enums.AssetOrderByStatistics;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetStatisticsOrder.Tokenizer.class)
public class AssetStatisticsOrder extends BaseAssetOrder {
	
	public interface Tokenizer extends BaseAssetOrder.Tokenizer {
		String trendingDaysEqual();
		String orderBy();
	}

	/**
	 * Trending Days Equal
	 */
	private Integer trendingDaysEqual;
	/**
	 * order by meta asc/desc
	 */
	private AssetOrderByStatistics orderBy;

	// trendingDaysEqual:
	public Integer getTrendingDaysEqual(){
		return this.trendingDaysEqual;
	}
	public void setTrendingDaysEqual(Integer trendingDaysEqual){
		this.trendingDaysEqual = trendingDaysEqual;
	}

	public void trendingDaysEqual(String multirequestToken){
		setToken("trendingDaysEqual", multirequestToken);
	}

	// orderBy:
	public AssetOrderByStatistics getOrderBy(){
		return this.orderBy;
	}
	public void setOrderBy(AssetOrderByStatistics orderBy){
		this.orderBy = orderBy;
	}

	public void orderBy(String multirequestToken){
		setToken("orderBy", multirequestToken);
	}


	public AssetStatisticsOrder() {
		super();
	}

	public AssetStatisticsOrder(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		trendingDaysEqual = GsonParser.parseInt(jsonObject.get("trendingDaysEqual"));
		orderBy = AssetOrderByStatistics.get(GsonParser.parseString(jsonObject.get("orderBy")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetStatisticsOrder");
		kparams.add("trendingDaysEqual", this.trendingDaysEqual);
		kparams.add("orderBy", this.orderBy);
		return kparams;
	}

}

