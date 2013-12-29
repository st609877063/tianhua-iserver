package com.people.dptwb.manager.user;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.people.dptwb.common.UserValidator;
import com.people.dptwb.entity.FeLoginUser;
import com.people.dptwb.manager.TBaseManager;

public class UserLoginManager extends TBaseManager {
	private static final Logger logger = Logger
			.getLogger(UserLoginManager.class);

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param isRemember
	 * @param request
	 * @param response
	 * @return null：用户不存在
	 */
	public FeLoginUser doUserLogin(FeLoginUser user, boolean isRemember,
			HttpServletRequest request, HttpServletResponse response) {
		FeLoginUser loginUser = null;

		return loginUser;
	}

	/**
	 * 设置cookie
	 * 
	 * @param userId
	 * @param userName
	 * @param nickName
	 * @param isAdmin
	 * @param isRemember
	 * @throws UnsupportedEncodingException
	 */

	public void setUserCookie(String userId, String userName, String nickName,
			String accountId, String isAdmin, boolean isRemember,
			HttpServletRequest request, HttpServletResponse response) {
		UserValidator userValidator = new UserValidator();
		String[] userInfo = new String[] { userId, userName, nickName,
				accountId, isAdmin };
		userValidator.setSession(userInfo, request);
	}

	/**
	 * 用户退出程序，如果检查到
	 * 
	 * @param request
	 * @param response
	 */

	public void doLogout(HttpServletRequest request,
			HttpServletResponse response) {
		UserValidator userValidator = new UserValidator();
		logger.info("执行本地用户退出");
		userValidator.clearSession(request, response);
	}

}
