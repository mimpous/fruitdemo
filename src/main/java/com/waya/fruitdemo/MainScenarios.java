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

	// Fruit Baskets
	static List<Basket> pelleBasket = new ArrayList<Basket>();
	static List<Basket> kajsaBasket = new ArrayList<Basket>();
	static List<Basket> friendBasket = new ArrayList<Basket>();

	// Initialize Stands
	static Route routeGotheburgHelsinborg = new Route();

	public static void main(String[] args) {

		MainScenarios demo = new MainScenarios();
		demo.loadRoutes();

		demo.basicInfo();

		// add Pear Stands
		demo.loadExtraFruitAndRoutes();

		// Scenario 1 - Find First Stand
		demo.scenario1();

		// Scenario 2 - Print out Stand , Prices
		demo.scenario2();

		// Scenario 3, modifications
		demo.scenario3();

		// Scenario 4 modification
		demo.scenario4();

	}

	private void basicInfo() {
		// Basic Information, find min stand
		Stand minStand = findMinStandAndReturn();

		System.out.println("Purchase fruits from Stand :" + minStand.getStandName());
		System.out.println(
				"****************************************************************************************************");
		System.out.println("\n");
		addFruitsToBasketFromStand(pelleBasket, minStand, item -> item.getFruit() instanceof Cherries);
		addFruitsToBasketFromStand(kajsaBasket, minStand, item -> item.getFruit() instanceof Peaches);

	}

	private void scenario1() {
		Stand firstStand = getFirstFromList();

		System.out.println("Scenario 1 - First Stand From List has Pears");
		System.out.println(
				"****************************************************************************************************");
		System.out.println("Stand Name  : " + firstStand.getStandName() + ", Price : " + firstStand.getSum());

		// Purchase Pear (Scenario 1)
		purchasePear(pelleBasket, item -> item.getFruit() instanceof Pear);
		purchasePear(kajsaBasket, item -> item.getFruit() instanceof Pear);
		System.out.println(
				"****************************************************************************************************");
		System.out.println("\n\n");
	}

	private void scenario2() {
		System.out.println("Scenario 2 - Available Stands");
		System.out.println(
				"****************************************************************************************************");
		System.out.println(routeGotheburgHelsinborg.toString());

		System.out.println(
				"****************************************************************************************************");
		System.out.println("\nPeles basket ");
		System.out.println("----------------------------------------------------------------");
		pelleBasket.forEach(System.out::println);

		System.out.println("\nkajsa basket");
		System.out.println("----------------------------------------------------------------");
		kajsaBasket.forEach(System.out::println);
		System.out.println("\n");
	}

	private void scenario3() {
		System.out.println("Scenario 3 - Stands and purchased");
		System.out.println(
				"****************************************************************************************************");
		routeGotheburgHelsinborg.getStands().stream().map(i -> i.getPurchasedBaskets()).distinct()
				.forEach(System.out::println);
	}

	private void scenario4() {

		System.out.println("\nScenario 4 finding route for remaining fruits");
		System.out.println(
				"****************************************************************************************************");
		Stand newMinStand = findMinStandAndReturn();
		addFruitsToBasketFromStand(friendBasket, newMinStand);
		System.out.println("Friend basket ");
		System.out.println("----------------------------------------------------------------");
		friendBasket.forEach(System.out::println);
	}

	public void loadRoutes() {
		routeGotheburgHelsinborg.addToRoute(new Stand(new Basket(new Cherries(1)), new Basket(new Peaches(3))));
		routeGotheburgHelsinborg.addToRoute(new Stand(new Basket(new Cherries(4)), new Basket(new Peaches(5))));
		routeGotheburgHelsinborg.addToRoute(new Stand(new Basket(new Cherries(2)), new Basket(new Peaches(1))));

		System.out.println("Stands loaded.");
		System.out.println(routeGotheburgHelsinborg.toString());
		System.out.println(
				"****************************************************************************************************");

	}

	public void loadExtraFruitAndRoutes() {
		// Scenario 1 - Adding a new Fruit and an extra Stand with Cherries
		routeGotheburgHelsinborg.addToRoute(new Stand(new Basket(new Pear(2)), new Basket(new Peaches(2))));
		routeGotheburgHelsinborg.addToRoute(new Stand(new Basket(new Cherries(4)), new Basket(new Pear(3))));
		System.out.println("Stands loaded.");
		System.out.println(routeGotheburgHelsinborg.toString());
		System.out.println(
				"****************************************************************************************************");
		System.out.println("\n");
	}

	private Stand findMinStandAndReturn() {
		// Basic Information find the lowest possible stand
		Stand minStand = routeGotheburgHelsinborg.getStands().stream().filter(m -> m.getSum() > 0)
				.min(Comparator.comparing(Stand::getSum)).orElseThrow(NoSuchElementException::new);

		System.out.println("Stand Name  with the minimum total cost is : " + minStand.getStandName() + " Total : "
				+ minStand.getSum());
		System.out.println(
				"****************************************************************************************************");
		System.out.println("\n");

		return minStand;
	}

	private void addFruitsToBasketFromStand(List<Basket> fruitBasket, Stand stand, Predicate<? super Basket> p) {

		List<Basket> bli = stand.getBaskets().stream().filter(p).collect(Collectors.toList());

		// remove fruit basket from stand
		// modification for scenario3, added extra function so we can keep all purcased
		stand.keepBasketFruit(bli);
		stand.getBaskets().removeAll(bli);

		// purchase fruit basket
		fruitBasket.addAll(bli);

	}

	private void addFruitsToBasketFromStand(List<Basket> fruitBasket, Stand stand) {

		List<Basket> bli = stand.getBaskets().stream().collect(Collectors.toList());

		// remove fruit basket from stand
		// modification for scenario3, added extra function so we can keep all purcased
		stand.keepBasketFruit(bli);
		stand.getBaskets().removeAll(bli);

		// purchase fruit basket
		fruitBasket.addAll(bli);

	}

	private static Stand getFirstFromList() {
		List<Stand> standsWithPears = routeGotheburgHelsinborg.getStands().stream()
				.filter(a -> a.getBaskets().stream().map(b -> b.getFruit()).anyMatch(Pear.class::isInstance)).limit(1)
				.collect(Collectors.toList());
		return standsWithPears.get(0);
	}

	private void purchasePear(List<Basket> fruitBasket, Predicate<? super Basket> p) {

		List<Stand> standsWithPears = routeGotheburgHelsinborg.getStands().stream()
				.filter(a -> a.getBaskets().stream().map(b -> b.getFruit()).anyMatch(Pear.class::isInstance)).limit(1)
				.collect(Collectors.toList());

		Optional<Stand> firstElement = standsWithPears.stream().findFirst();
		addFruitsToBasketFromStand(fruitBasket, firstElement.get(), item -> item.getFruit() instanceof Pear);

	}

}
