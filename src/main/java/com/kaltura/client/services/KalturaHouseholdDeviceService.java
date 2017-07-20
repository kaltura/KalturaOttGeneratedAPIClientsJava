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
import com.kaltura.client.enums.*;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaHouseholdDeviceService extends KalturaServiceBase {
    public KalturaHouseholdDeviceService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Add device to household  */
    public KalturaHouseholdDevice add(KalturaHouseholdDevice device) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("device", device);
        this.kalturaClient.queueServiceCall("householddevice", "add", kparams, KalturaHouseholdDevice.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdDevice.class, resultXmlElement);
    }

	/**  Registers a device to a household using pin code  */
    public KalturaHouseholdDevice addByPin(String deviceName, String pin) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("deviceName", deviceName);
        kparams.add("pin", pin);
        this.kalturaClient.queueServiceCall("householddevice", "addByPin", kparams, KalturaHouseholdDevice.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdDevice.class, resultXmlElement);
    }

	/**  Removes a device from household  */
    public boolean delete(String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("householddevice", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Generates device pin to use when adding a device to household by pin  */
    public KalturaDevicePin generatePin(String udid, int brandId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("udid", udid);
        kparams.add("brandId", brandId);
        this.kalturaClient.queueServiceCall("householddevice", "generatePin", kparams, KalturaDevicePin.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaDevicePin.class, resultXmlElement);
    }

	/**  Returns device registration status to the supplied household  */
    public KalturaHouseholdDevice get() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("householddevice", "get", kparams, KalturaHouseholdDevice.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdDevice.class, resultXmlElement);
    }

    public KalturaHouseholdDeviceListResponse list() throws KalturaApiException {
        return this.list(null);
    }

	/**  Returns the devices within the household  */
    public KalturaHouseholdDeviceListResponse list(KalturaHouseholdDeviceFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("householddevice", "list", kparams, KalturaHouseholdDeviceListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdDeviceListResponse.class, resultXmlElement);
    }

    public KalturaLoginResponse loginWithPin(int partnerId, String pin) throws KalturaApiException {
        return this.loginWithPin(partnerId, pin, null);
    }

	/**  User sign-in via a time-expired sign-in PIN.  */
    public KalturaLoginResponse loginWithPin(int partnerId, String pin, String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("pin", pin);
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("householddevice", "loginWithPin", kparams, KalturaLoginResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLoginResponse.class, resultXmlElement);
    }

	/**  Update the name of the device by UDID  */
    public KalturaHouseholdDevice update(String udid, KalturaHouseholdDevice device) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("udid", udid);
        kparams.add("device", device);
        this.kalturaClient.queueServiceCall("householddevice", "update", kparams, KalturaHouseholdDevice.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdDevice.class, resultXmlElement);
    }

	/**  Update the name of the device by UDID  */
    public boolean updateStatus(String udid, KalturaDeviceStatus status) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("udid", udid);
        kparams.add("status", status);
        this.kalturaClient.queueServiceCall("householddevice", "updateStatus", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
