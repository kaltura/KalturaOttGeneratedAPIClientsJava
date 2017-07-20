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
public class KalturaPurchaseSettingsService extends KalturaServiceBase {
    public KalturaPurchaseSettingsService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Retrieve the purchase settings.              Includes specification of where
	  these settings were defined – account, household or user  */
    public KalturaPurchaseSettings get(KalturaEntityReferenceBy by) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("by", by);
        this.kalturaClient.queueServiceCall("purchasesettings", "get", kparams, KalturaPurchaseSettings.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaPurchaseSettings.class, resultXmlElement);
    }

	/**  Set a purchase PIN for the household or user  */
    public KalturaPurchaseSettings update(KalturaEntityReferenceBy entityReference, KalturaPurchaseSettings settings) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entityReference", entityReference);
        kparams.add("settings", settings);
        this.kalturaClient.queueServiceCall("purchasesettings", "update", kparams, KalturaPurchaseSettings.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaPurchaseSettings.class, resultXmlElement);
    }
}
