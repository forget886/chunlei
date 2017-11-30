package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.wuxi.bean.vo.Car;

public class CarFactoryBean implements FactoryBean<Car>{

	private static final Logger logger = LoggerFactory.getLogger(CarFactoryBean.class);
	
	private String brand;
	
	public CarFactoryBean(){
		logger.info("实例化 carfactorybean...");
	}
	
	@Override
	public Car getObject() throws Exception {
		logger.info("使用factorybean的方式创建car实例");
		Car car = new Car();
		car.setBrand(brand);
		return car;
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
