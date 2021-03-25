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
import com.kaltura.client.types.Duration;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UnifiedBillingCycle.Tokenizer.class)
public class UnifiedBillingCycle extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String name();
		Duration.Tokenizer duration();
		String paymentGatewayId();
	}

	/**
	 * UnifiedBillingCycle name
	 */
	private String name;
	/**
	 * cycle duration
	 */
	private Duration duration;
	/**
	 * Payment Gateway Id
	 */
	private Integer paymentGatewayId;

	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// duration:
	public Duration getDuration(){
		return this.duration;
	}
	public void setDuration(Duration duration){
		this.duration = duration;
	}

	// paymentGatewayId:
	public Integer getPaymentGatewayId(){
		return this.paymentGatewayId;
	}
	public void setPaymentGatewayId(Integer paymentGatewayId){
		this.paymentGatewayId = paymentGatewayId;
	}

	public void paymentGatewayId(String multirequestToken){
		setToken("paymentGatewayId", multirequestToken);
	}


	public UnifiedBillingCycle() {
		super();
	}

	public UnifiedBillingCycle(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		name = GsonParser.parseString(jsonObject.get("name"));
		duration = GsonParser.parseObject(jsonObject.getAsJsonObject("duration"), Duration.class);
		paymentGatewayId = GsonParser.parseInt(jsonObject.get("paymentGatewayId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUnifiedBillingCycle");
		kparams.add("name", this.name);
		kparams.add("duration", this.duration);
		kparams.add("paymentGatewayId", this.paymentGatewayId);
		return kparams;
	}

}

