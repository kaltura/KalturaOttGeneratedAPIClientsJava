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
import java.util.HashMap;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Payment gateway profile  */
@SuppressWarnings("serial")
public class KalturaPaymentGatewayProfile extends KalturaPaymentGatewayBaseProfile {
	/**  Payment gateway is active status  */
    public int isActive = Integer.MIN_VALUE;
	/**  Payment gateway adapter URL  */
    public String adapterUrl;
	/**  Payment gateway transact URL  */
    public String transactUrl;
	/**  Payment gateway status URL  */
    public String statusUrl;
	/**  Payment gateway renew URL  */
    public String renewUrl;
	/**  Payment gateway extra parameters  */
    public HashMap<String, KalturaStringValue> paymentGatewaySettings;
	/**  Payment gateway external identifier  */
    public String externalIdentifier;
	/**  Pending Interval in minutes  */
    public int pendingInterval = Integer.MIN_VALUE;
	/**  Pending Retries  */
    public int pendingRetries = Integer.MIN_VALUE;
	/**  Shared Secret  */
    public String sharedSecret;
	/**  Renew Interval Minutes  */
    public int renewIntervalMinutes = Integer.MIN_VALUE;
	/**  Renew Start Minutes  */
    public int renewStartMinutes = Integer.MIN_VALUE;

    public KalturaPaymentGatewayProfile() {
    }

    public KalturaPaymentGatewayProfile(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("isActive")) {
                this.isActive = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("adapterUrl")) {
                this.adapterUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("transactUrl")) {
                this.transactUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("statusUrl")) {
                this.statusUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("renewUrl")) {
                this.renewUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("paymentGatewaySettings")) {
                this.paymentGatewaySettings = ParseUtils.parseMap(KalturaStringValue.class, aNode);
                continue;
            } else if (nodeName.equals("externalIdentifier")) {
                this.externalIdentifier = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("pendingInterval")) {
                this.pendingInterval = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("pendingRetries")) {
                this.pendingRetries = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("sharedSecret")) {
                this.sharedSecret = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("renewIntervalMinutes")) {
                this.renewIntervalMinutes = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("renewStartMinutes")) {
                this.renewStartMinutes = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaPaymentGatewayProfile");
        kparams.add("isActive", this.isActive);
        kparams.add("adapterUrl", this.adapterUrl);
        kparams.add("transactUrl", this.transactUrl);
        kparams.add("statusUrl", this.statusUrl);
        kparams.add("renewUrl", this.renewUrl);
        kparams.add("paymentGatewaySettings", this.paymentGatewaySettings);
        kparams.add("externalIdentifier", this.externalIdentifier);
        kparams.add("pendingInterval", this.pendingInterval);
        kparams.add("pendingRetries", this.pendingRetries);
        kparams.add("sharedSecret", this.sharedSecret);
        kparams.add("renewIntervalMinutes", this.renewIntervalMinutes);
        kparams.add("renewStartMinutes", this.renewStartMinutes);
        return kparams;
    }

}

