package com.gift.action;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts2.interceptor.ServletRequestAware;import org.apache.struts2.interceptor.ServletResponseAware;import org.springframework.stereotype.Controller;import com.opensymphony.xwork2.ActionSupport;@Controllerpublic class BaseAction extends ActionSupport implements ServletRequestAware,		ServletResponseAware {	private static final long serialVersionUID = 1L;		private Integer cookieUid;		private String cookieUserName;		private String refer;			private boolean success = true;;		private String errMsg;		private HttpServletRequest request;	private HttpServletResponse response;	public void setServletRequest(HttpServletRequest request) {		this.request = request;	}	public void setServletResponse(HttpServletResponse response) {		this.response = response;	}	/**	 * @return the request	 */	public HttpServletRequest getRequest() {		return request;	}	/**	 * @return the response	 */	public HttpServletResponse getResponse() {		return response;	}	public void setCookieUid(Integer cookieUid) {		this.cookieUid = cookieUid;	}	public Integer getCookieUid() {		return cookieUid;	}	public void setCookieUserName(String cookieUserName) {		this.cookieUserName = cookieUserName;	}	public String getCookieUserName() {		return cookieUserName;	}	public void setRefer(String refer) {		this.refer = refer;	}	public String getRefer() {		return refer;	}	public void setSuccess(boolean success) {		this.success = success;	}	public boolean isSuccess() {		return success;	}	public void setErrMsg(String errMsg) {		this.errMsg = errMsg;	}	public String getErrMsg() {		return errMsg;	}}