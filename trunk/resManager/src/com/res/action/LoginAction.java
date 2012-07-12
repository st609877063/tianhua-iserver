package com.res.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.res.bean.ResUser;
import com.res.manager.ResUserManager;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	
	
	public String tologin() throws Exception {
		return "success";
	}
	
	public String dologin() throws Exception {
		
		if(userName == null || userName.equals("") || password == null || password.equals("")) {
			return "failed";
		}
		
		ResUserManager userManager = new ResUserManager();
		ResUser vo = userManager.findResUserByNamePw(userName, password);
		if(vo == null) {
			return "failed";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			userManager.setUserCookie(String.valueOf(vo.getUserId()), vo.getUserName(), request, response);
		}
		
		return "success";
	}

	public String dologout() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ResUserManager userManager = new ResUserManager();
		userManager.doLogout(request, response);
		return "success";
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
}
