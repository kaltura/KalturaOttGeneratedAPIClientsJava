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
import com.kaltura.client.enums.*;
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
public class KalturaNotificationService extends KalturaServiceBase {
    public KalturaNotificationService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  TBD  */
    public KalturaRegistryResponse register(String identifier, KalturaNotificationType type) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("identifier", identifier);
        kparams.add("type", type);
        this.kalturaClient.queueServiceCall("notification", "register", kparams, KalturaRegistryResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRegistryResponse.class, resultXmlElement);
    }

	/**  Sends push notification to user devices  */
    public boolean sendPush(int userId, KalturaPushMessage pushMessage) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("userId", userId);
        kparams.add("pushMessage", pushMessage);
        this.kalturaClient.queueServiceCall("notification", "sendPush", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Registers the device push token to the push service  */
    public boolean setDevicePushToken(String pushToken) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("pushToken", pushToken);
        this.kalturaClient.queueServiceCall("notification", "setDevicePushToken", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
