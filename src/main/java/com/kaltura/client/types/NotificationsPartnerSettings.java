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
package com.kaltura.client.types;

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(NotificationsPartnerSettings.Tokenizer.class)
public class NotificationsPartnerSettings extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String pushNotificationEnabled();
		String pushSystemAnnouncementsEnabled();
		String pushStartHour();
		String pushEndHour();
		String inboxEnabled();
		String messageTTLDays();
		String automaticIssueFollowNotification();
		String topicExpirationDurationDays();
		String reminderEnabled();
		String reminderOffsetSec();
		String pushAdapterUrl();
		String churnMailTemplateName();
		String churnMailSubject();
		String senderEmail();
		String mailSenderName();
		String mailNotificationAdapterId();
		String smsEnabled();
	}

	/**
	 * Push notification capability is enabled for the account
	 */
	private Boolean pushNotificationEnabled;
	/**
	 * System announcement capability is enabled for the account
	 */
	private Boolean pushSystemAnnouncementsEnabled;
	/**
	 * Window start time (UTC) for send automated push messages
	 */
	private Integer pushStartHour;
	/**
	 * Window end time (UTC) for send automated push messages
	 */
	private Integer pushEndHour;
	/**
	 * Inbox enabled
	 */
	private Boolean inboxEnabled;
	/**
	 * Message TTL in days
	 */
	private Integer messageTTLDays;
	/**
	 * Automatic issue follow notification
	 */
	private Boolean automaticIssueFollowNotification;
	/**
	 * Topic expiration duration in days
	 */
	private Integer topicExpirationDurationDays;
	/**
	 * Reminder enabled
	 */
	private Boolean reminderEnabled;
	/**
	 * Offset time (UTC) in seconds for send reminder
	 */
	private Integer reminderOffsetSec;
	/**
	 * Push adapter URL
	 */
	private String pushAdapterUrl;
	/**
	 * Churn mail template name
	 */
	private String churnMailTemplateName;
	/**
	 * Churn mail subject
	 */
	private String churnMailSubject;
	/**
	 * Sender email
	 */
	private String senderEmail;
	/**
	 * Mail sender name
	 */
	private String mailSenderName;
	/**
	 * Mail notification adapter identifier
	 */
	private Long mailNotificationAdapterId;
	/**
	 * SMS capability is enabled for the account
	 */
	private Boolean smsEnabled;

	// pushNotificationEnabled:
	public Boolean getPushNotificationEnabled(){
		return this.pushNotificationEnabled;
	}
	public void setPushNotificationEnabled(Boolean pushNotificationEnabled){
		this.pushNotificationEnabled = pushNotificationEnabled;
	}

	public void pushNotificationEnabled(String multirequestToken){
		setToken("pushNotificationEnabled", multirequestToken);
	}

	// pushSystemAnnouncementsEnabled:
	public Boolean getPushSystemAnnouncementsEnabled(){
		return this.pushSystemAnnouncementsEnabled;
	}
	public void setPushSystemAnnouncementsEnabled(Boolean pushSystemAnnouncementsEnabled){
		this.pushSystemAnnouncementsEnabled = pushSystemAnnouncementsEnabled;
	}

	public void pushSystemAnnouncementsEnabled(String multirequestToken){
		setToken("pushSystemAnnouncementsEnabled", multirequestToken);
	}

	// pushStartHour:
	public Integer getPushStartHour(){
		return this.pushStartHour;
	}
	public void setPushStartHour(Integer pushStartHour){
		this.pushStartHour = pushStartHour;
	}

	public void pushStartHour(String multirequestToken){
		setToken("pushStartHour", multirequestToken);
	}

	// pushEndHour:
	public Integer getPushEndHour(){
		return this.pushEndHour;
	}
	public void setPushEndHour(Integer pushEndHour){
		this.pushEndHour = pushEndHour;
	}

	public void pushEndHour(String multirequestToken){
		setToken("pushEndHour", multirequestToken);
	}

	// inboxEnabled:
	public Boolean getInboxEnabled(){
		return this.inboxEnabled;
	}
	public void setInboxEnabled(Boolean inboxEnabled){
		this.inboxEnabled = inboxEnabled;
	}

	public void inboxEnabled(String multirequestToken){
		setToken("inboxEnabled", multirequestToken);
	}

	// messageTTLDays:
	public Integer getMessageTTLDays(){
		return this.messageTTLDays;
	}
	public void setMessageTTLDays(Integer messageTTLDays){
		this.messageTTLDays = messageTTLDays;
	}

	public void messageTTLDays(String multirequestToken){
		setToken("messageTTLDays", multirequestToken);
	}

	// automaticIssueFollowNotification:
	public Boolean getAutomaticIssueFollowNotification(){
		return this.automaticIssueFollowNotification;
	}
	public void setAutomaticIssueFollowNotification(Boolean automaticIssueFollowNotification){
		this.automaticIssueFollowNotification = automaticIssueFollowNotification;
	}

	public void automaticIssueFollowNotification(String multirequestToken){
		setToken("automaticIssueFollowNotification", multirequestToken);
	}

	// topicExpirationDurationDays:
	public Integer getTopicExpirationDurationDays(){
		return this.topicExpirationDurationDays;
	}
	public void setTopicExpirationDurationDays(Integer topicExpirationDurationDays){
		this.topicExpirationDurationDays = topicExpirationDurationDays;
	}

	public void topicExpirationDurationDays(String multirequestToken){
		setToken("topicExpirationDurationDays", multirequestToken);
	}

	// reminderEnabled:
	public Boolean getReminderEnabled(){
		return this.reminderEnabled;
	}
	public void setReminderEnabled(Boolean reminderEnabled){
		this.reminderEnabled = reminderEnabled;
	}

	public void reminderEnabled(String multirequestToken){
		setToken("reminderEnabled", multirequestToken);
	}

	// reminderOffsetSec:
	public Integer getReminderOffsetSec(){
		return this.reminderOffsetSec;
	}
	public void setReminderOffsetSec(Integer reminderOffsetSec){
		this.reminderOffsetSec = reminderOffsetSec;
	}

	public void reminderOffsetSec(String multirequestToken){
		setToken("reminderOffsetSec", multirequestToken);
	}

	// pushAdapterUrl:
	public String getPushAdapterUrl(){
		return this.pushAdapterUrl;
	}
	public void setPushAdapterUrl(String pushAdapterUrl){
		this.pushAdapterUrl = pushAdapterUrl;
	}

	public void pushAdapterUrl(String multirequestToken){
		setToken("pushAdapterUrl", multirequestToken);
	}

	// churnMailTemplateName:
	public String getChurnMailTemplateName(){
		return this.churnMailTemplateName;
	}
	public void setChurnMailTemplateName(String churnMailTemplateName){
		this.churnMailTemplateName = churnMailTemplateName;
	}

	public void churnMailTemplateName(String multirequestToken){
		setToken("churnMailTemplateName", multirequestToken);
	}

	// churnMailSubject:
	public String getChurnMailSubject(){
		return this.churnMailSubject;
	}
	public void setChurnMailSubject(String churnMailSubject){
		this.churnMailSubject = churnMailSubject;
	}

	public void churnMailSubject(String multirequestToken){
		setToken("churnMailSubject", multirequestToken);
	}

	// senderEmail:
	public String getSenderEmail(){
		return this.senderEmail;
	}
	public void setSenderEmail(String senderEmail){
		this.senderEmail = senderEmail;
	}

	public void senderEmail(String multirequestToken){
		setToken("senderEmail", multirequestToken);
	}

	// mailSenderName:
	public String getMailSenderName(){
		return this.mailSenderName;
	}
	public void setMailSenderName(String mailSenderName){
		this.mailSenderName = mailSenderName;
	}

	public void mailSenderName(String multirequestToken){
		setToken("mailSenderName", multirequestToken);
	}

	// mailNotificationAdapterId:
	public Long getMailNotificationAdapterId(){
		return this.mailNotificationAdapterId;
	}
	public void setMailNotificationAdapterId(Long mailNotificationAdapterId){
		this.mailNotificationAdapterId = mailNotificationAdapterId;
	}

	public void mailNotificationAdapterId(String multirequestToken){
		setToken("mailNotificationAdapterId", multirequestToken);
	}

	// smsEnabled:
	public Boolean getSmsEnabled(){
		return this.smsEnabled;
	}
	public void setSmsEnabled(Boolean smsEnabled){
		this.smsEnabled = smsEnabled;
	}

	public void smsEnabled(String multirequestToken){
		setToken("smsEnabled", multirequestToken);
	}


	public NotificationsPartnerSettings() {
		super();
	}

	public NotificationsPartnerSettings(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		pushNotificationEnabled = GsonParser.parseBoolean(jsonObject.get("pushNotificationEnabled"));
		pushSystemAnnouncementsEnabled = GsonParser.parseBoolean(jsonObject.get("pushSystemAnnouncementsEnabled"));
		pushStartHour = GsonParser.parseInt(jsonObject.get("pushStartHour"));
		pushEndHour = GsonParser.parseInt(jsonObject.get("pushEndHour"));
		inboxEnabled = GsonParser.parseBoolean(jsonObject.get("inboxEnabled"));
		messageTTLDays = GsonParser.parseInt(jsonObject.get("messageTTLDays"));
		automaticIssueFollowNotification = GsonParser.parseBoolean(jsonObject.get("automaticIssueFollowNotification"));
		topicExpirationDurationDays = GsonParser.parseInt(jsonObject.get("topicExpirationDurationDays"));
		reminderEnabled = GsonParser.parseBoolean(jsonObject.get("reminderEnabled"));
		reminderOffsetSec = GsonParser.parseInt(jsonObject.get("reminderOffsetSec"));
		pushAdapterUrl = GsonParser.parseString(jsonObject.get("pushAdapterUrl"));
		churnMailTemplateName = GsonParser.parseString(jsonObject.get("churnMailTemplateName"));
		churnMailSubject = GsonParser.parseString(jsonObject.get("churnMailSubject"));
		senderEmail = GsonParser.parseString(jsonObject.get("senderEmail"));
		mailSenderName = GsonParser.parseString(jsonObject.get("mailSenderName"));
		mailNotificationAdapterId = GsonParser.parseLong(jsonObject.get("mailNotificationAdapterId"));
		smsEnabled = GsonParser.parseBoolean(jsonObject.get("smsEnabled"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaNotificationsPartnerSettings");
		kparams.add("pushNotificationEnabled", this.pushNotificationEnabled);
		kparams.add("pushSystemAnnouncementsEnabled", this.pushSystemAnnouncementsEnabled);
		kparams.add("pushStartHour", this.pushStartHour);
		kparams.add("pushEndHour", this.pushEndHour);
		kparams.add("inboxEnabled", this.inboxEnabled);
		kparams.add("messageTTLDays", this.messageTTLDays);
		kparams.add("automaticIssueFollowNotification", this.automaticIssueFollowNotification);
		kparams.add("topicExpirationDurationDays", this.topicExpirationDurationDays);
		kparams.add("reminderEnabled", this.reminderEnabled);
		kparams.add("reminderOffsetSec", this.reminderOffsetSec);
		kparams.add("pushAdapterUrl", this.pushAdapterUrl);
		kparams.add("churnMailTemplateName", this.churnMailTemplateName);
		kparams.add("churnMailSubject", this.churnMailSubject);
		kparams.add("senderEmail", this.senderEmail);
		kparams.add("mailSenderName", this.mailSenderName);
		kparams.add("mailNotificationAdapterId", this.mailNotificationAdapterId);
		kparams.add("smsEnabled", this.smsEnabled);
		return kparams;
	}

}

