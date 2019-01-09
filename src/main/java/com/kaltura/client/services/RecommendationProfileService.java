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

import com.kaltura.client.types.RecommendationProfile;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class RecommendationProfileService {
	
	public static class AddRecommendationProfileBuilder extends RequestBuilder<RecommendationProfile, RecommendationProfile.Tokenizer, AddRecommendationProfileBuilder> {
		
		public AddRecommendationProfileBuilder(RecommendationProfile recommendationEngine) {
			super(RecommendationProfile.class, "recommendationprofile", "add");
			params.add("recommendationEngine", recommendationEngine);
		}
	}

	/**
	 * Insert new recommendation engine for partner
	 * 
	 * @param recommendationEngine recommendation engine Object
	 */
    public static AddRecommendationProfileBuilder add(RecommendationProfile recommendationEngine)  {
		return new AddRecommendationProfileBuilder(recommendationEngine);
	}
	
	public static class DeleteRecommendationProfileBuilder extends RequestBuilder<Boolean, String, DeleteRecommendationProfileBuilder> {
		
		public DeleteRecommendationProfileBuilder(int id) {
			super(Boolean.class, "recommendationprofile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete recommendation engine by recommendation engine id
	 * 
	 * @param id recommendation engine Identifier
	 */
    public static DeleteRecommendationProfileBuilder delete(int id)  {
		return new DeleteRecommendationProfileBuilder(id);
	}
	
	public static class GenerateSharedSecretRecommendationProfileBuilder extends RequestBuilder<RecommendationProfile, RecommendationProfile.Tokenizer, GenerateSharedSecretRecommendationProfileBuilder> {
		
		public GenerateSharedSecretRecommendationProfileBuilder(int recommendationEngineId) {
			super(RecommendationProfile.class, "recommendationprofile", "generateSharedSecret");
			params.add("recommendationEngineId", recommendationEngineId);
		}
		
		public void recommendationEngineId(String multirequestToken) {
			params.add("recommendationEngineId", multirequestToken);
		}
	}

	/**
	 * Generate recommendation engine  shared secret
	 * 
	 * @param recommendationEngineId recommendation engine Identifier
	 */
    public static GenerateSharedSecretRecommendationProfileBuilder generateSharedSecret(int recommendationEngineId)  {
		return new GenerateSharedSecretRecommendationProfileBuilder(recommendationEngineId);
	}
	
	public static class ListRecommendationProfileBuilder extends ListResponseRequestBuilder<RecommendationProfile, RecommendationProfile.Tokenizer, ListRecommendationProfileBuilder> {
		
		public ListRecommendationProfileBuilder() {
			super(RecommendationProfile.class, "recommendationprofile", "list");
		}
	}

	/**
	 * Returns all recommendation engines for partner
	 */
    public static ListRecommendationProfileBuilder list()  {
		return new ListRecommendationProfileBuilder();
	}
	
	public static class UpdateRecommendationProfileBuilder extends RequestBuilder<RecommendationProfile, RecommendationProfile.Tokenizer, UpdateRecommendationProfileBuilder> {
		
		public UpdateRecommendationProfileBuilder(int recommendationEngineId, RecommendationProfile recommendationEngine) {
			super(RecommendationProfile.class, "recommendationprofile", "update");
			params.add("recommendationEngineId", recommendationEngineId);
			params.add("recommendationEngine", recommendationEngine);
		}
		
		public void recommendationEngineId(String multirequestToken) {
			params.add("recommendationEngineId", multirequestToken);
		}
	}

	/**
	 * Update recommendation engine details
	 * 
	 * @param recommendationEngineId recommendation engine identifier
	 * @param recommendationEngine recommendation engine Object
	 */
    public static UpdateRecommendationProfileBuilder update(int recommendationEngineId, RecommendationProfile recommendationEngine)  {
		return new UpdateRecommendationProfileBuilder(recommendationEngineId, recommendationEngine);
	}
}
