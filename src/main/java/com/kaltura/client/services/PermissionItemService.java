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
// Copyright (C) 2006-2021  Kaltura Inc.
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
import com.kaltura.client.types.PermissionItem;
import com.kaltura.client.types.PermissionItemFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PermissionItemService {
	
	public static class ListPermissionItemBuilder extends ListResponseRequestBuilder<PermissionItem, PermissionItem.Tokenizer, ListPermissionItemBuilder> {
		
		public ListPermissionItemBuilder(PermissionItemFilter filter, FilterPager pager) {
			super(PermissionItem.class, "permissionitem", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListPermissionItemBuilder list()  {
		return list(null);
	}

	public static ListPermissionItemBuilder list(PermissionItemFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Return a list of permission items with filtering options
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListPermissionItemBuilder list(PermissionItemFilter filter, FilterPager pager)  {
		return new ListPermissionItemBuilder(filter, pager);
	}
}
