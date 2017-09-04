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
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class PushMessage extends ObjectBase {

	/**  The message that will be presented to the user.  */
    private String message;
	/**  Optional. Can be used to change the default push sound on the user device.  */
    private String sound;
	/**  Optional. Used to change the default action of the application when a push is
	  received.  */
    private String action;
	/**  Optional. Used to direct the application to the relevant page.  */
    private String url;

    // message:
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    // sound:
    public String getSound(){
        return this.sound;
    }
    public void setSound(String sound){
        this.sound = sound;
    }

    // action:
    public String getAction(){
        return this.action;
    }
    public void setAction(String action){
        this.action = action;
    }

    // url:
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }


    public PushMessage() {
       super();
    }

    public PushMessage(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        message = GsonParser.parseString(jsonObject.get("message"));
        sound = GsonParser.parseString(jsonObject.get("sound"));
        action = GsonParser.parseString(jsonObject.get("action"));
        url = GsonParser.parseString(jsonObject.get("url"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaPushMessage");
        kparams.add("message", this.message);
        kparams.add("sound", this.sound);
        kparams.add("action", this.action);
        kparams.add("url", this.url);
        return kparams;
    }

}

