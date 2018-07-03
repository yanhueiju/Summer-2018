package com.zyh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.po.Orders;
import com.zyh.service.OrdersService;

@RequestMapping("/orders")
@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	//注意：在控制器中，定义的方法的修辞符，只能是 public 不能为private 
	@RequestMapping("/order_list")
	public String findAll(Model model){
		List<Orders> orders = ordersService.findAll();
		//查询的结果如何返回到前端的界面？
		model.addAttribute("orders",orders);
		return "/WEB-INF/jsp/orders_list.jsp";
	}
}
