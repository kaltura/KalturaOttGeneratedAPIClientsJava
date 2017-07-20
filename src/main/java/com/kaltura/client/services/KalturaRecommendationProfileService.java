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
public class KalturaRecommendationProfileService extends KalturaServiceBase {
    public KalturaRecommendationProfileService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Insert new recommendation engine for partner  */
    public KalturaRecommendationProfile add(KalturaRecommendationProfile recommendationEngine) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("recommendationEngine", recommendationEngine);
        this.kalturaClient.queueServiceCall("recommendationprofile", "add", kparams, KalturaRecommendationProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecommendationProfile.class, resultXmlElement);
    }

	/**  Delete recommendation engine by recommendation engine id  */
    public boolean delete(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("recommendationprofile", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Generate recommendation engine  shared secret  */
    public KalturaRecommendationProfile generateSharedSecret(int recommendationEngineId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("recommendationEngineId", recommendationEngineId);
        this.kalturaClient.queueServiceCall("recommendationprofile", "generateSharedSecret", kparams, KalturaRecommendationProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecommendationProfile.class, resultXmlElement);
    }

	/**  Returns all recommendation engines for partner  */
    public KalturaRecommendationProfileListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("recommendationprofile", "list", kparams, KalturaRecommendationProfileListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecommendationProfileListResponse.class, resultXmlElement);
    }

	/**  Update recommendation engine details  */
    public KalturaRecommendationProfile update(int recommendationEngineId, KalturaRecommendationProfile recommendationEngine) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("recommendationEngineId", recommendationEngineId);
        kparams.add("recommendationEngine", recommendationEngine);
        this.kalturaClient.queueServiceCall("recommendationprofile", "update", kparams, KalturaRecommendationProfile.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecommendationProfile.class, resultXmlElement);
    }
}
