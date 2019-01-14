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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ChannelsFilter.Tokenizer.class)
public class ChannelsFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idEqual();
		String mediaIdEqual();
		String nameEqual();
		String nameStartsWith();
	}

	/**
	 * channel identifier to filter by
	 */
	private Integer idEqual;
	/**
	 * media identifier to filter by
	 */
	private Long mediaIdEqual;
	/**
	 * Exact channel name to filter by
	 */
	private String nameEqual;
	/**
	 * Channel name starts with (auto-complete)
	 */
	private String nameStartsWith;

	// idEqual:
	public Integer getIdEqual(){
		return this.idEqual;
	}
	public void setIdEqual(Integer idEqual){
		this.idEqual = idEqual;
	}

	public void idEqual(String multirequestToken){
		setToken("idEqual", multirequestToken);
	}

	// mediaIdEqual:
	public Long getMediaIdEqual(){
		return this.mediaIdEqual;
	}
	public void setMediaIdEqual(Long mediaIdEqual){
		this.mediaIdEqual = mediaIdEqual;
	}

	public void mediaIdEqual(String multirequestToken){
		setToken("mediaIdEqual", multirequestToken);
	}

	// nameEqual:
	public String getNameEqual(){
		return this.nameEqual;
	}
	public void setNameEqual(String nameEqual){
		this.nameEqual = nameEqual;
	}

	public void nameEqual(String multirequestToken){
		setToken("nameEqual", multirequestToken);
	}

	// nameStartsWith:
	public String getNameStartsWith(){
		return this.nameStartsWith;
	}
	public void setNameStartsWith(String nameStartsWith){
		this.nameStartsWith = nameStartsWith;
	}

	public void nameStartsWith(String multirequestToken){
		setToken("nameStartsWith", multirequestToken);
	}


	public ChannelsFilter() {
		super();
	}

	public ChannelsFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseInt(jsonObject.get("idEqual"));
		mediaIdEqual = GsonParser.parseLong(jsonObject.get("mediaIdEqual"));
		nameEqual = GsonParser.parseString(jsonObject.get("nameEqual"));
		nameStartsWith = GsonParser.parseString(jsonObject.get("nameStartsWith"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaChannelsFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("mediaIdEqual", this.mediaIdEqual);
		kparams.add("nameEqual", this.nameEqual);
		kparams.add("nameStartsWith", this.nameStartsWith);
		return kparams;
	}

}

