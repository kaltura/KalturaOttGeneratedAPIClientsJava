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
import com.kaltura.client.enums.KalturaPlatform;
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
public class KalturaDeviceReport extends KalturaReport {
	/**  Partner id  */
    public int partnerId = Integer.MIN_VALUE;
	/**  Configuration group id  */
    public String configurationGroupId;
	/**  Device UDID  */
    public String udid;
	/**  Push parameters  */
    public KalturaPushParams pushParameters;
	/**  Version number  */
    public String versionNumber;
	/**  Version platform  */
    public KalturaPlatform versionPlatform;
	/**  Version application name  */
    public String versionAppName;
	/**  Last access IP  */
    public String lastAccessIP;
	/**  Last access date  */
    public long lastAccessDate = Long.MIN_VALUE;
	/**  User agent  */
    public String userAgent;
	/**  Operation system  */
    public String operationSystem;

    public KalturaDeviceReport() {
    }

    public KalturaDeviceReport(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("partnerId")) {
                this.partnerId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("configurationGroupId")) {
                this.configurationGroupId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("udid")) {
                this.udid = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("pushParameters")) {
                this.pushParameters = ParseUtils.parseObject(KalturaPushParams.class, aNode);
                continue;
            } else if (nodeName.equals("versionNumber")) {
                this.versionNumber = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("versionPlatform")) {
                this.versionPlatform = KalturaPlatform.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("versionAppName")) {
                this.versionAppName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastAccessIP")) {
                this.lastAccessIP = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastAccessDate")) {
                this.lastAccessDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("userAgent")) {
                this.userAgent = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("operationSystem")) {
                this.operationSystem = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaDeviceReport");
        kparams.add("partnerId", this.partnerId);
        kparams.add("configurationGroupId", this.configurationGroupId);
        kparams.add("udid", this.udid);
        kparams.add("pushParameters", this.pushParameters);
        kparams.add("versionNumber", this.versionNumber);
        kparams.add("versionPlatform", this.versionPlatform);
        kparams.add("versionAppName", this.versionAppName);
        kparams.add("lastAccessIP", this.lastAccessIP);
        kparams.add("lastAccessDate", this.lastAccessDate);
        kparams.add("userAgent", this.userAgent);
        kparams.add("operationSystem", this.operationSystem);
        return kparams;
    }

}

