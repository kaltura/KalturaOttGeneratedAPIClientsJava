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
// Copyright (C) 2006-2019  Kaltura Inc.
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

import com.kaltura.client.enums.LogLevel;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SystemService {
	
	public static class GetLogLevelSystemBuilder extends RequestBuilder<String, String, GetLogLevelSystemBuilder> {
		
		public GetLogLevelSystemBuilder() {
			super(String.class, "system", "getLogLevel");
		}
	}

	/**
	 * Gets the current level of the KLogger
	 */
    public static GetLogLevelSystemBuilder getLogLevel()  {
		return new GetLogLevelSystemBuilder();
	}
	
	public static class GetTimeSystemBuilder extends RequestBuilder<Long, String, GetTimeSystemBuilder> {
		
		public GetTimeSystemBuilder() {
			super(Long.class, "system", "getTime");
		}
	}

	/**
	 * Returns current server timestamp
	 */
    public static GetTimeSystemBuilder getTime()  {
		return new GetTimeSystemBuilder();
	}
	
	public static class GetVersionSystemBuilder extends RequestBuilder<String, String, GetVersionSystemBuilder> {
		
		public GetVersionSystemBuilder() {
			super(String.class, "system", "getVersion");
		}
	}

	/**
	 * Returns current server version
	 */
    public static GetVersionSystemBuilder getVersion()  {
		return new GetVersionSystemBuilder();
	}
	
	public static class PingSystemBuilder extends RequestBuilder<Boolean, String, PingSystemBuilder> {
		
		public PingSystemBuilder() {
			super(Boolean.class, "system", "ping");
		}
	}

	/**
	 * Returns true
	 */
    public static PingSystemBuilder ping()  {
		return new PingSystemBuilder();
	}
	
	public static class SetLogLevelSystemBuilder extends RequestBuilder<Boolean, String, SetLogLevelSystemBuilder> {
		
		public SetLogLevelSystemBuilder(LogLevel level) {
			super(Boolean.class, "system", "setLogLevel");
			params.add("level", level);
		}
		
		public void level(String multirequestToken) {
			params.add("level", multirequestToken);
		}
	}

	/**
	 * Sets the current level of the KLogger
	 * 
	 * @param level Possible levels: trace, debug, info, warning, error, all
	 */
    public static SetLogLevelSystemBuilder setLogLevel(LogLevel level)  {
		return new SetLogLevelSystemBuilder(level);
	}
}
