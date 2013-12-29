package com.people.dptwb.common;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.people.dptwb.constant.Constant;
import com.people.dptwb.tools.PeopleTCoderUtil;

public class UserValidator {

	private static final Logger logger = Logger.getLogger(UserValidator.class);

	/**
	 * 从COOKIE中获取用户数据
	 * 
	 * @param request
	 * @return 信息数组，5个数据；0：userid；1：username；2：nickname; 3：accountid; 4：isadmin
	 */
	public String[] getCookie(HttpServletRequest request) {
		String[] userinfo = null;
		String uid = null;
		String username = null;
		String nickName = null;
		String accountId = null;
		String isAdmin = null;

		PeopleTCoderUtil coder = new PeopleTCoderUtil();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];

				if (!cookie.getName().startsWith(Constant.COOKIE_STARTWITH_STRING)) {//固定加密串的前部分字符
					continue;
				}
				if (Constant.BNUFE_COOKIE_UID
						.equals(coder.decrypt(cookie.getName()))) {
					uid = cookie.getValue();
				}
				if (Constant.BNUFE_COOKIE_USERNAME.equals(coder.decrypt(cookie
						.getName()))) {
					username = cookie.getValue();
				}
				if (Constant.BNUFE_COOKIE_NICKNAME.equals(coder.decrypt(cookie
						.getName()))) {
					nickName = cookie.getValue();
				}
				if (Constant.BNUFE_COOKIE_ACCOUNTID.equals(coder.decrypt(cookie
						.getName()))) {
					accountId = cookie.getValue();
				}
				if (Constant.BNUFE_COOKIE_ISADMIN.equals(coder.decrypt(cookie
						.getName()))) {
					isAdmin = cookie.getValue();
				}
			}

			if ((StringUtils.isNotBlank(uid)) && (StringUtils.isNotBlank(username))
					&& (StringUtils.isNotBlank(nickName))
					&& (StringUtils.isNotBlank(accountId))
					&& (StringUtils.isNotBlank(isAdmin))) {

				userinfo = new String[5];
				//解密
				userinfo[0] = coder.decrypt(uid);
				userinfo[1] = coder.decrypt(username);
				String decodeStr = coder.decrypt(nickName);
				try {
					userinfo[2] = new String(new BASE64Decoder().decodeBuffer(decodeStr), "UTF-8");
				} catch (IOException e) {
					logger.error("对COOKIE中笔名进行解码时出现异常", e);
				}
				userinfo[3] = coder.decrypt(accountId);
				userinfo[4] = coder.decrypt(isAdmin);
			}
		}
		return userinfo;
	}
	
	
	public String[] getSession(HttpServletRequest request) {
		String[] userinfo = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			SessionUserInfo sessionUser = (SessionUserInfo) session.getAttribute(Constant.SESSION_BNUFE_CONSTANT);
			if (sessionUser != null) {
				userinfo = new String[5];
				userinfo[0] = String.valueOf(sessionUser.getUserId());
				userinfo[1] = sessionUser.getUserName();
				userinfo[2] = sessionUser.getNickName();
				userinfo[3] = String.valueOf(sessionUser.getAccountId());
				userinfo[4] = String.valueOf(sessionUser.getIsAdmin());
			}
				
				
//			nickName = userinfo[2];
//			try {
//				userinfo[2] = new String(new BASE64Decoder().decodeBuffer(nickName), "UTF-8");
//			} catch (IOException e) {
//				logger.error("对COOKIE中笔名进行解码时出现异常", e);
//			}
		}
		return userinfo;
	}
	

	//开发时使用，假设cookie已存在

	//	public String[] getCookie(HttpServletRequest request) {
	//		String[] userinfo = new String[3];
	//		
	//		userinfo[0] = "1";
	//		userinfo[1] = "12345678901";
	//		userinfo[2] = "0";
	//		return userinfo;
	//	}

	/**
	 * 
	 * @param userinfo
	 *            信息数组，5个数据；0：userid；1：username；2：nickname; 3：accountid;
	 *            4：isadmin
	 * @param isRemember
	 * @param response
	 */
	public void setCookie(String[] userinfo, boolean isRemember,
			HttpServletResponse response) {
		/** 加密 */
		PeopleTCoderUtil coder = new PeopleTCoderUtil();
		userinfo[0] = coder.encrypt(userinfo[0]);
		userinfo[1] = coder.encrypt(userinfo[1]);
		String encodeStr = null;
		try {
			encodeStr = new BASE64Encoder().encode(userinfo[2]
					.getBytes("UTF-8"));
		} catch (Exception e) {
			logger.error("对笔名进行UTF8编码时出现异常", e);
		}
		
		userinfo[2] = coder.encrypt(encodeStr);
		userinfo[3] = coder.encrypt(userinfo[3]);
		userinfo[4] = coder.encrypt(userinfo[4]);

		Cookie uidCookie = new Cookie(coder.encrypt(Constant.BNUFE_COOKIE_UID),
				userinfo[0]);
		Cookie nameCookie = new Cookie(
				coder.encrypt(Constant.BNUFE_COOKIE_USERNAME), userinfo[1]);
		Cookie nickNameCookie = new Cookie(
				coder.encrypt(Constant.BNUFE_COOKIE_NICKNAME), userinfo[2]);
		Cookie accountIdCookie = new Cookie(
				coder.encrypt(Constant.BNUFE_COOKIE_ACCOUNTID), userinfo[3]);
		Cookie isAdminCookie = new Cookie(
				coder.encrypt(Constant.BNUFE_COOKIE_ISADMIN), userinfo[4]);
		int cookieExpiry = 60 * 60 * 24; //cookie生命期，一天
		if (isRemember) {
			uidCookie.setMaxAge(cookieExpiry);
			nameCookie.setMaxAge(cookieExpiry);
			nickNameCookie.setMaxAge(cookieExpiry);
			accountIdCookie.setMaxAge(cookieExpiry);
			isAdminCookie.setMaxAge(cookieExpiry);
		}

		response.addCookie(uidCookie);
		response.addCookie(nameCookie);
		response.addCookie(nickNameCookie);
		response.addCookie(accountIdCookie);
		response.addCookie(isAdminCookie);
		
		response.setHeader( "Pragma", "no-cache" );
	    response.addHeader( "Cache-Control", "must-revalidate" );
	    response.addHeader( "Cache-Control", "no-cache" );
	    response.addHeader( "Cache-Control", "no-store" );
	    response.setDateHeader("Expires", 0);
	}
	
	
	public void setSession(String[] userinfo, HttpServletRequest request) {
		if (userinfo != null && userinfo.length == 5) {
			HttpSession session = request.getSession(true);
			
			
			SessionUserInfo sessionUser = new SessionUserInfo();
			sessionUser.setUserId(Integer.parseInt(userinfo[0]));
			sessionUser.setUserName(userinfo[1]);
			sessionUser.setNickName(userinfo[2]);
			sessionUser.setAccountId(Integer.parseInt(userinfo[3]));
			sessionUser.setIsAdmin(Integer.parseInt(userinfo[4]));
			
			
			session.setAttribute(Constant.SESSION_BNUFE_CONSTANT, sessionUser);
			
		}
		
//		String encodeStr = null;
//		try {
//			encodeStr = new BASE64Encoder().encode(userinfo[2]
//					.getBytes("UTF-8"));
//		} catch (Exception e) {
//			logger.error("对笔名进行UTF8编码时出现异常", e);
//		}
//		userinfo[2] = encodeStr;
		
		
	}
	

	/**
	 * 清除cookie信息
	 */
	public void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		PeopleTCoderUtil coder = new PeopleTCoderUtil();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (!cookie.getName().startsWith(Constant.COOKIE_STARTWITH_STRING)) {
					continue;
				}
				if (Constant.BNUFE_COOKIE_UID
						.equals(coder.decrypt(cookie.getName()))) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
				if (Constant.BNUFE_COOKIE_USERNAME.equals(coder.decrypt(cookie
						.getName()))) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
				if (Constant.BNUFE_COOKIE_NICKNAME.equals(coder.decrypt(cookie
						.getName()))) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
				if (Constant.BNUFE_COOKIE_ACCOUNTID.equals(coder.decrypt(cookie
						.getName()))) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
				if (Constant.BNUFE_COOKIE_ISADMIN.equals(coder.decrypt(cookie
						.getName()))) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					continue;
				}
			}
		}
	}
	
	
	public void clearSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
