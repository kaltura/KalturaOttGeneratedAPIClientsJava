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

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.ContextType;
import com.kaltura.client.enums.PlaybackContextType;
import com.kaltura.client.types.AssetFile;
import com.kaltura.client.types.AssetFileContext;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetFileService {
	
	public static class GetContextAssetFileBuilder extends RequestBuilder<AssetFileContext, AssetFileContext.Tokenizer, GetContextAssetFileBuilder> {
		
		public GetContextAssetFileBuilder(String id, ContextType contextType) {
			super(AssetFileContext.class, "assetfile", "getContext");
			params.add("id", id);
			params.add("contextType", contextType);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void contextType(String multirequestToken) {
			params.add("contextType", multirequestToken);
		}
	}

	/**
	 * get KalturaAssetFileContext
	 * 
	 * @param id Asset file identifier
	 * @param contextType Kaltura Context Type (none = 0, recording = 1)
	 */
    public static GetContextAssetFileBuilder getContext(String id, ContextType contextType)  {
		return new GetContextAssetFileBuilder(id, contextType);
	}
	
	public static class PlayManifestAssetFileBuilder extends RequestBuilder<AssetFile, AssetFile.Tokenizer, PlayManifestAssetFileBuilder> {
		
		public PlayManifestAssetFileBuilder(int partnerId, String assetId, AssetType assetType, long assetFileId, PlaybackContextType contextType, String ks) {
			super(AssetFile.class, "assetfile", "playManifest");
			params.add("partnerId", partnerId);
			params.add("assetId", assetId);
			params.add("assetType", assetType);
			params.add("assetFileId", assetFileId);
			params.add("contextType", contextType);
			params.add("ks", ks);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
		
		public void assetFileId(String multirequestToken) {
			params.add("assetFileId", multirequestToken);
		}
		
		public void contextType(String multirequestToken) {
			params.add("contextType", multirequestToken);
		}
		
		public void ks(String multirequestToken) {
			params.add("ks", multirequestToken);
		}
	}

	public static PlayManifestAssetFileBuilder playManifest(int partnerId, String assetId, AssetType assetType, long assetFileId, PlaybackContextType contextType)  {
		return playManifest(partnerId, assetId, assetType, assetFileId, contextType, null);
	}

	/**
	 * Redirects to play manifest
	 * 
	 * @param partnerId Partner identifier
	 * @param assetId Asset identifier
	 * @param assetType Asset type
	 * @param assetFileId Asset file identifier
	 * @param contextType Playback context type
	 * @param ks Kaltura session for the user, not mandatory for anonymous user
	 */
    public static PlayManifestAssetFileBuilder playManifest(int partnerId, String assetId, AssetType assetType, long assetFileId, PlaybackContextType contextType, String ks)  {
		return new PlayManifestAssetFileBuilder(partnerId, assetId, assetType, assetFileId, contextType, ks);
	}
}
