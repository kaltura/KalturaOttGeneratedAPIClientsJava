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
package com.kaltura.client.types;

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(WatchBasedRecommendationsAdminConfiguration.Tokenizer.class)
public class WatchBasedRecommendationsAdminConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String maxProfiles();
		String activeUserDurationDays();
		String recommendationsCachingTimeDays();
	}

	/**
	 * The maximum number of profiles.
	 */
	private Integer maxProfiles;
	/**
	 * The duration that a user is considered active after his last playback.
	 */
	private Integer activeUserDurationDays;
	/**
	 * The number of days the recommendations will be cached.
	 */
	private Integer recommendationsCachingTimeDays;

	// maxProfiles:
	public Integer getMaxProfiles(){
		return this.maxProfiles;
	}
	public void setMaxProfiles(Integer maxProfiles){
		this.maxProfiles = maxProfiles;
	}

	public void maxProfiles(String multirequestToken){
		setToken("maxProfiles", multirequestToken);
	}

	// activeUserDurationDays:
	public Integer getActiveUserDurationDays(){
		return this.activeUserDurationDays;
	}
	public void setActiveUserDurationDays(Integer activeUserDurationDays){
		this.activeUserDurationDays = activeUserDurationDays;
	}

	public void activeUserDurationDays(String multirequestToken){
		setToken("activeUserDurationDays", multirequestToken);
	}

	// recommendationsCachingTimeDays:
	public Integer getRecommendationsCachingTimeDays(){
		return this.recommendationsCachingTimeDays;
	}
	public void setRecommendationsCachingTimeDays(Integer recommendationsCachingTimeDays){
		this.recommendationsCachingTimeDays = recommendationsCachingTimeDays;
	}

	public void recommendationsCachingTimeDays(String multirequestToken){
		setToken("recommendationsCachingTimeDays", multirequestToken);
	}


	public WatchBasedRecommendationsAdminConfiguration() {
		super();
	}

	public WatchBasedRecommendationsAdminConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		maxProfiles = GsonParser.parseInt(jsonObject.get("maxProfiles"));
		activeUserDurationDays = GsonParser.parseInt(jsonObject.get("activeUserDurationDays"));
		recommendationsCachingTimeDays = GsonParser.parseInt(jsonObject.get("recommendationsCachingTimeDays"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaWatchBasedRecommendationsAdminConfiguration");
		kparams.add("maxProfiles", this.maxProfiles);
		kparams.add("activeUserDurationDays", this.activeUserDurationDays);
		kparams.add("recommendationsCachingTimeDays", this.recommendationsCachingTimeDays);
		return kparams;
	}

}

