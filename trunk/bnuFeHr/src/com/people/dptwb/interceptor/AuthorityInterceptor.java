package com.people.dptwb.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.people.dptwb.action.TBaseAction;
import com.people.dptwb.common.UserValidator;


@SuppressWarnings("serial")
/**
 * COOKIE登录状态验证拦截器
 */
public class AuthorityInterceptor extends AbstractInterceptor {
	
	private static final Logger logger = Logger.getLogger(AuthorityInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserValidator uv = new UserValidator();
		
		String clientIp = ValidClientAddr.getRealRemoteAddr(request);
		
		String[] userinfo = uv.getCookie(request);
		//String[] userinfo = uv.getSession(request);
		
		//没有cookie信息
		if (userinfo == null || userinfo[0] == null || Integer.parseInt(userinfo[0]) <= 0) {
			
			String queryStr = request.getQueryString();
			String url = "";
			if(StringUtils.isBlank(queryStr)){
				url = request.getRequestURL().toString();
			}else{
				url = request.getRequestURL().append("?").append(queryStr).toString();
			}	
			String refer = URLEncoder.encode(url, "UTF-8");
			
			TBaseAction action = (TBaseAction)invocation.getAction();
			action.setRefer(refer);
			return Action.LOGIN;
			
		} else {
			TBaseAction action = (TBaseAction)invocation.getAction();
			//设置用户信息
			action.setCookieUid(Integer.parseInt(userinfo[0]));
			action.setCookieUsername(userinfo[1]);
			action.setCookieNickName(userinfo[2]);
			action.setCookieAdminFlag(userinfo[4]);
			
			return invocation.invoke();
		}
	
		
	}
	
}
