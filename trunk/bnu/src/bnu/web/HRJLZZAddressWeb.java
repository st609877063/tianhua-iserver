package bnu.web;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import com.actionsoft.awf.organization.cache.DepartmentCache;
import com.actionsoft.awf.organization.cache.RoleCache;
import com.actionsoft.awf.organization.cache.TeamCache;
import com.actionsoft.awf.organization.cache.TeamMemberCache;
import com.actionsoft.awf.organization.cache.UserCache;
import com.actionsoft.awf.organization.cache.UserMapCache;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.organization.model.DepartmentModel;
import com.actionsoft.awf.organization.model.RoleModel;
import com.actionsoft.awf.organization.model.TeamMemberModel;
import com.actionsoft.awf.organization.model.TeamModel;
import com.actionsoft.awf.organization.model.UserMapModel;
import com.actionsoft.awf.organization.model.UserModel;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

/**
 * Using
*  标题:经理自助查询获取地址簿WEB
 * @author dunan
 * @version V1.0
 * Updated on 2009-3-11
*/
public class HRJLZZAddressWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public HRJLZZAddressWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public HRJLZZAddressWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 返回树状邮件地址页面（左下框架）
	 * 
	 * @param me
	 *            UserContext 用户
	 * @param companyid
	 *            int 要显示指定单位的通讯簿
	 * @param groupstyle
	 *            int 显示方式
	 * @return 返回树状邮件地址页面描述
	 */
	public String getAddressTree(String awsuid,String sapuid,int companyId, int departmentId) {
		
		StringBuffer treeList = new StringBuffer("");
		Hashtable hash = new Hashtable();
		//当前用户所在部门
		DepartmentModel rootModel = (DepartmentModel)DepartmentCache.getModel(departmentId);
		
		boolean CurrIsManager = this.getContext().getUserModel()._isManager;
		RoleModel mainRoleModel = this.getContext().getRoleModel();
		if (rootModel != null) { // 按组织结构显示
			if (mainRoleModel._groupName.equals("行政角色") &&
					mainRoleModel._roleName.equals("部门经理") 
					|| mainRoleModel._roleName.equals("部门总监") 
					|| mainRoleModel._roleName.equals("总裁")) { 			
						
				//============判断如果已经显示过则不做重复显示==========================================
				hash.put(departmentId, this.getContext());
				//============================================================================
				
				treeList.append(getNodeObjectOfDepartment("root", rootModel, true));// 异步，动态加载
				int ownerDepartmentRootId = 0;
				if (ownerDepartmentRootId > 0) {
					
					treeList.append("AWS_NODE_OD_ID_" + ownerDepartmentRootId + ".toggle();\n");
				}
				//treeList.append("	treePanel.render();\n root.expand(false, true);\n");
			}
		}
		
		
		//add by dunan 2008-12-8
		Hashtable userMap = UserMapCache.getMapListOfUser(awsuid);
		if(userMap.size() > 0) {
			for(int i = 0; i < userMap.size(); i++) {
				UserMapModel model = (UserMapModel)userMap.get(new Integer(i));
				
				//============判断如果已经显示过则不做重复显示============================
					if(hash.get(model._departmentId)!=null)	continue;
					//hash.put(model._departmentId, model);
				//==================================================================
				
				int mapDeptid = model._departmentId;
				int roleMapid = model._roleId;
				RoleModel roleModel = (RoleModel)RoleCache.getModel(roleMapid);
				if (!roleModel._groupName.equals("行政角色")) { //过滤非行政角色
					continue;
				}
				if (!roleModel._roleName.equals("部门经理") && !roleModel._roleName.equals("部门总监") 
						&& !roleModel._roleName.equals("总裁室成员") && !roleModel._roleName.equals("总裁")) {
					continue;
				}
//				if(model._isManager == true) {
					//当前用户兼职部门
					DepartmentModel rootMapModel = (DepartmentModel)DepartmentCache.getModel(mapDeptid);
					if (rootMapModel != null) { // 按组织结构显示
						treeList.append(getNodeObjectOfDepartment("root", rootMapModel, true));// 异步，动态加载
						int ownerDepartmentRootId = 0;
						if (ownerDepartmentRootId > 0) {
							// 自动展开，方便用户看到本部门的帐户
							treeList.append("AWS_NODE_OD_ID_" + ownerDepartmentRootId + ".toggle();\n");
						}
						
						hash.put(model._departmentId, model); //判断如果已经显示过则不做重复显示, put the sentence here!
					}
//				}
			}
		}
		treeList.append("	treePanel.render();\n root.expand(false, true);\n");
		//end
		String sid = "<input type=hidden name=sid value=" + getContext().getSessionId() + ">\n";
		Hashtable hashTags = new Hashtable();
		hashTags.put("treeList", treeList.toString());
		hashTags.put("companyid", Integer.toString(companyId));
		hashTags.put("departmentId", Integer.toString(departmentId));
		hashTags.put("isShowAll", "0");
		hashTags.put("sid", sid);
		hashTags.put("sessionId", super.getContext().getSessionId());
		hashTags.put("searchResult", "");
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("Kingsoft_HR_JLZZ_Tree.htm"), hashTags);
	}

	/**
	 * 遍历指定的部门，获取其子部门和帐户
	 * 
	 * @param departmentModel
	 * @param js
	 * @return
	 * @author jack
	 */
	private String eachORG(DepartmentModel departmentModel, StringBuffer js) {
		Hashtable subDeptList = DepartmentCache.getSubDepartmentList(departmentModel._id);
		if (subDeptList != null) {// 存在子部门
			for (int i = 0; i < subDeptList.size(); i++) {
				DepartmentModel model = (DepartmentModel) subDeptList.get(new Integer(i));
				js.append(getNodeObjectOfDepartment("AWS_NODE_OD_ID_" + departmentModel._id, model, false));
				// if (DepartmentCache.isExistSubModel(model._id)) {// 递归部门
				// js = new StringBuffer(eachORG(model, js));
				// }
			}
		}
		// 当前部门下的帐户
		Hashtable userHash = UserCache.getUserListOfDepartment(departmentModel._id);
		if (userHash != null) {
			for (int i = 0; i < userHash.size(); i++) {
				UserModel userModel = (UserModel) userHash.get(new Integer(i));
				if (userModel._isDisenable == true)
					continue;// 注销的不再显示
				js.append(getNodeObjectOfUser("AWS_NODE_OD_ID_" + departmentModel._id, userModel));
			}
		}
		if (subDeptList != null) {// 存在子部门
			for (int i = 0; i < subDeptList.size(); i++) {
				DepartmentModel model = (DepartmentModel) subDeptList.get(new Integer(i));
				// if (DepartmentCache.isExistSubModel(model._id)) {// 递归部门
				js = new StringBuffer(eachORG(model, js));
				// }
			}
		}
		return js.toString();
	}

	/**
	 * 获取支持Extjs动态树加载的JSON串
	 * 
	 * @param requestType
	 *            类型（什么树）
	 * @param param1
	 *            扩展参数1
	 * @param param2
	 *            扩展参数2
	 * @param param3
	 *            扩展参数3
	 * @return
	 */
	public String getJsonTreeOfORG(String requestType, String param1, String param2, String param3) {
		StringBuffer jsonStr = new StringBuffer("[");
		if (requestType.equals("Department")) {
			int rootDepartmentId = Integer.parseInt(param1);
			// 列出指定部门的下级列表
			Hashtable subDepartmentList = DepartmentCache.getSubDepartmentList(rootDepartmentId);
			if (subDepartmentList != null) {
				for (int i = 0; i < subDepartmentList.size(); i++) {
					DepartmentModel departmentModel = (DepartmentModel) subDepartmentList.get(new Integer(i));
					jsonStr.append(getJsonOfDepartment(departmentModel)).append(",");
				}
			}
			// 列出指定部门的帐户
			Hashtable userHash = UserCache.getUserListOfDepartment(rootDepartmentId);
			if (userHash != null) {
				for (int i = 0; i < userHash.size(); i++) {
					UserModel userModel = (UserModel) userHash.get(new Integer(i));
					if (userModel._isDisenable == true)
						continue;// 注销的不再显示
					jsonStr.append(getJsonOfUser(userModel)).append(",");
				}
			}// add by wangwq 增加了 兼职部门的显示
			/**update by dunan 2008-12-18
			Hashtable userMapHash = UserMapCache.getMapListOfDepartment(rootDepartmentId);
			if (userMapHash != null) {
				for (int i = 0; i < userMapHash.size(); i++) {
					UserMapModel userMapModel = (UserMapModel) userMapHash.get(new Integer(i));
					UserModel userModel = (UserModel) UserCache.getModel(userMapModel._mapId);
					if (userModel._isDisenable || (userHash != null && userHash.contains(userModel)))
						continue;// 注销的不再显示 兼职的不重复显示
					jsonStr.append(getJsonOfUser(userModel, userMapModel)).append(",");
				} 
			}*/
		} else if (requestType.equals("Role")) {
			int rootRoleId = Integer.parseInt(param1);
			// 列出指定分类下的用户列表
			Hashtable userList = UserCache.getUserListOfRole(rootRoleId);
			for (int p = 0; p < userList.size(); p++) {
				UserModel userModel = (UserModel) userList.get(new Integer(p));
				if (userModel._isDisenable == true)
					continue;// 注销的不再显示
				jsonStr.append(getJsonOfUser(userModel)).append(",");
			}
		} else if (requestType.equals("Team")) {
			int teamRootId = Integer.parseInt(param1);
			// 列出指定分类下的用户列表
			Hashtable teamMemberList = TeamMemberCache.getListOfMember(teamRootId);
			for (int p = 0; p < teamMemberList.size(); p++) {
				TeamMemberModel teamMemberModel = (TeamMemberModel) teamMemberList.get(new Integer(p));
				UserModel userModel = (UserModel) UserCache.getModel(teamMemberModel._userId);
				if (userModel._isDisenable == true)
					continue;// 注销的不再显示
				jsonStr.append(getJsonOfUser(userModel)).append(",");
			}
		}
		if (jsonStr.toString().lastIndexOf(",") > -1)
			jsonStr.setLength(jsonStr.length() - 1);
		jsonStr.append("]");
		// System.out.println(jsonStr.toString());
		return jsonStr.toString();
	}

	/**
	 * 将一个部门对象转换成一个JSON描述串
	 * 
	 * @param model
	 * @return
	 */
	private String getJsonOfDepartment(DepartmentModel model) {
		StringBuffer jsonStr = new StringBuffer("{");
		jsonStr.append("'id':'AWS_NODE_OD_ID_" + model._id + "',");
		jsonStr.append("'text':'" + model._departmentName + "',");
		jsonStr.append("'cls':'x-tree-node-department',");
		jsonStr.append("'wasChecked':false,");
		jsonStr.append("'leaf':false,");
		jsonStr.append("'checked':false,");
		jsonStr.append("'type':'Department'");
		// jsonStr.append("'onclick':\"new Ajax.Request('../extjs-tree.json',
		// {asynchronous:true, evalScripts:true, method:'get'});\"");
		jsonStr.append("}");
		return jsonStr.toString();
	}
	/**
	 * 将一个帐户对象转换成一个JSON描述串
	 * 
	 * @param model
	 * @return
	 */
	private String getJsonOfUser(UserModel model) {
		StringBuffer jsonStr = new StringBuffer("{");
		jsonStr.append("'id':'AWS_NODE_OU_ID_" + model._id + "',");
		jsonStr.append("'text':'" + model._userName + "',");
		if (model._isManager) {
			jsonStr.append("'cls':'x-tree-node-manager',");
			jsonStr.append("'qtip':'管理者，登录帐户：" + model._uid + "',");
		} else {
			jsonStr.append("'cls':'x-tree-node-user',");
			jsonStr.append("'qtip':'登录帐户：" + model._uid + "',");
		}
		jsonStr.append("'leaf':true,");
		jsonStr.append("'wasChecked':false,");
		jsonStr.append("'checked':false,");
		jsonStr.append("'uid':'" + model._uid + "',");
		jsonStr.append("'type':'user'");
		// jsonStr.append("'onclick':\"new Ajax.Request('../extjs-tree.json',
		// {asynchronous:true, evalScripts:true, method:'get'});\"");
		jsonStr.append("}");
		return jsonStr.toString();
	}

	/**
	 * add by wangwq 增加了兼职部门的人员的显示 将一个帐户对象转换成一个JSON描述串
	 * 
	 * @param model
	 * @return
	 */
	private String getJsonOfUser(UserModel model, UserMapModel userMapMpdel) {
		StringBuffer jsonStr = new StringBuffer("{");
		jsonStr.append("'id':'AWS_NODE_OU_ID_" + model._id + "',");
		jsonStr.append("'text':'" + model._userName + "',");
		if (userMapMpdel._isManager) {
			jsonStr.append("'cls':'x-tree-node-manager',");
			jsonStr.append("'qtip':'管理者，登录帐户：" + model._uid + "',");
		} else {
			jsonStr.append("'cls':'x-tree-node-user',");
			jsonStr.append("'qtip':'登录帐户：" + model._uid + "',");
		}
		jsonStr.append("'leaf':true,");
		jsonStr.append("'wasChecked':false,");
		jsonStr.append("'checked':false,");
		jsonStr.append("'uid':'" + model._uid + "',");
		jsonStr.append("'type':'user'");
		// jsonStr.append("'onclick':\"new Ajax.Request('../extjs-tree.json',
		// {asynchronous:true, evalScripts:true, method:'get'});\"");
		jsonStr.append("}");
		return jsonStr.toString();
	}

	/**
	 * 将一个部门对象转换成一个被Extjs创建的JavaScript语句
	 * 
	 * @param model
	 * @return
	 */
	private String getNodeObjectOfDepartment(String parentNode, DepartmentModel model, boolean isAsync) {
		String nodeType = "AsyncTreeNode";
		if (!isAsync)
			nodeType = "TreeNode";
		String node = "var AWS_NODE_OD_ID_" + model._id + "= new Ext.tree." + nodeType + "({id:'AWS_NODE_OD_ID_" + model._id + "',text:'" + model._departmentName + "',type:'Department',loader:new Ext.tree.TreeLoader({dataUrl:'./login.wf?sid=" + getContext().getSessionId()
				+ "&cmd=Address_Inner_Tree_JSONDATE',baseParams:{requestType:'Department',param1:'" + model._id + "'}}),'leaf':false,'cls':'x-tree-node-department','checked':false,'wasChecked':false});\n";
		return node + parentNode + ".appendChild(AWS_NODE_OD_ID_" + model._id + ");\n";
	}
	/**
	 * 将一个帐户对象转换成一个被Extjs创建的JavaScript语句
	 * 
	 * @param model
	 * @return
	 */
	private String getNodeObjectOfUser(String parentNode, UserModel model) {
		String isManager = "";
		if (model._isManager) {
			isManager = "'cls':'x-tree-node-manager','qtip':'管理者，登录帐户：" + model._uid + "'";
		} else {
			isManager = "'cls':'x-tree-node-user','qtip':'登录帐户：" + model._uid + "'";
		}
		String node = "var AWS_NODE_OU_ID_" + model._id + "= new Ext.tree.TreeNode({id:'AWS_NODE_OU_ID_" + model._id + "',text:'" + model._userName + "',type:'User','leaf':true," + isManager + ",'checked':false,'uid':'" + model._uid + "','wasChecked':false});\n";
		return node + parentNode + ".appendChild(AWS_NODE_OU_ID_" + model._id + ");\n";
	}
	
	
}

