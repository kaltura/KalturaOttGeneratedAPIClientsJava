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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.types.HouseholdPaymentGateway;
import com.kaltura.client.types.KeyValue;
import com.kaltura.client.types.PaymentGatewayConfiguration;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdPaymentGatewayService {
	
	public static class DisableHouseholdPaymentGatewayBuilder extends RequestBuilder<Boolean, String, DisableHouseholdPaymentGatewayBuilder> {
		
		public DisableHouseholdPaymentGatewayBuilder(int paymentGatewayId) {
			super(Boolean.class, "householdpaymentgateway", "disable");
			params.add("paymentGatewayId", paymentGatewayId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
	}

	/**  Disable payment-gateway on the household  */
    public static DisableHouseholdPaymentGatewayBuilder disable(int paymentGatewayId)  {
		return new DisableHouseholdPaymentGatewayBuilder(paymentGatewayId);
	}
	
	public static class EnableHouseholdPaymentGatewayBuilder extends RequestBuilder<Boolean, String, EnableHouseholdPaymentGatewayBuilder> {
		
		public EnableHouseholdPaymentGatewayBuilder(int paymentGatewayId) {
			super(Boolean.class, "householdpaymentgateway", "enable");
			params.add("paymentGatewayId", paymentGatewayId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
	}

	/**  Enable a payment-gateway provider for the household.  */
    public static EnableHouseholdPaymentGatewayBuilder enable(int paymentGatewayId)  {
		return new EnableHouseholdPaymentGatewayBuilder(paymentGatewayId);
	}
	
	public static class GetChargeIDHouseholdPaymentGatewayBuilder extends RequestBuilder<String, String, GetChargeIDHouseholdPaymentGatewayBuilder> {
		
		public GetChargeIDHouseholdPaymentGatewayBuilder(String paymentGatewayExternalId) {
			super(String.class, "householdpaymentgateway", "getChargeID");
			params.add("paymentGatewayExternalId", paymentGatewayExternalId);
		}
		
		public void paymentGatewayExternalId(String multirequestToken) {
			params.add("paymentGatewayExternalId", multirequestToken);
		}
	}

	/**  Get a household’s billing account identifier (charge ID) for a given payment
	  gateway  */
    public static GetChargeIDHouseholdPaymentGatewayBuilder getChargeID(String paymentGatewayExternalId)  {
		return new GetChargeIDHouseholdPaymentGatewayBuilder(paymentGatewayExternalId);
	}
	
	public static class InvokeHouseholdPaymentGatewayBuilder extends RequestBuilder<PaymentGatewayConfiguration, PaymentGatewayConfiguration.Tokenizer, InvokeHouseholdPaymentGatewayBuilder> {
		
		public InvokeHouseholdPaymentGatewayBuilder(int paymentGatewayId, String intent, List<KeyValue> extraParameters) {
			super(PaymentGatewayConfiguration.class, "householdpaymentgateway", "invoke");
			params.add("paymentGatewayId", paymentGatewayId);
			params.add("intent", intent);
			params.add("extraParameters", extraParameters);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
		
		public void intent(String multirequestToken) {
			params.add("intent", multirequestToken);
		}
	}

	/**  Gets the Payment Gateway Configuration for the payment gateway identifier given  */
    public static InvokeHouseholdPaymentGatewayBuilder invoke(int paymentGatewayId, String intent, List<KeyValue> extraParameters)  {
		return new InvokeHouseholdPaymentGatewayBuilder(paymentGatewayId, intent, extraParameters);
	}
	
	public static class ListHouseholdPaymentGatewayBuilder extends ListResponseRequestBuilder<HouseholdPaymentGateway, HouseholdPaymentGateway.Tokenizer, ListHouseholdPaymentGatewayBuilder> {
		
		public ListHouseholdPaymentGatewayBuilder() {
			super(HouseholdPaymentGateway.class, "householdpaymentgateway", "list");
		}
	}

	/**  Get a list of all configured Payment Gateways providers available for the
	  account. For each payment is provided with the household associated payment
	  methods.  */
    public static ListHouseholdPaymentGatewayBuilder list()  {
		return new ListHouseholdPaymentGatewayBuilder();
	}
	
	public static class SetChargeIDHouseholdPaymentGatewayBuilder extends RequestBuilder<Boolean, String, SetChargeIDHouseholdPaymentGatewayBuilder> {
		
		public SetChargeIDHouseholdPaymentGatewayBuilder(String paymentGatewayExternalId, String chargeId) {
			super(Boolean.class, "householdpaymentgateway", "setChargeID");
			params.add("paymentGatewayExternalId", paymentGatewayExternalId);
			params.add("chargeId", chargeId);
		}
		
		public void paymentGatewayExternalId(String multirequestToken) {
			params.add("paymentGatewayExternalId", multirequestToken);
		}
		
		public void chargeId(String multirequestToken) {
			params.add("chargeId", multirequestToken);
		}
	}

	/**  Set user billing account identifier (charge ID), for a specific household and a
	  specific payment gateway  */
    public static SetChargeIDHouseholdPaymentGatewayBuilder setChargeID(String paymentGatewayExternalId, String chargeId)  {
		return new SetChargeIDHouseholdPaymentGatewayBuilder(paymentGatewayExternalId, chargeId);
	}
}
