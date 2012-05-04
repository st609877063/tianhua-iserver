package com.platform.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.platform.service.LogService;

@Controller
@RequestMapping("/log")
public class LogController {
	
	private LogService logService;
	@RequestMapping(method = RequestMethod.GET)
	public void getLogs(HttpServletRequest request , HttpServletResponse response,Model model) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		//System.out.println(startRow+":"+pagesize);
		try {
			writer = response.getWriter();
			String str = logService.getPageLogs(startRow, pagesize,sortorder);
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
