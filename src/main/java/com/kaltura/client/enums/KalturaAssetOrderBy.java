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
package com.kaltura.client.enums;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaAssetOrderBy implements KalturaEnumAsString {
    RELEVANCY_DESC ("RELEVANCY_DESC"),
    NAME_ASC ("NAME_ASC"),
    NAME_DESC ("NAME_DESC"),
    VIEWS_DESC ("VIEWS_DESC"),
    RATINGS_DESC ("RATINGS_DESC"),
    VOTES_DESC ("VOTES_DESC"),
    START_DATE_DESC ("START_DATE_DESC"),
    START_DATE_ASC ("START_DATE_ASC"),
    LIKES_DESC ("LIKES_DESC");

    public String hashCode;

    KalturaAssetOrderBy(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaAssetOrderBy get(String hashCode) {
        if (hashCode.equals("RELEVANCY_DESC"))
        {
           return RELEVANCY_DESC;
        }
        else 
        if (hashCode.equals("NAME_ASC"))
        {
           return NAME_ASC;
        }
        else 
        if (hashCode.equals("NAME_DESC"))
        {
           return NAME_DESC;
        }
        else 
        if (hashCode.equals("VIEWS_DESC"))
        {
           return VIEWS_DESC;
        }
        else 
        if (hashCode.equals("RATINGS_DESC"))
        {
           return RATINGS_DESC;
        }
        else 
        if (hashCode.equals("VOTES_DESC"))
        {
           return VOTES_DESC;
        }
        else 
        if (hashCode.equals("START_DATE_DESC"))
        {
           return START_DATE_DESC;
        }
        else 
        if (hashCode.equals("START_DATE_ASC"))
        {
           return START_DATE_ASC;
        }
        else 
        if (hashCode.equals("LIKES_DESC"))
        {
           return LIKES_DESC;
        }
        else 
        {
           return RELEVANCY_DESC;
        }
    }
}
