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
import com.kaltura.client.enums.KalturaRecordingType;
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

@SuppressWarnings("serial")
public class KalturaSeriesRecording extends KalturaObjectBase {
	/**  Kaltura unique ID representing the series recording identifier  */
    public long id = Long.MIN_VALUE;
	/**  Kaltura EpgId  */
    public long epgId = Long.MIN_VALUE;
	/**  Kaltura ChannelId  */
    public long channelId = Long.MIN_VALUE;
	/**  Kaltura SeriesId  */
    public String seriesId;
	/**  Kaltura SeasonNumber  */
    public int seasonNumber = Integer.MIN_VALUE;
	/**  Recording Type: single/series/season  */
    public KalturaRecordingType type;
	/**  Specifies when was the series recording created. Date and time represented as
	  epoch.  */
    public long createDate = Long.MIN_VALUE;
	/**  Specifies when was the series recording last updated. Date and time represented
	  as epoch.  */
    public long updateDate = Long.MIN_VALUE;
	/**  List of the season numbers to exclude.  */
    public ArrayList<KalturaIntegerValue> excludedSeasons;

    public KalturaSeriesRecording() {
    }

    public KalturaSeriesRecording(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("epgId")) {
                this.epgId = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("channelId")) {
                this.channelId = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("seriesId")) {
                this.seriesId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("seasonNumber")) {
                this.seasonNumber = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("type")) {
                this.type = KalturaRecordingType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("createDate")) {
                this.createDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("updateDate")) {
                this.updateDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("excludedSeasons")) {
                this.excludedSeasons = ParseUtils.parseArray(KalturaIntegerValue.class, aNode);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaSeriesRecording");
        kparams.add("epgId", this.epgId);
        kparams.add("channelId", this.channelId);
        kparams.add("seriesId", this.seriesId);
        kparams.add("seasonNumber", this.seasonNumber);
        kparams.add("type", this.type);
        return kparams;
    }

}

