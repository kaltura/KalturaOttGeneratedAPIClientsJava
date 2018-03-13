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
		String entitlementId();
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
	 * Entitlement identifier
	 */
	private String entitlementId;
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
	public void setId(Integer id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
	}

	// entitlementId:
	public String getEntitlementId(){
		return this.entitlementId;
	}
	public void setEntitlementId(String entitlementId){
		this.entitlementId = entitlementId;
	}

	public void entitlementId(String multirequestToken){
		setToken("entitlementId", multirequestToken);
	}

	// currentUses:
	public Integer getCurrentUses(){
		return this.currentUses;
	}
	public void setCurrentUses(Integer currentUses){
		this.currentUses = currentUses;
	}

	public void currentUses(String multirequestToken){
		setToken("currentUses", multirequestToken);
	}

	// endDate:
	public Long getEndDate(){
		return this.endDate;
	}
	public void setEndDate(Long endDate){
		this.endDate = endDate;
	}

	public void endDate(String multirequestToken){
		setToken("endDate", multirequestToken);
	}

	// currentDate:
	public Long getCurrentDate(){
		return this.currentDate;
	}
	public void setCurrentDate(Long currentDate){
		this.currentDate = currentDate;
	}

	public void currentDate(String multirequestToken){
		setToken("currentDate", multirequestToken);
	}

	// lastViewDate:
	public Long getLastViewDate(){
		return this.lastViewDate;
	}
	public void setLastViewDate(Long lastViewDate){
		this.lastViewDate = lastViewDate;
	}

	public void lastViewDate(String multirequestToken){
		setToken("lastViewDate", multirequestToken);
	}

	// purchaseDate:
	public Long getPurchaseDate(){
		return this.purchaseDate;
	}
	public void setPurchaseDate(Long purchaseDate){
		this.purchaseDate = purchaseDate;
	}

	public void purchaseDate(String multirequestToken){
		setToken("purchaseDate", multirequestToken);
	}

	// paymentMethod:
	public PaymentMethodType getPaymentMethod(){
		return this.paymentMethod;
	}
	public void setPaymentMethod(PaymentMethodType paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public void paymentMethod(String multirequestToken){
		setToken("paymentMethod", multirequestToken);
	}

	// deviceUdid:
	public String getDeviceUdid(){
		return this.deviceUdid;
	}
	public void setDeviceUdid(String deviceUdid){
		this.deviceUdid = deviceUdid;
	}

	public void deviceUdid(String multirequestToken){
		setToken("deviceUdid", multirequestToken);
	}

	// deviceName:
	public String getDeviceName(){
		return this.deviceName;
	}
	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public void deviceName(String multirequestToken){
		setToken("deviceName", multirequestToken);
	}

	// isCancelationWindowEnabled:
	public Boolean getIsCancelationWindowEnabled(){
		return this.isCancelationWindowEnabled;
	}
	public void setIsCancelationWindowEnabled(Boolean isCancelationWindowEnabled){
		this.isCancelationWindowEnabled = isCancelationWindowEnabled;
	}

	public void isCancelationWindowEnabled(String multirequestToken){
		setToken("isCancelationWindowEnabled", multirequestToken);
	}

	// maxUses:
	public Integer getMaxUses(){
		return this.maxUses;
	}
	public void setMaxUses(Integer maxUses){
		this.maxUses = maxUses;
	}

	public void maxUses(String multirequestToken){
		setToken("maxUses", multirequestToken);
	}

	// userId:
	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	public void userId(String multirequestToken){
		setToken("userId", multirequestToken);
	}

	// householdId:
	public Long getHouseholdId(){
		return this.householdId;
	}
	public void setHouseholdId(Long householdId){
		this.householdId = householdId;
	}

	public void householdId(String multirequestToken){
		setToken("householdId", multirequestToken);
	}


	public Entitlement() {
		super();
	}

	public Entitlement(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		entitlementId = GsonParser.parseString(jsonObject.get("entitlementId"));
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

