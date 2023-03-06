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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.SearchPriorityGroup;
import com.kaltura.client.types.SearchPriorityGroupFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SearchPriorityGroupService {
	
	public static class AddSearchPriorityGroupBuilder extends RequestBuilder<SearchPriorityGroup, SearchPriorityGroup.Tokenizer, AddSearchPriorityGroupBuilder> {
		
		public AddSearchPriorityGroupBuilder(SearchPriorityGroup searchPriorityGroup) {
			super(SearchPriorityGroup.class, "searchprioritygroup", "add");
			params.add("searchPriorityGroup", searchPriorityGroup);
		}
	}

	/**
	 * Add a new priority group.
	 * 
	 * @param searchPriorityGroup Search priority group.
	 */
    public static AddSearchPriorityGroupBuilder add(SearchPriorityGroup searchPriorityGroup)  {
		return new AddSearchPriorityGroupBuilder(searchPriorityGroup);
	}
	
	public static class DeleteSearchPriorityGroupBuilder extends RequestBuilder<Boolean, String, DeleteSearchPriorityGroupBuilder> {
		
		public DeleteSearchPriorityGroupBuilder(int id) {
			super(Boolean.class, "searchprioritygroup", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete the existing priority group by its identifier.
	 * 
	 * @param id The identifier of a search priority group.
	 */
    public static DeleteSearchPriorityGroupBuilder delete(int id)  {
		return new DeleteSearchPriorityGroupBuilder(id);
	}
	
	public static class ListSearchPriorityGroupBuilder extends ListResponseRequestBuilder<SearchPriorityGroup, SearchPriorityGroup.Tokenizer, ListSearchPriorityGroupBuilder> {
		
		public ListSearchPriorityGroupBuilder(SearchPriorityGroupFilter filter, FilterPager pager) {
			super(SearchPriorityGroup.class, "searchprioritygroup", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSearchPriorityGroupBuilder list(SearchPriorityGroupFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Gets list of search priority groups which meet the filter criteria.
	 * 
	 * @param filter Filter.
	 * @param pager Page size and index.
	 */
    public static ListSearchPriorityGroupBuilder list(SearchPriorityGroupFilter filter, FilterPager pager)  {
		return new ListSearchPriorityGroupBuilder(filter, pager);
	}
	
	public static class UpdateSearchPriorityGroupBuilder extends RequestBuilder<SearchPriorityGroup, SearchPriorityGroup.Tokenizer, UpdateSearchPriorityGroupBuilder> {
		
		public UpdateSearchPriorityGroupBuilder(long id, SearchPriorityGroup searchPriorityGroup) {
			super(SearchPriorityGroup.class, "searchprioritygroup", "update");
			params.add("id", id);
			params.add("searchPriorityGroup", searchPriorityGroup);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing priority group.
	 * 
	 * @param id Identifier of search priority group.
	 * @param searchPriorityGroup Search priority group.
	 */
    public static UpdateSearchPriorityGroupBuilder update(long id, SearchPriorityGroup searchPriorityGroup)  {
		return new UpdateSearchPriorityGroupBuilder(id, searchPriorityGroup);
	}
}
