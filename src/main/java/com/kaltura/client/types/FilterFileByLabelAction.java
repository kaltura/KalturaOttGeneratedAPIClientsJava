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

/**
 * FilterFile By Label
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(FilterFileByLabelAction.Tokenizer.class)
public abstract class FilterFileByLabelAction extends FilterAction {
	
	public interface Tokenizer extends FilterAction.Tokenizer {
		String labelIn();
	}

	/**
	 * List of comma separated labels
	 */
	private String labelIn;

	// labelIn:
	public String getLabelIn(){
		return this.labelIn;
	}
	public void setLabelIn(String labelIn){
		this.labelIn = labelIn;
	}

	public void labelIn(String multirequestToken){
		setToken("labelIn", multirequestToken);
	}


	public FilterFileByLabelAction() {
		super();
	}

	public FilterFileByLabelAction(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		labelIn = GsonParser.parseString(jsonObject.get("labelIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaFilterFileByLabelAction");
		kparams.add("labelIn", this.labelIn);
		return kparams;
	}

}

