package com.zyh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.mapper.OrdersMapper;
import com.zyh.po.Orders;

@Service
public class OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	
	public List<Orders> findAll(){
		return ordersMapper.findAll();
	}
}
