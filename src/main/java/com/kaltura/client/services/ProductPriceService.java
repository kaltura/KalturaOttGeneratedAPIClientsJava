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

import com.kaltura.client.types.ProductPrice;
import com.kaltura.client.types.ProductPriceFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ProductPriceService {
	
	public static class ListProductPriceBuilder extends ListResponseRequestBuilder<ProductPrice, ProductPrice.Tokenizer, ListProductPriceBuilder> {
		
		public ListProductPriceBuilder(ProductPriceFilter filter) {
			super(ProductPrice.class, "productprice", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Returns a price and a purchase status for each subscription or/and media file,
	  for a given user (if passed) and with the consideration of a coupon code (if
	  passed).
	 * 
	 * @param filter Request filter
	 */
    public static ListProductPriceBuilder list(ProductPriceFilter filter)  {
		return new ListProductPriceBuilder(filter);
	}
}
