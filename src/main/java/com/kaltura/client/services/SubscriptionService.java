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

import com.kaltura.client.types.Coupon;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Subscription;
import com.kaltura.client.types.SubscriptionFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SubscriptionService {
	
	public static class ListSubscriptionBuilder extends ListResponseRequestBuilder<Subscription, Subscription.Tokenizer, ListSubscriptionBuilder> {
		
		public ListSubscriptionBuilder(SubscriptionFilter filter, FilterPager pager) {
			super(Subscription.class, "subscription", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSubscriptionBuilder list()  {
		return list(null);
	}

	public static ListSubscriptionBuilder list(SubscriptionFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns a list of subscriptions requested by Subscription ID or file ID
	 * 
	 * @param filter Filter request
	 * @param pager Page size and index
	 */
    public static ListSubscriptionBuilder list(SubscriptionFilter filter, FilterPager pager)  {
		return new ListSubscriptionBuilder(filter, pager);
	}
	
	public static class ValidateCouponSubscriptionBuilder extends RequestBuilder<Coupon, Coupon.Tokenizer, ValidateCouponSubscriptionBuilder> {
		
		public ValidateCouponSubscriptionBuilder(int id, String code) {
			super(Coupon.class, "subscription", "validateCoupon");
			params.add("id", id);
			params.add("code", code);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void code(String multirequestToken) {
			params.add("code", multirequestToken);
		}
	}

	/**
	 * Returns information about a coupon for subscription
	 * 
	 * @param id subscription id
	 * @param code coupon code
	 */
    public static ValidateCouponSubscriptionBuilder validateCoupon(int id, String code)  {
		return new ValidateCouponSubscriptionBuilder(id, code);
	}
}
