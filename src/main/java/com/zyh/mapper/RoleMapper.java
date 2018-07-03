package com.zyh.mapper;

import java.util.List;

import com.zyh.po.Role;

public interface RoleMapper {
	
	public void addRole(Role role);
	public List<Role> findAllRole();
	public void deleteRoleById(int id);

	public Role findRoleById(String id);
	public void updateRole(Role role);
}
