package com.platform.service;

import com.platform.domain.Role;

public interface RoleService {
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public String getRoles();
	
	/**
	 * 根据角色ID获取角色
	 * @param id
	 * @return
	 */
	public Role getRoleById(String roleId);
	
	/**
	 * 根据角色名获取角色
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name);
	
	/**
	 * 获取所有操作
	 * @return
	 */
	public String getOperations();
	
	/**
	 * @param roleId
	 * @return
	 */
	public String getOperationsByRole(String roleId);
	
	/**
	 * 根据角色ID获取未授权操作
	 * @param id 角色ID
	 * @return 
	 */
	
	public String getOtherOperations(String roleId);
	
	/**
	 * 根据角色ID获取授权操作
	 * @param id 角色ID
	 * @return
	 */
	public String getHaveOperations(String roleId) ;
	
	/**
	 * 根绝角色名获取授权操作
	 * @param name
	 * @return
	 */
	//public String getOperationsByName(String name);
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	public boolean saveRole(Role role);
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public boolean updateRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	public boolean removeRole(Role role);
	
	/**
	 * 根据ID删除角色
	 * @param role
	 * @param id
	 * @return
	 */
	public boolean removeRole(Role role,String id);
}
