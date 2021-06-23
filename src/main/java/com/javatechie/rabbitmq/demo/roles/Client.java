package com.javatechie.rabbitmq.demo.roles;

import java.util.UUID;

import org.bson.json.JsonObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Order;
import com.javatechie.rabbitmq.demo.service.OrderService;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(value = "/customer")
public class Client {
	@Autowired
	private RabbitTemplate template;
	@Autowired
    private OrderService OrderService;
	@ApiOperation(value = "新增訂單", notes = "訂單是否成立")
	@PostMapping(value = "/addOrder")
	public JsonObject addOrder(@RequestBody Order order) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		order.setOrderId(UUID.randomUUID().toString());
		order.setOrderTime(strDate);
//		Order order = new Order(order,"PROCESS","order place successfully");
		//轉交訂單給服務生
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,order);
		return new JsonObject("{'msg':'success'}");
	}
	@ApiOperation(value = "顧客查看訂單", notes = "依customerName找歷史訂單")
	@GetMapping(value = "/checkOrders/{customerName}")
	public ResponseEntity<ArrayList<Order>> checkOrders(@PathVariable("customerName") String customerName) {
		ArrayList<Order> order = OrderService.getOrder(customerName);
    	
		return ResponseEntity.ok(order);
	}
	
//	@GetMapping(value = "checkShoppingCar/{customerName}")
//	public String checkShoppingCar(@PathVariable("customerName") String customerName) {
//		return "fuck 還沒做redis購物車";
//	}
}
