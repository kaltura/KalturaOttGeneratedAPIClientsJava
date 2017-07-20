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
import java.util.ArrayList;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  PPV details  */
@SuppressWarnings("serial")
public class KalturaPpv extends KalturaObjectBase {
	/**  PPV identifier  */
    public String id;
	/**  the name for the ppv  */
    public String name;
	/**  The price of the ppv  */
    public KalturaPriceDetails price;
	/**  A list of file types identifiers that are supported in this ppv  */
    public ArrayList<KalturaIntegerValue> fileTypes;
	/**  The internal discount module for the ppv  */
    public KalturaDiscountModule discountModule;
	/**  Coupons group for the ppv  */
    public KalturaCouponsGroup couponsGroup;
	/**  A list of the descriptions of the ppv on different languages (language code and
	  translation)  */
    public ArrayList<KalturaTranslationToken> descriptions;
	/**  Product code for the ppv  */
    public String productCode;
	/**  Indicates whether or not this ppv can be purchased standalone or only as part of
	  a subscription  */
    public boolean isSubscriptionOnly;
	/**  Indicates whether or not this ppv can be consumed only on the first device  */
    public boolean firstDeviceLimitation;
	/**  PPV usage module  */
    public KalturaUsageModule usageModule;

    public KalturaPpv() {
    }

    public KalturaPpv(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("price")) {
                this.price = ParseUtils.parseObject(KalturaPriceDetails.class, aNode);
                continue;
            } else if (nodeName.equals("fileTypes")) {
                this.fileTypes = ParseUtils.parseArray(KalturaIntegerValue.class, aNode);
                continue;
            } else if (nodeName.equals("discountModule")) {
                this.discountModule = ParseUtils.parseObject(KalturaDiscountModule.class, aNode);
                continue;
            } else if (nodeName.equals("couponsGroup")) {
                this.couponsGroup = ParseUtils.parseObject(KalturaCouponsGroup.class, aNode);
                continue;
            } else if (nodeName.equals("descriptions")) {
                this.descriptions = ParseUtils.parseArray(KalturaTranslationToken.class, aNode);
                continue;
            } else if (nodeName.equals("productCode")) {
                this.productCode = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isSubscriptionOnly")) {
                this.isSubscriptionOnly = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("firstDeviceLimitation")) {
                this.firstDeviceLimitation = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("usageModule")) {
                this.usageModule = ParseUtils.parseObject(KalturaUsageModule.class, aNode);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaPpv");
        kparams.add("id", this.id);
        kparams.add("name", this.name);
        kparams.add("price", this.price);
        kparams.add("fileTypes", this.fileTypes);
        kparams.add("discountModule", this.discountModule);
        kparams.add("couponsGroup", this.couponsGroup);
        kparams.add("descriptions", this.descriptions);
        kparams.add("productCode", this.productCode);
        kparams.add("isSubscriptionOnly", this.isSubscriptionOnly);
        kparams.add("firstDeviceLimitation", this.firstDeviceLimitation);
        kparams.add("usageModule", this.usageModule);
        return kparams;
    }

}

