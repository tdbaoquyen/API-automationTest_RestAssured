package com.api.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils 
{
	public static String CONFIG_PATH = "./configuration/configs.properties";
	public static String TOKEN_PATH = "./configuration/token.properties";
	
	// === 1. LẤY RA GIÁ TRỊ PROPERTY TỪ FILE CONFIG THEO KEY BẤT KỲ === //
	public static String getProperty(String path, String key) {//em làm thế này là dư nè
		Properties property = new Properties();
		String value = null;
		FileInputStream inputFile = null;
		// bắt ngoại lệ nếu không tìm thấy đường dẫn file
		try {
			inputFile = new FileInputStream (CONFIG_PATH);
			property.load(inputFile);
			value = property.getProperty(key);
			return value;
		}
		catch (Exception ex) {
			System.out.println("Error while getting Properties value: " + key);
			ex.printStackTrace();
		}
		finally {
			if (inputFile != null) {
				try {
					inputFile.close();
				}catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return value;
	}
	
	// === 2. SAVE TOKEN VÀO FILE PROPERTIES NAME "TOKEN" === //
	public static void saveToken(String token)	{
		Properties property = new Properties();
		FileOutputStream outputFile = null;
		// bắt ngoại lệ nếu không truy cập được file theo đường dẫn
		try {
			outputFile = new FileOutputStream (TOKEN_PATH);
			property.setProperty("token", token);
			property.store(outputFile, "Token information");
			System.out.println("Set new TOKEN value in file Properties: SUCCESSFULL");
		}
		catch (Exception ex) {
			System.out.println("Set new TOKEN value in file Properties: UNSUCCESSFULL");
			ex.printStackTrace();
		}
		finally {
			if (outputFile != null) {
				try{
					outputFile.close();
					System.out.println("Sorry!! File was closed!");
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}	
			}
		}
	}
	
	// === 3. LẤY RA TOKEN ĐÃ LƯU === //
	public static String getToken(String path, String token) 
	{
		Properties property = new Properties();
		String value = null;
		FileInputStream inputFile = null;
		
		// bắt ngoại lệ nếu không được được file theo đường dẫn
		try {
			inputFile = new FileInputStream (TOKEN_PATH);
			property.load(inputFile);
			value = property.getProperty(token);
			return value;
		}
		catch (Exception ex) {
			System.out.println("Error while getting TOKEN value");
			ex.printStackTrace();
		}
		finally {
			if (inputFile != null) {
				try {
					System.out.println("Sorry!! File was closed!");
					inputFile.close();
				}catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return value;
	}

}
