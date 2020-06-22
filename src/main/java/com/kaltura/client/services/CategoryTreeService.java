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

import com.kaltura.client.types.CategoryTree;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CategoryTreeService {
	
	public static class DuplicateCategoryTreeBuilder extends RequestBuilder<CategoryTree, CategoryTree.Tokenizer, DuplicateCategoryTreeBuilder> {
		
		public DuplicateCategoryTreeBuilder(long categoryItemId, String name) {
			super(CategoryTree.class, "categorytree", "duplicate");
			params.add("categoryItemId", categoryItemId);
			params.add("name", name);
		}
		
		public void categoryItemId(String multirequestToken) {
			params.add("categoryItemId", multirequestToken);
		}
		
		public void name(String multirequestToken) {
			params.add("name", multirequestToken);
		}
	}

	/**
	 * Duplicate category Item
	 * 
	 * @param categoryItemId Category item identifier
	 * @param name Root category name
	 */
    public static DuplicateCategoryTreeBuilder duplicate(long categoryItemId, String name)  {
		return new DuplicateCategoryTreeBuilder(categoryItemId, name);
	}
	
	public static class GetCategoryTreeBuilder extends RequestBuilder<CategoryTree, CategoryTree.Tokenizer, GetCategoryTreeBuilder> {
		
		public GetCategoryTreeBuilder(long categoryItemId) {
			super(CategoryTree.class, "categorytree", "get");
			params.add("categoryItemId", categoryItemId);
		}
		
		public void categoryItemId(String multirequestToken) {
			params.add("categoryItemId", multirequestToken);
		}
	}

	/**
	 * Retrive category tree.
	 * 
	 * @param categoryItemId Category item identifier
	 */
    public static GetCategoryTreeBuilder get(long categoryItemId)  {
		return new GetCategoryTreeBuilder(categoryItemId);
	}
}
