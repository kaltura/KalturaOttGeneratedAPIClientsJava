// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
// text.
//
// Copyright (C) 2006-2023  Kaltura Inc.
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

import com.kaltura.client.types.PriceDetails;
import com.kaltura.client.types.PriceDetailsFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PriceDetailsService {
	
	public static class AddPriceDetailsBuilder extends RequestBuilder<PriceDetails, PriceDetails.Tokenizer, AddPriceDetailsBuilder> {
		
		public AddPriceDetailsBuilder(PriceDetails priceDetails) {
			super(PriceDetails.class, "pricedetails", "add");
			params.add("priceDetails", priceDetails);
		}
	}

	/**
	 * Insert new PriceDetails for partner
	 * 
	 * @param priceDetails PriceDetails Object
	 */
    public static AddPriceDetailsBuilder add(PriceDetails priceDetails)  {
		return new AddPriceDetailsBuilder(priceDetails);
	}
	
	public static class DeletePriceDetailsBuilder extends RequestBuilder<Boolean, String, DeletePriceDetailsBuilder> {
		
		public DeletePriceDetailsBuilder(long id) {
			super(Boolean.class, "pricedetails", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete PriceDetails
	 * 
	 * @param id PriceDetails identifier
	 */
    public static DeletePriceDetailsBuilder delete(long id)  {
		return new DeletePriceDetailsBuilder(id);
	}
	
	public static class ListPriceDetailsBuilder extends ListResponseRequestBuilder<PriceDetails, PriceDetails.Tokenizer, ListPriceDetailsBuilder> {
		
		public ListPriceDetailsBuilder(PriceDetailsFilter filter) {
			super(PriceDetails.class, "pricedetails", "list");
			params.add("filter", filter);
		}
	}

	public static ListPriceDetailsBuilder list()  {
		return list(null);
	}

	/**
	 * Returns the list of available prices, can be filtered by price IDs
	 * 
	 * @param filter Filter
	 */
    public static ListPriceDetailsBuilder list(PriceDetailsFilter filter)  {
		return new ListPriceDetailsBuilder(filter);
	}
	
	public static class UpdatePriceDetailsBuilder extends RequestBuilder<PriceDetails, PriceDetails.Tokenizer, UpdatePriceDetailsBuilder> {
		
		public UpdatePriceDetailsBuilder(long id, PriceDetails priceDetails) {
			super(PriceDetails.class, "pricedetails", "update");
			params.add("id", id);
			params.add("priceDetails", priceDetails);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * update existing PriceDetails
	 * 
	 * @param id id of priceDetails
	 * @param priceDetails priceDetails to update
	 */
    public static UpdatePriceDetailsBuilder update(long id, PriceDetails priceDetails)  {
		return new UpdatePriceDetailsBuilder(id, priceDetails);
	}
}
