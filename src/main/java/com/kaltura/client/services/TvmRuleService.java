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

import com.kaltura.client.types.TvmRule;
import com.kaltura.client.types.TvmRuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TvmRuleService {
	
	public static class ListTvmRuleBuilder extends ListResponseRequestBuilder<TvmRule, TvmRule.Tokenizer, ListTvmRuleBuilder> {
		
		public ListTvmRuleBuilder(TvmRuleFilter filter) {
			super(TvmRule.class, "tvmrule", "list");
			params.add("filter", filter);
		}
	}

	public static ListTvmRuleBuilder list()  {
		return list(null);
	}

	/**
	 * Get the list of tvm rules for the partner
	 * 
	 * @param filter TvmRuleFilter Filter
	 */
    public static ListTvmRuleBuilder list(TvmRuleFilter filter)  {
		return new ListTvmRuleBuilder(filter);
	}
}
