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
public class KalturaOssAdapterProfileService extends KalturaServiceBase {
    public KalturaOssAdapterProfileService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Insert new OSS adapter for partner  */
    public KalturaOSSAdapterProfile add(KalturaOSSAdapterProfile ossAdapter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("ossAdapter", ossAdapter);
        this.kalturaClient.queueServiceCall("ossadapterprofile", "add", kparams, KalturaOSSAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOSSAdapterProfile.class, resultXmlElement);
    }

	/**  Delete OSS adapter by OSS adapter id  */
    public boolean delete(int ossAdapterId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("ossAdapterId", ossAdapterId);
        this.kalturaClient.queueServiceCall("ossadapterprofile", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Generate oss adapter shared secret  */
    public KalturaOSSAdapterProfile generateSharedSecret(int ossAdapterId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("ossAdapterId", ossAdapterId);
        this.kalturaClient.queueServiceCall("ossadapterprofile", "generateSharedSecret", kparams, KalturaOSSAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOSSAdapterProfile.class, resultXmlElement);
    }

	/**  Returns all OSS adapters for partner : id + name  */
    public KalturaOSSAdapterProfile get(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("ossadapterprofile", "get", kparams, KalturaOSSAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOSSAdapterProfile.class, resultXmlElement);
    }

	/**  Returns all OSS adapters for partner : id + name  */
    public KalturaOSSAdapterProfileListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("ossadapterprofile", "list", kparams, KalturaOSSAdapterProfileListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOSSAdapterProfileListResponse.class, resultXmlElement);
    }

	/**  Update OSS adapter details  */
    public KalturaOSSAdapterProfile update(int ossAdapterId, KalturaOSSAdapterProfile ossAdapter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("ossAdapterId", ossAdapterId);
        kparams.add("ossAdapter", ossAdapter);
        this.kalturaClient.queueServiceCall("ossadapterprofile", "update", kparams, KalturaOSSAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOSSAdapterProfile.class, resultXmlElement);
    }
}
