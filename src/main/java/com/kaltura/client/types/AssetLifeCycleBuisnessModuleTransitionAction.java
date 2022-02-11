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
// Copyright (C) 2006-2022  Kaltura Inc.
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
@MultiRequestBuilder.Tokenizer(AssetLifeCycleBuisnessModuleTransitionAction.Tokenizer.class)
public class AssetLifeCycleBuisnessModuleTransitionAction extends AssetLifeCycleTransitionAction {
	
	public interface Tokenizer extends AssetLifeCycleTransitionAction.Tokenizer {
		String fileTypeIds();
		String ppvIds();
	}

	/**
	 * Comma separated list of fileType Ids.
	 */
	private String fileTypeIds;
	/**
	 * Comma separated list of ppv Ids.
	 */
	private String ppvIds;

	// fileTypeIds:
	public String getFileTypeIds(){
		return this.fileTypeIds;
	}
	public void setFileTypeIds(String fileTypeIds){
		this.fileTypeIds = fileTypeIds;
	}

	public void fileTypeIds(String multirequestToken){
		setToken("fileTypeIds", multirequestToken);
	}

	// ppvIds:
	public String getPpvIds(){
		return this.ppvIds;
	}
	public void setPpvIds(String ppvIds){
		this.ppvIds = ppvIds;
	}

	public void ppvIds(String multirequestToken){
		setToken("ppvIds", multirequestToken);
	}


	public AssetLifeCycleBuisnessModuleTransitionAction() {
		super();
	}

	public AssetLifeCycleBuisnessModuleTransitionAction(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		fileTypeIds = GsonParser.parseString(jsonObject.get("fileTypeIds"));
		ppvIds = GsonParser.parseString(jsonObject.get("ppvIds"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetLifeCycleBuisnessModuleTransitionAction");
		kparams.add("fileTypeIds", this.fileTypeIds);
		kparams.add("ppvIds", this.ppvIds);
		return kparams;
	}

}

