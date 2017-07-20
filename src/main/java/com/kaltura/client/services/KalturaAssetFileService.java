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
public class KalturaAssetFileService extends KalturaServiceBase {
    public KalturaAssetFileService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  get KalturaAssetFileContext  */
    public KalturaAssetFileContext getContext(String id, KalturaContextType contextType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("contextType", contextType);
        this.kalturaClient.queueServiceCall("assetfile", "getContext", kparams, KalturaAssetFileContext.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAssetFileContext.class, resultXmlElement);
    }

    public void playManifest(int partnerId, String assetId, KalturaAssetType assetType, long assetFileId, KalturaPlaybackContextType contextType) throws KalturaApiException {
        this.playManifest(partnerId, assetId, assetType, assetFileId, contextType, null);
    }

	/**  Redirects to play manifest  */
    public void playManifest(int partnerId, String assetId, KalturaAssetType assetType, long assetFileId, KalturaPlaybackContextType contextType, String ks) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("assetId", assetId);
        kparams.add("assetType", assetType);
        kparams.add("assetFileId", assetFileId);
        kparams.add("contextType", contextType);
        kparams.add("ks", ks);
        this.kalturaClient.queueServiceCall("assetfile", "playManifest", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }
}
