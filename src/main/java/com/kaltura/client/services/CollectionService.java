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

import com.kaltura.client.types.Collection;
import com.kaltura.client.types.CollectionFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CollectionService {
	
	public static class AddCollectionBuilder extends RequestBuilder<Collection, Collection.Tokenizer, AddCollectionBuilder> {
		
		public AddCollectionBuilder(Collection collection) {
			super(Collection.class, "collection", "add");
			params.add("collection", collection);
		}
	}

	/**
	 * Insert new collection for partner
	 * 
	 * @param collection collection object
	 */
    public static AddCollectionBuilder add(Collection collection)  {
		return new AddCollectionBuilder(collection);
	}
	
	public static class DeleteCollectionBuilder extends RequestBuilder<Boolean, String, DeleteCollectionBuilder> {
		
		public DeleteCollectionBuilder(long id) {
			super(Boolean.class, "collection", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete collection
	 * 
	 * @param id Collection id
	 */
    public static DeleteCollectionBuilder delete(long id)  {
		return new DeleteCollectionBuilder(id);
	}
	
	public static class ListCollectionBuilder extends ListResponseRequestBuilder<Collection, Collection.Tokenizer, ListCollectionBuilder> {
		
		public ListCollectionBuilder(CollectionFilter filter, FilterPager pager) {
			super(Collection.class, "collection", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListCollectionBuilder list()  {
		return list(null);
	}

	public static ListCollectionBuilder list(CollectionFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns a list of collections requested by Collection IDs or file identifier or
	  coupon group identifier
	 * 
	 * @param filter Filter request
	 * @param pager Page size and index
	 */
    public static ListCollectionBuilder list(CollectionFilter filter, FilterPager pager)  {
		return new ListCollectionBuilder(filter, pager);
	}
	
	public static class UpdateCollectionBuilder extends RequestBuilder<Collection, Collection.Tokenizer, UpdateCollectionBuilder> {
		
		public UpdateCollectionBuilder(long id, Collection collection) {
			super(Collection.class, "collection", "update");
			params.add("id", id);
			params.add("collection", collection);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update Collection
	 * 
	 * @param id Collection id
	 * @param collection Collection
	 */
    public static UpdateCollectionBuilder update(long id, Collection collection)  {
		return new UpdateCollectionBuilder(id, collection);
	}
}
