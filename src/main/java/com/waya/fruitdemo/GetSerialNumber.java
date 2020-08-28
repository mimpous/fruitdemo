package com.waya.fruitdemo;

public class GetSerialNumber {
	
	GetSerialNumber() {				
	}
	
	public static int SERIAL_NO = 1;
	
	public static String getStandName() {
		return Constants.STAND_NAME + "_" + SERIAL_NO++;
	}
	 

}
