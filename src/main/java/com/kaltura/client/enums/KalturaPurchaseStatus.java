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
public enum KalturaPurchaseStatus implements KalturaEnumAsString {
    PPV_PURCHASED ("ppv_purchased"),
    FREE ("free"),
    FOR_PURCHASE_SUBSCRIPTION_ONLY ("for_purchase_subscription_only"),
    SUBSCRIPTION_PURCHASED ("subscription_purchased"),
    FOR_PURCHASE ("for_purchase"),
    SUBSCRIPTION_PURCHASED_WRONG_CURRENCY ("subscription_purchased_wrong_currency"),
    PRE_PAID_PURCHASED ("pre_paid_purchased"),
    GEO_COMMERCE_BLOCKED ("geo_commerce_blocked"),
    ENTITLED_TO_PREVIEW_MODULE ("entitled_to_preview_module"),
    FIRST_DEVICE_LIMITATION ("first_device_limitation"),
    COLLECTION_PURCHASED ("collection_purchased"),
    USER_SUSPENDED ("user_suspended"),
    NOT_FOR_PURCHASE ("not_for_purchase"),
    INVALID_CURRENCY ("invalid_currency"),
    CURRENCY_NOT_DEFINED_ON_PRICE_CODE ("currency_not_defined_on_price_code");

    public String hashCode;

    KalturaPurchaseStatus(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public static KalturaPurchaseStatus get(String hashCode) {
        if (hashCode.equals("ppv_purchased"))
        {
           return PPV_PURCHASED;
        }
        else 
        if (hashCode.equals("free"))
        {
           return FREE;
        }
        else 
        if (hashCode.equals("for_purchase_subscription_only"))
        {
           return FOR_PURCHASE_SUBSCRIPTION_ONLY;
        }
        else 
        if (hashCode.equals("subscription_purchased"))
        {
           return SUBSCRIPTION_PURCHASED;
        }
        else 
        if (hashCode.equals("for_purchase"))
        {
           return FOR_PURCHASE;
        }
        else 
        if (hashCode.equals("subscription_purchased_wrong_currency"))
        {
           return SUBSCRIPTION_PURCHASED_WRONG_CURRENCY;
        }
        else 
        if (hashCode.equals("pre_paid_purchased"))
        {
           return PRE_PAID_PURCHASED;
        }
        else 
        if (hashCode.equals("geo_commerce_blocked"))
        {
           return GEO_COMMERCE_BLOCKED;
        }
        else 
        if (hashCode.equals("entitled_to_preview_module"))
        {
           return ENTITLED_TO_PREVIEW_MODULE;
        }
        else 
        if (hashCode.equals("first_device_limitation"))
        {
           return FIRST_DEVICE_LIMITATION;
        }
        else 
        if (hashCode.equals("collection_purchased"))
        {
           return COLLECTION_PURCHASED;
        }
        else 
        if (hashCode.equals("user_suspended"))
        {
           return USER_SUSPENDED;
        }
        else 
        if (hashCode.equals("not_for_purchase"))
        {
           return NOT_FOR_PURCHASE;
        }
        else 
        if (hashCode.equals("invalid_currency"))
        {
           return INVALID_CURRENCY;
        }
        else 
        if (hashCode.equals("currency_not_defined_on_price_code"))
        {
           return CURRENCY_NOT_DEFINED_ON_PRICE_CODE;
        }
        else 
        {
           return PPV_PURCHASED;
        }
    }
}
