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

import com.kaltura.client.types.SemanticQueryPartnerConfiguration;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SemanticQueryPartnerConfigurationService {
	
	public static class GetSemanticQueryPartnerConfigurationBuilder extends RequestBuilder<SemanticQueryPartnerConfiguration, SemanticQueryPartnerConfiguration.Tokenizer, GetSemanticQueryPartnerConfigurationBuilder> {
		
		public GetSemanticQueryPartnerConfigurationBuilder() {
			super(SemanticQueryPartnerConfiguration.class, "semanticquerypartnerconfiguration", "get");
		}
	}

	/**
	 * Retrieves the current partner configuration for semantic query.
	 */
    public static GetSemanticQueryPartnerConfigurationBuilder get()  {
		return new GetSemanticQueryPartnerConfigurationBuilder();
	}
	
	public static class UpdateSemanticQueryPartnerConfigurationBuilder extends RequestBuilder<SemanticQueryPartnerConfiguration, SemanticQueryPartnerConfiguration.Tokenizer, UpdateSemanticQueryPartnerConfigurationBuilder> {
		
		public UpdateSemanticQueryPartnerConfigurationBuilder(SemanticQueryPartnerConfiguration configuration) {
			super(SemanticQueryPartnerConfiguration.class, "semanticquerypartnerconfiguration", "update");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Updates the partner configuration for semantic query.
	 * 
	 * @param configuration The configuration parameters for semantic query generation.
	 */
    public static UpdateSemanticQueryPartnerConfigurationBuilder update(SemanticQueryPartnerConfiguration configuration)  {
		return new UpdateSemanticQueryPartnerConfigurationBuilder(configuration);
	}
}
