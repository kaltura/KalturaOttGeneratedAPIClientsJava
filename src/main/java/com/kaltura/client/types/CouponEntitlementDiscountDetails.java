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
// Copyright (C) 2006-2020  Kaltura Inc.
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
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Coupon discount details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CouponEntitlementDiscountDetails.Tokenizer.class)
public class CouponEntitlementDiscountDetails extends EntitlementDiscountDetails {
	
	public interface Tokenizer extends EntitlementDiscountDetails.Tokenizer {
		String couponCode();
		String endlessCoupon();
	}

	/**
	 * Coupon Code
	 */
	private String couponCode;
	/**
	 * Endless coupon
	 */
	private Boolean endlessCoupon;

	// couponCode:
	public String getCouponCode(){
		return this.couponCode;
	}
	// endlessCoupon:
	public Boolean getEndlessCoupon(){
		return this.endlessCoupon;
	}

	public CouponEntitlementDiscountDetails() {
		super();
	}

	public CouponEntitlementDiscountDetails(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		couponCode = GsonParser.parseString(jsonObject.get("couponCode"));
		endlessCoupon = GsonParser.parseBoolean(jsonObject.get("endlessCoupon"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCouponEntitlementDiscountDetails");
		return kparams;
	}

}

