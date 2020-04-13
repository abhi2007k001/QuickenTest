package com.dlc.Mob_Pages;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.dlc.core.BaseUI;
import com.dlc.core.ReadConfig;
import com.dlc.utilities.genricUtils;
import com.dlc.utilities.mobileUitils;
import com.dlc.utilities.webUtils;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EnrollmentPage extends BaseUI {

	// Mobile_Utils util= new Mobile_Utils();

	public EnrollmentPage() {

		PageFactory.initElements(new AppiumFieldDecorator(ad), this);
	}

	private By enrollmentSquare = By.id("com.gov.asha:id/card_enrollment");
	private By stateDd = By.id("com.gov.asha:id/spinner_textview");
	private By stateDdValue = By.xpath("//android.widget.TextView[@text='+']");
	private By familyInfoSave = By.id("com.gov.asha:id/save_ind_btn");
	private By addMember = By.id("com.gov.asha:id/fab_btn");
	private By memberName = By.id("com.gov.asha:id/ind_name_edittext");
	private By memberAge = By.id("com.gov.asha:id/age_value_edittext");
	private By memberGenderMale = By.id("com.gov.asha:id/enrolment_gender_male");
	private By memberGenderFemale = By.id("com.gov.asha:id/enrolment_gender_female");
	private By memberGenderOther = By.id("com.gov.asha:id/enrolment_gender_other");
	private By memberMaritalDd = By.id("com.gov.asha:id/marital_spinner");
	private By memberMaritalDdValue = By.xpath("//android.widget.TextView[@text='+']");

	private By individualNameLabel = By.id("com.gov.asha:id/ind_name");
	private By individualAgeLabel = By.id("com.gov.asha:id/indivi_age");
	private By individualGenderLabel = By.id("com.gov.asha:id/ind_gender");
	private By individualCbacScoreLabel = By.id("com.gov.asha:id/ind_cbac_score");

	private By cbacPartA = By.id("com.gov.asha:id/stepper_tv_parta");
	private By cbacPartASmokeDd = By.id("com.gov.asha:id/risk_assessment_smoke_value");
	private By cbacPartASmokeDdValue = By.xpath("//android.widget.TextView[@text='+']");
	private By cbacPartAlcocholRadio = By.id("com.gov.asha:id/consume_alcohol_yes");
	private By cbacPartAwaistDd = By.id("com.gov.asha:id/risk_assessment_waist_measurement_spinner_value");
	private By cbacPartAwaistDdValue = By.xpath("//android.widget.TextView[@text='+']");
	private By cbacPartAactivity = By.id("com.gov.asha:id/physical_activity_less");
	private By cbacPartAhistory = By.id("com.gov.asha:id/family_history_no");
	private By cbacPartAtotalScore = By.id("com.gov.asha:id/risk_assessment_total_score_value");

	private By cbacPartB = By.id("com.gov.asha:id/stepper_tv_partb");
	private By cbacPartBshortBreatheNo = By.id("com.gov.asha:id/shortness_of_breath_radio_btn_no");
	private By cbacPartBCoughYes = By.id("com.gov.asha:id/coughing_week_radio_btn_yes");
	private By cbacPartBsputumNo = By.id("com.gov.asha:id/blood_sputum_radio_btn_no");

	private By cbacPartC = By.id("com.gov.asha:id/stepper_tv_partc");
	private By cbacPartCcookingDd = By.id("com.gov.asha:id/cooking_info_spinner");
	private By cbacPartCcookingOk = By.id("com.gov.asha:id/mulit_spinner_ok");
	private By fireWoodCb = By.xpath("//android.widget.CheckBox[@index='1']");

	private By cbacPartCresidueBurn = By.id("com.gov.asha:id/checkbox_crop_residue_burning");
	private By cbacPartCSave = By.id("com.gov.asha:id/save_partc_btn");

	public void clickEnrollmentSquare() throws InterruptedException {
		mobileUitils.click(ad, enrollmentSquare, "Enrollment SQUARE");

	}

	public void selectVillage(String Village) throws InterruptedException {
		mobileUitils.click(ad, stateDd, "Open Village dropdown");
		Thread.sleep(1500);
		mobileUitils.clickByText(ad, stateDdValue, Village, "Selected Village:" + Village);
		// mobileUitils.click(ad, stateDdValue, "Select village");

	}

	public void clickfamilyInfoSave() throws InterruptedException {

		mobileUitils.scrollNclick(ad, familyInfoSave, "Save Button", ReadConfig.properties.getProperty("saveTEXT"));

	}

	public void clickAddMemberIcon() throws InterruptedException {

		mobileUitils.click(ad, addMember, "Add Memeber");

	}

	public void setMemberName(String textName) throws InterruptedException {

		Thread.sleep(2000);
		mobileUitils.type(ad, memberName, textName, "Memeber Name textBox");
	}

	public void setMemberAge(String textAge) throws InterruptedException {

		mobileUitils.scrollToId(ad, memberAge);
		mobileUitils.type(ad, memberAge, textAge, "Age Text Box");

	}

	public void selectGender(String textGender) throws InterruptedException {

		if (textGender.equalsIgnoreCase("Male")) {

			mobileUitils.click(ad, memberGenderMale, "Selected Male gender");

		} else if (textGender.equalsIgnoreCase("Female")) {
			mobileUitils.click(ad, memberGenderFemale, "Selected Female gender");

		} else if (textGender.equalsIgnoreCase("Other")) {
			mobileUitils.click(ad, memberGenderOther, "Selected Other gender");

		}

	}

	public void selectMaritalStatus(String textMarital) throws InterruptedException {

		mobileUitils.scrollNclick(ad, familyInfoSave, "Save Button", ReadConfig.properties.getProperty("saveTEXT"));
		mobileUitils.click(ad, memberMaritalDd, "Expand marital drop-down");
		Thread.sleep(1500);
		mobileUitils.clickByText(ad, memberMaritalDdValue, textMarital, "Selected Village :" + textMarital);

	}

	public void clickMemberInfoSave() throws InterruptedException {

		mobileUitils.scrollNclick(ad, familyInfoSave, "Save Info", ReadConfig.properties.getProperty("saveTEXT"));

	}

	public void cbacPartAScreen() throws InterruptedException {

		mobileUitils.click(ad, cbacPartA, "Navigate cbac : Part A screen");

	}

	public void cbacPartASmokeValue(String smokeText) throws InterruptedException {

		mobileUitils.click(ad, cbacPartASmokeDd, "Expand Smoke Drop-down");
		Thread.sleep(1500);
		mobileUitils.clickByText(ad, cbacPartASmokeDdValue, smokeText, "Selected Village :" + smokeText);
		// mobileUitils.click(ad, cbacPartASmokeDdValue, " Select Smoke
		// Drop-down value");

	}

	public void cbacPartAlcohol(String parameter) throws InterruptedException {

		if (parameter.equalsIgnoreCase("Yes")) {

			mobileUitils.click(ad, cbacPartAlcocholRadio, "Select Alcohol Radio button");
		}

	}

	public void SelectcbacPartAwaist(String waistText) throws InterruptedException {

		mobileUitils.scrollToId(ad, cbacPartAactivity);
		mobileUitils.click(ad, cbacPartAwaistDd, "Expand waist size drop-down");
		Thread.sleep(2000);
		mobileUitils.clickByText(ad, cbacPartAwaistDdValue, waistText, "Selected Village :" + waistText);

	}

	public void SelectcbacPartAexercise(String activityText) throws InterruptedException {

		if (activityText.equalsIgnoreCase("less")) {
			mobileUitils.click(ad, cbacPartAactivity, "Select waist size");
		}
	}

	public void SelectcbacPartAhistory(String familyHistoryText) throws InterruptedException {

		if (familyHistoryText.equalsIgnoreCase("Yes")) {

			mobileUitils.click(ad, cbacPartAhistory, "Select history");
		}

	}

	public String cbacPartAtotalScore() throws InterruptedException {

		String totalScore = ad.findElement(cbacPartAtotalScore).getText();

		return totalScore;

	}

	public String getIndividualName() throws InterruptedException {

		mobileUitils.scrollToId(ad, individualCbacScoreLabel);
		String name = ad.findElement(individualNameLabel).getText();

		return name;

	}

	public String getIndividualAge() throws InterruptedException {

		String age = ad.findElement(individualAgeLabel).getText();

		return age;

	}

	public String getIndividualGender() throws InterruptedException {

		String gender = ad.findElement(individualGenderLabel).getText();

		return gender;

	}

	public String getCbacScore() throws InterruptedException {

		String cbacScore = ad.findElement(individualCbacScoreLabel).getText();

		return cbacScore;

	}

	public void SelectIndividual(String text) throws InterruptedException {

		ad.findElement(By.xpath("//android.widget.TextView[@text='" + text + "']")).click();

	}

	public void selectPartB() throws InterruptedException {
		mobileUitils.click(ad, cbacPartB, "Part B Section");
	}

	public void selectPartBcbreathNo() throws InterruptedException {
		mobileUitils.click(ad, cbacPartBshortBreatheNo, "Select cbacPartBshortBreatheNo option");
	}

	public void selectPartBcoughYes() throws InterruptedException {
		mobileUitils.click(ad, cbacPartBCoughYes, "SelectPartCcoughYes option");
	}

	public void selectPartsputumNo() throws InterruptedException {
		mobileUitils.click(ad, cbacPartBsputumNo, "selectPartCsputumNo");
	}

	public void cbacPartC() throws InterruptedException {
		mobileUitils.click(ad, cbacPartC, "Part C Section");
	}

	public void cbacPartCresidueBurn() throws InterruptedException {
		mobileUitils.click(ad, cbacPartCresidueBurn, "Select cbacPartCresidueBurn");
	}

	public void cbacPartCSave() throws InterruptedException {
		mobileUitils.click(ad, cbacPartCSave, "Save Parct C");
	}

	public void cbacPartCcookingDd() throws InterruptedException {
		mobileUitils.click(ad, cbacPartCcookingDd, "COoking drop-down");
	}

	public void cbacPartCcookingOk() throws InterruptedException {
		mobileUitils.click(ad, cbacPartCcookingOk, "COoking drop-down Ok");
	}

	public void cbacPartCcookingValue(String testValue) throws InterruptedException {
		Thread.sleep(1500);
		mobileUitils.clickByText(ad, cbacPartAwaistDdValue, testValue, "Selected Village :" + testValue);
	}

	public void addFamily(Hashtable<String, String> data) throws InterruptedException {

		clickEnrollmentSquare();
		selectVillage(data.get("Village"));
		clickfamilyInfoSave();

	}

	public String addIndividualInfo(Hashtable<String, String> data) throws InterruptedException {

		clickAddMemberIcon();
		String usernname = data.get("UserName") + " " + genricUtils.getAlphaNumericString(10);
		setMemberName(usernname);
		setMemberAge(data.get("Age"));
		selectGender(data.get("Gender"));
		selectMaritalStatus(data.get("MaritalStatus"));
		clickMemberInfoSave();
		return usernname;

	}

	public void cbacPartAInfo(Hashtable<String, String> data) throws InterruptedException {

		cbacPartAScreen();
		cbacPartASmokeValue(data.get("Smoke"));
		cbacPartAlcohol(data.get("Alcohol"));
		SelectcbacPartAwaist(data.get("Waist"));
		SelectcbacPartAexercise(data.get("Activity"));
		SelectcbacPartAhistory(data.get("FamilyHistory"));
		clickMemberInfoSave();
	}

	public void cbacPartBinfo() throws InterruptedException {
		selectPartB();
		selectPartBcbreathNo();
		// selectPartBcoughYes();
		selectPartsputumNo();
		clickMemberInfoSave();

	}

	public void cbacPartCinfo() throws InterruptedException {

		cbacPartC();
		cbacPartCcookingDd();
		cbacPartCcookingOk();
		webUtils.click(ad, fireWoodCb, "FireWood Seleceted");
		cbacPartCcookingOk();
		Thread.sleep(1500);
		cbacPartCSave();

	}
}
