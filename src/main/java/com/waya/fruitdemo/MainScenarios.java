package com.waya.fruitdemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.waya.fruitdemo.model.Basket;
import com.waya.fruitdemo.model.Route;
import com.waya.fruitdemo.model.Stand;
import com.waya.fruitdemo.obj.Cherries;
import com.waya.fruitdemo.obj.Peaches;
import com.waya.fruitdemo.obj.Pear;

public class MainScenarios {
	
	//Fruit Basket 
	static List<Basket> pelleBasket = new ArrayList<Basket>();
	static List<Basket> kajsaBasket = new ArrayList<Basket>();
	
	//Initialize Stands
	static Route routeGotheburgHelsinborg = new Route();

	public void loadRoutes() {
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(1)) ,  new Basket(new Peaches(3))) );
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(4)) ,  new Basket(new Peaches(5))) );
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(2)) ,  new Basket(new Peaches(1))) ); 
		
		System.out.println("Stands loaded.");
		System.out.println( routeGotheburgHelsinborg.toString() );
		System.out.println("****************************************************************************************************");

	}
	
	public void loadExtraFruitAndRoutes() {
 		//Scenario 1 - Adding a new Fruit and an extra Stand with Cherries
 		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Pear(2)) ,  new Basket(new Peaches(2))) );
 		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(4)) ,  new Basket(new Pear(3))) );
		System.out.println("Stands loaded.");
		System.out.println( routeGotheburgHelsinborg.toString() );
		System.out.println("****************************************************************************************************");
		System.out.println("\n");
	}
	
	private Stand findMinStandAndReturn() {
		//Basic Information find the lowest possible stand
 		Stand minStand =  routeGotheburgHelsinborg.getStands().
		stream().min(Comparator.comparing(Stand::getSum)).orElseThrow(NoSuchElementException::new);
 		
 		System.out.println( "Basic Information : Stand Name  with the minimum total cost is : " + minStand.getStandName() + " Total : " + minStand.getSum() ); 
		System.out.println("****************************************************************************************************");
		System.out.println("\n");

		return minStand;
	}
	
	private void addFruitsToBasketFromStand(List<Basket> fruitBasket,  Stand stand , Predicate<? super Basket> p  ) {
		
		List<Basket> bli=   stand.getBaskets().stream()
							.filter(p) 
							.collect(Collectors.toList());
		
		//remove fruit basket from stand
		stand.getBaskets().removeAll( bli );
		
		//purchase fruit basket
		fruitBasket.addAll(bli);
		 
	}
	
	public static void main(String[] args) { 
		
		MainScenarios demo = 	new MainScenarios();
		demo.loadRoutes();
		
		//Basic Information, find min stand
		Stand minStand = demo.findMinStandAndReturn();
		 
 
		System.out.println("Purchase fruits from Stand :" + minStand.getStandName());
		System.out.println("****************************************************************************************************");
		System.out.println("\n");
		demo.addFruitsToBasketFromStand(pelleBasket,  minStand  , item -> item.getFruit() instanceof Cherries  );
		demo.addFruitsToBasketFromStand(kajsaBasket,  minStand  , item -> item.getFruit() instanceof Peaches  );
		
		//add Pear Stands
		demo.loadExtraFruitAndRoutes();
		
		//Scenario 1 - Find First Stand
 		Stand firstStand = getFirstFromList();
 		
 		System.out.println( "Scenario 1 - First Stand From List has Pears");
		System.out.println("****************************************************************************************************");
	    System.out.println("Stand Name  : "  + firstStand.getStandName()  + ", Price : " + firstStand.getSum());
			
		
		//Purchase Pear (Scenario 1)
 		demo.purchasePear( pelleBasket , item -> item.getFruit() instanceof Pear);
		demo.purchasePear( kajsaBasket , item -> item.getFruit() instanceof Pear);
		System.out.println("****************************************************************************************************");
		System.out.println("\n\n");
 	
		//Scenario 2 - Print out Stand , Prices 
		System.out.println( "Scenario 2 - Available Stands");
		System.out.println("****************************************************************************************************");
		System.out.println( routeGotheburgHelsinborg.toString() );

		System.out.println("****************************************************************************************************");
		System.out.println("\nPeles basket ");
		System.out.println("----------------------------------------------------------------");
		pelleBasket.forEach(System.out::println);
		
		System.out.println("\nkajsa basket");
		System.out.println("----------------------------------------------------------------");
		kajsaBasket.forEach(System.out::println);
		
		
		
	}
	
	private static Stand getFirstFromList() {
		List<Stand> standsWithPears = routeGotheburgHelsinborg.getStands().stream()
		        .filter(a -> a.getBaskets().stream()  
		                .map(b -> b.getFruit()  )  
		                .anyMatch(Pear.class::isInstance)).limit(1)  
		        .collect(Collectors.toList());
		return standsWithPears.get(0);
	}
	
	private void purchasePear(List<Basket> fruitBasket, Predicate<? super Basket> p ) {
		
		List<Stand> standsWithPears = routeGotheburgHelsinborg.getStands().stream()
		        .filter(a -> a.getBaskets().stream()  
		                .map(b -> b.getFruit()  )  
		                .anyMatch(Pear.class::isInstance)).limit(1)  
		        .collect(Collectors.toList());
		
		Optional<Stand> firstElement = standsWithPears.stream().findFirst();
 		addFruitsToBasketFromStand(fruitBasket,  firstElement.get()  , item -> item.getFruit() instanceof Pear  );
	
	}
	
