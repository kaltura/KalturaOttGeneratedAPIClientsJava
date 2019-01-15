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

import com.kaltura.client.types.ExportTask;
import com.kaltura.client.types.ExportTaskFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ExportTaskService {
	
	public static class AddExportTaskBuilder extends RequestBuilder<ExportTask, ExportTask.Tokenizer, AddExportTaskBuilder> {
		
		public AddExportTaskBuilder(ExportTask task) {
			super(ExportTask.class, "exporttask", "add");
			params.add("task", task);
		}
	}

	/**
	 * Adds a new bulk export task
	 * 
	 * @param task The task model to add
	 */
    public static AddExportTaskBuilder add(ExportTask task)  {
		return new AddExportTaskBuilder(task);
	}
	
	public static class DeleteExportTaskBuilder extends RequestBuilder<Boolean, String, DeleteExportTaskBuilder> {
		
		public DeleteExportTaskBuilder(long id) {
			super(Boolean.class, "exporttask", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Deletes an existing bulk export task by task identifier
	 * 
	 * @param id The identifier of the task to delete
	 */
    public static DeleteExportTaskBuilder delete(long id)  {
		return new DeleteExportTaskBuilder(id);
	}
	
	public static class GetExportTaskBuilder extends RequestBuilder<ExportTask, ExportTask.Tokenizer, GetExportTaskBuilder> {
		
		public GetExportTaskBuilder(long id) {
			super(ExportTask.class, "exporttask", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Gets an existing bulk export task by task identifier
	 * 
	 * @param id The identifier of the task to get
	 */
    public static GetExportTaskBuilder get(long id)  {
		return new GetExportTaskBuilder(id);
	}
	
	public static class ListExportTaskBuilder extends ListResponseRequestBuilder<ExportTask, ExportTask.Tokenizer, ListExportTaskBuilder> {
		
		public ListExportTaskBuilder(ExportTaskFilter filter) {
			super(ExportTask.class, "exporttask", "list");
			params.add("filter", filter);
		}
	}

	public static ListExportTaskBuilder list()  {
		return list(null);
	}

	/**
	 * Returns bulk export tasks by tasks identifiers
	 * 
	 * @param filter Bulk export tasks filter
	 */
    public static ListExportTaskBuilder list(ExportTaskFilter filter)  {
		return new ListExportTaskBuilder(filter);
	}
	
	public static class UpdateExportTaskBuilder extends RequestBuilder<ExportTask, ExportTask.Tokenizer, UpdateExportTaskBuilder> {
		
		public UpdateExportTaskBuilder(long id, ExportTask task) {
			super(ExportTask.class, "exporttask", "update");
			params.add("id", id);
			params.add("task", task);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Updates an existing bulk export task by task identifier
	 * 
	 * @param id The task id to update
	 * @param task The task model to update
	 */
    public static UpdateExportTaskBuilder update(long id, ExportTask task)  {
		return new UpdateExportTaskBuilder(id, task);
	}
}
