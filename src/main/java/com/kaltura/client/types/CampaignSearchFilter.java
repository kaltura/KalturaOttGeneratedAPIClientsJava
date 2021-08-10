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
import com.kaltura.client.enums.ObjectState;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CampaignSearchFilter.Tokenizer.class)
public class CampaignSearchFilter extends CampaignFilter {
	
	public interface Tokenizer extends CampaignFilter.Tokenizer {
		String startDateGreaterThanOrEqual();
		String endDateLessThanOrEqual();
		String stateEqual();
		String hasPromotion();
	}

	/**
	 * start Date Greater Than Or Equal
	 */
	private Long startDateGreaterThanOrEqual;
	/**
	 * end Date Greater Than Or Equal
	 */
	private Long endDateLessThanOrEqual;
	/**
	 * state Equal
	 */
	private ObjectState stateEqual;
	/**
	 * has Promotion
	 */
	private Boolean hasPromotion;

	// startDateGreaterThanOrEqual:
	public Long getStartDateGreaterThanOrEqual(){
		return this.startDateGreaterThanOrEqual;
	}
	public void setStartDateGreaterThanOrEqual(Long startDateGreaterThanOrEqual){
		this.startDateGreaterThanOrEqual = startDateGreaterThanOrEqual;
	}

	public void startDateGreaterThanOrEqual(String multirequestToken){
		setToken("startDateGreaterThanOrEqual", multirequestToken);
	}

	// endDateLessThanOrEqual:
	public Long getEndDateLessThanOrEqual(){
		return this.endDateLessThanOrEqual;
	}
	public void setEndDateLessThanOrEqual(Long endDateLessThanOrEqual){
		this.endDateLessThanOrEqual = endDateLessThanOrEqual;
	}

	public void endDateLessThanOrEqual(String multirequestToken){
		setToken("endDateLessThanOrEqual", multirequestToken);
	}

	// stateEqual:
	public ObjectState getStateEqual(){
		return this.stateEqual;
	}
	public void setStateEqual(ObjectState stateEqual){
		this.stateEqual = stateEqual;
	}

	public void stateEqual(String multirequestToken){
		setToken("stateEqual", multirequestToken);
	}

	// hasPromotion:
	public Boolean getHasPromotion(){
		return this.hasPromotion;
	}
	public void setHasPromotion(Boolean hasPromotion){
		this.hasPromotion = hasPromotion;
	}

	public void hasPromotion(String multirequestToken){
		setToken("hasPromotion", multirequestToken);
	}


	public CampaignSearchFilter() {
		super();
	}

	public CampaignSearchFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		startDateGreaterThanOrEqual = GsonParser.parseLong(jsonObject.get("startDateGreaterThanOrEqual"));
		endDateLessThanOrEqual = GsonParser.parseLong(jsonObject.get("endDateLessThanOrEqual"));
		stateEqual = ObjectState.get(GsonParser.parseString(jsonObject.get("stateEqual")));
		hasPromotion = GsonParser.parseBoolean(jsonObject.get("hasPromotion"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCampaignSearchFilter");
		kparams.add("startDateGreaterThanOrEqual", this.startDateGreaterThanOrEqual);
		kparams.add("endDateLessThanOrEqual", this.endDateLessThanOrEqual);
		kparams.add("stateEqual", this.stateEqual);
		kparams.add("hasPromotion", this.hasPromotion);
		return kparams;
	}

}

