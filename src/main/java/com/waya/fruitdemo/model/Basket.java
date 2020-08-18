package com.waya.fruitdemo.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	List<Fruits> fruits;
	
	public Basket( Fruits aFruit ) {  
		getFruits().add( aFruit );
	}

	public List<Fruits> getFruits() {
		if ( fruits == null ) {
			fruits=new ArrayList<Fruits>();
		}
		return fruits;
	}

	@Override
	public String toString() {
		return "Basket [ fruits=" + fruits  + "] " ;
	}
	public double getSumOfFruits( ) { 
		return  fruits.stream().mapToDouble(Fruits::getPrice).sum(); 
	} 
}