//	public static void main1(String[] args) { 
//		 
//
//
// 		 
//		//Initialize Stands
//		Route routeGotheburgHelsinborg = new Route();
//		
//
//		//Print Stands with values
//		System.out.println( routeGotheburgHelsinborg.toString());	
//		
//		//Basic Information find the lowest possible stand
// 		Stand minStand =  routeGotheburgHelsinborg.getStands().
//		stream().min(Comparator.comparing(Stand::getSum)).orElseThrow(NoSuchElementException::new);
// 		
// 		System.out.println( "Basic Information : Stand Name  with the minimum total cost is : " + minStand.getStandName() + " Total : " + minStand.getSum() ); 
// 		
// 		
//
// 		pelleBasket.addAll( minStand.getBaskets().stream()
// 				.flatMap(b -> b.getFruits().stream() ) 
// 				.filter(item -> item instanceof Cherries)
// 				 .collect(Collectors.toList()));
//		
//		  
// 		kajsaBasket.addAll( minStand.getBaskets().stream()
// 				.flatMap(b -> b.getFruits().stream() ) 
// 				.filter(item -> item instanceof Peaches)
// 				 .collect(Collectors.toList()));
// 		 minStand.getBaskets().clear();
//
// 		 
// 		 
// 		 System.out.println("Pele" + pelleBasket.toString());
// 		 System.out.println("Kajsa" + kajsaBasket.toString());
// 		 

// 		
// 		System.out.println( "Display Stands :" + routeGotheburgHelsinborg + "\n");
// 		
// 		Stand firstStand = routeGotheburgHelsinborg.getStands().stream().findFirst()
//        .get();
// 		
// 		 
// 		System.out.println("Scenario 1 - Stand Name  : "  + firstStand.getStandName()  + ", Price : " + firstStand.getSum());
//	
// 		//Scenario2
// 		//Purchase to fruit basket 		
// 		List<Stand> searchFruitPear = routeGotheburgHelsinborg.getStands().stream()
// 		        .filter(a -> a.getBaskets().stream()  
// 		                .flatMap(b -> b.getFruits().stream() )  
// 		                .anyMatch(Pear.class::isInstance))  
// 		        .collect(Collectors.toList());
// 		
// 		routeGotheburgHelsinborg.getStands().remove( searchFruitPear.get(0) );
// 		
// 		
// 		
// 		//fruitBasket.addAll( searchFruitPear);
//
//// 		System.out.println( "Scenario 2 : fruitbasket " + fruitBasket );
// 		 
// 		
// 		//System.out.println(  "Scenario 2 : fruitbasket " + fruitBasket  );
// 		
// 		System.out.println("remaining" + routeGotheburgHelsinborg.getStands());
//	
//	}
}
