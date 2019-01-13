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
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Business module rule filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BusinessModuleRuleFilter.Tokenizer.class)
public class BusinessModuleRuleFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String businessModuleTypeApplied();
		String businessModuleIdApplied();
		String segmentIdsApplied();
	}

	/**
	 * Business module type the rules applied on
	 */
	private TransactionType businessModuleTypeApplied;
	/**
	 * Business module ID the rules applied on
	 */
	private Long businessModuleIdApplied;
	/**
	 * Comma separated segment IDs the rules applied on
	 */
	private String segmentIdsApplied;

	// businessModuleTypeApplied:
	public TransactionType getBusinessModuleTypeApplied(){
		return this.businessModuleTypeApplied;
	}
	public void setBusinessModuleTypeApplied(TransactionType businessModuleTypeApplied){
		this.businessModuleTypeApplied = businessModuleTypeApplied;
	}

	public void businessModuleTypeApplied(String multirequestToken){
		setToken("businessModuleTypeApplied", multirequestToken);
	}

	// businessModuleIdApplied:
	public Long getBusinessModuleIdApplied(){
		return this.businessModuleIdApplied;
	}
	public void setBusinessModuleIdApplied(Long businessModuleIdApplied){
		this.businessModuleIdApplied = businessModuleIdApplied;
	}

	public void businessModuleIdApplied(String multirequestToken){
		setToken("businessModuleIdApplied", multirequestToken);
	}

	// segmentIdsApplied:
	public String getSegmentIdsApplied(){
		return this.segmentIdsApplied;
	}
	public void setSegmentIdsApplied(String segmentIdsApplied){
		this.segmentIdsApplied = segmentIdsApplied;
	}

	public void segmentIdsApplied(String multirequestToken){
		setToken("segmentIdsApplied", multirequestToken);
	}


	public BusinessModuleRuleFilter() {
		super();
	}

	public BusinessModuleRuleFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		businessModuleTypeApplied = TransactionType.get(GsonParser.parseString(jsonObject.get("businessModuleTypeApplied")));
		businessModuleIdApplied = GsonParser.parseLong(jsonObject.get("businessModuleIdApplied"));
		segmentIdsApplied = GsonParser.parseString(jsonObject.get("segmentIdsApplied"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBusinessModuleRuleFilter");
		kparams.add("businessModuleTypeApplied", this.businessModuleTypeApplied);
		kparams.add("businessModuleIdApplied", this.businessModuleIdApplied);
		kparams.add("segmentIdsApplied", this.segmentIdsApplied);
		return kparams;
	}

}

