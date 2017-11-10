package com.wuxi.service;

import org.junit.Test;

import com.wuxi.util.JsonUtil;

public class SerializeTest {

	@Test
	public void serialize(){
		System.out.println(JsonUtil.toJson(new Car(0)));
	}
	
	@Test
	public void extend(){
		Car car = new Car(1);
		System.out.println(car instanceof Car);
		System.out.println(car instanceof Shop);
		Shop shop = new Shop(2);
		System.out.println(shop instanceof Shop);
		System.out.println(shop instanceof Car);
	}
}

class Shop extends Car{

	public Shop(int brand) {
		super(brand);
	}
	
}

class Car{
	
	public Car(int brand){
		this.brand = brand;
	}
	
	private int brand;

	public void setBrand(int brand) {
		this.brand = brand;
	}
	
	public int getBrand1(){
		return 1;
	}
	
	public int getBrand2(){
		return 2;
	}
	
}