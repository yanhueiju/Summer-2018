package com.zyh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.UserMapper;
import com.zyh.po.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public void addUser(User user){
		
		userMapper.addUser(user);
	}

	public List<User> findAll() {
		return userMapper.findAllUsers();
	}
}
