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
package com.kaltura.client.types;

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * A clout upload settings refers to partner configuration with regards to files
  that are loaded to KTP cloud (e.g. S3)
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(CloudUploadSettingsConfiguration.Tokenizer.class)
public class CloudUploadSettingsConfiguration extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		String defaultAllowedFileExtensions();
		String customAllowedFileExtensions();
	}

	/**
	 * Comma seperated list of file extensions that allowed to all partners
	 */
	private String defaultAllowedFileExtensions;
	/**
	 * Comma seperated list of file extensions that allowed to partner in question     
	          {&amp;quot;323&amp;quot;, &amp;quot;text/h323&amp;quot; },             
	  {&amp;quot;3g2&amp;quot;,&amp;quot;video/3gpp2&amp;quot;},              {
	  &amp;quot;3gp&amp;quot;,&amp;quot;video/3gpp&amp;quot;},              {
	  &amp;quot;3gp2&amp;quot;, &amp;quot;video/3gpp2&amp;quot;},             
	  {&amp;quot;3gpp&amp;quot;,  &amp;quot;video/3gpp&amp;quot;},             
	  {&amp;quot;7z&amp;quot;,  &amp;quot;application/x-7z-compressed&amp;quot;},     
	          {&amp;quot;aa&amp;quot;,&amp;quot;audio/audible&amp;quot; },            
	   {&amp;quot;aac&amp;quot;,&amp;quot;audio/aac&amp;quot;},             
	  {&amp;quot;aaf&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;aax&amp;quot;,&amp;quot;audio/vnd.audible.aax&amp;quot;},        
	       {&amp;quot;ac3&amp;quot;,&amp;quot;audio/ac3&amp;quot;},             
	  {&amp;quot;aca&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;accda&amp;quot;,&amp;quot;application/msaccess.addin&amp;quot;}, 
	             
	  {&amp;quot;accdb&amp;quot;,&amp;quot;application/msaccess&amp;quot;},           
	    {&amp;quot;accdc&amp;quot;,&amp;quot;application/msaccess.cab&amp;quot;},     
	          {&amp;quot;accde&amp;quot;,&amp;quot;application/msaccess&amp;quot;},   
	           
	  {&amp;quot;accdr&amp;quot;,&amp;quot;application/msaccess.runtime&amp;quot;},   
	            {&amp;quot;accdt&amp;quot;,&amp;quot;application/msaccess&amp;quot;}, 
	             
	  {&amp;quot;accdw&amp;quot;,&amp;quot;application/msaccess.webapplication&amp;quot;},
	              
	  {&amp;quot;accft&amp;quot;,&amp;quot;application/msaccess.ftemplate&amp;quot;}, 
	             
	  {&amp;quot;acx&amp;quot;,&amp;quot;application/internet-property-stream&amp;quot;},
	               {&amp;quot;addin&amp;quot;,&amp;quot;text/xml&amp;quot;},          
	     {&amp;quot;ade&amp;quot;,&amp;quot;application/msaccess&amp;quot;},          
	    
	  {&amp;quot;adobebridge&amp;quot;,&amp;quot;application/x-bridge-url&amp;quot;}, 
	              {&amp;quot;adp&amp;quot;,&amp;quot;application/msaccess&amp;quot;}, 
	              {&amp;quot;adt&amp;quot;,&amp;quot;audio/vnd.dlna.adts&amp;quot;},  
	             {&amp;quot;adts&amp;quot;,&amp;quot;audio/aac&amp;quot;},            
	   {&amp;quot;afm&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},        
	       {&amp;quot;ai&amp;quot;,&amp;quot;application/postscript&amp;quot;},       
	        {&amp;quot;aif&amp;quot;,&amp;quot;audio/x-aiff&amp;quot;},             
	  {&amp;quot;aifc&amp;quot;,&amp;quot;audio/aiff&amp;quot;},             
	  {&amp;quot;aiff&amp;quot;,&amp;quot;audio/aiff&amp;quot;},             
	  {&amp;quot;air&amp;quot;,&amp;quot;application/vnd.adobe.air-application-installer-package+zip&amp;quot;},
	               {&amp;quot;amc&amp;quot;,&amp;quot;application/x-mpeg&amp;quot;},  
	            
	  {&amp;quot;application&amp;quot;,&amp;quot;application/x-ms-application&amp;quot;},
	               {&amp;quot;art&amp;quot;,&amp;quot;image/x-jg&amp;quot;},          
	     {&amp;quot;asa&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;asax&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;ascx&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;asd&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;asf&amp;quot;,&amp;quot;video/x-ms-asf&amp;quot;},             
	  {&amp;quot;ashx&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;asi&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;asm&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;asmx&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;aspx&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;asr&amp;quot;,&amp;quot;video/x-ms-asf&amp;quot;},             
	  {&amp;quot;asx&amp;quot;,&amp;quot;video/x-ms-asf&amp;quot;},             
	  {&amp;quot;atom&amp;quot;,&amp;quot;application/atom+xml&amp;quot;},            
	   {&amp;quot;au&amp;quot;,&amp;quot;audio/basic&amp;quot;},             
	  {&amp;quot;avi&amp;quot;,&amp;quot;video/x-msvideo&amp;quot;},             
	  {&amp;quot;axs&amp;quot;,&amp;quot;application/olescript&amp;quot;},            
	   {&amp;quot;bas&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;bcpio&amp;quot;,&amp;quot;application/x-bcpio&amp;quot;},            
	   {&amp;quot;bin&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},        
	       {&amp;quot;bmp&amp;quot;,&amp;quot;image/bmp&amp;quot;},             
	  {&amp;quot;c&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;cab&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;caf&amp;quot;,&amp;quot;audio/x-caf&amp;quot;},             
	  {&amp;quot;calx&amp;quot;,&amp;quot;application/vnd.ms-office.calx&amp;quot;},  
	            
	  {&amp;quot;cat&amp;quot;,&amp;quot;application/vnd.ms-pki.seccat&amp;quot;},    
	           {&amp;quot;cc&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;cd&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;cdda&amp;quot;,&amp;quot;audio/aiff&amp;quot;},             
	  {&amp;quot;cdf&amp;quot;,&amp;quot;application/x-cdf&amp;quot;},             
	  {&amp;quot;cer&amp;quot;,&amp;quot;application/x-x509-ca-cert&amp;quot;},       
	        {&amp;quot;chm&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},   
	           
	  {&amp;quot;class&amp;quot;,&amp;quot;application/x-java-applet&amp;quot;},      
	         {&amp;quot;clp&amp;quot;,&amp;quot;application/x-msclip&amp;quot;},      
	         {&amp;quot;cmx&amp;quot;,&amp;quot;image/x-cmx&amp;quot;},             
	  {&amp;quot;cnf&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;cod&amp;quot;,&amp;quot;image/cis-cod&amp;quot;},             
	  {&amp;quot;config&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;contact&amp;quot;,&amp;quot;text/x-ms-contact&amp;quot;},            
	   {&amp;quot;coverage&amp;quot;,&amp;quot;application/xml&amp;quot;},            
	   {&amp;quot;cpio&amp;quot;,&amp;quot;application/x-cpio&amp;quot;},             
	  {&amp;quot;cpp&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;crd&amp;quot;,&amp;quot;application/x-mscardfile&amp;quot;},         
	      {&amp;quot;crl&amp;quot;,&amp;quot;application/pkix-crl&amp;quot;},         
	      {&amp;quot;crt&amp;quot;,&amp;quot;application/x-x509-ca-cert&amp;quot;},   
	            {&amp;quot;cs&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;csdproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;csh&amp;quot;,&amp;quot;application/x-csh&amp;quot;},             
	  {&amp;quot;csproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;css&amp;quot;,&amp;quot;text/css&amp;quot;},             
	  {&amp;quot;csv&amp;quot;,&amp;quot;text/csv&amp;quot;},             
	  {&amp;quot;cur&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;cxx&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;dat&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;datasource&amp;quot;,&amp;quot;application/xml&amp;quot;},       
	        {&amp;quot;dbproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;dcr&amp;quot;,&amp;quot;application/x-director&amp;quot;},           
	    {&amp;quot;def&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;deploy&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;der&amp;quot;,&amp;quot;application/x-x509-ca-cert&amp;quot;},
	               {&amp;quot;dgml&amp;quot;,&amp;quot;application/xml&amp;quot;},    
	           {&amp;quot;dib&amp;quot;,&amp;quot;image/bmp&amp;quot;},             
	  {&amp;quot;dif&amp;quot;,&amp;quot;video/x-dv&amp;quot;},             
	  {&amp;quot;dir&amp;quot;,&amp;quot;application/x-director&amp;quot;},           
	    {&amp;quot;disco&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;dll&amp;quot;,&amp;quot;application/x-msdownload&amp;quot;},         
	      {&amp;quot;dll.config&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;dlm&amp;quot;,&amp;quot;text/dlm&amp;quot;},             
	  {&amp;quot;doc&amp;quot;,&amp;quot;application/msword&amp;quot;},             
	  {&amp;quot;docm&amp;quot;,&amp;quot;application/vnd.ms-word.document.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;docx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.wordprocessingml.document&amp;quot;},
	               {&amp;quot;dot&amp;quot;,&amp;quot;application/msword&amp;quot;},  
	            
	  {&amp;quot;dotm&amp;quot;,&amp;quot;application/vnd.ms-word.template.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;dotx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.wordprocessingml.template&amp;quot;},
	              
	  {&amp;quot;dsp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;dsw&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;dtd&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;dtsconfig&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;dv&amp;quot;,&amp;quot;video/x-dv&amp;quot;},             
	  {&amp;quot;dvi&amp;quot;,&amp;quot;application/x-dvi&amp;quot;},             
	  {&amp;quot;dwf&amp;quot;,&amp;quot;drawing/x-dwf&amp;quot;},             
	  {&amp;quot;dwp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;dxr&amp;quot;,&amp;quot;application/x-director&amp;quot;},       
	        {&amp;quot;eml&amp;quot;,&amp;quot;message/rfc822&amp;quot;},             
	  {&amp;quot;emz&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;eot&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},     
	          {&amp;quot;eps&amp;quot;,&amp;quot;application/postscript&amp;quot;},   
	            {&amp;quot;etl&amp;quot;,&amp;quot;application/etl&amp;quot;},        
	       {&amp;quot;etx&amp;quot;,&amp;quot;text/x-setext&amp;quot;},             
	  {&amp;quot;evy&amp;quot;,&amp;quot;application/envoy&amp;quot;},             
	  {&amp;quot;exe&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;exe.config&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;fdf&amp;quot;,&amp;quot;application/vnd.fdf&amp;quot;},             
	  {&amp;quot;fif&amp;quot;,&amp;quot;application/fractals&amp;quot;},             
	  {&amp;quot;filters&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;fla&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;flr&amp;quot;,&amp;quot;x-world/x-vrml&amp;quot;},             
	  {&amp;quot;flv&amp;quot;,&amp;quot;video/x-flv&amp;quot;},             
	  {&amp;quot;fsscript&amp;quot;,&amp;quot;application/fsharp-script&amp;quot;},   
	           
	  {&amp;quot;fsx&amp;quot;,&amp;quot;application/fsharp-script&amp;quot;},        
	       {&amp;quot;generictest&amp;quot;,&amp;quot;application/xml&amp;quot;},     
	          {&amp;quot;gif&amp;quot;,&amp;quot;image/gif&amp;quot;},             
	  {&amp;quot;group&amp;quot;,&amp;quot;text/x-ms-group&amp;quot;},             
	  {&amp;quot;gsm&amp;quot;,&amp;quot;audio/x-gsm&amp;quot;},             
	  {&amp;quot;gtar&amp;quot;,&amp;quot;application/x-gtar&amp;quot;},             
	  {&amp;quot;gz&amp;quot;,&amp;quot;application/x-gzip&amp;quot;},             
	  {&amp;quot;h&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;hdf&amp;quot;,&amp;quot;application/x-hdf&amp;quot;},             
	  {&amp;quot;hdml&amp;quot;,&amp;quot;text/x-hdml&amp;quot;},             
	  {&amp;quot;hhc&amp;quot;,&amp;quot;application/x-oleobject&amp;quot;},          
	     {&amp;quot;hhk&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;hhp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},  
	             {&amp;quot;hlp&amp;quot;,&amp;quot;application/winhlp&amp;quot;},    
	           {&amp;quot;hpp&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;hqx&amp;quot;,&amp;quot;application/mac-binhex40&amp;quot;},         
	      {&amp;quot;hta&amp;quot;,&amp;quot;application/hta&amp;quot;},             
	  {&amp;quot;htc&amp;quot;,&amp;quot;text/x-component&amp;quot;},             
	  {&amp;quot;htm&amp;quot;,&amp;quot;text/html&amp;quot;},             
	  {&amp;quot;html&amp;quot;,&amp;quot;text/html&amp;quot;},             
	  {&amp;quot;htt&amp;quot;,&amp;quot;text/webviewhtml&amp;quot;},             
	  {&amp;quot;hxa&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;hxc&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;hxd&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;hxe&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;hxf&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;hxh&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;hxi&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},     
	          {&amp;quot;hxk&amp;quot;,&amp;quot;application/xml&amp;quot;},          
	     {&amp;quot;hxq&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;hxr&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},  
	            
	  {&amp;quot;hxs&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;hxt&amp;quot;,&amp;quot;text/html&amp;quot;},             
	  {&amp;quot;hxv&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;hxw&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;hxx&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;i&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;ico&amp;quot;,&amp;quot;image/x-icon&amp;quot;},             
	  {&amp;quot;ics&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;idl&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;ief&amp;quot;,&amp;quot;image/ief&amp;quot;},             
	  {&amp;quot;iii&amp;quot;,&amp;quot;application/x-iphone&amp;quot;},             
	  {&amp;quot;inc&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;inf&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;inl&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;ins&amp;quot;,&amp;quot;application/x-internet-signup&amp;quot;},    
	           {&amp;quot;ipa&amp;quot;,&amp;quot;application/x-itunes-ipa&amp;quot;},
	              
	  {&amp;quot;ipg&amp;quot;,&amp;quot;application/x-itunes-ipg&amp;quot;},         
	      {&amp;quot;ipproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;ipsw&amp;quot;,&amp;quot;application/x-itunes-ipsw&amp;quot;},       
	        {&amp;quot;iqy&amp;quot;,&amp;quot;text/x-ms-iqy&amp;quot;},             
	  {&amp;quot;isp&amp;quot;,&amp;quot;application/x-internet-signup&amp;quot;},    
	           {&amp;quot;ite&amp;quot;,&amp;quot;application/x-itunes-ite&amp;quot;},
	              
	  {&amp;quot;itlp&amp;quot;,&amp;quot;application/x-itunes-itlp&amp;quot;},       
	        {&amp;quot;itms&amp;quot;,&amp;quot;application/x-itunes-itms&amp;quot;}, 
	             
	  {&amp;quot;itpc&amp;quot;,&amp;quot;application/x-itunes-itpc&amp;quot;},       
	        {&amp;quot;ivf&amp;quot;,&amp;quot;video/x-ivf&amp;quot;},             
	  {&amp;quot;jar&amp;quot;,&amp;quot;application/java-archive&amp;quot;},         
	      {&amp;quot;java&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},    
	           {&amp;quot;jck&amp;quot;,&amp;quot;application/liquidmotion&amp;quot;},
	              
	  {&amp;quot;jcz&amp;quot;,&amp;quot;application/liquidmotion&amp;quot;},         
	      {&amp;quot;jfif&amp;quot;,&amp;quot;image/pjpeg&amp;quot;},             
	  {&amp;quot;jnlp&amp;quot;,&amp;quot;application/x-java-jnlp-file&amp;quot;},    
	           {&amp;quot;jpb&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},
	               {&amp;quot;jpe&amp;quot;,&amp;quot;image/jpeg&amp;quot;},          
	     {&amp;quot;jpeg&amp;quot;,&amp;quot;image/jpeg&amp;quot;},             
	  {&amp;quot;jpg&amp;quot;,&amp;quot;image/jpeg&amp;quot;},             
	  {&amp;quot;js&amp;quot;,&amp;quot;application/x-javascript&amp;quot;},          
	     {&amp;quot;jsx&amp;quot;,&amp;quot;text/jscript&amp;quot;},             
	  {&amp;quot;jsxbin&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;latex&amp;quot;,&amp;quot;application/x-latex&amp;quot;},            
	   {&amp;quot;library-ms&amp;quot;,&amp;quot;application/windows-library+xml&amp;quot;},
	              
	  {&amp;quot;lit&amp;quot;,&amp;quot;application/x-ms-reader&amp;quot;},          
	     {&amp;quot;loadtest&amp;quot;,&amp;quot;application/xml&amp;quot;},          
	     {&amp;quot;lpk&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;lsf&amp;quot;,&amp;quot;video/x-la-asf&amp;quot;},            
	   {&amp;quot;lst&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;lsx&amp;quot;,&amp;quot;video/x-la-asf&amp;quot;},             
	  {&amp;quot;lzh&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;m13&amp;quot;,&amp;quot;application/x-msmediaview&amp;quot;},    
	          
	  {&amp;quot;m14&amp;quot;,&amp;quot;application/x-msmediaview&amp;quot;},        
	       {&amp;quot;m1v&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;m2t&amp;quot;,&amp;quot;video/vnd.dlna.mpeg-tts&amp;quot;},          
	     {&amp;quot;m2ts&amp;quot;,&amp;quot;video/vnd.dlna.mpeg-tts&amp;quot;},      
	         {&amp;quot;m2v&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;m3u&amp;quot;,&amp;quot;audio/x-mpegurl&amp;quot;},             
	  {&amp;quot;m3u8&amp;quot;,&amp;quot;audio/x-mpegurl&amp;quot;},             
	  {&amp;quot;m4a&amp;quot;,&amp;quot;audio/m4a&amp;quot;},             
	  {&amp;quot;m4b&amp;quot;,&amp;quot;audio/m4b&amp;quot;},             
	  {&amp;quot;m4p&amp;quot;,&amp;quot;audio/m4p&amp;quot;},             
	  {&amp;quot;m4r&amp;quot;,&amp;quot;audio/x-m4r&amp;quot;},             
	  {&amp;quot;m4v&amp;quot;,&amp;quot;video/x-m4v&amp;quot;},             
	  {&amp;quot;mac&amp;quot;,&amp;quot;image/x-macpaint&amp;quot;},             
	  {&amp;quot;mak&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;man&amp;quot;,&amp;quot;application/x-troff-man&amp;quot;},          
	     {&amp;quot;manifest&amp;quot;,&amp;quot;application/x-ms-manifest&amp;quot;},
	               {&amp;quot;map&amp;quot;,&amp;quot;text/plain&amp;quot;},          
	     {&amp;quot;master&amp;quot;,&amp;quot;application/xml&amp;quot;},            
	   {&amp;quot;mda&amp;quot;,&amp;quot;application/msaccess&amp;quot;},            
	   {&amp;quot;mdb&amp;quot;,&amp;quot;application/x-msaccess&amp;quot;},          
	     {&amp;quot;mde&amp;quot;,&amp;quot;application/msaccess&amp;quot;},          
	     {&amp;quot;mdp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;me&amp;quot;,&amp;quot;application/x-troff-me&amp;quot;},     
	         
	  {&amp;quot;mfp&amp;quot;,&amp;quot;application/x-shockwave-flash&amp;quot;},    
	           {&amp;quot;mht&amp;quot;,&amp;quot;message/rfc822&amp;quot;},          
	     {&amp;quot;mhtml&amp;quot;,&amp;quot;message/rfc822&amp;quot;},             
	  {&amp;quot;mid&amp;quot;,&amp;quot;audio/mid&amp;quot;},             
	  {&amp;quot;midi&amp;quot;,&amp;quot;audio/mid&amp;quot;},             
	  {&amp;quot;mix&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;mk&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;mmf&amp;quot;,&amp;quot;application/x-smaf&amp;quot;},             
	  {&amp;quot;mno&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;mny&amp;quot;,&amp;quot;application/x-msmoney&amp;quot;},            
	   {&amp;quot;mod&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mov&amp;quot;,&amp;quot;video/quicktime&amp;quot;},             
	  {&amp;quot;movie&amp;quot;,&amp;quot;video/x-sgi-movie&amp;quot;},             
	  {&amp;quot;mp2&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mp2v&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mp3&amp;quot;,&amp;quot;audio/mpeg&amp;quot;},             
	  {&amp;quot;mp4&amp;quot;,&amp;quot;video/mp4&amp;quot;},             
	  {&amp;quot;mp4v&amp;quot;,&amp;quot;video/mp4&amp;quot;},             
	  {&amp;quot;mpa&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mpe&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mpeg&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mpf&amp;quot;,&amp;quot;application/vnd.ms-mediapackage&amp;quot;},  
	             {&amp;quot;mpg&amp;quot;,&amp;quot;video/mpeg&amp;quot;},            
	   {&amp;quot;mpp&amp;quot;,&amp;quot;application/vnd.ms-project&amp;quot;},      
	         {&amp;quot;mpv2&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;mqv&amp;quot;,&amp;quot;video/quicktime&amp;quot;},             
	  {&amp;quot;ms&amp;quot;,&amp;quot;application/x-troff-ms&amp;quot;},            
	   {&amp;quot;msi&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},        
	       {&amp;quot;mso&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},    
	           {&amp;quot;mts&amp;quot;,&amp;quot;video/vnd.dlna.mpeg-tts&amp;quot;}, 
	              {&amp;quot;mtx&amp;quot;,&amp;quot;application/xml&amp;quot;},      
	         {&amp;quot;mvb&amp;quot;,&amp;quot;application/x-msmediaview&amp;quot;}, 
	             
	  {&amp;quot;mvc&amp;quot;,&amp;quot;application/x-miva-compiled&amp;quot;},      
	         {&amp;quot;mxp&amp;quot;,&amp;quot;application/x-mmxp&amp;quot;},        
	       {&amp;quot;nc&amp;quot;,&amp;quot;application/x-netcdf&amp;quot;},         
	      {&amp;quot;nsc&amp;quot;,&amp;quot;video/x-ms-asf&amp;quot;},             
	  {&amp;quot;nws&amp;quot;,&amp;quot;message/rfc822&amp;quot;},             
	  {&amp;quot;ocx&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;oda&amp;quot;,&amp;quot;application/oda&amp;quot;},             
	  {&amp;quot;odc&amp;quot;,&amp;quot;text/x-ms-odc&amp;quot;},             
	  {&amp;quot;odh&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;odl&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;odp&amp;quot;,&amp;quot;application/vnd.oasis.opendocument.presentation&amp;quot;},
	              
	  {&amp;quot;ods&amp;quot;,&amp;quot;application/oleobject&amp;quot;},            
	   {&amp;quot;odt&amp;quot;,&amp;quot;application/vnd.oasis.opendocument.text&amp;quot;},
	               {&amp;quot;one&amp;quot;,&amp;quot;application/onenote&amp;quot;}, 
	              {&amp;quot;onea&amp;quot;,&amp;quot;application/onenote&amp;quot;}, 
	             
	  {&amp;quot;onepkg&amp;quot;,&amp;quot;application/onenote&amp;quot;},           
	    {&amp;quot;onetmp&amp;quot;,&amp;quot;application/onenote&amp;quot;},         
	      {&amp;quot;onetoc&amp;quot;,&amp;quot;application/onenote&amp;quot;},       
	        {&amp;quot;onetoc2&amp;quot;,&amp;quot;application/onenote&amp;quot;},    
	           {&amp;quot;orderedtest&amp;quot;,&amp;quot;application/xml&amp;quot;}, 
	             
	  {&amp;quot;osdx&amp;quot;,&amp;quot;application/opensearchdescription+xml&amp;quot;},
	               {&amp;quot;p10&amp;quot;,&amp;quot;application/pkcs10&amp;quot;},  
	             {&amp;quot;p12&amp;quot;,&amp;quot;application/x-pkcs12&amp;quot;},  
	            
	  {&amp;quot;p7b&amp;quot;,&amp;quot;application/x-pkcs7-certificates&amp;quot;}, 
	             
	  {&amp;quot;p7c&amp;quot;,&amp;quot;application/pkcs7-mime&amp;quot;},           
	    {&amp;quot;p7m&amp;quot;,&amp;quot;application/pkcs7-mime&amp;quot;},         
	     
	  {&amp;quot;p7r&amp;quot;,&amp;quot;application/x-pkcs7-certreqresp&amp;quot;},  
	            
	  {&amp;quot;p7s&amp;quot;,&amp;quot;application/pkcs7-signature&amp;quot;},      
	         {&amp;quot;pbm&amp;quot;,&amp;quot;image/x-portable-bitmap&amp;quot;},   
	            {&amp;quot;pcast&amp;quot;,&amp;quot;application/x-podcast&amp;quot;},
	               {&amp;quot;pct&amp;quot;,&amp;quot;image/pict&amp;quot;},          
	     {&amp;quot;pcx&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;pcz&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},  
	             {&amp;quot;pdf&amp;quot;,&amp;quot;application/pdf&amp;quot;},       
	        {&amp;quot;pfb&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},   
	           
	  {&amp;quot;pfm&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;pfx&amp;quot;,&amp;quot;application/x-pkcs12&amp;quot;},         
	      {&amp;quot;pgm&amp;quot;,&amp;quot;image/x-portable-graymap&amp;quot;},     
	          {&amp;quot;pic&amp;quot;,&amp;quot;image/pict&amp;quot;},             
	  {&amp;quot;pict&amp;quot;,&amp;quot;image/pict&amp;quot;},             
	  {&amp;quot;pkgdef&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;pkgundef&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;pko&amp;quot;,&amp;quot;application/vnd.ms-pki.pko&amp;quot;},       
	        {&amp;quot;pls&amp;quot;,&amp;quot;audio/scpls&amp;quot;},             
	  {&amp;quot;pma&amp;quot;,&amp;quot;application/x-perfmon&amp;quot;},            
	   {&amp;quot;pmc&amp;quot;,&amp;quot;application/x-perfmon&amp;quot;},           
	    {&amp;quot;pml&amp;quot;,&amp;quot;application/x-perfmon&amp;quot;},          
	     {&amp;quot;pmr&amp;quot;,&amp;quot;application/x-perfmon&amp;quot;},         
	      {&amp;quot;pmw&amp;quot;,&amp;quot;application/x-perfmon&amp;quot;},        
	       {&amp;quot;png&amp;quot;,&amp;quot;image/png&amp;quot;},             
	  {&amp;quot;pnm&amp;quot;,&amp;quot;image/x-portable-anymap&amp;quot;},          
	     {&amp;quot;pnt&amp;quot;,&amp;quot;image/x-macpaint&amp;quot;},             
	  {&amp;quot;pntg&amp;quot;,&amp;quot;image/x-macpaint&amp;quot;},             
	  {&amp;quot;pnz&amp;quot;,&amp;quot;image/png&amp;quot;},             
	  {&amp;quot;pot&amp;quot;,&amp;quot;application/vnd.ms-powerpoint&amp;quot;},    
	          
	  {&amp;quot;potm&amp;quot;,&amp;quot;application/vnd.ms-powerpoint.template.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;potx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.presentationml.template&amp;quot;},
	              
	  {&amp;quot;ppa&amp;quot;,&amp;quot;application/vnd.ms-powerpoint&amp;quot;},    
	          
	  {&amp;quot;ppam&amp;quot;,&amp;quot;application/vnd.ms-powerpoint.addin.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;ppm&amp;quot;,&amp;quot;image/x-portable-pixmap&amp;quot;},          
	     {&amp;quot;pps&amp;quot;,&amp;quot;application/vnd.ms-powerpoint&amp;quot;}, 
	             
	  {&amp;quot;ppsm&amp;quot;,&amp;quot;application/vnd.ms-powerpoint.slideshow.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;ppsx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.presentationml.slideshow&amp;quot;},
	              
	  {&amp;quot;ppt&amp;quot;,&amp;quot;application/vnd.ms-powerpoint&amp;quot;},    
	          
	  {&amp;quot;pptm&amp;quot;,&amp;quot;application/vnd.ms-powerpoint.presentation.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;pptx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.presentationml.presentation&amp;quot;},
	              
	  {&amp;quot;prf&amp;quot;,&amp;quot;application/pics-rules&amp;quot;},           
	    {&amp;quot;prm&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},       
	        {&amp;quot;prx&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},   
	            {&amp;quot;ps&amp;quot;,&amp;quot;application/postscript&amp;quot;},  
	            
	  {&amp;quot;psc1&amp;quot;,&amp;quot;application/powershell&amp;quot;},          
	     {&amp;quot;psd&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;psess&amp;quot;,&amp;quot;application/xml&amp;quot;},         
	      {&amp;quot;psm&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},     
	          {&amp;quot;psp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;}, 
	             
	  {&amp;quot;pub&amp;quot;,&amp;quot;application/x-mspublisher&amp;quot;},        
	      
	  {&amp;quot;pwz&amp;quot;,&amp;quot;application/vnd.ms-powerpoint&amp;quot;},    
	           {&amp;quot;qht&amp;quot;,&amp;quot;text/x-html-insertion&amp;quot;},   
	            {&amp;quot;qhtm&amp;quot;,&amp;quot;text/x-html-insertion&amp;quot;}, 
	              {&amp;quot;qt&amp;quot;,&amp;quot;video/quicktime&amp;quot;},       
	        {&amp;quot;qti&amp;quot;,&amp;quot;image/x-quicktime&amp;quot;},          
	     {&amp;quot;qtif&amp;quot;,&amp;quot;image/x-quicktime&amp;quot;},            
	   {&amp;quot;qtl&amp;quot;,&amp;quot;application/x-quicktimeplayer&amp;quot;},   
	           
	  {&amp;quot;qxd&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;ra&amp;quot;,&amp;quot;audio/x-pn-realaudio&amp;quot;},          
	     {&amp;quot;ram&amp;quot;,&amp;quot;audio/x-pn-realaudio&amp;quot;},          
	     {&amp;quot;rar&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},      
	         {&amp;quot;ras&amp;quot;,&amp;quot;image/x-cmu-raster&amp;quot;},        
	       {&amp;quot;rat&amp;quot;,&amp;quot;application/rat-file&amp;quot;},        
	       {&amp;quot;rc&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;rc2&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;rct&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;rdlc&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;resx&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;rf&amp;quot;,&amp;quot;image/vnd.rn-realflash&amp;quot;},            
	   {&amp;quot;rgb&amp;quot;,&amp;quot;image/x-rgb&amp;quot;},             
	  {&amp;quot;rgs&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;rm&amp;quot;,&amp;quot;application/vnd.rn-realmedia&amp;quot;},      
	         {&amp;quot;rmi&amp;quot;,&amp;quot;audio/mid&amp;quot;},             
	  {&amp;quot;rmp&amp;quot;,&amp;quot;application/vnd.rn-rn_music_package&amp;quot;},
	               {&amp;quot;roff&amp;quot;,&amp;quot;application/x-troff&amp;quot;},
	              
	  {&amp;quot;rpm&amp;quot;,&amp;quot;audio/x-pn-realaudio-plugin&amp;quot;},      
	         {&amp;quot;rqy&amp;quot;,&amp;quot;text/x-ms-rqy&amp;quot;},             
	  {&amp;quot;rtf&amp;quot;,&amp;quot;application/rtf&amp;quot;},             
	  {&amp;quot;rtx&amp;quot;,&amp;quot;text/richtext&amp;quot;},             
	  {&amp;quot;ruleset&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;s&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;safariextz&amp;quot;,&amp;quot;application/x-safari-safariextz&amp;quot;},
	              
	  {&amp;quot;scd&amp;quot;,&amp;quot;application/x-msschedule&amp;quot;},         
	      {&amp;quot;sct&amp;quot;,&amp;quot;text/scriptlet&amp;quot;},             
	  {&amp;quot;sd2&amp;quot;,&amp;quot;audio/x-sd2&amp;quot;},             
	  {&amp;quot;sdp&amp;quot;,&amp;quot;application/sdp&amp;quot;},             
	  {&amp;quot;sea&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	     
	  {&amp;quot;searchconnector-ms&amp;quot;,&amp;quot;application/windows-search-connector+xml&amp;quot;},
	              
	  {&amp;quot;setpay&amp;quot;,&amp;quot;application/set-payment-initiation&amp;quot;},
	              
	  {&amp;quot;setreg&amp;quot;,&amp;quot;application/set-registration-initiation&amp;quot;},
	               {&amp;quot;settings&amp;quot;,&amp;quot;application/xml&amp;quot;},
	              
	  {&amp;quot;sgimb&amp;quot;,&amp;quot;application/x-sgimb&amp;quot;},            
	   {&amp;quot;sgml&amp;quot;,&amp;quot;text/sgml&amp;quot;},             
	  {&amp;quot;sh&amp;quot;,&amp;quot;application/x-sh&amp;quot;},             
	  {&amp;quot;shar&amp;quot;,&amp;quot;application/x-shar&amp;quot;},             
	  {&amp;quot;shtml&amp;quot;,&amp;quot;text/html&amp;quot;},             
	  {&amp;quot;sit&amp;quot;,&amp;quot;application/x-stuffit&amp;quot;},            
	   {&amp;quot;sitemap&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;skin&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;sldm&amp;quot;,&amp;quot;application/vnd.ms-powerpoint.slide.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;sldx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.presentationml.slide&amp;quot;},
	              
	  {&amp;quot;slk&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},         
	      {&amp;quot;sln&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;slupkg-ms&amp;quot;,&amp;quot;application/x-ms-license&amp;quot;},   
	            {&amp;quot;smd&amp;quot;,&amp;quot;audio/x-smd&amp;quot;},            
	   {&amp;quot;smi&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},        
	       {&amp;quot;smx&amp;quot;,&amp;quot;audio/x-smd&amp;quot;},             
	  {&amp;quot;smz&amp;quot;,&amp;quot;audio/x-smd&amp;quot;},             
	  {&amp;quot;snd&amp;quot;,&amp;quot;audio/basic&amp;quot;},             
	  {&amp;quot;snippet&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;snp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;sol&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;sor&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;spc&amp;quot;,&amp;quot;application/x-pkcs7-certificates&amp;quot;}, 
	             
	  {&amp;quot;spl&amp;quot;,&amp;quot;application/futuresplash&amp;quot;},         
	      {&amp;quot;src&amp;quot;,&amp;quot;application/x-wais-source&amp;quot;},    
	           {&amp;quot;srf&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;ssisdeploymentmanifest&amp;quot;,&amp;quot;text/xml&amp;quot;},      
	         {&amp;quot;ssm&amp;quot;,&amp;quot;application/streamingmedia&amp;quot;},
	              
	  {&amp;quot;sst&amp;quot;,&amp;quot;application/vnd.ms-pki.certstore&amp;quot;}, 
	             
	  {&amp;quot;stl&amp;quot;,&amp;quot;application/vnd.ms-pki.stl&amp;quot;},       
	        {&amp;quot;sv4cpio&amp;quot;,&amp;quot;application/x-sv4cpio&amp;quot;},  
	            
	  {&amp;quot;sv4crc&amp;quot;,&amp;quot;application/x-sv4crc&amp;quot;},          
	     {&amp;quot;svc&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;swf&amp;quot;,&amp;quot;application/x-shockwave-flash&amp;quot;},    
	           {&amp;quot;t&amp;quot;,&amp;quot;application/x-troff&amp;quot;},       
	        {&amp;quot;tar&amp;quot;,&amp;quot;application/x-tar&amp;quot;},          
	     {&amp;quot;tcl&amp;quot;,&amp;quot;application/x-tcl&amp;quot;},             
	  {&amp;quot;testrunconfig&amp;quot;,&amp;quot;application/xml&amp;quot;},        
	       {&amp;quot;testsettings&amp;quot;,&amp;quot;application/xml&amp;quot;},    
	           {&amp;quot;tex&amp;quot;,&amp;quot;application/x-tex&amp;quot;},       
	        {&amp;quot;texi&amp;quot;,&amp;quot;application/x-texinfo&amp;quot;},     
	          {&amp;quot;texinfo&amp;quot;,&amp;quot;application/x-texinfo&amp;quot;},
	              
	  {&amp;quot;tgz&amp;quot;,&amp;quot;application/x-compressed&amp;quot;},         
	     
	  {&amp;quot;thmx&amp;quot;,&amp;quot;application/vnd.ms-officetheme&amp;quot;},  
	            
	  {&amp;quot;thn&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;tif&amp;quot;,&amp;quot;image/tiff&amp;quot;},             
	  {&amp;quot;tiff&amp;quot;,&amp;quot;image/tiff&amp;quot;},             
	  {&amp;quot;tlh&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;tli&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;toc&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;tr&amp;quot;,&amp;quot;application/x-troff&amp;quot;},           
	    {&amp;quot;trm&amp;quot;,&amp;quot;application/x-msterminal&amp;quot;},       
	        {&amp;quot;trx&amp;quot;,&amp;quot;application/xml&amp;quot;},            
	   {&amp;quot;ts&amp;quot;,&amp;quot;video/vnd.dlna.mpeg-tts&amp;quot;},          
	     {&amp;quot;tsv&amp;quot;,&amp;quot;text/tab-separated-values&amp;quot;},     
	          {&amp;quot;ttf&amp;quot;,&amp;quot;application/octet-stream&amp;quot;}, 
	             
	  {&amp;quot;tts&amp;quot;,&amp;quot;video/vnd.dlna.mpeg-tts&amp;quot;},          
	     {&amp;quot;txt&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;u32&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;uls&amp;quot;,&amp;quot;text/iuls&amp;quot;},             
	  {&amp;quot;user&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;ustar&amp;quot;,&amp;quot;application/x-ustar&amp;quot;},            
	   {&amp;quot;vb&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vbdproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vbk&amp;quot;,&amp;quot;video/mpeg&amp;quot;},             
	  {&amp;quot;vbproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vbs&amp;quot;,&amp;quot;text/vbscript&amp;quot;},             
	  {&amp;quot;vcf&amp;quot;,&amp;quot;text/x-vcard&amp;quot;},             
	  {&amp;quot;vcproj&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;vcs&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vcxproj&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;vddproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vdp&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vdproj&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vdx&amp;quot;,&amp;quot;application/vnd.ms-visio.viewer&amp;quot;},  
	             {&amp;quot;vml&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;vscontent&amp;quot;,&amp;quot;application/xml&amp;quot;},            
	   {&amp;quot;vsct&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;vsd&amp;quot;,&amp;quot;application/vnd.visio&amp;quot;},            
	   {&amp;quot;vsi&amp;quot;,&amp;quot;application/ms-vsi&amp;quot;},             
	  {&amp;quot;vsix&amp;quot;,&amp;quot;application/vsix&amp;quot;},             
	  {&amp;quot;vsixlangpack&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;vsixmanifest&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;vsmdi&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;vspscc&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vss&amp;quot;,&amp;quot;application/vnd.visio&amp;quot;},            
	   {&amp;quot;vsscc&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vssettings&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;vssscc&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;vst&amp;quot;,&amp;quot;application/vnd.visio&amp;quot;},            
	   {&amp;quot;vstemplate&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;vsto&amp;quot;,&amp;quot;application/x-ms-vsto&amp;quot;},           
	    {&amp;quot;vsw&amp;quot;,&amp;quot;application/vnd.visio&amp;quot;},          
	     {&amp;quot;vsx&amp;quot;,&amp;quot;application/vnd.visio&amp;quot;},         
	      {&amp;quot;vtx&amp;quot;,&amp;quot;application/vnd.visio&amp;quot;},        
	       {&amp;quot;wav&amp;quot;,&amp;quot;audio/wav&amp;quot;},             
	  {&amp;quot;wave&amp;quot;,&amp;quot;audio/wav&amp;quot;},             
	  {&amp;quot;wax&amp;quot;,&amp;quot;audio/x-ms-wax&amp;quot;},             
	  {&amp;quot;wbk&amp;quot;,&amp;quot;application/msword&amp;quot;},             
	  {&amp;quot;wbmp&amp;quot;,&amp;quot;image/vnd.wap.wbmp&amp;quot;},             
	  {&amp;quot;wcm&amp;quot;,&amp;quot;application/vnd.ms-works&amp;quot;},         
	      {&amp;quot;wdb&amp;quot;,&amp;quot;application/vnd.ms-works&amp;quot;},     
	          {&amp;quot;wdp&amp;quot;,&amp;quot;image/vnd.ms-photo&amp;quot;},       
	       
	  {&amp;quot;webarchive&amp;quot;,&amp;quot;application/x-safari-webarchive&amp;quot;},
	               {&amp;quot;webtest&amp;quot;,&amp;quot;application/xml&amp;quot;}, 
	              {&amp;quot;wiq&amp;quot;,&amp;quot;application/xml&amp;quot;},      
	         {&amp;quot;wiz&amp;quot;,&amp;quot;application/msword&amp;quot;},        
	       {&amp;quot;wks&amp;quot;,&amp;quot;application/vnd.ms-works&amp;quot;},    
	          
	  {&amp;quot;wlmp&amp;quot;,&amp;quot;application/wlmoviemaker&amp;quot;},        
	      
	  {&amp;quot;wlpginstall&amp;quot;,&amp;quot;application/x-wlpg-detect&amp;quot;},
	              
	  {&amp;quot;wlpginstall3&amp;quot;,&amp;quot;application/x-wlpg3-detect&amp;quot;},
	               {&amp;quot;wm&amp;quot;,&amp;quot;video/x-ms-wm&amp;quot;},        
	       {&amp;quot;wma&amp;quot;,&amp;quot;audio/x-ms-wma&amp;quot;},             
	  {&amp;quot;wmd&amp;quot;,&amp;quot;application/x-ms-wmd&amp;quot;},             
	  {&amp;quot;wmf&amp;quot;,&amp;quot;application/x-msmetafile&amp;quot;},         
	      {&amp;quot;wml&amp;quot;,&amp;quot;text/vnd.wap.wml&amp;quot;},             
	  {&amp;quot;wmlc&amp;quot;,&amp;quot;application/vnd.wap.wmlc&amp;quot;},        
	       {&amp;quot;wmls&amp;quot;,&amp;quot;text/vnd.wap.wmlscript&amp;quot;},     
	         
	  {&amp;quot;wmlsc&amp;quot;,&amp;quot;application/vnd.wap.wmlscriptc&amp;quot;}, 
	              {&amp;quot;wmp&amp;quot;,&amp;quot;video/x-ms-wmp&amp;quot;},       
	        {&amp;quot;wmv&amp;quot;,&amp;quot;video/x-ms-wmv&amp;quot;},             
	  {&amp;quot;wmx&amp;quot;,&amp;quot;video/x-ms-wmx&amp;quot;},             
	  {&amp;quot;wmz&amp;quot;,&amp;quot;application/x-ms-wmz&amp;quot;},             
	  {&amp;quot;wpl&amp;quot;,&amp;quot;application/vnd.ms-wpl&amp;quot;},           
	    {&amp;quot;wps&amp;quot;,&amp;quot;application/vnd.ms-works&amp;quot;},       
	        {&amp;quot;wri&amp;quot;,&amp;quot;application/x-mswrite&amp;quot;},      
	         {&amp;quot;wrl&amp;quot;,&amp;quot;x-world/x-vrml&amp;quot;},            
	   {&amp;quot;wrz&amp;quot;,&amp;quot;x-world/x-vrml&amp;quot;},             
	  {&amp;quot;wsc&amp;quot;,&amp;quot;text/scriptlet&amp;quot;},             
	  {&amp;quot;wsdl&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;wvx&amp;quot;,&amp;quot;video/x-ms-wvx&amp;quot;},             
	  {&amp;quot;x&amp;quot;,&amp;quot;application/directx&amp;quot;},             
	  {&amp;quot;xaf&amp;quot;,&amp;quot;x-world/x-vrml&amp;quot;},             
	  {&amp;quot;xaml&amp;quot;,&amp;quot;application/xaml+xml&amp;quot;},            
	   {&amp;quot;xap&amp;quot;,&amp;quot;application/x-silverlight-app&amp;quot;},   
	            {&amp;quot;xbap&amp;quot;,&amp;quot;application/x-ms-xbap&amp;quot;}, 
	              {&amp;quot;xbm&amp;quot;,&amp;quot;image/x-xbitmap&amp;quot;},      
	         {&amp;quot;xdr&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;xht&amp;quot;,&amp;quot;application/xhtml+xml&amp;quot;},            
	   {&amp;quot;xhtml&amp;quot;,&amp;quot;application/xhtml+xml&amp;quot;},         
	      {&amp;quot;xla&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},     
	         
	  {&amp;quot;xlam&amp;quot;,&amp;quot;application/vnd.ms-excel.addin.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;xlc&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},         
	      {&amp;quot;xld&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},     
	          {&amp;quot;xlk&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;}, 
	             
	  {&amp;quot;xll&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},         
	      {&amp;quot;xlm&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},     
	          {&amp;quot;xls&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;}, 
	             
	  {&amp;quot;xlsb&amp;quot;,&amp;quot;application/vnd.ms-excel.sheet.binary.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;xlsm&amp;quot;,&amp;quot;application/vnd.ms-excel.sheet.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;xlsx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.spreadsheetml.sheet&amp;quot;},
	              
	  {&amp;quot;xlt&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},         
	     
	  {&amp;quot;xltm&amp;quot;,&amp;quot;application/vnd.ms-excel.template.macroenabled.12&amp;quot;},
	              
	  {&amp;quot;xltx&amp;quot;,&amp;quot;application/vnd.openxmlformats-officedocument.spreadsheetml.template&amp;quot;},
	              
	  {&amp;quot;xlw&amp;quot;,&amp;quot;application/vnd.ms-excel&amp;quot;},         
	      {&amp;quot;xml&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;xmta&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;xof&amp;quot;,&amp;quot;x-world/x-vrml&amp;quot;},             
	  {&amp;quot;xoml&amp;quot;,&amp;quot;text/plain&amp;quot;},             
	  {&amp;quot;xpm&amp;quot;,&amp;quot;image/x-xpixmap&amp;quot;},             
	  {&amp;quot;xps&amp;quot;,&amp;quot;application/vnd.ms-xpsdocument&amp;quot;},   
	            {&amp;quot;xrm-ms&amp;quot;,&amp;quot;text/xml&amp;quot;},            
	   {&amp;quot;xsc&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;xsd&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;xsf&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;xsl&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;xslt&amp;quot;,&amp;quot;text/xml&amp;quot;},             
	  {&amp;quot;xsn&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;xss&amp;quot;,&amp;quot;application/xml&amp;quot;},             
	  {&amp;quot;xtp&amp;quot;,&amp;quot;application/octet-stream&amp;quot;},         
	      {&amp;quot;xwd&amp;quot;,&amp;quot;image/x-xwindowdump&amp;quot;},          
	     {&amp;quot;z&amp;quot;,&amp;quot;application/x-compress&amp;quot;},          
	     {&amp;quot;zip&amp;quot;,&amp;quot;application/x-zip-compressed&amp;quot;}}
	 */
	private String customAllowedFileExtensions;

	// defaultAllowedFileExtensions:
	public String getDefaultAllowedFileExtensions(){
		return this.defaultAllowedFileExtensions;
	}
	// customAllowedFileExtensions:
	public String getCustomAllowedFileExtensions(){
		return this.customAllowedFileExtensions;
	}
	public void setCustomAllowedFileExtensions(String customAllowedFileExtensions){
		this.customAllowedFileExtensions = customAllowedFileExtensions;
	}

	public void customAllowedFileExtensions(String multirequestToken){
		setToken("customAllowedFileExtensions", multirequestToken);
	}


	public CloudUploadSettingsConfiguration() {
		super();
	}

	public CloudUploadSettingsConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		defaultAllowedFileExtensions = GsonParser.parseString(jsonObject.get("defaultAllowedFileExtensions"));
		customAllowedFileExtensions = GsonParser.parseString(jsonObject.get("customAllowedFileExtensions"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCloudUploadSettingsConfiguration");
		kparams.add("customAllowedFileExtensions", this.customAllowedFileExtensions);
		return kparams;
	}

}

