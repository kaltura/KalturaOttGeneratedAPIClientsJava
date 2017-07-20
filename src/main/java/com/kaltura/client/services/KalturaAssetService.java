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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaServiceBase;
import com.kaltura.client.types.*;
import java.util.ArrayList;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.enums.*;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaAssetService extends KalturaServiceBase {
    public KalturaAssetService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaAssetCount count(ArrayList<KalturaAssetGroupBy> groupBy) throws KalturaApiException {
        return this.count(groupBy, null);
    }

	/**  Returns a group-by result for media or EPG according to given filter. Lists
	  values of each field and their respective count.  */
    public KalturaAssetCount count(ArrayList<KalturaAssetGroupBy> groupBy, KalturaSearchAssetFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("groupBy", groupBy);
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("asset", "count", kparams, KalturaAssetCount.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAssetCount.class, resultXmlElement);
    }

	/**  Returns media or EPG asset by media / EPG internal or external identifier  */
    public KalturaAsset get(String id, KalturaAssetReferenceType assetReferenceType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("assetReferenceType", assetReferenceType);
        this.kalturaClient.queueServiceCall("asset", "get", kparams, KalturaAsset.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAsset.class, resultXmlElement);
    }

	/**  This action delivers all data relevant for player  */
    public KalturaPlaybackContext getPlaybackContext(String assetId, KalturaAssetType assetType, KalturaPlaybackContextOptions contextDataParams) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("assetId", assetId);
        kparams.add("assetType", assetType);
        kparams.add("contextDataParams", contextDataParams);
        this.kalturaClient.queueServiceCall("asset", "getPlaybackContext", kparams, KalturaPlaybackContext.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaPlaybackContext.class, resultXmlElement);
    }

    public KalturaAssetListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaAssetListResponse list(KalturaAssetFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  Returns media or EPG assets. Filters by media identifiers or by EPG internal or
	  external identifier.  */
    public KalturaAssetListResponse list(KalturaAssetFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("asset", "list", kparams, KalturaAssetListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAssetListResponse.class, resultXmlElement);
    }
}
