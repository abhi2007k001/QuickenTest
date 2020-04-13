package com.dlc.Mob_Pages;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.dlc.core.BaseUI;
import com.dlc.core.ReadConfig;
import com.dlc.utilities.mobileUitils;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BaseUI {

	// Mobile_Utils util= new Mobile_Utils();

	public LoginPage() {

		PageFactory.initElements(new AppiumFieldDecorator(ad), this);
	}

	private By password = By.id("com.gov.asha:id/password");
	private By login = By.id("com.gov.asha:id/logIn");
	private By loginText = By.xpath("//android.widget.TextView[@text='LOGIN']");
	private By pin = By.id("com.gov.asha:id/cphm_verification_value");
	private By enrollmentSquare = By.id("com.gov.asha:id/card_enrollment");
	private By stateDd = By.id("com.gov.asha:id/spinner_textview");
	private By stateDdValue = By.xpath("//android.widget.TextView[@text='Bangalore']");
	private By oTPText = By.id("com.gov.asha:id/captchaImage");
	private By otp = By.id("com.gov.asha:id/text_captcha_value");
	private By familyInfoSave = By.id("com.gov.asha:id/save_ind_btn");
	private By addMember = By.id("com.gov.asha:id/fab_btn");
	private By memberName = By.id("com.gov.asha:id/ind_name_edittext");
	private By memberAge = By.id("com.gov.asha:id/age_value_edittext");
	private By memberGenderMale = By.id("com.gov.asha:id/enrolment_gender_male");
	private By memberGenderFemale = By.id("com.gov.asha:id/enrolment_gender_female");
	private By memberGenderOther = By.id("com.gov.asha:id/enrolment_gender_other");
	private By memberMaritalDd = By.id("com.gov.asha:id/marital_spinner");
	private By memberMaritalDdValue = By.xpath("//android.widget.TextView[@text='Single']");
	private By memberSave = By.id("com.gov.asha:id/save_ind_btn");

	private By individualNameLabel = By.id("com.gov.asha:id/ind_name");
	private By individualAgeLabel = By.id("com.gov.asha:id/indivi_age");
	private By individualGenderLabel = By.id("com.gov.asha:id/ind_gender");
	private By individualCbacScoreLabel = By.id("com.gov.asha:id/ind_cbac_score");

	private By cbacPartA = By.id("com.gov.asha:id/stepper_tv_parta");
	private By cbacPartASmokeDd = By.id("com.gov.asha:id/risk_assessment_smoke_value");
	private By cbacPartASmokeDdValue = By.xpath("//android.widget.TextView[@text='Daily']");
	private By cbacPartAlcocholRadio = By.id("com.gov.asha:id/consume_alcohol_yes");
	private By cbacPartAwaistDd = By.id("com.gov.asha:id/risk_assessment_waist_measurement_spinner_value");
	private By cbacPartAwaistDdValue = By.xpath("//android.widget.TextView[@text='90 cm or less']");
	private By cbacPartAactivity = By.id("com.gov.asha:id/physical_activity_less");
	private By cbacPartAhistory = By.id("com.gov.asha:id/family_history_no");
	private By cbacPartAtotalScore = By.id("com.gov.asha:id/risk_assessment_total_score_value");

	@AndroidFindBy(id = "com.gov.asha:id/password")
	public WebElement ashaId1;

	@AndroidFindBy(id = "com.gov.asha:id/spinner_textview")
	public WebElement stateDd1;

	@AndroidFindBy(xpath = "//*[text()='Bangalore']")
	public WebElement stateDdValue1;

	@AndroidFindBy(id = "com.gov.asha:id/password")
	public WebElement mobileNo;

	public void setPassword(String input) throws InterruptedException {

		mobileUitils.type(ad, password, input, "Passworg Textbox");

	}

	public void clickLogin() throws InterruptedException {

		// mobileUitils.scroll(ad, "LOGIN");
		mobileUitils.scrollNclick(ad, login, "Login Button", ReadConfig.properties.getProperty("loginTEXT"));

	}

	public void setPIN(String input) throws InterruptedException {
		Thread.sleep(2000);
		mobileUitils.type(ad, pin, input, "Set PIN");

	}

	public void clickEnrollmentSquare() throws InterruptedException {
		mobileUitils.click(ad, enrollmentSquare, "Enrollment SQUARE");

	}

	public void selectVillage() throws InterruptedException {
		mobileUitils.click(ad, stateDd, "Open Village dropdown");
		mobileUitils.click(ad, stateDdValue, "Select village");

	}

	public void setCaptcha() throws InterruptedException {

		mobileUitils.scrollToId(ad, login);

		String oTP = ad.findElement(oTPText).getAttribute("content-desc");
		mobileUitils.type(ad, otp, oTP, "OTP textBox");
	}

	public void clickfamilyInfoSave() throws InterruptedException {

		mobileUitils.scrollNclick(ad, familyInfoSave, "Save Button", ReadConfig.properties.getProperty("saveTEXT"));

	}

	public void clickAddMemberIcon() throws InterruptedException {

		mobileUitils.click(ad, addMember, "Add Memeber");

	}

	public void setMemberName(String textName) throws InterruptedException {

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
		mobileUitils.click(ad, memberMaritalDdValue, "Select " + textMarital);

	}

	public void clickMemberInfoSave() throws InterruptedException {

		mobileUitils.scrollNclick(ad, familyInfoSave, "Save Info", ReadConfig.properties.getProperty("saveTEXT"));

	}

	public void cbacPartAScreen() throws InterruptedException {

		mobileUitils.click(ad, cbacPartA, "Navigate cbac : Part A screen");

	}

	public void cbacPartASmokeValue() throws InterruptedException {

		mobileUitils.click(ad, cbacPartASmokeDd, "Expand Smoke Drop-down");
		mobileUitils.click(ad, cbacPartASmokeDdValue, " Select Smoke Drop-down value");

	}

	public void cbacPartAlcohol() throws InterruptedException {

		mobileUitils.click(ad, cbacPartAlcocholRadio, "Select Alcohol Radio button");

	}

	public void SelectcbacPartAwaist() throws InterruptedException {

		mobileUitils.scrollToId(ad, cbacPartAactivity);
		mobileUitils.click(ad, cbacPartAwaistDd, "Expand waist size drop-down");
		mobileUitils.click(ad, cbacPartAwaistDdValue, "Select waist size");

	}

	public void SelectcbacPartAexercise() throws InterruptedException {

		mobileUitils.click(ad, cbacPartAactivity, "Select waist size");

	}

	public void SelectcbacPartAhistory() throws InterruptedException {

		mobileUitils.click(ad, cbacPartAhistory, "Select history");

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

	public void login(Hashtable<String, String> data) throws InterruptedException {

		setPassword(data.get("Password"));
		setCaptcha();
		clickLogin();
		setPIN(data.get("Pin"));

	}

}
