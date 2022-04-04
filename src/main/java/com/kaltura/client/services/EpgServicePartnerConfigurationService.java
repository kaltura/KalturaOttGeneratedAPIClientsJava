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
// Copyright (C) 2006-2022  Kaltura Inc.
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

import com.kaltura.client.types.EpgServicePartnerConfiguration;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EpgServicePartnerConfigurationService {
	
	public static class GetEpgServicePartnerConfigurationBuilder extends RequestBuilder<EpgServicePartnerConfiguration, EpgServicePartnerConfiguration.Tokenizer, GetEpgServicePartnerConfigurationBuilder> {
		
		public GetEpgServicePartnerConfigurationBuilder() {
			super(EpgServicePartnerConfiguration.class, "epgservicepartnerconfiguration", "get");
		}
	}

	/**
	 * Returns EPG cache service partner configurations
	 */
    public static GetEpgServicePartnerConfigurationBuilder get()  {
		return new GetEpgServicePartnerConfigurationBuilder();
	}
	
	public static class UpdateEpgServicePartnerConfigurationBuilder extends NullRequestBuilder {
		
		public UpdateEpgServicePartnerConfigurationBuilder(EpgServicePartnerConfiguration config) {
			super("epgservicepartnerconfiguration", "update");
			params.add("config", config);
		}
	}

	/**
	 * Returns EPG cache service partner configurations
	 * 
	 * @param config the partner config updates
	 */
    public static UpdateEpgServicePartnerConfigurationBuilder update(EpgServicePartnerConfiguration config)  {
		return new UpdateEpgServicePartnerConfigurationBuilder(config);
	}
}
