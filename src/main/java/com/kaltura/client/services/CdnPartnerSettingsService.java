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

import com.kaltura.client.types.CDNPartnerSettings;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CdnPartnerSettingsService {
	
	public static class GetCdnPartnerSettingsBuilder extends RequestBuilder<CDNPartnerSettings, CDNPartnerSettings.Tokenizer, GetCdnPartnerSettingsBuilder> {
		
		public GetCdnPartnerSettingsBuilder() {
			super(CDNPartnerSettings.class, "cdnpartnersettings", "get");
		}
	}

	/**
	 * Retrieve the partner’s CDN settings (default adapters)
	 */
    public static GetCdnPartnerSettingsBuilder get()  {
		return new GetCdnPartnerSettingsBuilder();
	}
	
	public static class UpdateCdnPartnerSettingsBuilder extends RequestBuilder<CDNPartnerSettings, CDNPartnerSettings.Tokenizer, UpdateCdnPartnerSettingsBuilder> {
		
		public UpdateCdnPartnerSettingsBuilder(CDNPartnerSettings settings) {
			super(CDNPartnerSettings.class, "cdnpartnersettings", "update");
			params.add("settings", settings);
		}
	}

	/**
	 * Configure the partner’s CDN settings (default adapters)
	 * 
	 * @param settings CDN partner settings
	 */
    public static UpdateCdnPartnerSettingsBuilder update(CDNPartnerSettings settings)  {
		return new UpdateCdnPartnerSettingsBuilder(settings);
	}
}
