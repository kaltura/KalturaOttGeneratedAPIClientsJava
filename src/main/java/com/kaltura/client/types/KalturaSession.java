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
import com.kaltura.client.enums.KalturaSessionType;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Kaltura Session  */
@SuppressWarnings("serial")
public class KalturaSession extends KalturaObjectBase {
	/**  KS  */
    public String ks;
	/**  Session type  */
    public KalturaSessionType sessionType;
	/**  Partner identifier  */
    public int partnerId = Integer.MIN_VALUE;
	/**  User identifier  */
    public String userId;
	/**  Expiry  */
    public int expiry = Integer.MIN_VALUE;
	/**  Privileges  */
    public String privileges;
	/**  UDID  */
    public String udid;
	/**  Create date  */
    public int createDate = Integer.MIN_VALUE;

    public KalturaSession() {
    }

    public KalturaSession(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("ks")) {
                this.ks = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("sessionType")) {
                this.sessionType = KalturaSessionType.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("partnerId")) {
                this.partnerId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("userId")) {
                this.userId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("expiry")) {
                this.expiry = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("privileges")) {
                this.privileges = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("udid")) {
                this.udid = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("createDate")) {
                this.createDate = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaSession");
        kparams.add("ks", this.ks);
        kparams.add("sessionType", this.sessionType);
        kparams.add("partnerId", this.partnerId);
        kparams.add("userId", this.userId);
        kparams.add("expiry", this.expiry);
        kparams.add("privileges", this.privileges);
        kparams.add("udid", this.udid);
        kparams.add("createDate", this.createDate);
        return kparams;
    }

}

