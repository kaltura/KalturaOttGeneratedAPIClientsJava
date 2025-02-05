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
import com.kaltura.client.types.GenerateMetadataBySubtitlesJob;
import com.kaltura.client.types.GenerateMetadataResult;
import com.kaltura.client.types.MetaFieldNameMap;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AiMetadataGeneratorService {
	
	public static class GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder extends RequestBuilder<GenerateMetadataBySubtitlesJob, GenerateMetadataBySubtitlesJob.Tokenizer, GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder> {
		
		public GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder(long subtitlesFileId, List<StringValue> externalAssetIds, String targetDisplayLanguage) {
			super(GenerateMetadataBySubtitlesJob.class, "aimetadatagenerator", "generateMetadataBySubtitles");
			params.add("subtitlesFileId", subtitlesFileId);
			params.add("externalAssetIds", externalAssetIds);
			params.add("targetDisplayLanguage", targetDisplayLanguage);
		}
		
		public void subtitlesFileId(String multirequestToken) {
			params.add("subtitlesFileId", multirequestToken);
		}
		
		public void targetDisplayLanguage(String multirequestToken) {
			params.add("targetDisplayLanguage", multirequestToken);
		}
	}

	/**
	 * initiate the the process of metadata generation based on the subtitles file.
	 * 
	 * @param subtitlesFileId he subtitles file ID returned when uploaded the subtitles file by the subtitles
	 * service.
	 *             Represents also the job ID used by the generate metadata process
	 * @param externalAssetIds A list of external asset IDs to be populated with the generated metadata
	 * @param targetDisplayLanguage The language used for the generated results.
	 *             Must be a valid existing KalturaLanguage systemName.\nIf not
	 * provided then the subtitles language will be used
	 */
    public static GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder generateMetadataBySubtitles(long subtitlesFileId, List<StringValue> externalAssetIds, String targetDisplayLanguage)  {
		return new GenerateMetadataBySubtitlesAiMetadataGeneratorBuilder(subtitlesFileId, externalAssetIds, targetDisplayLanguage);
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
	 * retrieve the generated metadata
	 * 
	 * @param jobId The job ID (equals the subtitles file ID returned by the subtitles.uploadFile
	 * service)
	 */
    public static GetGeneratedMetadataAiMetadataGeneratorBuilder getGeneratedMetadata(long jobId)  {
		return new GetGeneratedMetadataAiMetadataGeneratorBuilder(jobId);
	}
	
	public static class GetGenerateMetadataJobAiMetadataGeneratorBuilder extends RequestBuilder<GenerateMetadataBySubtitlesJob, GenerateMetadataBySubtitlesJob.Tokenizer, GetGenerateMetadataJobAiMetadataGeneratorBuilder> {
		
		public GetGenerateMetadataJobAiMetadataGeneratorBuilder(long id) {
			super(GenerateMetadataBySubtitlesJob.class, "aimetadatagenerator", "getGenerateMetadataJob");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * retrieve the status of the metadata generation job, identified by the subtitles
	  file ID.
	 * 
	 * @param id The file (job) ID as received from subtitles.uploadFile response"
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
	 * Get metadata mapping structure and available generated metadata fields
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
	 * retrieve feature configuration
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
	 * update feature configuration
	 * 
	 * @param configuration the partner configuration to be set
	 */
    public static UpdatePartnerConfigurationAiMetadataGeneratorBuilder updatePartnerConfiguration(AiMetadataGeneratorConfiguration configuration)  {
		return new UpdatePartnerConfigurationAiMetadataGeneratorBuilder(configuration);
	}
}
