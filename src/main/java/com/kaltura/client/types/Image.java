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
import com.kaltura.client.enums.ImageObjectType;
import com.kaltura.client.enums.ImageStatus;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Image.Tokenizer.class)
public class Image extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String version();
		String imageTypeId();
		String imageObjectId();
		String imageObjectType();
		String status();
		String url();
		String contentId();
		String isDefault();
	}

	/**
	 * Image ID
	 */
	private Long id;
	/**
	 * Image version
	 */
	private String version;
	/**
	 * Image type ID
	 */
	private Long imageTypeId;
	/**
	 * ID of the object the image is related to
	 */
	private Long imageObjectId;
	/**
	 * Type of the object the image is related to
	 */
	private ImageObjectType imageObjectType;
	/**
	 * Image content status
	 */
	private ImageStatus status;
	/**
	 * Image URL
	 */
	private String url;
	/**
	 * Image content ID
	 */
	private String contentId;
	/**
	 * Specifies if the image is default for atleast one image type.
	 */
	private Boolean isDefault;

	// id:
	public Long getId(){
		return this.id;
	}
	// version:
	public String getVersion(){
		return this.version;
	}
	// imageTypeId:
	public Long getImageTypeId(){
		return this.imageTypeId;
	}
	public void setImageTypeId(Long imageTypeId){
		this.imageTypeId = imageTypeId;
	}

	public void imageTypeId(String multirequestToken){
		setToken("imageTypeId", multirequestToken);
	}

	// imageObjectId:
	public Long getImageObjectId(){
		return this.imageObjectId;
	}
	public void setImageObjectId(Long imageObjectId){
		this.imageObjectId = imageObjectId;
	}

	public void imageObjectId(String multirequestToken){
		setToken("imageObjectId", multirequestToken);
	}

	// imageObjectType:
	public ImageObjectType getImageObjectType(){
		return this.imageObjectType;
	}
	public void setImageObjectType(ImageObjectType imageObjectType){
		this.imageObjectType = imageObjectType;
	}

	public void imageObjectType(String multirequestToken){
		setToken("imageObjectType", multirequestToken);
	}

	// status:
	public ImageStatus getStatus(){
		return this.status;
	}
	// url:
	public String getUrl(){
		return this.url;
	}
	// contentId:
	public String getContentId(){
		return this.contentId;
	}
	// isDefault:
	public Boolean getIsDefault(){
		return this.isDefault;
	}

	public Image() {
		super();
	}

	public Image(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		version = GsonParser.parseString(jsonObject.get("version"));
		imageTypeId = GsonParser.parseLong(jsonObject.get("imageTypeId"));
		imageObjectId = GsonParser.parseLong(jsonObject.get("imageObjectId"));
		imageObjectType = ImageObjectType.get(GsonParser.parseString(jsonObject.get("imageObjectType")));
		status = ImageStatus.get(GsonParser.parseString(jsonObject.get("status")));
		url = GsonParser.parseString(jsonObject.get("url"));
		contentId = GsonParser.parseString(jsonObject.get("contentId"));
		isDefault = GsonParser.parseBoolean(jsonObject.get("isDefault"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaImage");
		kparams.add("imageTypeId", this.imageTypeId);
		kparams.add("imageObjectId", this.imageObjectId);
		kparams.add("imageObjectType", this.imageObjectType);
		return kparams;
	}

}

