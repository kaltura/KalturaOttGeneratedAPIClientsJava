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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CategoryItemSearchFilter.Tokenizer.class)
public class CategoryItemSearchFilter extends CategoryItemFilter {
	
	public interface Tokenizer extends CategoryItemFilter.Tokenizer {
		String kSql();
		String rootOnly();
	}

	/**
	 * KSQL expression
	 */
	private String kSql;
	/**
	 * Root only
	 */
	private Boolean rootOnly;

	// kSql:
	public String getKSql(){
		return this.kSql;
	}
	public void setKSql(String kSql){
		this.kSql = kSql;
	}

	public void kSql(String multirequestToken){
		setToken("kSql", multirequestToken);
	}

	// rootOnly:
	public Boolean getRootOnly(){
		return this.rootOnly;
	}
	public void setRootOnly(Boolean rootOnly){
		this.rootOnly = rootOnly;
	}

	public void rootOnly(String multirequestToken){
		setToken("rootOnly", multirequestToken);
	}


	public CategoryItemSearchFilter() {
		super();
	}

	public CategoryItemSearchFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		kSql = GsonParser.parseString(jsonObject.get("kSql"));
		rootOnly = GsonParser.parseBoolean(jsonObject.get("rootOnly"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCategoryItemSearchFilter");
		kparams.add("kSql", this.kSql);
		kparams.add("rootOnly", this.rootOnly);
		return kparams;
	}

}

