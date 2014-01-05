package com.people.dptwb.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {
	public final static int AJAX_STATUS_FAIL = 1;

	public final static int AJAX_STATUS_SUCCESS = 0;

	/**
	 * 生成AJAX返回XML字符串的方法
	 * @param result
	 * @return
	 */
	private static String generateXml(AjaxResult result) {
		StringBuilder sb = new StringBuilder(256);

		sb.append("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
		sb.append("\n\n");

		sb.append("<result>\n");
		sb.append("\t<status id=\"statusCode\">");
		sb.append(result.getStatus());
		sb.append("</status>\n");

		sb.append("\t<message>");
		sb.append(result.getMessage());
		sb.append("</message>\n");

		sb.append("\t<redirect-url>");
		sb.append(result.getRedirectURL() == null ? "" : result
				.getRedirectURL());
		sb.append("</redirect-url>\n");
		sb.append("</result>");

		return sb.toString();
	}

	/**
	 * 生成处理成功的XML返回字符串
	 * @param msg 返回的消息
	 * @return
	 */
	public static String genFailXml(String msg) {
		return genXml(null, null, msg, null, AJAX_STATUS_FAIL);
	}

	/**
	 * 生成处理失败的XML返回字符串
	 * @param msg 返回的消息
	 * @return
	 */
	public static String genSuccessXml(String msg) {
		return genXml(null, null, msg, null, AJAX_STATUS_FAIL);
	}

	private static String genXml(HttpServletRequest request,
			HttpServletResponse response, String msg, String url, int status) {
		AjaxResult result = new AjaxResult();
		result.setMessage(msg);
		result.setStatus(status);
		if (request != null && url != null) {
			String ctxPath = request.getContextPath();

			if ("/".equals(ctxPath)) {
				ctxPath = "";
			}

			if (url.startsWith("redirect:/")) {
				// because of "redirect:".length() = 9
				result.setRedirectURL(ctxPath + url.substring(9));
			} else if (url.startsWith("/")) {
				result.setRedirectURL(ctxPath + url);
			} else {
				result.setRedirectURL(ctxPath + '/' + url);
			}
		}
		return generateXml(result);

	}
	
	private static AjaxResult genAjaxResult(HttpServletRequest request,
			HttpServletResponse response, String msg, String url, int status) {
		AjaxResult result = new AjaxResult();
		result.setMessage(msg);
		result.setStatus(status);
		return result;

	}
	
	/**
	 * 生成处理失败的XML返回字符串
	 * @param msg 返回的消息
	 * @return
	 */
	public static AjaxResult genFailResult(String msg) {
		return genAjaxResult(null, null, msg, null, AJAX_STATUS_FAIL);
	}

	/**
	 * 生成处理成功的XML返回字符串
	 * @param msg 返回的消息
	 * @return
	 */
	public static AjaxResult genSuccessResult(String msg) {
		return genAjaxResult(null, null, msg, null, AJAX_STATUS_SUCCESS);
	}
	
	
	public static String createXMLMessage(AjaxResult result) {
		StringBuffer sb = new StringBuffer(256);

		// sb.append(result.getStatus())
		// .append("###")
		// .append(result.getMessage())
		// .append("###")
		// .append(result.getRedirectURL() == null ? "" :
		// result.getRedirectURL()) ;

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		sb.append("\n\n");

		sb.append("<result>\n");
		sb.append("\t<status>");
		sb.append(result.getStatus());
		sb.append("</status>\n");

		sb.append("\t<message>");
		sb.append(result.getMessage());
		sb.append("</message>\n");

		sb.append("\t<redirect-url>");
		sb.append(result.getRedirectURL() == null ? "" : result
				.getRedirectURL());
		sb.append("</redirect-url>\n");
		sb.append("</result>");

		return sb.toString();
	}
	

	public static void sendFailXml(HttpServletResponse response, String msg)
			throws IOException {
		sendXml(null, response, msg, null, AJAX_STATUS_FAIL);
	}

	public static void sendFailXml(HttpServletRequest request,
			HttpServletResponse response, String msg, String url)
			throws IOException {
		sendXml(request, response, msg, url, AJAX_STATUS_FAIL);
	}

	public static void sendSuccessXml(HttpServletResponse response, String msg)
			throws IOException {
		sendSuccessXml(null, response, msg, null);
	}

	public static void sendSuccessXml(HttpServletRequest request,
			HttpServletResponse response, String msg, String url)
			throws IOException {
		sendXml(request, response, msg, url, AJAX_STATUS_SUCCESS);
	}

	/**
	 * @param request
	 * @param response
	 * @param msg
	 * @param url
	 * @param status
	 *            TODO
	 * @throws IOException
	 */
	private static void sendXml(HttpServletRequest request,
			HttpServletResponse response, String msg, String url, int status)
			throws IOException {
		AjaxResult result = new AjaxResult();
		result.setMessage(msg);
		result.setStatus(status);
		if (request != null && url != null) {
			String ctxPath = request.getContextPath();

			if ("/".equals(ctxPath)) {
				ctxPath = "";
			}

			if (url.startsWith("redirect:/")) {
				// because of "redirect:".length() = 9
				result.setRedirectURL(ctxPath + url.substring(9));
			} else if (url.startsWith("/")) {
				result.setRedirectURL(ctxPath + url);
			} else {
				result.setRedirectURL(ctxPath + '/' + url);
			}
		}

		sendXml(request, response, createXMLMessage(result));
	}

	public static void sendHtml(HttpServletRequest request,
			HttpServletResponse response, String html) throws IOException {
		response.setContentType("text/html; charset=UTF-8");

		response.getWriter().print(html);
		response.flushBuffer();
	}

	public static void sendText(HttpServletRequest request,
			HttpServletResponse response, String text) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		response.getWriter().print(text);
		response.flushBuffer();
	}

	public static void sendXml(HttpServletRequest request,
			HttpServletResponse response, String xml) throws IOException {
		response.setContentType("text/xml; charset=UTF-8");

		response.getWriter().print(xml);
		response.flushBuffer();
	}

}
