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

import com.kaltura.client.types.AssetStructMeta;
import com.kaltura.client.types.AssetStructMetaFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetStructMetaService {
	
	public static class ListAssetStructMetaBuilder extends ListResponseRequestBuilder<AssetStructMeta, AssetStructMeta.Tokenizer, ListAssetStructMetaBuilder> {
		
		public ListAssetStructMetaBuilder(AssetStructMetaFilter filter) {
			super(AssetStructMeta.class, "assetstructmeta", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Return a list of asset struct metas for the account with optional filter
	 * 
	 * @param filter Filter parameters for filtering out the result
	 */
    public static ListAssetStructMetaBuilder list(AssetStructMetaFilter filter)  {
		return new ListAssetStructMetaBuilder(filter);
	}
	
	public static class UpdateAssetStructMetaBuilder extends RequestBuilder<AssetStructMeta, AssetStructMeta.Tokenizer, UpdateAssetStructMetaBuilder> {
		
		public UpdateAssetStructMetaBuilder(long assetStructId, long metaId, AssetStructMeta assetStructMeta) {
			super(AssetStructMeta.class, "assetstructmeta", "update");
			params.add("assetStructId", assetStructId);
			params.add("metaId", metaId);
			params.add("assetStructMeta", assetStructMeta);
		}
		
		public void assetStructId(String multirequestToken) {
			params.add("assetStructId", multirequestToken);
		}
		
		public void metaId(String multirequestToken) {
			params.add("metaId", multirequestToken);
		}
	}

	/**
	 * Update Asset struct meta
	 * 
	 * @param assetStructId AssetStruct Identifier
	 * @param metaId Meta Identifier
	 * @param assetStructMeta AssetStructMeta Object
	 */
    public static UpdateAssetStructMetaBuilder update(long assetStructId, long metaId, AssetStructMeta assetStructMeta)  {
		return new UpdateAssetStructMetaBuilder(assetStructId, metaId, assetStructMeta);
	}
}
