package com.people.dptwb.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionManager {
	private static final Logger logger = Logger
			.getLogger(ConnectionManager.class);

	private static DataSource ds;
	private static DataSource dsread;
	private static ConnectionManager instance;
	
	private static final boolean isLocal = true;

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
	 * 
	 * @param ds
	 *            查询
	 * @param dsupdate
	 *            更新
	 * @param dssearch
	 *            更新
	 * @throws Exception
	 * */
	public static synchronized void initial() throws Exception {
		if(isLocal) {
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

			if (dsread == null) {
				try {
					Context initCtx = new InitialContext();
					Context envCtx = (Context) initCtx.lookup("java:comp/env");
					// 从Context中lookup数据源。
					dsread = (DataSource) envCtx.lookup("jdbc/query");
				} catch (Exception e) {
					logger.error("获取jdbc/read数据源时出现异常", e);
					throw e;
				}
			}
		}
	}

	/**
	 * 获取更新连接
	 * 
	 * @category 原链接 可读可写
	 * */
	public synchronized static Connection getConnection() {
		Connection conn = null;
		
		if(isLocal) {
			try {
				conn = ds.getConnection();
			} catch (Exception e) {
				logger.error("获取main连接时出现异常！", e);
			}
		} else {
			try {
				String username = "52m055zjky";
				String password = "z5xmlyzi24wwwzmlwj20iwhyml4zmyhllz14042k";
				String driver = "com.mysql.jdbc.Driver";
				String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_bnufe";//使用主库的域名
				Class.forName(driver).newInstance();
				Connection con = DriverManager.getConnection(url, username,
						password);

			} catch (Exception e) {
				logger.error("SAE获取main连接时出现异常！", e);
			}
		}
		
		return conn;
	}

	public synchronized static Connection getReadConnection() {
		Connection conn = null;
		
		if(isLocal) {
			try {
				conn = dsread.getConnection();
			} catch (Exception e) {
				logger.error("获取read连接时出现异常！", e);
			}
		} else {
			try {
				String username = "52m055zjky";
				String password = "z5xmlyzi24wwwzmlwj20iwhyml4zmyhllz14042k";
				String driver = "com.mysql.jdbc.Driver";
				String url="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_bnufe";//使用主库的域名
				Class.forName(driver).newInstance();
				Connection con = DriverManager.getConnection(url, username,
						password);
			} catch (Exception e) {
				logger.error("SAE获取main连接时出现异常！", e);
			}
		} 
		return conn;
	}
	// SAE使用
	// 用 户 名 : accesskey(52m055zjky)
	// 密　　码 : secretkey(z5xmlyzi24wwwzmlwj20iwhyml4zmyhllz14042k)
	// 主库域名 : w.rdc.sae.sina.com.cn
	// 从库域名 : r.rdc.sae.sina.com.cn
	// 端　　口 : 3307
	// 数据库名 : app_appname(app_bnufe)
}
