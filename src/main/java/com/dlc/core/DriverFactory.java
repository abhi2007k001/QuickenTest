package com.dlc.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	@SuppressWarnings("rawtypes")
	private static WebDriver driver2;
	private static WebDriver driver1;

	public static WebDriver getinstance(String device) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		File f = new File("src");
		File fl = new File(f, "Asha_Feb11.apk");

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ashaApp");
		cap.setCapability("noReset", "true");
		cap.setCapability(MobileCapabilityType.APP, fl.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("platformName", "Android");
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("appPackage", "com.gov.asha");
		// cap.setCapability("MainActivity","com.gov.asha.MainActivity");
		cap.setCapability("appWaitActivity", "com.gov.asha.MainActivity");
		driver2 = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		return driver2;

	}

	public static WebDriver getinstanceWeb(String device) throws MalformedURLException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();

		File file = new File("src/main/resources/drivers/chromedriver.exe");

		System.out.println(file.getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("ignore-certificate-errors");
		options.setAcceptInsecureCerts(true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setCapability("pageLoadStrategy", "none");

		driver1 = new ChromeDriver(cap);
		/*
		 * driver1.get("https://10.63.110.62/#/login"); Thread.sleep(3000);
		 */
		return driver1;

	}

	@SuppressWarnings("rawtypes")
	public static WebDriver getDriver() {
		return driver1;
	}

}
