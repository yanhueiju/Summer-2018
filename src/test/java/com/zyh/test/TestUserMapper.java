package com.zyh.test;
//package com.itcast.caiwen.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.itcast.caiwen.mapper.UserMapper;
//import com.itcast.caiwen.po.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext*.xml")
//public class TestUserMapper {
//	@Autowired
//	private UserMapper userMapper;
//
//	@Test
//	public void testFindAll(){
//		List<User> userList = userMapper.selectAll();
//		for(User user : userList){
//			System.out.println(user);
//		}
//	}
//	
//	@Test
//	public void testInsertUser(){
//		User user = new User();
//		user.setUsername("通用mapper真牛");
//		user.setAddress("我是通用mapper增加的数�?");
//		
//		userMapper.insert(user);
//	}
//
//}
