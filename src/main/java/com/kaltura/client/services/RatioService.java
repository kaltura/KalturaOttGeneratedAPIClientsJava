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
package com.kaltura.client.services;

import com.kaltura.client.types.Ratio;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class RatioService {
	
	public static class AddRatioBuilder extends RequestBuilder<Ratio, Ratio.Tokenizer, AddRatioBuilder> {
		
		public AddRatioBuilder(Ratio ratio) {
			super(Ratio.class, "ratio", "add");
			params.add("ratio", ratio);
		}
	}

	/**
	 * Add new group ratio
	 * 
	 * @param ratio Ratio to add for the partner
	 */
    public static AddRatioBuilder add(Ratio ratio)  {
		return new AddRatioBuilder(ratio);
	}
	
	public static class ListRatioBuilder extends ListResponseRequestBuilder<Ratio, Ratio.Tokenizer, ListRatioBuilder> {
		
		public ListRatioBuilder() {
			super(Ratio.class, "ratio", "list");
		}
	}

	/**
	 * Get the list of available ratios
	 */
    public static ListRatioBuilder list()  {
		return new ListRatioBuilder();
	}
	
	public static class UpdateRatioBuilder extends RequestBuilder<Ratio, Ratio.Tokenizer, UpdateRatioBuilder> {
		
		public UpdateRatioBuilder(long id, Ratio ratio) {
			super(Ratio.class, "ratio", "update");
			params.add("id", id);
			params.add("ratio", ratio);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update group ratio&amp;#39;s PrecisionPrecentage
	 * 
	 * @param id The ratio ID
	 * @param ratio Ratio to update for the partner
	 */
    public static UpdateRatioBuilder update(long id, Ratio ratio)  {
		return new UpdateRatioBuilder(id, ratio);
	}
}
