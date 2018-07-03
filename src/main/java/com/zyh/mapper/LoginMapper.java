package com.zyh.mapper;

import java.util.HashMap;

import com.zyh.po.User;

public interface LoginMapper {

//	public User login(String username,String password);
	//由于mybatis在查询时，查询条件只能传递一个对象，如果条件有多个，则需要进行封装为对象后，再传递
	public User login(HashMap<String, String> paramsMap);
}
