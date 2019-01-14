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

import com.kaltura.client.types.ContentResource;
import com.kaltura.client.types.Image;
import com.kaltura.client.types.ImageFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ImageService {
	
	public static class AddImageBuilder extends RequestBuilder<Image, Image.Tokenizer, AddImageBuilder> {
		
		public AddImageBuilder(Image image) {
			super(Image.class, "image", "add");
			params.add("image", image);
		}
	}

	/**
	 * Add a new image
	 * 
	 * @param image Image
	 */
    public static AddImageBuilder add(Image image)  {
		return new AddImageBuilder(image);
	}
	
	public static class DeleteImageBuilder extends RequestBuilder<Boolean, String, DeleteImageBuilder> {
		
		public DeleteImageBuilder(long id) {
			super(Boolean.class, "image", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing image
	 * 
	 * @param id Image ID
	 */
    public static DeleteImageBuilder delete(long id)  {
		return new DeleteImageBuilder(id);
	}
	
	public static class ListImageBuilder extends ListResponseRequestBuilder<Image, Image.Tokenizer, ListImageBuilder> {
		
		public ListImageBuilder(ImageFilter filter) {
			super(Image.class, "image", "list");
			params.add("filter", filter);
		}
	}

	public static ListImageBuilder list()  {
		return list(null);
	}

	/**
	 * Get the list of images by different filtering
	 * 
	 * @param filter Filter
	 */
    public static ListImageBuilder list(ImageFilter filter)  {
		return new ListImageBuilder(filter);
	}
	
	public static class SetContentImageBuilder extends NullRequestBuilder {
		
		public SetContentImageBuilder(long id, ContentResource content) {
			super("image", "setContent");
			params.add("id", id);
			params.add("content", content);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Sets the content of an existing image
	 * 
	 * @param id Image ID
	 * @param content Content of the image to set
	 */
    public static SetContentImageBuilder setContent(long id, ContentResource content)  {
		return new SetContentImageBuilder(id, content);
	}
}
