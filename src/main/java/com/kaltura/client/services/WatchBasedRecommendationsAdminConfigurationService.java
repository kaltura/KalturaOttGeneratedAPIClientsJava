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

import com.kaltura.client.types.WatchBasedRecommendationsAdminConfiguration;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class WatchBasedRecommendationsAdminConfigurationService {
	
	public static class GetWatchBasedRecommendationsAdminConfigurationBuilder extends RequestBuilder<WatchBasedRecommendationsAdminConfiguration, WatchBasedRecommendationsAdminConfiguration.Tokenizer, GetWatchBasedRecommendationsAdminConfigurationBuilder> {
		
		public GetWatchBasedRecommendationsAdminConfigurationBuilder() {
			super(WatchBasedRecommendationsAdminConfiguration.class, "watchbasedrecommendationsadminconfiguration", "get");
		}
	}

	/**
	 * Get partner&amp;#39;s watch based recommendations admin configuration.
	 */
    public static GetWatchBasedRecommendationsAdminConfigurationBuilder get()  {
		return new GetWatchBasedRecommendationsAdminConfigurationBuilder();
	}
	
	public static class UpdateWatchBasedRecommendationsAdminConfigurationBuilder extends RequestBuilder<WatchBasedRecommendationsAdminConfiguration, WatchBasedRecommendationsAdminConfiguration.Tokenizer, UpdateWatchBasedRecommendationsAdminConfigurationBuilder> {
		
		public UpdateWatchBasedRecommendationsAdminConfigurationBuilder(WatchBasedRecommendationsAdminConfiguration configuration) {
			super(WatchBasedRecommendationsAdminConfiguration.class, "watchbasedrecommendationsadminconfiguration", "update");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Updates partner&amp;#39;s watch based recommendations admin configuration.
	 * 
	 * @param configuration watch based recommendations admin configuration
	 */
    public static UpdateWatchBasedRecommendationsAdminConfigurationBuilder update(WatchBasedRecommendationsAdminConfiguration configuration)  {
		return new UpdateWatchBasedRecommendationsAdminConfigurationBuilder(configuration);
	}
}
