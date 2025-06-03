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
// Copyright (C) 2006-2023  Kaltura Inc.
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

import com.kaltura.client.types.FilteringCondition;
import com.kaltura.client.types.SearchableAttributes;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SemanticAssetSearchPartnerConfigService {
	
	public static class GetFilteringConditionSemanticAssetSearchPartnerConfigBuilder extends RequestBuilder<FilteringCondition, FilteringCondition.Tokenizer, GetFilteringConditionSemanticAssetSearchPartnerConfigBuilder> {
		
		public GetFilteringConditionSemanticAssetSearchPartnerConfigBuilder() {
			super(FilteringCondition.class, "semanticassetsearchpartnerconfig", "getFilteringCondition");
		}
	}

	/**
	 * Retrieve the filtering condition configuration for the partner.
	 */
    public static GetFilteringConditionSemanticAssetSearchPartnerConfigBuilder getFilteringCondition()  {
		return new GetFilteringConditionSemanticAssetSearchPartnerConfigBuilder();
	}
	
	public static class GetSearchableAttributesSemanticAssetSearchPartnerConfigBuilder extends RequestBuilder<SearchableAttributes, SearchableAttributes.Tokenizer, GetSearchableAttributesSemanticAssetSearchPartnerConfigBuilder> {
		
		public GetSearchableAttributesSemanticAssetSearchPartnerConfigBuilder(int assetStructId) {
			super(SearchableAttributes.class, "semanticassetsearchpartnerconfig", "getSearchableAttributes");
			params.add("assetStructId", assetStructId);
		}
		
		public void assetStructId(String multirequestToken) {
			params.add("assetStructId", multirequestToken);
		}
	}

	/**
	 * Retrieve the current field configurations for semantic search.
	 * 
	 * @param assetStructId Asset structure ID to filter configurations.
	 */
    public static GetSearchableAttributesSemanticAssetSearchPartnerConfigBuilder getSearchableAttributes(int assetStructId)  {
		return new GetSearchableAttributesSemanticAssetSearchPartnerConfigBuilder(assetStructId);
	}
	
	public static class UpsertFilteringConditionSemanticAssetSearchPartnerConfigBuilder extends RequestBuilder<FilteringCondition, FilteringCondition.Tokenizer, UpsertFilteringConditionSemanticAssetSearchPartnerConfigBuilder> {
		
		public UpsertFilteringConditionSemanticAssetSearchPartnerConfigBuilder(FilteringCondition filteringCondition) {
			super(FilteringCondition.class, "semanticassetsearchpartnerconfig", "upsertFilteringCondition");
			params.add("filteringCondition", filteringCondition);
		}
	}

	/**
	 * Update rule that controls embedding generation and search behavior.
	 * 
	 * @param filteringCondition Rule configuration parameters.
	 */
    public static UpsertFilteringConditionSemanticAssetSearchPartnerConfigBuilder upsertFilteringCondition(FilteringCondition filteringCondition)  {
		return new UpsertFilteringConditionSemanticAssetSearchPartnerConfigBuilder(filteringCondition);
	}
	
	public static class UpsertSearchableAttributesSemanticAssetSearchPartnerConfigBuilder extends RequestBuilder<SearchableAttributes, SearchableAttributes.Tokenizer, UpsertSearchableAttributesSemanticAssetSearchPartnerConfigBuilder> {
		
		public UpsertSearchableAttributesSemanticAssetSearchPartnerConfigBuilder(SearchableAttributes attributes) {
			super(SearchableAttributes.class, "semanticassetsearchpartnerconfig", "upsertSearchableAttributes");
			params.add("attributes", attributes);
		}
	}

	/**
	 * Update which fields should be included in semantic search for specific asset
	  types.
	 * 
	 * @param attributes Fields configuration parameters.
	 */
    public static UpsertSearchableAttributesSemanticAssetSearchPartnerConfigBuilder upsertSearchableAttributes(SearchableAttributes attributes)  {
		return new UpsertSearchableAttributesSemanticAssetSearchPartnerConfigBuilder(attributes);
	}
}
