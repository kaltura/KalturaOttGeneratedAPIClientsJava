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

import com.kaltura.client.types.Favorite;
import com.kaltura.client.types.FavoriteFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class FavoriteService {
	
	public static class AddFavoriteBuilder extends RequestBuilder<Favorite, Favorite.Tokenizer, AddFavoriteBuilder> {
		
		public AddFavoriteBuilder(Favorite favorite) {
			super(Favorite.class, "favorite", "add");
			params.add("favorite", favorite);
		}
	}

	/**
	 * Add media to user&amp;#39;s favorite list
	 * 
	 * @param favorite Favorite details.
	 */
    public static AddFavoriteBuilder add(Favorite favorite)  {
		return new AddFavoriteBuilder(favorite);
	}
	
	public static class DeleteFavoriteBuilder extends RequestBuilder<Boolean, String, DeleteFavoriteBuilder> {
		
		public DeleteFavoriteBuilder(int id) {
			super(Boolean.class, "favorite", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Remove media from user&amp;#39;s favorite list
	 * 
	 * @param id Media identifier
	 */
    public static DeleteFavoriteBuilder delete(int id)  {
		return new DeleteFavoriteBuilder(id);
	}
	
	public static class ListFavoriteBuilder extends ListResponseRequestBuilder<Favorite, Favorite.Tokenizer, ListFavoriteBuilder> {
		
		public ListFavoriteBuilder(FavoriteFilter filter) {
			super(Favorite.class, "favorite", "list");
			params.add("filter", filter);
		}
	}

	public static ListFavoriteBuilder list()  {
		return list(null);
	}

	/**
	 * Retrieving users&amp;#39; favorites
	 * 
	 * @param filter Request filter
	 */
    public static ListFavoriteBuilder list(FavoriteFilter filter)  {
		return new ListFavoriteBuilder(filter);
	}
}
