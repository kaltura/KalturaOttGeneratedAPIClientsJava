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
// Copyright (C) 2006-2020  Kaltura Inc.
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

import com.kaltura.client.types.DynamicList;
import com.kaltura.client.types.DynamicListFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DynamicListService {
	
	public static class AddDynamicListBuilder extends RequestBuilder<DynamicList, DynamicList.Tokenizer, AddDynamicListBuilder> {
		
		public AddDynamicListBuilder(DynamicList objectToAdd) {
			super(DynamicList.class, "dynamiclist", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add an object
	 * 
	 * @param objectToAdd Object to add
	 */
    public static AddDynamicListBuilder add(DynamicList objectToAdd)  {
		return new AddDynamicListBuilder(objectToAdd);
	}
	
	public static class UpdateDynamicListBuilder extends RequestBuilder<DynamicList, DynamicList.Tokenizer, UpdateDynamicListBuilder> {
		
		public UpdateDynamicListBuilder(long id, DynamicList objectToUpdate) {
			super(DynamicList.class, "dynamiclist", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an object
	 * 
	 * @param id Object ID to update
	 * @param objectToUpdate Object to update
	 */
    public static UpdateDynamicListBuilder update(long id, DynamicList objectToUpdate)  {
		return new UpdateDynamicListBuilder(id, objectToUpdate);
	}
	
	public static class DeleteDynamicListBuilder extends NullRequestBuilder {
		
		public DeleteDynamicListBuilder(long id) {
			super("dynamiclist", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an object
	 * 
	 * @param id Object ID to delete
	 */
    public static DeleteDynamicListBuilder delete(long id)  {
		return new DeleteDynamicListBuilder(id);
	}
	
	public static class ListDynamicListBuilder extends ListResponseRequestBuilder<DynamicList, DynamicList.Tokenizer, ListDynamicListBuilder> {
		
		public ListDynamicListBuilder(DynamicListFilter filter, FilterPager pager) {
			super(DynamicList.class, "dynamiclist", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListDynamicListBuilder list(DynamicListFilter filter)  {
		return list(filter, null);
	}

    public static ListDynamicListBuilder list(DynamicListFilter filter, FilterPager pager)  {
		return new ListDynamicListBuilder(filter, pager);
	}
}
