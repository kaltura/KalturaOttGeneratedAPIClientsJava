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
public class KalturaRecordingService extends KalturaServiceBase {
    public KalturaRecordingService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Issue a record request for a program  */
    public KalturaRecording add(KalturaRecording recording) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("recording", recording);
        this.kalturaClient.queueServiceCall("recording", "add", kparams, KalturaRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecording.class, resultXmlElement);
    }

	/**  Cancel a previously requested recording. Cancel recording can be called for
	  recording in status Scheduled or Recording Only  */
    public KalturaRecording cancel(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("recording", "cancel", kparams, KalturaRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecording.class, resultXmlElement);
    }

	/**  Delete one or more user recording(s). Delete recording can be called only for
	  recordings in status Recorded  */
    public KalturaRecording delete(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("recording", "delete", kparams, KalturaRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecording.class, resultXmlElement);
    }

	/**  Returns recording object by internal identifier  */
    public KalturaRecording get(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("recording", "get", kparams, KalturaRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecording.class, resultXmlElement);
    }

    public KalturaRecordingListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaRecordingListResponse list(KalturaRecordingFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  Return a list of recordings for the household with optional filter by status and
	  KSQL.  */
    public KalturaRecordingListResponse list(KalturaRecordingFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("recording", "list", kparams, KalturaRecordingListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecordingListResponse.class, resultXmlElement);
    }

	/**  Protects an existing recording from the cleanup process for the defined
	  protection period  */
    public KalturaRecording protect(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("recording", "protect", kparams, KalturaRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaRecording.class, resultXmlElement);
    }
}
