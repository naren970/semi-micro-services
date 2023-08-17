package com.gotracrat.managecompany.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import com.gotracrat.managecompany.entity.UserLog;

@Slf4j
public class GotracratUtility {

    public static byte[] compress(String data) {
        if (StringUtils.isEmpty(data))
            return null;
        byte[] compressed = new byte[0];
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data.getBytes());
            gzip.close();
            compressed = bos.toByteArray();
            bos.close();
        } catch (IOException e) {
            log.error("Error while compressing company Logo" + e.getMessage());
        }

        return compressed;
    }

    public static String decompress(byte[] compressed) {
        if (compressed == null || compressed.length < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
            BufferedReader br = null;
            if (isGzipStream(compressed)) {
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
            } else {
                br = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                bis.close();
            }

        } catch (IOException e) {
            log.error("Error while Decompressing company Logo" + e.getMessage());
        }
        return sb.toString();
    }

    public static boolean isGzipStream(byte[] bytes) {
        int head = ((int) bytes[0] & 0xff) | ((bytes[1] << 8) & 0xff00);
        return (GZIPInputStream.GZIP_MAGIC == head);
    }

    public static UserLog getUserLog(String userName, Integer companyId, String userAction, String actionComment) {
        UserLog userLog = new UserLog();
        userLog.setLogDate(java.util.Calendar.getInstance().getTime());
        userLog.setUserAction(userAction);
        userLog.setActionComment(actionComment);
        userLog.setLogXml(actionComment);
        userLog.setUserName(userName);
        userLog.setCompanyID(companyId);
        return userLog;
    }
}
