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

import com.kaltura.client.types.GenerateSemanticQuery;
import com.kaltura.client.types.SemanticQuery;
import com.kaltura.client.types.SemanticQueryPartnerConfiguration;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SemanticQueryService {
	
	public static class GenerateSemanticQueryBuilder extends RequestBuilder<SemanticQuery, SemanticQuery.Tokenizer, GenerateSemanticQueryBuilder> {
		
		public GenerateSemanticQueryBuilder(GenerateSemanticQuery query) {
			super(SemanticQuery.class, "semanticquery", "generate");
			params.add("query", query);
		}
	}

	/**
	 * Generates semantic queries based on the input query.
	 * 
	 * @param query The semantic query generation request containing the input query text.
	 */
    public static GenerateSemanticQueryBuilder generate(GenerateSemanticQuery query)  {
		return new GenerateSemanticQueryBuilder(query);
	}
	
	public static class GetPartnerConfigurationSemanticQueryBuilder extends RequestBuilder<SemanticQueryPartnerConfiguration, SemanticQueryPartnerConfiguration.Tokenizer, GetPartnerConfigurationSemanticQueryBuilder> {
		
		public GetPartnerConfigurationSemanticQueryBuilder() {
			super(SemanticQueryPartnerConfiguration.class, "semanticquery", "getPartnerConfiguration");
		}
	}

	/**
	 * Retrieves the current partner configuration for semantic query.
	 */
    public static GetPartnerConfigurationSemanticQueryBuilder getPartnerConfiguration()  {
		return new GetPartnerConfigurationSemanticQueryBuilder();
	}
	
	public static class UpdatePartnerConfigurationSemanticQueryBuilder extends RequestBuilder<SemanticQueryPartnerConfiguration, SemanticQueryPartnerConfiguration.Tokenizer, UpdatePartnerConfigurationSemanticQueryBuilder> {
		
		public UpdatePartnerConfigurationSemanticQueryBuilder(SemanticQueryPartnerConfiguration configuration) {
			super(SemanticQueryPartnerConfiguration.class, "semanticquery", "updatePartnerConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Updates the partner configuration for semantic query.
	 * 
	 * @param configuration The configuration parameters for semantic query generation.
	 */
    public static UpdatePartnerConfigurationSemanticQueryBuilder updatePartnerConfiguration(SemanticQueryPartnerConfiguration configuration)  {
		return new UpdatePartnerConfigurationSemanticQueryBuilder(configuration);
	}
}
