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
package com.kaltura.client.services;

import com.kaltura.client.types.LongValue;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SystemService {
	
	public static class ClearLocalServerCacheSystemBuilder extends RequestBuilder<Boolean, String, ClearLocalServerCacheSystemBuilder> {
		
		public ClearLocalServerCacheSystemBuilder(String clearCacheAction, String key) {
			super(Boolean.class, "system", "clearLocalServerCache");
			params.add("clearCacheAction", clearCacheAction);
			params.add("key", key);
		}
		
		public void clearCacheAction(String multirequestToken) {
			params.add("clearCacheAction", multirequestToken);
		}
		
		public void key(String multirequestToken) {
			params.add("key", multirequestToken);
		}
	}

	public static ClearLocalServerCacheSystemBuilder clearLocalServerCache()  {
		return clearLocalServerCache(null);
	}

	public static ClearLocalServerCacheSystemBuilder clearLocalServerCache(String clearCacheAction)  {
		return clearLocalServerCache(clearCacheAction, null);
	}

	/**
	 * Clear local server cache
	 * 
	 * @param clearCacheAction clear cache action to perform, possible values: clear_all / keys / getKey
	 * @param key key to get in case you send action getKey
	 */
    public static ClearLocalServerCacheSystemBuilder clearLocalServerCache(String clearCacheAction, String key)  {
		return new ClearLocalServerCacheSystemBuilder(clearCacheAction, key);
	}
	
	public static class GetInvalidationKeyValueSystemBuilder extends RequestBuilder<LongValue, LongValue.Tokenizer, GetInvalidationKeyValueSystemBuilder> {
		
		public GetInvalidationKeyValueSystemBuilder(String invalidationKey, String layeredCacheConfigName, int groupId) {
			super(LongValue.class, "system", "getInvalidationKeyValue");
			params.add("invalidationKey", invalidationKey);
			params.add("layeredCacheConfigName", layeredCacheConfigName);
			params.add("groupId", groupId);
		}
		
		public void invalidationKey(String multirequestToken) {
			params.add("invalidationKey", multirequestToken);
		}
		
		public void layeredCacheConfigName(String multirequestToken) {
			params.add("layeredCacheConfigName", multirequestToken);
		}
		
		public void groupId(String multirequestToken) {
			params.add("groupId", multirequestToken);
		}
	}

	public static GetInvalidationKeyValueSystemBuilder getInvalidationKeyValue(String invalidationKey)  {
		return getInvalidationKeyValue(invalidationKey, null);
	}

	public static GetInvalidationKeyValueSystemBuilder getInvalidationKeyValue(String invalidationKey, String layeredCacheConfigName)  {
		return getInvalidationKeyValue(invalidationKey, layeredCacheConfigName, 0);
	}

	/**
	 * Returns the epoch value of an invalidation key if it was found
	 * 
	 * @param invalidationKey the invalidation key to fetch it's value
	 * @param layeredCacheConfigName the layered cache config name of the invalidation key
	 * @param groupId groupId
	 */
    public static GetInvalidationKeyValueSystemBuilder getInvalidationKeyValue(String invalidationKey, String layeredCacheConfigName, int groupId)  {
		return new GetInvalidationKeyValueSystemBuilder(invalidationKey, layeredCacheConfigName, groupId);
	}
	
	public static class GetLayeredCacheGroupConfigSystemBuilder extends RequestBuilder<StringValue, StringValue.Tokenizer, GetLayeredCacheGroupConfigSystemBuilder> {
		
		public GetLayeredCacheGroupConfigSystemBuilder(int groupId) {
			super(StringValue.class, "system", "getLayeredCacheGroupConfig");
			params.add("groupId", groupId);
		}
		
		public void groupId(String multirequestToken) {
			params.add("groupId", multirequestToken);
		}
	}

	public static GetLayeredCacheGroupConfigSystemBuilder getLayeredCacheGroupConfig()  {
		return getLayeredCacheGroupConfig(0);
	}

	/**
	 * Returns the current layered cache group config of the sent groupId. You need to
	  send groupId only if you wish to get it for a specific groupId and not the one
	  the KS belongs to.
	 * 
	 * @param groupId groupId
	 */
    public static GetLayeredCacheGroupConfigSystemBuilder getLayeredCacheGroupConfig(int groupId)  {
		return new GetLayeredCacheGroupConfigSystemBuilder(groupId);
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
	
	public static class IncrementLayeredCacheGroupConfigVersionSystemBuilder extends RequestBuilder<Boolean, String, IncrementLayeredCacheGroupConfigVersionSystemBuilder> {
		
		public IncrementLayeredCacheGroupConfigVersionSystemBuilder(int groupId) {
			super(Boolean.class, "system", "incrementLayeredCacheGroupConfigVersion");
			params.add("groupId", groupId);
		}
		
		public void groupId(String multirequestToken) {
			params.add("groupId", multirequestToken);
		}
	}

	public static IncrementLayeredCacheGroupConfigVersionSystemBuilder incrementLayeredCacheGroupConfigVersion()  {
		return incrementLayeredCacheGroupConfigVersion(0);
	}

	/**
	 * Returns true if version has been incremented successfully or false otherwise.
	  You need to send groupId only if you wish to increment for a specific groupId
	  and not the one the KS belongs to.
	 * 
	 * @param groupId groupId
	 */
    public static IncrementLayeredCacheGroupConfigVersionSystemBuilder incrementLayeredCacheGroupConfigVersion(int groupId)  {
		return new IncrementLayeredCacheGroupConfigVersionSystemBuilder(groupId);
	}
	
	public static class InvalidateLayeredCacheInvalidationKeySystemBuilder extends RequestBuilder<Boolean, String, InvalidateLayeredCacheInvalidationKeySystemBuilder> {
		
		public InvalidateLayeredCacheInvalidationKeySystemBuilder(String key) {
			super(Boolean.class, "system", "invalidateLayeredCacheInvalidationKey");
			params.add("key", key);
		}
		
		public void key(String multirequestToken) {
			params.add("key", multirequestToken);
		}
	}

	/**
	 * Returns true if the invalidation key was invalidated successfully or false
	  otherwise.
	 * 
	 * @param key the invalidation key to invalidate
	 */
    public static InvalidateLayeredCacheInvalidationKeySystemBuilder invalidateLayeredCacheInvalidationKey(String key)  {
		return new InvalidateLayeredCacheInvalidationKeySystemBuilder(key);
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
}
