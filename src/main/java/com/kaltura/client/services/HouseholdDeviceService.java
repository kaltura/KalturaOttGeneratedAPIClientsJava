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
package com.kaltura.client.services;

import com.kaltura.client.enums.DeviceStatus;
import com.kaltura.client.types.DevicePin;
import com.kaltura.client.types.HouseholdDevice;
import com.kaltura.client.types.HouseholdDeviceFilter;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdDeviceService {
	
	public static class AddHouseholdDeviceBuilder extends RequestBuilder<HouseholdDevice, HouseholdDevice.Tokenizer, AddHouseholdDeviceBuilder> {
		
		public AddHouseholdDeviceBuilder(HouseholdDevice device) {
			super(HouseholdDevice.class, "householddevice", "add");
			params.add("device", device);
		}
	}

	/**
	 * Add device to household
	 * 
	 * @param device Device
	 */
    public static AddHouseholdDeviceBuilder add(HouseholdDevice device)  {
		return new AddHouseholdDeviceBuilder(device);
	}
	
	public static class AddByPinHouseholdDeviceBuilder extends RequestBuilder<HouseholdDevice, HouseholdDevice.Tokenizer, AddByPinHouseholdDeviceBuilder> {
		
		public AddByPinHouseholdDeviceBuilder(String deviceName, String pin) {
			super(HouseholdDevice.class, "householddevice", "addByPin");
			params.add("deviceName", deviceName);
			params.add("pin", pin);
		}
		
		public void deviceName(String multirequestToken) {
			params.add("deviceName", multirequestToken);
		}
		
		public void pin(String multirequestToken) {
			params.add("pin", multirequestToken);
		}
	}

	/**
	 * Registers a device to a household using pin code
	 * 
	 * @param deviceName Device name
	 * @param pin Pin code
	 */
    public static AddByPinHouseholdDeviceBuilder addByPin(String deviceName, String pin)  {
		return new AddByPinHouseholdDeviceBuilder(deviceName, pin);
	}
	
	public static class DeleteHouseholdDeviceBuilder extends RequestBuilder<Boolean, String, DeleteHouseholdDeviceBuilder> {
		
		public DeleteHouseholdDeviceBuilder(String udid) {
			super(Boolean.class, "householddevice", "delete");
			params.add("udid", udid);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	/**
	 * Removes a device from household
	 * 
	 * @param udid device UDID
	 */
    public static DeleteHouseholdDeviceBuilder delete(String udid)  {
		return new DeleteHouseholdDeviceBuilder(udid);
	}
	
	public static class GeneratePinHouseholdDeviceBuilder extends RequestBuilder<DevicePin, DevicePin.Tokenizer, GeneratePinHouseholdDeviceBuilder> {
		
		public GeneratePinHouseholdDeviceBuilder(String udid, int brandId) {
			super(DevicePin.class, "householddevice", "generatePin");
			params.add("udid", udid);
			params.add("brandId", brandId);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
		
		public void brandId(String multirequestToken) {
			params.add("brandId", multirequestToken);
		}
	}

	/**
	 * Generates device pin to use when adding a device to household by pin
	 * 
	 * @param udid Device UDID
	 * @param brandId Device brand identifier
	 */
    public static GeneratePinHouseholdDeviceBuilder generatePin(String udid, int brandId)  {
		return new GeneratePinHouseholdDeviceBuilder(udid, brandId);
	}
	
	public static class GetHouseholdDeviceBuilder extends RequestBuilder<HouseholdDevice, HouseholdDevice.Tokenizer, GetHouseholdDeviceBuilder> {
		
		public GetHouseholdDeviceBuilder() {
			super(HouseholdDevice.class, "householddevice", "get");
		}
	}

	/**
	 * Returns device registration status to the supplied household
	 */
    public static GetHouseholdDeviceBuilder get()  {
		return new GetHouseholdDeviceBuilder();
	}
	
	public static class ListHouseholdDeviceBuilder extends ListResponseRequestBuilder<HouseholdDevice, HouseholdDevice.Tokenizer, ListHouseholdDeviceBuilder> {
		
		public ListHouseholdDeviceBuilder(HouseholdDeviceFilter filter) {
			super(HouseholdDevice.class, "householddevice", "list");
			params.add("filter", filter);
		}
	}

	public static ListHouseholdDeviceBuilder list()  {
		return list(null);
	}

	/**
	 * Returns the devices within the household
	 * 
	 * @param filter Household devices filter
	 */
    public static ListHouseholdDeviceBuilder list(HouseholdDeviceFilter filter)  {
		return new ListHouseholdDeviceBuilder(filter);
	}
	
	public static class LoginWithPinHouseholdDeviceBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginWithPinHouseholdDeviceBuilder> {
		
		public LoginWithPinHouseholdDeviceBuilder(int partnerId, String pin, String udid) {
			super(LoginResponse.class, "householddevice", "loginWithPin");
			params.add("partnerId", partnerId);
			params.add("pin", pin);
			params.add("udid", udid);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void pin(String multirequestToken) {
			params.add("pin", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static LoginWithPinHouseholdDeviceBuilder loginWithPin(int partnerId, String pin)  {
		return loginWithPin(partnerId, pin, null);
	}

	/**
	 * User sign-in via a time-expired sign-in PIN.
	 * 
	 * @param partnerId Partner Identifier
	 * @param pin pin code
	 * @param udid Device UDID
	 */
    public static LoginWithPinHouseholdDeviceBuilder loginWithPin(int partnerId, String pin, String udid)  {
		return new LoginWithPinHouseholdDeviceBuilder(partnerId, pin, udid);
	}
	
	public static class UpdateHouseholdDeviceBuilder extends RequestBuilder<HouseholdDevice, HouseholdDevice.Tokenizer, UpdateHouseholdDeviceBuilder> {
		
		public UpdateHouseholdDeviceBuilder(String udid, HouseholdDevice device) {
			super(HouseholdDevice.class, "householddevice", "update");
			params.add("udid", udid);
			params.add("device", device);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	/**
	 * Update the name of the device by UDID
	 * 
	 * @param udid Device UDID
	 * @param device Device object
	 */
    public static UpdateHouseholdDeviceBuilder update(String udid, HouseholdDevice device)  {
		return new UpdateHouseholdDeviceBuilder(udid, device);
	}
	
	public static class UpdateStatusHouseholdDeviceBuilder extends RequestBuilder<Boolean, String, UpdateStatusHouseholdDeviceBuilder> {
		
		public UpdateStatusHouseholdDeviceBuilder(String udid, DeviceStatus status) {
			super(Boolean.class, "householddevice", "updateStatus");
			params.add("udid", udid);
			params.add("status", status);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
		
		public void status(String multirequestToken) {
			params.add("status", multirequestToken);
		}
	}

	/**
	 * Update the name of the device by UDID
	 * 
	 * @param udid Device UDID
	 * @param status Device status
	 */
    public static UpdateStatusHouseholdDeviceBuilder updateStatus(String udid, DeviceStatus status)  {
		return new UpdateStatusHouseholdDeviceBuilder(udid, status);
	}
}
