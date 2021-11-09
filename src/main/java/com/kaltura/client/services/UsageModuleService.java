// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
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

import com.kaltura.client.types.UsageModule;
import com.kaltura.client.types.UsageModuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UsageModuleService {
	
	public static class AddUsageModuleBuilder extends RequestBuilder<UsageModule, UsageModule.Tokenizer, AddUsageModuleBuilder> {
		
		public AddUsageModuleBuilder(UsageModule usageModule) {
			super(UsageModule.class, "usagemodule", "add");
			params.add("usageModule", usageModule);
		}
	}

	/**
	 * Insert new UsageModule
	 * 
	 * @param usageModule usage module Object
	 */
    public static AddUsageModuleBuilder add(UsageModule usageModule)  {
		return new AddUsageModuleBuilder(usageModule);
	}
	
	public static class DeleteUsageModuleBuilder extends RequestBuilder<Boolean, String, DeleteUsageModuleBuilder> {
		
		public DeleteUsageModuleBuilder(long id) {
			super(Boolean.class, "usagemodule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete UsageModule
	 * 
	 * @param id UsageModule id
	 */
    public static DeleteUsageModuleBuilder delete(long id)  {
		return new DeleteUsageModuleBuilder(id);
	}
	
	public static class ListUsageModuleBuilder extends ListResponseRequestBuilder<UsageModule, UsageModule.Tokenizer, ListUsageModuleBuilder> {
		
		public ListUsageModuleBuilder(UsageModuleFilter filter) {
			super(UsageModule.class, "usagemodule", "list");
			params.add("filter", filter);
		}
	}

	public static ListUsageModuleBuilder list()  {
		return list(null);
	}

	/**
	 * Returns the list of available usage module
	 * 
	 * @param filter Filter request
	 */
    public static ListUsageModuleBuilder list(UsageModuleFilter filter)  {
		return new ListUsageModuleBuilder(filter);
	}
	
	public static class UpdateUsageModuleBuilder extends RequestBuilder<UsageModule, UsageModule.Tokenizer, UpdateUsageModuleBuilder> {
		
		public UpdateUsageModuleBuilder(int id, UsageModule usageModule) {
			super(UsageModule.class, "usagemodule", "update");
			params.add("id", id);
			params.add("usageModule", usageModule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update usage module
	 * 
	 * @param id usage module id
	 * @param usageModule usage module Object
	 */
    public static UpdateUsageModuleBuilder update(int id, UsageModule usageModule)  {
		return new UpdateUsageModuleBuilder(id, usageModule);
	}
}
