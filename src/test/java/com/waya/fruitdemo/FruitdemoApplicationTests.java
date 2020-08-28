package com.waya.fruitdemo;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.waya.fruitdemo.model.Basket;
import com.waya.fruitdemo.model.Route;
import com.waya.fruitdemo.model.Stand;
import com.waya.fruitdemo.obj.Cherries;
import com.waya.fruitdemo.obj.Peaches;
import com.waya.fruitdemo.obj.Pear;

@SpringBootTest
class FruitdemoApplicationTests {

	Route routeGotheburgHelsinborg ;
	
	@BeforeEach
    public void setup() {
	  routeGotheburgHelsinborg = new Route();
	  routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(1)) ,  new Basket(new Peaches(3))) );
	  routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(4)) ,  new Basket(new Peaches(5))) );
	  routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(2)) ,  new Basket(new Peaches(1))) );
	  System.out.println( routeGotheburgHelsinborg.toString());
    }
	
	@Test
	void basicScenarioFindMinStand() {
		//Basic Information find the lowest possible stand
 		Stand minStand =  routeGotheburgHelsinborg.getStands().
		stream().min(Comparator.comparing(Stand::getSum)).orElseThrow(NoSuchElementException::new);
 		
 		System.out.println("Stand :" + minStand.getStandName() + " Total : " + minStand.getSum() ); 
	}
	
	@Test
	void scenarioOnePrintFirtStand() {
 		//Scenario 1 - Adding a new Fruit
		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Pear(12)) ,  new Basket(new Peaches(1))) );
 		routeGotheburgHelsinborg.addToRoute( new Stand( new Basket(new Cherries(4)) ,  new Basket(new Peaches(7))) );

 		Stand firstStand = routeGotheburgHelsinborg.getStands().stream().findFirst()
        .get();
 		
 		System.out.println("Scenario 1 - Stand Name : "  + firstStand.getStandName()  + ", Price : " + firstStand.getSum());
	} 
	
 
}
