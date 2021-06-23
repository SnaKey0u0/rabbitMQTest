package com.javatechie.rabbitmq.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderContent {
	private int hamburger;
	private int frenchFries;
	private int milktea;
	public OrderContent(){
		
	}
	public OrderContent(int hamburger,int frenchFries,int milktea){
		super();
		this.hamburger=hamburger;
		this.frenchFries=frenchFries;
		this.milktea=milktea;
		
	}
	public int getHamburger() {
		return hamburger;
	}
	
	public void setHamburger(int hamburger) {
		this.hamburger=hamburger;
	}
	public int getFrenchFries() {
		return frenchFries;
	}
	
	public void setFrenchFries(int frenchFries) {
		this.frenchFries=frenchFries;
	}
	public int getMilktea() {
		return milktea;
	}
	
	public void setMilktea(int milktea) {
		this.milktea=milktea;
	}
	public String toString(){//overriding the toString() method  
		return "hamburger 有 "+hamburger+"個 frenchFries 有 "+frenchFries+"個 milktea 有 "+milktea+"個";  
	}
}
