package com.dlc.dlcAutomation;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dlc.core.BaseAPI;
import com.dlc.core.ReadConfig;
import com.dlc.utilities.apiUtils;

import io.restassured.response.Response;

public class sampleTestAPI extends BaseAPI {

	String enrollmentURI = "/enrollment/individual";

	@BeforeClass()
	public void initt() throws SQLException {

		super.testSetUp();

	}

	@Test(description = "", enabled = true, priority = 0)
	public void enrollAuser() throws Exception {
		logger.log(Status.INFO, "Test to enroll a user");

		HashMap<String, String> bodyvalues = new HashMap<String, String>();

		Response resp = apiUtils.postREquest(enrollmentURI, ReadConfig.getHeaders(), "enrollment.json", bodyvalues);

		logger.log(Status.INFO, "Response Code :" + resp.statusCode());
		String individualID = resp.jsonPath().getString("individualId");

		logger.log(Status.INFO, "Test to get the individual info");
		apiUtils.getRequest(enrollmentURI + "/" + individualID, ReadConfig.getHeaders());

	}

}
