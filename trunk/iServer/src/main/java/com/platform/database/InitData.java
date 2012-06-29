package com.platform.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class InitData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(InitData.class.getName());
	/**
	 * Constructor of the object.
	 */
	public InitData() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		String path = this.getServletContext().getRealPath("/")+"\\WEB-INF\\classes\\";

		Properties jp = new Properties();
		Properties cp = new Properties();
		try {
			jp.load(new FileInputStream(new File(path + "jdbc.properties")));
			cp.load(new FileInputStream(new File(path + "config.properties")));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GlobalVariables.driverClass = jp.getProperty("jdbc.driverClass");
		GlobalVariables.jdbcUrl = jp.getProperty("jdbc.jdbcUrl");
		GlobalVariables.user = jp.getProperty("jdbc.user");
		GlobalVariables.password = jp.getProperty("jdbc.password");
		GlobalVariables.uri = this.getServletContext().getRealPath("/");
		GlobalVariables.backupLocation = cp.getProperty("backupLocation");
		GlobalVariables.fileLocation = cp.getProperty("fileLocation");
		GlobalVariables.urlLocation = cp.getProperty("urlLocation");
		GlobalVariables.thumbLocation = cp.getProperty("thumbLocation");
		GlobalVariables.serverName = cp.getProperty("serverName");
		GlobalVariables.serverLink = cp.getProperty("serverLink"); 
		System.out.println("jdbcUrl==="+GlobalVariables.jdbcUrl);
		ControlDatabase.init(GlobalVariables.driverClass,GlobalVariables.jdbcUrl,GlobalVariables.user,GlobalVariables.password);
		logger.info("database initialization success!");
	}

}
