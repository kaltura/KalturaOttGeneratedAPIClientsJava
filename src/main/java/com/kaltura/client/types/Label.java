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
import com.kaltura.client.enums.EntityAttribute;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Label.Tokenizer.class)
public class Label extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String value();
		String entityAttribute();
	}

	/**
	 * Label identifier
	 */
	private Long id;
	/**
	 * Label value. It must be unique in the context of entityAttribute
	 */
	private String value;
	/**
	 * Identifier of entity to which label belongs
	 */
	private EntityAttribute entityAttribute;

	// id:
	public Long getId(){
		return this.id;
	}
	// value:
	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}

	public void value(String multirequestToken){
		setToken("value", multirequestToken);
	}

	// entityAttribute:
	public EntityAttribute getEntityAttribute(){
		return this.entityAttribute;
	}
	public void setEntityAttribute(EntityAttribute entityAttribute){
		this.entityAttribute = entityAttribute;
	}

	public void entityAttribute(String multirequestToken){
		setToken("entityAttribute", multirequestToken);
	}


	public Label() {
		super();
	}

	public Label(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		value = GsonParser.parseString(jsonObject.get("value"));
		entityAttribute = EntityAttribute.get(GsonParser.parseString(jsonObject.get("entityAttribute")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLabel");
		kparams.add("value", this.value);
		kparams.add("entityAttribute", this.entityAttribute);
		return kparams;
	}

}

