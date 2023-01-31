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
import com.kaltura.client.enums.ChannelStruct;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ChannelSearchByKsqlFilter.Tokenizer.class)
public class ChannelSearchByKsqlFilter extends ChannelsBaseFilter {
	
	public interface Tokenizer extends ChannelsBaseFilter.Tokenizer {
		String kSql();
		String channelStructEqual();
	}

	/**
	 * KSQL expression
	 */
	private String kSql;
	/**
	 * channel struct
	 */
	private ChannelStruct channelStructEqual;

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

	// channelStructEqual:
	public ChannelStruct getChannelStructEqual(){
		return this.channelStructEqual;
	}
	public void setChannelStructEqual(ChannelStruct channelStructEqual){
		this.channelStructEqual = channelStructEqual;
	}

	public void channelStructEqual(String multirequestToken){
		setToken("channelStructEqual", multirequestToken);
	}


	public ChannelSearchByKsqlFilter() {
		super();
	}

	public ChannelSearchByKsqlFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		kSql = GsonParser.parseString(jsonObject.get("kSql"));
		channelStructEqual = ChannelStruct.get(GsonParser.parseString(jsonObject.get("channelStructEqual")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaChannelSearchByKsqlFilter");
		kparams.add("kSql", this.kSql);
		kparams.add("channelStructEqual", this.channelStructEqual);
		return kparams;
	}

}

