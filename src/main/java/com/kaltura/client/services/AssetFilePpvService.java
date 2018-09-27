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

import com.kaltura.client.types.AssetFilePpv;
import com.kaltura.client.types.AssetFilePpvFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetFilePpvService {
	
	public static class AddAssetFilePpvBuilder extends RequestBuilder<AssetFilePpv, AssetFilePpv.Tokenizer, AddAssetFilePpvBuilder> {
		
		public AddAssetFilePpvBuilder(AssetFilePpv assetFilePpv) {
			super(AssetFilePpv.class, "assetfileppv", "add");
			params.add("assetFilePpv", assetFilePpv);
		}
	}

	/**
	 * Add asset file ppv
	 * 
	 * @param assetFilePpv asset file ppv
	 */
    public static AddAssetFilePpvBuilder add(AssetFilePpv assetFilePpv)  {
		return new AddAssetFilePpvBuilder(assetFilePpv);
	}
	
	public static class DeleteAssetFilePpvBuilder extends RequestBuilder<Boolean, String, DeleteAssetFilePpvBuilder> {
		
		public DeleteAssetFilePpvBuilder(long assetFileId, long ppvModuleId) {
			super(Boolean.class, "assetfileppv", "delete");
			params.add("assetFileId", assetFileId);
			params.add("ppvModuleId", ppvModuleId);
		}
		
		public void assetFileId(String multirequestToken) {
			params.add("assetFileId", multirequestToken);
		}
		
		public void ppvModuleId(String multirequestToken) {
			params.add("ppvModuleId", multirequestToken);
		}
	}

	/**
	 * Delete asset file ppv
	 * 
	 * @param assetFileId Asset file id
	 * @param ppvModuleId Ppv module id
	 */
    public static DeleteAssetFilePpvBuilder delete(long assetFileId, long ppvModuleId)  {
		return new DeleteAssetFilePpvBuilder(assetFileId, ppvModuleId);
	}
	
	public static class ListAssetFilePpvBuilder extends ListResponseRequestBuilder<AssetFilePpv, AssetFilePpv.Tokenizer, ListAssetFilePpvBuilder> {
		
		public ListAssetFilePpvBuilder(AssetFilePpvFilter filter) {
			super(AssetFilePpv.class, "assetfileppv", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Return a list of asset files ppvs for the account with optional filter
	 * 
	 * @param filter Filter parameters for filtering out the result
	 */
    public static ListAssetFilePpvBuilder list(AssetFilePpvFilter filter)  {
		return new ListAssetFilePpvBuilder(filter);
	}
	
	public static class UpdateAssetFilePpvBuilder extends RequestBuilder<AssetFilePpv, AssetFilePpv.Tokenizer, UpdateAssetFilePpvBuilder> {
		
		public UpdateAssetFilePpvBuilder(long assetFileId, long ppvModuleId, AssetFilePpv assetFilePpv) {
			super(AssetFilePpv.class, "assetfileppv", "update");
			params.add("assetFileId", assetFileId);
			params.add("ppvModuleId", ppvModuleId);
			params.add("assetFilePpv", assetFilePpv);
		}
		
		public void assetFileId(String multirequestToken) {
			params.add("assetFileId", multirequestToken);
		}
		
		public void ppvModuleId(String multirequestToken) {
			params.add("ppvModuleId", multirequestToken);
		}
	}

	/**
	 * Update assetFilePpv
	 * 
	 * @param assetFileId Asset file id
	 * @param ppvModuleId Ppv module id
	 * @param assetFilePpv assetFilePpv
	 */
    public static UpdateAssetFilePpvBuilder update(long assetFileId, long ppvModuleId, AssetFilePpv assetFilePpv)  {
		return new UpdateAssetFilePpvBuilder(assetFileId, ppvModuleId, assetFilePpv);
	}
}
