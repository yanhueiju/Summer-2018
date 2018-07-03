package com.zyh.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.AdminMapper;
import com.zyh.po.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
	
	public void addAdmin(Admin admin) {
		
		adminMapper.addAdmin(admin);
	}
	
	public List<Admin> findAllAdmin(){
		
		return adminMapper.findAllAdmin();
	}
	
	public void deleteAdminById(int id) {
		
		adminMapper.deleteAdminById(id);
	}
	
	public Admin findAdminById(String id) {
		return adminMapper.findAdminById(id);
	}

	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminMapper.updateAdmin(admin);
	}

	public List<Admin> fuzzyInfo(HashMap<String, String> admin) {
		// TODO Auto-generated method stub
		return adminMapper.fuzzyInfo(admin);
	}
}
