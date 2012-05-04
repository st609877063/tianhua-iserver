/**
 * @author   ZhouMing
 * @version  1.0.0
 * @category com.dhcc.cloud.controller.UserController
 */
package com.platform.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.framework.util.DateTime;
import com.platform.database.GlobalVariables;
import com.platform.domain.User;
import com.platform.service.RoleService;
import com.platform.service.UserService;
import com.platform.utils.MD5;
@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	@SuppressWarnings("unused")
	private RoleService roleService;

	private static List<User> list = new ArrayList<User>();

	@RequestMapping(method = RequestMethod.GET)
	public void getUsers(HttpServletResponse response,Model model) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String str = userService.getUsers();
			writer.write(str);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public void login(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session, User user) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String username = user.getName();
		String password = user.getPassword();
		
		boolean flag = false;
		int failTyle = 0;
		//HttpClientFactory.getHttpClient();
		JSONObject json = new JSONObject();
		try {
			
			writer = response .getWriter();
			
			list = userService.getListUsers();
			Iterator<User> it = list.iterator();
			
			for(;it.hasNext();){
			
				User u = it.next();
				if(u.getName().equals(username)&&u.getPassword().equals(MD5.getMD5(password))){
					if(u.getValid()==1){
						failTyle =1;
						break;
					}
					else {
						flag=true;
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						session.setAttribute("userId", u.getUserId());
						session.setAttribute("fileLocation", GlobalVariables.fileLocation);
						break;
					}
				}
			}
			if(failTyle==1){
				json.put("success", false);
				json.put("msg", "权限不足无法登陆.");
			}
			else if(flag){
				//user.setLastIp(request.getRemoteAddr());
				//serService.updateUser(user);
				json.put("success", true);
				json.put("msg", "登陆成功.");
			}else{
				json.put("success", false);
				json.put("msg", "用户名或密码错误，请重新登陆.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			writer.print(json);
	}
	
	@RequestMapping(value = "/iphone/checkLogin",method = RequestMethod.POST)
	public void loginIphone(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session, User user){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username===="+username);
		String userId = "";
		try {
			writer = response .getWriter();
			list = userService.getListUsers();
			Iterator<User> it = list.iterator();
			
			for(;it.hasNext();){
				User u = it.next();
				if(u.getName().equals(username)&&u.getPassword().equals(MD5.getMD5(password))){
					userId = u.getUserId()+"";
					flag = true;
					break;
				}
			}
			if(flag){
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
						"<root>\n " +
						"<result>success</result>\n" +
						"<data>\n " +
						"<userid>"+userId+"</userid>\n " +
						"</data>\n" +
						"</root>\n");
			}else{
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
						"<root>\n " +
						"<result>fail</result>\n" +
						"<data>\n " +
						"<errorCode>-1</errorCode>\n  " +
						"</data>\n " +
						"</root>\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(sb.toString());
	}
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public void logout(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session, User user) {
		//String username = user.getName();
		String username = request.getParameter("username");
		session.removeAttribute(username);
		session.invalidate();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			response.sendRedirect("/"+GlobalVariables.serverName+"login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getUser(@PathVariable
	String id, Model model) {
		userService.getUserById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addUser(HttpServletRequest request,HttpServletResponse response,User user) throws IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		String flag = "";
		user.setPassword(MD5.getMD5(request.getParameter("password")));
		user.setCreateDate(DateTime.getCurrentDateByString());
		flag = userService.saveUser(user);
		JSONObject json = new JSONObject();
		if(flag!=null&&!"".equals(flag)){
			json.put("success", true);
			json.put("msg","添加用户成功");
		}else{
			json.put("success", false);
			json.put("msg","添加用户失败");
		}
		writer.print(json);
	}

	@RequestMapping(value = "/iphone/register",method = RequestMethod.POST)
	public void registerUser(HttpServletRequest request,HttpServletResponse response,HttpSession session,User user) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("userName");	
		String password = request.getParameter("password");	
		PrintWriter writer = null;
		StringBuffer sb = new StringBuffer();
		writer = response.getWriter();
		String flag = "";
		user.setName(username);
		user.setPassword(MD5.getMD5(password));
		user.setCreateDate(DateTime.getCurrentDateByString());
		
		//判断用户是否存在
		User resultUser = userService.getUserByName(username);
		if(resultUser == null) {
			flag = userService.saveUser(user);
			if(flag!=null&&!"".equals(flag)){
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
						"<root> " +
						"<result>success</result>" +
						"<data> <userid>"+user.getUserId()+"</userid></data>" +
				"</root>");
			}else{
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
						"<root> " +
						"<data> <code>-2</code>" +
						"<info>注册失败!</info></data>" +
				"</root>");
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
					"<root> " +
					"<result>fail</result>" +
					"<data> <code>-1</code>" +
					"<info>该用户已经存在!</info></data>" +
			"</root>");
		}
		
		writer.print(sb.toString());
	}
	
	@RequestMapping(value = "/password",method = RequestMethod.PUT)
	public void updatePassword(HttpServletRequest request,HttpServletResponse response,HttpSession session,User user) throws IOException, JSONException {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");	
			String oldpassword = request.getParameter("oldpassword");	
			PrintWriter writer = response.getWriter();
			boolean changeOK =false;
			//String roleId = request.getParameter("roleId");
			//Set<Role> set = new HashSet<Role>();
			//Role role = roleService.getRoleById(roleId);
			//set.add(role);
			//user.setRoles(set);
			User u = userService.getUserById(""+user.getUserId());
			user.setPassword(MD5.getMD5(request.getParameter("password")));
			changeOK = (u.getPassword()).equals(MD5.getMD5(oldpassword))&&userService.updateUser(user);
			JSONObject json = new JSONObject();
			json.put("success",changeOK);
			if(changeOK){
				json.put("msg","修改密码成功");
			}else{
				json.put("msg","修改密码失败,请重新修改");
			}
			writer.print(json);

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(HttpServletRequest request,HttpServletResponse response,HttpSession session,User user) throws IOException, JSONException {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");	
			PrintWriter writer = response.getWriter();
			boolean changeOK =false;
			//String roleId = request.getParameter("roleId");
			//Set<Role> set = new HashSet<Role>();
			//Role role = roleService.getRoleById(roleId);
			//set.add(role);
			//user.setRoles(set);
			user.setPassword(MD5.getMD5(request.getParameter("password")));
			changeOK = userService.updateUser(user);
			JSONObject json = new JSONObject();
			json.put("success",changeOK);
			if(changeOK){
				json.put("msg","修改用户成功");
			}else{
				json.put("msg","修改用户失败,请重新修改");
			}
			writer.print(json);

	}
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteUser(HttpServletRequest request,HttpServletResponse response,User user) throws IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		String rows=(String)request.getParameter("rowid");
		String[] rowids=rows.split(",");
		boolean flag=false;
		for(int i=0;i<rowids.length;i++){
			flag = userService.removeUser(user, rowids[i]);
		}
		JSONObject json = new JSONObject();
		json.put("success",flag);
		if(flag){
			json.put("msg","删除用户成功");
		}else{
			json.put("msg","删除用户失败,请重新删除");
		}
		writer.print(json);
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
