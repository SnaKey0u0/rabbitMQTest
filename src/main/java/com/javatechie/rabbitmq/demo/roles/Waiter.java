package com.javatechie.rabbitmq.demo.roles;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Order;

@Component
@RestController
@RequestMapping(value = "/waiter")
public class Waiter {
	
	@Autowired
	private RabbitTemplate template;
	
	ArrayList<Order>array=new ArrayList<>();
	
	//聽第一個queue
	@RabbitListener(queues= MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(Order order) {
		array.add(order);
	}
	
	@GetMapping(value = "/checkOrders")
	public ArrayList<Order> checkOrders() {
		//回傳服務生手上所有訂單
		return array;
	}
	
	@PostMapping(value = "/confirmOrder")
	public String confirmOrder(@RequestBody int n) {
		//轉交訂單給廚師
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY_2,array.get(n));
		//移除暫存訂單
		array.remove(n);
		return "success";
	}
}
