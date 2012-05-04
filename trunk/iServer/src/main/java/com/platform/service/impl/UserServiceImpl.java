package com.platform.service.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.RowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.framework.persistence.JdbcQueryManager;
import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.framework.util.DateTime;
import com.framework.util.MD5;
import com.platform.domain.Role;
import com.platform.domain.User;
import com.platform.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private PersistenceManager pm;
	private QueryManager qm;
	@SuppressWarnings("unused")
	private JdbcQueryManager jqm;
	public void setPersistenceManager(PersistenceManager pm) {
		this.pm = pm;
	}

	public void setQueryManager(QueryManager qm) {
		this.qm = qm;
	}
	public void setJdbcQueryManager(JdbcQueryManager jqm) {
		this.jqm = jqm;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> getListUsers() {
		List<User> list = qm.findByNamedQuery("getUsers");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getUsers() {
		List<User> list = qm.findByNamedQuery("getUsers");
		Iterator<User> it = list.iterator();
		JSONObject obj = new JSONObject();
		JSONArray jarray = new JSONArray();
		
		try {
			obj.put("total", list.size()-1);
			obj.put("page", 1);
			for (; it.hasNext();) {
				User user = it.next();
				if( "1".endsWith(user.getUserId()+"")) continue;
				JSONObject object = new JSONObject();
//				JSONArray array = new JSONArray();
//				object.put("id", user.getUserId());//id
//				array.put(user.getUserId());
//				array.put(user.getName());
//				array.put(user.getPassword());
				object.put("userId",user.getUserId());
				object.put("name",user.getName());
				object.put("password",user.getPassword());
				object.put("createDate",user.getCreateDate());
				if(user.getValid()==1)
					object.put("valid","是");
				else
					object.put("valid","否");
				Set<Role> set = user.getRoles();
				Iterator<Role> roles = set.iterator();
				String rolename = "普通用户";
				for (; roles.hasNext();) {
					Role role = roles.next();
					rolename = role.getName();
				}
				object.put("rolename",rolename);
				//object.put("cell", array);//cell
				jarray.put(object);
			}
			obj.put("rows", jarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public User getUserById(String id) {
		List<User> list = qm.findByNamedQuery("getUserById", id);
		User user = null;
		if(list != null && list.size() >=1) {
			user = list.get(0);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public User getUserByName(String name) {
		List<User> list = qm.findByNamedQuery("getUserByName", name);
		User user = null;
		if(list != null && list.size() >=1) {
			user = list.get(0);
		}
		return user;
	}

	@Transactional(readOnly = true)
	public String getRolesById(String id) {
		Iterator<Role> roles = null;
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		User user = getUserById(id);
		Set<Role> set = user.getRoles();
		roles = set.iterator();
		try {
			for (; roles.hasNext();) {
				Role role = roles.next();
				JSONObject object = new JSONObject();
				object.put("name", role.getName());
				array.put(object);
			}
			obj.put("rows", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	@Transactional(readOnly = true)
	public Role getRoleById(String id) {
		Iterator<Role> roles = null;
		User user = getUserById(id);
		Set<Role> set = user.getRoles();
		roles = set.iterator();
		Role role = null;
		for (; roles.hasNext();) {
			role = roles.next();
		}
		return role;
	}

	@Transactional(readOnly = true)
	public String getRolesByName(String name) {
		Iterator<Role> roles = null;
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		User user = getUserByName(name);
		Set<Role> set = user.getRoles();
		roles = set.iterator();
		try {
			for (; roles.hasNext();) {
				Role role = roles.next();
				JSONObject object = new JSONObject();
				object.put("name", role.getName());
				array.put(object);
			}
			obj.put("rows", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	@Transactional(readOnly = true)
	public Role getRoleByName(String name) {
		Iterator<Role> roles = null;
		User user = getUserByName(name);
		Set<Role> set = user.getRoles();
		roles = set.iterator();
		Role role = null;
		for (; roles.hasNext();) {
			role = roles.next();
		}
		return role;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveUser(User user) {
		
		long maxId = Long.parseLong(getMaxUserId())+1;
		user.setCode(MD5.encrypt(user.getName()));
		user.setValid(1);
		user.setUserId(maxId+"");
		user.setCreateDate(DateTime.getCurrentDateByString());
		pm.save(user, true);
		return user.getUserId();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String getMaxUserId(){
		String sql = "";
		sql = "select max(cast(user_id as DECIMAL)) from users";
		String maxId ="";
		RowSet rs = jqm.getRowSet(sql);
		try {
			if (rs.next()&&rs.getString(1)!=null) {
				maxId = rs.getString(1);
			}
			else maxId = "1";
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maxId;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateUser(User user) {
		
		pm.update(user);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean removeUser(User user) {
		pm.remove(user);
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean removeUser(User user, String id) {
		pm.remove(user.getClass(), id);
		return true;
	}

}
