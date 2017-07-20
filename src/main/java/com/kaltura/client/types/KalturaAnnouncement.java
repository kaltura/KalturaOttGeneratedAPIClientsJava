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
package com.kaltura.client.types;

import org.w3c.dom.Element;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.enums.KalturaAnnouncementStatus;
import com.kaltura.client.enums.KalturaAnnouncementRecipientsType;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaAnnouncement extends KalturaObjectBase {
	/**  Announcement name  */
    public String name;
	/**  Announcement message  */
    public String message;
	/**  Announcement enabled  */
    public boolean enabled;
	/**  Announcement start time  */
    public long startTime = Long.MIN_VALUE;
	/**  Announcement time zone  */
    public String timezone;
	/**  Announcement status: NotSent=0/Sending=1/Sent=2/Aborted=3  */
    public KalturaAnnouncementStatus status;
	/**  Announcement recipients: All=0/LoggedIn=1/Guests=2/Other=3  */
    public KalturaAnnouncementRecipientsType recipients;
	/**  Announcement id  */
    public int id = Integer.MIN_VALUE;

    public KalturaAnnouncement() {
    }

    public KalturaAnnouncement(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("message")) {
                this.message = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("enabled")) {
                this.enabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("startTime")) {
                this.startTime = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("timezone")) {
                this.timezone = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("status")) {
                this.status = KalturaAnnouncementStatus.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("recipients")) {
                this.recipients = KalturaAnnouncementRecipientsType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaAnnouncement");
        kparams.add("name", this.name);
        kparams.add("message", this.message);
        kparams.add("enabled", this.enabled);
        kparams.add("startTime", this.startTime);
        kparams.add("timezone", this.timezone);
        kparams.add("recipients", this.recipients);
        return kparams;
    }

}

