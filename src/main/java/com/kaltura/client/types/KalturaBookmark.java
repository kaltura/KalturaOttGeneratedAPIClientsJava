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
import com.kaltura.client.enums.KalturaPositionOwner;
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
public class KalturaBookmark extends KalturaSlimAsset {
	/**  User identifier  */
    public String userId;
	/**  The position of the user in the specific asset (in seconds)  */
    public int position = Integer.MIN_VALUE;
	/**  Indicates who is the owner of this position  */
    public KalturaPositionOwner positionOwner;
	/**  Specifies whether the user&amp;#39;s current position exceeded 95% of the
	  duration  */
    public boolean finishedWatching;
	/**  Insert only player data  */
    public KalturaBookmarkPlayerData playerData;

    public KalturaBookmark() {
    }

    public KalturaBookmark(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("userId")) {
                this.userId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("position")) {
                this.position = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("positionOwner")) {
                this.positionOwner = KalturaPositionOwner.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("finishedWatching")) {
                this.finishedWatching = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("playerData")) {
                this.playerData = ParseUtils.parseObject(KalturaBookmarkPlayerData.class, aNode);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaBookmark");
        kparams.add("position", this.position);
        kparams.add("playerData", this.playerData);
        return kparams;
    }

}

