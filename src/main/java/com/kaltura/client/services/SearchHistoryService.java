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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.SearchHistory;
import com.kaltura.client.types.SearchHistoryFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SearchHistoryService {
	
	public static class CleanSearchHistoryBuilder extends RequestBuilder<Boolean, String, CleanSearchHistoryBuilder> {
		
		public CleanSearchHistoryBuilder(SearchHistoryFilter filter) {
			super(Boolean.class, "searchhistory", "clean");
			params.add("filter", filter);
		}
	}

	public static CleanSearchHistoryBuilder clean()  {
		return clean(null);
	}

	/**
	 * Clean the user’s search history
	 * 
	 * @param filter Filter of search history
	 */
    public static CleanSearchHistoryBuilder clean(SearchHistoryFilter filter)  {
		return new CleanSearchHistoryBuilder(filter);
	}
	
	public static class DeleteSearchHistoryBuilder extends RequestBuilder<Boolean, String, DeleteSearchHistoryBuilder> {
		
		public DeleteSearchHistoryBuilder(String id) {
			super(Boolean.class, "searchhistory", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a specific search history.              Possible error code: 2032 -
	  ItemNotFound
	 * 
	 * @param id ID of the search history reference as shown in the list action
	 */
    public static DeleteSearchHistoryBuilder delete(String id)  {
		return new DeleteSearchHistoryBuilder(id);
	}
	
	public static class ListSearchHistoryBuilder extends ListResponseRequestBuilder<SearchHistory, SearchHistory.Tokenizer, ListSearchHistoryBuilder> {
		
		public ListSearchHistoryBuilder(SearchHistoryFilter filter, FilterPager pager) {
			super(SearchHistory.class, "searchhistory", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSearchHistoryBuilder list()  {
		return list(null);
	}

	public static ListSearchHistoryBuilder list(SearchHistoryFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get user&amp;#39;s last search requests
	 * 
	 * @param filter Filter parameters for filtering out the result
	 * @param pager Page size and index. Number of assets to return per page. Possible range 5 ≤
	 * size ≥ 50. If omitted - will be set to 25. If a value &gt; 50 provided –
	 * will set to 50&gt;
	 */
    public static ListSearchHistoryBuilder list(SearchHistoryFilter filter, FilterPager pager)  {
		return new ListSearchHistoryBuilder(filter, pager);
	}
}
