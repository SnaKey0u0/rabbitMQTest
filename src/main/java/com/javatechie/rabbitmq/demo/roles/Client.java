package com.javatechie.rabbitmq.demo.roles;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Order;
import com.javatechie.rabbitmq.demo.service.OrderService;

@RestController
@RequestMapping(value = "/customer")
public class Client {
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping(value = "/addOrder")
	public String addOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
//		Order order = new Order(order,"PROCESS","order place successfully");
		//轉交訂單給服務生
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,order);
		return "addSuccess";
	}
	@GetMapping(value = "checkOrders/{customerName}")
	public String checkOrders(@PathVariable("customerName") String customerName) {
		return "fuck 還沒做資料庫查詢";
	}
	@GetMapping(value = "checkShoppingCar/{customerName}")
	public String checkShoppingCar(@PathVariable("customerName") String customerName) {
		return "fuck 還沒做redis購物車";
	}
}
