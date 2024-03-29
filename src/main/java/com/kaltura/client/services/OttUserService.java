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

import com.kaltura.client.types.DynamicData;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.OTTUserDynamicData;
import com.kaltura.client.types.OTTUserFilter;
import com.kaltura.client.types.RetryDeleteRequest;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class OttUserService {
	
	public static class ActivateOttUserBuilder extends RequestBuilder<OTTUser, OTTUser.Tokenizer, ActivateOttUserBuilder> {
		
		public ActivateOttUserBuilder(int partnerId, String username, String activationToken) {
			super(OTTUser.class, "ottuser", "activate");
			params.add("partnerId", partnerId);
			params.add("username", username);
			params.add("activationToken", activationToken);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void username(String multirequestToken) {
			params.add("username", multirequestToken);
		}
		
		public void activationToken(String multirequestToken) {
			params.add("activationToken", multirequestToken);
		}
	}

	/**
	 * Activate the account by activation token
	 * 
	 * @param partnerId The partner ID
	 * @param username Username of the user to activate
	 * @param activationToken Activation token of the user
	 */
    public static ActivateOttUserBuilder activate(int partnerId, String username, String activationToken)  {
		return new ActivateOttUserBuilder(partnerId, username, activationToken);
	}
	
	public static class AddRoleOttUserBuilder extends RequestBuilder<Boolean, String, AddRoleOttUserBuilder> {
		
		public AddRoleOttUserBuilder(long roleId) {
			super(Boolean.class, "ottuser", "addRole");
			params.add("roleId", roleId);
		}
		
		public void roleId(String multirequestToken) {
			params.add("roleId", multirequestToken);
		}
	}

	/**
	 * Deprecate - use Register or Update actions instead by setting user.roleIds
	  parameter
	 * 
	 * @param roleId The role identifier to add
	 */
    public static AddRoleOttUserBuilder addRole(long roleId)  {
		return new AddRoleOttUserBuilder(roleId);
	}
	
	public static class AnonymousLoginOttUserBuilder extends RequestBuilder<LoginSession, LoginSession.Tokenizer, AnonymousLoginOttUserBuilder> {
		
		public AnonymousLoginOttUserBuilder(int partnerId, String udid) {
			super(LoginSession.class, "ottuser", "anonymousLogin");
			params.add("partnerId", partnerId);
			params.add("udid", udid);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static AnonymousLoginOttUserBuilder anonymousLogin(int partnerId)  {
		return anonymousLogin(partnerId, null);
	}

	/**
	 * Returns tokens (KS and refresh token) for anonymous access
	 * 
	 * @param partnerId The partner ID
	 * @param udid The caller device's UDID
	 */
    public static AnonymousLoginOttUserBuilder anonymousLogin(int partnerId, String udid)  {
		return new AnonymousLoginOttUserBuilder(partnerId, udid);
	}
	
	public static class DeleteOttUserBuilder extends RequestBuilder<Boolean, String, DeleteOttUserBuilder> {
		
		public DeleteOttUserBuilder() {
			super(Boolean.class, "ottuser", "delete");
		}
	}

	/**
	 * Permanently delete a user. User to delete cannot be an exclusive household
	  master, and cannot be default user.
	 */
    public static DeleteOttUserBuilder delete()  {
		return new DeleteOttUserBuilder();
	}
	
	public static class DeleteDynamicDataOttUserBuilder extends RequestBuilder<Boolean, String, DeleteDynamicDataOttUserBuilder> {
		
		public DeleteDynamicDataOttUserBuilder(String key) {
			super(Boolean.class, "ottuser", "deleteDynamicData");
			params.add("key", key);
		}
		
		public void key(String multirequestToken) {
			params.add("key", multirequestToken);
		}
	}

	/**
	 * Deletes dynamic data item for a user.
	 * 
	 * @param key Key of dynamic data item.
	 */
    public static DeleteDynamicDataOttUserBuilder deleteDynamicData(String key)  {
		return new DeleteDynamicDataOttUserBuilder(key);
	}
	
	public static class GetOttUserBuilder extends RequestBuilder<OTTUser, OTTUser.Tokenizer, GetOttUserBuilder> {
		
		public GetOttUserBuilder() {
			super(OTTUser.class, "ottuser", "get");
		}
	}

	/**
	 * Retrieving users&amp;#39; data
	 */
    public static GetOttUserBuilder get()  {
		return new GetOttUserBuilder();
	}
	
	public static class GetEncryptedUserIdOttUserBuilder extends RequestBuilder<StringValue, StringValue.Tokenizer, GetEncryptedUserIdOttUserBuilder> {
		
		public GetEncryptedUserIdOttUserBuilder() {
			super(StringValue.class, "ottuser", "getEncryptedUserId");
		}
	}

	/**
	 * Returns the identifier of the user encrypted with SHA1 using configured key
	 */
    public static GetEncryptedUserIdOttUserBuilder getEncryptedUserId()  {
		return new GetEncryptedUserIdOttUserBuilder();
	}
	
	public static class ListOttUserBuilder extends ListResponseRequestBuilder<OTTUser, OTTUser.Tokenizer, ListOttUserBuilder> {
		
		public ListOttUserBuilder(OTTUserFilter filter) {
			super(OTTUser.class, "ottuser", "list");
			params.add("filter", filter);
		}
	}

	public static ListOttUserBuilder list()  {
		return list(null);
	}

	/**
	 * Returns list of OTTUser (limited to 500 items). Filters by username/external
	  identifier/idIn or roleIdIn
	 * 
	 * @param filter Filter request
	 */
    public static ListOttUserBuilder list(OTTUserFilter filter)  {
		return new ListOttUserBuilder(filter);
	}
	
	public static class LoginOttUserBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginOttUserBuilder> {
		
		public LoginOttUserBuilder(int partnerId, String username, String password, Map<String, StringValue> extraParams, String udid) {
			super(LoginResponse.class, "ottuser", "login");
			params.add("partnerId", partnerId);
			params.add("username", username);
			params.add("password", password);
			params.add("extraParams", extraParams);
			params.add("udid", udid);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void username(String multirequestToken) {
			params.add("username", multirequestToken);
		}
		
		public void password(String multirequestToken) {
			params.add("password", multirequestToken);
		}
		
		public void udid(String multirequestToken) {
			params.add("udid", multirequestToken);
		}
	}

	public static LoginOttUserBuilder login(int partnerId)  {
		return login(partnerId, null);
	}

	public static LoginOttUserBuilder login(int partnerId, String username)  {
		return login(partnerId, username, null);
	}

	public static LoginOttUserBuilder login(int partnerId, String username, String password)  {
		return login(partnerId, username, password, null);
	}

	public static LoginOttUserBuilder login(int partnerId, String username, String password, Map<String, StringValue> extraParams)  {
		return login(partnerId, username, password, extraParams, null);
	}

	/**
	 * login with user name and password.
	 * 
	 * @param partnerId Partner Identifier
	 * @param username user name
	 * @param password password
	 * @param extraParams extra params
	 * @param udid Device UDID
	 */
    public static LoginOttUserBuilder login(int partnerId, String username, String password, Map<String, StringValue> extraParams, String udid)  {
		return new LoginOttUserBuilder(partnerId, username, password, extraParams, udid);
	}
	
	public static class LoginWithPinOttUserBuilder extends RequestBuilder<LoginResponse, LoginResponse.Tokenizer, LoginWithPinOttUserBuilder> {
		
		public LoginWithPinOttUserBuilder(int partnerId, String pin, String udid, String secret, Map<String, StringValue> extraParams) {
			super(LoginResponse.class, "ottuser", "loginWithPin");
			params.add("partnerId", partnerId);
			params.add("pin", pin);
			params.add("udid", udid);
			params.add("secret", secret);
			params.add("extraParams", extraParams);
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
		
		public void secret(String multirequestToken) {
			params.add("secret", multirequestToken);
		}
	}

	public static LoginWithPinOttUserBuilder loginWithPin(int partnerId, String pin)  {
		return loginWithPin(partnerId, pin, null);
	}

	public static LoginWithPinOttUserBuilder loginWithPin(int partnerId, String pin, String udid)  {
		return loginWithPin(partnerId, pin, udid, null);
	}

	public static LoginWithPinOttUserBuilder loginWithPin(int partnerId, String pin, String udid, String secret)  {
		return loginWithPin(partnerId, pin, udid, secret, null);
	}

	/**
	 * User sign-in via a time-expired sign-in PIN.
	 * 
	 * @param partnerId Partner Identifier
	 * @param pin pin code
	 * @param udid Device UDID
	 * @param secret Additional security parameter to validate the login
	 * @param extraParams extra params
	 */
    public static LoginWithPinOttUserBuilder loginWithPin(int partnerId, String pin, String udid, String secret, Map<String, StringValue> extraParams)  {
		return new LoginWithPinOttUserBuilder(partnerId, pin, udid, secret, extraParams);
	}
	
	public static class LogoutOttUserBuilder extends RequestBuilder<Boolean, String, LogoutOttUserBuilder> {
		
		public LogoutOttUserBuilder(Map<String, StringValue> adapterData) {
			super(Boolean.class, "ottuser", "logout");
			params.add("adapterData", adapterData);
		}
	}

	public static LogoutOttUserBuilder logout()  {
		return logout(null);
	}

	/**
	 * Logout the calling user.
	 * 
	 * @param adapterData adapter data
	 */
    public static LogoutOttUserBuilder logout(Map<String, StringValue> adapterData)  {
		return new LogoutOttUserBuilder(adapterData);
	}
	
	public static class RegisterOttUserBuilder extends RequestBuilder<OTTUser, OTTUser.Tokenizer, RegisterOttUserBuilder> {
		
		public RegisterOttUserBuilder(int partnerId, OTTUser user, String password) {
			super(OTTUser.class, "ottuser", "register");
			params.add("partnerId", partnerId);
			params.add("user", user);
			params.add("password", password);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void password(String multirequestToken) {
			params.add("password", multirequestToken);
		}
	}

	/**
	 * Sign up a new user.
	 * 
	 * @param partnerId Partner identifier
	 * @param user The user model to add
	 * @param password password
	 */
    public static RegisterOttUserBuilder register(int partnerId, OTTUser user, String password)  {
		return new RegisterOttUserBuilder(partnerId, user, password);
	}
	
	public static class ResendActivationTokenOttUserBuilder extends RequestBuilder<Boolean, String, ResendActivationTokenOttUserBuilder> {
		
		public ResendActivationTokenOttUserBuilder(int partnerId, String username) {
			super(Boolean.class, "ottuser", "resendActivationToken");
			params.add("partnerId", partnerId);
			params.add("username", username);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void username(String multirequestToken) {
			params.add("username", multirequestToken);
		}
	}

	/**
	 * Resend the activation token to a user
	 * 
	 * @param partnerId The partner ID
	 * @param username Username of the user to activate
	 */
    public static ResendActivationTokenOttUserBuilder resendActivationToken(int partnerId, String username)  {
		return new ResendActivationTokenOttUserBuilder(partnerId, username);
	}
	
	public static class ResetPasswordOttUserBuilder extends RequestBuilder<Boolean, String, ResetPasswordOttUserBuilder> {
		
		public ResetPasswordOttUserBuilder(int partnerId, String username, String templateName) {
			super(Boolean.class, "ottuser", "resetPassword");
			params.add("partnerId", partnerId);
			params.add("username", username);
			params.add("templateName", templateName);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void username(String multirequestToken) {
			params.add("username", multirequestToken);
		}
		
		public void templateName(String multirequestToken) {
			params.add("templateName", multirequestToken);
		}
	}

	public static ResetPasswordOttUserBuilder resetPassword(int partnerId, String username)  {
		return resetPassword(partnerId, username, null);
	}

	/**
	 * Send an e-mail with URL to enable the user to set new password.
	 * 
	 * @param partnerId Partner Identifier
	 * @param username user name
	 * @param templateName Template name for reset password
	 */
    public static ResetPasswordOttUserBuilder resetPassword(int partnerId, String username, String templateName)  {
		return new ResetPasswordOttUserBuilder(partnerId, username, templateName);
	}
	
	public static class RetryDeleteOttUserBuilder extends NullRequestBuilder {
		
		public RetryDeleteOttUserBuilder(RetryDeleteRequest request) {
			super("ottuser", "retryDelete");
			params.add("request", request);
		}
	}

	/**
	 * Retry delete OTT user entities by retention.
	 * 
	 * @param request Request data
	 */
    public static RetryDeleteOttUserBuilder retryDelete(RetryDeleteRequest request)  {
		return new RetryDeleteOttUserBuilder(request);
	}
	
	public static class SetInitialPasswordOttUserBuilder extends RequestBuilder<OTTUser, OTTUser.Tokenizer, SetInitialPasswordOttUserBuilder> {
		
		public SetInitialPasswordOttUserBuilder(int partnerId, String token, String password) {
			super(OTTUser.class, "ottuser", "setInitialPassword");
			params.add("partnerId", partnerId);
			params.add("token", token);
			params.add("password", password);
		}
		
		public void partnerId(String multirequestToken) {
			params.add("partnerId", multirequestToken);
		}
		
		public void token(String multirequestToken) {
			params.add("token", multirequestToken);
		}
		
		public void password(String multirequestToken) {
			params.add("password", multirequestToken);
		}
	}

	/**
	 * Renew the user&amp;#39;s password after validating the token that sent as part
	  of URL in e-mail.
	 * 
	 * @param partnerId Partner Identifier
	 * @param token Token that sent by e-mail
	 * @param password New password
	 */
    public static SetInitialPasswordOttUserBuilder setInitialPassword(int partnerId, String token, String password)  {
		return new SetInitialPasswordOttUserBuilder(partnerId, token, password);
	}
	
	public static class UpdateOttUserBuilder extends RequestBuilder<OTTUser, OTTUser.Tokenizer, UpdateOttUserBuilder> {
		
		public UpdateOttUserBuilder(OTTUser user, String id) {
			super(OTTUser.class, "ottuser", "update");
			params.add("user", user);
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	public static UpdateOttUserBuilder update(OTTUser user)  {
		return update(user, null);
	}

	/**
	 * Update user information
	 * 
	 * @param user User data (includes basic and dynamic data)
	 * @param id User ID
	 */
    public static UpdateOttUserBuilder update(OTTUser user, String id)  {
		return new UpdateOttUserBuilder(user, id);
	}
	
	public static class UpdateDynamicDataOttUserBuilder extends RequestBuilder<OTTUserDynamicData, OTTUserDynamicData.Tokenizer, UpdateDynamicDataOttUserBuilder> {
		
		public UpdateDynamicDataOttUserBuilder(String key, StringValue value) {
			super(OTTUserDynamicData.class, "ottuser", "updateDynamicData");
			params.add("key", key);
			params.add("value", value);
		}
		
		public void key(String multirequestToken) {
			params.add("key", multirequestToken);
		}
	}

	/**
	 * Update user dynamic data. If it is needed to update several items, use a
	  multi-request to avoid race conditions.              This API endpoint will
	  deprecated soon. Please use UpsertDynamicData instead of it.
	 * 
	 * @param key Type of dynamicData. Max length of key is 50 characters.
	 * @param value Value of dynamicData. Max length of value is 512 characters.
	 */
    public static UpdateDynamicDataOttUserBuilder updateDynamicData(String key, StringValue value)  {
		return new UpdateDynamicDataOttUserBuilder(key, value);
	}
	
	public static class UpdateLoginDataOttUserBuilder extends RequestBuilder<Boolean, String, UpdateLoginDataOttUserBuilder> {
		
		public UpdateLoginDataOttUserBuilder(String username, String oldPassword, String newPassword) {
			super(Boolean.class, "ottuser", "updateLoginData");
			params.add("username", username);
			params.add("oldPassword", oldPassword);
			params.add("newPassword", newPassword);
		}
		
		public void username(String multirequestToken) {
			params.add("username", multirequestToken);
		}
		
		public void oldPassword(String multirequestToken) {
			params.add("oldPassword", multirequestToken);
		}
		
		public void newPassword(String multirequestToken) {
			params.add("newPassword", multirequestToken);
		}
	}

	/**
	 * Given a user name and existing password, change to a new password.
	 * 
	 * @param username user name
	 * @param oldPassword old password
	 * @param newPassword new password
	 */
    public static UpdateLoginDataOttUserBuilder updateLoginData(String username, String oldPassword, String newPassword)  {
		return new UpdateLoginDataOttUserBuilder(username, oldPassword, newPassword);
	}
	
	public static class UpdatePasswordOttUserBuilder extends NullRequestBuilder {
		
		public UpdatePasswordOttUserBuilder(int userId, String password) {
			super("ottuser", "updatePassword");
			params.add("userId", userId);
			params.add("password", password);
		}
		
		public void userId(String multirequestToken) {
			params.add("userId", multirequestToken);
		}
		
		public void password(String multirequestToken) {
			params.add("password", multirequestToken);
		}
	}

	/**
	 * Update the user&amp;#39;s existing password.
	 * 
	 * @param userId User Identifier
	 * @param password new password
	 */
    public static UpdatePasswordOttUserBuilder updatePassword(int userId, String password)  {
		return new UpdatePasswordOttUserBuilder(userId, password);
	}
	
	public static class UpsertDynamicDataOttUserBuilder extends RequestBuilder<DynamicData, DynamicData.Tokenizer, UpsertDynamicDataOttUserBuilder> {
		
		public UpsertDynamicDataOttUserBuilder(String key, StringValue value) {
			super(DynamicData.class, "ottuser", "upsertDynamicData");
			params.add("key", key);
			params.add("value", value);
		}
		
		public void key(String multirequestToken) {
			params.add("key", multirequestToken);
		}
	}

	/**
	 * Adds or updates dynamic data item for a user. If it is needed to update several
	  items, use a multi-request to avoid race conditions.
	 * 
	 * @param key Key of dynamic data item. Max length of key is 50 characters.
	 * @param value Value of dynamic data item. Max length of value is 512 characters.
	 */
    public static UpsertDynamicDataOttUserBuilder upsertDynamicData(String key, StringValue value)  {
		return new UpsertDynamicDataOttUserBuilder(key, value);
	}
}
