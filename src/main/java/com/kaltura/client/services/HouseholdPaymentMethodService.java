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

import com.kaltura.client.types.HouseholdPaymentMethod;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdPaymentMethodService {
	
	public static class AddHouseholdPaymentMethodBuilder extends RequestBuilder<HouseholdPaymentMethod, HouseholdPaymentMethod.Tokenizer, AddHouseholdPaymentMethodBuilder> {
		
		public AddHouseholdPaymentMethodBuilder(HouseholdPaymentMethod householdPaymentMethod) {
			super(HouseholdPaymentMethod.class, "householdpaymentmethod", "add");
			params.add("householdPaymentMethod", householdPaymentMethod);
		}
	}

	/**
	 * Add a new payment method for household
	 * 
	 * @param householdPaymentMethod Household payment method
	 */
    public static AddHouseholdPaymentMethodBuilder add(HouseholdPaymentMethod householdPaymentMethod)  {
		return new AddHouseholdPaymentMethodBuilder(householdPaymentMethod);
	}
	
	public static class ForceRemoveHouseholdPaymentMethodBuilder extends RequestBuilder<Boolean, String, ForceRemoveHouseholdPaymentMethodBuilder> {
		
		public ForceRemoveHouseholdPaymentMethodBuilder(int paymentGatewayId, int paymentMethodId) {
			super(Boolean.class, "householdpaymentmethod", "forceRemove");
			params.add("paymentGatewayId", paymentGatewayId);
			params.add("paymentMethodId", paymentMethodId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
		
		public void paymentMethodId(String multirequestToken) {
			params.add("paymentMethodId", multirequestToken);
		}
	}

	/**
	 * Force remove of a payment method of the household.
	 * 
	 * @param paymentGatewayId Payment Gateway Identifier
	 * @param paymentMethodId Payment method Identifier
	 */
    public static ForceRemoveHouseholdPaymentMethodBuilder forceRemove(int paymentGatewayId, int paymentMethodId)  {
		return new ForceRemoveHouseholdPaymentMethodBuilder(paymentGatewayId, paymentMethodId);
	}
	
	public static class ListHouseholdPaymentMethodBuilder extends ListResponseRequestBuilder<HouseholdPaymentMethod, HouseholdPaymentMethod.Tokenizer, ListHouseholdPaymentMethodBuilder> {
		
		public ListHouseholdPaymentMethodBuilder() {
			super(HouseholdPaymentMethod.class, "householdpaymentmethod", "list");
		}
	}

	/**
	 * Get a list of all payment methods of the household.
	 */
    public static ListHouseholdPaymentMethodBuilder list()  {
		return new ListHouseholdPaymentMethodBuilder();
	}
	
	public static class RemoveHouseholdPaymentMethodBuilder extends RequestBuilder<Boolean, String, RemoveHouseholdPaymentMethodBuilder> {
		
		public RemoveHouseholdPaymentMethodBuilder(int paymentGatewayId, int paymentMethodId) {
			super(Boolean.class, "householdpaymentmethod", "remove");
			params.add("paymentGatewayId", paymentGatewayId);
			params.add("paymentMethodId", paymentMethodId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
		
		public void paymentMethodId(String multirequestToken) {
			params.add("paymentMethodId", multirequestToken);
		}
	}

	/**
	 * Removes a payment method of the household.
	 * 
	 * @param paymentGatewayId Payment Gateway Identifier
	 * @param paymentMethodId Payment method Identifier
	 */
    public static RemoveHouseholdPaymentMethodBuilder remove(int paymentGatewayId, int paymentMethodId)  {
		return new RemoveHouseholdPaymentMethodBuilder(paymentGatewayId, paymentMethodId);
	}
	
	public static class SetAsDefaultHouseholdPaymentMethodBuilder extends RequestBuilder<Boolean, String, SetAsDefaultHouseholdPaymentMethodBuilder> {
		
		public SetAsDefaultHouseholdPaymentMethodBuilder(int paymentGatewayId, int paymentMethodId) {
			super(Boolean.class, "householdpaymentmethod", "setAsDefault");
			params.add("paymentGatewayId", paymentGatewayId);
			params.add("paymentMethodId", paymentMethodId);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
		
		public void paymentMethodId(String multirequestToken) {
			params.add("paymentMethodId", multirequestToken);
		}
	}

	/**
	 * Set a payment method as default for the household.
	 * 
	 * @param paymentGatewayId Payment Gateway Identifier
	 * @param paymentMethodId Payment method Identifier
	 */
    public static SetAsDefaultHouseholdPaymentMethodBuilder setAsDefault(int paymentGatewayId, int paymentMethodId)  {
		return new SetAsDefaultHouseholdPaymentMethodBuilder(paymentGatewayId, paymentMethodId);
	}
}
