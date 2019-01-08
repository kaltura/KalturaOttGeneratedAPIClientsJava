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
package com.kaltura.client.services;

import com.kaltura.client.types.KeyValue;
import com.kaltura.client.types.PaymentGatewayConfiguration;
import com.kaltura.client.types.PaymentGatewayProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PaymentGatewayProfileService {
	
	public static class AddPaymentGatewayProfileBuilder extends RequestBuilder<PaymentGatewayProfile, PaymentGatewayProfile.Tokenizer, AddPaymentGatewayProfileBuilder> {
		
		public AddPaymentGatewayProfileBuilder(PaymentGatewayProfile paymentGateway) {
			super(PaymentGatewayProfile.class, "paymentgatewayprofile", "add");
			params.add("paymentGateway", paymentGateway);
		}
	}

	/**
	 * Insert new payment gateway for partner
	 * 
	 * @param paymentGateway Payment Gateway Object
	 */
    public static AddPaymentGatewayProfileBuilder add(PaymentGatewayProfile paymentGateway)  {
		return new AddPaymentGatewayProfileBuilder(paymentGateway);
	}
	
	public static class DeletePaymentGatewayProfileBuilder extends RequestBuilder<Boolean, String, DeletePaymentGatewayProfileBuilder> {
		
		public DeletePaymentGatewayProfileBuilder(int paymentGatewayId) {
			super(Boolean.class, "paymentgatewayprofile", "delete");
			params.add("paymentGatewayId", paymentGatewayId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
	}

	/**
	 * Delete payment gateway by payment gateway id
	 * 
	 * @param paymentGatewayId Payment Gateway Identifier
	 */
    public static DeletePaymentGatewayProfileBuilder delete(int paymentGatewayId)  {
		return new DeletePaymentGatewayProfileBuilder(paymentGatewayId);
	}
	
	public static class GenerateSharedSecretPaymentGatewayProfileBuilder extends RequestBuilder<PaymentGatewayProfile, PaymentGatewayProfile.Tokenizer, GenerateSharedSecretPaymentGatewayProfileBuilder> {
		
		public GenerateSharedSecretPaymentGatewayProfileBuilder(int paymentGatewayId) {
			super(PaymentGatewayProfile.class, "paymentgatewayprofile", "generateSharedSecret");
			params.add("paymentGatewayId", paymentGatewayId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
	}

	/**
	 * Generate payment gateway shared secret
	 * 
	 * @param paymentGatewayId Payment gateway identifier
	 */
    public static GenerateSharedSecretPaymentGatewayProfileBuilder generateSharedSecret(int paymentGatewayId)  {
		return new GenerateSharedSecretPaymentGatewayProfileBuilder(paymentGatewayId);
	}
	
	public static class GetConfigurationPaymentGatewayProfileBuilder extends RequestBuilder<PaymentGatewayConfiguration, PaymentGatewayConfiguration.Tokenizer, GetConfigurationPaymentGatewayProfileBuilder> {
		
		public GetConfigurationPaymentGatewayProfileBuilder(String alias, String intent, List<KeyValue> extraParameters) {
			super(PaymentGatewayConfiguration.class, "paymentgatewayprofile", "getConfiguration");
			params.add("alias", alias);
			params.add("intent", intent);
			params.add("extraParameters", extraParameters);
		}
		
		public void alias(String multirequestToken) {
			params.add("alias", multirequestToken);
		}
		
		public void intent(String multirequestToken) {
			params.add("intent", multirequestToken);
		}
	}

	/**
	 * Gets the Payment Gateway Configuration for the payment gateway identifier given
	 * 
	 * @param alias The payemnt gateway for which to return the registration URL/s for the
	 * household. If omitted – return the regisration URL for the household for the
	 * default payment gateway
	 * @param intent Represent the client’s intent for working with the payment gateway. Intent
	 * options to be coordinated with the applicable payment gateway adapter.
	 * @param extraParameters Additional parameters to send to the payment gateway adapter.
	 */
    public static GetConfigurationPaymentGatewayProfileBuilder getConfiguration(String alias, String intent, List<KeyValue> extraParameters)  {
		return new GetConfigurationPaymentGatewayProfileBuilder(alias, intent, extraParameters);
	}
	
	public static class ListPaymentGatewayProfileBuilder extends ListResponseRequestBuilder<PaymentGatewayProfile, PaymentGatewayProfile.Tokenizer, ListPaymentGatewayProfileBuilder> {
		
		public ListPaymentGatewayProfileBuilder() {
			super(PaymentGatewayProfile.class, "paymentgatewayprofile", "list");
		}
	}

	/**
	 * Returns all payment gateways for partner : id + name
	 */
    public static ListPaymentGatewayProfileBuilder list()  {
		return new ListPaymentGatewayProfileBuilder();
	}
	
	public static class UpdatePaymentGatewayProfileBuilder extends RequestBuilder<PaymentGatewayProfile, PaymentGatewayProfile.Tokenizer, UpdatePaymentGatewayProfileBuilder> {
		
		public UpdatePaymentGatewayProfileBuilder(int paymentGatewayId, PaymentGatewayProfile paymentGateway) {
			super(PaymentGatewayProfile.class, "paymentgatewayprofile", "update");
			params.add("paymentGatewayId", paymentGatewayId);
			params.add("paymentGateway", paymentGateway);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
	}

	/**
	 * Update payment gateway details
	 * 
	 * @param paymentGatewayId Payment Gateway Identifier
	 * @param paymentGateway Payment Gateway Object
	 */
    public static UpdatePaymentGatewayProfileBuilder update(int paymentGatewayId, PaymentGatewayProfile paymentGateway)  {
		return new UpdatePaymentGatewayProfileBuilder(paymentGatewayId, paymentGateway);
	}
}
