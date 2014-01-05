package com.people.dptwb.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.people.dptwb.common.UserValidator;

@SuppressWarnings("serial")
public class LoginRegInterceptor extends AbstractInterceptor {

	private static final Logger logger = Logger.getLogger(LoginRegInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			UserValidator uv = new UserValidator();
			String[] userinfo = uv.getCookie(request);
			
			//String[] userinfo = uv.getSession(request);

			if (userinfo != null && userinfo[0] != null && Integer.parseInt(userinfo[0]) > 0) {
				logger.info("有本地用户COOKIE，用户已登录");
				return "haslogin";
			}
		} catch (Exception e) {
			logger.error("注册登录拦截器出现异常，", e);
		}
		return invocation.invoke();
	}

}
