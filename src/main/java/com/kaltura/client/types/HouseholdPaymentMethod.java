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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdPaymentMethod.Tokenizer.class)
public class HouseholdPaymentMethod extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String externalId();
		String paymentGatewayId();
		String details();
		String isDefault();
		String paymentMethodProfileId();
	}

	/**
	 * Household payment method identifier (internal)
	 */
	private Integer id;
	/**
	 * External identifier for the household payment method
	 */
	private String externalId;
	/**
	 * Payment-gateway identifier
	 */
	private Integer paymentGatewayId;
	/**
	 * Description of the payment method details
	 */
	private String details;
	/**
	 * indicates whether the payment method is set as default for the household
	 */
	private Boolean isDefault;
	/**
	 * Payment method profile identifier
	 */
	private Integer paymentMethodProfileId;

	// id:
	public Integer getId(){
		return this.id;
	}
	// externalId:
	public String getExternalId(){
		return this.externalId;
	}
	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public void externalId(String multirequestToken){
		setToken("externalId", multirequestToken);
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

	// details:
	public String getDetails(){
		return this.details;
	}
	public void setDetails(String details){
		this.details = details;
	}

	public void details(String multirequestToken){
		setToken("details", multirequestToken);
	}

	// isDefault:
	public Boolean getIsDefault(){
		return this.isDefault;
	}
	// paymentMethodProfileId:
	public Integer getPaymentMethodProfileId(){
		return this.paymentMethodProfileId;
	}
	public void setPaymentMethodProfileId(Integer paymentMethodProfileId){
		this.paymentMethodProfileId = paymentMethodProfileId;
	}

	public void paymentMethodProfileId(String multirequestToken){
		setToken("paymentMethodProfileId", multirequestToken);
	}


	public HouseholdPaymentMethod() {
		super();
	}

	public HouseholdPaymentMethod(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		paymentGatewayId = GsonParser.parseInt(jsonObject.get("paymentGatewayId"));
		details = GsonParser.parseString(jsonObject.get("details"));
		isDefault = GsonParser.parseBoolean(jsonObject.get("isDefault"));
		paymentMethodProfileId = GsonParser.parseInt(jsonObject.get("paymentMethodProfileId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdPaymentMethod");
		kparams.add("externalId", this.externalId);
		kparams.add("paymentGatewayId", this.paymentGatewayId);
		kparams.add("details", this.details);
		kparams.add("paymentMethodProfileId", this.paymentMethodProfileId);
		return kparams;
	}

}

