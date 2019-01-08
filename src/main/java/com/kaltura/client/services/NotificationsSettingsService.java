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

import com.kaltura.client.types.NotificationsSettings;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class NotificationsSettingsService {
	
	public static class GetNotificationsSettingsBuilder extends RequestBuilder<NotificationsSettings, NotificationsSettings.Tokenizer, GetNotificationsSettingsBuilder> {
		
		public GetNotificationsSettingsBuilder() {
			super(NotificationsSettings.class, "notificationssettings", "get");
		}
	}

	/**
	 * Retrieve the user’s notification settings.
	 */
    public static GetNotificationsSettingsBuilder get()  {
		return new GetNotificationsSettingsBuilder();
	}
	
	public static class UpdateNotificationsSettingsBuilder extends RequestBuilder<Boolean, String, UpdateNotificationsSettingsBuilder> {
		
		public UpdateNotificationsSettingsBuilder(NotificationsSettings settings) {
			super(Boolean.class, "notificationssettings", "update");
			params.add("settings", settings);
		}
	}

	/**
	 * Update the user’s notification settings.
	 * 
	 * @param settings Notifications settings
	 */
    public static UpdateNotificationsSettingsBuilder update(NotificationsSettings settings)  {
		return new UpdateNotificationsSettingsBuilder(settings);
	}
	
	public static class UpdateWithTokenNotificationsSettingsBuilder extends RequestBuilder<Boolean, String, UpdateWithTokenNotificationsSettingsBuilder> {
		
		public UpdateWithTokenNotificationsSettingsBuilder(NotificationsSettings settings, String token, int partnerId) {
			super(Boolean.class, "notificationssettings", "updateWithToken");
			params.add("settings", settings);
			params.add("token", token);
			params.add("partnerId", partnerId);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
	}

	/**
	 * Update the user’s notification settings.
	 * 
	 * @param settings Notifications settings
	 * @param token User's token identifier
	 * @param partnerId Partner identifier
	 */
    public static UpdateWithTokenNotificationsSettingsBuilder updateWithToken(NotificationsSettings settings, String token, int partnerId)  {
		return new UpdateWithTokenNotificationsSettingsBuilder(settings, token, partnerId);
	}
}
