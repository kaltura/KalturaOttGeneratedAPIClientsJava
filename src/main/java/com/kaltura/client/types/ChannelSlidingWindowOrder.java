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
import com.kaltura.client.enums.ChannelSlidingWindowOrderByType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ChannelSlidingWindowOrder.Tokenizer.class)
public class ChannelSlidingWindowOrder extends BaseChannelOrder {
	
	public interface Tokenizer extends BaseChannelOrder.Tokenizer {
		String period();
		String orderBy();
	}

	/**
	 * Sliding window period in minutes
	 */
	private Integer period;
	/**
	 * Order By
	 */
	private ChannelSlidingWindowOrderByType orderBy;

	// period:
	public Integer getPeriod(){
		return this.period;
	}
	public void setPeriod(Integer period){
		this.period = period;
	}

	public void period(String multirequestToken){
		setToken("period", multirequestToken);
	}

	// orderBy:
	public ChannelSlidingWindowOrderByType getOrderBy(){
		return this.orderBy;
	}
	public void setOrderBy(ChannelSlidingWindowOrderByType orderBy){
		this.orderBy = orderBy;
	}

	public void orderBy(String multirequestToken){
		setToken("orderBy", multirequestToken);
	}


	public ChannelSlidingWindowOrder() {
		super();
	}

	public ChannelSlidingWindowOrder(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		period = GsonParser.parseInt(jsonObject.get("period"));
		orderBy = ChannelSlidingWindowOrderByType.get(GsonParser.parseString(jsonObject.get("orderBy")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaChannelSlidingWindowOrder");
		kparams.add("period", this.period);
		kparams.add("orderBy", this.orderBy);
		return kparams;
	}

}

