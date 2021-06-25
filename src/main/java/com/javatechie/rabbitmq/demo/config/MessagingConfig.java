package com.javatechie.rabbitmq.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
@Configuration
public class MessagingConfig{
	public static final String QUEUE ="my_queue";
	public static final String EXCHANGE ="my_exchange";
	public static final String ROUTING_KEY ="my_routingKey";
	
	public static final String QUEUE_2 ="my_queue2";
	public static final String EXCHANGE_2 ="my_exchange2";
	public static final String ROUTING_KEY_2 ="my_routingKey2";
	@Bean
	public Queue queue() {
		return new AnonymousQueue();
	}
	
	@Bean
	public Queue queue2() {
		return new AnonymousQueue();
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}
	
//	@Bean
//	public TopicExchange exchange2() {
//		return new TopicExchange(EXCHANGE_2);
//	}
	
	@Bean
	public Binding binding(TopicExchange exchange) {
		return BindingBuilder.bind(queue()).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	public Binding binding2(TopicExchange exchange, Queue queue2) {
		return BindingBuilder.bind(queue2()).to(exchange).with(ROUTING_KEY_2);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}