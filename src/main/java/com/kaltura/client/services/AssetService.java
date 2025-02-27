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

import com.kaltura.client.FileHolder;
import com.kaltura.client.Files;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.UnmatchedItemsPolicy;
import com.kaltura.client.types.AdsContext;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetCount;
import com.kaltura.client.types.AssetFilter;
import com.kaltura.client.types.AssetGroupBy;
import com.kaltura.client.types.BaseAssetOrder;
import com.kaltura.client.types.BulkUpload;
import com.kaltura.client.types.BulkUploadAssetData;
import com.kaltura.client.types.BulkUploadJobData;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListGroupsRepresentativesFilter;
import com.kaltura.client.types.PersonalAssetSelectionFilter;
import com.kaltura.client.types.PlaybackContext;
import com.kaltura.client.types.PlaybackContextOptions;
import com.kaltura.client.types.RepresentativeSelectionPolicy;
import com.kaltura.client.types.SearchAssetFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetService {
	
	public static class AddAssetBuilder extends RequestBuilder<Asset, Asset.Tokenizer, AddAssetBuilder> {
		
		public AddAssetBuilder(Asset asset) {
			super(Asset.class, "asset", "add");
			params.add("asset", asset);
		}
	}

	/**
	 * Add a new asset.              For metas of type bool-&amp;gt; use
	  kalturaBoolValue, type number-&amp;gt; KalturaDoubleValue, type date -&amp;gt;
	  KalturaLongValue, type string -&amp;gt; KalturaStringValue
	 * 
	 * @param asset Asset object
	 */
    public static AddAssetBuilder add(Asset asset)  {
		return new AddAssetBuilder(asset);
	}
	
	public static class AddFromBulkUploadAssetBuilder extends RequestBuilder<BulkUpload, BulkUpload.Tokenizer, AddFromBulkUploadAssetBuilder> {
		
		public AddFromBulkUploadAssetBuilder(FileHolder fileData, BulkUploadJobData bulkUploadJobData, BulkUploadAssetData bulkUploadAssetData) {
			super(BulkUpload.class, "asset", "addFromBulkUpload");
			files = new Files();
			files.add("fileData", fileData);
			params.add("bulkUploadJobData", bulkUploadJobData);
			params.add("bulkUploadAssetData", bulkUploadAssetData);
		}
	}

	public static AddFromBulkUploadAssetBuilder addFromBulkUpload(File fileData, BulkUploadJobData bulkUploadJobData, BulkUploadAssetData bulkUploadAssetData)  {
		return addFromBulkUpload(new FileHolder(fileData), bulkUploadJobData, bulkUploadAssetData);
	}

	public static AddFromBulkUploadAssetBuilder addFromBulkUpload(InputStream fileData, String fileDataMimeType, String fileDataName, long fileDataSize, BulkUploadJobData bulkUploadJobData, BulkUploadAssetData bulkUploadAssetData)  {
		return addFromBulkUpload(new FileHolder(fileData, fileDataMimeType, fileDataName, fileDataSize), bulkUploadJobData, bulkUploadAssetData);
	}

	public static AddFromBulkUploadAssetBuilder addFromBulkUpload(FileInputStream fileData, String fileDataMimeType, String fileDataName, BulkUploadJobData bulkUploadJobData, BulkUploadAssetData bulkUploadAssetData)  {
		return addFromBulkUpload(new FileHolder(fileData, fileDataMimeType, fileDataName), bulkUploadJobData, bulkUploadAssetData);
	}

	/**
	 * Add new bulk upload batch job Conversion profile id can be specified in the API
	  (note that the total request body size is limited to 10MB).
	 * 
	 * @param fileData fileData
	 * @param bulkUploadJobData bulkUploadJobData
	 * @param bulkUploadAssetData bulkUploadAssetData
	 */
    public static AddFromBulkUploadAssetBuilder addFromBulkUpload(FileHolder fileData, BulkUploadJobData bulkUploadJobData, BulkUploadAssetData bulkUploadAssetData)  {
		return new AddFromBulkUploadAssetBuilder(fileData, bulkUploadJobData, bulkUploadAssetData);
	}
	
	public static class CountAssetBuilder extends RequestBuilder<AssetCount, AssetCount.Tokenizer, CountAssetBuilder> {
		
		public CountAssetBuilder(SearchAssetFilter filter) {
			super(AssetCount.class, "asset", "count");
			params.add("filter", filter);
		}
	}

	public static CountAssetBuilder count()  {
		return count(null);
	}

	/**
	 * Returns a group-by result for media or EPG according to given filter. Lists
	  values of each field and their respective count.
	 * 
	 * @param filter Filtering the assets request
	 */
    public static CountAssetBuilder count(SearchAssetFilter filter)  {
		return new CountAssetBuilder(filter);
	}
	
	public static class DeleteAssetBuilder extends RequestBuilder<Boolean, String, DeleteAssetBuilder> {
		
		public DeleteAssetBuilder(long id, AssetReferenceType assetReferenceType) {
			super(Boolean.class, "asset", "delete");
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

	/**
	 * Delete an existing asset
	 * 
	 * @param id Asset Identifier
	 * @param assetReferenceType Type of asset
	 */
    public static DeleteAssetBuilder delete(long id, AssetReferenceType assetReferenceType)  {
		return new DeleteAssetBuilder(id, assetReferenceType);
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

	/**
	 * Returns media or EPG asset by media / EPG internal or external identifier.      
	         Note: OPC accounts asset.get for internal identifier doesn&amp;#39;t take
	  under consideration personalized aspects neither shop limitations.
	 * 
	 * @param id Asset identifier
	 * @param assetReferenceType Asset type
	 */
    public static GetAssetBuilder get(String id, AssetReferenceType assetReferenceType)  {
		return new GetAssetBuilder(id, assetReferenceType);
	}
	
	public static class GetAdsContextAssetBuilder extends RequestBuilder<AdsContext, AdsContext.Tokenizer, GetAdsContextAssetBuilder> {
		
		public GetAdsContextAssetBuilder(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams) {
			super(AdsContext.class, "asset", "getAdsContext");
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

	/**
	 * Returns the data for ads control
	 * 
	 * @param assetId Asset identifier
	 * @param assetType Asset type
	 * @param contextDataParams Parameters for the request
	 */
    public static GetAdsContextAssetBuilder getAdsContext(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams)  {
		return new GetAdsContextAssetBuilder(assetId, assetType, contextDataParams);
	}
	
	public static class GetPlaybackContextAssetBuilder extends RequestBuilder<PlaybackContext, PlaybackContext.Tokenizer, GetPlaybackContextAssetBuilder> {
		
		public GetPlaybackContextAssetBuilder(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams, String sourceType) {
			super(PlaybackContext.class, "asset", "getPlaybackContext");
			params.add("assetId", assetId);
			params.add("assetType", assetType);
			params.add("contextDataParams", contextDataParams);
			params.add("sourceType", sourceType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
		
		public void sourceType(String multirequestToken) {
			params.add("sourceType", multirequestToken);
		}
	}

	public static GetPlaybackContextAssetBuilder getPlaybackContext(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams)  {
		return getPlaybackContext(assetId, assetType, contextDataParams, null);
	}

	/**
	 * This action delivers all data relevant for player
	 * 
	 * @param assetId Asset identifier
	 * @param assetType Asset type
	 * @param contextDataParams Parameters for the request
	 * @param sourceType Filter sources by type
	 */
    public static GetPlaybackContextAssetBuilder getPlaybackContext(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams, String sourceType)  {
		return new GetPlaybackContextAssetBuilder(assetId, assetType, contextDataParams, sourceType);
	}
	
	public static class GetPlaybackManifestAssetBuilder extends RequestBuilder<PlaybackContext, PlaybackContext.Tokenizer, GetPlaybackManifestAssetBuilder> {
		
		public GetPlaybackManifestAssetBuilder(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams, String sourceType) {
			super(PlaybackContext.class, "asset", "getPlaybackManifest");
			params.add("assetId", assetId);
			params.add("assetType", assetType);
			params.add("contextDataParams", contextDataParams);
			params.add("sourceType", sourceType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
		
		public void sourceType(String multirequestToken) {
			params.add("sourceType", multirequestToken);
		}
	}

	public static GetPlaybackManifestAssetBuilder getPlaybackManifest(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams)  {
		return getPlaybackManifest(assetId, assetType, contextDataParams, null);
	}

	/**
	 * This action delivers all data relevant for player
	 * 
	 * @param assetId Asset identifier
	 * @param assetType Asset type
	 * @param contextDataParams Parameters for the request
	 * @param sourceType Filter sources by type
	 */
    public static GetPlaybackManifestAssetBuilder getPlaybackManifest(String assetId, AssetType assetType, PlaybackContextOptions contextDataParams, String sourceType)  {
		return new GetPlaybackManifestAssetBuilder(assetId, assetType, contextDataParams, sourceType);
	}
	
	public static class GroupRepresentativeListAssetBuilder extends ListResponseRequestBuilder<Asset, Asset.Tokenizer, GroupRepresentativeListAssetBuilder> {
		
		public GroupRepresentativeListAssetBuilder(AssetGroupBy groupBy, UnmatchedItemsPolicy unmatchedItemsPolicy, BaseAssetOrder orderBy, ListGroupsRepresentativesFilter filter, RepresentativeSelectionPolicy selectionPolicy, FilterPager pager) {
			super(Asset.class, "asset", "groupRepresentativeList");
			params.add("groupBy", groupBy);
			params.add("unmatchedItemsPolicy", unmatchedItemsPolicy);
			params.add("orderBy", orderBy);
			params.add("filter", filter);
			params.add("selectionPolicy", selectionPolicy);
			params.add("pager", pager);
		}
		
		public void unmatchedItemsPolicy(String multirequestToken) {
			params.add("unmatchedItemsPolicy", multirequestToken);
		}
	}

	public static GroupRepresentativeListAssetBuilder groupRepresentativeList(AssetGroupBy groupBy, UnmatchedItemsPolicy unmatchedItemsPolicy)  {
		return groupRepresentativeList(groupBy, unmatchedItemsPolicy, null);
	}

	public static GroupRepresentativeListAssetBuilder groupRepresentativeList(AssetGroupBy groupBy, UnmatchedItemsPolicy unmatchedItemsPolicy, BaseAssetOrder orderBy)  {
		return groupRepresentativeList(groupBy, unmatchedItemsPolicy, orderBy, null);
	}

	public static GroupRepresentativeListAssetBuilder groupRepresentativeList(AssetGroupBy groupBy, UnmatchedItemsPolicy unmatchedItemsPolicy, BaseAssetOrder orderBy, ListGroupsRepresentativesFilter filter)  {
		return groupRepresentativeList(groupBy, unmatchedItemsPolicy, orderBy, filter, null);
	}

	public static GroupRepresentativeListAssetBuilder groupRepresentativeList(AssetGroupBy groupBy, UnmatchedItemsPolicy unmatchedItemsPolicy, BaseAssetOrder orderBy, ListGroupsRepresentativesFilter filter, RepresentativeSelectionPolicy selectionPolicy)  {
		return groupRepresentativeList(groupBy, unmatchedItemsPolicy, orderBy, filter, selectionPolicy, null);
	}

	/**
	 * Returns assets deduplicated by asset metadata (or supported asset&amp;#39;s
	  property).
	 * 
	 * @param groupBy A metadata (or supported asset's property) to group by the assets
	 * @param unmatchedItemsPolicy Defines the policy to handle assets that don't have groupBy property
	 * @param orderBy A metadata or supported asset's property to sort by
	 * @param filter Filtering the assets request
	 * @param selectionPolicy A policy that implements a well defined parametric process to select an asset
	 * out of group
	 * @param pager Paging the request
	 */
    public static GroupRepresentativeListAssetBuilder groupRepresentativeList(AssetGroupBy groupBy, UnmatchedItemsPolicy unmatchedItemsPolicy, BaseAssetOrder orderBy, ListGroupsRepresentativesFilter filter, RepresentativeSelectionPolicy selectionPolicy, FilterPager pager)  {
		return new GroupRepresentativeListAssetBuilder(groupBy, unmatchedItemsPolicy, orderBy, filter, selectionPolicy, pager);
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

	/**
	 * Returns media or EPG assets. Filters by media identifiers or by EPG internal or
	  external identifier.
	 * 
	 * @param filter Filtering the assets request
	 * @param pager Paging the request
	 */
    public static ListAssetBuilder list(AssetFilter filter, FilterPager pager)  {
		return new ListAssetBuilder(filter, pager);
	}
	
	public static class ListPersonalSelectionAssetBuilder extends ListResponseRequestBuilder<Asset, Asset.Tokenizer, ListPersonalSelectionAssetBuilder> {
		
		public ListPersonalSelectionAssetBuilder(PersonalAssetSelectionFilter filter) {
			super(Asset.class, "asset", "listPersonalSelection");
			params.add("filter", filter);
		}
	}

	/**
	 * Returns recent selected assets
	 * 
	 * @param filter Filtering the assets request
	 */
    public static ListPersonalSelectionAssetBuilder listPersonalSelection(PersonalAssetSelectionFilter filter)  {
		return new ListPersonalSelectionAssetBuilder(filter);
	}
	
	public static class RemoveMetasAndTagsAssetBuilder extends RequestBuilder<Boolean, String, RemoveMetasAndTagsAssetBuilder> {
		
		public RemoveMetasAndTagsAssetBuilder(long id, AssetReferenceType assetReferenceType, String idIn) {
			super(Boolean.class, "asset", "removeMetasAndTags");
			params.add("id", id);
			params.add("assetReferenceType", assetReferenceType);
			params.add("idIn", idIn);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void assetReferenceType(String multirequestToken) {
			params.add("assetReferenceType", multirequestToken);
		}
		
		public void idIn(String multirequestToken) {
			params.add("idIn", multirequestToken);
		}
	}

	/**
	 * remove metas and tags from asset
	 * 
	 * @param id Asset Identifier
	 * @param assetReferenceType Type of asset
	 * @param idIn comma separated ids of metas and tags
	 */
    public static RemoveMetasAndTagsAssetBuilder removeMetasAndTags(long id, AssetReferenceType assetReferenceType, String idIn)  {
		return new RemoveMetasAndTagsAssetBuilder(id, assetReferenceType, idIn);
	}
	
	public static class UpdateAssetBuilder extends RequestBuilder<Asset, Asset.Tokenizer, UpdateAssetBuilder> {
		
		public UpdateAssetBuilder(long id, Asset asset) {
			super(Asset.class, "asset", "update");
			params.add("id", id);
			params.add("asset", asset);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * update an existing asset.              For metas of type bool-&amp;gt; use
	  kalturaBoolValue, type number-&amp;gt; KalturaDoubleValue, type date -&amp;gt;
	  KalturaLongValue, type string -&amp;gt; KalturaStringValue
	 * 
	 * @param id Asset Identifier
	 * @param asset Asset object
	 */
    public static UpdateAssetBuilder update(long id, Asset asset)  {
		return new UpdateAssetBuilder(id, asset);
	}
}
