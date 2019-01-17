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
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CouponsGroupService {
	
	public static class AddCouponsGroupBuilder extends RequestBuilder<CouponsGroup, CouponsGroup.Tokenizer, AddCouponsGroupBuilder> {
		
		public AddCouponsGroupBuilder(CouponsGroup couponsGroup) {
			super(CouponsGroup.class, "couponsgroup", "add");
			params.add("couponsGroup", couponsGroup);
		}
	}

	/**
	 * Add coupons group
	 * 
	 * @param couponsGroup Coupons group
	 */
    public static AddCouponsGroupBuilder add(CouponsGroup couponsGroup)  {
		return new AddCouponsGroupBuilder(couponsGroup);
	}
	
	public static class DeleteCouponsGroupBuilder extends RequestBuilder<Boolean, String, DeleteCouponsGroupBuilder> {
		
		public DeleteCouponsGroupBuilder(long id) {
			super(Boolean.class, "couponsgroup", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a coupons group
	 * 
	 * @param id Coupons group identifier
	 */
    public static DeleteCouponsGroupBuilder delete(long id)  {
		return new DeleteCouponsGroupBuilder(id);
	}
	
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
	
	public static class ListCouponsGroupBuilder extends ListResponseRequestBuilder<CouponsGroup, CouponsGroup.Tokenizer, ListCouponsGroupBuilder> {
		
		public ListCouponsGroupBuilder() {
			super(CouponsGroup.class, "couponsgroup", "list");
		}
	}

	/**
	 * Returns information about partner coupons groups
	 */
    public static ListCouponsGroupBuilder list()  {
		return new ListCouponsGroupBuilder();
	}
	
	public static class UpdateCouponsGroupBuilder extends RequestBuilder<CouponsGroup, CouponsGroup.Tokenizer, UpdateCouponsGroupBuilder> {
		
		public UpdateCouponsGroupBuilder(long id, CouponsGroup couponsGroup) {
			super(CouponsGroup.class, "couponsgroup", "update");
			params.add("id", id);
			params.add("couponsGroup", couponsGroup);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update coupons group
	 * 
	 * @param id Coupons group identifier
	 * @param couponsGroup Coupons group
	 */
    public static UpdateCouponsGroupBuilder update(long id, CouponsGroup couponsGroup)  {
		return new UpdateCouponsGroupBuilder(id, couponsGroup);
	}
}
