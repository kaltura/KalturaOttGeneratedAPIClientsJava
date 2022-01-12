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
// Copyright (C) 2006-2022  Kaltura Inc.
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

import com.kaltura.client.types.CategoryVersion;
import com.kaltura.client.types.CategoryVersionFilter;
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

public class CategoryVersionService {
	
	public static class AddCategoryVersionBuilder extends RequestBuilder<CategoryVersion, CategoryVersion.Tokenizer, AddCategoryVersionBuilder> {
		
		public AddCategoryVersionBuilder(CategoryVersion objectToAdd) {
			super(CategoryVersion.class, "categoryversion", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * categoryVersion add
	 * 
	 * @param objectToAdd categoryVersion details
	 */
    public static AddCategoryVersionBuilder add(CategoryVersion objectToAdd)  {
		return new AddCategoryVersionBuilder(objectToAdd);
	}
	
	public static class UpdateCategoryVersionBuilder extends RequestBuilder<CategoryVersion, CategoryVersion.Tokenizer, UpdateCategoryVersionBuilder> {
		
		public UpdateCategoryVersionBuilder(long id, CategoryVersion objectToUpdate) {
			super(CategoryVersion.class, "categoryversion", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * categoryVersion update
	 * 
	 * @param id Category version identifier
	 * @param objectToUpdate categoryVersion details
	 */
    public static UpdateCategoryVersionBuilder update(long id, CategoryVersion objectToUpdate)  {
		return new UpdateCategoryVersionBuilder(id, objectToUpdate);
	}
	
	public static class DeleteCategoryVersionBuilder extends NullRequestBuilder {
		
		public DeleteCategoryVersionBuilder(long id) {
			super("categoryversion", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Remove category version
	 * 
	 * @param id Category version identifier
	 */
    public static DeleteCategoryVersionBuilder delete(long id)  {
		return new DeleteCategoryVersionBuilder(id);
	}
	
	public static class ListCategoryVersionBuilder extends ListResponseRequestBuilder<CategoryVersion, CategoryVersion.Tokenizer, ListCategoryVersionBuilder> {
		
		public ListCategoryVersionBuilder(CategoryVersionFilter filter, FilterPager pager) {
			super(CategoryVersion.class, "categoryversion", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListCategoryVersionBuilder list(CategoryVersionFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Gets all category versions
	 * 
	 * @param filter Request filter
	 * @param pager Request pager
	 */
    public static ListCategoryVersionBuilder list(CategoryVersionFilter filter, FilterPager pager)  {
		return new ListCategoryVersionBuilder(filter, pager);
	}
	
	public static class CreateTreeCategoryVersionBuilder extends RequestBuilder<CategoryVersion, CategoryVersion.Tokenizer, CreateTreeCategoryVersionBuilder> {
		
		public CreateTreeCategoryVersionBuilder(long categoryItemId, String name, String comment) {
			super(CategoryVersion.class, "categoryversion", "createTree");
			params.add("categoryItemId", categoryItemId);
			params.add("name", name);
			params.add("comment", comment);
		}
		
		public void categoryItemId(String multirequestToken) {
			params.add("categoryItemId", multirequestToken);
		}
		
		public void name(String multirequestToken) {
			params.add("name", multirequestToken);
		}
		
		public void comment(String multirequestToken) {
			params.add("comment", multirequestToken);
		}
	}

	/**
	 * Acreate new tree for this categoryItem
	 * 
	 * @param categoryItemId the categoryItemId to create the tree accordingly
	 * @param name Name of version
	 * @param comment Comment of version
	 */
    public static CreateTreeCategoryVersionBuilder createTree(long categoryItemId, String name, String comment)  {
		return new CreateTreeCategoryVersionBuilder(categoryItemId, name, comment);
	}
	
	public static class SetDefaultCategoryVersionBuilder extends NullRequestBuilder {
		
		public SetDefaultCategoryVersionBuilder(long id, boolean force) {
			super("categoryversion", "setDefault");
			params.add("id", id);
			params.add("force", force);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void force(String multirequestToken) {
			params.add("force", multirequestToken);
		}
	}

	public static SetDefaultCategoryVersionBuilder setDefault(long id)  {
		return setDefault(id, false);
	}

	/**
	 * Set new default category version
	 * 
	 * @param id category version id to set as default
	 * @param force force to set even if version is older then currenct version
	 */
    public static SetDefaultCategoryVersionBuilder setDefault(long id, boolean force)  {
		return new SetDefaultCategoryVersionBuilder(id, force);
	}
}
