package com.platform.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.platform.database.ControlDatabase;
import com.platform.database.GlobalVariables;
import com.platform.domain.Operation;
import com.platform.domain.Role;
import com.platform.service.OperationService;
import com.platform.service.RoleService;
import com.platform.service.UserService;
import com.platform.utils.DateUtil;
import com.platform.utils.FileUtil;

@Controller
@RequestMapping("/operation")
public class OperationController {
	private OperationService operationService;
	private UserService userService;
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	public void getOperations(HttpSession session, Model model,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String str = null;
		try {
			writer = response.getWriter();
			String username = (String) session.getAttribute("username");
			if ("1".equals(username)) {
				str = operationService.getOperations();
			} else { 
				Role role = userService.getRoleByName(username);
				str = roleService.getOperationsByRole(role.getRoleId());
			}
			System.out.println(str);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public void getOtherOperations(@PathVariable
			String id,HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			String str = roleService.getOtherOperations(id);
			writer.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/some/{id}", method = RequestMethod.GET)
	public void getHaveOperations(@PathVariable
			String id,HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			String str = null;
			str = roleService.getHaveOperations(id);
			writer.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/backup",method = RequestMethod.GET)
	public void backupDatabase(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		boolean flag = false;
		Date date = new Date();
		String backupName = "".equals(request.getParameter("backupName2"))?request.getParameter("backupName"):request.getParameter("backupName2");
//		String backupName2 = request.getParameter("backupName2");
		String name = backupName+DateUtil.formatDate(date)+".sql";
		flag = ControlDatabase.backup(GlobalVariables.uri+GlobalVariables.backupLocation+"/"+name);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",flag);
		if(flag){
			json.put("msg","备份成功");
		}else{
			json.put("msg","备份失败,请稍候重试");
		}
		writer.print(json);
	}
	
	@RequestMapping(value = "/restore",method = RequestMethod.GET)
	public void restoreDatabase(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		boolean flag = false;
		String name = request.getParameter("backupName");
		flag = ControlDatabase.load(GlobalVariables.uri+GlobalVariables.backupLocation+"/"+name);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",flag);
		if(flag){
			json.put("msg","还原成功");
		}else{
			json.put("msg","还原失败,请稍候重试");
		}
		writer.print(json);
	}
	
	@RequestMapping(value = "/databaseList",method = RequestMethod.GET)
	public void getDatabasesList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		File f = FileUtil.mkdir(GlobalVariables.uri+GlobalVariables.backupLocation+"/"); //新建文件实例
		File[] list = f.listFiles(); /* 此处获取文件夹下的所有文件 */
		StringBuffer sb = new StringBuffer();
		
		sb.append("<select style=\"width:100px;\" id=\"backupName\" name=\"backupName\" size=\"1\">");
		if(list.length==0){
			sb.append("<option  value="+"0"+">");
			sb.append("没有备份");
			sb.append("</option>");
			
		}else{
			for(int i =0;i<list.length;i++){
				String name = list[i].getName();
				sb.append("<option  value="+name+">");
				sb.append(name.substring(0,name.length()-4));
				sb.append("</option>");
			}
		}
		sb.append("</select>");
		writer.write(sb.toString());
	}
	
	@RequestMapping(value = "/databaseName",method = RequestMethod.GET)
	public void getDatabasesName(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		File f = FileUtil.mkdir(GlobalVariables.uri+GlobalVariables.backupLocation+"/"); //新建文件实例
		File[] list = f.listFiles(); /* 此处获取文件夹下的所有文件 */
		StringBuffer sb = new StringBuffer();
		
		sb.append("<select style=\"width:100px;\" id=\"backupName\" name=\"backupName\" size=\"1\">");
		if(list.length==0){
			sb.append("<option  value="+"0"+">");
			sb.append("没有备份");
			sb.append("</option>");
			
		}else{
			for(int i =0;i<list.length;i++){
				String name = list[i].getName();
				sb.append("<option  value="+name.substring(0,name.length()-14)+">");
				sb.append(name.substring(0,name.length()-4));
				sb.append("</option>");
			}
		}
		sb.append("</select>");
		writer.write(sb.toString());
	}
	@RequestMapping(method = RequestMethod.PUT)
	public void saveRoleOperation(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();

			Role role = new Role();
			Set<Operation> set = new HashSet<Operation>();
			String app = request.getParameter("appList");
			String roleId = request.getParameter("roleId");
			role = roleService.getRoleById(roleId);
			if (!"".equals(app)) {
				String[] appArray = app.split(",");
				for (int i = 0; i < appArray.length; i++) {
					Operation op = operationService
							.getOperationById(appArray[i]);
					set.add(op);
				}
			}
			role.setOperations(set);
			roleService.updateRole(role);
			writer.print("{success:true}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
