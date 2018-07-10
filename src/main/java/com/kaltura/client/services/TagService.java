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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Tag;
import com.kaltura.client.types.TagFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TagService {
	
	public static class AddTagBuilder extends RequestBuilder<Tag, Tag.Tokenizer, AddTagBuilder> {
		
		public AddTagBuilder(Tag tag) {
			super(Tag.class, "tag", "add");
			params.add("tag", tag);
		}
	}

	/**
	 * Add a new tag
	 * 
	 * @param tag Tag Object
	 */
    public static AddTagBuilder add(Tag tag)  {
		return new AddTagBuilder(tag);
	}
	
	public static class DeleteTagBuilder extends RequestBuilder<Boolean, String, DeleteTagBuilder> {
		
		public DeleteTagBuilder(long id) {
			super(Boolean.class, "tag", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing tag
	 * 
	 * @param id Tag Identifier
	 */
    public static DeleteTagBuilder delete(long id)  {
		return new DeleteTagBuilder(id);
	}
	
	public static class ListTagBuilder extends ListResponseRequestBuilder<Tag, Tag.Tokenizer, ListTagBuilder> {
		
		public ListTagBuilder(TagFilter filter, FilterPager pager) {
			super(Tag.class, "tag", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListTagBuilder list()  {
		return list(null);
	}

	public static ListTagBuilder list(TagFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get the list of tags for the partner
	 * 
	 * @param filter Filter
	 * @param pager Page size and index
	 */
    public static ListTagBuilder list(TagFilter filter, FilterPager pager)  {
		return new ListTagBuilder(filter, pager);
	}
	
	public static class UpdateTagBuilder extends RequestBuilder<Tag, Tag.Tokenizer, UpdateTagBuilder> {
		
		public UpdateTagBuilder(long id, Tag tag) {
			super(Tag.class, "tag", "update");
			params.add("id", id);
			params.add("tag", tag);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing tag
	 * 
	 * @param id Tag Identifier
	 * @param tag Tag Object
	 */
    public static UpdateTagBuilder update(long id, Tag tag)  {
		return new UpdateTagBuilder(id, tag);
	}
}
