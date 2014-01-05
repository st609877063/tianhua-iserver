package com.people.dptwb.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.people.dptwb.common.UserValidator;

public class TBaseAjax {

	private int cookieAccountId;
	private int cookieUid = -1;
	private String cookieUsername;
	private String cookieNickName;

	private WebContext webContext;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String clientAddr;

	protected TBaseAjax() {
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		HttpServletResponse response = ctx.getHttpServletResponse();
		setWebContext(ctx);
		setRequest(request);
		setResponse(response);
		// UserValidator uv = new UserValidator();
		// setClientAddr(uv.getRealRemoteAddr(request));

		String[] cookieInfo = getCookieInfo();
		if (cookieInfo != null) {
			setCookieUid(Integer.parseInt(cookieInfo[0]));
			setCookieUsername(cookieInfo[1]);
			setCookieNickName(cookieInfo[2]);
			setCookieAccountId(Integer.parseInt(cookieInfo[3]));
		}
	}

	protected String[] getCookieInfo() {
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		UserValidator uv = new UserValidator();
		String[] info = uv.getCookie(request);
		//String[] info = uv.getSession(request);
		return info;
	}

	public int getCookieAccountId() {
		return cookieAccountId;
	}

	public void setCookieAccountId(int cookieAccountId) {
		this.cookieAccountId = cookieAccountId;
	}

	public int getCookieUid() {
		return cookieUid;
	}

	public void setCookieUid(int cookieUid) {
		this.cookieUid = cookieUid;
	}

	public String getCookieUsername() {
		return cookieUsername;
	}

	public void setCookieUsername(String cookieUsername) {
		this.cookieUsername = cookieUsername;
	}

	public String getCookieNickName() {
		return cookieNickName;
	}

	public void setCookieNickName(String cookieNickName) {
		this.cookieNickName = cookieNickName;
	}

	public WebContext getWebContext() {
		return webContext;
	}

	public void setWebContext(WebContext webContext) {
		this.webContext = webContext;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

}
