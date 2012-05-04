package com.platform.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.framework.util.DateTime;
import com.platform.domain.Operation;
import com.platform.domain.Role;
import com.platform.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	private PersistenceManager pm;
	private QueryManager qm;
	
	public void setPersistenceManager(PersistenceManager pm) {
		this.pm = pm;
	}

	public void setQueryManager(QueryManager qm) {
		this.qm = qm;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getRoles() {
		List<Role> list = qm.findByNamedQuery("getRoles");
		Iterator<Role> it = list.iterator();
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
	
		try {
			obj.put("total", list.size());
		for(;it.hasNext();){
			Role role = it.next();
			JSONObject object = new JSONObject();
			object.put("roleId",role.getRoleId());
			object.put("name",role.getName());
			object.put("roleDesc",role.getRoleDesc());
			object.put("createDate",role.getCreateDate());
			array.put(object);
		}
			obj.put("rows", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Role getRoleById(String roleId) {
		List<Role> list = qm.findByNamedQuery("getRoleById", roleId);
		Role role = list.get(0);
		return role;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Role getRoleByName(String name) {
		List<Role> list = qm.findByNamedQuery("getRoleByName", name);
		Role role = list.get(0);
		return role;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getOperations() {
		List<Operation> list = qm.findByNamedQuery("getOperations");
		Iterator<Operation> it = list.iterator();
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONObject object = null;
		try {
			obj.put("total", list.size());
			for(;it.hasNext();){
			Operation operation = it.next();
			object=new JSONObject();
			object.put("operationId", operation.getOperationId());
			object.put("name",operation.getName());
			array.put(object);
			}
			obj.put("rows", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	public String getOperationsByRole(String roleId){
		Iterator<Operation> operations= null;
		JSONArray array = new JSONArray();
		Role role = getRoleById(roleId);
		Set<Operation> set =role.getOperations();
		operations = set.iterator();
		try {
			for(;operations.hasNext();){
				Operation operation = operations.next();
				JSONObject object = new JSONObject();
				object.put("operationId", operation.getOperationId());
				object.put("text",operation.getName());
				object.put("id", operation.getSeq());
				object.put("parentId", operation.getParentId());
				object.put("leaf", operation.isLeaf());
				array.put(object);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array.toString();
	}
	@SuppressWarnings("unchecked")
	public String getOtherOperations(String roleId){
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray someArray = new JSONArray();
		JSONArray allArray = new JSONArray();
		int flag = 0,count=0;
		
		try {
			JSONObject someObj = new JSONObject(getHaveOperations(roleId));
			JSONObject allObj =new JSONObject(getOperations());
			someArray = someObj.getJSONArray("rows");
			allArray = allObj.getJSONArray("rows");
			int ssum = someArray.length();
			int asum = allArray.length();
//			if(someArray.length()==0){
//				return allObj.toString();
//			}
//			else{
			for(int i=0;i<asum;i++){
				JSONObject obj = (JSONObject)(allArray.get(i));
				String id = obj.getString("operationId");
				for(int j=0;j<ssum;j++){
					JSONObject o = (JSONObject)(someArray.get(j));
					String oid = o.getString("operationId");
					if(id.equals(oid)){
						flag = 1;
						break;
					}	
				}
				if(flag == 0){
					count++;
					array.put(obj);
				}
				flag = 0;
			}
			object.put("total", count);
			object.put("rows", array);
			System.out.println("------------------------");
			System.out.println(object.toString());
			System.out.println("------------------------");
		
//			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object.toString();
	}
	
	@Transactional(readOnly = true)
	public String getHaveOperations(String roleId) {
		Iterator<Operation> operations= null;
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		Role role = getRoleById(roleId);
		Set<Operation> set =role.getOperations();
		operations = set.iterator();
		try {
			obj.put("total", set.size());
			for(;operations.hasNext();){
				Operation operation = operations.next();
				JSONObject object = new JSONObject();
				object.put("operationId",operation.getOperationId());
				object.put("name",operation.getName());
				array.put(object);
			}
			obj.put("rows", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}
//	@Transactional(readOnly = true)
//	public String getOperationsByName(String name) {
//		Iterator<Operation> operations= null;
//		JSONArray array = new JSONArray();
//		JSONObject obj = new JSONObject();
//		JSONObject object =null;
//		Role role = getRoleByName(name);
//		Set<Operation> set =role.getOperations();
//		operations = set.iterator();
//		try {
//			for(;operations.hasNext();){
//				Operation operation = operations.next();
//				object = new JSONObject();	
//					object.put("operationId",operation.getOperationId());
//					object.put("name",operation.getName());
//					array.put(object);
//				} 
//				obj.put("rows", array);
//			}
//		catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return obj.toString();
//	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveRole(Role role) {
		role.setCreateDate(DateTime.getCurrentDateByString());
		pm.save(role,true);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateRole(Role role) {
		pm.update(role);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean removeRole(Role role) {
		pm.remove(role);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean removeRole(Role role,String id) {
		pm.remove(role.getClass(),id);
		return true;
	}
}
