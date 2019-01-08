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
import com.kaltura.client.enums.CouponStatus;
import com.kaltura.client.types.CouponsGroup;
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
 * Coupon details container
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Coupon.Tokenizer.class)
public class Coupon extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		CouponsGroup.Tokenizer couponsGroup();
		String status();
		String totalUses();
		String leftUses();
	}

	/**
	 * Coupons group details
	 */
	private CouponsGroup couponsGroup;
	/**
	 * Coupon status
	 */
	private CouponStatus status;
	/**
	 * Total available coupon uses
	 */
	private Integer totalUses;
	/**
	 * Left coupon uses
	 */
	private Integer leftUses;

	// couponsGroup:
	public CouponsGroup getCouponsGroup(){
		return this.couponsGroup;
	}
	// status:
	public CouponStatus getStatus(){
		return this.status;
	}
	// totalUses:
	public Integer getTotalUses(){
		return this.totalUses;
	}
	// leftUses:
	public Integer getLeftUses(){
		return this.leftUses;
	}

	public Coupon() {
		super();
	}

	public Coupon(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		couponsGroup = GsonParser.parseObject(jsonObject.getAsJsonObject("couponsGroup"), CouponsGroup.class);
		status = CouponStatus.get(GsonParser.parseString(jsonObject.get("status")));
		totalUses = GsonParser.parseInt(jsonObject.get("totalUses"));
		leftUses = GsonParser.parseInt(jsonObject.get("leftUses"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCoupon");
		return kparams;
	}

}

