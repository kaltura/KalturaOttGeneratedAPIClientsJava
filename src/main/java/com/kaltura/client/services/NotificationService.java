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

import com.kaltura.client.enums.NotificationType;
import com.kaltura.client.types.PushMessage;
import com.kaltura.client.types.RegistryResponse;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class NotificationService {
	
	public static class RegisterNotificationBuilder extends RequestBuilder<RegistryResponse, RegistryResponse.Tokenizer, RegisterNotificationBuilder> {
		
		public RegisterNotificationBuilder(String identifier, NotificationType type) {
			super(RegistryResponse.class, "notification", "register");
			params.add("identifier", identifier);
			params.add("type", type);
		}
		
		public void identifier(String multirequestToken) {
			params.add("identifier", multirequestToken);
		}
		
		public void type(String multirequestToken) {
			params.add("type", multirequestToken);
		}
	}

	/**
	 * TBD
	 * 
	 * @param identifier In case type is 'announcement', identifier should be the announcement ID. In
	 * case type is 'system', identifier should be 'login' (the login topic)
	 * @param type 'announcement' - TV-Series topic, 'system' - login topic
	 */
    public static RegisterNotificationBuilder register(String identifier, NotificationType type)  {
		return new RegisterNotificationBuilder(identifier, type);
	}
	
	public static class SendPushNotificationBuilder extends RequestBuilder<Boolean, String, SendPushNotificationBuilder> {
		
		public SendPushNotificationBuilder(int userId, PushMessage pushMessage) {
			super(Boolean.class, "notification", "sendPush");
			params.add("userId", userId);
			params.add("pushMessage", pushMessage);
		}
		
		public void userId(String multirequestToken) {
			params.add("userId", multirequestToken);
		}
	}

	/**
	 * Sends push notification to user devices
	 * 
	 * @param userId User identifications
	 * @param pushMessage Message push data
	 */
    public static SendPushNotificationBuilder sendPush(int userId, PushMessage pushMessage)  {
		return new SendPushNotificationBuilder(userId, pushMessage);
	}
	
	public static class SendSmsNotificationBuilder extends RequestBuilder<Boolean, String, SendSmsNotificationBuilder> {
		
		public SendSmsNotificationBuilder(String message) {
			super(Boolean.class, "notification", "sendSms");
			params.add("message", message);
		}
		
		public void message(String multirequestToken) {
			params.add("message", multirequestToken);
		}
	}

	/**
	 * Sends SMS notification to user
	 * 
	 * @param message Message to send
	 */
    public static SendSmsNotificationBuilder sendSms(String message)  {
		return new SendSmsNotificationBuilder(message);
	}
	
	public static class SetDevicePushTokenNotificationBuilder extends RequestBuilder<Boolean, String, SetDevicePushTokenNotificationBuilder> {
		
		public SetDevicePushTokenNotificationBuilder(String pushToken) {
			super(Boolean.class, "notification", "setDevicePushToken");
			params.add("pushToken", pushToken);
		}
		
		public void pushToken(String multirequestToken) {
			params.add("pushToken", multirequestToken);
		}
	}

	/**
	 * Registers the device push token to the push service
	 * 
	 * @param pushToken The device-application pair authentication for push delivery
	 */
    public static SetDevicePushTokenNotificationBuilder setDevicePushToken(String pushToken)  {
		return new SetDevicePushTokenNotificationBuilder(pushToken);
	}
}
