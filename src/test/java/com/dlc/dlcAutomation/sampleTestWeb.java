package com.dlc.dlcAutomation;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dlc.Web_Pages.EnrollmentPage;
import com.dlc.Web_Pages.LoginPage;
import com.dlc.core.BaseUI;

public class sampleTestWeb extends BaseUI {

	LoginPage log;
	EnrollmentPage enroll;

	@BeforeClass()
	public void initt() throws SQLException {

		String s1 = System.getProperty("Denvironment");
		System.out.println(s1);

		log = new LoginPage(d);
		enroll = new EnrollmentPage(d);
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(description = "", enabled = true, priority = 1, invocationCount = 1)
	public void weBTesting() throws Exception {

		log.login();
		enroll.enroll();

	}

}
