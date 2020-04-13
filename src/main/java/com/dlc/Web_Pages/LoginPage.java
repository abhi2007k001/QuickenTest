package com.dlc.Web_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dlc.utilities.webUtils;

//import drivers.Mobile_Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage {

	WebDriver d;
	// Mobile_Utils util= new Mobile_Utils();

	@FindBy(id = "username")
	public By ashaId1;

	private By ashaUsername = By.id("username");
	private By ashaPassword = By.id("password");
	private By ashaLogin = By.xpath("//*[@type='submit']");

	@AndroidFindBy(id = "com.gov.asha:id/spinner_textview")
	public WebElement stateDd;

	@AndroidFindBy(xpath = "//*[text()='Andhra Pradesh']")
	public WebElement stateDdValue;

	@AndroidFindBy(id = "com.gov.asha:id/password")
	public WebElement mobileNo;

	public LoginPage(WebDriver d) {

		this.d = d;
		PageFactory.initElements(d, this);
	}

	public void setUSerName(String userName, String eleName) throws InterruptedException {

		webUtils.type(d, ashaUsername, userName, eleName);
	}

	public void setPAssword(String userName, String eleName) throws InterruptedException {

		webUtils.type(d, ashaPassword, userName, eleName);
	}

	public void clickLogin(String eleName) throws InterruptedException {

		webUtils.click(d, ashaLogin, eleName);
	}

	public void login() throws InterruptedException {

		Thread.sleep(5000);
		setUSerName("doc_10800060", "Username TextBox");
		setPAssword("password", "Password TextBox");
		clickLogin("Login/Submit Button");

	}

}