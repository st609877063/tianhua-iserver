package com.res.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.res.action.BaseAction;
import com.res.tools.UserValidator;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {
	
	private static final Log LOG = LogFactory.getLog(AuthorityInterceptor.class);
		
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletResponse response = ServletActionContext.getResponse();
		
		LOG.debug(request.getRemoteAddr()+": enter interceptor");
		
		/*Pattern pattern = Pattern.compile("192.168(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){2}");
		Matcher matcher = pattern.matcher(request.getRemoteAddr());
		if(!matcher.matches()){
			return "input";
		}*/
			
		String[] userinfo = UserValidator.getCookie(request);
		
		//没有cookie信息
		if (userinfo == null) {
			LOG.debug("NOT LOGIN");
			String queryStr = request.getQueryString();
			String url = "";
			if(StringUtils.isBlank(queryStr)){
				url = request.getRequestURL().toString();
			}else{
				url = request.getRequestURL().append("?").append(queryStr).toString();
			}	
			String refer = URLEncoder.encode(url, "GBK");
			
			BaseAction action = (BaseAction)invocation.getAction();
			action.setRefer(refer);
			return Action.LOGIN;
			
		}else {
			LOG.debug("HAS LOGINED");
			BaseAction action = (BaseAction)invocation.getAction();
			
			//设置用户信息
			action.setCookieUid(Integer.parseInt(userinfo[0]));
			action.setCookieUserName(userinfo[1]);
			
			return invocation.invoke();
		}
		
	}
}
