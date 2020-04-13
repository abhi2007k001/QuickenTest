package com.dlc.utilities;

import org.testng.asserts.SoftAssert;

import com.dlc.Mob_Pages.LoginPage;
import com.dlc.core.BaseUI;

public class verificationUtils extends BaseUI {

	LoginPage log = new LoginPage();

	public boolean verifyIndividualInfo(String nameExp) throws InterruptedException {

		SoftAssert sa = new SoftAssert();

		boolean flag = false;

		String name = log.getIndividualName();
		String age = log.getIndividualAge();
		String gender = log.getIndividualGender();
		String cbacScore = log.getCbacScore().trim();

		sa.assertEquals(name, nameExp);
		sa.assertEquals(age, "32 years");
		sa.assertEquals(gender, "Male");
		sa.assertEquals(cbacScore, "CBAC:-");
		sa.assertAll();

		return flag;

	}

}
