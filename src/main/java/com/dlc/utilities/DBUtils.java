package com.dlc.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.aventstack.extentreports.Status;
import com.dlc.core.BaseUI;
import com.dlc.core.ExtentReportLogger;

public class DBUtils extends BaseUI {

	private static final String PROP_FILENAME = "dbUtil.properties";
	public static Properties properties = new Properties();
	public static Connection connection1 = null;
	public static Connection connection2 = null;
	public Xls_Reader xlsDB = new Xls_Reader(
			System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\" + "DataDB.xlsx");
	static {
		try {
			FileInputStream in = new FileInputStream(new File(PROP_FILENAME));
			properties.load(in);
			in.close();
		} catch (IOException ex) {
			System.err.println("Failed to read: " + PROP_FILENAME);
		}
	}

	static String DB_CONNECTION = String.valueOf(properties.getProperty("dbURL")).trim();
	static String DB_USER = String.valueOf(properties.getProperty("dbUsername")).trim();
	static String DB_PASSWORD = String.valueOf(properties.getProperty("dbPassword")).trim();

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			connection1 = DriverManager.getConnection(DB_CONNECTION, "admin", "admin123");
			System.out.println("DB Connected");
			// logger.log(Status.INFO , "dB Connected...");
		} catch (SQLException e) {
			System.out.println("Connection Failed!!!");
			logger.log(Status.INFO, "Connection Failed!!!...");
			e.printStackTrace();
		}

	}

	public static Connection getDBconnect(String uRL, String userName, String password) throws SQLException {
		try {

			connection2 = DriverManager.getConnection(uRL, userName, password);
			System.out.println("DB Connected");

		} catch (SQLException e) {
			System.out.println("Connection Failed!!!");
			e.printStackTrace();
		}

		return connection1;

	}

	public static String executeQuery(Connection con, String sQuery, String source, String params) throws SQLException {
		PreparedStatement preparedStatement = null;
		String result = "";
		String x[] = params.split(";");
		if (source.equalsIgnoreCase("phc")) {
			sQuery = sQuery.replace("#phcId", x[0]).replace("#stateId", x[1]);

		} else if (source.equalsIgnoreCase("kpi"))
			sQuery = sQuery.replace("#locationType", x[0]).replace("#locationId", x[1]).replace("#filterId", x[2]);

		try {

			preparedStatement = con.prepareStatement(sQuery);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
				break;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

		}

		ExtentReportLogger.query(sQuery);
		ExtentReportLogger.output(result);

		return result;
	}

	public static ResultSet executeQuery(String sQuery) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {

			preparedStatement = connection1.prepareStatement(sQuery);

			result = preparedStatement.executeQuery();

		} catch (SQLException s) {
			s.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

		}

		return result;
	}

	public static void excuteUpdate(String sQuery) throws SQLException {

		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection1.prepareStatement(sQuery);

			preparedStatement.executeUpdate();
			System.out.println("Record is updated successfully...");

		} catch (SQLException s) {
			s.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

		}

	}

	public static void closeDBConnection() throws SQLException {
		try {
			if (connection1 != null) {
				connection1.close();
			}
			System.out.println("DB Connection closed successfully...");
		} catch (SQLException s) {
			s.printStackTrace();
		}

	}

}