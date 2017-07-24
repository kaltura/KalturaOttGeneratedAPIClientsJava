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
public class KalturaChannelFilter extends KalturaAssetFilter {
	/**  Channel Id  */
    public int idEqual = Integer.MIN_VALUE;
	/**  Search assets using dynamic criteria. Provided collection of nested expressions
	  with key, comparison operators, value, and logical conjunction.             
	  Possible keys: any Tag or Meta defined in the system and the following reserved
	  keys: start_date, end_date.               epg_id, media_id - for specific asset
	  IDs.              geo_block - only valid value is &amp;quot;true&amp;quot;: When
	  enabled, only assets that are not restriced to the user by geo-block rules will
	  return.              parental_rules - only valid value is
	  &amp;quot;true&amp;quot;: When enabled, only assets that the user
	  doesn&amp;#39;t need to provide PIN code will return.             
	  user_interests - only valid value is &amp;quot;true&amp;quot;. When enabled,
	  only assets that the user defined as his interests (by tags and metas) will
	  return.              epg_channel_id – the channel identifier of the EPG
	  program.              entitled_assets - valid values: &amp;quot;free&amp;quot;,
	  &amp;quot;entitled&amp;quot;, &amp;quot;both&amp;quot;. free - gets only free to
	  watch assets. entitled - only those that the user is implicitly entitled to
	  watch.              Comparison operators: for numerical fields =, &amp;gt;,
	  &amp;gt;=, &amp;lt;, &amp;lt;=, : (in). For alpha-numerical fields =, != (not),
	  ~ (like), !~, ^ (starts with), + (exists), !+ (not exists).              Logical
	  conjunction: and, or.               Search values are limited to 20 characters
	  each.              (maximum length of entire filter is 2048 characters)  */
    public String kSql;

    public KalturaChannelFilter() {
    }

    public KalturaChannelFilter(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("idEqual")) {
                this.idEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("kSql")) {
                this.kSql = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaChannelFilter");
        kparams.add("idEqual", this.idEqual);
        kparams.add("kSql", this.kSql);
        return kparams;
    }

}

