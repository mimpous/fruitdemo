package com.waya.fruitdemo.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

	public List<Stand> stands = new ArrayList<Stand>();
	  
	public void addToRoute( Stand theStand ) {
		stands.add( theStand );
	}

	public List<Stand> getStands() {
		return stands;
	}
	
	@Override
	public String toString() {
		return "Route [stands=" + stands + "]";
	}
	
}
