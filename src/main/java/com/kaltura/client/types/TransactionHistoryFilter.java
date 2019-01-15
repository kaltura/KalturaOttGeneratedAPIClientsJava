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
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Transactions filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TransactionHistoryFilter.Tokenizer.class)
public class TransactionHistoryFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String entityReferenceEqual();
		String startDateGreaterThanOrEqual();
		String endDateLessThanOrEqual();
	}

	/**
	 * Reference type to filter by
	 */
	private EntityReferenceBy entityReferenceEqual;
	/**
	 * Filter transactions later than specific date
	 */
	private Integer startDateGreaterThanOrEqual;
	/**
	 * Filter transactions earlier than specific date
	 */
	private Integer endDateLessThanOrEqual;

	// entityReferenceEqual:
	public EntityReferenceBy getEntityReferenceEqual(){
		return this.entityReferenceEqual;
	}
	public void setEntityReferenceEqual(EntityReferenceBy entityReferenceEqual){
		this.entityReferenceEqual = entityReferenceEqual;
	}

	public void entityReferenceEqual(String multirequestToken){
		setToken("entityReferenceEqual", multirequestToken);
	}

	// startDateGreaterThanOrEqual:
	public Integer getStartDateGreaterThanOrEqual(){
		return this.startDateGreaterThanOrEqual;
	}
	public void setStartDateGreaterThanOrEqual(Integer startDateGreaterThanOrEqual){
		this.startDateGreaterThanOrEqual = startDateGreaterThanOrEqual;
	}

	public void startDateGreaterThanOrEqual(String multirequestToken){
		setToken("startDateGreaterThanOrEqual", multirequestToken);
	}

	// endDateLessThanOrEqual:
	public Integer getEndDateLessThanOrEqual(){
		return this.endDateLessThanOrEqual;
	}
	public void setEndDateLessThanOrEqual(Integer endDateLessThanOrEqual){
		this.endDateLessThanOrEqual = endDateLessThanOrEqual;
	}

	public void endDateLessThanOrEqual(String multirequestToken){
		setToken("endDateLessThanOrEqual", multirequestToken);
	}


	public TransactionHistoryFilter() {
		super();
	}

	public TransactionHistoryFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		entityReferenceEqual = EntityReferenceBy.get(GsonParser.parseString(jsonObject.get("entityReferenceEqual")));
		startDateGreaterThanOrEqual = GsonParser.parseInt(jsonObject.get("startDateGreaterThanOrEqual"));
		endDateLessThanOrEqual = GsonParser.parseInt(jsonObject.get("endDateLessThanOrEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTransactionHistoryFilter");
		kparams.add("entityReferenceEqual", this.entityReferenceEqual);
		kparams.add("startDateGreaterThanOrEqual", this.startDateGreaterThanOrEqual);
		kparams.add("endDateLessThanOrEqual", this.endDateLessThanOrEqual);
		return kparams;
	}

}

