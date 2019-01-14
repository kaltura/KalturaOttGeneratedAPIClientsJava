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

import com.kaltura.client.types.Ppv;
import com.kaltura.client.types.PpvFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PpvService {
	
	public static class GetPpvBuilder extends RequestBuilder<Ppv, Ppv.Tokenizer, GetPpvBuilder> {
		
		public GetPpvBuilder(long id) {
			super(Ppv.class, "ppv", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Returns ppv object by internal identifier
	 * 
	 * @param id ppv identifier
	 */
    public static GetPpvBuilder get(long id)  {
		return new GetPpvBuilder(id);
	}
	
	public static class ListPpvBuilder extends ListResponseRequestBuilder<Ppv, Ppv.Tokenizer, ListPpvBuilder> {
		
		public ListPpvBuilder(PpvFilter filter) {
			super(Ppv.class, "ppv", "list");
			params.add("filter", filter);
		}
	}

	public static ListPpvBuilder list()  {
		return list(null);
	}

	/**
	 * Returns all ppv objects
	 * 
	 * @param filter Filter parameters for filtering out the result
	 */
    public static ListPpvBuilder list(PpvFilter filter)  {
		return new ListPpvBuilder(filter);
	}
}
