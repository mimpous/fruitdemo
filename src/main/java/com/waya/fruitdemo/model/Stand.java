package com.waya.fruitdemo.model;

import java.util.ArrayList;
import java.util.List;

import com.waya.fruitdemo.GetSerialNumber;

public class Stand {

	private String standId = null;

	List<Basket> baskets;

	public Stand() {
		standId = GetSerialNumber.getStandName();
	}

	public Stand(Basket fruitOne, Basket fruitTwo) {
		standId = GetSerialNumber.getStandName();
		getBaskets().add(fruitOne);
		getBaskets().add(fruitTwo);
	}

	public List<Basket> getBaskets() {
		if (baskets == null) {
			baskets = new ArrayList<Basket>();
		}
		return baskets;
	}

	public void addFruit(Basket fruitBasket) {
		getBaskets().add(fruitBasket);
	}

	public String getStandName() {
		return standId;
	}	 
	
	public double getSum( ) { 
		return  baskets.stream().mapToDouble(Basket::getSumOfFruits).sum(); 
	}

	@Override
	public String toString() {
		return "\n Stand [standId=" + standId + ", baskets=" + baskets + ", getSum()=" + getSum() + "]";
	}
 
}
