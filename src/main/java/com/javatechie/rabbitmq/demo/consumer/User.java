package com.javatechie.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Order;

@Component
public class User {
	
	@RabbitListener(queues= MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(Order order) {
		
		System.out.println("Message received from Queue : "+order);
	}
}
