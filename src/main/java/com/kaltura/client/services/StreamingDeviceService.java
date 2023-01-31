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

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.types.StreamingDevice;
import com.kaltura.client.types.StreamingDeviceFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class StreamingDeviceService {
	
	public static class BookPlaybackSessionStreamingDeviceBuilder extends RequestBuilder<Boolean, String, BookPlaybackSessionStreamingDeviceBuilder> {
		
		public BookPlaybackSessionStreamingDeviceBuilder(String fileId, String assetId, AssetType assetType) {
			super(Boolean.class, "streamingdevice", "bookPlaybackSession");
			params.add("fileId", fileId);
			params.add("assetId", assetId);
			params.add("assetType", assetType);
		}
		
		public void fileId(String multirequestToken) {
			params.add("fileId", multirequestToken);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void assetType(String multirequestToken) {
			params.add("assetType", multirequestToken);
		}
	}

	/**
	 * Reserves a concurrency slot for the given asset-device combination
	 * 
	 * @param fileId KalturaMediaFile.id media file belonging to the asset for which a concurrency
	 * slot is being reserved
	 * @param assetId KalturaAsset.id - asset for which a concurrency slot is being reserved
	 * @param assetType Identifies the type of asset for which the concurrency slot is being reserved
	 */
    public static BookPlaybackSessionStreamingDeviceBuilder bookPlaybackSession(String fileId, String assetId, AssetType assetType)  {
		return new BookPlaybackSessionStreamingDeviceBuilder(fileId, assetId, assetType);
	}
	
	public static class ListStreamingDeviceBuilder extends ListResponseRequestBuilder<StreamingDevice, StreamingDevice.Tokenizer, ListStreamingDeviceBuilder> {
		
		public ListStreamingDeviceBuilder(StreamingDeviceFilter filter) {
			super(StreamingDevice.class, "streamingdevice", "list");
			params.add("filter", filter);
		}
	}

	public static ListStreamingDeviceBuilder list()  {
		return list(null);
	}

	/**
	 * Lists of devices that are streaming at that moment
	 * 
	 * @param filter Segmentation type filter - basically empty
	 */
    public static ListStreamingDeviceBuilder list(StreamingDeviceFilter filter)  {
		return new ListStreamingDeviceBuilder(filter);
	}
}
