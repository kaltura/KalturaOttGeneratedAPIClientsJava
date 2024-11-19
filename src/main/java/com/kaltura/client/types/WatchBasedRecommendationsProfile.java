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
@MultiRequestBuilder.Tokenizer(WatchBasedRecommendationsProfile.Tokenizer.class)
public class WatchBasedRecommendationsProfile extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String topicIds();
		String analysisMediaTypeIds();
		String userInterestPlayThresholdInPercentages();
		String numberOfInterests();
		String fallbackChannelId();
		String minPlaybacks();
		String maxPlaybacks();
		String allowedRecommendationsKsql();
		String playbackInterestsCalculationPeriodDays();
	}

	/**
	 * Unique identifier for the profile
	 */
	private Long id;
	/**
	 * Friendly name for the profile
	 */
	private String name;
	/**
	 * List of comma seperated topic ids considered for recommendations calculation.
	 */
	private String topicIds;
	/**
	 * List of comma seperated type ids considered for recommendations calculation.
	 */
	private String analysisMediaTypeIds;
	/**
	 * The minimum coverage in percentages that media is considered viewed.
	 */
	private Integer userInterestPlayThresholdInPercentages;
	/**
	 * The number of interests that will be selected per user.
	 */
	private Integer numberOfInterests;
	/**
	 * Reference to partner default recommendations (first 30 assets that are included
	  in the referred KalturaChannel).
	 */
	private Long fallbackChannelId;
	/**
	 * Minimum number of media assets that user shall watch to trigger user interests
	  calculation.
	 */
	private Integer minPlaybacks;
	/**
	 * Maximum number of assets that watched by a user and will be considered for
	  recommendations calculation (the last maxPlaybacks shall be used in the
	  analysis).
	 */
	private Integer maxPlaybacks;
	/**
	 * A kSql is used to filter the “user interests“ recommendations. Only asset
	  properties, metas, or tags are allowed ti be included in this ksql.
	 */
	private String allowedRecommendationsKsql;
	/**
	 * The number of days the user interests are considered to be up-to-date.
	 */
	private Integer playbackInterestsCalculationPeriodDays;

	// id:
	public Long getId(){
		return this.id;
	}
	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// topicIds:
	public String getTopicIds(){
		return this.topicIds;
	}
	public void setTopicIds(String topicIds){
		this.topicIds = topicIds;
	}

	public void topicIds(String multirequestToken){
		setToken("topicIds", multirequestToken);
	}

	// analysisMediaTypeIds:
	public String getAnalysisMediaTypeIds(){
		return this.analysisMediaTypeIds;
	}
	public void setAnalysisMediaTypeIds(String analysisMediaTypeIds){
		this.analysisMediaTypeIds = analysisMediaTypeIds;
	}

	public void analysisMediaTypeIds(String multirequestToken){
		setToken("analysisMediaTypeIds", multirequestToken);
	}

	// userInterestPlayThresholdInPercentages:
	public Integer getUserInterestPlayThresholdInPercentages(){
		return this.userInterestPlayThresholdInPercentages;
	}
	public void setUserInterestPlayThresholdInPercentages(Integer userInterestPlayThresholdInPercentages){
		this.userInterestPlayThresholdInPercentages = userInterestPlayThresholdInPercentages;
	}

	public void userInterestPlayThresholdInPercentages(String multirequestToken){
		setToken("userInterestPlayThresholdInPercentages", multirequestToken);
	}

	// numberOfInterests:
	public Integer getNumberOfInterests(){
		return this.numberOfInterests;
	}
	public void setNumberOfInterests(Integer numberOfInterests){
		this.numberOfInterests = numberOfInterests;
	}

	public void numberOfInterests(String multirequestToken){
		setToken("numberOfInterests", multirequestToken);
	}

	// fallbackChannelId:
	public Long getFallbackChannelId(){
		return this.fallbackChannelId;
	}
	public void setFallbackChannelId(Long fallbackChannelId){
		this.fallbackChannelId = fallbackChannelId;
	}

	public void fallbackChannelId(String multirequestToken){
		setToken("fallbackChannelId", multirequestToken);
	}

	// minPlaybacks:
	public Integer getMinPlaybacks(){
		return this.minPlaybacks;
	}
	public void setMinPlaybacks(Integer minPlaybacks){
		this.minPlaybacks = minPlaybacks;
	}

	public void minPlaybacks(String multirequestToken){
		setToken("minPlaybacks", multirequestToken);
	}

	// maxPlaybacks:
	public Integer getMaxPlaybacks(){
		return this.maxPlaybacks;
	}
	public void setMaxPlaybacks(Integer maxPlaybacks){
		this.maxPlaybacks = maxPlaybacks;
	}

	public void maxPlaybacks(String multirequestToken){
		setToken("maxPlaybacks", multirequestToken);
	}

	// allowedRecommendationsKsql:
	public String getAllowedRecommendationsKsql(){
		return this.allowedRecommendationsKsql;
	}
	public void setAllowedRecommendationsKsql(String allowedRecommendationsKsql){
		this.allowedRecommendationsKsql = allowedRecommendationsKsql;
	}

	public void allowedRecommendationsKsql(String multirequestToken){
		setToken("allowedRecommendationsKsql", multirequestToken);
	}

	// playbackInterestsCalculationPeriodDays:
	public Integer getPlaybackInterestsCalculationPeriodDays(){
		return this.playbackInterestsCalculationPeriodDays;
	}
	public void setPlaybackInterestsCalculationPeriodDays(Integer playbackInterestsCalculationPeriodDays){
		this.playbackInterestsCalculationPeriodDays = playbackInterestsCalculationPeriodDays;
	}

	public void playbackInterestsCalculationPeriodDays(String multirequestToken){
		setToken("playbackInterestsCalculationPeriodDays", multirequestToken);
	}


	public WatchBasedRecommendationsProfile() {
		super();
	}

	public WatchBasedRecommendationsProfile(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		topicIds = GsonParser.parseString(jsonObject.get("topicIds"));
		analysisMediaTypeIds = GsonParser.parseString(jsonObject.get("analysisMediaTypeIds"));
		userInterestPlayThresholdInPercentages = GsonParser.parseInt(jsonObject.get("userInterestPlayThresholdInPercentages"));
		numberOfInterests = GsonParser.parseInt(jsonObject.get("numberOfInterests"));
		fallbackChannelId = GsonParser.parseLong(jsonObject.get("fallbackChannelId"));
		minPlaybacks = GsonParser.parseInt(jsonObject.get("minPlaybacks"));
		maxPlaybacks = GsonParser.parseInt(jsonObject.get("maxPlaybacks"));
		allowedRecommendationsKsql = GsonParser.parseString(jsonObject.get("allowedRecommendationsKsql"));
		playbackInterestsCalculationPeriodDays = GsonParser.parseInt(jsonObject.get("playbackInterestsCalculationPeriodDays"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaWatchBasedRecommendationsProfile");
		kparams.add("name", this.name);
		kparams.add("topicIds", this.topicIds);
		kparams.add("analysisMediaTypeIds", this.analysisMediaTypeIds);
		kparams.add("userInterestPlayThresholdInPercentages", this.userInterestPlayThresholdInPercentages);
		kparams.add("numberOfInterests", this.numberOfInterests);
		kparams.add("fallbackChannelId", this.fallbackChannelId);
		kparams.add("minPlaybacks", this.minPlaybacks);
		kparams.add("maxPlaybacks", this.maxPlaybacks);
		kparams.add("allowedRecommendationsKsql", this.allowedRecommendationsKsql);
		kparams.add("playbackInterestsCalculationPeriodDays", this.playbackInterestsCalculationPeriodDays);
		return kparams;
	}

}

