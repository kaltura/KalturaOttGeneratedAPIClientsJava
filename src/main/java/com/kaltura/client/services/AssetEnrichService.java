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

import com.kaltura.client.FileHolder;
import com.kaltura.client.Files;
import com.kaltura.client.types.CaptionUploadData;
import com.kaltura.client.types.CaptionUploadJob;
import com.kaltura.client.types.EnrichedMetadataResult;
import com.kaltura.client.types.MetaEnrichConfiguration;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.request.RequestBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AssetEnrichService {
	
	public static class GenerateMetadataAssetEnrichBuilder extends RequestBuilder<CaptionUploadJob, CaptionUploadJob.Tokenizer, GenerateMetadataAssetEnrichBuilder> {
		
		public GenerateMetadataAssetEnrichBuilder(long captionUploadJobId, List<StringValue> externalAssetIds, String targetDisplayLanguage) {
			super(CaptionUploadJob.class, "assetenrich", "generateMetadata");
			params.add("captionUploadJobId", captionUploadJobId);
			params.add("externalAssetIds", externalAssetIds);
			params.add("targetDisplayLanguage", targetDisplayLanguage);
		}
		
		public void captionUploadJobId(String multirequestToken) {
			params.add("captionUploadJobId", multirequestToken);
		}
		
		public void targetDisplayLanguage(String multirequestToken) {
			params.add("targetDisplayLanguage", multirequestToken);
		}
	}

	/**
	 * Initiate the process of metadata generation
	 * 
	 * @param captionUploadJobId job id to generate metadata for
	 * @param externalAssetIds external asset ids
	 * @param targetDisplayLanguage relevant language
	 */
    public static GenerateMetadataAssetEnrichBuilder generateMetadata(long captionUploadJobId, List<StringValue> externalAssetIds, String targetDisplayLanguage)  {
		return new GenerateMetadataAssetEnrichBuilder(captionUploadJobId, externalAssetIds, targetDisplayLanguage);
	}
	
	public static class GetCaptionUploadJobAssetEnrichBuilder extends RequestBuilder<CaptionUploadJob, CaptionUploadJob.Tokenizer, GetCaptionUploadJobAssetEnrichBuilder> {
		
		public GetCaptionUploadJobAssetEnrichBuilder(long captionUploadJobId) {
			super(CaptionUploadJob.class, "assetenrich", "getCaptionUploadJob");
			params.add("captionUploadJobId", captionUploadJobId);
		}
		
		public void captionUploadJobId(String multirequestToken) {
			params.add("captionUploadJobId", multirequestToken);
		}
	}

	/**
	 * retrieve the status of the metadata generation job
	 * 
	 * @param captionUploadJobId job id to get
	 */
    public static GetCaptionUploadJobAssetEnrichBuilder getCaptionUploadJob(long captionUploadJobId)  {
		return new GetCaptionUploadJobAssetEnrichBuilder(captionUploadJobId);
	}
	
	public static class GetGeneratedMetadataAssetEnrichBuilder extends RequestBuilder<EnrichedMetadataResult, EnrichedMetadataResult.Tokenizer, GetGeneratedMetadataAssetEnrichBuilder> {
		
		public GetGeneratedMetadataAssetEnrichBuilder(long captionUploadJobId) {
			super(EnrichedMetadataResult.class, "assetenrich", "getGeneratedMetadata");
			params.add("captionUploadJobId", captionUploadJobId);
		}
		
		public void captionUploadJobId(String multirequestToken) {
			params.add("captionUploadJobId", multirequestToken);
		}
	}

	/**
	 * retrieve the generated metadata
	 * 
	 * @param captionUploadJobId job id
	 */
    public static GetGeneratedMetadataAssetEnrichBuilder getGeneratedMetadata(long captionUploadJobId)  {
		return new GetGeneratedMetadataAssetEnrichBuilder(captionUploadJobId);
	}
	
	public static class GetPartnerConfigurationAssetEnrichBuilder extends RequestBuilder<MetaEnrichConfiguration, MetaEnrichConfiguration.Tokenizer, GetPartnerConfigurationAssetEnrichBuilder> {
		
		public GetPartnerConfigurationAssetEnrichBuilder() {
			super(MetaEnrichConfiguration.class, "assetenrich", "getPartnerConfiguration");
		}
	}

	/**
	 * retrieve feature configuration
	 */
    public static GetPartnerConfigurationAssetEnrichBuilder getPartnerConfiguration()  {
		return new GetPartnerConfigurationAssetEnrichBuilder();
	}
	
	public static class UpdatePartnerConfigurationAssetEnrichBuilder extends RequestBuilder<MetaEnrichConfiguration, MetaEnrichConfiguration.Tokenizer, UpdatePartnerConfigurationAssetEnrichBuilder> {
		
		public UpdatePartnerConfigurationAssetEnrichBuilder(MetaEnrichConfiguration configuration) {
			super(MetaEnrichConfiguration.class, "assetenrich", "updatePartnerConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * update feature configuration
	 * 
	 * @param configuration the partner configuration to be set
	 */
    public static UpdatePartnerConfigurationAssetEnrichBuilder updatePartnerConfiguration(MetaEnrichConfiguration configuration)  {
		return new UpdatePartnerConfigurationAssetEnrichBuilder(configuration);
	}
	
	public static class UploadCaptionFileAssetEnrichBuilder extends RequestBuilder<CaptionUploadJob, CaptionUploadJob.Tokenizer, UploadCaptionFileAssetEnrichBuilder> {
		
		public UploadCaptionFileAssetEnrichBuilder(CaptionUploadData json, FileHolder fileName) {
			super(CaptionUploadJob.class, "assetenrich", "uploadCaptionFile");
			params.add("json", json);
			files = new Files();
			files.add("fileName", fileName);
		}
	}

	public static UploadCaptionFileAssetEnrichBuilder uploadCaptionFile(CaptionUploadData json, File fileName)  {
		return uploadCaptionFile(json, new FileHolder(fileName));
	}

	public static UploadCaptionFileAssetEnrichBuilder uploadCaptionFile(CaptionUploadData json, InputStream fileName, String fileNameMimeType, String fileNameName, long fileNameSize)  {
		return uploadCaptionFile(json, new FileHolder(fileName, fileNameMimeType, fileNameName, fileNameSize));
	}

	public static UploadCaptionFileAssetEnrichBuilder uploadCaptionFile(CaptionUploadData json, FileInputStream fileName, String fileNameMimeType, String fileNameName)  {
		return uploadCaptionFile(json, new FileHolder(fileName, fileNameMimeType, fileNameName));
	}

	/**
	 * Add a file to be used for enriching the assets&amp;#39; metadata
	 * 
	 * @param json Properties of the caption file to be uploaded
	 * @param fileName The caption text file to upload. The file must be in UTF-8 encoding.
	 */
    public static UploadCaptionFileAssetEnrichBuilder uploadCaptionFile(CaptionUploadData json, FileHolder fileName)  {
		return new UploadCaptionFileAssetEnrichBuilder(json, fileName);
	}
}
