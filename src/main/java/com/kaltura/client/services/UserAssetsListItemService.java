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

import com.kaltura.client.enums.UserAssetsListItemType;
import com.kaltura.client.enums.UserAssetsListType;
import com.kaltura.client.types.UserAssetsListItem;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UserAssetsListItemService {
	
	public static class AddUserAssetsListItemBuilder extends RequestBuilder<UserAssetsListItem, UserAssetsListItem.Tokenizer, AddUserAssetsListItemBuilder> {
		
		public AddUserAssetsListItemBuilder(UserAssetsListItem userAssetsListItem) {
			super(UserAssetsListItem.class, "userassetslistitem", "add");
			params.add("userAssetsListItem", userAssetsListItem);
		}
	}

	/**
	 * Adds a new item to user’s private asset list
	 * 
	 * @param userAssetsListItem A list item to add
	 */
    public static AddUserAssetsListItemBuilder add(UserAssetsListItem userAssetsListItem)  {
		return new AddUserAssetsListItemBuilder(userAssetsListItem);
	}
	
	public static class DeleteUserAssetsListItemBuilder extends RequestBuilder<Boolean, String, DeleteUserAssetsListItemBuilder> {
		
		public DeleteUserAssetsListItemBuilder(String assetId, UserAssetsListType listType) {
			super(Boolean.class, "userassetslistitem", "delete");
			params.add("assetId", assetId);
			params.add("listType", listType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void listType(String multirequestToken) {
			params.add("listType", multirequestToken);
		}
	}

	/**
	 * Deletes an item from user’s private asset list
	 * 
	 * @param assetId Asset id to delete
	 * @param listType Asset list type to delete from
	 */
    public static DeleteUserAssetsListItemBuilder delete(String assetId, UserAssetsListType listType)  {
		return new DeleteUserAssetsListItemBuilder(assetId, listType);
	}
	
	public static class GetUserAssetsListItemBuilder extends RequestBuilder<UserAssetsListItem, UserAssetsListItem.Tokenizer, GetUserAssetsListItemBuilder> {
		
		public GetUserAssetsListItemBuilder(String assetId, UserAssetsListType listType, UserAssetsListItemType itemType) {
			super(UserAssetsListItem.class, "userassetslistitem", "get");
			params.add("assetId", assetId);
			params.add("listType", listType);
			params.add("itemType", itemType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void listType(String multirequestToken) {
			params.add("listType", multirequestToken);
		}
		
		public void itemType(String multirequestToken) {
			params.add("itemType", multirequestToken);
		}
	}

	/**
	 * Get an item from user’s private asset list
	 * 
	 * @param assetId Asset id to get
	 * @param listType Asset list type to get from
	 * @param itemType item type to get
	 */
    public static GetUserAssetsListItemBuilder get(String assetId, UserAssetsListType listType, UserAssetsListItemType itemType)  {
		return new GetUserAssetsListItemBuilder(assetId, listType, itemType);
	}
}
