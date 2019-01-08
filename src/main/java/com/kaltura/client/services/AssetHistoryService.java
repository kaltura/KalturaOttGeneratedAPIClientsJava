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

import com.kaltura.client.types.AssetHistory;
import com.kaltura.client.types.AssetHistoryFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetHistoryService {
	
	public static class CleanAssetHistoryBuilder extends NullRequestBuilder {
		
		public CleanAssetHistoryBuilder(AssetHistoryFilter filter) {
			super("assethistory", "clean");
			params.add("filter", filter);
		}
	}

	public static CleanAssetHistoryBuilder clean()  {
		return clean(null);
	}

	/**
	 * Clean the user’s viewing history
	 * 
	 * @param filter List of assets identifier
	 */
    public static CleanAssetHistoryBuilder clean(AssetHistoryFilter filter)  {
		return new CleanAssetHistoryBuilder(filter);
	}
	
	public static class ListAssetHistoryBuilder extends ListResponseRequestBuilder<AssetHistory, AssetHistory.Tokenizer, ListAssetHistoryBuilder> {
		
		public ListAssetHistoryBuilder(AssetHistoryFilter filter, FilterPager pager) {
			super(AssetHistory.class, "assethistory", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListAssetHistoryBuilder list()  {
		return list(null);
	}

	public static ListAssetHistoryBuilder list(AssetHistoryFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get recently watched media for user, ordered by recently watched first.
	 * 
	 * @param filter Filter parameters for filtering out the result
	 * @param pager Page size and index. Number of assets to return per page. Possible range 5 ≤
	 * size ≥ 50. If omitted - will be set to 25. If a value &gt; 50 provided –
	 * will set to 50
	 */
    public static ListAssetHistoryBuilder list(AssetHistoryFilter filter, FilterPager pager)  {
		return new ListAssetHistoryBuilder(filter, pager);
	}
}
