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

import com.kaltura.client.types.LineupChannelAsset;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class LineupService {
	
	public static class GetLineupBuilder extends ListResponseRequestBuilder<LineupChannelAsset, LineupChannelAsset.Tokenizer, GetLineupBuilder> {
		
		public GetLineupBuilder(int pageIndex, int pageSize) {
			super(LineupChannelAsset.class, "lineup", "get");
			params.add("pageIndex", pageIndex);
			params.add("pageSize", pageSize);
		}
		
		public void pageIndex(String multirequestToken) {
			params.add("pageIndex", multirequestToken);
		}
		
		public void pageSize(String multirequestToken) {
			params.add("pageSize", multirequestToken);
		}
	}

	/**
	 * Return regional lineup (list of lineup channel asset objects) based on the
	  requester session characteristics and his region.
	 * 
	 * @param pageIndex Page index - The page index to retrieve, (if it is not sent the default page
	 * size is 1).
	 * @param pageSize Page size - The page size to retrieve. Must be one of the follow numbers: 100,
	 * 200, 800, 1200, 1600 (if it is not sent the default page size is 500).
	 */
    public static GetLineupBuilder get(int pageIndex, int pageSize)  {
		return new GetLineupBuilder(pageIndex, pageSize);
	}
	
	public static class SendUpdatedNotificationLineupBuilder extends RequestBuilder<Boolean, String, SendUpdatedNotificationLineupBuilder> {
		
		public SendUpdatedNotificationLineupBuilder(String regionIds) {
			super(Boolean.class, "lineup", "sendUpdatedNotification");
			params.add("regionIds", regionIds);
		}
		
		public void regionIds(String multirequestToken) {
			params.add("regionIds", multirequestToken);
		}
	}

	/**
	 * Sends lineup update requested notification.
	 * 
	 * @param regionIds Region IDs separated by commas.
	 */
    public static SendUpdatedNotificationLineupBuilder sendUpdatedNotification(String regionIds)  {
		return new SendUpdatedNotificationLineupBuilder(regionIds);
	}
}
