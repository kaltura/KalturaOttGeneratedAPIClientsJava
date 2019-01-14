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
// Copyright (C) 2006-2018  Kaltura Inc.
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

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * KalturaSubscriptionEntitlement
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SubscriptionEntitlement.Tokenizer.class)
public class SubscriptionEntitlement extends Entitlement {
	
	public interface Tokenizer extends Entitlement.Tokenizer {
		String nextRenewalDate();
		String isRenewableForPurchase();
		String isRenewable();
		String isInGracePeriod();
		String paymentGatewayId();
		String paymentMethodId();
		String scheduledSubscriptionId();
		String unifiedPaymentId();
		String isSuspended();
	}

	/**
	 * The date of the next renewal (only for subscription)
	 */
	private Long nextRenewalDate;
	/**
	 * Indicates whether the subscription is renewable in this purchase (only for
	  subscription)
	 */
	private Boolean isRenewableForPurchase;
	/**
	 * Indicates whether a subscription is renewable (only for subscription)
	 */
	private Boolean isRenewable;
	/**
	 * Indicates whether the user is currently in his grace period entitlement
	 */
	private Boolean isInGracePeriod;
	/**
	 * Payment Gateway identifier
	 */
	private Integer paymentGatewayId;
	/**
	 * Payment Method identifier
	 */
	private Integer paymentMethodId;
	/**
	 * Scheduled Subscription Identifier
	 */
	private Long scheduledSubscriptionId;
	/**
	 * Unified payment identifier
	 */
	private Long unifiedPaymentId;
	/**
	 * Indicates if the subscription suspended
	 */
	private Boolean isSuspended;

	// nextRenewalDate:
	public Long getNextRenewalDate(){
		return this.nextRenewalDate;
	}
	// isRenewableForPurchase:
	public Boolean getIsRenewableForPurchase(){
		return this.isRenewableForPurchase;
	}
	// isRenewable:
	public Boolean getIsRenewable(){
		return this.isRenewable;
	}
	// isInGracePeriod:
	public Boolean getIsInGracePeriod(){
		return this.isInGracePeriod;
	}
	// paymentGatewayId:
	public Integer getPaymentGatewayId(){
		return this.paymentGatewayId;
	}
	public void setPaymentGatewayId(Integer paymentGatewayId){
		this.paymentGatewayId = paymentGatewayId;
	}

	public void paymentGatewayId(String multirequestToken){
		setToken("paymentGatewayId", multirequestToken);
	}

	// paymentMethodId:
	public Integer getPaymentMethodId(){
		return this.paymentMethodId;
	}
	public void setPaymentMethodId(Integer paymentMethodId){
		this.paymentMethodId = paymentMethodId;
	}

	public void paymentMethodId(String multirequestToken){
		setToken("paymentMethodId", multirequestToken);
	}

	// scheduledSubscriptionId:
	public Long getScheduledSubscriptionId(){
		return this.scheduledSubscriptionId;
	}
	// unifiedPaymentId:
	public Long getUnifiedPaymentId(){
		return this.unifiedPaymentId;
	}
	// isSuspended:
	public Boolean getIsSuspended(){
		return this.isSuspended;
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
		unifiedPaymentId = GsonParser.parseLong(jsonObject.get("unifiedPaymentId"));
		isSuspended = GsonParser.parseBoolean(jsonObject.get("isSuspended"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubscriptionEntitlement");
		kparams.add("paymentGatewayId", this.paymentGatewayId);
		kparams.add("paymentMethodId", this.paymentMethodId);
		return kparams;
	}

}

