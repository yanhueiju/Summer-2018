package com.zyh.mapper;

import java.util.List;

import com.zyh.po.User;
import com.zyh.po.UserParam;

public interface UserMapper{

	//新增一个用户信息  接口
	public void addUser(User user);
	//接口中的方法名，�?定要�? .xml文件中的  id的标签名相同
	public List<User> findAllUsers();
	
	public List<User> findByName(String userName);
	
	//根据多个条件 查询用户列表
	public List<User> findByParams(User userParam);
	
	//根据多个条件查询用户列表
	public List<User> findListByParam(UserParam userParam);
	//查询条件下�?�结果数
	public int findListByParamCount(UserParam userParam);
}
