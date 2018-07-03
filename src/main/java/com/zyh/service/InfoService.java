package com.zyh.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.InfoMapper;
import com.zyh.po.Info;

@Service
public class InfoService {
	@Autowired
	private InfoMapper infoMapper;
	
	public void addInfo(Info info) {
		
		infoMapper.addInfo(info);
	}
	
	public List<Info> findAllInfo(){
		
		return infoMapper.findAllInfo();
	}
	
	public void deleteInfoById(int id) {
		
		infoMapper.deleteInfoById(id);
	}
	
	public Info findInfoById(String id) {
		return infoMapper.findInfoById(id);
	}


	public void updateInfo(Info info) {
		// TODO Auto-generated method stub
		infoMapper.updateInfo(info);
	}

	public List<Info> fuzzyInfo(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		return infoMapper.fuzzyInfo(info);
	}

}
