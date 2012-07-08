package com.res.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 初始化数据连接管理类
 */
public class InitDBFactoryServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(InitDBFactoryServlet.class);
	private static final long serialVersionUID = 1L;

	public InitDBFactoryServlet() {
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
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		String path = this.getServletContext().getRealPath("/")+"\\WEB-INF\\classes\\";

		Properties jp = new Properties();
		Properties cp = new Properties();
		try {
			jp.load(new FileInputStream(new File(path + "jdbc.properties")));
			cp.load(new FileInputStream(new File(path + "config.properties")));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		
		
		ConnectionManager2.testTable();
	}

}
