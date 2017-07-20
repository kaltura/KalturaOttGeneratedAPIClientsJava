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

/**  Media file details  */
@SuppressWarnings("serial")
public class KalturaMediaFile extends KalturaObjectBase {
	/**  Unique identifier for the asset  */
    public int assetId = Integer.MIN_VALUE;
	/**  File unique identifier  */
    public int id = Integer.MIN_VALUE;
	/**  Device types as defined in the system  */
    public String type;
	/**  URL of the media file to be played  */
    public String url;
	/**  Duration of the media file  */
    public long duration = Long.MIN_VALUE;
	/**  External identifier for the media file  */
    public String externalId;
	/**  Billing type  */
    public String billingType;
	/**  Quality  */
    public String quality;
	/**  Handling type  */
    public String handlingType;
	/**  CDN name  */
    public String cdnName;
	/**  CDN code  */
    public String cdnCode;
	/**  Alt CDN code  */
    public String altCdnCode;
	/**  PPV Module  */
    public KalturaStringValueArray ppvModules;
	/**  Product code  */
    public String productCode;

    public KalturaMediaFile() {
    }

    public KalturaMediaFile(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("assetId")) {
                this.assetId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("type")) {
                this.type = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("url")) {
                this.url = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("duration")) {
                this.duration = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("externalId")) {
                this.externalId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("billingType")) {
                this.billingType = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("quality")) {
                this.quality = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("handlingType")) {
                this.handlingType = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("cdnName")) {
                this.cdnName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("cdnCode")) {
                this.cdnCode = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("altCdnCode")) {
                this.altCdnCode = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("ppvModules")) {
                this.ppvModules = ParseUtils.parseObject(KalturaStringValueArray.class, aNode);
                continue;
            } else if (nodeName.equals("productCode")) {
                this.productCode = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaMediaFile");
        kparams.add("assetId", this.assetId);
        kparams.add("type", this.type);
        kparams.add("url", this.url);
        kparams.add("duration", this.duration);
        kparams.add("externalId", this.externalId);
        kparams.add("billingType", this.billingType);
        kparams.add("quality", this.quality);
        kparams.add("handlingType", this.handlingType);
        kparams.add("cdnName", this.cdnName);
        kparams.add("cdnCode", this.cdnCode);
        kparams.add("altCdnCode", this.altCdnCode);
        kparams.add("ppvModules", this.ppvModules);
        kparams.add("productCode", this.productCode);
        return kparams;
    }

}

