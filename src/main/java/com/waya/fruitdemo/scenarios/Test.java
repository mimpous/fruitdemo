package com.waya.fruitdemo.scenarios;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.waya.fruitdemo.model.Basket;
import com.waya.fruitdemo.model.Route;
import com.waya.fruitdemo.model.Stand;
import com.waya.fruitdemo.obj.Cherries;
import com.waya.fruitdemo.obj.Peaches;
import com.waya.fruitdemo.obj.Pear;

public class Test {

	public static void main(String[] args) { 
	 
		//Initialize Stands
		Route routeGotheburgHelsinborg = new Route();
		
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(1)) ,  new Basket(new Peaches(3))) );
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(4)) ,  new Basket(new Peaches(5))) );
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(2)) ,  new Basket(new Peaches(1))) );

		//Print Stands with values
		System.out.println( routeGotheburgHelsinborg.toString());	
		
		//Basic Information find the lowest possible stand
 		Stand minStand =  routeGotheburgHelsinborg.getStands().
		stream().min(Comparator.comparing(Stand::getSum)).orElseThrow(NoSuchElementException::new);
 		
 		System.out.println( minStand.getStandName() + " Total : " + minStand.getSum() ); 
 		
 		
 		//Scenario 1 - Adding a new Fruit and an extra Stand with Cheries
 		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Pear(12)) ,  new Basket(new Peaches(1))) );
 		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(4)) ,  new Basket(new Peaches(7))) );
 		
 		
 		Stand firstStand = routeGotheburgHelsinborg.getStands().stream().findFirst()
        .get();
 		
 		System.out.println("Scenario 1 - Stand Name : "  + firstStand.getStandName()  + ", Price : " + firstStand.getSum());
	
 		//Scenario2
 		//Purchase to fruit basket 		
 		List<Stand> fruitBasket = routeGotheburgHelsinborg.getStands().stream()
 		        .filter(a -> a.getBaskets().stream()  
 		                .flatMap(b -> b.getFruits().stream() )  
 		                .anyMatch(Pear.class::isInstance))  
 		        .collect(Collectors.toList());

 		System.out.println( "fruitbasket " + fruitBasket );
	
	}

}
