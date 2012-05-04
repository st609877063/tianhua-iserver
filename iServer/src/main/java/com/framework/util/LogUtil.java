package com.framework.util;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.domain.Logs;
import com.platform.filter.SessionFilter;
import com.platform.service.LogService;

public class LogUtil implements AfterReturningAdvice {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void afterReturning(Object arg0, Method m, Object[] args,
			Object target) throws Throwable {

		HttpSession session = SessionFilter.session.get();
//		if(session==null||"".equals(session.getAttribute("username"))){
//			throw new CloudException("无访问权限,请进行其他操作!");
//		}
		System.out.println("---------------------------- 开始记录日志");
		if(session==null) return;
		String username = (String)session.getAttribute("username");
		Logs log = new Logs();
		log.setUsername(username);
		log.setType("操作日志");
		log.setMessage(m.getName());
		log.setCreateDate(DateTime.getCurrentDatetimeByString());
		ServletContext servletContext = session.getServletContext();    
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		LogService logService= (LogService) ctx.getBean("logService");
		logService.saveLog(log);
		System.out.println("----------------------------  结束记录日志");
		logger.info("使用人: " + username + " ===> 创建时间: "
				+ DateTime.getCurrentDateByString() + " ===> 执行方法: " + m.getName());
	}


}