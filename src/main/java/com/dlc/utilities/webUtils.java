package com.dlc.utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.dlc.core.BaseUI;
import com.dlc.core.ExtentReportLogger;

public class webUtils extends BaseUI {

	// TODO Auto-generated method stub

	static WebDriver d1;

	public static List<WebElement> waitForElementVisible(WebDriver d1, By element) throws InterruptedException {

		d1.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		int timeOut = 2;
		int z = 0;

		List<WebElement> list = null;
		if (z < timeOut) {

			list = d1.findElements((By) element);
			if (list.size() == 0) {
				Thread.sleep(1000);
				z = z + 1;

			} else {

				scrollToWebElementMiddle(d1, element);

			}

		}
		return list;

	}

	public static void scrollToWebElementMiddle(WebDriver d1, By element) {

		WebElement ele = d1.findElement(element);
		((JavascriptExecutor) d1).executeScript(
				"window.scrollTo(0,arguments[0].getBoundingClientRect().top + window.pageYOffset - (window.innerHeight / 2))",
				ele);
	}

	public static WebElement click(WebDriver d, By element, String eleName) throws InterruptedException {

		jsquery js = new jsquery();
		js.waitAllRequest();
		WebElement ele = null;
		List<WebElement> x = waitForElementVisible(d, element);

		if (x.size() > 0) {

			ele = d.findElement(element);
			if (ele.isDisplayed()) {
				ele.click();
			}
			logger.log(Status.INFO, "Clicked on " + eleName);

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

	public static WebElement type(WebDriver d, By element, String userName, String eleName)
			throws InterruptedException {
		jsquery js = new jsquery();
		js.waitAllRequest();
		WebElement ele = null;
		List<WebElement> x = waitForElementVisible(d, element);

		if (x.size() > 0) {

			ele = d.findElement(element);
			ele.clear();
			ele.sendKeys(userName);
			logger.log(Status.INFO, "Entered " + userName + " to " + eleName + " field");
		}
		return ele;

	}

	public static void safeJavaScriptClick(WebDriver d, By element) throws Exception {

		WebElement ele = null;
		ele = d.findElement(element);
		try {
			if (ele.isEnabled() && ele.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) d).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}

}
