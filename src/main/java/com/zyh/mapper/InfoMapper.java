package com.zyh.mapper;

import java.util.HashMap;
import java.util.List;

import com.zyh.po.Info;

public interface InfoMapper {

	public void addInfo(Info info);
	public List<Info> findAllInfo();
	public void deleteInfoById(int id);
	public Info findInfoById(String id);
	public void updateInfo(Info info);
	public List<Info> fuzzyInfo(HashMap<String, String> info);
}
