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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Report;
import com.kaltura.client.types.ReportFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ReportService {
	
	public static class GetReportBuilder extends RequestBuilder<Report, Report.Tokenizer, GetReportBuilder> {
		
		public GetReportBuilder(String udid) {
			super(Report.class, "report", "get");
			params.add("udid", udid);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	/**
	 * Return a device configuration retrieval log request for a specific device.
	 * 
	 * @param udid Device UDID
	 */
    public static GetReportBuilder get(String udid)  {
		return new GetReportBuilder(udid);
	}
	
	public static class ListReportBuilder extends ListResponseRequestBuilder<Report, Report.Tokenizer, ListReportBuilder> {
		
		public ListReportBuilder(ReportFilter filter, FilterPager pager) {
			super(Report.class, "report", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListReportBuilder list(ReportFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Return device configurations retrieval log. Supports paging and can be filtered
	  with the parameter &amp;quot;FromData&amp;quot;.
	 * 
	 * @param filter Filter option for from date (sec)
	 * @param pager Page size and index
	 */
    public static ListReportBuilder list(ReportFilter filter, FilterPager pager)  {
		return new ListReportBuilder(filter, pager);
	}
}
