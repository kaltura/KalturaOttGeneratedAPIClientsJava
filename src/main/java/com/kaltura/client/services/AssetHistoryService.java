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

import com.kaltura.client.enums.NotWatchedReturnStrategy;
import com.kaltura.client.enums.WatchedAllReturnStrategy;
import com.kaltura.client.types.AssetHistory;
import com.kaltura.client.types.AssetHistoryFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.SeriesIdArguments;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
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
	
	public static class GetNextEpisodeAssetHistoryBuilder extends RequestBuilder<AssetHistory, AssetHistory.Tokenizer, GetNextEpisodeAssetHistoryBuilder> {
		
		public GetNextEpisodeAssetHistoryBuilder(long assetId, SeriesIdArguments seriesIdArguments, NotWatchedReturnStrategy notWatchedReturnStrategy, WatchedAllReturnStrategy watchedAllReturnStrategy) {
			super(AssetHistory.class, "assethistory", "getNextEpisode");
			params.add("assetId", assetId);
			params.add("seriesIdArguments", seriesIdArguments);
			params.add("notWatchedReturnStrategy", notWatchedReturnStrategy);
			params.add("watchedAllReturnStrategy", watchedAllReturnStrategy);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void notWatchedReturnStrategy(String multirequestToken) {
			params.add("notWatchedReturnStrategy", multirequestToken);
		}
		
		public void watchedAllReturnStrategy(String multirequestToken) {
			params.add("watchedAllReturnStrategy", multirequestToken);
		}
	}

	public static GetNextEpisodeAssetHistoryBuilder getNextEpisode()  {
		return getNextEpisode(Long.MIN_VALUE);
	}

	public static GetNextEpisodeAssetHistoryBuilder getNextEpisode(long assetId)  {
		return getNextEpisode(assetId, null);
	}

	public static GetNextEpisodeAssetHistoryBuilder getNextEpisode(long assetId, SeriesIdArguments seriesIdArguments)  {
		return getNextEpisode(assetId, seriesIdArguments, null);
	}

	public static GetNextEpisodeAssetHistoryBuilder getNextEpisode(long assetId, SeriesIdArguments seriesIdArguments, NotWatchedReturnStrategy notWatchedReturnStrategy)  {
		return getNextEpisode(assetId, seriesIdArguments, notWatchedReturnStrategy, null);
	}

	/**
	 * Get next episode by last watch asset in given assetId
	 * 
	 * @param assetId asset Id of series to search for next episode
	 * @param seriesIdArguments series Id arguments
	 * @param notWatchedReturnStrategy not watched any episode strategy
	 * @param watchedAllReturnStrategy watched all series episodes strategy
	 */
    public static GetNextEpisodeAssetHistoryBuilder getNextEpisode(long assetId, SeriesIdArguments seriesIdArguments, NotWatchedReturnStrategy notWatchedReturnStrategy, WatchedAllReturnStrategy watchedAllReturnStrategy)  {
		return new GetNextEpisodeAssetHistoryBuilder(assetId, seriesIdArguments, notWatchedReturnStrategy, watchedAllReturnStrategy);
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
