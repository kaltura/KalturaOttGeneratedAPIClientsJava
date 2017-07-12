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

import com.kaltura.client.Params;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetCount;
import com.kaltura.client.types.AssetFilter;
import com.kaltura.client.types.AssetGroupBy;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
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

    public static RequestBuilder<AssetCount> count(List<AssetGroupBy> groupBy)  {
        return count(groupBy, null);
    }

	/**  Returns a group-by result for media or EPG according to given filter. Lists
	  values of each field and their respective count.  */
    public static RequestBuilder<AssetCount> count(List<AssetGroupBy> groupBy, SearchAssetFilter filter)  {
        Params kparams = new Params();
        kparams.add("groupBy", groupBy);
        kparams.add("filter", filter);

        return new RequestBuilder<AssetCount>(AssetCount.class, "asset", "count", kparams);
    }

	/**  Returns media or EPG asset by media / EPG internal or external identifier  */
    public static RequestBuilder<Asset> get(String id, AssetReferenceType assetReferenceType)  {
        Params kparams = new Params();
        kparams.add("id", id);
        kparams.add("assetReferenceType", assetReferenceType);

        return new RequestBuilder<Asset>(Asset.class, "asset", "get", kparams);
    }

	/**  This action delivers all data relevant for player  */
    public static RequestBuilder<PlaybackContext> getPlaybackContext(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams)  {
        Params kparams = new Params();
        kparams.add("assetId", assetId);
        kparams.add("assetType", assetType);
        kparams.add("contextDataParams", contextDataParams);

        return new RequestBuilder<PlaybackContext>(PlaybackContext.class, "asset", "getPlaybackContext", kparams);
    }

    public static RequestBuilder<ListResponse<Asset>> list()  {
        return list(null);
    }

    public static RequestBuilder<ListResponse<Asset>> list(AssetFilter filter)  {
        return list(filter, null);
    }

	/**  Returns media or EPG assets. Filters by media identifiers or by EPG internal or
	  external identifier.  */
    public static RequestBuilder<ListResponse<Asset>> list(AssetFilter filter, FilterPager pager)  {
        Params kparams = new Params();
        kparams.add("filter", filter);
        kparams.add("pager", pager);

        return new ListResponseRequestBuilder<Asset>(Asset.class, "asset", "list", kparams);
    }
}
