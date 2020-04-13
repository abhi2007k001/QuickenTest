package com.dlc.core;

import io.restassured.RestAssured;

public class BaseAPI extends BaseUI {

	// public Xls_Reader xlsAPI = new Xls_Reader(
	// System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\"+
	// "DataAPI.xlsx");

	public static void testSetUp() {

		String sBasePath = null;
		// rc=new ReadHostConfig();
		sBasePath = "";

		RestAssured.baseURI = ReadConfig.getRestURL();
		RestAssured.port = 80;
		RestAssured.basePath = sBasePath;

	}

}
