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

/**  Asset statistics  */
@SuppressWarnings("serial")
public class KalturaAssetStatistics extends KalturaObjectBase {
	/**  Unique identifier for the asset  */
    public int assetId = Integer.MIN_VALUE;
	/**  Total number of likes for this asset  */
    public int likes = Integer.MIN_VALUE;
	/**  Total number of views for this asset  */
    public int views = Integer.MIN_VALUE;
	/**  Number of people that rated the asset  */
    public int ratingCount = Integer.MIN_VALUE;
	/**  Average rating for the asset  */
    public double rating = Double.MIN_VALUE;
	/**  Buzz score  */
    public KalturaBuzzScore buzzScore;

    public KalturaAssetStatistics() {
    }

    public KalturaAssetStatistics(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("assetId")) {
                this.assetId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("likes")) {
                this.likes = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("views")) {
                this.views = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("ratingCount")) {
                this.ratingCount = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("rating")) {
                this.rating = ParseUtils.parseDouble(txt);
                continue;
            } else if (nodeName.equals("buzzScore")) {
                this.buzzScore = ParseUtils.parseObject(KalturaBuzzScore.class, aNode);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaAssetStatistics");
        kparams.add("assetId", this.assetId);
        kparams.add("likes", this.likes);
        kparams.add("views", this.views);
        kparams.add("ratingCount", this.ratingCount);
        kparams.add("rating", this.rating);
        kparams.add("buzzScore", this.buzzScore);
        return kparams;
    }

}

