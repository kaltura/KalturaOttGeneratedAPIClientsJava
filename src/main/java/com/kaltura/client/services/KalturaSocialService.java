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
public class KalturaSocialService extends KalturaServiceBase {
    public KalturaSocialService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  List social accounts  */
    public KalturaSocial get(KalturaSocialNetwork type) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("type", type);
        this.kalturaClient.queueServiceCall("social", "get", kparams, KalturaSocial.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocial.class, resultXmlElement);
    }

	/**  Return the user object with social information according to a provided external
	  social token  */
    public KalturaSocial getByToken(int partnerId, String token, KalturaSocialNetwork type) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("token", token);
        kparams.add("type", type);
        this.kalturaClient.queueServiceCall("social", "getByToken", kparams, KalturaSocial.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocial.class, resultXmlElement);
    }

    public KalturaSocialConfig getConfiguration(KalturaSocialNetwork type) throws KalturaApiException {
        return this.getConfiguration(type, Integer.MIN_VALUE);
    }

	/**  Retrieve the social network’s configuration information  */
    public KalturaSocialConfig getConfiguration(KalturaSocialNetwork type, int partnerId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("type", type);
        kparams.add("partnerId", partnerId);
        this.kalturaClient.queueServiceCall("social", "getConfiguration", kparams, KalturaSocialConfig.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocialConfig.class, resultXmlElement);
    }

    public KalturaLoginResponse login(int partnerId, String token, KalturaSocialNetwork type) throws KalturaApiException {
        return this.login(partnerId, token, type, null);
    }

	/**  Login using social token  */
    public KalturaLoginResponse login(int partnerId, String token, KalturaSocialNetwork type, String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("token", token);
        kparams.add("type", type);
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("social", "login", kparams, KalturaLoginResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLoginResponse.class, resultXmlElement);
    }

	/**  Connect an existing user in the system to an external social network user  */
    public KalturaSocial merge(String token, KalturaSocialNetwork type) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("token", token);
        kparams.add("type", type);
        this.kalturaClient.queueServiceCall("social", "merge", kparams, KalturaSocial.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocial.class, resultXmlElement);
    }

    public KalturaSocial register(int partnerId, String token, KalturaSocialNetwork type) throws KalturaApiException {
        return this.register(partnerId, token, type, null);
    }

	/**  Create a new user in the system using a provided external social token  */
    public KalturaSocial register(int partnerId, String token, KalturaSocialNetwork type, String email) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("token", token);
        kparams.add("type", type);
        kparams.add("email", email);
        this.kalturaClient.queueServiceCall("social", "register", kparams, KalturaSocial.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocial.class, resultXmlElement);
    }

	/**  Disconnect an existing user in the system from its external social network user  */
    public KalturaSocial unmerge(KalturaSocialNetwork type) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("type", type);
        this.kalturaClient.queueServiceCall("social", "unmerge", kparams, KalturaSocial.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocial.class, resultXmlElement);
    }

	/**  Set the user social network’s configuration information  */
    public KalturaSocialConfig UpdateConfiguration(KalturaSocialConfig configuration) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("configuration", configuration);
        this.kalturaClient.queueServiceCall("social", "UpdateConfiguration", kparams, KalturaSocialConfig.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSocialConfig.class, resultXmlElement);
    }
}
