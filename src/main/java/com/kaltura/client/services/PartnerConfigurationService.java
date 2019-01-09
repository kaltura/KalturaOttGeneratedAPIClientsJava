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

import com.kaltura.client.types.PartnerConfiguration;
import com.kaltura.client.types.PartnerConfigurationFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PartnerConfigurationService {
	
	public static class ListPartnerConfigurationBuilder extends ListResponseRequestBuilder<PartnerConfiguration, PartnerConfiguration.Tokenizer, ListPartnerConfigurationBuilder> {
		
		public ListPartnerConfigurationBuilder(PartnerConfigurationFilter filter) {
			super(PartnerConfiguration.class, "partnerconfiguration", "list");
			params.add("filter", filter);
		}
	}

	/**
	 * Get the list of PartnerConfiguration
	 * 
	 * @param filter filter by PartnerConfiguration type
	 */
    public static ListPartnerConfigurationBuilder list(PartnerConfigurationFilter filter)  {
		return new ListPartnerConfigurationBuilder(filter);
	}
	
	public static class UpdatePartnerConfigurationBuilder extends RequestBuilder<Boolean, String, UpdatePartnerConfigurationBuilder> {
		
		public UpdatePartnerConfigurationBuilder(PartnerConfiguration configuration) {
			super(Boolean.class, "partnerconfiguration", "update");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Update Partner Configuration
	 * 
	 * @param configuration Partner Configuration
	 *             possible configuration type: 
	 *             'configuration': { 'value': 0, 'partner_configuration_type': {
	 * 'type': 'OSSAdapter', 'objectType': 'KalturaPartnerConfigurationHolder' },
	 *             'objectType': 'KalturaBillingPartnerConfig'}
	 */
    public static UpdatePartnerConfigurationBuilder update(PartnerConfiguration configuration)  {
		return new UpdatePartnerConfigurationBuilder(configuration);
	}
}
