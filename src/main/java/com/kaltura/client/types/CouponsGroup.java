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
import com.kaltura.client.enums.CouponGroupType;
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
 * Coupons group details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CouponsGroup.Tokenizer.class)
public class CouponsGroup extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String startDate();
		String endDate();
		String maxUsesNumber();
		String maxUsesNumberOnRenewableSub();
		String couponGroupType();
		String maxHouseholdUses();
		String discountId();
	}

	/**
	 * Coupon group identifier
	 */
	private String id;
	/**
	 * Coupon group name
	 */
	private String name;
	/**
	 * The first date the coupons in this coupons group are valid
	 */
	private Long startDate;
	/**
	 * The last date the coupons in this coupons group are valid
	 */
	private Long endDate;
	/**
	 * Maximum number of uses for each coupon in the group
	 */
	private Integer maxUsesNumber;
	/**
	 * Maximum number of uses for each coupon in the group on a renewable subscription
	 */
	private Integer maxUsesNumberOnRenewableSub;
	/**
	 * Type of the coupon group
	 */
	private CouponGroupType couponGroupType;
	/**
	 * Maximum number of uses per household for each coupon in the group
	 */
	private Integer maxHouseholdUses;
	/**
	 * Discount ID
	 */
	private Long discountId;

	// id:
	public String getId(){
		return this.id;
	}
	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// startDate:
	public Long getStartDate(){
		return this.startDate;
	}
	public void setStartDate(Long startDate){
		this.startDate = startDate;
	}

	public void startDate(String multirequestToken){
		setToken("startDate", multirequestToken);
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

	// maxUsesNumber:
	public Integer getMaxUsesNumber(){
		return this.maxUsesNumber;
	}
	public void setMaxUsesNumber(Integer maxUsesNumber){
		this.maxUsesNumber = maxUsesNumber;
	}

	public void maxUsesNumber(String multirequestToken){
		setToken("maxUsesNumber", multirequestToken);
	}

	// maxUsesNumberOnRenewableSub:
	public Integer getMaxUsesNumberOnRenewableSub(){
		return this.maxUsesNumberOnRenewableSub;
	}
	public void setMaxUsesNumberOnRenewableSub(Integer maxUsesNumberOnRenewableSub){
		this.maxUsesNumberOnRenewableSub = maxUsesNumberOnRenewableSub;
	}

	public void maxUsesNumberOnRenewableSub(String multirequestToken){
		setToken("maxUsesNumberOnRenewableSub", multirequestToken);
	}

	// couponGroupType:
	public CouponGroupType getCouponGroupType(){
		return this.couponGroupType;
	}
	public void setCouponGroupType(CouponGroupType couponGroupType){
		this.couponGroupType = couponGroupType;
	}

	public void couponGroupType(String multirequestToken){
		setToken("couponGroupType", multirequestToken);
	}

	// maxHouseholdUses:
	public Integer getMaxHouseholdUses(){
		return this.maxHouseholdUses;
	}
	public void setMaxHouseholdUses(Integer maxHouseholdUses){
		this.maxHouseholdUses = maxHouseholdUses;
	}

	public void maxHouseholdUses(String multirequestToken){
		setToken("maxHouseholdUses", multirequestToken);
	}

	// discountId:
	public Long getDiscountId(){
		return this.discountId;
	}
	public void setDiscountId(Long discountId){
		this.discountId = discountId;
	}

	public void discountId(String multirequestToken){
		setToken("discountId", multirequestToken);
	}


	public CouponsGroup() {
		super();
	}

	public CouponsGroup(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		maxUsesNumber = GsonParser.parseInt(jsonObject.get("maxUsesNumber"));
		maxUsesNumberOnRenewableSub = GsonParser.parseInt(jsonObject.get("maxUsesNumberOnRenewableSub"));
		couponGroupType = CouponGroupType.get(GsonParser.parseString(jsonObject.get("couponGroupType")));
		maxHouseholdUses = GsonParser.parseInt(jsonObject.get("maxHouseholdUses"));
		discountId = GsonParser.parseLong(jsonObject.get("discountId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCouponsGroup");
		kparams.add("name", this.name);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("maxUsesNumber", this.maxUsesNumber);
		kparams.add("maxUsesNumberOnRenewableSub", this.maxUsesNumberOnRenewableSub);
		kparams.add("couponGroupType", this.couponGroupType);
		kparams.add("maxHouseholdUses", this.maxHouseholdUses);
		kparams.add("discountId", this.discountId);
		return kparams;
	}

}

