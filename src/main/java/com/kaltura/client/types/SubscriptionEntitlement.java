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

import com.kaltura.client.Params;
import com.kaltura.client.utils.GsonParser;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  KalturaSubscriptionEntitlement  */
@SuppressWarnings("serial")
public class SubscriptionEntitlement extends Entitlement {

	/**  The date of the next renewal (only for subscription)  */
    private Long nextRenewalDate;
	/**  Indicates whether the subscription is renewable in this purchase (only for
	  subscription)  */
    private Boolean isRenewableForPurchase;
	/**  Indicates whether a subscription is renewable (only for subscription)  */
    private Boolean isRenewable;
	/**  Indicates whether the user is currently in his grace period entitlement  */
    private Boolean isInGracePeriod;
	/**  Payment Gateway identifier  */
    private Integer paymentGatewayId;
	/**  Payment Method identifier  */
    private Integer paymentMethodId;
	/**  Scheduled Subscription Identifier  */
    private Long scheduledSubscriptionId;

    // nextRenewalDate:
    public Long getNextRenewalDate(){
        return this.nextRenewalDate;
    }
    public void setNextRenewalDate(Long nextRenewalDate){
        this.nextRenewalDate = nextRenewalDate;
    }

    // isRenewableForPurchase:
    public Boolean getIsRenewableForPurchase(){
        return this.isRenewableForPurchase;
    }
    public void setIsRenewableForPurchase(Boolean isRenewableForPurchase){
        this.isRenewableForPurchase = isRenewableForPurchase;
    }

    // isRenewable:
    public Boolean getIsRenewable(){
        return this.isRenewable;
    }
    public void setIsRenewable(Boolean isRenewable){
        this.isRenewable = isRenewable;
    }

    // isInGracePeriod:
    public Boolean getIsInGracePeriod(){
        return this.isInGracePeriod;
    }
    public void setIsInGracePeriod(Boolean isInGracePeriod){
        this.isInGracePeriod = isInGracePeriod;
    }

    // paymentGatewayId:
    public Integer getPaymentGatewayId(){
        return this.paymentGatewayId;
    }
    public void setPaymentGatewayId(Integer paymentGatewayId){
        this.paymentGatewayId = paymentGatewayId;
    }

    // paymentMethodId:
    public Integer getPaymentMethodId(){
        return this.paymentMethodId;
    }
    public void setPaymentMethodId(Integer paymentMethodId){
        this.paymentMethodId = paymentMethodId;
    }

    // scheduledSubscriptionId:
    public Long getScheduledSubscriptionId(){
        return this.scheduledSubscriptionId;
    }
    public void setScheduledSubscriptionId(Long scheduledSubscriptionId){
        this.scheduledSubscriptionId = scheduledSubscriptionId;
    }


    public SubscriptionEntitlement() {
       super();
    }

    public SubscriptionEntitlement(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        nextRenewalDate = GsonParser.parseLong(jsonObject.get("nextRenewalDate"));
        isRenewableForPurchase = GsonParser.parseBoolean(jsonObject.get("isRenewableForPurchase"));
        isRenewable = GsonParser.parseBoolean(jsonObject.get("isRenewable"));
        isInGracePeriod = GsonParser.parseBoolean(jsonObject.get("isInGracePeriod"));
        paymentGatewayId = GsonParser.parseInt(jsonObject.get("paymentGatewayId"));
        paymentMethodId = GsonParser.parseInt(jsonObject.get("paymentMethodId"));
        scheduledSubscriptionId = GsonParser.parseLong(jsonObject.get("scheduledSubscriptionId"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaSubscriptionEntitlement");
        kparams.add("paymentGatewayId", this.paymentGatewayId);
        kparams.add("paymentMethodId", this.paymentMethodId);
        kparams.add("scheduledSubscriptionId", this.scheduledSubscriptionId);
        return kparams;
    }

}

