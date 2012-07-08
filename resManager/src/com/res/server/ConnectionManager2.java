package com.res.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ConnectionManager2 {

	private static Logger logger = Logger.getLogger(ConnectionManager2.class.getName());

	public static synchronized Connection getConnection() {
		loadDriver(GlobalVariables.driverClass);
		Connection conn = null;
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(GlobalVariables.jdbcUrl, GlobalVariables.user, GlobalVariables.password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static void loadDriver(String driver) {
		try {
			Class.forName(driver).newInstance();
			logger.info("Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			logger.error("\nUnable to load the JDBC driver " + driver);
			logger.error("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			logger.error("\nUnable to instantiate the JDBC driver " + driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			logger.error("\nNot allowed to access the JDBC driver " + driver);
			iae.printStackTrace(System.err);
		}
	}

	public static void close(Connection con, Statement statement, ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testTable() {
		Connection conn = null;
		Statement usersStatement = null;
		ResultSet usersResultSet = null;

		conn = getConnection();
		try {
			usersStatement = conn.createStatement();
			// 初始化用户信息
			usersResultSet = usersStatement.executeQuery("select * from res_user");
			while (usersResultSet.next()) {
				System.out.println("user info:"+usersResultSet.getString(2));
			}
			System.out.println("DB OK...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, usersStatement, usersResultSet);
		}
	}

	
}
