package com.dlc.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dlc.utilities.genricUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseUI {

	protected static WebDriver d;
	public static AndroidDriver<MobileElement> ad;
	public static AppiumDriverLocalService service;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ExtentHtmlReporter htmlReporter1, htmlReporter2;

	static {
		if (extent == null) {

			Date dt = new Date();
			String fileName = dt.toString().replace(":", "_").replace(" ", "_") + ".html";
			System.out.println(fileName);

			htmlReporter1 = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Report\\" + fileName);
			htmlReporter2 = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Report\\" + "report.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter1);
			extent.attachReporter(htmlReporter2);
			extent.setSystemInfo("Environment", "Automation Testing");
			extent.setSystemInfo("User Name", "DLC");

			htmlReporter1.config().setDocumentTitle("DLC Automation Report");
			htmlReporter1.config().setReportName("DLC Automation Report");
			htmlReporter1.config().setTheme(Theme.STANDARD);
			htmlReporter1.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter2.config().setTestViewChartLocation(ChartLocation.TOP);

			htmlReporter2.config().setDocumentTitle("DLC Automation Report.");
			htmlReporter2.config().setReportName("DLC Automation Report");

			htmlReporter2.config().setTheme(Theme.STANDARD);

		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@BeforeSuite
	@Parameters({ "device" })
	public static void setUp(String device) throws InterruptedException, IOException {

		String text = genricUtils.getAlphaNumericString(10);

		if (device.equalsIgnoreCase("Appium")) {
			startServer();
			startEmulator();
			ad = (AndroidDriver<MobileElement>) DriverFactory.getinstance(device);

		}

		if (device.equalsIgnoreCase("Chrome")) {
			d = DriverFactory.getinstanceWeb(device);
			d.get(ReadConfig.properties.getProperty("webURL"));
			Thread.sleep(3000);

		}

	}

	public static AppiumDriverLocalService startServer() {
		//
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void startEmulator() throws IOException, InterruptedException {

		Runtime.getRuntime()
				.exec(System.getProperty("user.dir") + "\\src\\main\\resources\\emulators\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public static void getScreenshot(String s) throws IOException {
		File scrfile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir") + "\\" + s + ".png"));

	}

	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			logger.fail(result.getThrowable());
			String screenShotPath = BaseUI.capture("ssss");
			logger.log(Status.FAIL, result.getThrowable());
			logger.log(Status.FAIL, "Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);

	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		logger = extent.createTest(result.getName());

		// logger.log(Status.INFO, MarkupHelper.createLabel("Base URI :
		// "+RestAssured.baseURI, ExtentColor.PURPLE));

	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		// extent.endTest(test);
		extent.flush();
	}

	@AfterSuite
	public void shutDown() {
		// service.stop();
		if (d != null) {
			d.close();
			d.quit();
		}
		extent.flush();
		// d.close();
		// d.quit();

		System.out.println("========================> Suite finished");
	}

	public static String capture(String screenShotName) throws IOException {
		Date d1 = new Date();
		String screenshotFile = d1.toString().replace(":", "_").replace(" ", "_");
		TakesScreenshot ts = (TakesScreenshot) d;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenshotFile + ".jpg";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}

	public void captureSs(WebDriver d) throws IOException {
		Date d1 = new Date();
		String screenshotFile = d1.toString().replace(":", "_").replace(" ", "_");
		TakesScreenshot ts = (TakesScreenshot) d;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenshotFile + ".jpg";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		logger.log(Status.INFO, "Snapshot below: " + logger.addScreenCaptureFromPath(screenshotFile));

	}
}