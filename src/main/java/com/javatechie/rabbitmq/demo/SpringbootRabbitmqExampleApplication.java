package com.javatechie.rabbitmq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
//@ServletComponentScan
@SpringBootApplication
public class SpringbootRabbitmqExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqExampleApplication.class, args);
	}

}
