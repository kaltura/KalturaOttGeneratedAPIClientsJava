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
// Copyright (C) 2006-2019  Kaltura Inc.
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

import com.kaltura.client.types.HouseholdCoupon;
import com.kaltura.client.types.HouseholdCouponFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdCouponService {
	
	public static class AddHouseholdCouponBuilder extends RequestBuilder<HouseholdCoupon, HouseholdCoupon.Tokenizer, AddHouseholdCouponBuilder> {
		
		public AddHouseholdCouponBuilder(HouseholdCoupon householdCoupon) {
			super(HouseholdCoupon.class, "householdcoupon", "add");
			params.add("householdCoupon", householdCoupon);
		}
	}

	/**
	 * householdCoupon add
	 * 
	 * @param householdCoupon householdCoupon details
	 */
    public static AddHouseholdCouponBuilder add(HouseholdCoupon householdCoupon)  {
		return new AddHouseholdCouponBuilder(householdCoupon);
	}
	
	public static class DeleteHouseholdCouponBuilder extends NullRequestBuilder {
		
		public DeleteHouseholdCouponBuilder(String code) {
			super("householdcoupon", "delete");
			params.add("code", code);
		}
		
		public void code(String multirequestToken) {
			params.add("code", multirequestToken);
		}
	}

	/**
	 * Remove coupon from household
	 * 
	 * @param code Coupon code
	 */
    public static DeleteHouseholdCouponBuilder delete(String code)  {
		return new DeleteHouseholdCouponBuilder(code);
	}
	
	public static class ListHouseholdCouponBuilder extends ListResponseRequestBuilder<HouseholdCoupon, HouseholdCoupon.Tokenizer, ListHouseholdCouponBuilder> {
		
		public ListHouseholdCouponBuilder(HouseholdCouponFilter filter) {
			super(HouseholdCoupon.class, "householdcoupon", "list");
			params.add("filter", filter);
		}
	}

	public static ListHouseholdCouponBuilder list()  {
		return list(null);
	}

	/**
	 * Gets all HouseholdCoupon items for a household
	 * 
	 * @param filter Request filter
	 */
    public static ListHouseholdCouponBuilder list(HouseholdCouponFilter filter)  {
		return new ListHouseholdCouponBuilder(filter);
	}
}
