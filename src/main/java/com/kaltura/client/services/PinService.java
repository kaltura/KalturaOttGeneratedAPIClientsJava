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

import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.PinType;
import com.kaltura.client.types.Pin;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PinService {
	
	public static class GetPinBuilder extends RequestBuilder<Pin, Pin.Tokenizer, GetPinBuilder> {
		
		public GetPinBuilder(EntityReferenceBy by, PinType type, int ruleId) {
			super(Pin.class, "pin", "get");
			params.add("by", by);
			params.add("type", type);
			params.add("ruleId", ruleId);
		}
		
		public void by(String multirequestToken) {
			params.add("by", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
	}

	public static GetPinBuilder get(EntityReferenceBy by, PinType type)  {
		return get(by, type, Integer.MIN_VALUE);
	}

	/**
	 * Retrieve the parental or purchase PIN that applies for the household or user.
	  Includes specification of where the PIN was defined at – account, household or
	  user  level
	 * 
	 * @param by Reference type to filter by
	 * @param type The PIN type to retrieve
	 * @param ruleId Rule ID - for PIN per rule (MediaCorp): BEO-1923
	 */
    public static GetPinBuilder get(EntityReferenceBy by, PinType type, int ruleId)  {
		return new GetPinBuilder(by, type, ruleId);
	}
	
	public static class UpdatePinBuilder extends RequestBuilder<Pin, Pin.Tokenizer, UpdatePinBuilder> {
		
		public UpdatePinBuilder(EntityReferenceBy by, PinType type, Pin pin, int ruleId) {
			super(Pin.class, "pin", "update");
			params.add("by", by);
			params.add("type", type);
			params.add("pin", pin);
			params.add("ruleId", ruleId);
		}
		
		public void by(String multirequestToken) {
			params.add("by", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
	}

	public static UpdatePinBuilder update(EntityReferenceBy by, PinType type, Pin pin)  {
		return update(by, type, pin, Integer.MIN_VALUE);
	}

	/**
	 * Set the parental or purchase PIN that applies for the user or the household.
	 * 
	 * @param by Reference type to filter by
	 * @param type The PIN type to retrieve
	 * @param pin PIN to set
	 * @param ruleId Rule ID - for PIN per rule (MediaCorp): BEO-1923
	 */
    public static UpdatePinBuilder update(EntityReferenceBy by, PinType type, Pin pin, int ruleId)  {
		return new UpdatePinBuilder(by, type, pin, ruleId);
	}
	
	public static class ValidatePinBuilder extends RequestBuilder<Boolean, String, ValidatePinBuilder> {
		
		public ValidatePinBuilder(String pin, PinType type, int ruleId) {
			super(Boolean.class, "pin", "validate");
			params.add("pin", pin);
			params.add("type", type);
			params.add("ruleId", ruleId);
		}
		
		public void pin(String multirequestToken) {
			params.add("pin", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
		
		public void ruleId(String multirequestToken) {
			params.add("ruleId", multirequestToken);
		}
	}

	public static ValidatePinBuilder validate(String pin, PinType type)  {
		return validate(pin, type, Integer.MIN_VALUE);
	}

	/**
	 * Validate a purchase or parental PIN for a user.
	 * 
	 * @param pin PIN to validate
	 * @param type The PIN type to retrieve
	 * @param ruleId Rule ID - for PIN per rule (MediaCorp): BEO-1923
	 */
    public static ValidatePinBuilder validate(String pin, PinType type, int ruleId)  {
		return new ValidatePinBuilder(pin, type, ruleId);
	}
}
