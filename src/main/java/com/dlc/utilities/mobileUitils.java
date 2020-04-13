package com.dlc.utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.dlc.core.BaseUI;
import com.dlc.core.ExtentReportLogger;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class mobileUitils extends BaseUI {

	// TODO Auto-generated method stub

	static WebDriver d1;

	public static List<MobileElement> waitForElementVisible(WebDriver ad, By element) throws InterruptedException {

		ad.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		String[] eleText = element.toString().split(" ");

		int timeOut = 10;
		int z = 0;

		List<MobileElement> list = null;
		if (z < timeOut) {

			list = ad.findElements(element);
			if (list.size() == 0) {
				Thread.sleep(1000);
				z = z + 1;

			} else {

			}

		}
		return list;

	}

	public static void scrollToWebElementMiddle(AndroidDriver<MobileElement> ad, By element) {

		MobileElement ele = ad.findElement(element);
		((JavascriptExecutor) ad).executeScript(
				"window.scrollTo(0,arguments[0].getBoundingClientRect().top + window.pageYOffset - (window.innerHeight / 2))",
				ele);
	}

	public static WebElement click(AndroidDriver<MobileElement> ad, By element, String eleName)
			throws InterruptedException {

		WebElement ele = null;
		String[] loc = element.toString().split(":");

		List<MobileElement> x = waitForElementVisible(ad, element);

		if (x.size() > 0) {
			ele = ad.findElement(element);
			ele.click();
			logger.log(Status.INFO, "Clicked on " + eleName);

		} else {
			if (loc[0].contains("id")) {

				scrollToIdNclick(ad, element);
			}

		}
		return ele;

	}

	public static WebElement clickByText(AndroidDriver<MobileElement> ad, By element, String textName, String eleName)
			throws InterruptedException {

		String[] locator = element.toString().replace("+", textName).split(":");
		By byEle = By.xpath(locator[1]);

		WebElement ele = null;
		List<MobileElement> x = waitForElementVisible(ad, byEle);

		if (x.size() > 0) {
			ele = ad.findElement(byEle);
			ele.click();
			logger.log(Status.INFO, "Clicked on:" + eleName);

		}
		return ele;

	}

	public static WebElement scrollNclick(AndroidDriver<MobileElement> ad, By element, String eleName,
			String scrollText) throws InterruptedException {

		WebElement ele = null;
		List<MobileElement> x = waitForElementVisible(ad, element);

		if (x.size() > 0) {
			ele = ad.findElement(element);
			ele.click();
			logger.log(Status.INFO, "Clicked on " + eleName);

		} else {
			scrollnClickText(ad, scrollText);
		}
		return ele;

	}

	public static WebElement isDisplayed(WebDriver d, By element, String eleName) throws InterruptedException {

		d.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		try {

			if (d.findElement(element).isDisplayed()) {

			} else {

				Assert.fail("");
			}
		} catch (Exception e) {

			ExtentReportLogger.error(eleName + " is not displayed");

		} finally {

			d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

		return (WebElement) element;

	}

	public static void type(AndroidDriver<MobileElement> ad, By element, String userName, String eleName)
			throws InterruptedException {

		WebElement ele = null;
		String[] loc = element.toString().split(":");
		List<MobileElement> x = waitForElementVisible(ad, element);

		if (x.size() > 0) {

			ele = ad.findElement(element);
			ele.clear();
			Thread.sleep(1000);
			ele.sendKeys(userName);
			logger.log(Status.INFO, "Entered " + userName + " to " + eleName + " field");
		} else {
			if (loc[0].contains("id")) {

				scrollToIdNType(ad, element, userName);
			}

		}

	}

	public static void scrollnClickText(AndroidDriver<MobileElement> ad, String text) {

		MobileElement elementToClick = ad.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().textContains(" + text + "));");

		elementToClick.click();

		logger.log(Status.INFO, "Clicked on :" + text);
	}

	public static void scrollTillTextandClick(AndroidDriver<MobileElement> ad, String text) {
		MobileElement elementToClick = (MobileElement) ad.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().textContains(\'" + text + "'\"));");
		elementToClick.click();
		logger.log(Status.INFO, "Scrolled and clicked " + text + " field");

	}

	public static void scrollToId(AndroidDriver<MobileElement> ad, By id) throws InterruptedException {
		List<MobileElement> list = null;

		list = ad.findElements(id);
		if (list.size() == 0) {

			String loc[] = id.toString().split(" ");
			MobileElement el = (MobileElement) ad.findElementByAndroidUIAutomator(
					"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
							+ "new UiSelector().resourceIdMatches(\"" + loc[1] + "\"));");
			Thread.sleep(1500);
		}
	}

	public static void scrollToIdNclick(AndroidDriver<MobileElement> ad, By id) {

		List<MobileElement> list = null;

		list = ad.findElements(id);
		if (list.size() == 0) {

			String loc[] = id.toString().split(" ");

			MobileElement el = (MobileElement) ad.findElementByAndroidUIAutomator(
					"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
							+ "new UiSelector().resourceIdMatches(\"" + loc[1] + "\"));");
			el.click();
		}
	}

	public static void scrollToIdNType(AndroidDriver<MobileElement> ad, By id, String text) {

		List<MobileElement> list = null;

		list = ad.findElements(id);
		if (list.size() == 0) {

			String loc[] = id.toString().split(" ");

			MobileElement el = (MobileElement) ad.findElementByAndroidUIAutomator(
					"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
							+ "new UiSelector().resourceIdMatches(\"" + loc[1] + "\"));");
			el.clear();
			el.sendKeys(text);
		}
	}

}
