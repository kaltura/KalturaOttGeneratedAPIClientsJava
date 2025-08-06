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

import com.kaltura.client.types.AiRecommendationTreePartnerConfiguration;
import com.kaltura.client.types.TreeNaturalTextResponse;
import com.kaltura.client.types.TreeNextNodeResponse;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AiRecommendationTreeService {
	
	public static class GetNextNodeAndRecommendationAiRecommendationTreeBuilder extends RequestBuilder<TreeNextNodeResponse, TreeNextNodeResponse.Tokenizer, GetNextNodeAndRecommendationAiRecommendationTreeBuilder> {
		
		public GetNextNodeAndRecommendationAiRecommendationTreeBuilder(String treeId, String answerId, String topQuestionId) {
			super(TreeNextNodeResponse.class, "airecommendationtree", "getNextNodeAndRecommendation");
			params.add("treeId", treeId);
			params.add("answerId", answerId);
			params.add("topQuestionId", topQuestionId);
		}
		
		public void treeId(String multirequestToken) {
			params.add("treeId", multirequestToken);
		}
		
		public void answerId(String multirequestToken) {
			params.add("answerId", multirequestToken);
		}
		
		public void topQuestionId(String multirequestToken) {
			params.add("topQuestionId", multirequestToken);
		}
	}

	public static GetNextNodeAndRecommendationAiRecommendationTreeBuilder getNextNodeAndRecommendation()  {
		return getNextNodeAndRecommendation(null);
	}

	public static GetNextNodeAndRecommendationAiRecommendationTreeBuilder getNextNodeAndRecommendation(String treeId)  {
		return getNextNodeAndRecommendation(treeId, null);
	}

	public static GetNextNodeAndRecommendationAiRecommendationTreeBuilder getNextNodeAndRecommendation(String treeId, String answerId)  {
		return getNextNodeAndRecommendation(treeId, answerId, null);
	}

	/**
	 * Returns the next question, available answers, and content recommendations based
	  on the current path through the tree.
	 * 
	 * @param treeId ID of the tree to navigate (optional - if omitted, the active tree will be used)
	 * @param answerId Selected answer ID from the previous question (required if previousQuestionId is
	 * provided)
	 * @param topQuestionId Specific top-level question ID (relevant for first question only)
	 */
    public static GetNextNodeAndRecommendationAiRecommendationTreeBuilder getNextNodeAndRecommendation(String treeId, String answerId, String topQuestionId)  {
		return new GetNextNodeAndRecommendationAiRecommendationTreeBuilder(treeId, answerId, topQuestionId);
	}
	
	public static class GetPartnerConfigAiRecommendationTreeBuilder extends RequestBuilder<AiRecommendationTreePartnerConfiguration, AiRecommendationTreePartnerConfiguration.Tokenizer, GetPartnerConfigAiRecommendationTreeBuilder> {
		
		public GetPartnerConfigAiRecommendationTreeBuilder() {
			super(AiRecommendationTreePartnerConfiguration.class, "airecommendationtree", "getPartnerConfig");
		}
	}

	/**
	 * Retrieves the current configuration settings for TV Genie for a specific
	  partner.
	 */
    public static GetPartnerConfigAiRecommendationTreeBuilder getPartnerConfig()  {
		return new GetPartnerConfigAiRecommendationTreeBuilder();
	}
	
	public static class GetRecommendationWithNaturalTextAiRecommendationTreeBuilder extends RequestBuilder<TreeNaturalTextResponse, TreeNaturalTextResponse.Tokenizer, GetRecommendationWithNaturalTextAiRecommendationTreeBuilder> {
		
		public GetRecommendationWithNaturalTextAiRecommendationTreeBuilder(String naturalTextQuery, String questionId, String treeId) {
			super(TreeNaturalTextResponse.class, "airecommendationtree", "getRecommendationWithNaturalText");
			params.add("naturalTextQuery", naturalTextQuery);
			params.add("questionId", questionId);
			params.add("treeId", treeId);
		}
		
		public void naturalTextQuery(String multirequestToken) {
			params.add("naturalTextQuery", multirequestToken);
		}
		
		public void questionId(String multirequestToken) {
			params.add("questionId", multirequestToken);
		}
		
		public void treeId(String multirequestToken) {
			params.add("treeId", multirequestToken);
		}
	}

	public static GetRecommendationWithNaturalTextAiRecommendationTreeBuilder getRecommendationWithNaturalText(String naturalTextQuery)  {
		return getRecommendationWithNaturalText(naturalTextQuery, null);
	}

	public static GetRecommendationWithNaturalTextAiRecommendationTreeBuilder getRecommendationWithNaturalText(String naturalTextQuery, String questionId)  {
		return getRecommendationWithNaturalText(naturalTextQuery, questionId, null);
	}

	/**
	 * Returns content recommendations based on natural language input.
	 * 
	 * @param naturalTextQuery The query text entered by the user
	 * @param questionId The Id of the question that naturalTextQuery is the answer to (optional)
	 * @param treeId ID of the tree to use (mandatory if previousQuestionId is provided)
	 */
    public static GetRecommendationWithNaturalTextAiRecommendationTreeBuilder getRecommendationWithNaturalText(String naturalTextQuery, String questionId, String treeId)  {
		return new GetRecommendationWithNaturalTextAiRecommendationTreeBuilder(naturalTextQuery, questionId, treeId);
	}
	
	public static class UpsertPartnerConfigAiRecommendationTreeBuilder extends RequestBuilder<AiRecommendationTreePartnerConfiguration, AiRecommendationTreePartnerConfiguration.Tokenizer, UpsertPartnerConfigAiRecommendationTreeBuilder> {
		
		public UpsertPartnerConfigAiRecommendationTreeBuilder(AiRecommendationTreePartnerConfiguration configuration) {
			super(AiRecommendationTreePartnerConfiguration.class, "airecommendationtree", "upsertPartnerConfig");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Updates the configuration settings for TV Genie on a per-partner basis.
	 * 
	 * @param configuration The partner configuration to be set
	 */
    public static UpsertPartnerConfigAiRecommendationTreeBuilder upsertPartnerConfig(AiRecommendationTreePartnerConfiguration configuration)  {
		return new UpsertPartnerConfigAiRecommendationTreeBuilder(configuration);
	}
}
