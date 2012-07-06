package com.gift.action.gift_user;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_user;
import com.gift.common.CommonConstant;
import com.gift.service.Gift_user_loginService;

@Controller
@Scope("prototype")
public class Gift_userLoginAction extends BaseAction{

	private static final long serialVersionUID = 5745791743140349695L;
	
	@Resource(name = "gift_user_loginServiceImpl")
	private Gift_user_loginService service;
	
	private String userName;

	private String password;
	
	private boolean loginFail = false;
	
	private String errMsg;
	
	public String toLogin() {
		return SUCCESS;
	}


	/**
	 * 登录方法
	 * @return
	 */
	public String login(){
		
		if(StringUtils.isBlank(userName)){
			loginFail = true;
			errMsg = "用户名不能为空!";
		}else if(StringUtils.isBlank(password)){		
			loginFail = true;
			errMsg = "密码不能为空!";		
		}
		
		LOG.debug(userName+"尝试登录");
			
		Gift_user user = new Gift_user();
		user.setUser_name(userName);
		user.setPassword(password);	
		
		int loginResult = service.doLogin(user, getRequest(), getResponse());
		if(loginResult == CommonConstant.LOGIN_FAIL){
			LOG.debug("登录失败");
			loginFail = true;
			errMsg = "用户名或密码错误!";
			return INPUT;
		}
		
		LOG.debug(userName+"登录成功");

		return SUCCESS;	
	}
	
	/**
	 * 退出方法
	 * @return
	 */
	public String logout(){
		service.doLogout(getRequest(), getResponse());
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

	public void setLoginFail(boolean loginFail) {
		this.loginFail = loginFail;
	}

	public boolean isLoginFail() {
		return loginFail;
	}


	public void setService(Gift_user_loginService service) {
		this.service = service;
	}


	public Gift_user_loginService getService() {
		return service;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public String getErrMsg() {
		return errMsg;
	}


}
