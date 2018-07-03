package com.zyh.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.RoleMapper;
import com.zyh.po.Role;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	public void addRole(Role role) {
		// TODO Auto-generated method
		role.setCreateDate(new Date());
		roleMapper.addRole(role);
	}

	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.findAllRole();
	}

	public Role findRoleById(String id) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleById(id);
	}

	public void deleteRoleById(int id) {
		// TODO Auto-generated method stub
		roleMapper.deleteRoleById(id);
	}

	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.updateRole(role);
	}

}