//			System.out.println("sessionID1...."+session.getId());
		session.removeAttribute(Constant.SESSION_BNUFE_CONSTANT);
//			System.out.println("sessionID2...."+session.getId());
//			session.invalidate();
//			System.out.println("sessionID3...."+session.getId());
		
		}
	}
	

	/**
	 * 添加一个cookie信息
	 * 
	 * @param name
	 * @param value    如果是中文等字符，调用前需进行base64编码
	 * @param response
	 * @param expiryTime   cookie有效期，秒数  >0则设置
	 */
	public static void setCookieByName(String name, String value,
			HttpServletResponse response, int expiryTime) {
		PeopleTCoderUtil coder = new PeopleTCoderUtil();
		name = coder.encrypt(name);
		value = coder.encrypt(value);
		Cookie cookie = new Cookie(name, value);
		if (expiryTime > 0) {
			cookie.setMaxAge(expiryTime);
		}
		response.addCookie(cookie);
	}
	
	
	/**
	 * 更新公共的SESSION对象，即userinfo[]
	 * @param seq   更新内容的序号；0：userId; 1:username; 2: nickname; 3: accountId; 4: isadmin
	 * @param value   更新的值
	 * @param request
	 */
	public void setCommonSessionArrayBySeq(int seq, String value,
			HttpServletRequest request) {
		String[] userinfo = this.getSession(request);
		if (userinfo != null && userinfo.length == 5) {
//			if (seq == 2) {
//				// 更新用户昵称
//				String encodeStr = null;
//				try {
//					encodeStr = new BASE64Encoder().encode(value
//							.getBytes("UTF-8"));
//				} catch (Exception e) {
//					logger.error("对笔名进行UTF8编码时出现异常", e);
//				}
//				userinfo[2] = encodeStr; 
//			}
//			else {
				userinfo[seq] = value;
//			}
			this.setSession(userinfo, request);
		}
	}
	
	
	/**
	 * 添加指定名字的SESSION信息，此SESSION不写入用户基本信息SESSION，即userinfo[]
	 * 
	 * @param name
	 * @param value    如果是中文等字符，该对象必须实现serializeble接口
	 * @param request
	 */
	public void setSessionByName(String name, Object value,
			HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(name, value);
	}
	

	/**
	 * 获得一个cookie值
	 * 
	 * @param name
	 * @param request
	 * @return  如果是中文等，获取结果后，请自行进行base64解码
	 */
	public static String getCookieByName(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String value = "";
		if (cookies != null) {
			PeopleTCoderUtil coder = new PeopleTCoderUtil();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (name.equals(coder.decrypt(cookie.getName()))) {
					value = coder.decrypt(cookie.getValue());
					break;
				}
			}
		}
		return value;
	}
	
	
	/**
	 * 获取指定名字的SESSION
	 * @param name
	 * @param request
	 * @return
	 */
	public Object getSessionByName(String name, HttpServletRequest request) {
		Object reObj = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			reObj = session.getAttribute(name);
		}
		return reObj;
	}
	

	/**
	 * 删除一个cookie
	 * 
	 * @param name
	 * @param request
	 * @param response
	 */
//	public static void deleteCookieByName(String name, HttpServletRequest request,
//			HttpServletResponse response) {
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			PeopleTCoderUtil coder = new PeopleTCoderUtil();
//			for (int i = 0; i < cookies.length; i++) {
//				Cookie cookie = cookies[i];
//				if (name.equals(coder.decrypt(cookie.getName()))) {
//					cookie.setMaxAge(0);
//					response.addCookie(cookie);
//					break;
//				}
//			}
//		}
//	}
	
	/**
	 * 清除一个指定名字的SESSION
	 * @param name
	 * @param request
	 */
	public void clearSessionByName(String name, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(name);
		}
	}

}
