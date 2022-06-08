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
// Copyright (C) 2006-2022  Kaltura Inc.
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

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.types.AssetPersonalSelection;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetPersonalSelectionService {
	
	public static class DeleteAssetPersonalSelectionBuilder extends NullRequestBuilder {
		
		public DeleteAssetPersonalSelectionBuilder(long assetId, AssetType assetType, int slotNumber) {
			super("assetpersonalselection", "delete");
			params.add("assetId", assetId);
			params.add("assetType", assetType);
			params.add("slotNumber", slotNumber);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
		
		public void slotNumber(String multirequestToken) {
			params.add("slotNumber", multirequestToken);
		}
	}

	/**
	 * Remove asset selection in slot
	 * 
	 * @param assetId asset id
	 * @param assetType asset type: media/epg
	 * @param slotNumber slot number
	 */
    public static DeleteAssetPersonalSelectionBuilder delete(long assetId, AssetType assetType, int slotNumber)  {
		return new DeleteAssetPersonalSelectionBuilder(assetId, assetType, slotNumber);
	}
	
	public static class DeleteAllAssetPersonalSelectionBuilder extends NullRequestBuilder {
		
		public DeleteAllAssetPersonalSelectionBuilder(int slotNumber) {
			super("assetpersonalselection", "deleteAll");
			params.add("slotNumber", slotNumber);
		}
		
		public void slotNumber(String multirequestToken) {
			params.add("slotNumber", multirequestToken);
		}
	}

	/**
	 * Remove asset selection in slot
	 * 
	 * @param slotNumber slot number
	 */
    public static DeleteAllAssetPersonalSelectionBuilder deleteAll(int slotNumber)  {
		return new DeleteAllAssetPersonalSelectionBuilder(slotNumber);
	}
	
	public static class UpsertAssetPersonalSelectionBuilder extends RequestBuilder<AssetPersonalSelection, AssetPersonalSelection.Tokenizer, UpsertAssetPersonalSelectionBuilder> {
		
		public UpsertAssetPersonalSelectionBuilder(long assetId, AssetType assetType, int slotNumber) {
			super(AssetPersonalSelection.class, "assetpersonalselection", "upsert");
			params.add("assetId", assetId);
			params.add("assetType", assetType);
			params.add("slotNumber", slotNumber);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
		
		public void slotNumber(String multirequestToken) {
			params.add("slotNumber", multirequestToken);
		}
	}

	/**
	 * Add or update asset selection in slot
	 * 
	 * @param assetId asset id
	 * @param assetType asset type: media/epg
	 * @param slotNumber slot number
	 */
    public static UpsertAssetPersonalSelectionBuilder upsert(long assetId, AssetType assetType, int slotNumber)  {
		return new UpsertAssetPersonalSelectionBuilder(assetId, assetType, slotNumber);
	}
}
