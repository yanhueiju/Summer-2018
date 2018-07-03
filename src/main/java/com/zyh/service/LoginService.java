package com.zyh.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.LoginMapper;
import com.zyh.po.User;

@Service
public class LoginService {

	//调用 LoginMapper来查询数据，检验用户名和密码
	@Autowired
	private LoginMapper loginMapper;
	
	public User login(String username,String password){
		//封装用户名和密码，保存到一个新对象中
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("username", username);
		paramMap.put("password", password);
		
		return loginMapper.login(paramMap);
	}
}
