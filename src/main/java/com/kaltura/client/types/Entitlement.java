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
import com.kaltura.client.enums.PaymentMethodType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Entitlement
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Entitlement.Tokenizer.class)
public class Entitlement extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String productId();
		String currentUses();
		String endDate();
		String currentDate();
		String lastViewDate();
		String purchaseDate();
		String paymentMethod();
		String deviceUdid();
		String deviceName();
		String isCancelationWindowEnabled();
		String maxUses();
		String userId();
		String householdId();
	}

	/**
	 * Purchase identifier (for subscriptions and collections only)
	 */
	private Integer id;
	/**
	 * Product identifier
	 */
	private String productId;
	/**
	 * The current number of uses
	 */
	private Integer currentUses;
	/**
	 * The end date of the entitlement
	 */
	private Long endDate;
	/**
	 * Current date
	 */
	private Long currentDate;
	/**
	 * The last date the item was viewed
	 */
	private Long lastViewDate;
	/**
	 * Purchase date
	 */
	private Long purchaseDate;
	/**
	 * Payment Method
	 */
	private PaymentMethodType paymentMethod;
	/**
	 * The UDID of the device from which the purchase was made
	 */
	private String deviceUdid;
	/**
	 * The name of the device from which the purchase was made
	 */
	private String deviceName;
	/**
	 * Indicates whether a cancelation window period is enabled
	 */
	private Boolean isCancelationWindowEnabled;
	/**
	 * The maximum number of uses available for this item (only for subscription and
	  PPV)
	 */
	private Integer maxUses;
	/**
	 * The Identifier of the purchasing user
	 */
	private String userId;
	/**
	 * The Identifier of the purchasing household
	 */
	private Long householdId;

	// id:
	public Integer getId(){
		return this.id;
	}
	// productId:
	public String getProductId(){
		return this.productId;
	}
	// currentUses:
	public Integer getCurrentUses(){
		return this.currentUses;
	}
	// endDate:
	public Long getEndDate(){
		return this.endDate;
	}
	// currentDate:
	public Long getCurrentDate(){
		return this.currentDate;
	}
	// lastViewDate:
	public Long getLastViewDate(){
		return this.lastViewDate;
	}
	// purchaseDate:
	public Long getPurchaseDate(){
		return this.purchaseDate;
	}
	// paymentMethod:
	public PaymentMethodType getPaymentMethod(){
		return this.paymentMethod;
	}
	// deviceUdid:
	public String getDeviceUdid(){
		return this.deviceUdid;
	}
	// deviceName:
	public String getDeviceName(){
		return this.deviceName;
	}
	// isCancelationWindowEnabled:
	public Boolean getIsCancelationWindowEnabled(){
		return this.isCancelationWindowEnabled;
	}
	// maxUses:
	public Integer getMaxUses(){
		return this.maxUses;
	}
	// userId:
	public String getUserId(){
		return this.userId;
	}
	// householdId:
	public Long getHouseholdId(){
		return this.householdId;
	}

	public Entitlement() {
		super();
	}

	public Entitlement(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		productId = GsonParser.parseString(jsonObject.get("productId"));
		currentUses = GsonParser.parseInt(jsonObject.get("currentUses"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		currentDate = GsonParser.parseLong(jsonObject.get("currentDate"));
		lastViewDate = GsonParser.parseLong(jsonObject.get("lastViewDate"));
		purchaseDate = GsonParser.parseLong(jsonObject.get("purchaseDate"));
		paymentMethod = PaymentMethodType.get(GsonParser.parseString(jsonObject.get("paymentMethod")));
		deviceUdid = GsonParser.parseString(jsonObject.get("deviceUdid"));
		deviceName = GsonParser.parseString(jsonObject.get("deviceName"));
		isCancelationWindowEnabled = GsonParser.parseBoolean(jsonObject.get("isCancelationWindowEnabled"));
		maxUses = GsonParser.parseInt(jsonObject.get("maxUses"));
		userId = GsonParser.parseString(jsonObject.get("userId"));
		householdId = GsonParser.parseLong(jsonObject.get("householdId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEntitlement");
		return kparams;
	}

}

