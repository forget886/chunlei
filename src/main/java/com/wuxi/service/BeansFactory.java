package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wuxi.bean.vo.Car;

/**
 * 用javaConfig的方式申明一个bean
 * @author dasouche
 *
 */
@Configuration
public class BeansFactory {

    public static final Logger logger = LoggerFactory.getLogger(BeansFactory.class);
    
	@Bean
	public Car getCar(){
		logger.info("new car");
		Car car = new Car();
		car.setBrand("奔驰");
		car.setMaxSpeed(100);
		return car;
	}
}
