package com.dlc.dlcAutomation;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dlc.Mob_Pages.EnrollmentPage;
import com.dlc.Mob_Pages.LoginPage;
import com.dlc.core.BaseUI;
import com.dlc.utilities.DataUtil;
import com.dlc.utilities.verificationUtils;

public class sampleTestMob extends BaseUI {

	LoginPage log;
	EnrollmentPage enroll;
	verificationUtils ver;

	@BeforeClass()
	public void initt() throws SQLException {

		log = new LoginPage();
		enroll = new EnrollmentPage();
		ver = new verificationUtils();
		ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(description = "", enabled = true, priority = 1, dataProvider = "getData", invocationCount = 1)
	public void mobTesting(Hashtable<String, String> data) throws Exception {

		log.login(data);
		enroll.addFamily(data);

		String username = enroll.addIndividualInfo(data);
		ver.verifyIndividualInfo(username);
		enroll.SelectIndividual(username);
		enroll.cbacPartAInfo(data);
		enroll.cbacPartBinfo();

	}

	@DataProvider
	public Object[][] getData() {

		return DataUtil.getData("DataMob", "Data", "TestA1");

	}

}
