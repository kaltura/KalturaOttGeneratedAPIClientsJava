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

/**  PPV price details  */
@SuppressWarnings("serial")
public class KalturaPpvPrice extends KalturaProductPrice {
	/**  Media file identifier  */
    public int fileId = Integer.MIN_VALUE;
	/**  The associated PPV module identifier  */
    public String ppvModuleId;
	/**  Denotes whether this object is available only as part of a subscription or can
	  be sold separately  */
    public boolean isSubscriptionOnly;
	/**  The full price of the item (with no discounts)  */
    public KalturaPrice fullPrice;
	/**  The identifier of the relevant subscription  */
    public String subscriptionId;
	/**  The identifier of the relevant collection  */
    public String collectionId;
	/**  The identifier of the relevant pre paid  */
    public String prePaidId;
	/**  A list of the descriptions of the PPV module on different languages (language
	  code and translation)  */
    public ArrayList<KalturaTranslationToken> ppvDescriptions;
	/**  If the item already purchased - the identifier of the user (in the household)
	  who purchased this item  */
    public String purchaseUserId;
	/**  If the item already purchased - the identifier of the purchased file  */
    public int purchasedMediaFileId = Integer.MIN_VALUE;
	/**  Related media files identifiers (different types)  */
    public ArrayList<KalturaIntegerValue> relatedMediaFileIds;
	/**  If the item already purchased - since when the user can start watching the item  */
    public long startDate = Long.MIN_VALUE;
	/**  If the item already purchased - until when the user can watch the item  */
    public long endDate = Long.MIN_VALUE;
	/**  Discount end date  */
    public long discountEndDate = Long.MIN_VALUE;
	/**  If the item already purchased and played - the name of the device on which it
	  was first played  */
    public String firstDeviceName;
	/**  If waiver period is enabled - donates whether the user is still in the
	  cancelation window  */
    public boolean isInCancelationPeriod;
	/**  The PPV product code  */
    public String ppvProductCode;

    public KalturaPpvPrice() {
    }

    public KalturaPpvPrice(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("fileId")) {
                this.fileId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("ppvModuleId")) {
                this.ppvModuleId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isSubscriptionOnly")) {
                this.isSubscriptionOnly = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("fullPrice")) {
                this.fullPrice = ParseUtils.parseObject(KalturaPrice.class, aNode);
                continue;
            } else if (nodeName.equals("subscriptionId")) {
                this.subscriptionId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("collectionId")) {
                this.collectionId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("prePaidId")) {
                this.prePaidId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("ppvDescriptions")) {
                this.ppvDescriptions = ParseUtils.parseArray(KalturaTranslationToken.class, aNode);
                continue;
            } else if (nodeName.equals("purchaseUserId")) {
                this.purchaseUserId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("purchasedMediaFileId")) {
                this.purchasedMediaFileId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("relatedMediaFileIds")) {
                this.relatedMediaFileIds = ParseUtils.parseArray(KalturaIntegerValue.class, aNode);
                continue;
            } else if (nodeName.equals("startDate")) {
                this.startDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("endDate")) {
                this.endDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("discountEndDate")) {
                this.discountEndDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("firstDeviceName")) {
                this.firstDeviceName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isInCancelationPeriod")) {
                this.isInCancelationPeriod = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("ppvProductCode")) {
                this.ppvProductCode = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaPpvPrice");
        kparams.add("fileId", this.fileId);
        kparams.add("ppvModuleId", this.ppvModuleId);
        kparams.add("isSubscriptionOnly", this.isSubscriptionOnly);
        kparams.add("fullPrice", this.fullPrice);
        kparams.add("subscriptionId", this.subscriptionId);
        kparams.add("collectionId", this.collectionId);
        kparams.add("prePaidId", this.prePaidId);
        kparams.add("ppvDescriptions", this.ppvDescriptions);
        kparams.add("purchaseUserId", this.purchaseUserId);
        kparams.add("purchasedMediaFileId", this.purchasedMediaFileId);
        kparams.add("relatedMediaFileIds", this.relatedMediaFileIds);
        kparams.add("startDate", this.startDate);
        kparams.add("endDate", this.endDate);
        kparams.add("discountEndDate", this.discountEndDate);
        kparams.add("firstDeviceName", this.firstDeviceName);
        kparams.add("isInCancelationPeriod", this.isInCancelationPeriod);
        kparams.add("ppvProductCode", this.ppvProductCode);
        return kparams;
    }

}

