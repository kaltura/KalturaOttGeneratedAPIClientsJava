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
package com.kaltura.client.types;

import org.w3c.dom.Element;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.enums.KalturaQuotaOveragePolicy;
import com.kaltura.client.enums.KalturaProtectionPolicy;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaTimeShiftedTvPartnerSettings extends KalturaObjectBase {
	/**  Is catch-up enabled  */
    public boolean catchUpEnabled;
	/**  Is c-dvr enabled  */
    public boolean cdvrEnabled;
	/**  Is start-over enabled  */
    public boolean startOverEnabled;
	/**  Is trick-play enabled  */
    public boolean trickPlayEnabled;
	/**  Is recording schedule window enabled  */
    public boolean recordingScheduleWindowEnabled;
	/**  Is recording protection enabled  */
    public boolean protectionEnabled;
	/**  Catch-up buffer length  */
    public long catchUpBufferLength = Long.MIN_VALUE;
	/**  Trick play buffer length  */
    public long trickPlayBufferLength = Long.MIN_VALUE;
	/**  Recording schedule window. Indicates how long (in minutes) after the program
	  starts it is allowed to schedule the recording  */
    public long recordingScheduleWindow = Long.MIN_VALUE;
	/**  Indicates how long (in seconds) before the program starts the recording will
	  begin  */
    public long paddingBeforeProgramStarts = Long.MIN_VALUE;
	/**  Indicates how long (in seconds) after the program ends the recording will end  */
    public long paddingAfterProgramEnds = Long.MIN_VALUE;
	/**  Specify the time in days that a recording should be protected. Start time begins
	  at protection request.  */
    public int protectionPeriod = Integer.MIN_VALUE;
	/**  Indicates how many percent of the quota can be used for protection  */
    public int protectionQuotaPercentage = Integer.MIN_VALUE;
	/**  Specify the time in days that a recording should be kept for user. Start time
	  begins with the program end date.  */
    public int recordingLifetimePeriod = Integer.MIN_VALUE;
	/**  The time in days before the recording lifetime is due from which the client
	  should be able to warn user about deletion.  */
    public int cleanupNoticePeriod = Integer.MIN_VALUE;
	/**  Is recording of series enabled  */
    public boolean seriesRecordingEnabled;
	/**  Is recording playback for non-entitled channel enables  */
    public boolean nonEntitledChannelPlaybackEnabled;
	/**  Is recording playback for non-existing channel enables  */
    public boolean nonExistingChannelPlaybackEnabled;
	/**  Quota Policy  */
    public KalturaQuotaOveragePolicy quotaOveragePolicy;
	/**  Protection Policy  */
    public KalturaProtectionPolicy protectionPolicy;
	/**  The time in days for recovery recording that was delete by Auto Delete .  */
    public int recoveryGracePeriod = Integer.MIN_VALUE;

    public KalturaTimeShiftedTvPartnerSettings() {
    }

    public KalturaTimeShiftedTvPartnerSettings(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("catchUpEnabled")) {
                this.catchUpEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("cdvrEnabled")) {
                this.cdvrEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("startOverEnabled")) {
                this.startOverEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("trickPlayEnabled")) {
                this.trickPlayEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("recordingScheduleWindowEnabled")) {
                this.recordingScheduleWindowEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("protectionEnabled")) {
                this.protectionEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("catchUpBufferLength")) {
                this.catchUpBufferLength = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("trickPlayBufferLength")) {
                this.trickPlayBufferLength = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("recordingScheduleWindow")) {
                this.recordingScheduleWindow = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("paddingBeforeProgramStarts")) {
                this.paddingBeforeProgramStarts = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("paddingAfterProgramEnds")) {
                this.paddingAfterProgramEnds = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("protectionPeriod")) {
                this.protectionPeriod = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("protectionQuotaPercentage")) {
                this.protectionQuotaPercentage = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("recordingLifetimePeriod")) {
                this.recordingLifetimePeriod = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("cleanupNoticePeriod")) {
                this.cleanupNoticePeriod = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("seriesRecordingEnabled")) {
                this.seriesRecordingEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("nonEntitledChannelPlaybackEnabled")) {
                this.nonEntitledChannelPlaybackEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("nonExistingChannelPlaybackEnabled")) {
                this.nonExistingChannelPlaybackEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("quotaOveragePolicy")) {
                this.quotaOveragePolicy = KalturaQuotaOveragePolicy.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("protectionPolicy")) {
                this.protectionPolicy = KalturaProtectionPolicy.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("recoveryGracePeriod")) {
                this.recoveryGracePeriod = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaTimeShiftedTvPartnerSettings");
        kparams.add("catchUpEnabled", this.catchUpEnabled);
        kparams.add("cdvrEnabled", this.cdvrEnabled);
        kparams.add("startOverEnabled", this.startOverEnabled);
        kparams.add("trickPlayEnabled", this.trickPlayEnabled);
        kparams.add("recordingScheduleWindowEnabled", this.recordingScheduleWindowEnabled);
        kparams.add("protectionEnabled", this.protectionEnabled);
        kparams.add("catchUpBufferLength", this.catchUpBufferLength);
        kparams.add("trickPlayBufferLength", this.trickPlayBufferLength);
        kparams.add("recordingScheduleWindow", this.recordingScheduleWindow);
        kparams.add("paddingBeforeProgramStarts", this.paddingBeforeProgramStarts);
        kparams.add("paddingAfterProgramEnds", this.paddingAfterProgramEnds);
        kparams.add("protectionPeriod", this.protectionPeriod);
        kparams.add("protectionQuotaPercentage", this.protectionQuotaPercentage);
        kparams.add("recordingLifetimePeriod", this.recordingLifetimePeriod);
        kparams.add("cleanupNoticePeriod", this.cleanupNoticePeriod);
        kparams.add("seriesRecordingEnabled", this.seriesRecordingEnabled);
        kparams.add("nonEntitledChannelPlaybackEnabled", this.nonEntitledChannelPlaybackEnabled);
        kparams.add("nonExistingChannelPlaybackEnabled", this.nonExistingChannelPlaybackEnabled);
        kparams.add("quotaOveragePolicy", this.quotaOveragePolicy);
        kparams.add("protectionPolicy", this.protectionPolicy);
        kparams.add("recoveryGracePeriod", this.recoveryGracePeriod);
        return kparams;
    }

}

