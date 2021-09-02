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

import com.kaltura.client.types.PreviewModule;
import com.kaltura.client.types.PreviewModuleFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PreviewModuleService {
	
	public static class AddPreviewModuleBuilder extends RequestBuilder<PreviewModule, PreviewModule.Tokenizer, AddPreviewModuleBuilder> {
		
		public AddPreviewModuleBuilder(PreviewModule previewModule) {
			super(PreviewModule.class, "previewmodule", "add");
			params.add("previewModule", previewModule);
		}
	}

	/**
	 * Insert new PreviewModule for partner
	 * 
	 * @param previewModule Preview module object
	 */
    public static AddPreviewModuleBuilder add(PreviewModule previewModule)  {
		return new AddPreviewModuleBuilder(previewModule);
	}
	
	public static class DeletePreviewModuleBuilder extends RequestBuilder<Boolean, String, DeletePreviewModuleBuilder> {
		
		public DeletePreviewModuleBuilder(long id) {
			super(Boolean.class, "previewmodule", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Internal API !!! Delete PreviewModule
	 * 
	 * @param id PreviewModule id
	 */
    public static DeletePreviewModuleBuilder delete(long id)  {
		return new DeletePreviewModuleBuilder(id);
	}
	
	public static class ListPreviewModuleBuilder extends ListResponseRequestBuilder<PreviewModule, PreviewModule.Tokenizer, ListPreviewModuleBuilder> {
		
		public ListPreviewModuleBuilder(PreviewModuleFilter filter) {
			super(PreviewModule.class, "previewmodule", "list");
			params.add("filter", filter);
		}
	}

	public static ListPreviewModuleBuilder list()  {
		return list(null);
	}

	/**
	 * Returns all PreviewModule
	 * 
	 * @param filter Filter
	 */
    public static ListPreviewModuleBuilder list(PreviewModuleFilter filter)  {
		return new ListPreviewModuleBuilder(filter);
	}
	
	public static class UpdatePreviewModuleBuilder extends RequestBuilder<PreviewModule, PreviewModule.Tokenizer, UpdatePreviewModuleBuilder> {
		
		public UpdatePreviewModuleBuilder(long id, PreviewModule previewModule) {
			super(PreviewModule.class, "previewmodule", "update");
			params.add("id", id);
			params.add("previewModule", previewModule);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update PreviewModule
	 * 
	 * @param id PreviewModule id
	 * @param previewModule PreviewModule
	 */
    public static UpdatePreviewModuleBuilder update(long id, PreviewModule previewModule)  {
		return new UpdatePreviewModuleBuilder(id, previewModule);
	}
}
