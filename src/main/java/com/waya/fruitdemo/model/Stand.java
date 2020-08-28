package com.waya.fruitdemo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.waya.fruitdemo.GetSerialNumber;

public class Stand {

	private String standId = null;

	List<Basket> baskets;
	
	List<String> purchasedBasket;


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
	
	public List<String> getPurchasedBaskets() {
		if (purchasedBasket == null) {
			purchasedBasket = new ArrayList<String>();
		}
		return purchasedBasket;
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
	
	public void keepBasketFruit( List<Basket> removalList) { 
		//keeping as report
		getPurchasedBaskets().add( "standId=" + standId + ", " + Arrays.toString(removalList.toArray())  );
	}
	
}
