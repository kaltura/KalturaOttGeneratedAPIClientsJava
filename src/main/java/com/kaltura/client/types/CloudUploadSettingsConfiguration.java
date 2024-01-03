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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A clout upload settings refers to partner configuration with regards to files
  that are loaded to KTP cloud (e.g. S3)
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CloudUploadSettingsConfiguration.Tokenizer.class)
public class CloudUploadSettingsConfiguration extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		String defaultAllowedFileExtensions();
		String customAllowedFileExtensions();
	}

	/**
	 * Comma seperated list of file extensions that allowed to all partners
	 */
	private String defaultAllowedFileExtensions;
	/**
	 * Comma seperated list of file extensions that allowed to partner in question     
	          {&amp;quot;jpeg&amp;quot;,&amp;quot;image/jpeg&amp;quot;},             
	  {&amp;quot;jpg&amp;quot;,&amp;quot;image/jpeg&amp;quot;},             
	  {&amp;quot;png&amp;quot;,&amp;quot;image/png&amp;quot;},             
	  {&amp;quot;tif&amp;quot;,&amp;quot;image/tiff&amp;quot;},             
	  {&amp;quot;tiff&amp;quot;,&amp;quot;image/tiff&amp;quot;},             
	  {&amp;quot;gif&amp;quot;,&amp;quot;image/gif&amp;quot;},             
	  {&amp;quot;xls&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},         
	     
	  {&amp;quot;xlsx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.spreadsheetml.sheet&amp;quot;},
	               {&amp;quot;csv&amp;quot;,&amp;quot;text/csv&amp;quot;},            
	   {&amp;quot;xml&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;txt&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;doc&amp;quot;,&amp;quot;application/msword&amp;quot;},             
	  {&amp;quot;docx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.wordprocessingml.document&amp;quot;},
	               {&amp;quot;bmp&amp;quot;,&amp;quot;image/bmp&amp;quot;},           
	    {&amp;quot;ico&amp;quot;,&amp;quot;image/x-icon&amp;quot;},             
	  {&amp;quot;mp3&amp;quot;,&amp;quot;audio/mpeg&amp;quot;},             
	  {&amp;quot;pdf&amp;quot;,&amp;quot;application/pdf&amp;quot;}}
	 */
	private String customAllowedFileExtensions;

	// defaultAllowedFileExtensions:
	public String getDefaultAllowedFileExtensions(){
		return this.defaultAllowedFileExtensions;
	}
	// customAllowedFileExtensions:
	public String getCustomAllowedFileExtensions(){
		return this.customAllowedFileExtensions;
	}
	public void setCustomAllowedFileExtensions(String customAllowedFileExtensions){
		this.customAllowedFileExtensions = customAllowedFileExtensions;
	}

	public void customAllowedFileExtensions(String multirequestToken){
		setToken("customAllowedFileExtensions", multirequestToken);
	}


	public CloudUploadSettingsConfiguration() {
		super();
	}

	public CloudUploadSettingsConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		defaultAllowedFileExtensions = GsonParser.parseString(jsonObject.get("defaultAllowedFileExtensions"));
		customAllowedFileExtensions = GsonParser.parseString(jsonObject.get("customAllowedFileExtensions"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCloudUploadSettingsConfiguration");
		kparams.add("customAllowedFileExtensions", this.customAllowedFileExtensions);
		return kparams;
	}

}

