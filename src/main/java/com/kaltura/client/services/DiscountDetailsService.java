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
// Copyright (C) 2006-2021  Kaltura Inc.
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

import com.kaltura.client.types.DiscountDetails;
import com.kaltura.client.types.DiscountDetailsFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DiscountDetailsService {
	
	public static class AddDiscountDetailsBuilder extends RequestBuilder<DiscountDetails, DiscountDetails.Tokenizer, AddDiscountDetailsBuilder> {
		
		public AddDiscountDetailsBuilder(DiscountDetails discountDetails) {
			super(DiscountDetails.class, "discountdetails", "add");
			params.add("discountDetails", discountDetails);
		}
	}

	/**
	 * Internal API !!! Insert new DiscountDetails for partner
	 * 
	 * @param discountDetails Discount details Object
	 */
    public static AddDiscountDetailsBuilder add(DiscountDetails discountDetails)  {
		return new AddDiscountDetailsBuilder(discountDetails);
	}
	
	public static class DeleteDiscountDetailsBuilder extends RequestBuilder<Boolean, String, DeleteDiscountDetailsBuilder> {
		
		public DeleteDiscountDetailsBuilder(long id) {
			super(Boolean.class, "discountdetails", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Internal API !!! Delete DiscountDetails
	 * 
	 * @param id DiscountDetails id
	 */
    public static DeleteDiscountDetailsBuilder delete(long id)  {
		return new DeleteDiscountDetailsBuilder(id);
	}
	
	public static class ListDiscountDetailsBuilder extends ListResponseRequestBuilder<DiscountDetails, DiscountDetails.Tokenizer, ListDiscountDetailsBuilder> {
		
		public ListDiscountDetailsBuilder(DiscountDetailsFilter filter) {
			super(DiscountDetails.class, "discountdetails", "list");
			params.add("filter", filter);
		}
	}

	public static ListDiscountDetailsBuilder list()  {
		return list(null);
	}

	/**
	 * Returns the list of available discounts details, can be filtered by discount
	  codes
	 * 
	 * @param filter Filter
	 */
    public static ListDiscountDetailsBuilder list(DiscountDetailsFilter filter)  {
		return new ListDiscountDetailsBuilder(filter);
	}
	
	public static class UpdateDiscountDetailsBuilder extends RequestBuilder<DiscountDetails, DiscountDetails.Tokenizer, UpdateDiscountDetailsBuilder> {
		
		public UpdateDiscountDetailsBuilder(long id, DiscountDetails discountDetails) {
			super(DiscountDetails.class, "discountdetails", "update");
			params.add("id", id);
			params.add("discountDetails", discountDetails);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update discount details
	 * 
	 * @param id DiscountDetails id
	 * @param discountDetails Discount details Object
	 */
    public static UpdateDiscountDetailsBuilder update(long id, DiscountDetails discountDetails)  {
		return new UpdateDiscountDetailsBuilder(id, discountDetails);
	}
}
