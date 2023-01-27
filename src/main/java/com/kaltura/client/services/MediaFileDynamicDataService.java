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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.MediaFileDynamicData;
import com.kaltura.client.types.MediaFileDynamicDataFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class MediaFileDynamicDataService {
	
	public static class AddMediaFileDynamicDataBuilder extends RequestBuilder<MediaFileDynamicData, MediaFileDynamicData.Tokenizer, AddMediaFileDynamicDataBuilder> {
		
		public AddMediaFileDynamicDataBuilder(MediaFileDynamicData dynamicData) {
			super(MediaFileDynamicData.class, "mediafiledynamicdata", "add");
			params.add("dynamicData", dynamicData);
		}
	}

	/**
	 * Add a dynamicData value to the values list of a specific key name in a specific
	  mediaFileTypeId
	 * 
	 * @param dynamicData DynamicData value
	 */
    public static AddMediaFileDynamicDataBuilder add(MediaFileDynamicData dynamicData)  {
		return new AddMediaFileDynamicDataBuilder(dynamicData);
	}
	
	public static class DeleteMediaFileDynamicDataBuilder extends RequestBuilder<Boolean, String, DeleteMediaFileDynamicDataBuilder> {
		
		public DeleteMediaFileDynamicDataBuilder(long id) {
			super(Boolean.class, "mediafiledynamicdata", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing DynamicData value
	 * 
	 * @param id DynamicData identifier
	 */
    public static DeleteMediaFileDynamicDataBuilder delete(long id)  {
		return new DeleteMediaFileDynamicDataBuilder(id);
	}
	
	public static class ListMediaFileDynamicDataBuilder extends ListResponseRequestBuilder<MediaFileDynamicData, MediaFileDynamicData.Tokenizer, ListMediaFileDynamicDataBuilder> {
		
		public ListMediaFileDynamicDataBuilder(MediaFileDynamicDataFilter filter, FilterPager pager) {
			super(MediaFileDynamicData.class, "mediafiledynamicdata", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListMediaFileDynamicDataBuilder list()  {
		return list(null);
	}

	public static ListMediaFileDynamicDataBuilder list(MediaFileDynamicDataFilter filter)  {
		return list(filter, null);
	}

	/**
	 * List and filter existing mediaFile dynamicData values
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListMediaFileDynamicDataBuilder list(MediaFileDynamicDataFilter filter, FilterPager pager)  {
		return new ListMediaFileDynamicDataBuilder(filter, pager);
	}
	
	public static class UpdateMediaFileDynamicDataBuilder extends RequestBuilder<MediaFileDynamicData, MediaFileDynamicData.Tokenizer, UpdateMediaFileDynamicDataBuilder> {
		
		public UpdateMediaFileDynamicDataBuilder(long id, MediaFileDynamicData dynamicData) {
			super(MediaFileDynamicData.class, "mediafiledynamicdata", "update");
			params.add("id", id);
			params.add("dynamicData", dynamicData);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing dynamicData value
	 * 
	 * @param id DynamicData identifier
	 * @param dynamicData DynamicData value
	 */
    public static UpdateMediaFileDynamicDataBuilder update(long id, MediaFileDynamicData dynamicData)  {
		return new UpdateMediaFileDynamicDataBuilder(id, dynamicData);
	}
}
