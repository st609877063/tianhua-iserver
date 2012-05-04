package com.platform.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.framework.util.DateTime;
import com.platform.domain.Role;
import com.platform.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	RoleService roleService ;
	@RequestMapping(method = RequestMethod.GET)
	public void getRoles(HttpServletResponse response,Role role) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String str = roleService.getRoles();
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping(method = RequestMethod.POST)
	public void saveRole(HttpServletResponse response,Role role) throws JSONException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		boolean flag = false;
		role.setCreateDate(DateTime.getCurrentDateByString());
		flag = roleService.saveRole(role);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",flag);
		if(flag){
			json.put("msg","添加角色成功");
		}else{
			json.put("msg","添加角色失败,请重新添加");
		}
		writer.print(json);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteRole(HttpServletRequest request,HttpServletResponse response,Role role) throws IOException{
		String rows=(String)request.getParameter("rowid");
		String[] rowids=rows.split(",");
		boolean flag=false;
		PrintWriter writer = response.getWriter();
		for(int i=0;i<rowids.length;i++){
			flag=true;
			roleService.removeRole(role,rowids[i]);
		}
		if(flag)
			writer.print("{success:true}");
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateRole(HttpServletRequest request,HttpServletResponse response,Role role) throws IOException, JSONException{
		PrintWriter writer = response.getWriter();
		boolean changeOK =false;
		changeOK = roleService.updateRole(role);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",changeOK);
		if(changeOK){
			json.put("msg","修改角色成功");
		}else{
			json.put("msg","修改角色失败,请重新修改");
		}
		writer.print(json);
	}
	
	@RequestMapping(value="/export",method = RequestMethod.GET)
	public void exportRole(HttpServletRequest request,HttpServletResponse response) throws IOException{
	PrintWriter writer = response.getWriter();
	response.setHeader("Content-Type","application/force-download");
	response.setHeader("Content-Type","application/vnd.ms-excel");
	response.setHeader("Content-Disposition","attachment;filename=export.xls");
	writer.print(request.getParameter("exportContent")); 
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
