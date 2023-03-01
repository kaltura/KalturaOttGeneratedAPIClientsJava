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

import com.kaltura.client.types.CategoryItem;
import com.kaltura.client.types.CategoryItemFilter;
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

public class CategoryItemService {
	
	public static class AddCategoryItemBuilder extends RequestBuilder<CategoryItem, CategoryItem.Tokenizer, AddCategoryItemBuilder> {
		
		public AddCategoryItemBuilder(CategoryItem objectToAdd) {
			super(CategoryItem.class, "categoryitem", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * categoryItem add
	 * 
	 * @param objectToAdd categoryItem details
	 */
    public static AddCategoryItemBuilder add(CategoryItem objectToAdd)  {
		return new AddCategoryItemBuilder(objectToAdd);
	}
	
	public static class DeleteCategoryItemBuilder extends NullRequestBuilder {
		
		public DeleteCategoryItemBuilder(long id) {
			super("categoryitem", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Remove category
	 * 
	 * @param id Category identifier
	 */
    public static DeleteCategoryItemBuilder delete(long id)  {
		return new DeleteCategoryItemBuilder(id);
	}
	
	public static class ListCategoryItemBuilder extends ListResponseRequestBuilder<CategoryItem, CategoryItem.Tokenizer, ListCategoryItemBuilder> {
		
		public ListCategoryItemBuilder(CategoryItemFilter filter, FilterPager pager) {
			super(CategoryItem.class, "categoryitem", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListCategoryItemBuilder list()  {
		return list(null);
	}

	public static ListCategoryItemBuilder list(CategoryItemFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Gets all categoryItem items
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListCategoryItemBuilder list(CategoryItemFilter filter, FilterPager pager)  {
		return new ListCategoryItemBuilder(filter, pager);
	}
	
	public static class UpdateCategoryItemBuilder extends RequestBuilder<CategoryItem, CategoryItem.Tokenizer, UpdateCategoryItemBuilder> {
		
		public UpdateCategoryItemBuilder(long id, CategoryItem objectToUpdate) {
			super(CategoryItem.class, "categoryitem", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * categoryItem update
	 * 
	 * @param id Category identifier
	 * @param objectToUpdate categoryItem details
	 */
    public static UpdateCategoryItemBuilder update(long id, CategoryItem objectToUpdate)  {
		return new UpdateCategoryItemBuilder(id, objectToUpdate);
	}
}
