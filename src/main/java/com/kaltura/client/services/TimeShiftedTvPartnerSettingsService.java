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

import com.kaltura.client.types.TimeShiftedTvPartnerSettings;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TimeShiftedTvPartnerSettingsService {
	
	public static class GetTimeShiftedTvPartnerSettingsBuilder extends RequestBuilder<TimeShiftedTvPartnerSettings, TimeShiftedTvPartnerSettings.Tokenizer, GetTimeShiftedTvPartnerSettingsBuilder> {
		
		public GetTimeShiftedTvPartnerSettingsBuilder() {
			super(TimeShiftedTvPartnerSettings.class, "timeshiftedtvpartnersettings", "get");
		}
	}

	/**
	 * Retrieve the account’s time-shifted TV settings (catch-up and C-DVR,
	  Trick-play, Start-over)
	 */
    public static GetTimeShiftedTvPartnerSettingsBuilder get()  {
		return new GetTimeShiftedTvPartnerSettingsBuilder();
	}
	
	public static class UpdateTimeShiftedTvPartnerSettingsBuilder extends RequestBuilder<Boolean, String, UpdateTimeShiftedTvPartnerSettingsBuilder> {
		
		public UpdateTimeShiftedTvPartnerSettingsBuilder(TimeShiftedTvPartnerSettings settings) {
			super(Boolean.class, "timeshiftedtvpartnersettings", "update");
			params.add("settings", settings);
		}
	}

	/**
	 * Configure the account’s time-shifted TV settings (catch-up and C-DVR,
	  Trick-play, Start-over)
	 * 
	 * @param settings Time shifted TV settings
	 */
    public static UpdateTimeShiftedTvPartnerSettingsBuilder update(TimeShiftedTvPartnerSettings settings)  {
		return new UpdateTimeShiftedTvPartnerSettingsBuilder(settings);
	}
}
