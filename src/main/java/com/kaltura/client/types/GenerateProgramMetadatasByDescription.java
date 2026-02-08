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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GenerateProgramMetadatasByDescription.Tokenizer.class)
public class GenerateProgramMetadatasByDescription extends GenerateMetadataByDescription {
	
	public interface Tokenizer extends GenerateMetadataByDescription.Tokenizer {
		String regenerate();
	}

	/**
	 * A boolean flag that allows the API user to force the regeneration of metadata.  
	             If true, the service will run a new analysis even if enriched
	  metadata already exists for the program&amp;#39;s CRID.              If false
	  (default), the service will reuse existing metadata if available for the CRID.
	 */
	private Boolean regenerate;

	// regenerate:
	public Boolean getRegenerate(){
		return this.regenerate;
	}
	public void setRegenerate(Boolean regenerate){
		this.regenerate = regenerate;
	}

	public void regenerate(String multirequestToken){
		setToken("regenerate", multirequestToken);
	}


	public GenerateProgramMetadatasByDescription() {
		super();
	}

	public GenerateProgramMetadatasByDescription(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		regenerate = GsonParser.parseBoolean(jsonObject.get("regenerate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGenerateProgramMetadatasByDescription");
		kparams.add("regenerate", this.regenerate);
		return kparams;
	}

}

