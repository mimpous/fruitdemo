package com.waya.fruitdemo;

import java.util.concurrent.ThreadLocalRandom;

public class GetSerialNumber {
	
	GetSerialNumber() {
	}
	
	public static String getStandName() {
		return Constants.STAND_NAME + "_" + getRandomStandName();
	}
	private static int getRandomStandName() {
		return Math.abs( ThreadLocalRandom.current().nextInt());
	}

}
