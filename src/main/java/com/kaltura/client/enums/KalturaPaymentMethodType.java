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
public enum KalturaPaymentMethodType implements KalturaEnumAsString {
    UNKNOWN ("unknown"),
    CREDIT_CARD ("credit_card"),
    SMS ("sms"),
    PAY_PAL ("pay_pal"),
    DEBIT_CARD ("debit_card"),
    IDEAL ("ideal"),
    INCASO ("incaso"),
    GIFT ("gift"),
    VISA ("visa"),
    MASTER_CARD ("master_card"),
    IN_APP ("in_app"),
    M1 ("m1"),
    CHANGE_SUBSCRIPTION ("change_subscription"),
    OFFLINE ("offline");

    public String hashCode;

    KalturaPaymentMethodType(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaPaymentMethodType get(String hashCode) {
        if (hashCode.equals("unknown"))
        {
           return UNKNOWN;
        }
        else 
        if (hashCode.equals("credit_card"))
        {
           return CREDIT_CARD;
        }
        else 
        if (hashCode.equals("sms"))
        {
           return SMS;
        }
        else 
        if (hashCode.equals("pay_pal"))
        {
           return PAY_PAL;
        }
        else 
        if (hashCode.equals("debit_card"))
        {
           return DEBIT_CARD;
        }
        else 
        if (hashCode.equals("ideal"))
        {
           return IDEAL;
        }
        else 
        if (hashCode.equals("incaso"))
        {
           return INCASO;
        }
        else 
        if (hashCode.equals("gift"))
        {
           return GIFT;
        }
        else 
        if (hashCode.equals("visa"))
        {
           return VISA;
        }
        else 
        if (hashCode.equals("master_card"))
        {
           return MASTER_CARD;
        }
        else 
        if (hashCode.equals("in_app"))
        {
           return IN_APP;
        }
        else 
        if (hashCode.equals("m1"))
        {
           return M1;
        }
        else 
        if (hashCode.equals("change_subscription"))
        {
           return CHANGE_SUBSCRIPTION;
        }
        else 
        if (hashCode.equals("offline"))
        {
           return OFFLINE;
        }
        else 
        {
           return UNKNOWN;
        }
    }
}
