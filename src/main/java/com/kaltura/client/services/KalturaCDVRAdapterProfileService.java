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
public class KalturaCDVRAdapterProfileService extends KalturaServiceBase {
    public KalturaCDVRAdapterProfileService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Insert new C-DVR adapter for partner  */
    public KalturaCDVRAdapterProfile add(KalturaCDVRAdapterProfile adapter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("adapter", adapter);
        this.kalturaClient.queueServiceCall("cdvradapterprofile", "add", kparams, KalturaCDVRAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaCDVRAdapterProfile.class, resultXmlElement);
    }

	/**  Delete C-DVR adapter by C-DVR adapter id  */
    public boolean delete(int adapterId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("adapterId", adapterId);
        this.kalturaClient.queueServiceCall("cdvradapterprofile", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Generate C-DVR adapter shared secret  */
    public KalturaCDVRAdapterProfile generateSharedSecret(int adapterId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("adapterId", adapterId);
        this.kalturaClient.queueServiceCall("cdvradapterprofile", "generateSharedSecret", kparams, KalturaCDVRAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaCDVRAdapterProfile.class, resultXmlElement);
    }

	/**  Returns all C-DVR adapters for partner  */
    public KalturaCDVRAdapterProfileListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("cdvradapterprofile", "list", kparams, KalturaCDVRAdapterProfileListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaCDVRAdapterProfileListResponse.class, resultXmlElement);
    }

	/**  Update C-DVR adapter details  */
    public KalturaCDVRAdapterProfile update(int adapterId, KalturaCDVRAdapterProfile adapter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("adapterId", adapterId);
        kparams.add("adapter", adapter);
        this.kalturaClient.queueServiceCall("cdvradapterprofile", "update", kparams, KalturaCDVRAdapterProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaCDVRAdapterProfile.class, resultXmlElement);
    }
}
