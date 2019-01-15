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

import com.kaltura.client.types.MediaFileType;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class MediaFileTypeService {
	
	public static class AddMediaFileTypeBuilder extends RequestBuilder<MediaFileType, MediaFileType.Tokenizer, AddMediaFileTypeBuilder> {
		
		public AddMediaFileTypeBuilder(MediaFileType mediaFileType) {
			super(MediaFileType.class, "mediafiletype", "add");
			params.add("mediaFileType", mediaFileType);
		}
	}

	/**
	 * Add new media-file type
	 * 
	 * @param mediaFileType Media-file type
	 */
    public static AddMediaFileTypeBuilder add(MediaFileType mediaFileType)  {
		return new AddMediaFileTypeBuilder(mediaFileType);
	}
	
	public static class DeleteMediaFileTypeBuilder extends RequestBuilder<Boolean, String, DeleteMediaFileTypeBuilder> {
		
		public DeleteMediaFileTypeBuilder(int id) {
			super(Boolean.class, "mediafiletype", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete media-file type by id
	 * 
	 * @param id Media-file type identifier
	 */
    public static DeleteMediaFileTypeBuilder delete(int id)  {
		return new DeleteMediaFileTypeBuilder(id);
	}
	
	public static class ListMediaFileTypeBuilder extends ListResponseRequestBuilder<MediaFileType, MediaFileType.Tokenizer, ListMediaFileTypeBuilder> {
		
		public ListMediaFileTypeBuilder() {
			super(MediaFileType.class, "mediafiletype", "list");
		}
	}

	/**
	 * Returns a list of media-file types
	 */
    public static ListMediaFileTypeBuilder list()  {
		return new ListMediaFileTypeBuilder();
	}
	
	public static class UpdateMediaFileTypeBuilder extends RequestBuilder<MediaFileType, MediaFileType.Tokenizer, UpdateMediaFileTypeBuilder> {
		
		public UpdateMediaFileTypeBuilder(int id, MediaFileType mediaFileType) {
			super(MediaFileType.class, "mediafiletype", "update");
			params.add("id", id);
			params.add("mediaFileType", mediaFileType);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update existing media-file type
	 * 
	 * @param id Media-file type identifier
	 * @param mediaFileType Media-file type
	 */
    public static UpdateMediaFileTypeBuilder update(int id, MediaFileType mediaFileType)  {
		return new UpdateMediaFileTypeBuilder(id, mediaFileType);
	}
}
