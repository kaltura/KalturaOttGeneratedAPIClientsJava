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
import com.kaltura.client.types.IngestByCompoundFilter;
import com.kaltura.client.types.IngestByIdsFilter;
import com.kaltura.client.types.IngestEpg;
import com.kaltura.client.types.IngestStatusPartnerConfiguration;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class IngestStatusService {
	
	public static class GetEpgListIngestStatusBuilder extends ListResponseRequestBuilder<IngestEpg, IngestEpg.Tokenizer, GetEpgListIngestStatusBuilder> {
		
		public GetEpgListIngestStatusBuilder(IngestByIdsFilter idsFilter, IngestByCompoundFilter filter, FilterPager pager) {
			super(IngestEpg.class, "ingeststatus", "getEpgList");
			params.add("idsFilter", idsFilter);
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static GetEpgListIngestStatusBuilder getEpgList()  {
		return getEpgList(null);
	}

	public static GetEpgListIngestStatusBuilder getEpgList(IngestByIdsFilter idsFilter)  {
		return getEpgList(idsFilter, null);
	}

	public static GetEpgListIngestStatusBuilder getEpgList(IngestByIdsFilter idsFilter, IngestByCompoundFilter filter)  {
		return getEpgList(idsFilter, filter, null);
	}

	/**
	 * Response with list of ingest jobs.
	 * 
	 * @param idsFilter Filter pager
	 * @param filter Filter pager
	 * @param pager Filter pager
	 */
    public static GetEpgListIngestStatusBuilder getEpgList(IngestByIdsFilter idsFilter, IngestByCompoundFilter filter, FilterPager pager)  {
		return new GetEpgListIngestStatusBuilder(idsFilter, filter, pager);
	}
	
	public static class GetPartnerConfigurationIngestStatusBuilder extends RequestBuilder<IngestStatusPartnerConfiguration, IngestStatusPartnerConfiguration.Tokenizer, GetPartnerConfigurationIngestStatusBuilder> {
		
		public GetPartnerConfigurationIngestStatusBuilder() {
			super(IngestStatusPartnerConfiguration.class, "ingeststatus", "getPartnerConfiguration");
		}
	}

	/**
	 * Returns Core Ingest service partner configurations
	 */
    public static GetPartnerConfigurationIngestStatusBuilder getPartnerConfiguration()  {
		return new GetPartnerConfigurationIngestStatusBuilder();
	}
	
	public static class UpdatePartnerConfigurationIngestStatusBuilder extends NullRequestBuilder {
		
		public UpdatePartnerConfigurationIngestStatusBuilder(IngestStatusPartnerConfiguration config) {
			super("ingeststatus", "updatePartnerConfiguration");
			params.add("config", config);
		}
	}

	/**
	 * Returns Core Ingest service partner configurations
	 * 
	 * @param config the partner config updates
	 */
    public static UpdatePartnerConfigurationIngestStatusBuilder updatePartnerConfiguration(IngestStatusPartnerConfiguration config)  {
		return new UpdatePartnerConfigurationIngestStatusBuilder(config);
	}
}
