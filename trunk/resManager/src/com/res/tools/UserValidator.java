package com.res.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserValidator {
	
	private static final Log LOG = LogFactory.getLog(UserValidator.class);
	private static final String COOKIE_UID = "res_user_uid";
	private static final String COOKIE_USERNAME = "res_user_username";
	/**
	 * 获取用户COOKIE
	 * @return
	 */
	
	public static String[] getCookie(HttpServletRequest request) {
		String[] userinfo = null;
		String uid = null;
		String username = null;


		PeopleCoderUtil coder = new PeopleCoderUtil();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (COOKIE_UID.equals(cookie.getName())) {
					uid = cookie.getValue();
				}
				if (COOKIE_USERNAME.equals(cookie.getName())) {
					username = cookie.getValue();
				}
			}
			
			if ((uid != null && !"".equals(uid)) && (username != null && 
					!"".equals(username)) ) {
				userinfo = new String[2];
				//解密
				userinfo[0] = coder.decrypt(uid);
				userinfo[1] = coder.decrypt(username);	
			}
		}
		return userinfo;
	} 
	
	/**
	 * 设置cookie信息
	 * @param userinfo
	 */
	public static void setCookie(String[] userinfo, HttpServletResponse response) {
		/**加密*/
		PeopleCoderUtil coder = new PeopleCoderUtil();
		userinfo[0] = coder.encrypt(userinfo[0]);
		userinfo[1] = coder.encrypt(userinfo[1]);

		Cookie uidCookie = new Cookie(COOKIE_UID, userinfo[0]);
		Cookie nameCookie = new Cookie(COOKIE_USERNAME, userinfo[1]);
//		int cookieExpiry = 60*30;   //cookie生命期
//		
//		uidCookie.setMaxAge(cookieExpiry);
//		nameCookie.setMaxAge(cookieExpiry);
//		nickNameCookie.setMaxAge(cookieExpiry);
//		isAdminCookie.setMaxAge(cookieExpiry);
		
		response.addCookie(uidCookie);
		response.addCookie(nameCookie);
	}
	
	/**
	 * 清除cookie信息
	 */
	public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (COOKIE_UID.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
				if (COOKIE_USERNAME.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
			}
		}
	}

}
