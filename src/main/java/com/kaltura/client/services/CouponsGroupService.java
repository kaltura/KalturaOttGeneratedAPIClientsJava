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

import com.kaltura.client.types.CouponGenerationOptions;
import com.kaltura.client.types.CouponsGroup;
import com.kaltura.client.types.StringValueArray;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CouponsGroupService {
	
	public static class GenerateCouponsGroupBuilder extends RequestBuilder<StringValueArray, StringValueArray.Tokenizer, GenerateCouponsGroupBuilder> {
		
		public GenerateCouponsGroupBuilder(long id, CouponGenerationOptions couponGenerationOptions) {
			super(StringValueArray.class, "couponsgroup", "generate");
			params.add("id", id);
			params.add("couponGenerationOptions", couponGenerationOptions);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Generate a coupon
	 * 
	 * @param id Coupon group identifier
	 * @param couponGenerationOptions Coupon generation options
	 */
    public static GenerateCouponsGroupBuilder generate(long id, CouponGenerationOptions couponGenerationOptions)  {
		return new GenerateCouponsGroupBuilder(id, couponGenerationOptions);
	}
	
	public static class GetCouponsGroupBuilder extends RequestBuilder<CouponsGroup, CouponsGroup.Tokenizer, GetCouponsGroupBuilder> {
		
		public GetCouponsGroupBuilder(long id) {
			super(CouponsGroup.class, "couponsgroup", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Returns information about coupons group
	 * 
	 * @param id Coupons group ID
	 */
    public static GetCouponsGroupBuilder get(long id)  {
		return new GetCouponsGroupBuilder(id);
	}
}
