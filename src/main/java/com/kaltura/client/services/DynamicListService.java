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

import com.kaltura.client.FileHolder;
import com.kaltura.client.Files;
import com.kaltura.client.types.BulkUpload;
import com.kaltura.client.types.BulkUploadDynamicListData;
import com.kaltura.client.types.BulkUploadExcelJobData;
import com.kaltura.client.types.DynamicList;
import com.kaltura.client.types.DynamicListFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class DynamicListService {
	
	public static class AddDynamicListBuilder extends RequestBuilder<DynamicList, DynamicList.Tokenizer, AddDynamicListBuilder> {
		
		public AddDynamicListBuilder(DynamicList objectToAdd) {
			super(DynamicList.class, "dynamiclist", "add");
			params.add("objectToAdd", objectToAdd);
		}
	}

	/**
	 * Add new KalturaDynamicList
	 * 
	 * @param objectToAdd KalturaDynamicList Object to add
	 */
    public static AddDynamicListBuilder add(DynamicList objectToAdd)  {
		return new AddDynamicListBuilder(objectToAdd);
	}
	
	public static class AddFromBulkUploadDynamicListBuilder extends RequestBuilder<BulkUpload, BulkUpload.Tokenizer, AddFromBulkUploadDynamicListBuilder> {
		
		public AddFromBulkUploadDynamicListBuilder(FileHolder fileData, BulkUploadExcelJobData jobData, BulkUploadDynamicListData bulkUploadData) {
			super(BulkUpload.class, "dynamiclist", "addFromBulkUpload");
			files = new Files();
			files.add("fileData", fileData);
			params.add("jobData", jobData);
			params.add("bulkUploadData", bulkUploadData);
		}
	}

	public static AddFromBulkUploadDynamicListBuilder addFromBulkUpload(File fileData, BulkUploadExcelJobData jobData, BulkUploadDynamicListData bulkUploadData)  {
		return addFromBulkUpload(new FileHolder(fileData), jobData, bulkUploadData);
	}

	public static AddFromBulkUploadDynamicListBuilder addFromBulkUpload(InputStream fileData, String fileDataMimeType, String fileDataName, long fileDataSize, BulkUploadExcelJobData jobData, BulkUploadDynamicListData bulkUploadData)  {
		return addFromBulkUpload(new FileHolder(fileData, fileDataMimeType, fileDataName, fileDataSize), jobData, bulkUploadData);
	}

	public static AddFromBulkUploadDynamicListBuilder addFromBulkUpload(FileInputStream fileData, String fileDataMimeType, String fileDataName, BulkUploadExcelJobData jobData, BulkUploadDynamicListData bulkUploadData)  {
		return addFromBulkUpload(new FileHolder(fileData, fileDataMimeType, fileDataName), jobData, bulkUploadData);
	}

	/**
	 * Add new bulk upload batch job Conversion profile id can be specified in the API.
	 * 
	 * @param fileData fileData
	 * @param jobData jobData
	 * @param bulkUploadData bulkUploadData
	 */
    public static AddFromBulkUploadDynamicListBuilder addFromBulkUpload(FileHolder fileData, BulkUploadExcelJobData jobData, BulkUploadDynamicListData bulkUploadData)  {
		return new AddFromBulkUploadDynamicListBuilder(fileData, jobData, bulkUploadData);
	}
	
	public static class DeleteDynamicListBuilder extends NullRequestBuilder {
		
		public DeleteDynamicListBuilder(long id) {
			super("dynamiclist", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete existing DynamicList
	 * 
	 * @param id DynamicList identifier
	 */
    public static DeleteDynamicListBuilder delete(long id)  {
		return new DeleteDynamicListBuilder(id);
	}
	
	public static class ListDynamicListBuilder extends ListResponseRequestBuilder<DynamicList, DynamicList.Tokenizer, ListDynamicListBuilder> {
		
		public ListDynamicListBuilder(DynamicListFilter filter, FilterPager pager) {
			super(DynamicList.class, "dynamiclist", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListDynamicListBuilder list(DynamicListFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Returns the list of available DynamicList
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListDynamicListBuilder list(DynamicListFilter filter, FilterPager pager)  {
		return new ListDynamicListBuilder(filter, pager);
	}
	
	public static class UpdateDynamicListBuilder extends RequestBuilder<DynamicList, DynamicList.Tokenizer, UpdateDynamicListBuilder> {
		
		public UpdateDynamicListBuilder(long id, DynamicList objectToUpdate) {
			super(DynamicList.class, "dynamiclist", "update");
			params.add("id", id);
			params.add("objectToUpdate", objectToUpdate);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update existing KalturaDynamicList
	 * 
	 * @param id id of KalturaDynamicList to update
	 * @param objectToUpdate KalturaDynamicList Object to update
	 */
    public static UpdateDynamicListBuilder update(long id, DynamicList objectToUpdate)  {
		return new UpdateDynamicListBuilder(id, objectToUpdate);
	}
}
