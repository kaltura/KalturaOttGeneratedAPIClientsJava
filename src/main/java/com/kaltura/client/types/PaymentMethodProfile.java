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
@MultiRequestBuilder.Tokenizer(PaymentMethodProfile.Tokenizer.class)
public class PaymentMethodProfile extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String paymentGatewayId();
		String name();
		String allowMultiInstance();
	}

	/**
	 * Payment method identifier (internal)
	 */
	private Integer id;
	/**
	 * Payment gateway identifier (internal)
	 */
	private Integer paymentGatewayId;
	/**
	 * Payment method name
	 */
	private String name;
	/**
	 * Indicates whether the payment method allow multiple instances
	 */
	private Boolean allowMultiInstance;

	// id:
	public Integer getId(){
		return this.id;
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

	// allowMultiInstance:
	public Boolean getAllowMultiInstance(){
		return this.allowMultiInstance;
	}
	public void setAllowMultiInstance(Boolean allowMultiInstance){
		this.allowMultiInstance = allowMultiInstance;
	}

	public void allowMultiInstance(String multirequestToken){
		setToken("allowMultiInstance", multirequestToken);
	}


	public PaymentMethodProfile() {
		super();
	}

	public PaymentMethodProfile(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		paymentGatewayId = GsonParser.parseInt(jsonObject.get("paymentGatewayId"));
		name = GsonParser.parseString(jsonObject.get("name"));
		allowMultiInstance = GsonParser.parseBoolean(jsonObject.get("allowMultiInstance"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPaymentMethodProfile");
		kparams.add("paymentGatewayId", this.paymentGatewayId);
		kparams.add("name", this.name);
		kparams.add("allowMultiInstance", this.allowMultiInstance);
		return kparams;
	}

}

