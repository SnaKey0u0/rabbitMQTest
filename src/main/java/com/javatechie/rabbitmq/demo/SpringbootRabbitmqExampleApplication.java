package com.javatechie.rabbitmq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories("com.javatechie.rabbitmq.demo.repository") 
@EntityScan("com.javatechie.rabbitmq.demo.dto")
@SpringBootApplication
public class SpringbootRabbitmqExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqExampleApplication.class, args);
	}

}
