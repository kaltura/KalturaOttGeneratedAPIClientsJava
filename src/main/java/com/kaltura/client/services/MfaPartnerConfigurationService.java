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

import com.kaltura.client.types.MultifactorAuthenticationPartnerConfiguration;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class MfaPartnerConfigurationService {
	
	public static class GetMfaPartnerConfigurationBuilder extends RequestBuilder<MultifactorAuthenticationPartnerConfiguration, MultifactorAuthenticationPartnerConfiguration.Tokenizer, GetMfaPartnerConfigurationBuilder> {
		
		public GetMfaPartnerConfigurationBuilder() {
			super(MultifactorAuthenticationPartnerConfiguration.class, "mfapartnerconfiguration", "get");
		}
	}

	/**
	 * Get MFA partner configuration.
	 */
    public static GetMfaPartnerConfigurationBuilder get()  {
		return new GetMfaPartnerConfigurationBuilder();
	}
	
	public static class UpdateMfaPartnerConfigurationBuilder extends RequestBuilder<MultifactorAuthenticationPartnerConfiguration, MultifactorAuthenticationPartnerConfiguration.Tokenizer, UpdateMfaPartnerConfigurationBuilder> {
		
		public UpdateMfaPartnerConfigurationBuilder(MultifactorAuthenticationPartnerConfiguration configuration) {
			super(MultifactorAuthenticationPartnerConfiguration.class, "mfapartnerconfiguration", "update");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Update MFA partner configuration.
	 * 
	 * @param configuration MFA configuration
	 */
    public static UpdateMfaPartnerConfigurationBuilder update(MultifactorAuthenticationPartnerConfiguration configuration)  {
		return new UpdateMfaPartnerConfigurationBuilder(configuration);
	}
}
