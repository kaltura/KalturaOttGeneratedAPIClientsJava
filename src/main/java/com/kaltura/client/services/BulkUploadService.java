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
package com.kaltura.client.services;

import com.kaltura.client.types.BulkUpload;
import com.kaltura.client.types.BulkUploadFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class BulkUploadService {
	
	public static class GetBulkUploadBuilder extends RequestBuilder<BulkUpload, BulkUpload.Tokenizer, GetBulkUploadBuilder> {
		
		public GetBulkUploadBuilder(long id) {
			super(BulkUpload.class, "bulkupload", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get BulkUpload by ID
	 * 
	 * @param id ID to get
	 */
    public static GetBulkUploadBuilder get(long id)  {
		return new GetBulkUploadBuilder(id);
	}
	
	public static class ListBulkUploadBuilder extends ListResponseRequestBuilder<BulkUpload, BulkUpload.Tokenizer, ListBulkUploadBuilder> {
		
		public ListBulkUploadBuilder(BulkUploadFilter filter, FilterPager pager) {
			super(BulkUpload.class, "bulkupload", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListBulkUploadBuilder list(BulkUploadFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get list of KalturaBulkUpload by filter
	 * 
	 * @param filter Filtering the bulk action request
	 * @param pager Paging the request
	 */
    public static ListBulkUploadBuilder list(BulkUploadFilter filter, FilterPager pager)  {
		return new ListBulkUploadBuilder(filter, pager);
	}
}
