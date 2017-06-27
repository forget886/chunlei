package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.wuxi.bean.vo.Car;

/**
 * 用javaConfig的方式申明一个bean
 * @author dasouche
 *
 */
@Configuration
//@Import()  //合并多个配置类
//@ImportResource("classpath:Xxx/xml") //引入xml配置文件 可用autowired自动注入bean
public class BeansFactory {

    public static final Logger logger = LoggerFactory.getLogger(BeansFactory.class);
    
	@Bean
	public Car getCar(){
		logger.info("使用java config方式创建car实例");
		Car car = new Car();
		car.setBrand("奔驰");
		car.setMaxSpeed(100);
		return car;
	}
}
