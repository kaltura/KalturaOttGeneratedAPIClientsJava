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
// Copyright (C) 2006-2023  Kaltura Inc.
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
import com.kaltura.client.enums.DeviceStatus;
import com.kaltura.client.types.CustomDrmPlaybackPluginData;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Device details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(HouseholdDevice.Tokenizer.class)
public class HouseholdDevice extends OTTObjectSupportNullable {
	
	public interface Tokenizer extends OTTObjectSupportNullable.Tokenizer {
		String householdId();
		String udid();
		String name();
		String brandId();
		String activatedOn();
		String status();
		String deviceFamilyId();
		CustomDrmPlaybackPluginData.Tokenizer drm();
		String externalId();
		String macAddress();
		RequestBuilder.MapTokenizer<StringValue.Tokenizer> dynamicData();
		String model();
		String manufacturer();
		String manufacturerId();
		String lastActivityTime();
	}

	/**
	 * Household identifier
	 */
	private Integer householdId;
	/**
	 * Device UDID
	 */
	private String udid;
	/**
	 * Device name
	 */
	private String name;
	/**
	 * Device brand identifier
	 */
	private Integer brandId;
	/**
	 * Device activation date (epoch)
	 */
	private Long activatedOn;
	/**
	 * Device state
	 */
	private DeviceStatus status;
	/**
	 * Device family id
	 */
	private Long deviceFamilyId;
	/**
	 * Device DRM data
	 */
	private CustomDrmPlaybackPluginData drm;
	/**
	 * external Id
	 */
	private String externalId;
	/**
	 * mac address
	 */
	private String macAddress;
	/**
	 * Dynamic data
	 */
	private Map<String, StringValue> dynamicData;
	/**
	 * model
	 */
	private String model;
	/**
	 * manufacturer
	 */
	private String manufacturer;
	/**
	 * manufacturer Id, read only
	 */
	private Long manufacturerId;
	/**
	 * Last Activity Time, read only
	 */
	private Long lastActivityTime;

	// householdId:
	public Integer getHouseholdId(){
		return this.householdId;
	}
	public void setHouseholdId(Integer householdId){
		this.householdId = householdId;
	}

	public void householdId(String multirequestToken){
		setToken("householdId", multirequestToken);
	}

	// udid:
	public String getUdid(){
		return this.udid;
	}
	public void setUdid(String udid){
		this.udid = udid;
	}

	public void udid(String multirequestToken){
		setToken("udid", multirequestToken);
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

	// brandId:
	public Integer getBrandId(){
		return this.brandId;
	}
	public void setBrandId(Integer brandId){
		this.brandId = brandId;
	}

	public void brandId(String multirequestToken){
		setToken("brandId", multirequestToken);
	}

	// activatedOn:
	public Long getActivatedOn(){
		return this.activatedOn;
	}
	public void setActivatedOn(Long activatedOn){
		this.activatedOn = activatedOn;
	}

	public void activatedOn(String multirequestToken){
		setToken("activatedOn", multirequestToken);
	}

	// status:
	public DeviceStatus getStatus(){
		return this.status;
	}
	// deviceFamilyId:
	public Long getDeviceFamilyId(){
		return this.deviceFamilyId;
	}
	// drm:
	public CustomDrmPlaybackPluginData getDrm(){
		return this.drm;
	}
	// externalId:
	public String getExternalId(){
		return this.externalId;
	}
	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public void externalId(String multirequestToken){
		setToken("externalId", multirequestToken);
	}

	// macAddress:
	public String getMacAddress(){
		return this.macAddress;
	}
	public void setMacAddress(String macAddress){
		this.macAddress = macAddress;
	}

	public void macAddress(String multirequestToken){
		setToken("macAddress", multirequestToken);
	}

	// dynamicData:
	public Map<String, StringValue> getDynamicData(){
		return this.dynamicData;
	}
	public void setDynamicData(Map<String, StringValue> dynamicData){
		this.dynamicData = dynamicData;
	}

	// model:
	public String getModel(){
		return this.model;
	}
	public void setModel(String model){
		this.model = model;
	}

	public void model(String multirequestToken){
		setToken("model", multirequestToken);
	}

	// manufacturer:
	public String getManufacturer(){
		return this.manufacturer;
	}
	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}

	public void manufacturer(String multirequestToken){
		setToken("manufacturer", multirequestToken);
	}

	// manufacturerId:
	public Long getManufacturerId(){
		return this.manufacturerId;
	}
	// lastActivityTime:
	public Long getLastActivityTime(){
		return this.lastActivityTime;
	}

	public HouseholdDevice() {
		super();
	}

	public HouseholdDevice(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		householdId = GsonParser.parseInt(jsonObject.get("householdId"));
		udid = GsonParser.parseString(jsonObject.get("udid"));
		name = GsonParser.parseString(jsonObject.get("name"));
		brandId = GsonParser.parseInt(jsonObject.get("brandId"));
		activatedOn = GsonParser.parseLong(jsonObject.get("activatedOn"));
		status = DeviceStatus.get(GsonParser.parseString(jsonObject.get("status")));
		deviceFamilyId = GsonParser.parseLong(jsonObject.get("deviceFamilyId"));
		drm = GsonParser.parseObject(jsonObject.getAsJsonObject("drm"), CustomDrmPlaybackPluginData.class);
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		macAddress = GsonParser.parseString(jsonObject.get("macAddress"));
		dynamicData = GsonParser.parseMap(jsonObject.getAsJsonObject("dynamicData"), StringValue.class);
		model = GsonParser.parseString(jsonObject.get("model"));
		manufacturer = GsonParser.parseString(jsonObject.get("manufacturer"));
		manufacturerId = GsonParser.parseLong(jsonObject.get("manufacturerId"));
		lastActivityTime = GsonParser.parseLong(jsonObject.get("lastActivityTime"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaHouseholdDevice");
		kparams.add("householdId", this.householdId);
		kparams.add("udid", this.udid);
		kparams.add("name", this.name);
		kparams.add("brandId", this.brandId);
		kparams.add("activatedOn", this.activatedOn);
		kparams.add("externalId", this.externalId);
		kparams.add("macAddress", this.macAddress);
		kparams.add("dynamicData", this.dynamicData);
		kparams.add("model", this.model);
		kparams.add("manufacturer", this.manufacturer);
		return kparams;
	}

}

