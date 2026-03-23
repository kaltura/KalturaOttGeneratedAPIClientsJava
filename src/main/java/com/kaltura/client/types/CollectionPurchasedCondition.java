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

/**
 * Evaluates whether a user purchased a specific Collection (BoxSet).
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CollectionPurchasedCondition.Tokenizer.class)
public class CollectionPurchasedCondition extends BaseSegmentCondition {
	
	public interface Tokenizer extends BaseSegmentCondition.Tokenizer {
		String collectionIdEquals();
		String days();
	}

	/**
	 * The specific purchased collection product identifier to check.
	 */
	private Long collectionIdEquals;
	/**
	 * The number of days to look back for the purchase.
	 */
	private Integer days;

	// collectionIdEquals:
	public Long getCollectionIdEquals(){
		return this.collectionIdEquals;
	}
	public void setCollectionIdEquals(Long collectionIdEquals){
		this.collectionIdEquals = collectionIdEquals;
	}

	public void collectionIdEquals(String multirequestToken){
		setToken("collectionIdEquals", multirequestToken);
	}

	// days:
	public Integer getDays(){
		return this.days;
	}
	public void setDays(Integer days){
		this.days = days;
	}

	public void days(String multirequestToken){
		setToken("days", multirequestToken);
	}


	public CollectionPurchasedCondition() {
		super();
	}

	public CollectionPurchasedCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		collectionIdEquals = GsonParser.parseLong(jsonObject.get("collectionIdEquals"));
		days = GsonParser.parseInt(jsonObject.get("days"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCollectionPurchasedCondition");
		kparams.add("collectionIdEquals", this.collectionIdEquals);
		kparams.add("days", this.days);
		return kparams;
	}

}

