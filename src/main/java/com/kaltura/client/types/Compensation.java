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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.enums.CompensationType;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Compensation request parameters  */
@SuppressWarnings("serial")
public class Compensation extends ObjectBase {

	/**  Compensation identifier  */
    private Long id;
	/**  Subscription identifier  */
    private Long subscriptionId;
	/**  Compensation type  */
    private CompensationType compensationType;
	/**  Compensation amount  */
    private Double amount;
	/**  The number of renewals for compensation  */
    private Integer totalRenewalIterations;
	/**  The number of renewals the compensation was already applied on  */
    private Integer appliedRenewalIterations;
	/**  Purchase identifier  */
    private Integer purchaseId;

    // id:
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }

    // subscriptionId:
    public Long getSubscriptionId(){
        return this.subscriptionId;
    }
    public void setSubscriptionId(Long subscriptionId){
        this.subscriptionId = subscriptionId;
    }

    // compensationType:
    public CompensationType getCompensationType(){
        return this.compensationType;
    }
    public void setCompensationType(CompensationType compensationType){
        this.compensationType = compensationType;
    }

    // amount:
    public Double getAmount(){
        return this.amount;
    }
    public void setAmount(Double amount){
        this.amount = amount;
    }

    // totalRenewalIterations:
    public Integer getTotalRenewalIterations(){
        return this.totalRenewalIterations;
    }
    public void setTotalRenewalIterations(Integer totalRenewalIterations){
        this.totalRenewalIterations = totalRenewalIterations;
    }

    // appliedRenewalIterations:
    public Integer getAppliedRenewalIterations(){
        return this.appliedRenewalIterations;
    }
    public void setAppliedRenewalIterations(Integer appliedRenewalIterations){
        this.appliedRenewalIterations = appliedRenewalIterations;
    }

    // purchaseId:
    public Integer getPurchaseId(){
        return this.purchaseId;
    }
    public void setPurchaseId(Integer purchaseId){
        this.purchaseId = purchaseId;
    }


    public Compensation() {
       super();
    }

    public Compensation(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        id = GsonParser.parseLong(jsonObject.get("id"));
        subscriptionId = GsonParser.parseLong(jsonObject.get("subscriptionId"));
        compensationType = CompensationType.get(GsonParser.parseString(jsonObject.get("compensationType")));
        amount = GsonParser.parseDouble(jsonObject.get("amount"));
        totalRenewalIterations = GsonParser.parseInt(jsonObject.get("totalRenewalIterations"));
        appliedRenewalIterations = GsonParser.parseInt(jsonObject.get("appliedRenewalIterations"));
        purchaseId = GsonParser.parseInt(jsonObject.get("purchaseId"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaCompensation");
        kparams.add("compensationType", this.compensationType);
        kparams.add("amount", this.amount);
        kparams.add("totalRenewalIterations", this.totalRenewalIterations);
        kparams.add("purchaseId", this.purchaseId);
        return kparams;
    }

}

