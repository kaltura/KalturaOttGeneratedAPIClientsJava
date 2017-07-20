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
import com.kaltura.client.enums.KalturaWatchStatus;
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
public class KalturaAssetHistoryFilter extends KalturaFilter {
	/**  Comma separated list of asset types to search within.              Possible
	  values: 0 – EPG linear programs entries, any media type ID (according to media
	  type IDs defined dynamically in the system).              If omitted – all
	  types should be included.  */
    public String typeIn;
	/**  Comma separated list of asset identifiers.  */
    public String assetIdIn;
	/**  Which type of recently watched media to include in the result – those that
	  finished watching, those that are in progress or both.              If omitted
	  or specified filter = all – return all types.              Allowed values:
	  progress – return medias that are in-progress, done – return medias that
	  finished watching.  */
    public KalturaWatchStatus statusEqual;
	/**  How many days back to return the watched media. If omitted, default to 7 days  */
    public int daysLessThanOrEqual = Integer.MIN_VALUE;

    public KalturaAssetHistoryFilter() {
    }

    public KalturaAssetHistoryFilter(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("typeIn")) {
                this.typeIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("assetIdIn")) {
                this.assetIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("statusEqual")) {
                this.statusEqual = KalturaWatchStatus.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("daysLessThanOrEqual")) {
                this.daysLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaAssetHistoryFilter");
        kparams.add("typeIn", this.typeIn);
        kparams.add("assetIdIn", this.assetIdIn);
        kparams.add("statusEqual", this.statusEqual);
        kparams.add("daysLessThanOrEqual", this.daysLessThanOrEqual);
        return kparams;
    }

}

