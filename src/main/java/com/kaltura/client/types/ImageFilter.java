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
// Copyright (C) 2006-2021  Kaltura Inc.
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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ImageFilter.Tokenizer.class)
public class ImageFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idIn();
		String imageObjectIdEqual();
		String imageObjectTypeEqual();
		String isDefaultEqual();
		String imageObjectIdIn();
	}

	/**
	 * IDs to filter by
	 */
	private String idIn;
	/**
	 * ID of the object the is related to, to filter by
	 */
	private Long imageObjectIdEqual;
	/**
	 * Type of the object the image is related to, to filter by
	 */
	private ImageObjectType imageObjectTypeEqual;
	/**
	 * Filter images that are default on at least on image type or not default at any
	 */
	private Boolean isDefaultEqual;
	/**
	 * Comma separated imageObject ids list
	 */
	private String imageObjectIdIn;

	// idIn:
	public String getIdIn(){
		return this.idIn;
	}
	public void setIdIn(String idIn){
		this.idIn = idIn;
	}

	public void idIn(String multirequestToken){
		setToken("idIn", multirequestToken);
	}

	// imageObjectIdEqual:
	public Long getImageObjectIdEqual(){
		return this.imageObjectIdEqual;
	}
	public void setImageObjectIdEqual(Long imageObjectIdEqual){
		this.imageObjectIdEqual = imageObjectIdEqual;
	}

	public void imageObjectIdEqual(String multirequestToken){
		setToken("imageObjectIdEqual", multirequestToken);
	}

	// imageObjectTypeEqual:
	public ImageObjectType getImageObjectTypeEqual(){
		return this.imageObjectTypeEqual;
	}
	public void setImageObjectTypeEqual(ImageObjectType imageObjectTypeEqual){
		this.imageObjectTypeEqual = imageObjectTypeEqual;
	}

	public void imageObjectTypeEqual(String multirequestToken){
		setToken("imageObjectTypeEqual", multirequestToken);
	}

	// isDefaultEqual:
	public Boolean getIsDefaultEqual(){
		return this.isDefaultEqual;
	}
	public void setIsDefaultEqual(Boolean isDefaultEqual){
		this.isDefaultEqual = isDefaultEqual;
	}

	public void isDefaultEqual(String multirequestToken){
		setToken("isDefaultEqual", multirequestToken);
	}

	// imageObjectIdIn:
	public String getImageObjectIdIn(){
		return this.imageObjectIdIn;
	}
	public void setImageObjectIdIn(String imageObjectIdIn){
		this.imageObjectIdIn = imageObjectIdIn;
	}

	public void imageObjectIdIn(String multirequestToken){
		setToken("imageObjectIdIn", multirequestToken);
	}


	public ImageFilter() {
		super();
	}

	public ImageFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		imageObjectIdEqual = GsonParser.parseLong(jsonObject.get("imageObjectIdEqual"));
		imageObjectTypeEqual = ImageObjectType.get(GsonParser.parseString(jsonObject.get("imageObjectTypeEqual")));
		isDefaultEqual = GsonParser.parseBoolean(jsonObject.get("isDefaultEqual"));
		imageObjectIdIn = GsonParser.parseString(jsonObject.get("imageObjectIdIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaImageFilter");
		kparams.add("idIn", this.idIn);
		kparams.add("imageObjectIdEqual", this.imageObjectIdEqual);
		kparams.add("imageObjectTypeEqual", this.imageObjectTypeEqual);
		kparams.add("isDefaultEqual", this.isDefaultEqual);
		kparams.add("imageObjectIdIn", this.imageObjectIdIn);
		return kparams;
	}

}

