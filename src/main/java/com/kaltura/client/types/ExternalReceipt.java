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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ExternalReceipt.Tokenizer.class)
public class ExternalReceipt extends PurchaseBase {
	
	public interface Tokenizer extends PurchaseBase.Tokenizer {
		String receiptId();
		String paymentGatewayName();
	}

	/**
	 * A unique identifier that was provided by the In-App billing service to validate
	  the purchase
	 */
	private String receiptId;
	/**
	 * The payment gateway name for the In-App billing service to be used. Possible
	  values: Google/Apple
	 */
	private String paymentGatewayName;

	// receiptId:
	public String getReceiptId(){
		return this.receiptId;
	}
	public void setReceiptId(String receiptId){
		this.receiptId = receiptId;
	}

	public void receiptId(String multirequestToken){
		setToken("receiptId", multirequestToken);
	}

	// paymentGatewayName:
	public String getPaymentGatewayName(){
		return this.paymentGatewayName;
	}
	public void setPaymentGatewayName(String paymentGatewayName){
		this.paymentGatewayName = paymentGatewayName;
	}

	public void paymentGatewayName(String multirequestToken){
		setToken("paymentGatewayName", multirequestToken);
	}


	public ExternalReceipt() {
		super();
	}

	public ExternalReceipt(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		receiptId = GsonParser.parseString(jsonObject.get("receiptId"));
		paymentGatewayName = GsonParser.parseString(jsonObject.get("paymentGatewayName"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaExternalReceipt");
		kparams.add("receiptId", this.receiptId);
		kparams.add("paymentGatewayName", this.paymentGatewayName);
		return kparams;
	}

}

