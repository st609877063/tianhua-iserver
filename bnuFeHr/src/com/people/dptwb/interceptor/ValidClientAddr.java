package com.people.dptwb.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ValidClientAddr {
	private static final Logger logger = Logger.getLogger(ValidClientAddr.class);
	/**
	 * 获取真实的客户端IP
	 * 为适应nginx+tomcat修改
	 * @param request
	 * @return
	 */
	public static String getRealRemoteAddr(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String remoteIp = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(remoteIp)) {
			remoteIp = request.getRemoteAddr();
		}
		return remoteIp;
	}

}
