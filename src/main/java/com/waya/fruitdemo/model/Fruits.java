package com.waya.fruitdemo.model;

public class Fruits {

	
	private double price;
	
	public Fruits( double fruitPrice ) { 
		this.price = fruitPrice;
	}
	
	public double getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [price=" + price  + "]";
	}
	
	
}
