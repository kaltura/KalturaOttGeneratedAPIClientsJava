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
import com.kaltura.client.enums.EntityAttribute;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(LabelFilter.Tokenizer.class)
public class LabelFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idIn();
		String labelEqual();
		String labelStartsWith();
		String entityAttributeEqual();
	}

	/**
	 * Comma-separated identifiers of labels
	 */
	private String idIn;
	/**
	 * Filter the label with this value
	 */
	private String labelEqual;
	/**
	 * Filter labels which start with this value
	 */
	private String labelStartsWith;
	/**
	 * Type of entity that labels are associated with
	 */
	private EntityAttribute entityAttributeEqual;

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

	// labelEqual:
	public String getLabelEqual(){
		return this.labelEqual;
	}
	public void setLabelEqual(String labelEqual){
		this.labelEqual = labelEqual;
	}

	public void labelEqual(String multirequestToken){
		setToken("labelEqual", multirequestToken);
	}

	// labelStartsWith:
	public String getLabelStartsWith(){
		return this.labelStartsWith;
	}
	public void setLabelStartsWith(String labelStartsWith){
		this.labelStartsWith = labelStartsWith;
	}

	public void labelStartsWith(String multirequestToken){
		setToken("labelStartsWith", multirequestToken);
	}

	// entityAttributeEqual:
	public EntityAttribute getEntityAttributeEqual(){
		return this.entityAttributeEqual;
	}
	public void setEntityAttributeEqual(EntityAttribute entityAttributeEqual){
		this.entityAttributeEqual = entityAttributeEqual;
	}

	public void entityAttributeEqual(String multirequestToken){
		setToken("entityAttributeEqual", multirequestToken);
	}


	public LabelFilter() {
		super();
	}

	public LabelFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		labelEqual = GsonParser.parseString(jsonObject.get("labelEqual"));
		labelStartsWith = GsonParser.parseString(jsonObject.get("labelStartsWith"));
		entityAttributeEqual = EntityAttribute.get(GsonParser.parseString(jsonObject.get("entityAttributeEqual")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLabelFilter");
		kparams.add("idIn", this.idIn);
		kparams.add("labelEqual", this.labelEqual);
		kparams.add("labelStartsWith", this.labelStartsWith);
		kparams.add("entityAttributeEqual", this.entityAttributeEqual);
		return kparams;
	}

}

