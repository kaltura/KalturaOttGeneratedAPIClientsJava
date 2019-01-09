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

import com.kaltura.client.types.BusinessModuleRule;
import com.kaltura.client.types.BusinessModuleRuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class BusinessModuleRuleService {
	
	public static class AddBusinessModuleRuleBuilder extends RequestBuilder<BusinessModuleRule, BusinessModuleRule.Tokenizer, AddBusinessModuleRuleBuilder> {
		
		public AddBusinessModuleRuleBuilder(BusinessModuleRule businessModuleRule) {
			super(BusinessModuleRule.class, "businessmodulerule", "add");
			params.add("businessModuleRule", businessModuleRule);
		}
	}

	/**
	 * Add business module rule
	 * 
	 * @param businessModuleRule Business module rule
	 */
    public static AddBusinessModuleRuleBuilder add(BusinessModuleRule businessModuleRule)  {
		return new AddBusinessModuleRuleBuilder(businessModuleRule);
	}
	
	public static class DeleteBusinessModuleRuleBuilder extends NullRequestBuilder {
		
		public DeleteBusinessModuleRuleBuilder(long id) {
			super("businessmodulerule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete business module rule
	 * 
	 * @param id Business module rule ID
	 */
    public static DeleteBusinessModuleRuleBuilder delete(long id)  {
		return new DeleteBusinessModuleRuleBuilder(id);
	}
	
	public static class GetBusinessModuleRuleBuilder extends RequestBuilder<BusinessModuleRule, BusinessModuleRule.Tokenizer, GetBusinessModuleRuleBuilder> {
		
		public GetBusinessModuleRuleBuilder(long id) {
			super(BusinessModuleRule.class, "businessmodulerule", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get business module rule by ID
	 * 
	 * @param id ID to get
	 */
    public static GetBusinessModuleRuleBuilder get(long id)  {
		return new GetBusinessModuleRuleBuilder(id);
	}
	
	public static class ListBusinessModuleRuleBuilder extends ListResponseRequestBuilder<BusinessModuleRule, BusinessModuleRule.Tokenizer, ListBusinessModuleRuleBuilder> {
		
		public ListBusinessModuleRuleBuilder(BusinessModuleRuleFilter filter) {
			super(BusinessModuleRule.class, "businessmodulerule", "list");
			params.add("filter", filter);
		}
	}

	public static ListBusinessModuleRuleBuilder list()  {
		return list(null);
	}

	/**
	 * Get the list of business module rules for the partner
	 * 
	 * @param filter filter by condition name
	 */
    public static ListBusinessModuleRuleBuilder list(BusinessModuleRuleFilter filter)  {
		return new ListBusinessModuleRuleBuilder(filter);
	}
	
	public static class UpdateBusinessModuleRuleBuilder extends RequestBuilder<BusinessModuleRule, BusinessModuleRule.Tokenizer, UpdateBusinessModuleRuleBuilder> {
		
		public UpdateBusinessModuleRuleBuilder(long id, BusinessModuleRule businessModuleRule) {
			super(BusinessModuleRule.class, "businessmodulerule", "update");
			params.add("id", id);
			params.add("businessModuleRule", businessModuleRule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update business module rule
	 * 
	 * @param id Business module rule ID to update
	 * @param businessModuleRule Business module rule
	 */
    public static UpdateBusinessModuleRuleBuilder update(long id, BusinessModuleRule businessModuleRule)  {
		return new UpdateBusinessModuleRuleBuilder(id, businessModuleRule);
	}
}
