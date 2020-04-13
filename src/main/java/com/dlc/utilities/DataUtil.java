package com.dlc.utilities;

import java.util.Hashtable;

import com.dlc.core.BaseUI;
import com.dlc.core.ReadConfig;

public class DataUtil extends BaseUI {

	public static Object[][] getData(String xls1, String sheetName, String testCaseName) {

		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + ReadConfig.properties.getProperty("excelPath") + xls1 + ".xlsx");

		int testStartRowNum = 1;
		while (!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)) {
			testStartRowNum++;
		}
		System.out.println("Test starts from row - " + testStartRowNum);
		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;

		// calculate rows of data
		int rows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + rows).equals("")) {
			rows++;
		}
		System.out.println("Total rows are  - " + rows);

		// calculate total cols
		int cols = 0;
		while (!xls.getCellData(sheetName, cols, colStartRowNum).equals("")) {
			cols++;
		}
		System.out.println("Total cols are  - " + cols);
		Object[][] data = new Object[rows][1];
		// read the data
		int dataRow = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < cols; cNum++) {
				String key = xls.getCellData(sheetName, cNum, colStartRowNum).toString();
				String value = xls.getCellData(sheetName, cNum, rNum).toString();
				table.put(key, value);
				// 0,0 0,1 0,2
				// 1,0 1,1
			}
			data[dataRow][0] = table;
			dataRow++;
		}
		return data;
	}

	public static boolean isTestRunnable(Xls_Reader xls, String testName) {
		int rows = xls.getRowCount("TestCases");
		for (int rNum = 2; rNum <= rows; rNum++) {
			String currentTestName = xls.getCellData("TestCases", "TCID", rNum);
			if (currentTestName.equals(testName)) {
				// found the test
				String runmode = xls.getCellData("TestCases", "Runmodes", rNum);
				if (runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
