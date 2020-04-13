package com.dlc.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadConfig {

	private static final String DEFAULT_PROP_FILENAME = "testconfig.properties";
	private static final String TESTDATA_PROP_FILENAME = "testconfig.properties";
	public static Properties properties = new Properties();
	static String sTestEvn = null;
	static String sContentType = null;

	static {

		try {
			FileInputStream in = new FileInputStream(new File(DEFAULT_PROP_FILENAME));
			properties.load(in);
			in.close();
		} catch (IOException ex) {
			System.err.println("Failed to read: " + DEFAULT_PROP_FILENAME);
		}
	}

	public static Properties testdataConfig() {

		try {
			FileInputStream in = new FileInputStream(new File(DEFAULT_PROP_FILENAME));
			properties.load(in);
			in.close();
		} catch (IOException ex) {
			System.err.println("Failed to read: " + DEFAULT_PROP_FILENAME);
		}

		return properties;

	}

	public static String getEnv() {

		return String.valueOf(properties.getProperty("env")).trim();
	}

	public static String getRestURL() {

		String restURL = null;

		if (getEnv().equalsIgnoreCase("qa")) {
			restURL = String.valueOf(properties.getProperty("restURL")).trim();
		}
		return restURL;
	}

	public static Map<String, String> getHeaders() {

		HashMap<String, String> httpheaders = new HashMap<String, String>();
		Properties properties = new Properties();
		File file = null;

		if (getEnv().equalsIgnoreCase("qa")) {
			file = new File("qaHeaders.properties");
		}

		try {
			FileInputStream in = new FileInputStream(file);
			properties.load(in);
			in.close();
		} catch (IOException ex) {
			System.err.println("Failed to read: " + file.getName());
		}

		httpheaders.put("Content-Type", String.valueOf(properties.getProperty("Content-Type")).trim());
		httpheaders.put("facilityType", String.valueOf(properties.getProperty("facilityType")).trim());
		httpheaders.put("facilityTypeId", String.valueOf(properties.getProperty("facilityTypeId")).trim());
		httpheaders.put("txnUser", String.valueOf(properties.getProperty("txnUser")).trim());

		return httpheaders;

	}

}
