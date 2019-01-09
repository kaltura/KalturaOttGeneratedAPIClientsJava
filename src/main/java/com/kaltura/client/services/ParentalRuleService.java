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

import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.types.ParentalRule;
import com.kaltura.client.types.ParentalRuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ParentalRuleService {
	
	public static class AddParentalRuleBuilder extends RequestBuilder<ParentalRule, ParentalRule.Tokenizer, AddParentalRuleBuilder> {
		
		public AddParentalRuleBuilder(ParentalRule parentalRule) {
			super(ParentalRule.class, "parentalrule", "add");
			params.add("parentalRule", parentalRule);
		}
	}

	/**
	 * Add a new parentalRule
	 * 
	 * @param parentalRule parentalRule object
	 */
    public static AddParentalRuleBuilder add(ParentalRule parentalRule)  {
		return new AddParentalRuleBuilder(parentalRule);
	}
	
	public static class DeleteParentalRuleBuilder extends RequestBuilder<Boolean, String, DeleteParentalRuleBuilder> {
		
		public DeleteParentalRuleBuilder(long id) {
			super(Boolean.class, "parentalrule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing parentalRule
	 * 
	 * @param id parentalRule identifier
	 */
    public static DeleteParentalRuleBuilder delete(long id)  {
		return new DeleteParentalRuleBuilder(id);
	}
	
	public static class DisableParentalRuleBuilder extends RequestBuilder<Boolean, String, DisableParentalRuleBuilder> {
		
		public DisableParentalRuleBuilder(long ruleId, EntityReferenceBy entityReference) {
			super(Boolean.class, "parentalrule", "disable");
			params.add("ruleId", ruleId);
			params.add("entityReference", entityReference);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
		
		public void entityReference(String multirequestToken) {
			params.add("entityReference", multirequestToken);
		}
	}

	/**
	 * Disables a parental rule that was previously defined by the household master.
	  Disable can be at specific user or household level.
	 * 
	 * @param ruleId Rule Identifier
	 * @param entityReference Reference type to filter by
	 */
    public static DisableParentalRuleBuilder disable(long ruleId, EntityReferenceBy entityReference)  {
		return new DisableParentalRuleBuilder(ruleId, entityReference);
	}
	
	public static class DisableDefaultParentalRuleBuilder extends RequestBuilder<Boolean, String, DisableDefaultParentalRuleBuilder> {
		
		public DisableDefaultParentalRuleBuilder(EntityReferenceBy entityReference) {
			super(Boolean.class, "parentalrule", "disableDefault");
			params.add("entityReference", entityReference);
		}
		
		public void entityReference(String multirequestToken) {
			params.add("entityReference", multirequestToken);
		}
	}

	/**
	 * Disables a parental rule that was defined at account level. Disable can be at
	  specific user or household level.
	 * 
	 * @param entityReference Reference type to filter by
	 */
    public static DisableDefaultParentalRuleBuilder disableDefault(EntityReferenceBy entityReference)  {
		return new DisableDefaultParentalRuleBuilder(entityReference);
	}
	
	public static class EnableParentalRuleBuilder extends RequestBuilder<Boolean, String, EnableParentalRuleBuilder> {
		
		public EnableParentalRuleBuilder(long ruleId, EntityReferenceBy entityReference) {
			super(Boolean.class, "parentalrule", "enable");
			params.add("ruleId", ruleId);
			params.add("entityReference", entityReference);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
		
		public void entityReference(String multirequestToken) {
			params.add("entityReference", multirequestToken);
		}
	}

	/**
	 * Enable a parental rules for a user
	 * 
	 * @param ruleId Rule Identifier
	 * @param entityReference Reference type to filter by
	 */
    public static EnableParentalRuleBuilder enable(long ruleId, EntityReferenceBy entityReference)  {
		return new EnableParentalRuleBuilder(ruleId, entityReference);
	}
	
	public static class GetParentalRuleBuilder extends RequestBuilder<ParentalRule, ParentalRule.Tokenizer, GetParentalRuleBuilder> {
		
		public GetParentalRuleBuilder(long id) {
			super(ParentalRule.class, "parentalrule", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get an existing parentalRule by identifier
	 * 
	 * @param id parentalRule identifier
	 */
    public static GetParentalRuleBuilder get(long id)  {
		return new GetParentalRuleBuilder(id);
	}
	
	public static class ListParentalRuleBuilder extends ListResponseRequestBuilder<ParentalRule, ParentalRule.Tokenizer, ListParentalRuleBuilder> {
		
		public ListParentalRuleBuilder(ParentalRuleFilter filter) {
			super(ParentalRule.class, "parentalrule", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Return the parental rules that applies for the user or household. Can include
	  rules that have been associated in account, household, or user level.           
	    Association level is also specified in the response.
	 * 
	 * @param filter Filter
	 */
    public static ListParentalRuleBuilder list(ParentalRuleFilter filter)  {
		return new ListParentalRuleBuilder(filter);
	}
	
	public static class UpdateParentalRuleBuilder extends RequestBuilder<ParentalRule, ParentalRule.Tokenizer, UpdateParentalRuleBuilder> {
		
		public UpdateParentalRuleBuilder(long id, ParentalRule parentalRule) {
			super(ParentalRule.class, "parentalrule", "update");
			params.add("id", id);
			params.add("parentalRule", parentalRule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing parentalRule
	 * 
	 * @param id parentalRule identifier
	 * @param parentalRule parentalRule object
	 */
    public static UpdateParentalRuleBuilder update(long id, ParentalRule parentalRule)  {
		return new UpdateParentalRuleBuilder(id, parentalRule);
	}
}
