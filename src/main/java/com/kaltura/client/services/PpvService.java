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
package com.kaltura.client.services;

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Ppv;
import com.kaltura.client.types.PpvFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PpvService {
	
	public static class AddPpvBuilder extends RequestBuilder<Ppv, Ppv.Tokenizer, AddPpvBuilder> {
		
		public AddPpvBuilder(Ppv ppv) {
			super(Ppv.class, "ppv", "add");
			params.add("ppv", ppv);
		}
	}

	/**
	 * Add new ppv
	 * 
	 * @param ppv ppv objec
	 */
    public static AddPpvBuilder add(Ppv ppv)  {
		return new AddPpvBuilder(ppv);
	}
	
	public static class DeletePpvBuilder extends RequestBuilder<Boolean, String, DeletePpvBuilder> {
		
		public DeletePpvBuilder(long id) {
			super(Boolean.class, "ppv", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete Ppv
	 * 
	 * @param id Ppv id
	 */
    public static DeletePpvBuilder delete(long id)  {
		return new DeletePpvBuilder(id);
	}
	
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
		
		public ListPpvBuilder(PpvFilter filter, FilterPager pager) {
			super(Ppv.class, "ppv", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListPpvBuilder list()  {
		return list(null);
	}

	public static ListPpvBuilder list(PpvFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns all ppv objects
	 * 
	 * @param filter Filter parameters for filtering out the result
	 * @param pager Page size and index
	 */
    public static ListPpvBuilder list(PpvFilter filter, FilterPager pager)  {
		return new ListPpvBuilder(filter, pager);
	}
	
	public static class UpdatePpvBuilder extends RequestBuilder<Ppv, Ppv.Tokenizer, UpdatePpvBuilder> {
		
		public UpdatePpvBuilder(int id, Ppv ppv) {
			super(Ppv.class, "ppv", "update");
			params.add("id", id);
			params.add("ppv", ppv);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update ppv
	 * 
	 * @param id ppv id
	 * @param ppv ppv Object
	 */
    public static UpdatePpvBuilder update(int id, Ppv ppv)  {
		return new UpdatePpvBuilder(id, ppv);
	}
}
