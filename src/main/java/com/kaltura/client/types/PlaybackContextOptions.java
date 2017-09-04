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

import com.kaltura.client.Params;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.enums.PlaybackContextType;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class PlaybackContextOptions extends ObjectBase {

	/**  Protocol of the specific media object (http / https).  */
    private String mediaProtocol;
	/**  Playback streamer type: applehttp, mpegdash, url.  */
    private String streamerType;
	/**  List of comma separated media file IDs  */
    private String assetFileIds;
	/**  Playback context type  */
    private PlaybackContextType context;

    // mediaProtocol:
    public String getMediaProtocol(){
        return this.mediaProtocol;
    }
    public void setMediaProtocol(String mediaProtocol){
        this.mediaProtocol = mediaProtocol;
    }

    // streamerType:
    public String getStreamerType(){
        return this.streamerType;
    }
    public void setStreamerType(String streamerType){
        this.streamerType = streamerType;
    }

    // assetFileIds:
    public String getAssetFileIds(){
        return this.assetFileIds;
    }
    public void setAssetFileIds(String assetFileIds){
        this.assetFileIds = assetFileIds;
    }

    // context:
    public PlaybackContextType getContext(){
        return this.context;
    }
    public void setContext(PlaybackContextType context){
        this.context = context;
    }


    public PlaybackContextOptions() {
       super();
    }

    public PlaybackContextOptions(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        mediaProtocol = GsonParser.parseString(jsonObject.get("mediaProtocol"));
        streamerType = GsonParser.parseString(jsonObject.get("streamerType"));
        assetFileIds = GsonParser.parseString(jsonObject.get("assetFileIds"));
        context = PlaybackContextType.get(GsonParser.parseString(jsonObject.get("context")));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaPlaybackContextOptions");
        kparams.add("mediaProtocol", this.mediaProtocol);
        kparams.add("streamerType", this.streamerType);
        kparams.add("assetFileIds", this.assetFileIds);
        kparams.add("context", this.context);
        return kparams;
    }

}

