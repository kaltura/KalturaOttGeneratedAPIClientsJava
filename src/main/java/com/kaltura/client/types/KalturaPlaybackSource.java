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
import com.kaltura.client.enums.KalturaAdsPolicy;
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
public class KalturaPlaybackSource extends KalturaMediaFile {
	/**  Source format according to delivery profile streamer type (applehttp, mpegdash
	  etc.)  */
    public String format;
	/**  Comma separated string according to deliveryProfile media protocols
	  (&amp;#39;http,https&amp;#39; etc.)  */
    public String protocols;
	/**  DRM data object containing relevant license URL ,scheme name and certificate  */
    public ArrayList<KalturaDrmPlaybackPluginData> drm;
	/**  Ads policy  */
    public KalturaAdsPolicy adsPolicy;
	/**  The parameters to pass to the ads server  */
    public String adsParam;

    public KalturaPlaybackSource() {
    }

    public KalturaPlaybackSource(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("format")) {
                this.format = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("protocols")) {
                this.protocols = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("drm")) {
                this.drm = ParseUtils.parseArray(KalturaDrmPlaybackPluginData.class, aNode);
                continue;
            } else if (nodeName.equals("adsPolicy")) {
                this.adsPolicy = KalturaAdsPolicy.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("adsParam")) {
                this.adsParam = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaPlaybackSource");
        kparams.add("format", this.format);
        kparams.add("protocols", this.protocols);
        kparams.add("drm", this.drm);
        kparams.add("adsPolicy", this.adsPolicy);
        kparams.add("adsParam", this.adsParam);
        return kparams;
    }

}

