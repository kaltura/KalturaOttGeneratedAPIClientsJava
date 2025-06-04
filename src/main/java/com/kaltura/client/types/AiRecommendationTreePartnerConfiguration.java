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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A class representing the partner-specific configuration for TV Genie
  recommendation trees.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AiRecommendationTreePartnerConfiguration.Tokenizer.class)
public class AiRecommendationTreePartnerConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		RequestBuilder.ListTokenizer<StringValue.Tokenizer> activeMetadataTypes();
		String topLevelQuestions();
		String answersPerQuestion();
		String levels();
		String specialAnswers();
		String numOfRecommendedAssets();
		String removeWatchedRecommendations();
		String treeGenerationFrequency();
		String modelId();
		String activeTreeId();
	}

	/**
	 * List of metadata types to base questions on (genre, actor, director, etc.).
	 */
	private List<StringValue> activeMetadataTypes;
	/**
	 * Number of top-level questions to generate (range: 5-21).
	 */
	private Integer topLevelQuestions;
	/**
	 * Number of regular answers per question (range: 2-3).
	 */
	private Integer answersPerQuestion;
	/**
	 * Maximum depth of the decision tree (range: 1-5).
	 */
	private Integer levels;
	/**
	 * Whether to include special answers like &amp;quot;I don&amp;#39;t know&amp;quot;
	  or &amp;quot;Surprise Me&amp;quot;.
	 */
	private Boolean specialAnswers;
	/**
	 * Number of assets to include in each recommendation set.
	 */
	private Integer numOfRecommendedAssets;
	/**
	 * Whether to exclude already watched content.
	 */
	private Boolean removeWatchedRecommendations;
	/**
	 * Cron expression for scheduling tree regeneration.
	 */
	private String treeGenerationFrequency;
	/**
	 * Identifier for the LLM model used for tree generation.
	 */
	private String modelId;
	/**
	 * Identifier for the tree that is currently marked as Active (can be only one at a
	  time)
	 */
	private String activeTreeId;

	// activeMetadataTypes:
	public List<StringValue> getActiveMetadataTypes(){
		return this.activeMetadataTypes;
	}
	public void setActiveMetadataTypes(List<StringValue> activeMetadataTypes){
		this.activeMetadataTypes = activeMetadataTypes;
	}

	// topLevelQuestions:
	public Integer getTopLevelQuestions(){
		return this.topLevelQuestions;
	}
	public void setTopLevelQuestions(Integer topLevelQuestions){
		this.topLevelQuestions = topLevelQuestions;
	}

	public void topLevelQuestions(String multirequestToken){
		setToken("topLevelQuestions", multirequestToken);
	}

	// answersPerQuestion:
	public Integer getAnswersPerQuestion(){
		return this.answersPerQuestion;
	}
	public void setAnswersPerQuestion(Integer answersPerQuestion){
		this.answersPerQuestion = answersPerQuestion;
	}

	public void answersPerQuestion(String multirequestToken){
		setToken("answersPerQuestion", multirequestToken);
	}

	// levels:
	public Integer getLevels(){
		return this.levels;
	}
	public void setLevels(Integer levels){
		this.levels = levels;
	}

	public void levels(String multirequestToken){
		setToken("levels", multirequestToken);
	}

	// specialAnswers:
	public Boolean getSpecialAnswers(){
		return this.specialAnswers;
	}
	public void setSpecialAnswers(Boolean specialAnswers){
		this.specialAnswers = specialAnswers;
	}

	public void specialAnswers(String multirequestToken){
		setToken("specialAnswers", multirequestToken);
	}

	// numOfRecommendedAssets:
	public Integer getNumOfRecommendedAssets(){
		return this.numOfRecommendedAssets;
	}
	public void setNumOfRecommendedAssets(Integer numOfRecommendedAssets){
		this.numOfRecommendedAssets = numOfRecommendedAssets;
	}

	public void numOfRecommendedAssets(String multirequestToken){
		setToken("numOfRecommendedAssets", multirequestToken);
	}

	// removeWatchedRecommendations:
	public Boolean getRemoveWatchedRecommendations(){
		return this.removeWatchedRecommendations;
	}
	public void setRemoveWatchedRecommendations(Boolean removeWatchedRecommendations){
		this.removeWatchedRecommendations = removeWatchedRecommendations;
	}

	public void removeWatchedRecommendations(String multirequestToken){
		setToken("removeWatchedRecommendations", multirequestToken);
	}

	// treeGenerationFrequency:
	public String getTreeGenerationFrequency(){
		return this.treeGenerationFrequency;
	}
	public void setTreeGenerationFrequency(String treeGenerationFrequency){
		this.treeGenerationFrequency = treeGenerationFrequency;
	}

	public void treeGenerationFrequency(String multirequestToken){
		setToken("treeGenerationFrequency", multirequestToken);
	}

	// modelId:
	public String getModelId(){
		return this.modelId;
	}
	public void setModelId(String modelId){
		this.modelId = modelId;
	}

	public void modelId(String multirequestToken){
		setToken("modelId", multirequestToken);
	}

	// activeTreeId:
	public String getActiveTreeId(){
		return this.activeTreeId;
	}
	public void setActiveTreeId(String activeTreeId){
		this.activeTreeId = activeTreeId;
	}

	public void activeTreeId(String multirequestToken){
		setToken("activeTreeId", multirequestToken);
	}


	public AiRecommendationTreePartnerConfiguration() {
		super();
	}

	public AiRecommendationTreePartnerConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		activeMetadataTypes = GsonParser.parseArray(jsonObject.getAsJsonArray("activeMetadataTypes"), StringValue.class);
		topLevelQuestions = GsonParser.parseInt(jsonObject.get("topLevelQuestions"));
		answersPerQuestion = GsonParser.parseInt(jsonObject.get("answersPerQuestion"));
		levels = GsonParser.parseInt(jsonObject.get("levels"));
		specialAnswers = GsonParser.parseBoolean(jsonObject.get("specialAnswers"));
		numOfRecommendedAssets = GsonParser.parseInt(jsonObject.get("numOfRecommendedAssets"));
		removeWatchedRecommendations = GsonParser.parseBoolean(jsonObject.get("removeWatchedRecommendations"));
		treeGenerationFrequency = GsonParser.parseString(jsonObject.get("treeGenerationFrequency"));
		modelId = GsonParser.parseString(jsonObject.get("modelId"));
		activeTreeId = GsonParser.parseString(jsonObject.get("activeTreeId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAiRecommendationTreePartnerConfiguration");
		kparams.add("activeMetadataTypes", this.activeMetadataTypes);
		kparams.add("topLevelQuestions", this.topLevelQuestions);
		kparams.add("answersPerQuestion", this.answersPerQuestion);
		kparams.add("levels", this.levels);
		kparams.add("specialAnswers", this.specialAnswers);
		kparams.add("numOfRecommendedAssets", this.numOfRecommendedAssets);
		kparams.add("removeWatchedRecommendations", this.removeWatchedRecommendations);
		kparams.add("treeGenerationFrequency", this.treeGenerationFrequency);
		kparams.add("modelId", this.modelId);
		kparams.add("activeTreeId", this.activeTreeId);
		return kparams;
	}

}

