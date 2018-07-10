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

import com.kaltura.client.types.Bulk;
import com.kaltura.client.types.BulkFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class BulkService {
	
	public static class ListBulkBuilder extends ListResponseRequestBuilder<Bulk, Bulk.Tokenizer, ListBulkBuilder> {
		
		public ListBulkBuilder(BulkFilter filter, FilterPager pager) {
			super(Bulk.class, "bulk", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListBulkBuilder list()  {
		return list(null);
	}

	public static ListBulkBuilder list(BulkFilter filter)  {
		return list(filter, null);
	}

	/**
	 * List bulk actions
	 * 
	 * @param filter Filtering the bulk action request
	 * @param pager Paging the request
	 */
    public static ListBulkBuilder list(BulkFilter filter, FilterPager pager)  {
		return new ListBulkBuilder(filter, pager);
	}
	
	public static class ServeLogBulkBuilder extends RequestBuilder<Bulk, Bulk.Tokenizer, ServeLogBulkBuilder> {
		
		public ServeLogBulkBuilder(long id) {
			super(Bulk.class, "bulk", "serveLog");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * ServeLog action returns the log file for the bulk action
	 * 
	 * @param id bulk action id
	 */
    public static ServeLogBulkBuilder serveLog(long id)  {
		return new ServeLogBulkBuilder(id);
	}
}
