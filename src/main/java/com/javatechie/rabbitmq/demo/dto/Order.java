package com.javatechie.rabbitmq.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Order {
	private String orderId;
	private String restaurantName;
	private String customerName;
	private String orderTime;
	private OrderContent orderContent;
	public Order(){
		
	}
	public Order(String restaurantName,String orderId,String customerName,String orderTime,OrderContent orderContent){
		super();
		this.restaurantName=restaurantName;
		this.orderId=orderId;
		this.customerName=customerName;
		this.orderTime=orderTime;
		this.orderContent=orderContent;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	
	public void setRestaurantName(String restaurantName) {
		this.restaurantName=restaurantName;
	}
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId=orderId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName=customerName;
	}
	
	public String getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(String orderTime) {
		this.orderTime=orderTime;
	}
	
	public OrderContent getOrderContent() {
		return orderContent;
	}
	
	public void setOrderContent(OrderContent orderContent) {
		this.orderContent=orderContent;
	}
	
	public String toString(){//overriding the toString() method  
		return "RestaurantName 是 "+restaurantName+" orderId 是 "+orderId+" customerName 是 "+customerName+" orderTime 是 "+orderTime+" orderContent 是"+orderContent;  
	}
}
