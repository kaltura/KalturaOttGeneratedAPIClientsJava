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
public class KalturaAnnouncementService extends KalturaServiceBase {
    public KalturaAnnouncementService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Add a new future scheduled system announcement push notification  */
    public KalturaAnnouncement add(KalturaAnnouncement announcement) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("announcement", announcement);
        this.kalturaClient.queueServiceCall("announcement", "add", kparams, KalturaAnnouncement.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAnnouncement.class, resultXmlElement);
    }

	/**  Delete an existing announcing. Announcement cannot be delete while being sent.  */
    public boolean delete(long id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("announcement", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Enable system announcements  */
    public boolean enableSystemAnnouncements() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("announcement", "enableSystemAnnouncements", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public KalturaAnnouncementListResponse list(KalturaAnnouncementFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  Lists all announcements in the system.  */
    public KalturaAnnouncementListResponse list(KalturaAnnouncementFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("announcement", "list", kparams, KalturaAnnouncementListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAnnouncementListResponse.class, resultXmlElement);
    }

	/**  Update an existing future system announcement push notification. Announcement
	  can only be updated only before sending  */
    public KalturaAnnouncement update(int announcementId, KalturaAnnouncement announcement) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("announcementId", announcementId);
        kparams.add("announcement", announcement);
        this.kalturaClient.queueServiceCall("announcement", "update", kparams, KalturaAnnouncement.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaAnnouncement.class, resultXmlElement);
    }

	/**  Update a system announcement status  */
    public boolean updateStatus(long id, boolean status) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("status", status);
        this.kalturaClient.queueServiceCall("announcement", "updateStatus", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
