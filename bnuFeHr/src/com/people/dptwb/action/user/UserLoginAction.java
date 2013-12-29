package com.people.dptwb.action.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.people.dptwb.action.TBaseAction;
import com.people.dptwb.entity.FeLoginUser;
import com.people.dptwb.manager.user.UserLoginManager;

@SuppressWarnings("serial")
public class UserLoginAction extends TBaseAction {
	private static final Logger logger = Logger
			.getLogger(UserLoginAction.class);
	private String userName;
	private String password;
	private String errorMsg;

	public String toLogin() {
		logger.info("toLogin");
		// 如果没有登录则进行登录过程
		return SUCCESS;
	}

	/**
	 * 用户登录方法
	 */
	public String login() {
		// 检验用户名
		if (StringUtils.isBlank(userName)) {
			errorMsg = "用户名为空";
			return INPUT;
		}
		// 检验密码
		else if (StringUtils.isBlank(password)) {
			errorMsg = "密码为空";
			return INPUT;
		}

		if (userName.equals("admin") && password.equals("admin")) {

			FeLoginUser loginUser = new FeLoginUser();
			loginUser.setUserId(11111);
			loginUser.setUserName(userName);
			loginUser.setNickName(userName);
			loginUser.setAdmin(1);

			logger.info("用户名为：" + loginUser.getUserName() + "的用户，开始写入本地COOKIE");
			UserLoginManager manager = new UserLoginManager();
			manager.setUserCookie(String.valueOf(loginUser.getUserId()),
					loginUser.getUserName(), loginUser.getNickName(),
					String.valueOf(0), String.valueOf(loginUser.getAdmin()),
					false, request, response);

			return SUCCESS;
		} else {
			errorMsg = "用户名密码不匹配";
			return INPUT;
		}

	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	public String logout() {
		UserLoginManager userLoginManager = new UserLoginManager();
		userLoginManager.doLogout(request, response);
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
