package com.zyh.mapper;

import java.util.HashMap;
import java.util.List;

import com.zyh.po.Admin;

public interface AdminMapper {

	public void addAdmin(Admin admin);
	public List<Admin> findAllAdmin();
	public void deleteAdminById(int id);
	public Admin findAdminById(String id);
	public void updateAdmin(Admin admin);
	public List<Admin> fuzzyInfo(HashMap<String, String> admin);
}
