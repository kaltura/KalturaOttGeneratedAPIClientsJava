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

import com.kaltura.client.types.Permission;
import com.kaltura.client.types.PermissionFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PermissionService {
	
	public static class GetCurrentPermissionsPermissionBuilder extends RequestBuilder<String, String, GetCurrentPermissionsPermissionBuilder> {
		
		public GetCurrentPermissionsPermissionBuilder() {
			super(String.class, "permission", "getCurrentPermissions");
		}
	}

	/**
	 * Returns permission names as comma separated string
	 */
    public static GetCurrentPermissionsPermissionBuilder getCurrentPermissions()  {
		return new GetCurrentPermissionsPermissionBuilder();
	}
	
	public static class ListPermissionBuilder extends ListResponseRequestBuilder<Permission, Permission.Tokenizer, ListPermissionBuilder> {
		
		public ListPermissionBuilder(PermissionFilter filter) {
			super(Permission.class, "permission", "list");
			params.add("filter", filter);
		}
	}

	public static ListPermissionBuilder list()  {
		return list(null);
	}

	/**
	 * Retrieving permissions by identifiers, if filter is empty, returns all partner
	  permissions
	 * 
	 * @param filter Filter for permissions
	 */
    public static ListPermissionBuilder list(PermissionFilter filter)  {
		return new ListPermissionBuilder(filter);
	}
}
