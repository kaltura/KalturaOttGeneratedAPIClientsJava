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

import com.kaltura.client.types.BulkUploadStatistics;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class BulkUploadStatisticsService {
	
	public static class GetBulkUploadStatisticsBuilder extends RequestBuilder<BulkUploadStatistics, BulkUploadStatistics.Tokenizer, GetBulkUploadStatisticsBuilder> {
		
		public GetBulkUploadStatisticsBuilder(String bulkObjectTypeEqual, long createDateGreaterThanOrEqual) {
			super(BulkUploadStatistics.class, "bulkuploadstatistics", "get");
			params.add("bulkObjectTypeEqual", bulkObjectTypeEqual);
			params.add("createDateGreaterThanOrEqual", createDateGreaterThanOrEqual);
		}
		
		public void bulkObjectTypeEqual(String multirequestToken) {
			params.add("bulkObjectTypeEqual", multirequestToken);
		}
		
		public void createDateGreaterThanOrEqual(String multirequestToken) {
			params.add("createDateGreaterThanOrEqual", multirequestToken);
		}
	}

	/**
	 * Get BulkUploadStatistics count summary by status
	 * 
	 * @param bulkObjectTypeEqual bulkUploadObject for status summary
	 * @param createDateGreaterThanOrEqual date created filter
	 */
    public static GetBulkUploadStatisticsBuilder get(String bulkObjectTypeEqual, long createDateGreaterThanOrEqual)  {
		return new GetBulkUploadStatisticsBuilder(bulkObjectTypeEqual, createDateGreaterThanOrEqual);
	}
}
