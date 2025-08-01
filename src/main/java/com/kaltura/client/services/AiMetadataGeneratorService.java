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

import com.kaltura.client.types.AiMetadataGeneratorConfiguration;
import com.kaltura.client.types.GenerateMetadataByDescription;
import com.kaltura.client.types.GenerateMetadataBySubtitles;
import com.kaltura.client.types.GenerateMetadataJob;
import com.kaltura.client.types.GenerateMetadataResult;
import com.kaltura.client.types.MetaFieldNameMap;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AiMetadataGeneratorService {
	
	public static class GenerateMetadataByDescriptionAiMetadataGeneratorBuilder extends RequestBuilder<GenerateMetadataJob, GenerateMetadataJob.Tokenizer, GenerateMetadataByDescriptionAiMetadataGeneratorBuilder> {
		
		public GenerateMetadataByDescriptionAiMetadataGeneratorBuilder(GenerateMetadataByDescription generateMetadataByDescription) {
			super(GenerateMetadataJob.class, "aimetadatagenerator", "generateMetadataByDescription");
			params.add("generateMetadataByDescription", generateMetadataByDescription);
		}
	}

	/**
	 * Initiate the process of metadata generation based on existing asset description
	  metadata.              The service will analyze the asset&amp;#39;s description
	  and genre metadata using AI/LLM to generate              additional enriched
	  metadata fields including enhanced genre classifications, sentiment analysis,   
	            and relevant keywords. This method is useful for enriching assets that
	  already have basic              description metadata but need additional
	  AI-generated metadata fields.
	 * 
	 * @param generateMetadataByDescription Request object containing the external asset ID to analyze and enrich
	 */
    public static GenerateMetadataByDescriptionAiMetadataGeneratorBuilder generateMetadataByDescription(GenerateMetadataByDescription generateMetadataByDescription)  {
		return new GenerateMetadataByDescriptionAiMetadataGeneratorBuilder(generateMetadataByDescription);
	}
	
	public static class GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder extends RequestBuilder<GenerateMetadataJob, GenerateMetadataJob.Tokenizer, GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder> {
		
		public GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder(GenerateMetadataBySubtitles generateMetadataBySubtitles) {
			super(GenerateMetadataJob.class, "aimetadatagenerator", "generateMetadataBySubtitles");
			params.add("generateMetadataBySubtitles", generateMetadataBySubtitles);
		}
	}

	/**
	 * Initiate the process of metadata generation based on the subtitles file.        
	       The subtitles file must be previously uploaded using the
	  subtitles.uploadFile service.              The service will analyze the subtitle
	  content using AI/LLM to generate enriched metadata including              genre,
	  description, keywords, sentiment analysis, and other metadata fields.
	 * 
	 * @param generateMetadataBySubtitles Request object containing the subtitles file ID and optional external asset IDs
	 * to update
	 */
    public static GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder generateMetadataBySubtitles(GenerateMetadataBySubtitles generateMetadataBySubtitles)  {
		return new GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder(generateMetadataBySubtitles);
	}
	
	public static class GetGeneratedMetadataAiMetadataGeneratorBuilder extends RequestBuilder<GenerateMetadataResult, GenerateMetadataResult.Tokenizer, GetGeneratedMetadataAiMetadataGeneratorBuilder> {
		
		public GetGeneratedMetadataAiMetadataGeneratorBuilder(long jobId) {
			super(GenerateMetadataResult.class, "aimetadatagenerator", "getGeneratedMetadata");
			params.add("jobId", jobId);
		}
		
		public void jobId(String multirequestToken) {
			params.add("jobId", multirequestToken);
		}
	}

	/**
	 * Retrieve the generated metadata
	 * 
	 * @param jobId The job ID as received from GenerateMetadataBySubtitles.
	 */
    public static GetGeneratedMetadataAiMetadataGeneratorBuilder getGeneratedMetadata(long jobId)  {
		return new GetGeneratedMetadataAiMetadataGeneratorBuilder(jobId);
	}
	
	public static class GetGenerateMetadataJobAiMetadataGeneratorBuilder extends RequestBuilder<GenerateMetadataJob, GenerateMetadataJob.Tokenizer, GetGenerateMetadataJobAiMetadataGeneratorBuilder> {
		
		public GetGenerateMetadataJobAiMetadataGeneratorBuilder(long id) {
			super(GenerateMetadataJob.class, "aimetadatagenerator", "getGenerateMetadataJob");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get a metadata generation job.
	 * 
	 * @param id The job ID as received from GenerateMetadataBySubtitles.
	 */
    public static GetGenerateMetadataJobAiMetadataGeneratorBuilder getGenerateMetadataJob(long id)  {
		return new GetGenerateMetadataJobAiMetadataGeneratorBuilder(id);
	}
	
	public static class GetMetadataFieldDefinitionsAiMetadataGeneratorBuilder extends RequestBuilder<MetaFieldNameMap, MetaFieldNameMap.Tokenizer, GetMetadataFieldDefinitionsAiMetadataGeneratorBuilder> {
		
		public GetMetadataFieldDefinitionsAiMetadataGeneratorBuilder() {
			super(MetaFieldNameMap.class, "aimetadatagenerator", "getMetadataFieldDefinitions");
		}
	}

	/**
	 * Get metadata mapping structure and available generated metadata fields.
	 */
    public static GetMetadataFieldDefinitionsAiMetadataGeneratorBuilder getMetadataFieldDefinitions()  {
		return new GetMetadataFieldDefinitionsAiMetadataGeneratorBuilder();
	}
	
	public static class GetPartnerConfigurationAiMetadataGeneratorBuilder extends RequestBuilder<AiMetadataGeneratorConfiguration, AiMetadataGeneratorConfiguration.Tokenizer, GetPartnerConfigurationAiMetadataGeneratorBuilder> {
		
		public GetPartnerConfigurationAiMetadataGeneratorBuilder() {
			super(AiMetadataGeneratorConfiguration.class, "aimetadatagenerator", "getPartnerConfiguration");
		}
	}

	/**
	 * Get the metadata generation configuration.
	 */
    public static GetPartnerConfigurationAiMetadataGeneratorBuilder getPartnerConfiguration()  {
		return new GetPartnerConfigurationAiMetadataGeneratorBuilder();
	}
	
	public static class UpdatePartnerConfigurationAiMetadataGeneratorBuilder extends RequestBuilder<AiMetadataGeneratorConfiguration, AiMetadataGeneratorConfiguration.Tokenizer, UpdatePartnerConfigurationAiMetadataGeneratorBuilder> {
		
		public UpdatePartnerConfigurationAiMetadataGeneratorBuilder(AiMetadataGeneratorConfiguration configuration) {
			super(AiMetadataGeneratorConfiguration.class, "aimetadatagenerator", "updatePartnerConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Update/set the metadata generation configuration
	 * 
	 * @param configuration the partner configuration to be set
	 */
    public static UpdatePartnerConfigurationAiMetadataGeneratorBuilder updatePartnerConfiguration(AiMetadataGeneratorConfiguration configuration)  {
		return new UpdatePartnerConfigurationAiMetadataGeneratorBuilder(configuration);
	}
}
