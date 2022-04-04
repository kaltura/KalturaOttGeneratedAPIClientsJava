// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
// text.
//
// Copyright (C) 2006-2022  Kaltura Inc.
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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SubscriptionFilter.Tokenizer.class)
public class SubscriptionFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String subscriptionIdIn();
		String mediaFileIdEqual();
		String externalIdIn();
		String couponGroupIdEqual();
		String previewModuleIdEqual();
		String pricePlanIdEqual();
		String channelIdEqual();
		String kSql();
		String alsoInactive();
	}

	/**
	 * Comma separated subscription IDs to get the subscriptions by
	 */
	private String subscriptionIdIn;
	/**
	 * Media-file ID to get the subscriptions by
	 */
	private Integer mediaFileIdEqual;
	/**
	 * Comma separated subscription external IDs to get the subscriptions by
	 */
	private String externalIdIn;
	/**
	 * couponGroupIdEqual
	 */
	private Integer couponGroupIdEqual;
	/**
	 * previewModuleIdEqual
	 */
	private Long previewModuleIdEqual;
	/**
	 * pricePlanIdEqual
	 */
	private Long pricePlanIdEqual;
	/**
	 * channelIdEqual
	 */
	private Long channelIdEqual;
	/**
	 * KSQL expression
	 */
	private String kSql;
	/**
	 * return also inactive
	 */
	private Boolean alsoInactive;

	// subscriptionIdIn:
	public String getSubscriptionIdIn(){
		return this.subscriptionIdIn;
	}
	public void setSubscriptionIdIn(String subscriptionIdIn){
		this.subscriptionIdIn = subscriptionIdIn;
	}

	public void subscriptionIdIn(String multirequestToken){
		setToken("subscriptionIdIn", multirequestToken);
	}

	// mediaFileIdEqual:
	public Integer getMediaFileIdEqual(){
		return this.mediaFileIdEqual;
	}
	public void setMediaFileIdEqual(Integer mediaFileIdEqual){
		this.mediaFileIdEqual = mediaFileIdEqual;
	}

	public void mediaFileIdEqual(String multirequestToken){
		setToken("mediaFileIdEqual", multirequestToken);
	}

	// externalIdIn:
	public String getExternalIdIn(){
		return this.externalIdIn;
	}
	public void setExternalIdIn(String externalIdIn){
		this.externalIdIn = externalIdIn;
	}

	public void externalIdIn(String multirequestToken){
		setToken("externalIdIn", multirequestToken);
	}

	// couponGroupIdEqual:
	public Integer getCouponGroupIdEqual(){
		return this.couponGroupIdEqual;
	}
	public void setCouponGroupIdEqual(Integer couponGroupIdEqual){
		this.couponGroupIdEqual = couponGroupIdEqual;
	}

	public void couponGroupIdEqual(String multirequestToken){
		setToken("couponGroupIdEqual", multirequestToken);
	}

	// previewModuleIdEqual:
	public Long getPreviewModuleIdEqual(){
		return this.previewModuleIdEqual;
	}
	public void setPreviewModuleIdEqual(Long previewModuleIdEqual){
		this.previewModuleIdEqual = previewModuleIdEqual;
	}

	public void previewModuleIdEqual(String multirequestToken){
		setToken("previewModuleIdEqual", multirequestToken);
	}

	// pricePlanIdEqual:
	public Long getPricePlanIdEqual(){
		return this.pricePlanIdEqual;
	}
	public void setPricePlanIdEqual(Long pricePlanIdEqual){
		this.pricePlanIdEqual = pricePlanIdEqual;
	}

	public void pricePlanIdEqual(String multirequestToken){
		setToken("pricePlanIdEqual", multirequestToken);
	}

	// channelIdEqual:
	public Long getChannelIdEqual(){
		return this.channelIdEqual;
	}
	public void setChannelIdEqual(Long channelIdEqual){
		this.channelIdEqual = channelIdEqual;
	}

	public void channelIdEqual(String multirequestToken){
		setToken("channelIdEqual", multirequestToken);
	}

	// kSql:
	public String getKSql(){
		return this.kSql;
	}
	public void setKSql(String kSql){
		this.kSql = kSql;
	}

	public void kSql(String multirequestToken){
		setToken("kSql", multirequestToken);
	}

	// alsoInactive:
	public Boolean getAlsoInactive(){
		return this.alsoInactive;
	}
	public void setAlsoInactive(Boolean alsoInactive){
		this.alsoInactive = alsoInactive;
	}

	public void alsoInactive(String multirequestToken){
		setToken("alsoInactive", multirequestToken);
	}


	public SubscriptionFilter() {
		super();
	}

	public SubscriptionFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		subscriptionIdIn = GsonParser.parseString(jsonObject.get("subscriptionIdIn"));
		mediaFileIdEqual = GsonParser.parseInt(jsonObject.get("mediaFileIdEqual"));
		externalIdIn = GsonParser.parseString(jsonObject.get("externalIdIn"));
		couponGroupIdEqual = GsonParser.parseInt(jsonObject.get("couponGroupIdEqual"));
		previewModuleIdEqual = GsonParser.parseLong(jsonObject.get("previewModuleIdEqual"));
		pricePlanIdEqual = GsonParser.parseLong(jsonObject.get("pricePlanIdEqual"));
		channelIdEqual = GsonParser.parseLong(jsonObject.get("channelIdEqual"));
		kSql = GsonParser.parseString(jsonObject.get("kSql"));
		alsoInactive = GsonParser.parseBoolean(jsonObject.get("alsoInactive"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubscriptionFilter");
		kparams.add("subscriptionIdIn", this.subscriptionIdIn);
		kparams.add("mediaFileIdEqual", this.mediaFileIdEqual);
		kparams.add("externalIdIn", this.externalIdIn);
		kparams.add("couponGroupIdEqual", this.couponGroupIdEqual);
		kparams.add("previewModuleIdEqual", this.previewModuleIdEqual);
		kparams.add("pricePlanIdEqual", this.pricePlanIdEqual);
		kparams.add("channelIdEqual", this.channelIdEqual);
		kparams.add("kSql", this.kSql);
		kparams.add("alsoInactive", this.alsoInactive);
		return kparams;
	}

}

