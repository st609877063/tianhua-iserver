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

import com.framework.util.DateTime;
import com.platform.database.GlobalVariables;
import com.platform.domain.Magazine;
import com.platform.domain.MagazineClass;
import com.platform.domain.Section;
import com.platform.domain.User;
import com.platform.service.MagazineService;
import com.platform.service.SectionService;
import com.platform.service.UserService;
import com.platform.utils.UploadFile;

@Controller
@RequestMapping("/magazine")
public class MagazineController {
	
	MagazineService magazineService ;
	SectionService sectionService ;
	UserService userService ;
	
	@RequestMapping(method = RequestMethod.GET)
	public void getMagazines(HttpServletRequest request ,HttpServletResponse response,Magazine magazine) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		try {
			writer = response.getWriter();
			String str = magazineService.getMagazines(startRow, pagesize,sortorder);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/iphone/app",method = RequestMethod.GET)
	public void getXiaokangApp(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			sb.append("<rss version=\"2.0\">\n");
			sb.append("<item>\n");
			sb.append("<APPStore_id>1</APPStore_id> \n");
			sb.append("<APPStore_name><![CDATA[ 小康 ]]></APPStore_name>\n");
			sb.append("<APPStore_URL>http://itunes.apple.com/cn/app/id389715378?mt=8</APPStore_URL>\n");
			sb.append("</item>\n");
			sb.append("</rss>\n");
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/iphone/class",method = RequestMethod.GET)
	public void getIphoneMagazinesByClass(HttpServletRequest request ,HttpServletResponse response,Magazine magazine) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String magazineClass = request.getParameter("type");
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		try {
			writer = response.getWriter();
			String str = magazineService.getIphoneMagazinesByClass(magazineClass,startRow, pagesize,sortorder);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value ="/iphone",method = RequestMethod.GET)
	public void getIphoneMagazines(HttpServletRequest request ,HttpServletResponse response,Magazine magazine) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		try {
			writer = response.getWriter();
			String str = magazineService.getIphoneMagazines(startRow, pagesize,sortorder);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/name",method = RequestMethod.GET)
	public void getMagazineNames(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String magazineId = request.getParameter("magazineId");
		try {
			writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<select onChange=\"getSectionName()\" style=\"width:100px;\" id=\"magazineName\" name=\"magazineName\" size=\"1\">");
			List<?> list = magazineService.getMagazinesList();
			for(int i =0;i<list.size();i++){
				
				Magazine magazine = (Magazine) list.get(i);
				String sel = magazineId.equals(magazine.getMagazineId())?"selected":"";
				sb.append("<option "+sel+" value="+magazine.getMagazineId()+">");
				sb.append(magazine.getMagazineName());
				sb.append("</option>");
			}
			sb.append("</select>");
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/type",method = RequestMethod.GET)
	public void getMagazineTypes(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<select id=\"magazineClassId\" name=\"magazineClassId\" size=\"1\">");
			List<?> list = magazineService.getMagazineClassList();
			for(int i =0;i<list.size();i++){
				MagazineClass magazineClass = (MagazineClass) list.get(i);
				sb.append("<option  value="+magazineClass.getMagazineClassId()+">");
				sb.append(magazineClass.getMagazineClassName());
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
	public void saveMagazine(HttpServletRequest request,HttpServletResponse response,Magazine magazine) throws JSONException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		boolean flag = false;
		boolean flag2 = false;
		System.out.println(magazine.getMagazineName());
		//String magazineName =request.getParameter("magazineName");
		//if(magazineName!=null && !magazineName.equals("")) {
		//	magazineName = new String(magazineName.getBytes("iso-8859-1"), "utf-8"); 
		//}
		String userId = request.getParameter("userId");
		User user = userService.getUserById(userId);
		String magazineClassId = request.getParameter("magazineClassId");
		MagazineClass magazineClass = magazineService.getMagazineClass(magazineClassId);
		magazine.setUser(user);
		//magazine.setMagazineName(magazineName);
		magazine.setMagazineClass(magazineClass);
		magazine.setCreateDate(DateTime.getCurrentDateByString());
		flag = magazineService.saveMagazine(magazine);
		if("1".endsWith(magazineClassId))
			flag2 = sectionService.saveXKSections(magazine);
		else if("2".endsWith(magazineClassId))
			flag2 = sectionService.saveCZSections(magazine);

		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",flag);
		if(flag&&flag2){
			json.put("msg","添加杂志成功");
		}else{
			json.put("msg","添加杂志失败,请重新添加");
		}
		writer.print(json);
	}
	
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public void uploadMagazine(HttpServletRequest request,HttpServletResponse response){
		String timestamp = (String)request.getParameter("timestamp");
		//System.out.println("timestamp====="+timestamp);
		UploadFile.upload(request,GlobalVariables.uri,GlobalVariables.fileLocation,timestamp);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteMagazine(HttpServletRequest request,HttpServletResponse response,Magazine magazine) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		String rows=(String)request.getParameter("rowid");
		String[] rowids=rows.split(",");
		boolean flag=false;
		
		
		for(int i=0;i<rowids.length;i++){	
			if(rowids[i]!=null&&!"".equals(rowids[i])){
				//flag=sectionService.removeSections(rowids[i]);
				flag = magazineService.removeMagazine(magazine, rowids[i]);
			}
		}
		JSONObject json = new JSONObject();
		json.put("success",flag);
		if(flag){
			json.put("msg","删除杂志成功");
		}else{
			json.put("msg","删除杂志失败,请重新删除");
		}
		writer.print(json);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateMagazine(HttpServletRequest request,HttpServletResponse response,Magazine magazine) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		boolean changeOK =false;
		String userId = request.getParameter("userId");
		User user = userService.getUserById(userId);
		String magazineClassId = request.getParameter("magazineClassId");
		MagazineClass magazineClass = magazineService.getMagazineClass(magazineClassId);
		magazine.setUser(user);
		magazine.setMagazineClass(magazineClass);
		magazine.setUpdateDate(DateTime.getCurrentDateByString());
		changeOK = magazineService.updateMagazine(magazine);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",changeOK);
		if(changeOK){
			json.put("msg","修改杂志成功");
		}else{
			json.put("msg","修改杂志失败,请重新修改");
		}
		writer.print(json);
	}
	
	@RequestMapping(value="/export",method = RequestMethod.GET)
	public void exportMagazine(HttpServletRequest request,HttpServletResponse response) throws IOException{
	PrintWriter writer = response.getWriter();
	response.setHeader("Content-Type","application/force-download");
	response.setHeader("Content-Type","application/vnd.ms-excel");
	response.setHeader("Content-Disposition","attachment;filename=export.xls");
	writer.print(request.getParameter("exportContent")); 
	}
	
	public void setMagazineService(MagazineService magazineService) {
		this.magazineService = magazineService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}
}
