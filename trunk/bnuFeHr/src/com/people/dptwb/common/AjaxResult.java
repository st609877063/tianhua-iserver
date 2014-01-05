package com.people.dptwb.common;

import java.util.Map;

public class AjaxResult {

	private int status ;
	private String message ;
	private String redirectURL ;
	
	private Map model ;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setRedirectURL(String redirectURL) {
        /*
        if (redirectURL != null && redirectURL.startsWith("redirect:")) {
            this.redirectURL = redirectURL.substring(9);  // "redirect:".length() = 9
        } else {
            this.redirectURL = redirectURL;
        }
        */
        this.redirectURL = redirectURL;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map getModel() {
		return model;
	}
	public void setModel(Map model) {
		this.model = model;
	}
	
	public boolean isSuccess(){
		return status == AjaxUtil.AJAX_STATUS_SUCCESS ;
	}
	
}
