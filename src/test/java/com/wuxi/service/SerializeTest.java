package com.wuxi.service;

import org.junit.Test;

import com.wuxi.util.JsonUtil;

public class SerializeTest {

	@Test
	public void serialize(){
		System.out.println(JsonUtil.toJson(new Car(0)));
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