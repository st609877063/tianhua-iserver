/**
 * AjaxCookieInfo.java
 * description: 
 * microblog
 * author: mayq
 * 2009-11-20
 */
package com.people.dptwb.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.people.dptwb.common.UserValidator;

public class AjaxCookieInfo {
	
	public String[] getCookieInfo() {
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		UserValidator uv = new UserValidator();
		String[] cookieInfo = uv.getCookie(request);
		//String[] cookieInfo = uv.getSession(request);

		if(cookieInfo == null){
			return null;
		} else{
			//cookie存在。其他情况处理
		}
		return cookieInfo;
	}
	
	
	public String getClientAddr() {
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		String remoteIp = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(remoteIp)) {
			remoteIp = request.getRemoteAddr();
		}
		return remoteIp;
	}
	
	public WebContext getWebContext(){
		return  WebContextFactory.get();
	}
	
	public HttpServletRequest getHttpServletRequest(){
		return getWebContext().getHttpServletRequest();
	}
	
	public HttpServletResponse getHttpServletResponse(){
		return getWebContext().getHttpServletResponse();
	}
}
