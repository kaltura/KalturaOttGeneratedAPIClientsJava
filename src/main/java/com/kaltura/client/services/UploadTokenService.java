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
package com.kaltura.client.services;

import com.kaltura.client.FileHolder;
import com.kaltura.client.Files;
import com.kaltura.client.types.UploadToken;
import com.kaltura.client.utils.request.RequestBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class UploadTokenService {
	
	public static class AddUploadTokenBuilder extends RequestBuilder<UploadToken, UploadToken.Tokenizer, AddUploadTokenBuilder> {
		
		public AddUploadTokenBuilder(UploadToken uploadToken) {
			super(UploadToken.class, "uploadtoken", "add");
			params.add("uploadToken", uploadToken);
		}
	}

	public static AddUploadTokenBuilder add()  {
		return add(null);
	}

	/**
	 * Adds new upload token to upload a file
	 * 
	 * @param uploadToken Upload token details
	 */
    public static AddUploadTokenBuilder add(UploadToken uploadToken)  {
		return new AddUploadTokenBuilder(uploadToken);
	}
	
	public static class UploadUploadTokenBuilder extends RequestBuilder<UploadToken, UploadToken.Tokenizer, UploadUploadTokenBuilder> {
		
		public UploadUploadTokenBuilder(String uploadTokenId, FileHolder fileData) {
			super(UploadToken.class, "uploadtoken", "upload");
			params.add("uploadTokenId", uploadTokenId);
			files = new Files();
			files.add("fileData", fileData);
		}
		
		public void uploadTokenId(String multirequestToken) {
			params.add("uploadTokenId", multirequestToken);
		}
	}

	public static UploadUploadTokenBuilder upload(String uploadTokenId, File fileData)  {
		return upload(uploadTokenId, new FileHolder(fileData));
	}

	public static UploadUploadTokenBuilder upload(String uploadTokenId, InputStream fileData, String fileDataMimeType, String fileDataName, long fileDataSize)  {
		return upload(uploadTokenId, new FileHolder(fileData, fileDataMimeType, fileDataName, fileDataSize));
	}

	public static UploadUploadTokenBuilder upload(String uploadTokenId, FileInputStream fileData, String fileDataMimeType, String fileDataName)  {
		return upload(uploadTokenId, new FileHolder(fileData, fileDataMimeType, fileDataName));
	}

	/**
	 * Upload a file using the upload token id
	 * 
	 * @param uploadTokenId Identifier of existing upload-token
	 * @param fileData File to upload
	 */
    public static UploadUploadTokenBuilder upload(String uploadTokenId, FileHolder fileData)  {
		return new UploadUploadTokenBuilder(uploadTokenId, fileData);
	}
}
