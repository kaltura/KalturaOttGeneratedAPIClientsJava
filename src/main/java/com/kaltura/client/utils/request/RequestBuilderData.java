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
package com.kaltura.client.utils.request;

import com.kaltura.client.Params;
import com.kaltura.client.types.BaseResponseProfile;
import com.kaltura.client.types.SkipCondition;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public abstract class RequestBuilderData<SelfType> {
	
	protected Params params = new Params();
	
	protected RequestBuilderData() {
	}
	
	/**
	 * @param clientTag Client tag
	 */
	public SelfType setClientTag(String clientTag){
		params.add("clientTag", clientTag);
		return (SelfType) this;
	}
	
	/**
	 * @param apiVersion API Version
	 */
	public SelfType setApiVersion(String apiVersion){
		params.add("apiVersion", apiVersion);
		return (SelfType) this;
	}
	
	/**
	 * @param abortOnError Abort the Multireuqset call if any error occurs in one of the requests
	 */
	public SelfType setAbortOnError(Boolean abortOnError){
		params.add("abortOnError", abortOnError);
		return (SelfType) this;
	}
	
	/**
	 * @param partnerId Impersonated partner id
	 */
	public SelfType setPartnerId(Integer partnerId){
		params.add("partnerId", partnerId);
		return (SelfType) this;
	}
	
	/**
	 * @param userId Impersonated user id
	 */
	public SelfType setUserId(Integer userId){
		params.add("userId", userId);
		return (SelfType) this;
	}
	
	/**
	 * @param language Content language
	 */
	public SelfType setLanguage(String language){
		params.add("language", language);
		return (SelfType) this;
	}
	
	/**
	 * @param currency Content currency
	 */
	public SelfType setCurrency(String currency){
		params.add("currency", currency);
		return (SelfType) this;
	}
	
	/**
	 * @param ks Kaltura API session
	 */
	public SelfType setKs(String ks){
		params.add("ks", ks);
		return (SelfType) this;
	}
	
	/**
	 * @param sessionId Kaltura API session
	 */
	public SelfType setSessionId(String sessionId){
		params.add("ks", sessionId);
		return (SelfType) this;
	}
	
	/**
	 * @param responseProfile Response profile - this attribute will be automatically unset after every API call
	 */
	public SelfType setResponseProfile(BaseResponseProfile responseProfile){
		params.add("responseProfile", responseProfile);
		return (SelfType) this;
	}
	
	/**
	 * @param abortAllOnError Abort all following requests if current request has an error
	 */
	public SelfType setAbortAllOnError(Boolean abortAllOnError){
		params.add("abortAllOnError", abortAllOnError);
		return (SelfType) this;
	}
	
	/**
	 * @param skipCondition Skip current request according to skip condition
	 */
	public SelfType setSkipCondition(SkipCondition skipCondition){
		params.add("skipCondition", skipCondition);
		return (SelfType) this;
	}
	
}
