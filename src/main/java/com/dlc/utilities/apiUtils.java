package com.dlc.utilities;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.dlc.core.BaseAPI;
import com.dlc.core.ExtentReportLogger;
import com.dlc.core.ReadConfig;

import io.restassured.response.Response;

public class apiUtils extends BaseAPI {

	public static Response getRequest(String uRI, Map<String, String> headers)
			throws SQLException, ClassNotFoundException, IOException {

		Response response = given().spec(com.dlc.utilities.apiSpecs.GetReq(headers)).log().all().when().get(uRI).then()
				.log().all().extract().response();

		ExtentReportLogger.resultToReport(uRI, response);

		return response;
	}

	public static Response deleteRequest(String uRI, Map<String, String> headers)
			throws SQLException, ClassNotFoundException, IOException {

		Response response = given().spec(com.dlc.utilities.apiSpecs.GetReq(headers)).log().all().when().delete(uRI)
				.then().log().all().extract().response();

		ExtentReportLogger.requesrURL(ReadConfig.getRestURL() + uRI);
		ExtentReportLogger.getHeaders(headers);
		ExtentReportLogger.responseBody(response);

		return response;
	}

	public static Response postREquest(String uRI, Map<String, String> headers, String bodyFile,
			Map<String, String> bodyvalues) throws SQLException, ClassNotFoundException, IOException {

		Response response = given().spec(com.dlc.utilities.apiSpecs.buildReqSpecs(headers, bodyFile, bodyvalues)).log()
				.all().when().post(uRI).then().log().all().extract().response();

		ExtentReportLogger.resultToReport(uRI, response);
		return response;

	}
}
