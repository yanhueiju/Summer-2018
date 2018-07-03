package com.zyh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyh.po.User;
import com.zyh.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	//注入service
	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request,  String username,String password,Model model){
		//进行登录的操作
		User user = loginService.login(username, password);
//		String ok = request.getParameter("ok");
//		System.out.println(ok);
		//如果user = null 说明 用户名或密码不正确，如果不为null则登录成功
		if(user == null){//登录失败，跳转回 login.jsp界面
			return "login";//-----拼接后的效果，等价于 /login.jsp
		}else{//登录成功，跳入主界面index.jsp
			//登录成功后，将用户信息保存到Model中，返回给前端界面
			model.addAttribute("user", user);
			//将登录成功的用户信息保存到  Session会话中(如果用户在浏览器端，一定时间对界面不作任何操作，则会自动退出登录）
			request.getSession().setAttribute("user", user);
			return "index";
		}
	}
	@RequestMapping("/logout")
    public String execute(HttpSession session){
        session.invalidate();
        return "login";
    }
	
	
}
