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

import com.kaltura.client.FileHolder;
import com.kaltura.client.Files;
import com.kaltura.client.types.Subtitles;
import com.kaltura.client.types.UploadSubtitles;
import com.kaltura.client.utils.request.RequestBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SubtitlesService {
	
	public static class UploadFileSubtitlesBuilder extends RequestBuilder<Subtitles, Subtitles.Tokenizer, UploadFileSubtitlesBuilder> {
		
		public UploadFileSubtitlesBuilder(UploadSubtitles subtitles, FileHolder fileData) {
			super(Subtitles.class, "subtitles", "uploadFile");
			params.add("subtitles", subtitles);
			files = new Files();
			files.add("fileData", fileData);
		}
	}

	public static UploadFileSubtitlesBuilder uploadFile(UploadSubtitles subtitles, File fileData)  {
		return uploadFile(subtitles, new FileHolder(fileData));
	}

	public static UploadFileSubtitlesBuilder uploadFile(UploadSubtitles subtitles, InputStream fileData, String fileDataMimeType, String fileDataName, long fileDataSize)  {
		return uploadFile(subtitles, new FileHolder(fileData, fileDataMimeType, fileDataName, fileDataSize));
	}

	public static UploadFileSubtitlesBuilder uploadFile(UploadSubtitles subtitles, FileInputStream fileData, String fileDataMimeType, String fileDataName)  {
		return uploadFile(subtitles, new FileHolder(fileData, fileDataMimeType, fileDataName));
	}

	/**
	 * Upload a subtitles file for a later analysis.
	 * 
	 * @param subtitles Subtitle metadata
	 * @param fileData The subtitles text file to upload. Must be in UTF-8 encoding.
	 */
    public static UploadFileSubtitlesBuilder uploadFile(UploadSubtitles subtitles, FileHolder fileData)  {
		return new UploadFileSubtitlesBuilder(subtitles, fileData);
	}
}
