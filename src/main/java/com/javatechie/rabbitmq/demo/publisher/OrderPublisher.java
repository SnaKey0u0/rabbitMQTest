package com.javatechie.rabbitmq.demo.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Order;
import com.javatechie.rabbitmq.demo.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderPublisher {
	@Autowired
	private RabbitTemplate template;
	@Autowired
	private OrderService orderService;
	@PostMapping(value = "/addOrder")
	public String bookOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
//		Order order = new Order(order,"PROCESS","order place successfully");
		
		orderService.createOrder(order);
    	
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,order);
		return "addSuccess";
	}
}
