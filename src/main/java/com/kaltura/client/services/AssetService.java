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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetCount;
import com.kaltura.client.types.AssetFilter;
import com.kaltura.client.types.AssetGroupBy;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.PlaybackContext;
import com.kaltura.client.types.PlaybackContextOptions;
import com.kaltura.client.types.SearchAssetFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetService {
	
	public static class CountAssetBuilder extends RequestBuilder<AssetCount, AssetCount.Tokenizer, CountAssetBuilder> {
		
		public CountAssetBuilder(List<AssetGroupBy> groupBy, SearchAssetFilter filter) {
			super(AssetCount.class, "asset", "count");
			params.add("groupBy", groupBy);
			params.add("filter", filter);
		}
	}

	public static CountAssetBuilder count(List<AssetGroupBy> groupBy)  {
		return count(groupBy, null);
	}

	/**  Returns a group-by result for media or EPG according to given filter. Lists
	  values of each field and their respective count.  */
    public static CountAssetBuilder count(List<AssetGroupBy> groupBy, SearchAssetFilter filter)  {
		return new CountAssetBuilder(groupBy, filter);
	}
	
	public static class GetAssetBuilder extends RequestBuilder<Asset, Asset.Tokenizer, GetAssetBuilder> {
		
		public GetAssetBuilder(String id, AssetReferenceType assetReferenceType) {
			super(Asset.class, "asset", "get");
			params.add("id", id);
			params.add("assetReferenceType", assetReferenceType);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void assetReferenceType(String multirequestToken) {
			params.add("assetReferenceType", multirequestToken);
		}
	}

	/**  Returns media or EPG asset by media / EPG internal or external identifier  */
    public static GetAssetBuilder get(String id, AssetReferenceType assetReferenceType)  {
		return new GetAssetBuilder(id, assetReferenceType);
	}
	
	public static class GetPlaybackContextAssetBuilder extends RequestBuilder<PlaybackContext, PlaybackContext.Tokenizer, GetPlaybackContextAssetBuilder> {
		
		public GetPlaybackContextAssetBuilder(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams) {
			super(PlaybackContext.class, "asset", "getPlaybackContext");
			params.add("assetId", assetId);
			params.add("assetType", assetType);
			params.add("contextDataParams", contextDataParams);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
	}

	/**  This action delivers all data relevant for player  */
    public static GetPlaybackContextAssetBuilder getPlaybackContext(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams)  {
		return new GetPlaybackContextAssetBuilder(assetId, assetType, contextDataParams);
	}
	
	public static class ListAssetBuilder extends ListResponseRequestBuilder<Asset, Asset.Tokenizer, ListAssetBuilder> {
		
		public ListAssetBuilder(AssetFilter filter, FilterPager pager) {
			super(Asset.class, "asset", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListAssetBuilder list()  {
		return list(null);
	}

	public static ListAssetBuilder list(AssetFilter filter)  {
		return list(filter, null);
	}

	/**  Returns media or EPG assets. Filters by media identifiers or by EPG internal or
	  external identifier.  */
    public static ListAssetBuilder list(AssetFilter filter, FilterPager pager)  {
		return new ListAssetBuilder(filter, pager);
	}
}
