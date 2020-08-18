package com.waya.fruitdemo.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

	public List<Stand> stands = new ArrayList<Stand>();
	
	public List<Basket> purchacedFruitBasket = new ArrayList<Basket>();

	public void addToRoute( Stand theStand ) {
		stands.add( theStand );
	}

	@Override
	public String toString() {
		return "Route [stands=" + stands + "]";
	}

	public List<Stand> getStands() {
		return stands;
	}
	
	
	
}
