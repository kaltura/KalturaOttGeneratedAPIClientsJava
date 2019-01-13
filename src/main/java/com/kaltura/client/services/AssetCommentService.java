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

import com.kaltura.client.types.AssetComment;
import com.kaltura.client.types.AssetCommentFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetCommentService {
	
	public static class AddAssetCommentBuilder extends RequestBuilder<AssetComment, AssetComment.Tokenizer, AddAssetCommentBuilder> {
		
		public AddAssetCommentBuilder(AssetComment comment) {
			super(AssetComment.class, "assetcomment", "add");
			params.add("comment", comment);
		}
	}

	/**
	 * Add asset comments by asset id
	 * 
	 * @param comment comment
	 */
    public static AddAssetCommentBuilder add(AssetComment comment)  {
		return new AddAssetCommentBuilder(comment);
	}
	
	public static class ListAssetCommentBuilder extends ListResponseRequestBuilder<AssetComment, AssetComment.Tokenizer, ListAssetCommentBuilder> {
		
		public ListAssetCommentBuilder(AssetCommentFilter filter, FilterPager pager) {
			super(AssetComment.class, "assetcomment", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListAssetCommentBuilder list(AssetCommentFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns asset comments by asset id
	 * 
	 * @param filter Filtering the assets comments request
	 * @param pager Page size and index
	 */
    public static ListAssetCommentBuilder list(AssetCommentFilter filter, FilterPager pager)  {
		return new ListAssetCommentBuilder(filter, pager);
	}
}
