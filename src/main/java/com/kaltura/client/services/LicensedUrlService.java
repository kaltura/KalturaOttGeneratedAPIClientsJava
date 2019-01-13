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

import com.kaltura.client.types.LicensedUrl;
import com.kaltura.client.types.LicensedUrlBaseRequest;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class LicensedUrlService {
	
	public static class GetLicensedUrlBuilder extends RequestBuilder<LicensedUrl, LicensedUrl.Tokenizer, GetLicensedUrlBuilder> {
		
		public GetLicensedUrlBuilder(LicensedUrlBaseRequest request) {
			super(LicensedUrl.class, "licensedurl", "get");
			params.add("request", request);
		}
	}

	/**
	 * Get the URL for playing an asset - program, media or recording
	 * 
	 * @param request Licensed URL request parameters
	 */
    public static GetLicensedUrlBuilder get(LicensedUrlBaseRequest request)  {
		return new GetLicensedUrlBuilder(request);
	}
}
