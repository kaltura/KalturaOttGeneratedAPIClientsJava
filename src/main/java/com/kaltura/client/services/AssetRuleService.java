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

import com.kaltura.client.types.AssetRule;
import com.kaltura.client.types.AssetRuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetRuleService {
	
	public static class AddAssetRuleBuilder extends RequestBuilder<AssetRule, AssetRule.Tokenizer, AddAssetRuleBuilder> {
		
		public AddAssetRuleBuilder(AssetRule assetRule) {
			super(AssetRule.class, "assetrule", "add");
			params.add("assetRule", assetRule);
		}
	}

	/**
	 * Add asset rule
	 * 
	 * @param assetRule Asset rule
	 */
    public static AddAssetRuleBuilder add(AssetRule assetRule)  {
		return new AddAssetRuleBuilder(assetRule);
	}
	
	public static class DeleteAssetRuleBuilder extends RequestBuilder<Boolean, String, DeleteAssetRuleBuilder> {
		
		public DeleteAssetRuleBuilder(long id) {
			super(Boolean.class, "assetrule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete asset rule
	 * 
	 * @param id Asset rule ID
	 */
    public static DeleteAssetRuleBuilder delete(long id)  {
		return new DeleteAssetRuleBuilder(id);
	}
	
	public static class ListAssetRuleBuilder extends ListResponseRequestBuilder<AssetRule, AssetRule.Tokenizer, ListAssetRuleBuilder> {
		
		public ListAssetRuleBuilder(AssetRuleFilter filter) {
			super(AssetRule.class, "assetrule", "list");
			params.add("filter", filter);
		}
	}

	public static ListAssetRuleBuilder list()  {
		return list(null);
	}

	/**
	 * Get the list of asset rules for the partner
	 * 
	 * @param filter filter by condition name
	 */
    public static ListAssetRuleBuilder list(AssetRuleFilter filter)  {
		return new ListAssetRuleBuilder(filter);
	}
	
	public static class UpdateAssetRuleBuilder extends RequestBuilder<AssetRule, AssetRule.Tokenizer, UpdateAssetRuleBuilder> {
		
		public UpdateAssetRuleBuilder(long id, AssetRule assetRule) {
			super(AssetRule.class, "assetrule", "update");
			params.add("id", id);
			params.add("assetRule", assetRule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update asset rule
	 * 
	 * @param id Asset rule ID to update
	 * @param assetRule Asset rule
	 */
    public static UpdateAssetRuleBuilder update(long id, AssetRule assetRule)  {
		return new UpdateAssetRuleBuilder(id, assetRule);
	}
}
