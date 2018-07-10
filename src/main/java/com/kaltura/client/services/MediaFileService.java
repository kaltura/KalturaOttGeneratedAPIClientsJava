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

import com.kaltura.client.types.MediaFile;
import com.kaltura.client.types.MediaFileFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class MediaFileService {
	
	public static class AddMediaFileBuilder extends RequestBuilder<MediaFile, MediaFile.Tokenizer, AddMediaFileBuilder> {
		
		public AddMediaFileBuilder(MediaFile mediaFile) {
			super(MediaFile.class, "mediafile", "add");
			params.add("mediaFile", mediaFile);
		}
	}

	/**
	 * Add a new media file
	 * 
	 * @param mediaFile Media file object
	 */
    public static AddMediaFileBuilder add(MediaFile mediaFile)  {
		return new AddMediaFileBuilder(mediaFile);
	}
	
	public static class DeleteMediaFileBuilder extends RequestBuilder<Boolean, String, DeleteMediaFileBuilder> {
		
		public DeleteMediaFileBuilder(long id) {
			super(Boolean.class, "mediafile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing media file
	 * 
	 * @param id Media file identifier
	 */
    public static DeleteMediaFileBuilder delete(long id)  {
		return new DeleteMediaFileBuilder(id);
	}
	
	public static class ListMediaFileBuilder extends ListResponseRequestBuilder<MediaFile, MediaFile.Tokenizer, ListMediaFileBuilder> {
		
		public ListMediaFileBuilder(MediaFileFilter filter) {
			super(MediaFile.class, "mediafile", "list");
			params.add("filter", filter);
		}
	}

	public static ListMediaFileBuilder list()  {
		return list(null);
	}

	/**
	 * Returns a list of media-file
	 * 
	 * @param filter Filter
	 */
    public static ListMediaFileBuilder list(MediaFileFilter filter)  {
		return new ListMediaFileBuilder(filter);
	}
	
	public static class UpdateMediaFileBuilder extends RequestBuilder<MediaFile, MediaFile.Tokenizer, UpdateMediaFileBuilder> {
		
		public UpdateMediaFileBuilder(long id, MediaFile mediaFile) {
			super(MediaFile.class, "mediafile", "update");
			params.add("id", id);
			params.add("mediaFile", mediaFile);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * update an existing media file
	 * 
	 * @param id Media file identifier
	 * @param mediaFile Media file object
	 */
    public static UpdateMediaFileBuilder update(long id, MediaFile mediaFile)  {
		return new UpdateMediaFileBuilder(id, mediaFile);
	}
}
