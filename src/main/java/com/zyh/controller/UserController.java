package com.zyh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.po.User;
import com.zyh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//访问WEB-INF下的所有静态文件和JSP,不能直接通过浏览器访问，必须先经过controller进行跳转
	
	@RequestMapping("/init_user")
	public String init_adduser(){
		return "add_user";
	}
	
	@RequestMapping("/add")
	public String addUser(User user){
		userService.addUser(user);//通过service层来 将数据保存到数据库中
		
		return "index";
		
	}
	
	//编写一个查询所有用户列表信息
	@RequestMapping("/list")
	public String findAll(Model model){
		List<User> users = userService.findAll();
		//数据如何返回到前端？？？
		model.addAttribute("users", users);
		return "index";
	}
	
}
