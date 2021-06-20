package com.javatechie.rabbitmq.demo.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.rabbitmq.demo.dto.Order;
//import com.javatechie.rabbitmq.demo.dto.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

//	<Note> findByPharmacyId(String pharmacyId);
	
//	List<OrderStatus> findByPharmacyId(String pharmacyId);
//	List<Order> findByorderCustomerNameLike(String CustomerName);

	List<Order> findByCustomerName(String CustomerName);
	


}