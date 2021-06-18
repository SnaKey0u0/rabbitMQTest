package com.javatechie.rabbitmq.demo.roles;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Order;
import com.javatechie.rabbitmq.demo.service.OrderService;

@Component
@RestController
@RequestMapping(value = "/cooker")
public class Cooker {
	
	@Autowired
	private OrderService orderService;
	
	ArrayList<Order>array=new ArrayList<>();
	
	//聽第二個queue
	@RabbitListener(queues= MessagingConfig.QUEUE_2)
	public void consumeMessageFromQueue(Order order) {

		array.add(order);
	}
	@GetMapping(value = "/checkOrders")
	public ArrayList<Order> checkOrders() {
		//回傳廚師手上所有訂單
		return array;
	}
	
	@PostMapping(value = "/confirmOrder")
	public String confirmOrder(@RequestBody int n) {
		//廚師確認訂單，加入資料庫
		orderService.createOrder(array.get(n));
		//移除暫存訂單
		array.remove(n);
		return "success";
	}
}
