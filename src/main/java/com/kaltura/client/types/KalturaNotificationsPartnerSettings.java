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
public class KalturaNotificationsPartnerSettings extends KalturaObjectBase {
	/**  Push notification capability is enabled for the account  */
    public boolean pushNotificationEnabled;
	/**  System announcement capability is enabled for the account  */
    public boolean pushSystemAnnouncementsEnabled;
	/**  Window start time (UTC) for send automated push messages  */
    public int pushStartHour = Integer.MIN_VALUE;
	/**  Window end time (UTC) for send automated push messages  */
    public int pushEndHour = Integer.MIN_VALUE;
	/**  Inbox enabled  */
    public boolean inboxEnabled;
	/**  Message TTL in days  */
    public int messageTTLDays = Integer.MIN_VALUE;
	/**  Automatic issue follow notification  */
    public boolean automaticIssueFollowNotification;
	/**  Topic expiration duration in days  */
    public int topicExpirationDurationDays = Integer.MIN_VALUE;
	/**  Reminder enabled  */
    public boolean reminderEnabled;
	/**  Offset time (UTC) in seconds for send reminder  */
    public int reminderOffsetSec = Integer.MIN_VALUE;
	/**  Push adapter URL  */
    public String pushAdapterUrl;
	/**  Churn mail template name  */
    public String churnMailTemplateName;
	/**  Churn mail subject  */
    public String churnMailSubject;
	/**  Sender email  */
    public String senderEmail;
	/**  Mail sender name  */
    public String mailSenderName;

    public KalturaNotificationsPartnerSettings() {
    }

    public KalturaNotificationsPartnerSettings(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("pushNotificationEnabled")) {
                this.pushNotificationEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("pushSystemAnnouncementsEnabled")) {
                this.pushSystemAnnouncementsEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("pushStartHour")) {
                this.pushStartHour = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("pushEndHour")) {
                this.pushEndHour = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("inboxEnabled")) {
                this.inboxEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("messageTTLDays")) {
                this.messageTTLDays = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("automaticIssueFollowNotification")) {
                this.automaticIssueFollowNotification = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("topicExpirationDurationDays")) {
                this.topicExpirationDurationDays = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("reminderEnabled")) {
                this.reminderEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("reminderOffsetSec")) {
                this.reminderOffsetSec = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("pushAdapterUrl")) {
                this.pushAdapterUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("churnMailTemplateName")) {
                this.churnMailTemplateName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("churnMailSubject")) {
                this.churnMailSubject = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("senderEmail")) {
                this.senderEmail = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("mailSenderName")) {
                this.mailSenderName = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaNotificationsPartnerSettings");
        kparams.add("pushNotificationEnabled", this.pushNotificationEnabled);
        kparams.add("pushSystemAnnouncementsEnabled", this.pushSystemAnnouncementsEnabled);
        kparams.add("pushStartHour", this.pushStartHour);
        kparams.add("pushEndHour", this.pushEndHour);
        kparams.add("inboxEnabled", this.inboxEnabled);
        kparams.add("messageTTLDays", this.messageTTLDays);
        kparams.add("automaticIssueFollowNotification", this.automaticIssueFollowNotification);
        kparams.add("topicExpirationDurationDays", this.topicExpirationDurationDays);
        kparams.add("reminderEnabled", this.reminderEnabled);
        kparams.add("reminderOffsetSec", this.reminderOffsetSec);
        kparams.add("pushAdapterUrl", this.pushAdapterUrl);
        kparams.add("churnMailTemplateName", this.churnMailTemplateName);
        kparams.add("churnMailSubject", this.churnMailSubject);
        kparams.add("senderEmail", this.senderEmail);
        kparams.add("mailSenderName", this.mailSenderName);
        return kparams;
    }

}

