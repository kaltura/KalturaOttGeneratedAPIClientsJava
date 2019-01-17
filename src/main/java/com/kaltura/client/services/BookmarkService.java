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

import com.kaltura.client.types.Bookmark;
import com.kaltura.client.types.BookmarkFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class BookmarkService {
	
	public static class AddBookmarkBuilder extends RequestBuilder<Boolean, String, AddBookmarkBuilder> {
		
		public AddBookmarkBuilder(Bookmark bookmark) {
			super(Boolean.class, "bookmark", "add");
			params.add("bookmark", bookmark);
		}
	}

	/**
	 * Report player position and action for the user on the watched asset. Player
	  position is used to later allow resume watching.
	 * 
	 * @param bookmark Bookmark details
	 */
    public static AddBookmarkBuilder add(Bookmark bookmark)  {
		return new AddBookmarkBuilder(bookmark);
	}
	
	public static class ListBookmarkBuilder extends ListResponseRequestBuilder<Bookmark, Bookmark.Tokenizer, ListBookmarkBuilder> {
		
		public ListBookmarkBuilder(BookmarkFilter filter) {
			super(Bookmark.class, "bookmark", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Returns player position record/s for the requested asset and the requesting
	  user.               If default user makes the request – player position
	  records are provided for all of the users in the household.              If
	  non-default user makes the request - player position records are provided for
	  the requesting user and the default user of the household.
	 * 
	 * @param filter Filter option for the last position
	 */
    public static ListBookmarkBuilder list(BookmarkFilter filter)  {
		return new ListBookmarkBuilder(filter);
	}
}
