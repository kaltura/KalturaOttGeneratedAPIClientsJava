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

import com.kaltura.client.types.LiveToVodFullConfiguration;
import com.kaltura.client.types.LiveToVodLinearAssetConfiguration;
import com.kaltura.client.types.LiveToVodPartnerConfiguration;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class LiveToVodService {
	
	public static class GetConfigurationLiveToVodBuilder extends RequestBuilder<LiveToVodFullConfiguration, LiveToVodFullConfiguration.Tokenizer, GetConfigurationLiveToVodBuilder> {
		
		public GetConfigurationLiveToVodBuilder() {
			super(LiveToVodFullConfiguration.class, "livetovod", "getConfiguration");
		}
	}

	/**
	 * Get existing L2V configuration for both the partner level and all channels
	  level.
	 */
    public static GetConfigurationLiveToVodBuilder getConfiguration()  {
		return new GetConfigurationLiveToVodBuilder();
	}
	
	public static class GetLinearAssetConfigurationLiveToVodBuilder extends RequestBuilder<LiveToVodLinearAssetConfiguration, LiveToVodLinearAssetConfiguration.Tokenizer, GetLinearAssetConfigurationLiveToVodBuilder> {
		
		public GetLinearAssetConfigurationLiveToVodBuilder(long linearAssetId) {
			super(LiveToVodLinearAssetConfiguration.class, "livetovod", "getLinearAssetConfiguration");
			params.add("linearAssetId", linearAssetId);
		}
		
		public void linearAssetId(String multirequestToken) {
			params.add("linearAssetId", multirequestToken);
		}
	}

	/**
	 * Get existing L2V configuration for a specific linear asset.
	 * 
	 * @param linearAssetId Linear asset's identifier.
	 */
    public static GetLinearAssetConfigurationLiveToVodBuilder getLinearAssetConfiguration(long linearAssetId)  {
		return new GetLinearAssetConfigurationLiveToVodBuilder(linearAssetId);
	}
	
	public static class GetPartnerConfigurationLiveToVodBuilder extends RequestBuilder<LiveToVodPartnerConfiguration, LiveToVodPartnerConfiguration.Tokenizer, GetPartnerConfigurationLiveToVodBuilder> {
		
		public GetPartnerConfigurationLiveToVodBuilder() {
			super(LiveToVodPartnerConfiguration.class, "livetovod", "getPartnerConfiguration");
		}
	}

	/**
	 * Get existing L2V partner configuration.
	 */
    public static GetPartnerConfigurationLiveToVodBuilder getPartnerConfiguration()  {
		return new GetPartnerConfigurationLiveToVodBuilder();
	}
	
	public static class UpdateLinearAssetConfigurationLiveToVodBuilder extends RequestBuilder<LiveToVodLinearAssetConfiguration, LiveToVodLinearAssetConfiguration.Tokenizer, UpdateLinearAssetConfigurationLiveToVodBuilder> {
		
		public UpdateLinearAssetConfigurationLiveToVodBuilder(LiveToVodLinearAssetConfiguration configuration) {
			super(LiveToVodLinearAssetConfiguration.class, "livetovod", "updateLinearAssetConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Set L2V configuration for a specific Linear channel.
	 * 
	 * @param configuration Live to VOD linear asset (live channel) configuration object.
	 */
    public static UpdateLinearAssetConfigurationLiveToVodBuilder updateLinearAssetConfiguration(LiveToVodLinearAssetConfiguration configuration)  {
		return new UpdateLinearAssetConfigurationLiveToVodBuilder(configuration);
	}
	
	public static class UpdatePartnerConfigurationLiveToVodBuilder extends RequestBuilder<LiveToVodPartnerConfiguration, LiveToVodPartnerConfiguration.Tokenizer, UpdatePartnerConfigurationLiveToVodBuilder> {
		
		public UpdatePartnerConfigurationLiveToVodBuilder(LiveToVodPartnerConfiguration configuration) {
			super(LiveToVodPartnerConfiguration.class, "livetovod", "updatePartnerConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Set L2V configuration on the partner level.
	 * 
	 * @param configuration Live to VOD configuration object.
	 */
    public static UpdatePartnerConfigurationLiveToVodBuilder updatePartnerConfiguration(LiveToVodPartnerConfiguration configuration)  {
		return new UpdatePartnerConfigurationLiveToVodBuilder(configuration);
	}
}
