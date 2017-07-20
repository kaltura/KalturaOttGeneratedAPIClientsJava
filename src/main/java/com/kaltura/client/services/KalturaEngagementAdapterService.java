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
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaEngagementAdapterService extends KalturaServiceBase {
    public KalturaEngagementAdapterService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Insert new Engagement adapter for partner  */
    public KalturaEngagementAdapter add(KalturaEngagementAdapter engagementAdapter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("engagementAdapter", engagementAdapter);
        this.kalturaClient.queueServiceCall("engagementadapter", "add", kparams, KalturaEngagementAdapter.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEngagementAdapter.class, resultXmlElement);
    }

	/**  Delete Engagement adapter by Engagement adapter id  */
    public boolean delete(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("engagementadapter", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Generate engagement adapter shared secret  */
    public KalturaEngagementAdapter generateSharedSecret(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("engagementadapter", "generateSharedSecret", kparams, KalturaEngagementAdapter.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEngagementAdapter.class, resultXmlElement);
    }

	/**  Returns all Engagement adapters for partner : id + name  */
    public KalturaEngagementAdapter get(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("engagementadapter", "get", kparams, KalturaEngagementAdapter.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEngagementAdapter.class, resultXmlElement);
    }

	/**  Returns all Engagement adapters for partner : id + name  */
    public KalturaEngagementAdapterListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("engagementadapter", "list", kparams, KalturaEngagementAdapterListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEngagementAdapterListResponse.class, resultXmlElement);
    }

	/**  Update Engagement adapter details  */
    public KalturaEngagementAdapter update(int id, KalturaEngagementAdapter engagementAdapter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("engagementAdapter", engagementAdapter);
        this.kalturaClient.queueServiceCall("engagementadapter", "update", kparams, KalturaEngagementAdapter.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEngagementAdapter.class, resultXmlElement);
    }
}
