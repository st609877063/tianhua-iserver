package com.res.server;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionManager {
	private static final Logger logger = Logger.getLogger(ConnectionManager.class);

	private static DataSource ds;

	private static ConnectionManager instance;

	/**
	 * 外部调用数据源初始化
	 * */
	public synchronized static ConnectionManager getInstance() {
		if (instance == null) {
			try {
				ConnectionManager.initial();
			} catch (Exception e) {
				logger.fatal("初始化连接池时出现异常", e);
			}
			instance = new ConnectionManager();
			
		}
		return instance;
	}

	private ConnectionManager() {

	}

	/**
	 * 初始化数据源
	 * */
	public static synchronized void initial() throws Exception {
		if (ds == null) {
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				// 从Context中lookup数据源。
				ds = (DataSource) envCtx.lookup("jdbc/query");
			} catch (Exception e) {
				logger.error("获取jdbc/query数据源时出现异常", e);
				throw e;
			}
		}
	}

	/**
	 * 获取连接
	 * */
	public synchronized static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			logger.error("获取main连接时出现异常！", e);
			// System.out.println("获取连接时出现异常！");
			// e.printStackTrace();
		}
		return conn;
	}

}
