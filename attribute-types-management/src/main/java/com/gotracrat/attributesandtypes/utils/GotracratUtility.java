package com.gotracrat.attributesandtypes.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.gotracrat.attributesandtypes.entity.Attachment;
import com.gotracrat.attributesandtypes.entity.UserLog;

import io.micrometer.core.instrument.util.StringUtils;



public class GotracratUtility {
	public static byte[] compress(String data) throws IOException {
		if (StringUtils.isEmpty(data))
            return null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data.getBytes());
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
	}

	public static String decompress(byte[] compressed) throws IOException {
		if (compressed == null || compressed.length < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();;
        try {
			ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
			BufferedReader br = null;
			if(isGzipStream(compressed)) {
				GZIPInputStream gis = new GZIPInputStream(bis);
				br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
				sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				gis.close();
				bis.close();
			}else {
				br = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
				sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				bis.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static Attachment decompressAttachment(byte[] compressed, Attachment attachment) throws IOException {
		if (compressed == null || compressed.length < 1) {
			return null;
		}
		FileOutputStream fos = new FileOutputStream(attachment.getFilename());
		fos.write(compressed);
		fos.flush();
		fos.close();
		return attachment;

	}

	public static boolean isGzipStream(byte[] bytes) {
	      int head = ((int) bytes[0] & 0xff) | ((bytes[1] << 8) & 0xff00);
	      return (GZIPInputStream.GZIP_MAGIC == head);
	}
	
	public static byte[] encodeFileContent(String file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        	 System.out.println("Exception in encoding ::::"+ ex);
        }
        byte[] encodedBytes = bos.toByteArray();
        fis.close();
        bos.close();
        return encodedBytes;
       
    }
	
	 public static UserLog getUserLog(String userName,Integer companyId,String userAction,String actionComment){
	        UserLog userLog = new UserLog();
	        userLog.setLogDate(java.util.Calendar.getInstance().getTime());
	        userLog.setUserAction(userAction);
	        userLog.setActionComment(actionComment);
	        userLog.setLogXml(actionComment);
	        userLog.setUserName(userName);
	        userLog.setCompanyID(companyId);
	        return userLog;
	    }
	 public static UserLog setUserLogData(UserLog userLog,String userName,Integer companyId,String userAction,String actionComment){
	        userLog.setLogDate(java.util.Calendar.getInstance().getTime());
	        userLog.setUserAction(userAction);
	        userLog.setActionComment(actionComment);
	        userLog.setLogXml(actionComment);
	        userLog.setUserName(userName);
	        userLog.setCompanyID(companyId);
	        return userLog;
	    }
	 
	 
	 public static String getCategoryByModuleType(String moduleType,Integer entityTypeId) {
			String category=null;
			
			if (moduleType!=null) {
				if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
					category="Company ";
				} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
					category="Location ";
				} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
					category="Item ";
				} else if (moduleType.equalsIgnoreCase(GoTracratConstants.USER_MODULE_TYPE)) {
					category="User ";
				}
			}else if(entityTypeId!=0)
			{
				if (entityTypeId.equals(1)) {
					category="Company ";
				} else if (entityTypeId.equals(3)) {
					category="Location ";
				} else if (entityTypeId.equals(2)) {
					category="Item ";
				} else if (entityTypeId.equals(9)) {
					category="User ";
				}
				
			}
			return category;
		}
		

}
