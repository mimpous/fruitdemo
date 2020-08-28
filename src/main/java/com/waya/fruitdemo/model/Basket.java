package com.waya.fruitdemo.model;

public class Basket {

	//List<Fruits> fruits;
	private Fruits fruit;
	
	public Basket( Fruits aFruit ) {  
		fruit = aFruit ;
	}
 
	public Fruits getFruit() {
		return fruit;
	}
 
 
	@Override
	public String toString() {
		return "Basket [ fruit=" + fruit  + "] " ;
	}
	public double getSumOfFruits( ) { 
		return  fruit.getPrice(); 
	} 
}
