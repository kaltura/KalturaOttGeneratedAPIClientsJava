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

import com.kaltura.client.types.SubscriptionSet;
import com.kaltura.client.types.SubscriptionSetFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SubscriptionSetService {
	
	public static class AddSubscriptionSetBuilder extends RequestBuilder<SubscriptionSet, SubscriptionSet.Tokenizer, AddSubscriptionSetBuilder> {
		
		public AddSubscriptionSetBuilder(SubscriptionSet subscriptionSet) {
			super(SubscriptionSet.class, "subscriptionset", "add");
			params.add("subscriptionSet", subscriptionSet);
		}
	}

	/**
	 * Add a new subscriptionSet
	 * 
	 * @param subscriptionSet SubscriptionSet Object
	 */
    public static AddSubscriptionSetBuilder add(SubscriptionSet subscriptionSet)  {
		return new AddSubscriptionSetBuilder(subscriptionSet);
	}
	
	public static class DeleteSubscriptionSetBuilder extends RequestBuilder<Boolean, String, DeleteSubscriptionSetBuilder> {
		
		public DeleteSubscriptionSetBuilder(long id) {
			super(Boolean.class, "subscriptionset", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a subscriptionSet
	 * 
	 * @param id SubscriptionSet Identifier
	 */
    public static DeleteSubscriptionSetBuilder delete(long id)  {
		return new DeleteSubscriptionSetBuilder(id);
	}
	
	public static class GetSubscriptionSetBuilder extends RequestBuilder<SubscriptionSet, SubscriptionSet.Tokenizer, GetSubscriptionSetBuilder> {
		
		public GetSubscriptionSetBuilder(long id) {
			super(SubscriptionSet.class, "subscriptionset", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get the subscriptionSet according to the Identifier
	 * 
	 * @param id SubscriptionSet Identifier
	 */
    public static GetSubscriptionSetBuilder get(long id)  {
		return new GetSubscriptionSetBuilder(id);
	}
	
	public static class ListSubscriptionSetBuilder extends ListResponseRequestBuilder<SubscriptionSet, SubscriptionSet.Tokenizer, ListSubscriptionSetBuilder> {
		
		public ListSubscriptionSetBuilder(SubscriptionSetFilter filter) {
			super(SubscriptionSet.class, "subscriptionset", "list");
			params.add("filter", filter);
		}
	}

	public static ListSubscriptionSetBuilder list()  {
		return list(null);
	}

	/**
	 * Returns a list of subscriptionSets requested by ids or subscription ids
	 * 
	 * @param filter SubscriptionSet filter
	 */
    public static ListSubscriptionSetBuilder list(SubscriptionSetFilter filter)  {
		return new ListSubscriptionSetBuilder(filter);
	}
	
	public static class UpdateSubscriptionSetBuilder extends RequestBuilder<SubscriptionSet, SubscriptionSet.Tokenizer, UpdateSubscriptionSetBuilder> {
		
		public UpdateSubscriptionSetBuilder(long id, SubscriptionSet subscriptionSet) {
			super(SubscriptionSet.class, "subscriptionset", "update");
			params.add("id", id);
			params.add("subscriptionSet", subscriptionSet);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update the subscriptionSet
	 * 
	 * @param id SubscriptionSet Identifier
	 * @param subscriptionSet SubscriptionSet Object
	 */
    public static UpdateSubscriptionSetBuilder update(long id, SubscriptionSet subscriptionSet)  {
		return new UpdateSubscriptionSetBuilder(id, subscriptionSet);
	}
}
