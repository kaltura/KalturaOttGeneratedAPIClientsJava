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
import com.kaltura.client.enums.ConcurrencyLimitationType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Asset Condition
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ConcurrencyCondition.Tokenizer.class)
public class ConcurrencyCondition extends AssetCondition {
	
	public interface Tokenizer extends AssetCondition.Tokenizer {
		String limit();
		String concurrencyLimitationType();
	}

	/**
	 * Concurrency limitation
	 */
	private Integer limit;
	/**
	 * Concurrency limitation type
	 */
	private ConcurrencyLimitationType concurrencyLimitationType;

	// limit:
	public Integer getLimit(){
		return this.limit;
	}
	public void setLimit(Integer limit){
		this.limit = limit;
	}

	public void limit(String multirequestToken){
		setToken("limit", multirequestToken);
	}

	// concurrencyLimitationType:
	public ConcurrencyLimitationType getConcurrencyLimitationType(){
		return this.concurrencyLimitationType;
	}
	public void setConcurrencyLimitationType(ConcurrencyLimitationType concurrencyLimitationType){
		this.concurrencyLimitationType = concurrencyLimitationType;
	}

	public void concurrencyLimitationType(String multirequestToken){
		setToken("concurrencyLimitationType", multirequestToken);
	}


	public ConcurrencyCondition() {
		super();
	}

	public ConcurrencyCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		limit = GsonParser.parseInt(jsonObject.get("limit"));
		concurrencyLimitationType = ConcurrencyLimitationType.get(GsonParser.parseString(jsonObject.get("concurrencyLimitationType")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaConcurrencyCondition");
		kparams.add("limit", this.limit);
		kparams.add("concurrencyLimitationType", this.concurrencyLimitationType);
		return kparams;
	}

}

