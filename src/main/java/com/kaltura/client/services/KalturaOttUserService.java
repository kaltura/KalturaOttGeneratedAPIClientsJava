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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaServiceBase;
import com.kaltura.client.types.*;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import java.util.HashMap;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaOttUserService extends KalturaServiceBase {
    public KalturaOttUserService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Activate the account by activation token  */
    public KalturaOTTUser activate(int partnerId, String username, String activationToken) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("username", username);
        kparams.add("activationToken", activationToken);
        this.kalturaClient.queueServiceCall("ottuser", "activate", kparams, KalturaOTTUser.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOTTUser.class, resultXmlElement);
    }

	/**  Edit user details.  */
    public boolean addRole(long roleId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("roleId", roleId);
        this.kalturaClient.queueServiceCall("ottuser", "addRole", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public KalturaLoginSession anonymousLogin(int partnerId) throws KalturaApiException {
        return this.anonymousLogin(partnerId, null);
    }

	/**  Returns tokens (KS and refresh token) for anonymous access  */
    public KalturaLoginSession anonymousLogin(int partnerId, String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("ottuser", "anonymousLogin", kparams, KalturaLoginSession.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLoginSession.class, resultXmlElement);
    }

	/**  Permanently delete a user. User to delete cannot be an exclusive household
	  master, and cannot be default user.  */
    public boolean delete() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("ottuser", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Retrieving users&amp;#39; data  */
    public KalturaOTTUser get() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("ottuser", "get", kparams, KalturaOTTUser.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOTTUser.class, resultXmlElement);
    }

	/**  Returns the identifier of the user encrypted with SHA1 using configured key  */
    public KalturaStringValue getEncryptedUserId() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("ottuser", "getEncryptedUserId", kparams, KalturaStringValue.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaStringValue.class, resultXmlElement);
    }

    public KalturaOTTUserListResponse list() throws KalturaApiException {
        return this.list(null);
    }

	/**  Retrieve user by external identifier or username or if filter is null all user
	  in the master or the user itself  */
    public KalturaOTTUserListResponse list(KalturaOTTUserFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("ottuser", "list", kparams, KalturaOTTUserListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOTTUserListResponse.class, resultXmlElement);
    }

    public KalturaLoginResponse login(int partnerId) throws KalturaApiException {
        return this.login(partnerId, null);
    }

    public KalturaLoginResponse login(int partnerId, String username) throws KalturaApiException {
        return this.login(partnerId, username, null);
    }

    public KalturaLoginResponse login(int partnerId, String username, String password) throws KalturaApiException {
        return this.login(partnerId, username, password, null);
    }

    public KalturaLoginResponse login(int partnerId, String username, String password, HashMap<String, KalturaStringValue> extraParams) throws KalturaApiException {
        return this.login(partnerId, username, password, extraParams, null);
    }

	/**  login with user name and password.  */
    public KalturaLoginResponse login(int partnerId, String username, String password, HashMap<String, KalturaStringValue> extraParams, String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("username", username);
        kparams.add("password", password);
        kparams.add("extraParams", extraParams);
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("ottuser", "login", kparams, KalturaLoginResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLoginResponse.class, resultXmlElement);
    }

    public KalturaLoginResponse loginWithPin(int partnerId, String pin) throws KalturaApiException {
        return this.loginWithPin(partnerId, pin, null);
    }

    public KalturaLoginResponse loginWithPin(int partnerId, String pin, String udid) throws KalturaApiException {
        return this.loginWithPin(partnerId, pin, udid, null);
    }

	/**  User sign-in via a time-expired sign-in PIN.  */
    public KalturaLoginResponse loginWithPin(int partnerId, String pin, String udid, String secret) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("pin", pin);
        kparams.add("udid", udid);
        kparams.add("secret", secret);
        this.kalturaClient.queueServiceCall("ottuser", "loginWithPin", kparams, KalturaLoginResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLoginResponse.class, resultXmlElement);
    }

	/**  Logout the calling user.  */
    public boolean logout() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("ottuser", "logout", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public KalturaLoginSession refreshSession(String refreshToken) throws KalturaApiException {
        return this.refreshSession(refreshToken, null);
    }

	/**  Returns new Kaltura session (ks) for the user, using the supplied refresh_token
	  (only if it&amp;#39;s valid and not expired)  */
    public KalturaLoginSession refreshSession(String refreshToken, String udid) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("refreshToken", refreshToken);
        kparams.add("udid", udid);
        this.kalturaClient.queueServiceCall("ottuser", "refreshSession", kparams, KalturaLoginSession.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLoginSession.class, resultXmlElement);
    }

	/**  Sign up a new user.  */
    public KalturaOTTUser register(int partnerId, KalturaOTTUser user, String password) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("user", user);
        kparams.add("password", password);
        this.kalturaClient.queueServiceCall("ottuser", "register", kparams, KalturaOTTUser.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOTTUser.class, resultXmlElement);
    }

	/**  Resend the activation token to a user  */
    public boolean resendActivationToken(int partnerId, String username) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("username", username);
        this.kalturaClient.queueServiceCall("ottuser", "resendActivationToken", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Send an e-mail with URL to enable the user to set new password.  */
    public boolean resetPassword(int partnerId, String username) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("username", username);
        this.kalturaClient.queueServiceCall("ottuser", "resetPassword", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Renew the user&amp;#39;s password after validating the token that sent as part
	  of URL in e-mail.  */
    public KalturaOTTUser setInitialPassword(int partnerId, String token, String password) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("partnerId", partnerId);
        kparams.add("token", token);
        kparams.add("password", password);
        this.kalturaClient.queueServiceCall("ottuser", "setInitialPassword", kparams, KalturaOTTUser.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOTTUser.class, resultXmlElement);
    }

    public KalturaOTTUser update(KalturaOTTUser user) throws KalturaApiException {
        return this.update(user, null);
    }

	/**  Update user information  */
    public KalturaOTTUser update(KalturaOTTUser user, String id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("user", user);
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("ottuser", "update", kparams, KalturaOTTUser.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaOTTUser.class, resultXmlElement);
    }

	/**  Given a user name and existing password, change to a new password.  */
    public boolean updateLoginData(String username, String oldPassword, String newPassword) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("username", username);
        kparams.add("oldPassword", oldPassword);
        kparams.add("newPassword", newPassword);
        this.kalturaClient.queueServiceCall("ottuser", "updateLoginData", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Update the user&amp;#39;s existing password.  */
    public void updatePassword(int userId, String password) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("userId", userId);
        kparams.add("password", password);
        this.kalturaClient.queueServiceCall("ottuser", "updatePassword", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }
}
