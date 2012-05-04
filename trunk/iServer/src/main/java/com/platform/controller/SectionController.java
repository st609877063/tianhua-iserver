package com.platform.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.platform.domain.Magazine;
import com.platform.domain.Section;
import com.platform.service.ArticleService;
import com.platform.service.MagazineService;
import com.platform.service.SectionService;
import com.platform.service.UserService;

@Controller
@RequestMapping("/section")
public class SectionController {
	
	MagazineService magazineService;
	SectionService sectionService ;
	ArticleService articleService ;
	UserService userService ;
	
	@RequestMapping(method = RequestMethod.GET)
	public void getSections(HttpServletRequest request ,HttpServletResponse response,Section section) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		String magazineId = request.getParameter("magazineId");
		try {
			writer = response.getWriter();
			String str = sectionService.getSections(magazineId,startRow, pagesize,sortorder);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value ="/iphone",method = RequestMethod.GET)
	public void getIphoneSections(HttpServletRequest request ,HttpServletResponse response,Section section) {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		String magazineId = request.getParameter("magazineId");
		try {
			writer = response.getWriter();
			String str = sectionService.getIphoneSections(magazineId,startRow, pagesize,sortorder);
			System.out.println(str);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/name",method = RequestMethod.GET)
	public void getSectionNames(HttpServletRequest request ,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			String magazineId = request.getParameter("magazineId");
			sb.append("<select style=\"width:100px;\" id=\"sectionName\" name=\"sectionName\" size=\"1\">");
			List<?> list = sectionService.getSectionList(magazineId);
			for(int i =0;i<list.size();i++){
				Section section = (Section) list.get(i);
				sb.append("<option  value="+section.getSectionId()+">");
				sb.append(section.getSectionName());
				sb.append("</option>");
			}
			sb.append("</select>");
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveSection(HttpServletRequest request,HttpServletResponse response,Section section) throws JSONException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		boolean flag = false;
		String magazineId = request.getParameter("magazineId");
		Magazine magazine = magazineService.getMagazine(magazineId);
		section.setMagazine(magazine);
		flag = sectionService.saveSection(section);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",flag);
		if(flag){
			json.put("msg","添加栏目成功");
		}else{
			json.put("msg","添加栏目失败,请重新添加");
		}
		writer.print(json);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteSection(HttpServletRequest request,HttpServletResponse response,Section section) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		String rows=(String)request.getParameter("rowid");
		String[] rowids=rows.split(",");
		boolean flag=false;
		for(int i=0;i<rowids.length;i++){
			if(rowids[i]!=null&&!"".equals(rowids[i])){
			//flag=articleService.removeArticles(rowids[i]);
			flag = sectionService.removeSection(section, rowids[i]);
			}
		}
		JSONObject json = new JSONObject();
		json.put("success",flag);
		if(flag){
			json.put("msg","删除栏目成功");
		}else{
			json.put("msg","删除栏目失败,请重新删除");
		}
		writer.print(json);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateSection(HttpServletRequest request,HttpServletResponse response,Section section) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		boolean changeOK =false;
		String magazineId = request.getParameter("magazineId");
		Magazine magazine = magazineService.getMagazine(magazineId);
		section.setMagazine(magazine);
		changeOK = sectionService.updateSection(section);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",changeOK);
		if(changeOK){
			json.put("msg","修改栏目成功");
		}else{
			json.put("msg","修改栏目失败,请重新修改");
		}
		writer.print(json);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public void setMagazineService(MagazineService magazineService) {
		this.magazineService = magazineService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
}
