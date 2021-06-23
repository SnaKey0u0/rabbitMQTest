package com.javatechie.rabbitmq.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.rabbitmq.demo.dto.Order;

@Repository
public interface H2Repository extends CrudRepository<Order, String> {
    List<Order> findByCustomerName(String CustomerName);
}