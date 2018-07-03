package com.zyh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.po.User;

@Controller
@RequestMapping("/testspring")
public class TestController {

	@RequestMapping("/test")
	public String test(String username,String password,Model model){
		System.out.println("========接收的参数username="+username+",password="+password);
		User user = new User();
		user.setId(10);
		user.setUsername("admin");
		user.setAddress("湖北省武汉市");
		
		System.out.println("===========spring mvc 框架配置成功！");
		model.addAttribute("user", user);
		return "index";
	}
}
