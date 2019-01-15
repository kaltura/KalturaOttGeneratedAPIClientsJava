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

import com.kaltura.client.types.AssetUserRule;
import com.kaltura.client.types.AssetUserRuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetUserRuleService {
	
	public static class AddAssetUserRuleBuilder extends RequestBuilder<AssetUserRule, AssetUserRule.Tokenizer, AddAssetUserRuleBuilder> {
		
		public AddAssetUserRuleBuilder(AssetUserRule assetUserRule) {
			super(AssetUserRule.class, "assetuserrule", "add");
			params.add("assetUserRule", assetUserRule);
		}
	}

	/**
	 * Add asset user rule
	 * 
	 * @param assetUserRule Asset user rule
	 */
    public static AddAssetUserRuleBuilder add(AssetUserRule assetUserRule)  {
		return new AddAssetUserRuleBuilder(assetUserRule);
	}
	
	public static class AttachUserAssetUserRuleBuilder extends NullRequestBuilder {
		
		public AttachUserAssetUserRuleBuilder(long ruleId) {
			super("assetuserrule", "attachUser");
			params.add("ruleId", ruleId);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
	}

	/**
	 * Attach AssetUserRule To User
	 * 
	 * @param ruleId AssetUserRule id to add
	 */
    public static AttachUserAssetUserRuleBuilder attachUser(long ruleId)  {
		return new AttachUserAssetUserRuleBuilder(ruleId);
	}
	
	public static class DeleteAssetUserRuleBuilder extends NullRequestBuilder {
		
		public DeleteAssetUserRuleBuilder(long id) {
			super("assetuserrule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete asset user rule
	 * 
	 * @param id Asset user rule ID
	 */
    public static DeleteAssetUserRuleBuilder delete(long id)  {
		return new DeleteAssetUserRuleBuilder(id);
	}
	
	public static class DetachUserAssetUserRuleBuilder extends NullRequestBuilder {
		
		public DetachUserAssetUserRuleBuilder(long ruleId) {
			super("assetuserrule", "detachUser");
			params.add("ruleId", ruleId);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
	}

	/**
	 * Detach AssetUserRule from user
	 * 
	 * @param ruleId AssetUserRule id to remove
	 */
    public static DetachUserAssetUserRuleBuilder detachUser(long ruleId)  {
		return new DetachUserAssetUserRuleBuilder(ruleId);
	}
	
	public static class ListAssetUserRuleBuilder extends ListResponseRequestBuilder<AssetUserRule, AssetUserRule.Tokenizer, ListAssetUserRuleBuilder> {
		
		public ListAssetUserRuleBuilder(AssetUserRuleFilter filter) {
			super(AssetUserRule.class, "assetuserrule", "list");
			params.add("filter", filter);
		}
	}

	public static ListAssetUserRuleBuilder list()  {
		return list(null);
	}

	/**
	 * Get the list of asset user rules for the partner
	 * 
	 * @param filter AssetUserRule Filter
	 */
    public static ListAssetUserRuleBuilder list(AssetUserRuleFilter filter)  {
		return new ListAssetUserRuleBuilder(filter);
	}
	
	public static class UpdateAssetUserRuleBuilder extends RequestBuilder<AssetUserRule, AssetUserRule.Tokenizer, UpdateAssetUserRuleBuilder> {
		
		public UpdateAssetUserRuleBuilder(long id, AssetUserRule assetUserRule) {
			super(AssetUserRule.class, "assetuserrule", "update");
			params.add("id", id);
			params.add("assetUserRule", assetUserRule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update asset user rule
	 * 
	 * @param id Asset user rule ID to update
	 * @param assetUserRule Asset user rule
	 */
    public static UpdateAssetUserRuleBuilder update(long id, AssetUserRule assetUserRule)  {
		return new UpdateAssetUserRuleBuilder(id, assetUserRule);
	}
}
