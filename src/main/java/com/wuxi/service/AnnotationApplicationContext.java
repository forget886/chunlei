package com.wuxi.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wuxi.bean.vo.Car;

public class AnnotationApplicationContext {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansFactory.class);
		Car car = context.getBean(Car.class);
		System.out.println(car);
	}
}
