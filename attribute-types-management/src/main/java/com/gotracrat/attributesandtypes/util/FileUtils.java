package com.gotracrat.attributesandtypes.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class FileUtils {
	public static final String TRACRAT_BASE_DIR = System.getProperty("user.home") + File.separator + ".tracrat";
	
	 public static String generateXlsFileDirectory() {
	        return FileUtils.TRACRAT_BASE_DIR + File.separator + File.separator + "Documents"+ File.separator
	                + FileUtils.generateRandomString();
	    }
	 
	 public static String generateRandomString() {
	        String characters = "abcdefghijklmnopqrstuvwxyz123456789";
	        int length = generateRandomNumber();
	        char[] text = new char[length];
	        for (int i = 0; i < length; i++) {
	            text[i] = characters.charAt(random.nextInt(characters.length()));
	        }
	        return new String(text);
	    }

	 public static int generateRandomNumber() {
	        Random randomGenerator = new Random();
	        return randomGenerator.nextInt(11) + 5;
	    }
	 
	 public static Random random = new Random();

	public static String saveToFileSystem(InputStream inputStream,
			String fileUploadLocation, String fileName) throws IOException {
        String fileLocation = fileUploadLocation + File.separator + fileName;
        OutputStream out = new FileOutputStream(new File(fileLocation));
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
        return fileLocation;
    }
}
