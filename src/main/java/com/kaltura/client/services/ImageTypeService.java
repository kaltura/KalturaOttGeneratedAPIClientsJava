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

import com.kaltura.client.types.ImageType;
import com.kaltura.client.types.ImageTypeFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ImageTypeService {
	
	public static class AddImageTypeBuilder extends RequestBuilder<ImageType, ImageType.Tokenizer, AddImageTypeBuilder> {
		
		public AddImageTypeBuilder(ImageType imageType) {
			super(ImageType.class, "imagetype", "add");
			params.add("imageType", imageType);
		}
	}

	/**
	 * Add a new image type
	 * 
	 * @param imageType Image type object
	 */
    public static AddImageTypeBuilder add(ImageType imageType)  {
		return new AddImageTypeBuilder(imageType);
	}
	
	public static class DeleteImageTypeBuilder extends RequestBuilder<Boolean, String, DeleteImageTypeBuilder> {
		
		public DeleteImageTypeBuilder(long id) {
			super(Boolean.class, "imagetype", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing image type
	 * 
	 * @param id Image type ID
	 */
    public static DeleteImageTypeBuilder delete(long id)  {
		return new DeleteImageTypeBuilder(id);
	}
	
	public static class ListImageTypeBuilder extends ListResponseRequestBuilder<ImageType, ImageType.Tokenizer, ListImageTypeBuilder> {
		
		public ListImageTypeBuilder(ImageTypeFilter filter) {
			super(ImageType.class, "imagetype", "list");
			params.add("filter", filter);
		}
	}

	public static ListImageTypeBuilder list()  {
		return list(null);
	}

	/**
	 * Get the list of image types for the partner
	 * 
	 * @param filter Filter
	 */
    public static ListImageTypeBuilder list(ImageTypeFilter filter)  {
		return new ListImageTypeBuilder(filter);
	}
	
	public static class UpdateImageTypeBuilder extends RequestBuilder<ImageType, ImageType.Tokenizer, UpdateImageTypeBuilder> {
		
		public UpdateImageTypeBuilder(long id, ImageType imageType) {
			super(ImageType.class, "imagetype", "update");
			params.add("id", id);
			params.add("imageType", imageType);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing image type
	 * 
	 * @param id Image type ID
	 * @param imageType Image type object
	 */
    public static UpdateImageTypeBuilder update(long id, ImageType imageType)  {
		return new UpdateImageTypeBuilder(id, imageType);
	}
}
