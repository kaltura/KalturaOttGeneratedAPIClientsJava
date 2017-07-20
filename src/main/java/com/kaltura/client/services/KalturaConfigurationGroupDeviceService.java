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
public class KalturaConfigurationGroupDeviceService extends KalturaServiceBase {
    public KalturaConfigurationGroupDeviceService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Associate a collection of devices to a configuration group. If a device is
	  already associated to another group – old association is replaced  */
    public boolean add(KalturaConfigurationGroupDevice configurationGroupDevice) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("configurationGroupDevice", configurationGroupDevice);
        this.kalturaClient.queueServiceCall("configurationgroupdevice", "add", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Remove a device association  */
    public boolean delete(String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("configurationgroupdevice", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Return the configuration group to which a specific device is associated to  */
    public KalturaConfigurationGroupDevice get(String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("configurationgroupdevice", "get", kparams, KalturaConfigurationGroupDevice.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroupDevice.class, resultXmlElement);
    }

    public KalturaConfigurationGroupDeviceListResponse list(KalturaConfigurationGroupDeviceFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  Return the list of associated devices for a given configuration group  */
    public KalturaConfigurationGroupDeviceListResponse list(KalturaConfigurationGroupDeviceFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("configurationgroupdevice", "list", kparams, KalturaConfigurationGroupDeviceListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroupDeviceListResponse.class, resultXmlElement);
    }
}
