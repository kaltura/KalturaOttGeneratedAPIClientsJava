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
import com.kaltura.client.enums.KalturaMessageTemplateType;
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
public class KalturaMessageTemplate extends KalturaObjectBase {
	/**  The message template with placeholders  */
    public String message;
	/**  Default date format for the date &amp;amp; time entries used in the template  */
    public String dateFormat;
	/**  Template type. Possible values: Series, Reminder,Churn, SeriesReminder  */
    public KalturaMessageTemplateType messageType;
	/**  Sound file name to play upon message arrival to the device (if supported by
	  target device)  */
    public String sound;
	/**  an optional action  */
    public String action;
	/**  URL template for deep linking. Example - /app/location/{mediaId}  */
    public String url;

    public KalturaMessageTemplate() {
    }

    public KalturaMessageTemplate(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("message")) {
                this.message = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("dateFormat")) {
                this.dateFormat = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("messageType")) {
                this.messageType = KalturaMessageTemplateType.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("sound")) {
                this.sound = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("action")) {
                this.action = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("url")) {
                this.url = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaMessageTemplate");
        kparams.add("message", this.message);
        kparams.add("dateFormat", this.dateFormat);
        kparams.add("messageType", this.messageType);
        kparams.add("sound", this.sound);
        kparams.add("action", this.action);
        kparams.add("url", this.url);
        return kparams;
    }

}

