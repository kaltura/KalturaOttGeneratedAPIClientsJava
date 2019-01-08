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

import com.kaltura.client.types.PaymentMethodProfile;
import com.kaltura.client.types.PaymentMethodProfileFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PaymentMethodProfileService {
	
	public static class AddPaymentMethodProfileBuilder extends RequestBuilder<PaymentMethodProfile, PaymentMethodProfile.Tokenizer, AddPaymentMethodProfileBuilder> {
		
		public AddPaymentMethodProfileBuilder(PaymentMethodProfile paymentMethod) {
			super(PaymentMethodProfile.class, "paymentmethodprofile", "add");
			params.add("paymentMethod", paymentMethod);
		}
	}

	/**
	 * TBD
	 * 
	 * @param paymentMethod Payment method to add
	 */
    public static AddPaymentMethodProfileBuilder add(PaymentMethodProfile paymentMethod)  {
		return new AddPaymentMethodProfileBuilder(paymentMethod);
	}
	
	public static class DeletePaymentMethodProfileBuilder extends RequestBuilder<Boolean, String, DeletePaymentMethodProfileBuilder> {
		
		public DeletePaymentMethodProfileBuilder(int paymentMethodId) {
			super(Boolean.class, "paymentmethodprofile", "delete");
			params.add("paymentMethodId", paymentMethodId);
		}
		
		public void paymentMethodId(String multirequestToken) {
			params.add("paymentMethodId", multirequestToken);
		}
	}

	/**
	 * Delete payment method profile
	 * 
	 * @param paymentMethodId Payment method identifier to delete
	 */
    public static DeletePaymentMethodProfileBuilder delete(int paymentMethodId)  {
		return new DeletePaymentMethodProfileBuilder(paymentMethodId);
	}
	
	public static class ListPaymentMethodProfileBuilder extends ListResponseRequestBuilder<PaymentMethodProfile, PaymentMethodProfile.Tokenizer, ListPaymentMethodProfileBuilder> {
		
		public ListPaymentMethodProfileBuilder(PaymentMethodProfileFilter filter) {
			super(PaymentMethodProfile.class, "paymentmethodprofile", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * TBD
	 * 
	 * @param filter Payment gateway method profile filter
	 */
    public static ListPaymentMethodProfileBuilder list(PaymentMethodProfileFilter filter)  {
		return new ListPaymentMethodProfileBuilder(filter);
	}
	
	public static class UpdatePaymentMethodProfileBuilder extends RequestBuilder<PaymentMethodProfile, PaymentMethodProfile.Tokenizer, UpdatePaymentMethodProfileBuilder> {
		
		public UpdatePaymentMethodProfileBuilder(int paymentMethodId, PaymentMethodProfile paymentMethod) {
			super(PaymentMethodProfile.class, "paymentmethodprofile", "update");
			params.add("paymentMethodId", paymentMethodId);
			params.add("paymentMethod", paymentMethod);
		}
		
		public void paymentMethodId(String multirequestToken) {
			params.add("paymentMethodId", multirequestToken);
		}
	}

	/**
	 * Update payment method
	 * 
	 * @param paymentMethodId Payment method identifier to update
	 * @param paymentMethod Payment method to update
	 */
    public static UpdatePaymentMethodProfileBuilder update(int paymentMethodId, PaymentMethodProfile paymentMethod)  {
		return new UpdatePaymentMethodProfileBuilder(paymentMethodId, paymentMethod);
	}
}
