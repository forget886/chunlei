package com.wuxi.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wuxi.bean.vo.Car;

public class AnnotationApplicationContext {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeansFactory.class);
		//注册多个config配置类
		//context.register(annotatedClasses);
		//刷新容器以应用新注册的配置类
		//context.refresh();
		Car car = context.getBean(Car.class);
		System.out.println(car);
	}
}
