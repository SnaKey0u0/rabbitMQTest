package com.javatechie.rabbitmq.demo.roles;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.bson.json.JsonObject;
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

import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value = "服務員查看菜單", notes = "服務員查看菜單")
	@GetMapping(value = "/checkOrders")
	public ArrayList<Order> checkOrders() {
		//回傳服務生手上所有訂單
		return array;
	}
	@ApiOperation(value = "服務員確認菜單", notes = "服務員確認菜單")
	@PostMapping(value = "/confirmOrder/{position}")
	public JsonObject confirmOrder(@PathVariable("position")String position) {
		//轉交訂單給廚師
		int n = Integer.valueOf(position);
		template.convertAndSend(MessagingConfig.EXCHANGE_2, MessagingConfig.ROUTING_KEY_2,array.get(n));
		//移除暫存訂單
		array.remove(n);
		return new JsonObject("{'msg':'success'}");
	}
}
