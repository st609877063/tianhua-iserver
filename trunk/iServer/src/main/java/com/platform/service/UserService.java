package com.platform.service;

import java.util.List;

import com.platform.domain.Role;
import com.platform.domain.User;

public interface UserService {
	
	public List<User> getListUsers();
	
	public String getUsers();
	
	public User getUserById(String id);
	
	public User getUserByName(String name);
	
	public String getRolesById(String id);
	
	public String getRolesByName(String name);
	
	public Role getRoleById(String id);
	
	public Role getRoleByName(String name);
	
	public String saveUser(User user);
	
	public boolean updateUser(User user);
	
	public String getMaxUserId();
	
	public boolean removeUser(User user);
	
	public boolean removeUser(User user,String id);
}
