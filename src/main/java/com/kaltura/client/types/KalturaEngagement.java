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
import com.kaltura.client.enums.KalturaEngagementType;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Engagement  */
@SuppressWarnings("serial")
public class KalturaEngagement extends KalturaObjectBase {
	/**  Engagement id  */
    public int id = Integer.MIN_VALUE;
	/**  Total number of recipients  */
    public int totalNumberOfRecipients = Integer.MIN_VALUE;
	/**  Engagement type  */
    public KalturaEngagementType type;
	/**  Engagement adapter id  */
    public int adapterId = Integer.MIN_VALUE;
	/**  Engagement adapter dynamic data  */
    public String adapterDynamicData;
	/**  Interval (seconds)  */
    public int intervalSeconds = Integer.MIN_VALUE;
	/**  Manual User list  */
    public String userList;
	/**  Send time (seconds)  */
    public long sendTimeInSeconds = Long.MIN_VALUE;
	/**  Coupon GroupId  */
    public int couponGroupId = Integer.MIN_VALUE;

    public KalturaEngagement() {
    }

    public KalturaEngagement(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("totalNumberOfRecipients")) {
                this.totalNumberOfRecipients = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("type")) {
                this.type = KalturaEngagementType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("adapterId")) {
                this.adapterId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("adapterDynamicData")) {
                this.adapterDynamicData = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("intervalSeconds")) {
                this.intervalSeconds = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("userList")) {
                this.userList = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("sendTimeInSeconds")) {
                this.sendTimeInSeconds = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("couponGroupId")) {
                this.couponGroupId = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaEngagement");
        kparams.add("type", this.type);
        kparams.add("adapterId", this.adapterId);
        kparams.add("adapterDynamicData", this.adapterDynamicData);
        kparams.add("intervalSeconds", this.intervalSeconds);
        kparams.add("userList", this.userList);
        kparams.add("sendTimeInSeconds", this.sendTimeInSeconds);
        kparams.add("couponGroupId", this.couponGroupId);
        return kparams;
    }

}

