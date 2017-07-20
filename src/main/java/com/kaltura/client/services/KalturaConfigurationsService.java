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
public class KalturaConfigurationsService extends KalturaServiceBase {
    public KalturaConfigurationsService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Add a new device configuration to a configuration group  */
    public KalturaConfigurations add(KalturaConfigurations configurations) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("configurations", configurations);
        this.kalturaClient.queueServiceCall("configurations", "add", kparams, KalturaConfigurations.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurations.class, resultXmlElement);
    }

	/**  Delete a device configuration  */
    public boolean delete(String id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("configurations", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Return the device configuration  */
    public KalturaConfigurations get(String id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("configurations", "get", kparams, KalturaConfigurations.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurations.class, resultXmlElement);
    }

	/**  Return a list of device configurations of a configuration group  */
    public KalturaConfigurationsListResponse list(KalturaConfigurationsFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("configurations", "list", kparams, KalturaConfigurationsListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationsListResponse.class, resultXmlElement);
    }

    public String serveByDevice(String applicationName, String clientVersion, String platform, String udid, String tag) throws KalturaApiException {
        return this.serveByDevice(applicationName, clientVersion, platform, udid, tag, 0);
    }

	/**  Return a device configuration applicable for a specific device (UDID), app name,
	  software version, platform and optionally a configuration group’s tag  */
    public String serveByDevice(String applicationName, String clientVersion, String platform, String udid, String tag, int partnerId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("applicationName", applicationName);
        kparams.add("clientVersion", clientVersion);
        kparams.add("platform", platform);
        kparams.add("udid", udid);
        kparams.add("tag", tag);
        kparams.add("partnerId", partnerId);
        this.kalturaClient.queueServiceCall("configurations", "serveByDevice", kparams);
        return this.kalturaClient.serve();
    }

	/**  Update device configuration  */
    public KalturaConfigurations update(String id, KalturaConfigurations configurations) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("configurations", configurations);
        this.kalturaClient.queueServiceCall("configurations", "update", kparams, KalturaConfigurations.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurations.class, resultXmlElement);
    }
}
