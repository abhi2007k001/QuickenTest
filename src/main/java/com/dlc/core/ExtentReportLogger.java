package com.dlc.core;

import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ExtentReportLogger extends BaseUI {

	public static void description(String desc) {

		logger.log(Status.INFO, MarkupHelper.createLabel(" Test Desc : " + desc, ExtentColor.CYAN));
	}

	public static void error(String desc) {

		logger.log(Status.INFO, MarkupHelper.createLabel(" Error  : " + desc, ExtentColor.ORANGE));
	}

	public static void query(String query) {

		logger.log(Status.INFO, MarkupHelper.createLabel(" QUERY : " + query, ExtentColor.BLACK));
	}

	public static void getHeaders(Map<String, String> headers) {

		logger.log(Status.INFO, MarkupHelper.createLabel(" Headers : " + headers, ExtentColor.ORANGE));
	}

	public static void output(String output) {

		logger.log(Status.INFO, MarkupHelper.createLabel(" OUTPUT : " + output, ExtentColor.RED));
	}

	public static void requestBody(Response resp) {

		Markup mh = MarkupHelper.createCodeBlock(resp.body().asString());

		logger.log(Status.INFO, MarkupHelper.createLabel("Response body:", ExtentColor.YELLOW));
		logger.log(Status.INFO, mh);

		// System.out.println("Response Body :" + resp.body().asString());
	}

	public static void responseBody(Response resp) {

		Markup mh = MarkupHelper.createCodeBlock(resp.body().asString());

		logger.log(Status.INFO, MarkupHelper.createLabel("Response body:", ExtentColor.BLACK));
		logger.log(Status.INFO, mh);

		// System.out.println("Response Body :" + resp.body().asString());
	}

	public static void statusCode(Response resp) {

		logger.log(Status.INFO, MarkupHelper.createLabel(" Status code : " + resp.getStatusCode(), ExtentColor.INDIGO));
		// System.out.println("Status Code :" + resp.getStatusCode());

	}

	public static void requesrURL(String URI) {

		String temp = "";

		if (RestAssured.baseURI.contains("localhost")) {
			RestAssured.baseURI = temp;
		}

		// System.out.println(RestAssured.baseURI);
		logger.log(Status.INFO,
				MarkupHelper.createLabel("Endpoint URL  :  " + RestAssured.baseURI + URI, ExtentColor.PURPLE));
	}

	public static void resultToReport(String URI, Response resp) {

		requesrURL(URI);
		responseBody(resp);
		statusCode(resp);

	}

}