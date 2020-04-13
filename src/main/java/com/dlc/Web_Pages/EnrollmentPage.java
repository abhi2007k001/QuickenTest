package com.dlc.Web_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dlc.utilities.jsquery;
import com.dlc.utilities.webUtils;

public class EnrollmentPage {

	WebDriver d;
	// Mobile_Utils util= new Mobile_Utils();

	@FindBy(id = "username")
	public By ashaId1;

	private By enrollmentMenu = By.xpath("//*[@id='enrollment_menu' and text()=' ENROLLMENT ']");
	private By subCenterDd = By.id("sub-center");
	private By subCenterDdItem = By.xpath("//*[contains(@id,'cdk-overlay')]//*[text()=' msm_demo_hwc ']");
	private By villageNameMenu = By.id("villagename");
	private By villageNameMenuItem = By.xpath("//*[contains(@id,'cdk-overlay')]//*[text()=' village one msm ']");
	private By familyDetailsTab = By.xpath("//*[text()=' Fill Individual Details '] ");
	private By individualName = By.id("name");
	private By genderDd = By.id("gender");
	private By genderDdItem = By.xpath("//*[contains(@id,'cdk-overlay')]//*[text()=' Male ']");
	private By maritalDd = By.id("maritalStatus");
	private By maritalDdItem = By.xpath("//*[contains(@id,'cdk-overlay')]//*[text()=' Married ']");
	private By age = By.id("age");
	private By saveNsubmit = By.id("saveAndSubmitBtn");
	private By userProfileName = By.xpath("//*[@class='user-profile1']");

	public EnrollmentPage(WebDriver d) {

		this.d = d;
		PageFactory.initElements(d, this);
	}

	/*
	 * public void setUSerName(String userName) throws InterruptedException {
	 * 
	 * webUtils.type(d, ashaUsername, userName); }
	 * 
	 * public void setPAssword(String userName) throws InterruptedException {
	 * 
	 * webUtils.type(d, ashaPassword, userName); }
	 */
	public void clickEnrollment(String eleName) throws InterruptedException {

		webUtils.click(d, enrollmentMenu, eleName);
	}

	public void clickSubCenterMenu(String eleName) throws InterruptedException {

		webUtils.click(d, subCenterDd, eleName);
	}

	public void enroll() throws Exception {

		WebDriverWait wait = new WebDriverWait(d, 30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(enrollmentMenu));

		WebElement element = d.findElement(enrollmentMenu);
		Actions action = new Actions(d);

		// Focus to element
		action.moveToElement(element).perform();

		// To click on the element
		action.moveToElement(element).click().perform();

		// webUtils.click(d, enrollmentMenu, "Enrollment Option");
		Thread.sleep(5000);
		jsquery js = new jsquery();
		js.waitAllRequest();

		wait.until(ExpectedConditions.visibilityOfElementLocated(subCenterDd));

		WebElement we = d.findElement(subCenterDd);
		// Thread.sleep(2000);
		action.moveToElement(we).pause(2000).moveToElement(we).click().build().perform();
		Thread.sleep(2000);

		// webUtils.safeJavaScriptClick(d, subCenterDd);
		webUtils.click(d, subCenterDd, "SubCenter dropdown");
		// webUtils.click(d, subCenterDd, "SubCenter dropdown");
		Thread.sleep(2500);
		webUtils.click(d, subCenterDdItem, "SubCenter dropdown value");

		webUtils.click(d, villageNameMenu, "Village dropdown");
		Thread.sleep(1500);
		webUtils.click(d, villageNameMenuItem, "Village dropdown value");
		webUtils.click(d, familyDetailsTab, "Expanding Family section");
		webUtils.type(d, individualName, "automationUser", "Individual Name TextBox");
		webUtils.click(d, genderDd, "Gender dropdown");
		webUtils.click(d, genderDdItem, "Gender dropdown value");
		webUtils.click(d, maritalDd, "Marital dropdown ");
		webUtils.click(d, maritalDdItem, "Marital dropdown value");
		webUtils.type(d, age, "32", "Age TextBox");
		webUtils.click(d, saveNsubmit, "Save and Submit");

	}

}