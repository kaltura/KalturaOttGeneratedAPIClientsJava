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
public class KalturaSeriesRecordingService extends KalturaServiceBase {
    public KalturaSeriesRecordingService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Issue a record request for a complete season or series  */
    public KalturaSeriesRecording add(KalturaSeriesRecording recording) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("recording", recording);
        this.kalturaClient.queueServiceCall("seriesrecording", "add", kparams, KalturaSeriesRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecording.class, resultXmlElement);
    }

	/**  Cancel a previously requested series recording. Cancel series recording can be
	  called for recording in status Scheduled or Recording Only  */
    public KalturaSeriesRecording cancel(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("seriesrecording", "cancel", kparams, KalturaSeriesRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecording.class, resultXmlElement);
    }

	/**  Cancel EPG recording that was recorded as part of series  */
    public KalturaSeriesRecording cancelByEpgId(long id, long epgId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("epgId", epgId);
        this.kalturaClient.queueServiceCall("seriesrecording", "cancelByEpgId", kparams, KalturaSeriesRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecording.class, resultXmlElement);
    }

	/**  Cancel Season recording epgs that was recorded as part of series  */
    public KalturaSeriesRecording cancelBySeasonNumber(long id, long seasonNumber) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("seasonNumber", seasonNumber);
        this.kalturaClient.queueServiceCall("seriesrecording", "cancelBySeasonNumber", kparams, KalturaSeriesRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecording.class, resultXmlElement);
    }

	/**  Delete series recording(s). Delete series recording can be called recordings in
	  any status  */
    public KalturaSeriesRecording delete(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("seriesrecording", "delete", kparams, KalturaSeriesRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecording.class, resultXmlElement);
    }

	/**  Delete Season recording epgs that was recorded as part of series  */
    public KalturaSeriesRecording deleteBySeasonNumber(long id, int seasonNumber) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("seasonNumber", seasonNumber);
        this.kalturaClient.queueServiceCall("seriesrecording", "deleteBySeasonNumber", kparams, KalturaSeriesRecording.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecording.class, resultXmlElement);
    }

    public KalturaSeriesRecordingListResponse list() throws KalturaApiException {
        return this.list(null);
    }

	/**  Return a list of series recordings for the household with optional filter by
	  status and KSQL.  */
    public KalturaSeriesRecordingListResponse list(KalturaSeriesRecordingFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("seriesrecording", "list", kparams, KalturaSeriesRecordingListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaSeriesRecordingListResponse.class, resultXmlElement);
    }
}
