package com.people.dptwb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.people.dptwb.common.UserValidator;

/**
 * Action的基础类，继承于ActionSupport Description:
 * 包含cookieUid和cookieUsername，用于保存cookie中读取的信息
 * 
 */
@SuppressWarnings("serial")
public class TBaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private int cookieUid = -1;
	private String cookieUsername;
	private String cookieNickName;
	private String cookieAdminFlag;

	private String requestUrl;
	private String paramUrl;
	private String refer;
	private String pageTitle = "教育学部人事查询系统";
	private String pageType;
	private int index = 0;// 页面菜单级数
	public HttpServletRequest request;
	public HttpServletResponse response;

	protected void setCookieInfo() {
		UserValidator uv = new UserValidator();
		String requestUrl = request.getRequestURL().toString();
		String paramUrl = request.getQueryString();
		setRequestUrl(requestUrl);
		setParamUrl(paramUrl);
		 String[] userinfo = uv.getCookie(request);

		//String[] userinfo = uv.getSession(request);
		if (userinfo != null) {
			setCookieUid(Integer.parseInt(userinfo[0]));
			setCookieUsername(userinfo[1]);
			setCookieNickName(userinfo[2]);
		} else {
			setCookieUid(1);
			setCookieUsername("test");
			setCookieNickName("测试账号");
		}
	}


	/**
	 * @return the cookieAdminFlag
	 */
	public String getCookieAdminFlag() {
		return cookieAdminFlag;
	}

	/**
	 * @param cookieAdminFlag
	 *            the cookieAdminFlag to set
	 */
	public void setCookieAdminFlag(String cookieAdminFlag) {
		this.cookieAdminFlag = cookieAdminFlag;
	}

	/**
	 * @return the cookieUid
	 */
	public int getCookieUid() {
		return cookieUid;
	}

	/**
	 * @param cookieUid
	 *            the cookieUid to set
	 */
	public void setCookieUid(int uid) {
		this.cookieUid = uid;
	}

	/**
	 * @return the cookieUsername
	 */
	public String getCookieUsername() {
		return cookieUsername;
	}

	/**
	 * @param cookieUsername
	 *            the cookieUsername to set
	 */
	public void setCookieUsername(String username) {
		this.cookieUsername = username;
	}

	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * @param requestUrl
	 *            the requestUrl to set
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	/**
	 * @return the paramUrl
	 */
	public String getParamUrl() {
		return paramUrl;
	}

	/**
	 * @param paramUrl
	 *            the paramUrl to set
	 */
	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}


	/**
	 * @return the cookieNickName
	 */
	public String getCookieNickName() {
		return cookieNickName;
	}

	/**
	 * @param cookieNickName
	 *            the cookieNickName to set
	 */
	public void setCookieNickName(String cookieNickName) {
		this.cookieNickName = cookieNickName;
	}

}
