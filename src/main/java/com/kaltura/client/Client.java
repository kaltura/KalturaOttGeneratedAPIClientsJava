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
package com.kaltura.client;

import com.kaltura.client.utils.request.ConnectionConfiguration;
import com.kaltura.client.types.BaseResponseProfile;
import com.kaltura.client.types.SkipCondition;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class Client extends ClientBase {
	
	public Client(ConnectionConfiguration config) {
		super(config);
		
		this.setClientTag("java:18-12-09");
		this.setApiVersion("5.1.4.24705");
		this.clientConfiguration.put("format", 1); // JSON
	}
	
	/**
	 * @param clientTag Client tag
	 */
	public void setClientTag(String clientTag){
		this.clientConfiguration.put("clientTag", clientTag);
	}
	
	/**
	 * Client tag
	 * 
	 * @return String
	 */
	public String getClientTag(){
		if(this.clientConfiguration.containsKey("clientTag")){
			return(String) this.clientConfiguration.get("clientTag");
		}
		
		return null;
	}
	
	/**
	 * @param apiVersion API Version
	 */
	public void setApiVersion(String apiVersion){
		this.clientConfiguration.put("apiVersion", apiVersion);
	}
	
	/**
	 * API Version
	 * 
	 * @return String
	 */
	public String getApiVersion(){
		if(this.clientConfiguration.containsKey("apiVersion")){
			return(String) this.clientConfiguration.get("apiVersion");
		}
		
		return null;
	}
	
	/**
	 * @param abortOnError Abort the Multireuqset call if any error occurs in one of the requests
	 */
	public void setAbortOnError(Boolean abortOnError){
		this.clientConfiguration.put("abortOnError", abortOnError);
	}
	
	/**
	 * Abort the Multireuqset call if any error occurs in one of the requests
	 * 
	 * @return Boolean
	 */
	public Boolean getAbortOnError(){
		if(this.clientConfiguration.containsKey("abortOnError")){
			return(Boolean) this.clientConfiguration.get("abortOnError");
		}
		
		return null;
	}
	
	/**
	 * @param partnerId Impersonated partner id
	 */
	public void setPartnerId(Integer partnerId){
		this.requestConfiguration.put("partnerId", partnerId);
	}
	
	/**
	 * Impersonated partner id
	 * 
	 * @return Integer
	 */
	public Integer getPartnerId(){
		if(this.requestConfiguration.containsKey("partnerId")){
			return(Integer) this.requestConfiguration.get("partnerId");
		}
		
		return 0;
	}
	
	/**
	 * @param userId Impersonated user id
	 */
	public void setUserId(Integer userId){
		this.requestConfiguration.put("userId", userId);
	}
	
	/**
	 * Impersonated user id
	 * 
	 * @return Integer
	 */
	public Integer getUserId(){
		if(this.requestConfiguration.containsKey("userId")){
			return(Integer) this.requestConfiguration.get("userId");
		}
		
		return 0;
	}
	
	/**
	 * @param language Content language
	 */
	public void setLanguage(String language){
		this.requestConfiguration.put("language", language);
	}
	
	/**
	 * Content language
	 * 
	 * @return String
	 */
	public String getLanguage(){
		if(this.requestConfiguration.containsKey("language")){
			return(String) this.requestConfiguration.get("language");
		}
		
		return null;
	}
	
	/**
	 * @param currency Content currency
	 */
	public void setCurrency(String currency){
		this.requestConfiguration.put("currency", currency);
	}
	
	/**
	 * Content currency
	 * 
	 * @return String
	 */
	public String getCurrency(){
		if(this.requestConfiguration.containsKey("currency")){
			return(String) this.requestConfiguration.get("currency");
		}
		
		return null;
	}
	
	/**
	 * @param ks Kaltura API session
	 */
	public void setKs(String ks){
		this.requestConfiguration.put("ks", ks);
	}
	
	/**
	 * Kaltura API session
	 * 
	 * @return String
	 */
	public String getKs(){
		if(this.requestConfiguration.containsKey("ks")){
			return(String) this.requestConfiguration.get("ks");
		}
		
		return null;
	}
	
	/**
	 * @param sessionId Kaltura API session
	 */
	public void setSessionId(String sessionId){
		this.requestConfiguration.put("ks", sessionId);
	}
	
	/**
	 * Kaltura API session
	 * 
	 * @return String
	 */
	public String getSessionId(){
		if(this.requestConfiguration.containsKey("ks")){
			return(String) this.requestConfiguration.get("ks");
		}
		
		return null;
	}
	
	/**
	 * @param responseProfile Response profile - this attribute will be automatically unset after every API call
	 */
	public void setResponseProfile(BaseResponseProfile responseProfile){
		this.requestConfiguration.put("responseProfile", responseProfile);
	}
	
	/**
	 * Response profile - this attribute will be automatically unset after every API call
	 * 
	 * @return BaseResponseProfile
	 */
	public BaseResponseProfile getResponseProfile(){
		if(this.requestConfiguration.containsKey("responseProfile")){
			return(BaseResponseProfile) this.requestConfiguration.get("responseProfile");
		}
		
		return null;
	}
	
	/**
	 * @param abortAllOnError Abort all following requests if current request has an error
	 */
	public void setAbortAllOnError(Boolean abortAllOnError){
		this.requestConfiguration.put("abortAllOnError", abortAllOnError);
	}
	
	/**
	 * Abort all following requests if current request has an error
	 * 
	 * @return Boolean
	 */
	public Boolean getAbortAllOnError(){
		if(this.requestConfiguration.containsKey("abortAllOnError")){
			return(Boolean) this.requestConfiguration.get("abortAllOnError");
		}
		
		return null;
	}
	
	/**
	 * @param skipCondition Skip current request according to skip condition
	 */
	public void setSkipCondition(SkipCondition skipCondition){
		this.requestConfiguration.put("skipCondition", skipCondition);
	}
	
	/**
	 * Skip current request according to skip condition
	 * 
	 * @return SkipCondition
	 */
	public SkipCondition getSkipCondition(){
		if(this.requestConfiguration.containsKey("skipCondition")){
			return(SkipCondition) this.requestConfiguration.get("skipCondition");
		}
		
		return null;
	}
	
}
