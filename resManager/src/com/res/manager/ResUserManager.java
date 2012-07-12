package com.res.manager;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.res.bean.ResUser;
import com.res.server.ConnectionManager2;
import com.res.tools.UserValidator;

public class ResUserManager{

	private static final Logger logger = Logger.getLogger(ResUserManager.class);

	public ResUser findResUserByNamePw(String name, String pw) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResUser userVo = null;
		String sql = " select * from res_user where user_name ='"+name+"' and password='"+pw+"'";
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				userVo = new ResUser();
				userVo.setUserId(resultSet.getInt("user_id"));
				userVo.setUserName(resultSet.getString("user_name"));
			}
		} catch (Exception e) {
			logger.error("findResUserByNamePw() error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return userVo;
	}
	
	
	public void setUserCookie(String userId, String userName, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		UserValidator userValidator = new UserValidator();
		String[] userInfo = new String[] { userId, userName};
		userValidator.setCookie(userInfo, response);
	}
	
	/**
	 * 退出程序，清楚cookie信息
	 */
	public void doLogout(HttpServletRequest request, HttpServletResponse response) {
		UserValidator userValidator = new UserValidator();
		userValidator.clearCookie(request, response);
	}
	
}
