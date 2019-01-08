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

import com.kaltura.client.types.Meta;
import com.kaltura.client.types.MetaFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class MetaService {
	
	public static class AddMetaBuilder extends RequestBuilder<Meta, Meta.Tokenizer, AddMetaBuilder> {
		
		public AddMetaBuilder(Meta meta) {
			super(Meta.class, "meta", "add");
			params.add("meta", meta);
		}
	}

	/**
	 * Add a new meta
	 * 
	 * @param meta Meta Object
	 */
    public static AddMetaBuilder add(Meta meta)  {
		return new AddMetaBuilder(meta);
	}
	
	public static class DeleteMetaBuilder extends RequestBuilder<Boolean, String, DeleteMetaBuilder> {
		
		public DeleteMetaBuilder(long id) {
			super(Boolean.class, "meta", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing meta
	 * 
	 * @param id Meta Identifier
	 */
    public static DeleteMetaBuilder delete(long id)  {
		return new DeleteMetaBuilder(id);
	}
	
	public static class ListMetaBuilder extends ListResponseRequestBuilder<Meta, Meta.Tokenizer, ListMetaBuilder> {
		
		public ListMetaBuilder(MetaFilter filter) {
			super(Meta.class, "meta", "list");
			params.add("filter", filter);
		}
	}

	public static ListMetaBuilder list()  {
		return list(null);
	}

	/**
	 * Return a list of metas for the account with optional filter
	 * 
	 * @param filter Meta filter
	 */
    public static ListMetaBuilder list(MetaFilter filter)  {
		return new ListMetaBuilder(filter);
	}
	
	public static class UpdateMetaBuilder extends RequestBuilder<Meta, Meta.Tokenizer, UpdateMetaBuilder> {
		
		public UpdateMetaBuilder(long id, Meta meta) {
			super(Meta.class, "meta", "update");
			params.add("id", id);
			params.add("meta", meta);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update an existing meta
	 * 
	 * @param id Meta identifier
	 * @param meta Meta
	 */
    public static UpdateMetaBuilder update(long id, Meta meta)  {
		return new UpdateMetaBuilder(id, meta);
	}
}
