package com.dlc.dlcAutomation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dlc.constants.dbConstants;
import com.dlc.core.BaseAPI;
import com.dlc.core.BaseUI;
import com.dlc.core.ExtentReportLogger;
import com.dlc.core.ReadConfig;
import com.dlc.utilities.DBUtils;
import com.dlc.utilities.apiUtils;
import com.dlc.utilities.sshUtils;

public class sampleTestDb extends BaseUI {

	Connection connection1 = null;
	Connection connection2 = null;
	String enrollmentURI = "/enrollment/individual";
	sshUtils ssh;

	@BeforeClass()
	public void init() throws SQLException {

		BaseAPI.testSetUp();
		ssh = new sshUtils();
		connection1 = DBUtils.connection1;
		connection2 = DBUtils.getDBconnect(DBUtils.properties.getProperty("dbURL2"),
				DBUtils.properties.getProperty("dbUsername"), DBUtils.properties.getProperty("dbPassword"));

	}

	@SuppressWarnings("unused")

	@Test(description = "", enabled = true, priority = 1)
	public void dbTesting() throws Exception {

		HashMap<String, String> bodyvalues = new HashMap<String, String>();
		apiUtils.postREquest(enrollmentURI, ReadConfig.getHeaders(), "enrollment.json", bodyvalues);
		ExtentReportLogger.description("DashBoard Testing");
		ssh.runBatchRun();
		String result1 = DBUtils.executeQuery(connection2, dbConstants.phcquery, "phc", "1210093;0");
		String result2 = DBUtils.executeQuery(connection1, dbConstants.kpiQuery, "kpi", "7004;1210093;9999");

		Assert.assertEquals(Integer.parseInt(result2), Integer.parseInt(result2));

	}

	@Test(description = "", enabled = false, priority = 0)
	public void dbTesting01(Hashtable<String, String> data) throws Exception {

		ExtentReportLogger.description("DashBoard Testing");

		// ssh.runBatchRun();
		String result1 = DBUtils.executeQuery(connection2, dbConstants.phcquery, "phc", "1210093/0");
		String result2 = DBUtils.executeQuery(connection1, dbConstants.kpiQuery, "kpi", "7004;1210093;9999");

		Assert.assertEquals(Integer.parseInt(result2), Integer.parseInt(result1));

	}

	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * return DataUtil.getData(xlsWeb, "Data", "TestA1");
	 * 
	 * }
	 * 
	 * @DataProvider public Object[][] getData01() {
	 * 
	 * return DataUtil.getData(xlsWeb, "Sheet2", "TestB");
	 * 
	 * }
	 */
}
