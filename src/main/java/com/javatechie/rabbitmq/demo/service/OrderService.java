package com.javatechie.rabbitmq.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javatechie.rabbitmq.demo.dto.Order;
import com.javatechie.rabbitmq.demo.dto.OrderContent;
import com.javatechie.rabbitmq.demo.repository.OrderRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	public Order createOrder(Order orderobj) {
		Order order = new Order();
		order.setRestaurantName(orderobj.getRestaurantName());
		order.setOrderId(orderobj.getOrderId());
		order.setCustomerName(orderobj.getCustomerName());
		order.setOrderTime(orderobj.getOrderTime());
		order.setOrderContent(orderobj.getOrderContent());
		System.out.print("新增資料庫時"+order);
		return repository.insert(order);
	}
//	public Order getNote(String id) {
//		List<Order> notes = repository.findByPharmacyId(id);
//		return notes.get(0);
//	}
	

}