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
package com.kaltura.client.types;

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Image type
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ImageType.Tokenizer.class)
public class ImageType extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String systemName();
		String ratioId();
		String helpText();
		String defaultImageId();
	}

	/**
	 * Image type ID
	 */
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * System name
	 */
	private String systemName;
	/**
	 * Ration ID
	 */
	private Long ratioId;
	/**
	 * Help text
	 */
	private String helpText;
	/**
	 * Default image ID
	 */
	private Long defaultImageId;

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

	// systemName:
	public String getSystemName(){
		return this.systemName;
	}
	public void setSystemName(String systemName){
		this.systemName = systemName;
	}

	public void systemName(String multirequestToken){
		setToken("systemName", multirequestToken);
	}

	// ratioId:
	public Long getRatioId(){
		return this.ratioId;
	}
	public void setRatioId(Long ratioId){
		this.ratioId = ratioId;
	}

	public void ratioId(String multirequestToken){
		setToken("ratioId", multirequestToken);
	}

	// helpText:
	public String getHelpText(){
		return this.helpText;
	}
	public void setHelpText(String helpText){
		this.helpText = helpText;
	}

	public void helpText(String multirequestToken){
		setToken("helpText", multirequestToken);
	}

	// defaultImageId:
	public Long getDefaultImageId(){
		return this.defaultImageId;
	}
	public void setDefaultImageId(Long defaultImageId){
		this.defaultImageId = defaultImageId;
	}

	public void defaultImageId(String multirequestToken){
		setToken("defaultImageId", multirequestToken);
	}


	public ImageType() {
		super();
	}

	public ImageType(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		systemName = GsonParser.parseString(jsonObject.get("systemName"));
		ratioId = GsonParser.parseLong(jsonObject.get("ratioId"));
		helpText = GsonParser.parseString(jsonObject.get("helpText"));
		defaultImageId = GsonParser.parseLong(jsonObject.get("defaultImageId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaImageType");
		kparams.add("name", this.name);
		kparams.add("systemName", this.systemName);
		kparams.add("ratioId", this.ratioId);
		kparams.add("helpText", this.helpText);
		kparams.add("defaultImageId", this.defaultImageId);
		return kparams;
	}

}

